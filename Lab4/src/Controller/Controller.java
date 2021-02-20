package Controller;

import Model.State.ProgramState;
import Model.Values.ReferenceValue;
import Model.Values.Value;
import Repository.IRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller implements IController
{
    private IRepository repository;
    private ExecutorService executor;

    public Controller(IRepository repository)
    {
        this.repository=repository;
    }


    Map<Integer, Value> safeGarbageCollector(List<Integer> addressesFromSymbolTable, Map<Integer,Value> heap)
    {
        return heap.entrySet()
                .stream()
                .filter(e->addressesFromSymbolTable.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static List<Integer> getAddrFromSymTable (Collection<Value> symTableValues, Collection<Value> heap){
        List<Integer> l = Stream.concat(
                heap.stream()
                        .filter(v-> v instanceof ReferenceValue)
                        .map(v-> {ReferenceValue v1 = (ReferenceValue) v; return v1.getAddress();}),
                symTableValues.stream()
                        .filter(v-> v instanceof ReferenceValue)
                        .map(v-> {ReferenceValue v1 = (ReferenceValue) v; return v1.getAddress();})
        )
                .collect(Collectors.toList());

        return l;
    }

    List<ProgramState> removeCompletedPrg(List<ProgramState> inPrgList)
    {
        return inPrgList.stream().filter(v->v.isNotCompleted()).collect(Collectors.toList());
    }

    public void oneStepForAllPrg(List<ProgramState> prgList) throws Exception{
        prgList.forEach(p-> {
            try {
                repository.logPrgStateExec(p);
                System.out.println(p.toString());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        List<Callable<ProgramState>> callList = prgList.stream().
                map((ProgramState p) -> (Callable<ProgramState>)(()->{return p.oneStep();})).collect(Collectors.toList());
        try {
            List<ProgramState> newProgramList = executor.invokeAll(callList).stream()
                    .map(programStateFuture -> {try{
                        return programStateFuture.get();
                    } catch (ExecutionException e) {
                        throw new RuntimeException("Thread exception: " + e.getMessage());
                    } catch (InterruptedException e) {
                        throw new RuntimeException("Thread exception: " + e.getMessage());
                    }
                    })
                    .filter(programState -> programState != null)
                    .collect(Collectors.toList());
            prgList.addAll(newProgramList);
            prgList.forEach(p-> {
                try {
                    repository.logPrgStateExec(p);
                    System.out.println(p.toString());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });
            repository.setProgramList(prgList);
        }
        catch (InterruptedException e)
        {
            throw new Exception("Thread exception");
        }
        catch (RuntimeException e)
        {
            throw new Exception("Thread exception");
        }
    }



    @Override
    public void allSteps() throws Exception 
    {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> prgList=removeCompletedPrg(repository.getProgramsList());

        while(prgList.size() > 0){
            Collection<Value> allSymTbl = new ArrayList<>();
            prgList.forEach(v-> allSymTbl.addAll(v.getSymbolTable().getContent().values()));
            List<Integer> addr = getAddrFromSymTable(allSymTbl, prgList.get(0).getHeap().getHeapV().values());
            Map<Integer, Value> newHeap = safeGarbageCollector(addr, prgList.get(0).getHeap().getHeapV());
            prgList.get(0).getHeap().setHeap(newHeap);
            oneStepForAllPrg(prgList);
            prgList=removeCompletedPrg(repository.getProgramsList());
        }
        executor.shutdownNow();
        repository.setProgramList(prgList);
        
        
    }
}

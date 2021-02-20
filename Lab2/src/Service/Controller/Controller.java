package Service.Controller;

import Service.Model.Compare;
import Service.Repository.Repository;

public class Controller {
    private Repository repo;

    public Controller(Repository repo)
    {
        this.repo = repo;
    }

    public void addElem(Compare obj)
    {
        repo.add(obj);
    }

    public void delete(int index)
    {
        repo.delete(index);
    }

    public void filterByRepCost(float repCost)
    {
        Compare [] arr = this.repo.getAll();
        int arrSize = this.repo.getMax();
        for(int i =0;i<=arrSize;i++)
        {
            if (arr[i].compare(repCost))
            {
                System.out.print(arr[i]);
            }
        }
    }
}

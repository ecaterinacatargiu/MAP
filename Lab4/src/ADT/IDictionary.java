package ADT;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.Map;

public interface IDictionary<T1,T2>
{
    void add(T1 t1, T2 t2) throws Exception;
    void update(T1 t1, T2 t2) throws Exception;

    T2 lookFor(T1 id) throws Exception;

    boolean isDefined(T1 id);
    String toString();
    public Map<T1, T2> getContent();
}

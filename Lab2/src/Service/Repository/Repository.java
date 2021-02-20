package Service.Repository;
import Service.Model.Compare;

public interface Repository {
    void add(Compare obj);
    void delete(int i);
    Compare [] getAll();
    int getMax();
}

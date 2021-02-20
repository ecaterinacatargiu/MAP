package Model.Structure;

public interface MyILatch {

    boolean isDefined(int id);
    int lookup(int id);
    void update(int id, int val);
    int addToLatch(int val);

}

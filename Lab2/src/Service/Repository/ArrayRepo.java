package Service.Repository;
import Service.Model.Compare;


public class ArrayRepo implements Repository{
    Compare [] arr;
    int index;

    public ArrayRepo(int length)
    {
        arr = new Compare[length];
        index =0;
    }

    @Override
    public void add(Compare obj) {
        if(this.index<arr.length)
        {
            arr[index]=obj;
            index++;
        }
    }

    @Override
    public void delete(int i) {
        if((i>=0)&&(i<index))
        {
            arr[index] = arr[index-1];
            index--;
        }
    }

    @Override
    public Compare[] getAll() {
        return arr;
    }

    @Override
    public int getMax() {
        return this.index-1;
    }

    public int getLength(){
        return arr.length;
    }
}

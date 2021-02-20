package Service.Model;

public class BigCar implements Compare {
    private float repCost;
    public BigCar(float repCost)
    {
        this.repCost = repCost;
    }
    public float getRepCost()
    {
        return this.repCost;
    }
    public void setRepCost(float newRepCost)
    {
        this.repCost = newRepCost;
    }

    @Override
    public boolean compare(float x) {
        return this.repCost>x;
    }

    @Override
    public String toString(){
        return "Big Car:"+Float.toString(this.repCost)+", ";
    }

}

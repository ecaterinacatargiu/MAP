package Service.Model;

public class Car implements Compare{
    private float repCost;
    public Car(float repCost)
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
        return "Car: "+Float.toString(this.repCost)+", ";
    }
}

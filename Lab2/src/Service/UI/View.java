package Service.UI;

import Service.Controller.Controller;
import Service.Model.BigCar;
import Service.Model.Car;
import Service.Model.Compare;
import Service.Model.Motocycle;
import Service.Repository.ArrayRepo;
import Service.Repository.Repository;

public class View {
    public static void main(String[] args)
    {
       // System.out.println("HERE IS THE MAIN");
        Repository repo = new ArrayRepo(20);
        Controller controller = new Controller(repo);
        Compare car1 = new Car(2000);
        Compare car2 = new Car(350);
        Compare car3 = new Car(1250);
        Compare bigCar1 = new BigCar(4500);
        Compare bigCar2 = new BigCar(6000);
        Compare motor1 = new Motocycle(2000);
        Compare motor2 = new Motocycle(820);

        controller.addElem(car1);
        controller.addElem(car2);
        controller.addElem(car3);
        controller.addElem(bigCar1);
        controller.addElem(bigCar2);
        controller.addElem(motor1);
        controller.addElem(motor2);

        controller.filterByRepCost(1000);
    }

}

package View;

import Controller.Controller;

public class RunCommand extends Command {

    private Controller controller;

    public RunCommand(String key, String description, Controller ctrl)
    {
        super(key, description);
        this.controller = ctrl;
    }

    @Override
    public void execute() {
        try{
            controller.allSteps();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}

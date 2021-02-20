package View;

import java.net.CookieHandler;
import java.util.HashMap;
import java.util.Scanner;

public class TextMenu {

    private HashMap<String, Command> commands;

    public TextMenu() { commands = new HashMap<>(); }

    public void addCommand(Command c) { commands.put(c.getKey(), c); }

    private void printMenu()
    {
        for(Command com: commands.values())
        {
            String line = String.format("%4s : %s", com.getKey(), com.getDescription());
            System.out.println(line);
        }
    }

    void show()
    {
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            printMenu();
            System.out.printf("Input and option: ");
            String key = scanner.nextLine();
            Command comm = commands.get(key);
            if(comm == null)
            {
                System.out.printf("Invalid command");
                continue;
            }
            comm.execute();
        }
    }
}

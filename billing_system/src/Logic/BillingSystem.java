package Logic;
import java.io.Serializable;
import utilities.Console;

//objets
import Models.*;

import java.util.ArrayList;
import java.util.HashMap;

public class BillingSystem implements Serializable{

    //Scanner entry = new Scanner(System.in);
    private HashMap<String, Client> clients = new HashMap<>();
    private HashMap<String, Product> products = new HashMap<>();
    private ArrayList<Invoice> invoices = new ArrayList<>();

    private int customerCounter = 1;
    private int productsCounter = 1;
    private int invoicesCounter = 1;

    public void agregarCliente(){

        System.out.println("*********************************************************************");
        String id = "CLI-" +customerCounter;
        customerCounter++;

        System.out.println(">> Se ha generado el ID: " + id);
        String name = Console.readString("Nombre completo: ");
        String adress = Console.readString("Direccion: ");
        String email = Console.readString("Email: ");

        Client client = new Client(id, name, adress, email);

        clients.put(client.getId(), client);

    }


}

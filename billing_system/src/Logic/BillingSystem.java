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

    public void addClient(){
        System.out.println("*********************************************************************");
        String id = "CLI-" +customerCounter;
        customerCounter++;

        System.out.println(">> Se ha generado el ID: " + id);
        String name = Console.readString("Nombre completo: ");
        String adress = Console.readString("Direccion: ");
        String email = Console.readString("Email: ");

        Client client = new Client(id, name, adress, email);

        clients.put(client.getId(), client);
        System.out.println("Cliente agregado exitosamente");
        System.out.println("*********************************************************************");
    }

    public void addProduct(){
        System.out.println("*********************************************************************");
        String id = "PRO-" +productsCounter;
        productsCounter++;

        System.out.println(">> Se ha generado el ID: " + id);

        String name = Console.readString("Nombre del producto: ");
        String description = Console.readString("Descripcion del producto: ");
        double precio = Console.readDouble("Precio del producto: ");
        int Stock = Console.readInt("Stock inicial del producto: ");

        Product p = new Product(id, name, description, precio, Stock);
        products.put(p.getId(), p);

        System.out.println("Producto agregado exitosamente");
    }

    public void showCustomerCatalog(){
        System.out.println("------Clientes disponibles------");
        System.out.println("  ID  "+ "     NOMBRE ");
        for(Client c: clients.values()){
            System.out.println("  " + c.getId() + "   " + c.getName() + " ");
        }
    }

}

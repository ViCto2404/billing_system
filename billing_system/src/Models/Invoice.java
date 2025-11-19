package Models;
import java.io.Serializable;
import java.util.ArrayList;

public class Invoice implements Serializable{

    private String id;
    private String fecha;
    private Client client;
    private ArrayList<ItemInvoice> items;
    private double total;
    private String state;

    public Invoice(String id, String fecha, Client client){

        this.id = id;
        this.fecha = fecha;
        this.client = client;
        this.items = new ArrayList<>();
        this.total = 0.0;
        this.state = "Pagada";
    }

    public void agregarItem(ItemInvoice item){
        this.items.add(item);
        this.total += item.getSubtotal();

    }

    public String getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public Client getClient() {
        return client;
    }

    public ArrayList<ItemInvoice> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setItems(ArrayList<ItemInvoice> items) {
        this.items = items;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setId(String id) {
        this.id = id;
    }
}

package Models;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Representa una factura del sistema.
 * Contiene referencia al cliente, lista de items, total y estado.
 * Implementa Serializable para permitir persistencia si se desea.
 */
public class Invoice implements Serializable{

    // Identificador único de la factura (ej. "INV-1")
    private String id;
    // Fecha de emisión de la factura (formato libre, se almacena como String)
    private String fecha;
    // Cliente al que se le emite la factura
    private Client client;
    // Lista de items que componen la factura (productos, cantidad, subtotal)
    private ArrayList<ItemInvoice> items;
    // Total acumulado de la factura (suma de subtotales de items)
    private double total;
    // Estado de la factura (ej. "Pagada", "Pendiente", etc.)
    private String state;

    /**
     * Constructor: inicializa id, fecha, cliente y crea la lista vacía de items.
     * Inicializa total en 0.0 y establece un estado por defecto ("Pagada").
     * @param id Identificador de la factura
     * @param fecha Fecha de la factura
     * @param client Cliente asociado
     */
    public Invoice(String id, String fecha, Client client){

        this.id = id;
        this.fecha = fecha;
        this.client = client;
        this.items = new ArrayList<>(); // lista vacía para agregar ItemInvoice posteriormente
        this.total = 0.0; // total inicial
        this.state = "Pendiente"; // estado por defecto
    }

    /**
     * Agrega un item a la factura y actualiza el total sumando el subtotal del item.
     * @param item ItemInvoice a agregar
     */
    public void agregarItem(ItemInvoice item){
        this.items.add(item);
        this.total += item.getSubtotal();

    }

    // -------------------- Getters --------------------

    /** Devuelve el ID de la factura */
    public String getId() {
        return id;
    }

    /** Devuelve la fecha de la factura */
    public String getFecha() {
        return fecha;
    }

    /** Devuelve el cliente asociado a la factura */
    public Client getClient() {
        return client;
    }

    /** Devuelve la lista de items de la factura */
    public ArrayList<ItemInvoice> getItems() {
        return items;
    }

    /** Devuelve el total actual de la factura */
    public double getTotal() {
        return total;
    }

    /** Devuelve el estado de la factura */
    public String getState() {
        return state;
    }

    // -------------------- Setters --------------------

    /** Asigna un nuevo estado a la factura */
    public void setState(String state) {
        this.state = state;
    }

    /** Reemplaza la lista de items de la factura */
    public void setItems(ArrayList<ItemInvoice> items) {
        this.items = items;
    }

    /** Asigna un nuevo cliente a la factura */
    public void setClient(Client client) {
        this.client = client;
    }

    /** Asigna una nueva fecha a la factura */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /** Asigna un nuevo ID a la factura */
    public void setId(String id) {
        this.id = id;
    }
}

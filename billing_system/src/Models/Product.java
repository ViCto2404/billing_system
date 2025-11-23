package Models;
import java.io.Serializable;

/**
 * Modelo que representa un producto disponible para la venta.
 * Implementa Serializable para permitir persistencia si se desea.
 */
public class Product implements Serializable{

    // Identificador único del producto (ej. "PRO-1")
    private String id;
    // Nombre comercial del producto
    private String name;
    // Descripción breve del producto
    private String description;
    // Precio unitario del producto
    private double price;
    // Cantidad disponible en inventario
    private int stock;

    /**
     * Constructor: inicializa los atributos del producto.
     * @param id Identificador único
     * @param name Nombre del producto
     * @param description Descripción del producto
     * @param price Precio unitario
     * @param stock Cantidad en inventario
     */
    public Product(String id, String name, String description, double price, int stock){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;

    }

    // ------------------ Getters ------------------

    /** Devuelve el ID del producto */
    public String getId() {
        return id;
    }

    /** Devuelve el nombre del producto */
    public String getName() {
        return name;
    }

    /** Devuelve la descripción del producto */
    public String getDescription() {
        return description;
    }

    /** Devuelve el precio unitario del producto */
    public double getPrice() {
        return price;
    }

    /** Devuelve el stock disponible del producto */
    public int getStock() {
        return stock;
    }

    // ------------------ Setters ------------------

    /** Asigna un nuevo ID al producto */
    public void setId(String id) {
        this.id = id;
    }

    /** Asigna un nuevo nombre al producto */
    public void setName(String name) {
        this.name = name;
    }

    /** Asigna una nueva descripción al producto */
    public void setDescription(String description) {
        this.description = description;
    }

    /** Asigna un nuevo precio al producto */
    public void setPrice(double price) {
        this.price = price;
    }

    /** Asigna un nuevo valor de stock al producto */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Representación en texto del producto.
     * Método sobrescrito de Object.
     */
    @Override
    public String toString(){
        return String.format("ID: ", id, "Nombre: ", name, "Price: ", price, "Stock: ", stock);
    }

}

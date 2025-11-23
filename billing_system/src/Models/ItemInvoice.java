package Models;
import java.io.Serializable;

/**
 * Representa una línea (item) de una factura.
 * Contiene el producto vendido, la cantidad y el subtotal (precio * cantidad).
 * Implementa Serializable para permitir persistencia si se desea.
 */
public class ItemInvoice implements Serializable{

    // Producto asociado a esta línea de la factura
    private Product product;
    // Cantidad vendida del producto
    private int quantity;
    // Subtotal calculado (precio del producto * cantidad)
    private double subtotal;

    /**
     * Constructor: inicializa producto y cantidad.
     * El parámetro 'subtotal' se recibe pero el valor final se recalcula
     * mediante updateSubtotal() para garantizar consistencia.
     * @param product Producto vendido
     * @param quantity Cantidad vendida
     * @param subtotal Valor inicial del subtotal (no se usa directamente)
     */
    public ItemInvoice(Product product, int quantity, double subtotal){
        this.product = product;
        this.quantity = quantity;
        this.updateSubtotal(); // recalcula el subtotal en base al precio actual del producto
    }

    /**
     * Recalcula el subtotal multiplicando el precio actual del producto por la cantidad.
     * Método auxiliar privado para mantener subtotal consistente cuando cambian producto o cantidad.
     */
    private void updateSubtotal(){
        this.subtotal = this.product.getPrice() * this.quantity;
    }

    // ------------------ Getters ------------------

    /** Devuelve el producto asociado a la línea */
    public Product getProduct() {
        return product;
    }

    /** Devuelve la cantidad vendida en esta línea */
    public int getQuantity() {
        return quantity;
    }

    /** Devuelve el subtotal calculado para esta línea */
    public double getSubtotal() {
        return subtotal;
    }

    // ------------------ Setters ------------------

    /** Asigna un nuevo producto y actualiza el subtotal en consecuencia */
    public void setProduct(Product product) {
        this.product = product;
        updateSubtotal();
    }

    /** Asigna una nueva cantidad y actualiza el subtotal en consecuencia */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        updateSubtotal();
    }

    /** Permite establecer manualmente el subtotal (uso poco frecuente) */
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * Representación en texto de la línea de factura.
     * Método sobrescrito de Object.
     */
    @Override
    public String toString()
    {
        return String.format("Nombre: ", product.getName(), "Cantidad: ", quantity, "Subtotal: ", subtotal);
    }

}

package Models;
import java.io.Serializable;

public class ItemInvoice implements Serializable{

    private Product product;
    private int quantity;
    private double subtotal;

    public ItemInvoice(Product product, int quantity, double subtotal){
        this.product = product;
        this.quantity = quantity;
        this.updateSubtotal();
    }

    private void updateSubtotal(){
        this.subtotal = this.product.getPrice() * this.quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setProduct(Product product) {
        this.product = product;
        updateSubtotal();
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        updateSubtotal();
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString()
    {
        return String.format("Nombre: ", product.getName(), "Cantidad: ", quantity, "Subtotal: ", subtotal);
    }

}

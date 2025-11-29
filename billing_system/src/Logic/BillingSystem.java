package Logic;
import java.io.Serializable;
import utilities.Console;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//objets
import Models.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Sistema principal de facturación.
 * Contiene colecciones de clientes, productos y facturas,
 * además de métodos para agregar, eliminar, buscar e imprimir registros.
 */
public class BillingSystem implements Serializable{

    //Scanner entry = new Scanner(System.in);

    // Mapa de clientes: clave = ID del cliente, valor = objeto Client
    private HashMap<String, Client> clients = new HashMap<>();
    // Mapa de productos: clave = ID del producto, valor = objeto Product
    private HashMap<String, Product> products = new HashMap<>();
    // Lista de facturas
    private ArrayList<Invoice> invoices = new ArrayList<>();

    // Contadores para generar IDs automáticos
    private int customerCounter = 1;
    private int productsCounter = 1;
    private int invoicesCounter = 1;

//----------------------------METODOS DE ADICION DE REGISTROS ---------------------------------------------------------

    /**
     * Solicita datos por consola y crea un nuevo cliente con ID autogenerado.
     */
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

    /**
     * Solicita datos por consola y crea un nuevo producto con ID autogenerado.
     */
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

    /**
     * Muestra un listado simple de clientes con su ID y nombre.
     */
    private void showCustomerCatalog(){
        System.out.println("------Clientes disponibles------");
        System.out.println("  ID  "+ "     NOMBRE ");
        for(Client c: clients.values()){
            System.out.println("  " + c.getId() + "   " + c.getName() + " ");
        }
    }

    /**
     * Muestra un listado simple de productos con su ID y nombre.
     */
    private void showProductCatalog(){
        System.out.println("------Clientes disponibles------");
        System.out.println("  ID  "+ "     NOMBRE ");
        for(Product p: products.values()){
            System.out.println("  " + p.getId() + "   " + p.getName() + " ");
        }
    }

    /**
     * Muestra un listado simple de facturas con ID, cliente y total.
     */
    private void showInvoicesCatalog(){
        System.out.println("------Facturas disponibles------");
        System.out.println("  ID  "+ "   Cliente         " + " Total ");
        System.out.println("----------------------------------------------");

        for(Invoice f: invoices){
            System.out.println("  " + f.getId() + "  " + f.getClient().getName() + "          " + f.getTotal());
        }
        System.out.println("----------------------------------------------");
    }

    /**
     * Crea una factura: selecciona cliente, agrega items y actualiza stock.
     * Valida existencia de clientes/productos y evita facturas vacías.
     */
    public void addInvoice(){
        System.out.println("*********************************************************************");

        if(clients.isEmpty() || products.isEmpty()){
            System.out.println("Necesitas tener clientes y productos para proceder con la creacion de las facturas");
            return;
        }

        String idFactura = "INV-" + invoicesCounter;
        invoicesCounter++;

        System.out.println(">> ID de factura generado " + idFactura);

        LocalDateTime actualDate = LocalDateTime.now();

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String date = actualDate.format(format);

        showCustomerCatalog();

        String idCliente = Console.readString("Ingresa el ID del cliente: ");

        Client clientReal = clients.get(idCliente);

        if (clientReal == null){
            System.out.println("Cliente no encontrado, operacion cancelada");
            invoicesCounter--;
        }

        System.out.println(">> Facturado a: "+ clientReal.getName());

        Invoice newInvoice = new Invoice(idFactura, date, clientReal);

        boolean selling = true;
        while (selling){

            showProductCatalog();

            String idProd = Console.readString("Ingresa el ID del producto que deseas agregar: ");
            Product prodReal = products.get(idProd);

            if(prodReal != null){
                if(prodReal.getStock() <= 0){
                    System.out.println("El producto " + prodReal.getName() + "se encuentra agotado");
                }else {
                    int cantidad = Console.readInt("Cantidad a llevar (Max " + prodReal.getStock() + "): ");

                    if(cantidad > 0 && cantidad <= prodReal.getStock()){
                        ItemInvoice item = new ItemInvoice(prodReal, cantidad, prodReal.getPrice());
                        newInvoice.agregarItem(item);
                        prodReal.setStock(prodReal.getStock() - cantidad);

                        System.out.println("Agregado a la factura. subtotal acumulado " + newInvoice.getTotal());
                    }else {
                        System.out.println("Cantidad invalida");
                    }
                }
            }else {
                System.out.println("Producto no encontrado");
            }

            String response = Console.readString("Ingresa n si deseas no agregar otro producto y cualquier otra letra" +
                    "si deseas agregar mas productos: ");
            if (response.equals("n")){
                selling = false;
            }
        }

        if(!newInvoice.getItems().isEmpty()){
            invoices.add(newInvoice);
            System.out.println("Factura terminada exitosamente. Total: $"+ newInvoice.getTotal());
        }else{
            System.out.println("Factura vacia, se ha cancelado");
            invoicesCounter--;
        }

    }

    // Getters para acceder a las colecciones desde otras clases (si es necesario)
    public HashMap<String, Client> getClients() {
        return clients;
    }

    public HashMap<String, Product> getProducts() {
        return products;
    }

    public ArrayList<Invoice> getInvoices() {
        return invoices;
    }

//----------------------------METODOS DE REMOCION DE REGISTROS ---------------------------------------------------------

    /**
     * Elimina un cliente si no tiene facturas asociadas.
     * Pide confirmación antes de eliminar.
     */
    public void removeCustomer()
    {
        System.out.println("*********************************************************************");
        System.out.println("------Remover clientes disponibles------");
        showCustomerCatalog();

        String id = Console.readString("Ingrese el ID del cliente que quiere eliminar: ");

        if (!clients.containsKey(id)){
            System.out.println("El Id que añadiste no existe");
            return;
        }

        boolean hasInvoices = false;
        for (Invoice f: invoices){
            if (f.getId().equals(id)){
                hasInvoices = true;
                break;
            }
        }

        if(hasInvoices){
            System.out.println("Accion denegada, no se puede eliminar el cliente " + id);
            System.out.println("Razon: este cliente tiene facturas registradas, primero debes eliminar facturas" +
                    " asociadas");
            return;
        }

        String confirmation = Console.readString("Seguro que deseas eliminar el cliente permanentemente, " +
                "escribe s para si o cualquier otra letra para no ");

        if(confirmation.equals("s")){
            clients.remove(id);
            System.out.println("Cliente elminado exitosamente");
        }else {
            System.out.println("Operacion cancelada");
        }
    }

    /**
     * Elimina un producto si no aparece en ninguna factura histórica.
     * Pide confirmación antes de eliminar.
     */
    public void removeProduct(){
        System.out.println("*******************************************************************");
        System.out.println("------Remover productos disponibles------");
        showProductCatalog();

        String id = Console.readString("Ingresa el ID del producto que quieres eliminar: ");

        if (!products.containsKey(id)){
            System.out.println("Error: El producto con el ID: " + id + " no existe");
            return;
        }

        boolean inUse = false;
        for (Invoice i: invoices){
            for(ItemInvoice item: i.getItems()){
                if(item.getProduct().getId().equals(id)){
                    inUse = true;
                    break;
                }
            }
            if (inUse) break;
        }
        if(inUse){
            System.out.println("Accion denegada: No se puede eliminar el cliente " + id);
            System.out.println("Razon: Este producto apararece en facturas historicas");
            return;
        }

        String confirmation = Console.readString("Seguro que deseas eliminar el cliente permanentemente, " +
                "escribe s para si o cualquier otra letra para no ");
        if(confirmation.equals("s")){
            products.remove(id);
            System.out.println("Producto eliminado exitosamente");
        }else {
            System.out.println("Operacion cancelada");
        }
    }

    /**
     * Elimina una factura por ID tras pedir confirmación.
     */
    public void removeInvoice(){
        System.out.println("*********************************************************************");
        System.out.println("------Remover facturas disponibles------");

        if(invoices.isEmpty()){
           System.out.println("No hay facturas registradas");
           return;
        }

        showInvoicesCatalog();

        String id = Console.readString("Ingrese el ID de la factura que quieres eliminar: ");

        Invoice invoiceDelete = null;
        for (Invoice i: invoices){
            if(i.getId().equals(id)){
                invoiceDelete = i;
                break;
            }
        }

        if(invoiceDelete == null){
            System.out.println("Factura no encontrada");
            return;
        }

        String confirmation = Console.readString("Seguro que deseas eliminar el cliente permanentemente, " +
                "escribe s para si o cualquier otra letra para no ");

        if (confirmation.equals("s")){
            invoices.remove(invoiceDelete);
            System.out.println("Factura eliminada exitosamente");
        }else{
            System.out.println("Operacion cancelada");
        }
    }

    //----------------------------METODOS DE BUSQUEDA DE REGISTROS ---------------------------------------------------------

    /**
     * Busca y muestra un cliente por su ID.
     */
    public void searchForCustomerById(){
        System.out.println("*********************************************************************");
        System.out.println("------Buscar Cliente mediante ID------");
        showCustomerCatalog();

        String id = Console.readString("Ingresa el ID del producto que deseas buscar: ");

        Client client = clients.get(id);

        if(client != null){
            System.out.println("Cliente buscado exitosamente");
            System.out.println("----------------------------------------------");
            System.out.println("ID:        " + client.getId());
            System.out.println("Nombre:    " + client.getName());
            System.out.println("Direccion: "+ client.getAdress());
            System.out.println("Email:     "+ client.getEmail());
            System.out.println("----------------------------------------------");
        }else{
            System.out.println("No existe ningun cliente asociado al ID: "+ id);
        }
    }

    /**
     * Busca y muestra un producto por su ID.
     */
    public void searchForProductById(){
        System.out.println("*********************************************************************");
        System.out.println("------Buscar Producto mediante ID------");
        showProductCatalog();

        String id = Console.readString("Ingresa el ID del producto que deseas buscar: ");

        Product product = products.get(id);

        if(product != null){
            System.out.println("Producto encontrado");
            System.out.println("----------------------------------------------");
            System.out.println("ID:         " + product.getId());
            System.out.println("Nombre:     " + product.getName());
            System.out.println("Descripcion "+ product.getDescription());
            System.out.println("Precio:     " + product.getPrice());
            System.out.println("Stock       " + product.getStock());
            System.out.println("----------------------------------------------");
        }else{
            System.out.println("No existe ningun producto asociado al ID: "+ id);
        }
    }

    /**
     * Busca y muestra una factura por su ID con detalle de items y totales.
     */
    public void searchForInvoiceById(){
        System.out.println("*********************************************************************");
        System.out.println("------Buscar Factura mediante ID------");
        showInvoicesCatalog();

        String id = Console.readString("Ingresa el ID de la factura que deseas buscar: ");

        Invoice invoiceFound = null;

        for (Invoice f: invoices){
            if(f.getId().equals(id)){
                invoiceFound = f;
                break;
            }
        }

        if(invoiceFound != null){
            System.out.println("Factura mediante encontrado exitosamente");
            System.out.println("\n========================================");
            System.out.println("          DETALLE DE FACTURA            ");
            System.out.println("========================================");
            System.out.println("N de Factura:        " + invoiceFound.getId());
            System.out.println("Fecha:               " + invoiceFound.getFecha());
            System.out.println("Cliente:             " + invoiceFound.getClient().getName());
            System.out.println("----------------------------------------");

            int count = 1;

            System.out.println("# " + "Nombre       "+ "Cantidad  "+ "Subtotal ");
            for(ItemInvoice i: invoiceFound.getItems()){
                System.out.println(count + " "+ i.getProduct().getName() + "       " + i.getQuantity() + "         " + i.getSubtotal());
                count++;
            }
            System.out.println("----------------------------------------");
            System.out.println("Total a pagar:                     "+invoiceFound.getTotal());
            System.out.println("========================================");
        }else {
            System.out.println("No existe ningun factura asociado al ID: "+ id);
        }
    }

    //  ===================  IMPRIMIR ORDENADO POR ID ==============================

    /**
     * Imprime todos los clientes ordenados por su ID (alfabéticamente).
     */
    public void printClientsOrderedById() {
        System.out.println("----- CLIENTES ORDENADOS POR ID -----");

        ArrayList<Client> lista = new ArrayList<>(clients.values());
        lista.sort((c1, c2) -> c1.getId().compareTo(c2.getId()));

        for (Client c : lista) {
            System.out.println("ID: " + c.getId());
            System.out.println("Nombre: " + c.getName());
            System.out.println("Dirección: " + c.getAdress());
            System.out.println("Email: " + c.getEmail());
            System.out.println("------------------------------");
        }
    }

    /**
     * Imprime todos los productos ordenados por su ID.
     */
    public void printProductsOrderedById() {
        System.out.println("----- PRODUCTOS ORDENADOS POR ID -----");

        ArrayList<Product> lista = new ArrayList<>(products.values());
        lista.sort((p1, p2) -> p1.getId().compareTo(p2.getId()));

        for (Product p : lista) {
            System.out.println("ID: " + p.getId());
            System.out.println("Nombre: " + p.getName());
            System.out.println("Descripción: " + p.getDescription());
            System.out.println("Precio: " + p.getPrice());
            System.out.println("Stock: " + p.getStock());
            System.out.println("------------------------------");
        }
    }

    /**
     * Imprime todas las facturas ordenadas por su ID.
     */
    public void printInvoicesOrderedById() {
        System.out.println("----- FACTURAS ORDENADAS POR ID -----");

        ArrayList<Invoice> lista = new ArrayList<>(invoices);
        lista.sort((f1, f2) -> f1.getId().compareTo(f2.getId()));

        for (Invoice f : lista) {
            System.out.println("ID Factura: " + f.getId());
            System.out.println("Fecha: " + f.getFecha());
            System.out.println("Estatus de la factura: " + f.getState());
            System.out.println("Cliente: " + f.getClient().getName());
            System.out.println("Total: $" + f.getTotal());
            System.out.println("------------------------------");
        }
    }

    //  ===================  MODIFICACION DE REGISTROS ==============================

    public void modifyCustomer(){
        System.out.println("----- MODIFICAR CLIENTE -----");
        showCustomerCatalog();

        String id = Console.readString("Ingresa el ID del cliente que deseas editar: ");

        Client c = clients.get(id);

        if(c == null){
            System.out.println("No existe ningun cliente asociado al ID: "+ id);
            return;
        }

        System.out.println(">> Editando datos de: "+ c.getName());
        System.out.println("Ingrese los datos nuevos");

        String newName = Console.readString("Ingrese el nuevo nombre del cliente (actual: " + c.getName() + "): ");
        String newAdress = Console.readString("Ingrese la nueva direccion del cliente (actual: "+ c.getAdress()+"): ");
        String newMail = Console.readString("Ingrese el nuevo mail del cliente (actual: "+ c.getEmail()+ "): ");

        c.setName(newName);
        c.setAdress(newAdress);
        c.setEmail(newMail);

        System.out.println("Datos actualizados correctamente");

    }

    public void modifyProduct(){
        System.out.println("----- MODIFICAR PRODUCTO -----");
        showProductCatalog();

        String id = Console.readString("Ingresa el ID del producto que deseas editar: ");

        Product p = products.get(id);

        if(p == null){
            System.out.println("No existe ningun producto asociado al ID: "+ id);
            return;
        }

        System.out.println(">> Editando datos de: "+ p.getName());
        System.out.println("Ingrese los datos nuevos");

        String newName = Console.readString("Ingrese el nuevo nombre del producto (actual: " + p.getName() + "): ");
        String newDesc = Console.readString("Ingrese la nueva descripcion del producto (actual: "+ p.getDescription()+"): ");
        double newPrice = Console.readDouble("Ingrese el nuevo precio del producto (actual: "+ p.getPrice()+ "): ");
        int newStock = Console.readInt("Ingrese el nuevo stock del producto (actual: "+ p.getStock()+ "): ");

        p.setName(newName);
        p.setDescription(newDesc);
        p.setPrice(newPrice);
        p.setStock(newStock);

        System.out.println("Datos actualizados correctamente");

    }

    public void modifyInvoice(){
        System.out.println("----- MODIFICAR FACTURA -----");
        showInvoicesCatalog();

        String id = Console.readString("Ingresa el ID de la factura que deseas editar: ");

        Invoice invoiceToEdit = null;

        for(Invoice i : invoices){
            if(i.getId().equals(id)){
                invoiceToEdit = i;
                break;
            }
        }

        if(invoiceToEdit == null){
            System.out.println("No existe ninguna factura asociada al ID: "+ id);
            return;
        }

        System.out.println(">> Factura: "+ invoiceToEdit.getId()+ "| Cliente: " + invoiceToEdit.getClient().getName());
        System.out.println("El estado de la factura es " + invoiceToEdit.getState());

        System.out.println("Opciones para cambio de estado: 1 - Pendiente | 2 - Pagada | 3 - Anulada");

        int option = Console.readInt("Ingresa la opcion que quieras realizar: ");

        String newState = "Pendiente";

        switch(option){
            case 1: newState = "Pendiente"; break;
            case 2: newState = "Pagada"; break;
            case 3: newState = "Anulada"; break;
            default: System.out.println("Ingrese una opcion valida"); return;
        }

        invoiceToEdit.setState(newState);

        System.out.println("Datos actualizados correctamente");

    }

}

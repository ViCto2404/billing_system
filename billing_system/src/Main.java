//libraries
import java.util.Scanner;
import Logic.BillingSystem;
import utilities.PersistenceManager;

/**
 * Clase principal de la aplicación.
 * - Carga el estado del sistema desde persistencia.
 * - Muestra menús por consola y delega las operaciones al BillingSystem.
 */
public class Main{
    public static void main(String[] args){

            // Cargar o crear el sistema de facturación
            BillingSystem system = PersistenceManager.loadSystem();
            // Variable de control para los bucles de menú
            boolean bucle = true;
            // Almacena la opción ingresada por el usuario
            String user_action;
            // Scanner para leer la entrada por consola
            Scanner sc = new Scanner(System.in);

            System.out.print("Ingrese su nombre para acceder al sistema");
            String user_name = sc.nextLine();

            // Bucle principal del menú
            while(bucle){

                // Menú principal mostrado al usuario
                System.out.println("billing system v0.1");
                System.out.println("*********************************************************************");
                System.out.println("MENU PRINCIPAL: Ingrese el numero de la opcion a la que desea acceder");
                System.out.println("1. Añadir registro");
                System.out.println("2. Remover registro");
                System.out.println("3. Buscar registro mediante ID");
                System.out.println("4. Modificar registros");
                System.out.println("5. Salida de datos");
                System.out.println("6. Salir");
                System.out.println("*********************************************************************");
                System.out.print("Ingrese la opcion deseada: ");
                user_action = sc.nextLine();

                // Procesar la opción del menú principal
                switch (user_action) {
                    case "1":
                        // Submenú: añadir registros
                        while(bucle){
                            System.out.println("*********************************************************************");
                            System.out.println("MENU DE ADICION DE REGISTROS: Ingrese el numero de la opcion a la que desea acceder");
                            System.out.println("1. Añadir cliente");
                            System.out.println("2. Añadir producto");
                            System.out.println("3. Añadir factura");
                            System.out.println("4. volver atras");
                            System.out.println("*********************************************************************");
                            System.out.print("Ingrese la opcion deseada: ");
                            user_action = sc.nextLine();

                            // Opciones del submenú de adición
                            switch (user_action) {
                                case "1":
                                    // Llama al método que agrega un cliente
                                    system.addClient();
                                    //Guardamos el log de la accion
                                    utilities.log_manager.recordLog("Nuevo cliente registrado", user_name);
                                    // Salir del submenú para volver al principal
                                    bucle = false;
                                    break;
                                case "2":
                                    // Llama al método que agrega un producto
                                    system.addProduct();
                                    //Guardamos el log de la accion
                                    utilities.log_manager.recordLog("Nuevo producto registrado", user_name);
                                    bucle = false;
                                    break;
                                case "3":
                                    // Llama al método que crea una factura
                                    system.addInvoice();
                                    //Guardamos el log de la accion
                                    utilities.log_manager.recordLog("Nueva factura registrada", user_name);
                                    bucle = false;
                                    break;
                                case "4":
                                    // Volver al menú principal sin acción
                                    bucle = false;
                                    break;
                                default:
                                    // Opción no válida en el submenú
                                    System.out.println("Favor elegir una opcion permitida por el sistema");
                                    break;
                            }}
                        break;

                    case "2":
                        // Submenú: remover registros
                        while(bucle){
                            System.out.println("*********************************************************************");
                            System.out.println("MENU DE SUSTRACCION DE REGISTROS: Ingrese el numero de la opcion a la que desea acceder");
                            System.out.println("1. Remover cliente");
                            System.out.println("2. Remover producto");
                            System.out.println("3. Remover factura");
                            System.out.println("4. volver atras");
                            System.out.println("*********************************************************************");
                            System.out.print("Ingrese la opcion deseada: ");
                            user_action = sc.nextLine();

                            // Opciones del submenú de remoción
                            switch (user_action) {
                                case "1":
                                    // Elimina cliente (si es posible)
                                    system.removeCustomer();
                                    //Guardamos el log de la accion
                                    utilities.log_manager.recordLog("cliente removido", user_name);
                                    bucle = false;
                                    break;
                                case "2":
                                    // Elimina producto (si es posible)
                                    system.removeProduct();
                                    //Guardamos el log de la accion
                                    utilities.log_manager.recordLog("producto removido", user_name);
                                    bucle = false;
                                    break;
                                case "3":
                                    // Elimina factura seleccionada
                                    system.removeInvoice();
                                    //Guardamos el log de la accion
                                    utilities.log_manager.recordLog("factura removida", user_name);
                                    bucle = false;
                                    break;
                                case "4":
                                    // Volver al menú principal
                                    bucle = false;
                                    break;
                                default:
                                    System.out.println("Favor elegir una opcion permitida por el sistema");
                                    break;
                            }}
                        break;

                    case "3":
                        // Submenú: buscar registros por ID
                        while(bucle){
                            System.out.println("*********************************************************************");
                            System.out.println("MENU DE BUSQUEDA DE REGISTROS: Ingrese el numero de la opcion a la que desea acceder");
                            System.out.println("1. Buscar cliente por ID");
                            System.out.println("2. Buscar producto por ID");
                            System.out.println("3. Buscar factura por ID");
                            System.out.println("4. volver atras");
                            System.out.println("*********************************************************************");
                            System.out.print("Ingrese la opcion deseada: ");
                            user_action = sc.nextLine();

                            // Opciones del submenú de búsqueda
                            switch (user_action) {
                                case "1":
                                    // Busca y muestra cliente por ID
                                    system.searchForCustomerById();
                                    //Guardamos el log de la accion
                                    utilities.log_manager.recordLog("busqueda de cliente realizada", user_name);
                                    bucle = false;
                                    break;
                                case "2":
                                    // Busca y muestra producto por ID
                                    system.searchForProductById();
                                    //Guardamos el log de la accion
                                    utilities.log_manager.recordLog("busqueda de producto realizada", user_name);
                                    bucle = false;
                                    break;
                                case "3":
                                    // Busca y muestra factura por ID
                                    system.searchForInvoiceById();
                                    //Guardamos el log de la accion
                                    utilities.log_manager.recordLog("busqueda de factura realizada", user_name);
                                    bucle = false;
                                    break;
                                case "4":
                                    // Volver al menú principal
                                    bucle = false;
                                    break;
                                default:
                                    System.out.println("Favor elegir una opcion permitida por el sistema");
                                    break;
                            }}
                        break;
                    case "4":
                        // Submenú: modificar registros (placeholders actualmente)
                        while(bucle){
                            System.out.println("*********************************************************************");
                            System.out.println("MENU DE MODIFICACION: Ingrese el numero de la opcion a la que desea acceder");
                            System.out.println("1. Modificar cliente");
                            System.out.println("2. Modificar producto");
                            System.out.println("3. Modificar factura");
                            System.out.println("4. volver atras");
                            System.out.println("*********************************************************************");
                            System.out.print("Ingrese la opcion deseada: ");
                            user_action = sc.nextLine();

                            // Opciones del submenú de modificación
                            switch (user_action) {
                                case "1":
                                    // Lugar para implementar modificación de cliente
                                    //Guardamos el log de la accion
                                    utilities.log_manager.recordLog("registro de cliente modificado", user_name);
                                    bucle = false;
                                    break;
                                case "2":
                                    // Lugar para implementar modificación de producto
                                    //Guardamos el log de la accion
                                    utilities.log_manager.recordLog("registro de producto modificado", user_name);
                                    bucle = false;
                                    break;
                                case "3":
                                    // Lugar para implementar modificación de factura
                                    //Guardamos el log de la accion
                                    utilities.log_manager.recordLog("registro de factura modificado", user_name);
                                    bucle = false;
                                    break;
                                case "4":
                                    bucle = false;
                                    break;
                                default:
                                    System.out.println("Favor elegir una opcion permitida por el sistema");
                                    break;
                            }}
                        break;
                    case "5":
                        // Submenú: impresión / reportes
                        while(bucle){
                            System.out.println("*********************************************************************");
                            System.out.println("MENU DE IMPRESION: Ingrese el numero de la opcion a la que desea acceder");
                            System.out.println("1. Imprimir facturas ordenadas");
                            System.out.println("2. Imprimir clientes ordenados");
                            System.out.println("3. Imprimir productos ordenados");
                            System.out.println("4. Imprimir logs");
                            System.out.println("5. volver atras");
                            System.out.println("*********************************************************************");
                            System.out.print("Ingrese la opcion deseada: ");
                            user_action = sc.nextLine();

                            // Opciones del submenú de impresión
                            switch (user_action) {
                                case "1":
                                    // Muestra facturas ordenadas por ID
                                    system.printInvoicesOrderedById();
                                    //Guardamos el log de la accion
                                    utilities.log_manager.recordLog("reporte de facturas impreso", user_name);
                                    bucle = false;
                                    break;
                                case "2":
                                    // Muestra clientes ordenados por ID
                                    system.printClientsOrderedById();
                                    //Guardamos el log de la accion
                                    utilities.log_manager.recordLog("reporte de clientes impreso", user_name);
                                    bucle = false;
                                    break;
                                case "3":
                                    // Muestra productos ordenados por ID
                                    system.printProductsOrderedById();
                                    //Guardamos el log de la accion
                                    utilities.log_manager.recordLog("reporte de productos impreso", user_name);
                                    bucle = false;
                                    break;
                                case "4":
                                    // Lugar para imprimir logs
                                    utilities.log_manager.showLogs();
                                    //Guardamos el log de la accion
                                    utilities.log_manager.recordLog("reporte de logs impreso", user_name);
                                    bucle = false;
                                    break;
                                case "5":
                                    // Volver al menú principal
                                    bucle = false;
                                    break;
                                default:
                                    System.out.println("Favor elegir una opcion permitida por el sistema");
                                    break;
                            }}
                        break;
                    case "6":
                        // Guardar el estado actual y salir de la aplicación
                        PersistenceManager.saveSystem(system);
                        //Guardamos el log de la accion
                        utilities.log_manager.recordLog("Sistema guardado", user_name);
                        System.out.println("Gracias por utilizar nuestros servicios");
                        System.exit(0);
                        break;
                    default:
                        // Opción no válida en el menú principal
                        System.out.println("Favor elegir una opcion permitida por el sistema");
                        break;
                }
            // Reiniciar la bandera para permitir volver a entrar en submenús desde el principal
            bucle = true;
            }
        }
    }
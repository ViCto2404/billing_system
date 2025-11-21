//libraries
import java.util.Scanner;
import Logic.BillingSystem;
import utilities.PersistenceManager;


public class Main{
    public static void main(String[] args){

            BillingSystem system = PersistenceManager.loadSystem();
            boolean bucle = true;
            String user_action;
            Scanner sc = new Scanner(System.in);

            while(bucle){

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

                switch (user_action) {
                    case "1":
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

                            switch (user_action) {
                                case "1":
                                    system.addClient();
                                    bucle = false;
                                    break;
                                case "2":
                                    system.addProduct();
                                    bucle = false;
                                    break;
                                case "3":
                                    system.addInvoice();
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

                    case "2":
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

                            switch (user_action) {
                                case "1":
                                    system.removeCustomer();
                                    bucle = false;
                                    break;
                                case "2":
                                    system.removeProduct();
                                    bucle = false;
                                    break;
                                case "3":
                                    system.removeInvoice();
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

                    case "3":
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

                            switch (user_action) {
                                case "1":
                                    system.searchForCustomerById();
                                    bucle = false;
                                    break;
                                case "2":
                                    system.searchForProductById();
                                    bucle = false;
                                    break;
                                case "3":
                                    system.searchForInvoiceById();
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
                    case "4":
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

                            switch (user_action) {
                                case "1":
                                    //Aqui va el codigo para imprimir facturas ordenadas
                                    bucle = false;
                                    break;
                                case "2":
                                    //Aqui va el codigo para imprimir clientes ordenadas
                                    bucle = false;
                                    break;
                                case "3":
                                    //Aqui va el codigo para imprimir productos ordenados
                                    bucle = false;
                                    break;
                                case "4":
                                    //Aqui va el codigo para imprimir los logs
                                    bucle = false;
                                    break;
                                default:
                                    System.out.println("Favor elegir una opcion permitida por el sistema");
                                    break;
                            }}
                        break;
                    case "5":
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

                            switch (user_action) {
                                case "1":
                                    //Aqui va el codigo para imprimir facturas ordenadas
                                    bucle = false;
                                    break;
                                case "2":
                                    //Aqui va el codigo para imprimir clientes ordenadas
                                    bucle = false;
                                    break;
                                case "3":
                                    //Aqui va el codigo para imprimir productos ordenados
                                    bucle = false;
                                    break;
                                case "4":
                                    //Aqui va el codigo para imprimir los logs
                                    bucle = false;
                                    break;
                                case "5":
                                    bucle = false;
                                    break;
                                default:
                                    System.out.println("Favor elegir una opcion permitida por el sistema");
                                    break;
                            }}
                        break;
                    case "6":
                        PersistenceManager.saveSystem(system);
                        System.out.println("Gracias por utilizar nuestros servicios");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Favor elegir una opcion permitida por el sistema");
                        break;
                }
            bucle = true;
            }
        }
    }
package utilities;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Utilidad sencilla para leer datos desde la consola.
 * Provee métodos seguros (con validación) para leer String, int y double.
 * Usa un único Scanner compartido para evitar problemas con múltiples scanners sobre System.in.
 */
public class Console {

    // Scanner estático reutilizable para todas las lecturas de consola del programa
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Lee una línea de texto desde la consola.
     * @param message Mensaje que se muestra al usuario antes de leer la entrada.
     * @return Línea leída (String).
     */
    public static String readString(String message){
        System.out.print(message);
        return sc.nextLine();
    }

    /**
     * Lee un número entero con validación.
     * Muestra el mensaje, intenta leer un int y en caso de error consume la entrada
     * inválida y vuelve a solicitar hasta recibir un entero válido.
     * @param message Mensaje que se muestra al usuario.
     * @return Entero ingresado por el usuario.
     */
    public static int readInt(String message){
        while(true){
            try{
                System.out.print(message);
                int number = sc.nextInt();
                sc.nextLine(); // consumir el salto de línea restante
                return number;
            } catch (InputMismatchException e){
                // Mensaje de error y limpieza del buffer para permitir reintento
                System.out.print("Error: debes ingresar un numero entero valido. Intenta de nuevo");
                sc.nextLine();
            }
        }
    }

    /**
     * Lee un número decimal (double) con validación.
     * Repite la lectura hasta que el usuario ingrese un valor válido.
     * @param message Mensaje que se muestra al usuario.
     * @return Valor double ingresado por el usuario.
     */
    public static double readDouble(String message){
        while(true){
            try{
                System.out.print(message);
                double number = sc.nextDouble();
                sc.nextLine(); // consumir el salto de línea restante
                return number;
            }catch(InputMismatchException e){
                // Mensaje de error y limpieza del buffer para permitir reintento
                System.out.print("Error: Debes ingresar un numero decimal (Ej: 10.50)");
                sc.nextLine();
            }
        }
    }

}

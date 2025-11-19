package utilities;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {

    private static final Scanner sc = new Scanner(System.in);

    public static String readString(String message){
        System.out.println(message);
        return sc.nextLine();
    }

    public static int readInt(String message){
        while(true){
            try{
                System.out.println(message);
                int number = sc.nextInt();
                sc.nextLine();
                return number;
            } catch (InputMismatchException e){
                System.out.println("Error: debes ingresar un numero entero valido. Intenta de nuevo");
                sc.nextLine();
            }
        }
    }

    public static double readDouble(String message){
        while(true){
            try{
                System.out.println(message);
                double number = sc.nextDouble();
                sc.nextLine();
                return number;
            }catch(InputMismatchException e){
                System.out.println("Error: Debes ingresar un numero decimal (Ej: 10.50)");
                sc.nextLine();
            }
        }
    }

}

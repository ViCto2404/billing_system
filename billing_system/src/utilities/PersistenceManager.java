package utilities;
import Logic.BillingSystem;
import java.io.*;

public class PersistenceManager {

    private static final String NOMBRE_DEL_ARCHIVO = "datos_sistema.dat";

    public static BillingSystem loadSystem(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOMBRE_DEL_ARCHIVO))){

            BillingSystem billingSystem = (BillingSystem) ois.readObject();
            System.out.println("Datos cargados desde " + NOMBRE_DEL_ARCHIVO);
            return loadSystem();
        }catch (FileNotFoundException e){
            System.out.println("Archivo no encontrado, iniciando sistema nuevo");
            return new BillingSystem();
        }catch (IOException e){
            System.out.println("Error al cargar archivo, iniciando sistema nuevo: "+ e.getMessage());
            return new BillingSystem();
        }catch (ClassNotFoundException e){
            System.out.println("Error: Estructura de datos inconsistente. Iniciando sistema nuevo");
            return new BillingSystem();
        }
    }

    public static void saveSystem(BillingSystem system){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOMBRE_DEL_ARCHIVO))){
            oos.writeObject(system);
            System.out.println("Datos guardados correctamente en "+ NOMBRE_DEL_ARCHIVO);
        }catch (IOException e){
            System.out.println("Error al guardar los datos: "+ e.getMessage());
        }
    }

}

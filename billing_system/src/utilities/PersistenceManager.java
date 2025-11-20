package utilities;
import Logic.BillingSystem;
import java.io.*;

public class PersistenceManager implements Serializable{

    private static final String NOMBRE_DEL_ARCHIVO = "datos_sistema.dat";

    public static BillingSystem loadSystem(){

        File file = new File(NOMBRE_DEL_ARCHIVO);

        if(!file.exists()){
            System.out.println("No se encontro archivo de datos");
            System.out.println("Iniciando un sistema nuevo (contadores en 1)");
            return new BillingSystem();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){

            BillingSystem systemRecovery = (BillingSystem) ois.readObject();

            System.out.println("Datos cargados correctamente");
            System.out.println("Archivo leido desde " + file.getName());

            return systemRecovery;

        } catch (Exception e){
            System.out.println("Error critico al cargar los datos: " + e.getMessage());
            e.printStackTrace();
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

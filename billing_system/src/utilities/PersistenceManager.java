package utilities;
import Logic.BillingSystem;
import java.io.*;

/**
 * Utilidad para persistencia del objeto BillingSystem mediante serialización.
 * Contiene métodos estáticos para cargar y guardar el estado del sistema
 * en un archivo binario.
 */
public class PersistenceManager implements Serializable{

    // Nombre del archivo donde se guardan los datos del sistema
    private static final String NOMBRE_DEL_ARCHIVO = "datos_sistema.dat";

    /**
     * Intenta cargar el BillingSystem desde el archivo especificado.
     * - Si el archivo no existe, informa y devuelve un nuevo BillingSystem vacío.
     * - Si ocurre un error al leer, informa, muestra la excepción y devuelve un nuevo sistema.
     * @return BillingSystem recuperado o uno nuevo si no fue posible cargar.
     */
    public static BillingSystem loadSystem(){

        File file = new File(NOMBRE_DEL_ARCHIVO);

        // Si no existe el archivo, iniciar un sistema nuevo (contadores en 1)
        if(!file.exists()){
            System.out.println("No se encontro archivo de datos");
            System.out.println("Iniciando un sistema nuevo (contadores en 1)");
            return new BillingSystem();
        }

        // Intentar leer el objeto serializado desde el archivo
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){

            BillingSystem systemRecovery = (BillingSystem) ois.readObject();

            System.out.println("Datos cargados correctamente");
            System.out.println("Archivo leido desde " + file.getName());

            return systemRecovery;

        } catch (Exception e){
            // En caso de cualquier excepción, informar y devolver un sistema nuevo
            System.out.println("Error critico al cargar los datos: " + e.getMessage());
            e.printStackTrace();
            return new BillingSystem();
        }

    }

    /**
     * Guarda el BillingSystem provisto en el archivo definido.
     * Muestra mensajes de éxito o error según corresponda.
     * @param system Instancia de BillingSystem a serializar y guardar.
     */
    public static void saveSystem(BillingSystem system){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOMBRE_DEL_ARCHIVO))){
            oos.writeObject(system);
            System.out.println("Datos guardados correctamente en "+ NOMBRE_DEL_ARCHIVO);
        }catch (IOException e){
            // Mensaje informativo si ocurre un error de E/S al guardar
            System.out.println("Error al guardar los datos: "+ e.getMessage());
        }
    }

}

package utilities;
import Logic.BillingSystem;
import java.io.*;

public class PersistenceManager implements Serializable{

    private static final String NOMBRE_DEL_ARCHIVO = "datos_sistema.dat";

    public static void loadSystem(BillingSystem billingSystem){
        File archivo = new File(NOMBRE_DEL_ARCHIVO);

        // IMPRIMIR LA RUTA EXACTA (Para que sepas dónde buscarlo)
        System.out.println("💾 Intentando guardar en: " + archivo.getAbsolutePath());

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {

            oos.writeObject(billingSystem);
            System.out.println("✅ ¡ÉXITO! Archivo creado/actualizado correctamente.");

        } catch (NotSerializableException e) {
            // ESTE ES EL ERROR MÁS COMÚN
            System.out.println("❌ ERROR CRÍTICO: Falta 'implements Serializable' en alguna clase.");
            System.out.println("Clase culpable: " + e.getMessage());

        } catch (IOException e) {
            System.out.println("❌ Error de escritura: " + e.getMessage());
            e.printStackTrace(); // Imprime el error completo
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

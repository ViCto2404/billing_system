package utilities;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Clase responsable de manejar el registro de acciones (logs) en un archivo.
 * Contiene métodos para añadir una entrada al log y mostrar el historial completo.
 */
public class log_manager {

    // Nombre del archivo donde se almacenan los logs
    private static final String ARCHIVO_LOGS = "logs.txt";

    /**
     * Registra una entrada en el archivo de logs.
     *
     * @param description Descripción de la acción o evento.
     * @param user Usuario que realizó la acción.
     */
    public static void recordLog(String description, String user){

        // Genera un identificador único corto para el log (ej: LOG-1a2b3c4d)
        String idLog = "LOG-" + UUID.randomUUID().toString().substring(0,8);

        // Obtiene la fecha y hora actual en formato legible
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = now.format(formatter);

        // Construye la línea que se guardará en el archivo: id | fecha | usuario | descripción
        String lineLog = String.format("%s | %s | %s | %s", idLog, dateTime, user, description);

        // Usa try-with-resources para asegurar el cierre correcto de los streams al terminar
        try (FileWriter fw = new FileWriter(ARCHIVO_LOGS, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)){

            // Escribe la línea y añade un salto de línea
            out.println(lineLog);

        } catch (IOException e) {
            // Mensaje mínimo por si ocurre un error de E/S al guardar el log
            System.out.println("Error al guardar los logs "+ e.getMessage());
        }
    }

    /**
     * Muestra por consola todas las entradas del archivo de logs.
     * Si no existe el archivo, informa que no hay logs existentes.
     */
    public static void showLogs(){
        File file = new File(ARCHIVO_LOGS);

        // Si no existe el archivo, no hay nada que leer
        if(!file.exists()){
            System.out.println("No hay logs existentes");
            return;
        }

        System.out.println("\n--- HISTORIAL DE ACCIONES (LOGS) ---");
        // Lee el archivo línea por línea e imprime cada entrada
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e){
            // Mensaje mínimo por si ocurre un error de lectura
            System.out.println("Error al leer el archivo de logs " + e.getMessage());
        }
        System.out.println("------------------------------------");
    }
}

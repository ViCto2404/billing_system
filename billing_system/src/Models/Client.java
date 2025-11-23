package Models;
import java.io.Serializable;

/**
 * Clase que representa un cliente del sistema.
 * Implementa Serializable para permitir persistencia si se desea.
 */
public class Client implements Serializable{

    // Identificador único del cliente (ej. "CLI-1")
    private String id;
    // Nombre completo del cliente
    private String name;
    // Dirección del cliente (se mantiene el nombre 'adress' usado en el proyecto)
    private String adress;
    // Correo electrónico del cliente
    private String email;

    /**
     * Constructor: inicializa todos los campos del cliente.
     * @param id Identificador único
     * @param name Nombre completo
     * @param adress Dirección
     * @param email Correo electrónico
     */
    public Client(String id, String name, String adress, String email){

        this.id = id;
        this.name = name;
        this.adress = adress;
        this.email = email;

    }

    // ------------------ Getters ------------------

    /** Devuelve el ID del cliente */
    public String getId() {
        return id;
    }

    /** Devuelve el nombre del cliente */
    public String getName() {
        return name;
    }

    /** Devuelve la dirección del cliente */
    public String getAdress() {
        return adress;
    }

    /** Devuelve el email del cliente */
    public String getEmail() {
        return email;
    }

    // ------------------ Setters ------------------

    /** Asigna un nuevo ID al cliente */
    public void setId(String id) {
        this.id = id;
    }

    /** Asigna un nuevo nombre al cliente */
    public void setName(String name) {
        this.name = name;
    }

    /** Asigna una nueva dirección al cliente */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    /** Asigna un nuevo email al cliente */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Representación en texto del cliente.
     * Método sobrescrito de Object.
     */
    @Override
    public String toString() {
        return String.format("ID: ", id, "Nombre: ", name, "Email: ", email, "Adress: ", adress);
    }

}

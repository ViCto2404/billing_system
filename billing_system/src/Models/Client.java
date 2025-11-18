package Models;
import java.io.Serializable;

public class Client implements Serializable{

    private String id;
    private String name;
    private String adress;
    private String email;

    public Client(String id, String name, String adress, String email){

        this.id = id;
        this.name = name;
        this.adress = adress;
        this.email = email;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public String getEmail() {
        return email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("ID: ", id, "Nombre: ", name, "Email: ", email, "Adress: ", adress);
    }

}

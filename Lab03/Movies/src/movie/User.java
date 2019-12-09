package movie;

import javafx.beans.property.SimpleStringProperty;

public class User {

    private final SimpleStringProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty phone_number;
    private final SimpleStringProperty email;

    public User(String id, String name, String phone_number, String email) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.phone_number = new SimpleStringProperty(phone_number);
        this.email = new SimpleStringProperty(email);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String fId) {
        id.set(fId);
    }

    public String getname() {
        return name.get();
    }

    public void setname(String fname) {
        name.set(fname);
    }

    public String getPhone_number() {
        return phone_number.get();
    }

    public void setBroj_telefona(String fPhone_number) {
        phone_number.set(fPhone_number);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String fEmail) {
        email.set(fEmail);
    }
}
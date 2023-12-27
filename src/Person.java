import java.io.Serializable;

public abstract class Person implements Serializable {
    private Credential credential;
    private String name;

    Person() {
    }

    Person(String username, String password, String name) {
        setCredential(username, password);
        setName(name);
    }

    public void setCredential(String username, String password) {
        this.credential = new Credential(username, password);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Credential getCredential() {
        return credential;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Username: " + this.credential.getUsername() + " Name: " + name + " ";
    }
}

public abstract class Person{
    private Credential credential;
    private String name;
    Person(){}
    Person(String username, String password, String name){
        setCredential(username, password);
        setName(name);
    }
    public void setCredential(String username, String password) {
        this.credential.setUsername(username);
        this.credential.setPinHash(password);
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
    
}

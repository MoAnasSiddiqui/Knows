public class Credential{
    private String username;
    private String pinHash;
    Credential(){};
    Credential(String username, String password){
        setUsername(username);
        setPinHash(password);
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPinHash(String pinHash) {
    }
    public String getUsername() {
        return username;
    }
    public String getPinHash() {
        return pinHash;
    }

}
package fr.univ.nantes.enfola.m1.bean;

/**
 * @author Alexis Giraudet
 */
public class Query {
    private String username;
    private String password;
    private String key;

    public Query() {

    }

    public Query(String username, String password, String key) {
        this.username = username;
        this.password = password;
        this.key = key;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "username="+username+" password="+password+" key="+key;
    }
}

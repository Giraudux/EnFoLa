package fr.univ.nantes.enfola.m1.bean;

/**
 * @author Alexis Giraudet
 * @author Pierre Gaultier
 */
public class Reply {
    private int status;
    private String message;
    private String value;

    public Reply() {

    }

    public Reply(int status, String message, String value) {
        this.status = status;
        this.message = message;
        this.value = value;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "status=" + status + " message=" + (message == null ? message : "\"" + message + "\"") + " value=" + (value == null ? value : "\"" + value + "\"");
    }
}

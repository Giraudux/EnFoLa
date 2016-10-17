import java.util.Collection;

/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public interface SimpleConnector extends Connector {
    Glue getGlue();

    void setGlue(Glue glue);

    Collection<Role> getRoles();

    void setRoles(Collection<Role> roles);
}

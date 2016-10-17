import java.util.Collection;

/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public interface Glue {
    Collection<Role> getRoles();

    void setRoles(Collection<Role> roles);
}

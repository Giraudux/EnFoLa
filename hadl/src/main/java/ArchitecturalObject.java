import java.util.Collection;

/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public interface ArchitecturalObject {

    String getName();

    void setName(String name);

    Collection<Interface> getInterfaces();

    void setInterfaces(Collection<Interface> interfaces);
}

import java.util.Collection;

/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public interface Component extends ArchitecturalObject {
    Collection<Property> getProperties();

    void setProperties(Collection<Property> properties);

    Collection<Constraint> getConstraints();

    void getConstraints(Collection<Constraint> constraints);
}

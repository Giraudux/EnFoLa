import java.util.Collection;

/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public interface Configuration extends Component, Connector {
    Collection<Component> getComponents();

    void setComponents(Collection<Component> components);

    Collection<Connector> getConnectors();

    void setConnectors(Collection<Connector> connectors);

    Collection<Attachment> getAttachments();

    void setAttachments(Collection<Attachment> attachments);

    Collection<Binding> getBindings();

    void setBindings(Collection<Binding> bindings);
}

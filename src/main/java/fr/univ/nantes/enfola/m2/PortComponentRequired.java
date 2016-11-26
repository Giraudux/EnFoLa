package fr.univ.nantes.enfola.m2;

/**
 * @author Alexis Giraudet
 * @author Pierre Gaultier
 */
public class PortComponentRequired<T> implements PortComponent<T> {
    private static final Friend friend = new Friend();
    private final Component component;
    private Configuration configuration;

    /**
     * @param component
     */
    public PortComponentRequired(Component component) {
        super();

        this.component = component;
        this.configuration = null;

        component.addPortComponentRequired(friend, this);
    }

    /**
     * @param friend
     * @param configuration
     */
    public final void setConfiguration(Configuration.Friend friend, Configuration configuration) {
        friend.hashCode();

        this.configuration = configuration;
    }

    /**
     * @param friend
     * @param configuration
     * @param t
     */
    public final void read(Configuration.Friend friend, Configuration configuration, T t) {
        friend.hashCode();

        if (this.configuration == configuration) {
            component.read(PortComponentRequired.friend, this, t);
        }
    }

    /**
     *
     */
    public static final class Friend {
        private Friend() {
        }
    }
}

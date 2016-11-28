package fr.univ.nantes.enfola.m2;

/**
 * @author Alexis Giraudet
 * @author Pierre Gaultier
 */
public class PortComponentProvided<T> implements PortComponent<T> {
    private static final Friend friend = new Friend();
    private final Component component;
    private Configuration configuration;

    /**
     * @param component
     */
    public PortComponentProvided(Component component) {
        super();

        this.component = component;
        this.configuration = null;

        component.addPortComponentProvided(friend, this);
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
     * @param component
     * @param t
     */
    public final boolean read(Component.Friend friend, Component component, T t) {
        friend.hashCode();

        if (this.component == component) {
            return configuration.read(PortComponentProvided.friend, this, t);
        }

        return false;
    }

    /**
     *
     */
    public static final class Friend {
        private Friend() {
        }
    }
}

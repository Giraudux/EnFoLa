package fr.univ.nantes.enfola.m2;

/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public class PortComponentProvided<T> implements PortComponent<T> {

    private static final Friend friend = new Friend();
    private final Component component;
    private Configuration configuration;

    public PortComponentProvided(Component component) {
        super();

        this.component = component;
        this.configuration = null;

        component.addPortComponentProvided(friend, this);
    }

    public final void setConfiguration(Configuration.Friend friend, Configuration configuration) {
        friend.hashCode();

        this.configuration = configuration;
    }

    public final void read(Component.Friend friend, Component component, T t) {
        friend.hashCode();

        if (this.component == component) {
            configuration.read(PortComponentProvided.friend, this, t);
        }
    }

    public static final class Friend {
        private Friend() {
        }
    }
}

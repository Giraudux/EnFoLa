package fr.univ.nantes.enfola.m2;

/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public class PortComponentRequired<T> implements PortComponent<T> {

    private static final Friend friend = new Friend();
    private final Component component;
    private Configuration configuration;
    protected PortComponentRequired(Component component) {
        super();

        this.component = component;
        this.configuration = null;

        component.addPortComponentRequired(friend, this);
    }

    public final void setConfiguration(Configuration.Friend friend, Configuration configuration) {
        this.configuration = configuration;
    }

    public final void read(Configuration.Friend friend, Configuration configuration, T t) {
        friend.hashCode();

        if (this.configuration == configuration) {
            component.read(PortComponentRequired.friend, this, t);
        }
    }

    public static final class Friend {
        private Friend() {
        }
    }
}

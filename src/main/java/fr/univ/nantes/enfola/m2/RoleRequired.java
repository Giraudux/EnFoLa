package fr.univ.nantes.enfola.m2;

/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public class RoleRequired<T> implements Role<T> {

    private static final Friend friend = new Friend();
    private final Connector connector;
    private Configuration configuration;
    public RoleRequired(Connector connector) {
        super();

        this.connector = connector;
        this.configuration = null;

        connector.addRoleRequired(friend, this);
    }

    public final void setConfiguration(Configuration.Friend friend, Configuration configuration) {
        this.configuration = configuration;
    }

    public void read(Configuration.Friend friend, Configuration configuration, T t) {
        friend.hashCode();

        if (this.configuration == configuration) {
            ;//
        }
    }

    public static final class Friend {
        private Friend() {
        }
    }
}

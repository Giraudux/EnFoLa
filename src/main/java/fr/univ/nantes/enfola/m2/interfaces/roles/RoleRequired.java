package fr.univ.nantes.enfola.m2.interfaces.roles;

import fr.univ.nantes.enfola.m2.core.Configuration;
import fr.univ.nantes.enfola.m2.core.Connector;

/**
 * @author Alexis Giraudet
 * @author Pierre Gaultier
 */
public class RoleRequired<T> implements Role<T> {
    private static final Friend friend = new Friend();
    private final Connector connector;
    private Configuration configuration;

    /**
     * @param connector
     */
    public RoleRequired(Connector connector) {
        super();

        this.connector = connector;
        this.configuration = null;

        connector.addRoleRequired(friend, this);
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
    public void read(Configuration.Friend friend, Configuration configuration, T t) {
        friend.hashCode();

        if (this.configuration == configuration) {
            connector.read(RoleRequired.friend, this, t);
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

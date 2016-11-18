package fr.univ.nantes.enfola.m2;

/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public class RoleProvided<T> implements Role<T> {

    private static final Friend friend = new Friend();
    private final Connector connector;
    private Configuration configuration;
    public RoleProvided(Connector connector) {
        super();

        this.connector = connector;
        this.configuration = null;

        connector.addRoleProvided(friend, this);
    }

    public final void setConfiguration(Configuration.Friend friend, Configuration configuration) {
        this.configuration = configuration;
    }

    public static final class Friend {
        private Friend() {
        }
    }
}

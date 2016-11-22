package fr.univ.nantes.enfola.m2;

/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public class RoleProvided<T> implements Role<T> {

    private static final Friend friend = new Friend();
    private final Connector connector;
    private Configuration configuration;
    private Glue glue;

    public RoleProvided(Connector connector) {
        super();

        this.connector = connector;
        this.configuration = null;

        connector.addRoleProvided(friend, this);
    }

    public final void setConfiguration(Configuration.Friend friend, Configuration configuration) {
        friend.hashCode();

        this.configuration = configuration;
    }

    public final void setGlue(Glue.Friend friend, Glue glue) {
        friend.hashCode();

        this.glue = glue;
    }

    public final void read(Glue.Friend friend, Glue glue, T t) {
        friend.hashCode();

        if (this.glue == glue) {
            configuration.read(RoleProvided.friend, this, t);
        }
    }

    public static final class Friend {
        private Friend() {
        }
    }
}

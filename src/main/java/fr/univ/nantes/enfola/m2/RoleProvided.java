package fr.univ.nantes.enfola.m2;

/**
 * @author Alexis Giraudet
 * @author Pierre Gaultier
 */
public class RoleProvided<T> implements Role<T> {
    private static final Friend friend = new Friend();
    private final Connector connector;
    private Configuration configuration;
    private Glue glue;

    /**
     * @param connector
     */
    public RoleProvided(Connector connector) {
        super();

        this.connector = connector;
        this.configuration = null;

        connector.addRoleProvided(friend, this);
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
     * @param glue
     */
    public final void setGlue(Glue.Friend friend, Glue glue) {
        friend.hashCode();

        this.glue = glue;
    }

    /**
     * @param friend
     * @param glue
     * @param t
     */
    public final boolean read(Glue.Friend friend, Glue glue, T t) {
        friend.hashCode();

        if (this.glue == glue && configuration != null) {
            return configuration.read(RoleProvided.friend, this, t);
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

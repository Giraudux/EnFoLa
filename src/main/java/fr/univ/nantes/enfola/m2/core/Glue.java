package fr.univ.nantes.enfola.m2.core;

import fr.univ.nantes.enfola.m2.interfaces.roles.RoleProvided;

/**
 * @author Alexis Giraudet
 * @author Pierre Gaultier
 */
public abstract class Glue<R, W> {
    private static final Friend friend = new Friend();
    private final Connector connector;
    private RoleProvided<W> roleProvided;

    /**
     * @param connector
     */
    protected Glue(Connector connector) {
        super();

        this.connector = connector;
    }

    /**
     * @param friend
     * @param connector
     * @param roleProvided
     */
    public final void setRoleProvided(Connector.Friend friend, Connector connector, RoleProvided<W> roleProvided) {
        friend.hashCode();

        if (this.connector == connector) {
            this.roleProvided = roleProvided;
            roleProvided.setGlue(Glue.friend, this);
        }
    }

    /**
     * @param friend
     * @param connector
     * @param r
     */
    public final void read(Connector.Friend friend, Connector connector, R r) {
        friend.hashCode();

        if (this.connector == connector) {
            roleProvided.read(Glue.friend, this, transform(r));
        }
    }

    /**
     * @param r
     * @return
     */
    protected abstract W transform(R r);

    /**
     *
     */
    public static final class Friend {
        private Friend() {
        }
    }
}

package fr.univ.nantes.enfola.m2;

/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public abstract class Glue<R, W> {
    private static final Friend friend = new Friend();
    private final Connector connector;
    private RoleProvided<W> roleProvided;

    protected Glue(Connector connector) {
        super();

        this.connector = connector;
    }

    public final void setRoleProvided(Connector.Friend friend, Connector connector, RoleProvided<W> roleProvided) {
        friend.hashCode();

        if (this.connector == connector) {
            this.roleProvided = roleProvided;
            roleProvided.setGlue(Glue.friend, this);
        }
    }

    public final void read(Connector.Friend friend, Connector connector, R r) {
        friend.hashCode();

        if (this.connector == connector) {
            roleProvided.read(Glue.friend, this, transform(r));
        }
    }

    protected abstract W transform(R r);

    public static final class Friend {
        private Friend() {
        }
    }
}

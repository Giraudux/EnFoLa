package fr.univ.nantes.enfola.m2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public abstract class Connector implements ArchitecturalObject {
    private static final Friend friend = new Friend();
    private Map<RoleRequired, RoleProvided> connections;
    private Map<RoleRequired, Glue> glues;
    private Collection<RoleProvided> roleProvideds;
    private Collection<RoleRequired> roleRequireds;

    protected Connector() {
        roleProvideds = new ArrayList<RoleProvided>();
        roleRequireds = new ArrayList<RoleRequired>();
    }

    public final <T> void addRoleProvided(RoleProvided.Friend friend, RoleProvided<T> roleProvided) {
        friend.hashCode();

        roleProvideds.add(roleProvided);
    }

    public final <T> void addRoleRequired(RoleRequired.Friend friend, RoleRequired<T> roleRequired) {
        friend.hashCode();

        roleRequireds.add(roleRequired);
    }

    public final <T> void read(RoleRequired.Friend friend, RoleRequired<T> roleRequired, T t) {
        friend.hashCode();

        if (roleRequireds.contains(roleProvideds)) {
            //TODO call the glue
            connections.get(roleRequired).read(Connector.friend, this, glues.get(roleRequired).process(t));
        }
    }

    public final <R, W> void connect(RoleRequired<R> roleRequired, Glue<R, W> glue, RoleProvided<W> roleProvided) {
        connections.put(roleRequired, roleProvided);
        glues.put(roleRequired, glue);
    }

    public static final class Friend {
        private Friend() {
        }
    }
}

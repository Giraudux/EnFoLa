package fr.univ.nantes.enfola.m2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public abstract class Connector implements ArchitecturalObject {
    private static final Friend friend = new Friend();
    private Map<RoleRequired, Glue> glues;
    private Collection<RoleProvided> roleProvideds;
    private Collection<RoleRequired> roleRequireds;

    protected Connector() {
        super();

        glues = new HashMap<RoleRequired, Glue>();
        roleProvideds = new ArrayList<RoleProvided>();
        roleRequireds = new ArrayList<RoleRequired>();
    }

    public final <T> boolean addRoleProvided(RoleProvided.Friend friend, RoleProvided<T> roleProvided) {
        friend.hashCode();

        return roleProvideds.add(roleProvided);
    }

    public final <T> boolean addRoleRequired(RoleRequired.Friend friend, RoleRequired<T> roleRequired) {
        friend.hashCode();

        return roleRequireds.add(roleRequired);
    }

    public final <T> boolean removeRoleProvided(RoleProvided.Friend friend, RoleProvided<T> roleProvided) {
        friend.hashCode();

        return roleProvideds.remove(roleProvided);
    }

    public final <T> boolean removeRoleRequired(RoleRequired.Friend friend, RoleRequired<T> roleRequired) {
        friend.hashCode();

        return roleRequireds.remove(roleRequired);
    }

    public final <T> void read(RoleRequired.Friend friend, RoleRequired<T> roleRequired, T t) {
        friend.hashCode();

        Glue glue = glues.get(roleRequired);
        if (roleRequireds.contains(roleRequired) && glue != null) {
            glue.read(Connector.friend, this, t);
        }
    }

    protected final <R, W> void connect(RoleRequired<R> roleRequired, Glue<R, W> glue, RoleProvided<W> roleProvided) {
        if (roleRequireds.contains(roleRequired) && roleProvideds.contains(roleProvided)) {
            glues.put(roleRequired, glue);
            glue.setRoleProvided(friend, this, roleProvided);
        }
    }

    public static final class Friend {
        private Friend() {
        }
    }
}

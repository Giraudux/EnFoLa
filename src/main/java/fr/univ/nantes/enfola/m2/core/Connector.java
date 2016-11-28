package fr.univ.nantes.enfola.m2.core;

import fr.univ.nantes.enfola.m2.base.ArchitecturalObject;
import fr.univ.nantes.enfola.m2.interfaces.roles.RoleProvided;
import fr.univ.nantes.enfola.m2.interfaces.roles.RoleRequired;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author Alexis Giraudet
 * @author Pierre Gaultier
 */
public abstract class Connector implements ArchitecturalObject {
    private static final Friend friend = new Friend();
    private Map<RoleRequired, Glue> glues;
    private Collection<RoleProvided> roleProvideds;
    private Collection<RoleRequired> roleRequireds;

    /**
     *
     */
    protected Connector() {
        super();

        glues = new HashMap<RoleRequired, Glue>();
        roleProvideds = new HashSet<RoleProvided>();
        roleRequireds = new HashSet<RoleRequired>();
    }

    /**
     * @param friend
     * @param roleProvided
     * @param <T>
     * @return
     */
    public final <T> boolean addRoleProvided(RoleProvided.Friend friend, RoleProvided<T> roleProvided) {
        friend.hashCode();

        return roleProvideds.add(roleProvided);
    }

    /**
     * @param friend
     * @param roleRequired
     * @param <T>
     * @return
     */
    public final <T> boolean addRoleRequired(RoleRequired.Friend friend, RoleRequired<T> roleRequired) {
        friend.hashCode();

        return roleRequireds.add(roleRequired);
    }

    /**
     * @param friend
     * @param roleProvided
     * @param <T>
     * @return
     */
    public final <T> boolean removeRoleProvided(RoleProvided.Friend friend, RoleProvided<T> roleProvided) {
        friend.hashCode();

        return roleProvideds.remove(roleProvided);
    }

    /**
     * @param friend
     * @param roleRequired
     * @param <T>
     * @return
     */
    public final <T> boolean removeRoleRequired(RoleRequired.Friend friend, RoleRequired<T> roleRequired) {
        friend.hashCode();

        return roleRequireds.remove(roleRequired);
    }

    /**
     * @param friend
     * @param roleRequired
     * @param t
     * @param <T>
     */
    public final <T> void read(RoleRequired.Friend friend, RoleRequired<T> roleRequired, T t) {
        friend.hashCode();

        Glue glue = glues.get(roleRequired);
        if (roleRequireds.contains(roleRequired) && glue != null) {
            glue.read(Connector.friend, this, t);
        }
    }

    /**
     * @param roleRequired
     * @param glue
     * @param roleProvided
     * @param <R>
     * @param <W>
     */
    protected final <R, W> void connect(RoleRequired<R> roleRequired, Glue<R, W> glue, RoleProvided<W> roleProvided) {
        if (roleRequireds.contains(roleRequired) && roleProvideds.contains(roleProvided)) {
            glues.put(roleRequired, glue);
            glue.setRoleProvided(friend, this, roleProvided);
        }
    }

    protected final <R, W> void disconnect() {
        //TODO
    }

    /**
     *
     */
    public static final class Friend {
        private Friend() {
        }
    }
}

package fr.univ.nantes.enfola.m2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alexis Giraudet
 * @author Pierre Gaultier
 */
public abstract class Configuration implements ArchitecturalObject {
    private static final Friend friend = new Friend();
    private Map<PortComponentProvided, PortConfigurationProvided> bindings1;
    private Map<PortConfigurationRequired, PortComponentRequired> bindings2;
    private Map<PortComponentProvided, RoleRequired> attachments1;
    private Map<RoleProvided, PortComponentRequired> attachments2;
    private Map<PortConfigurationProvided, PortConfigurationRequired> bridges;

    /**
     *
     */
    protected Configuration() {
        super();

        bindings1 = new HashMap<PortComponentProvided, PortConfigurationProvided>();
        bindings2 = new HashMap<PortConfigurationRequired, PortComponentRequired>();
        attachments1 = new HashMap<PortComponentProvided, RoleRequired>();
        attachments2 = new HashMap<RoleProvided, PortComponentRequired>();
        bridges = new HashMap<PortConfigurationProvided, PortConfigurationRequired>();
    }

    /**
     * @param portConfigurationProvided
     * @param portConfigurationRequired
     * @param <T>
     */
    protected final <T> boolean bridge(PortConfigurationProvided<T> portConfigurationProvided, PortConfigurationRequired<T> portConfigurationRequired) {
        if (portConfigurationProvided != null && portConfigurationRequired != null && !bridges.containsKey(portConfigurationProvided)) {
            bridges.put(portConfigurationProvided, portConfigurationRequired);

            return true;
        }

        return false;
    }

    /**
     * @param portComponentProvided
     * @param portConfigurationProvided
     * @param <T>
     */
    protected final <T> boolean bind(PortComponentProvided<T> portComponentProvided, PortConfigurationProvided<T> portConfigurationProvided) {
        if (portComponentProvided != null && portConfigurationProvided != null && !bindings1.containsKey(portComponentProvided)) {
            bindings1.put(portComponentProvided, portConfigurationProvided);
            portComponentProvided.setConfiguration(friend, this);

            return true;
        }

        return false;
    }

    /**
     * @param portConfigurationRequired
     * @param portComponentRequired
     * @param <T>
     */
    protected final <T> boolean bind(PortConfigurationRequired<T> portConfigurationRequired, PortComponentRequired<T> portComponentRequired) {
        if (portConfigurationRequired != null && portComponentRequired != null && !bindings2.containsKey(portConfigurationRequired)) {
            bindings2.put(portConfigurationRequired, portComponentRequired);
            portConfigurationRequired.addConfiguration(friend, this);
            portComponentRequired.setConfiguration(friend, this);

            return true;
        }

        return false;
    }

    /**
     * @param portConfigurationRequired
     * @param portComponentRequired
     * @param <T>
     */
    protected final <T> boolean unbind(PortConfigurationRequired<T> portConfigurationRequired, PortComponentRequired<T> portComponentRequired) {
        if (bindings2.get(portConfigurationRequired) == portComponentRequired) {
            bindings2.remove(portConfigurationRequired);
            portConfigurationRequired.removeConfiguration(friend, this);
            portComponentRequired.setConfiguration(friend, null);

            return true;
        }

        return false;
    }

    /**
     * @param portComponentProvided
     * @param portConfigurationProvided
     * @param <T>
     */
    protected final <T> boolean unbind(PortComponentProvided<T> portComponentProvided, PortConfigurationProvided<T> portConfigurationProvided) {
        if (bindings1.get(portComponentProvided) == portConfigurationProvided) {
            bindings1.remove(portComponentProvided);
            portComponentProvided.setConfiguration(friend, null);

            return true;
        }

        return false;
    }

    /**
     * @param portComponentProvided
     * @param roleRequired
     * @param <T>
     */
    protected final <T> boolean attach(PortComponentProvided<T> portComponentProvided, RoleRequired<T> roleRequired) {
        if (portComponentProvided != null && roleRequired != null && !attachments1.containsKey(portComponentProvided)) {
            attachments1.put(portComponentProvided, roleRequired);
            portComponentProvided.setConfiguration(friend, this);
            roleRequired.setConfiguration(friend, this);

            return true;
        }

        return false;
    }

    /**
     * @param roleProvided
     * @param portComponentRequired
     * @param <T>
     */
    protected final <T> boolean attach(RoleProvided<T> roleProvided, PortComponentRequired<T> portComponentRequired) {
        if (roleProvided != null && portComponentRequired != null && !attachments2.containsKey(roleProvided)) {
            attachments2.put(roleProvided, portComponentRequired);
            roleProvided.setConfiguration(friend, this);
            portComponentRequired.setConfiguration(friend, this);

            return true;
        }

        return false;
    }

    /**
     * @param portComponentProvided
     * @param roleRequired
     * @param <T>
     */
    protected final <T> boolean detach(PortComponentProvided<T> portComponentProvided, RoleRequired<T> roleRequired) {
        if (attachments1.get(portComponentProvided) == roleRequired) {
            attachments1.remove(portComponentProvided);
            portComponentProvided.setConfiguration(friend, null);
            roleRequired.setConfiguration(friend, null);

            return true;
        }

        return false;
    }

    /**
     * @param roleProvided
     * @param portComponentRequired
     * @param <T>
     */
    protected final <T> boolean detach(RoleProvided<T> roleProvided, PortComponentRequired<T> portComponentRequired) {
        if (attachments2.get(roleProvided) == portComponentRequired) {
            attachments2.remove(roleProvided);
            roleProvided.setConfiguration(friend, null);
            portComponentRequired.setConfiguration(friend, null);

            return true;
        }

        return false;
    }

    /**
     * @param friend
     * @param portComponentProvided
     * @param t
     * @param <T>
     */
    public final <T> boolean read(PortComponentProvided.Friend friend, PortComponentProvided<T> portComponentProvided, T t) {
        friend.hashCode();

        boolean status = false;

        RoleRequired<T> roleRequired = attachments1.get(portComponentProvided);
        if (roleRequired != null) {
            roleRequired.read(Configuration.friend, this, t);

            status = true;
        }

        PortConfigurationProvided<T> portConfigurationProvided = bindings1.get(portComponentProvided);
        if (portConfigurationProvided != null) {
            portConfigurationProvided.read(Configuration.friend, t);

            status = true;
        }

        return status;
    }

    /**
     * @param friend
     * @param portConfigurationRequired
     * @param t
     * @param <T>
     */
    public final <T> boolean read(PortConfigurationRequired.Friend friend, PortConfigurationRequired<T> portConfigurationRequired, T t) {
        friend.hashCode();

        PortComponentRequired<T> portComponentRequired = bindings2.get(portConfigurationRequired);
        if (portComponentRequired != null) {
            portComponentRequired.read(Configuration.friend, this, t);

            return true;
        }

        return false;
    }

    /**
     * @param friend
     * @param roleProvided
     * @param t
     * @param <T>
     */
    public final <T> boolean read(RoleProvided.Friend friend, RoleProvided<T> roleProvided, T t) {
        friend.hashCode();

        PortComponentRequired<T> portComponentRequired = attachments2.get(roleProvided);
        if (portComponentRequired != null) {
            portComponentRequired.read(Configuration.friend, this, t);

            return true;
        }

        return false;
    }

    public final <T> boolean read(PortConfigurationProvided.Friend friend, PortConfigurationProvided<T> portConfigurationProvided, T t) {
        friend.hashCode();

        PortConfigurationRequired<T> portConfigurationRequired = bridges.get(portConfigurationProvided);
        if (portConfigurationRequired != null) {
            portConfigurationRequired.read(Configuration.friend, t);

            return true;
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

package fr.univ.nantes.enfola.m2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public abstract class Configuration implements ArchitecturalObject {
    private static final Friend friend = new Friend();
    private Map<PortComponentProvided, PortConfigurationProvided> bindings1;
    private Map<PortConfigurationRequired, PortComponentRequired> bindings2;
    private Map<PortComponentProvided, RoleRequired> attachments1;
    private Map<RoleProvided, PortComponentRequired> attachments2;

    protected Configuration() {
        bindings1 = new HashMap<PortComponentProvided, PortConfigurationProvided>();
        bindings2 = new HashMap<PortConfigurationRequired, PortComponentRequired>();
        attachments1 = new HashMap<PortComponentProvided, RoleRequired>();
        attachments2 = new HashMap<RoleProvided, PortComponentRequired>();
    }

    /**
     * @param portConfigurationRequired
     * @param portComponentRequired
     * @param <T>
     */
    public final <T> void bind(PortConfigurationRequired<T> portConfigurationRequired, PortComponentRequired<T> portComponentRequired) {
        bindings2.put(portConfigurationRequired, portComponentRequired);
        portConfigurationRequired.setConfiguration(friend, this);
        portComponentRequired.setConfiguration(friend, this);
    }

    /**
     * @param portComponentProvided
     * @param portConfigurationProvided
     * @param <T>
     */
    public final <T> void bind(PortComponentProvided<T> portComponentProvided, PortConfigurationProvided<T> portConfigurationProvided) {
        bindings1.put(portComponentProvided, portConfigurationProvided);
        portComponentProvided.setConfiguration(friend, this);
        portConfigurationProvided.setConfiguration(friend, this);
    }

    /**
     * @param portComponentProvided
     * @param roleRequired
     * @param <T>
     */
    public final <T> void attach(PortComponentProvided<T> portComponentProvided, RoleRequired<T> roleRequired) {
        attachments1.put(portComponentProvided, roleRequired);
        portComponentProvided.setConfiguration(friend, this);
        roleRequired.setConfiguration(friend, this);
    }

    /**
     * @param roleProvided
     * @param portComponentRequired
     * @param <T>
     */
    public final <T> void attach(RoleProvided<T> roleProvided, PortComponentRequired<T> portComponentRequired) {
        attachments2.put(roleProvided, portComponentRequired);
        roleProvided.setConfiguration(friend, this);
        portComponentRequired.setConfiguration(friend, this);
    }

    public final <T> void read(PortComponentProvided.Friend friend, PortComponentProvided<T> portComponentProvided, T t) {
        friend.hashCode();

        /*attachments1.get(portComponentProvided).read(Configuration.friend, this, t);*/
        RoleRequired<T> roleRequired = attachments1.get(portComponentProvided);
        if (roleRequired != null) {
            roleRequired.read(Configuration.friend, this, t);
        }

        /*bindings1.get(portComponentProvided).read(Configuration.friend, this, t);*/
        PortConfigurationProvided<T> portConfigurationProvided = bindings1.get(portComponentProvided);
        if (portConfigurationProvided != null) {
            portConfigurationProvided.read(Configuration.friend, this, t);
        }
    }

    public final <T> void read(PortConfigurationRequired.Friend friend, PortConfigurationRequired<T> portConfigurationRequired, T t) {
        friend.hashCode();

        /*bindings2.get(portConfigurationRequired).read(Configuration.friend, this, t);*/
        PortComponentRequired<T> portComponentRequired = bindings2.get(portConfigurationRequired);
        if (portComponentRequired != null) {
            portComponentRequired.read(Configuration.friend, this, t);
        }
    }

    public final <T> void read(RoleProvided.Friend friend, RoleProvided<T> roleProvided, T t) {
        friend.hashCode();

        /*attachments2.get(roleProvided).read(Configuration.friend, this, t);*/
        PortComponentRequired<T> portComponentRequired = attachments2.get(roleProvided);
        if (portComponentRequired != null) {
            portComponentRequired.read(Configuration.friend, this, t);
        }
    }

    public static final class Friend {
        private Friend() {
        }
    }
}

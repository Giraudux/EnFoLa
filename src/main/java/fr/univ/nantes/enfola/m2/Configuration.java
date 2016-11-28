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
    protected final <T> void bridge(PortConfigurationProvided<T> portConfigurationProvided, PortConfigurationRequired<T> portConfigurationRequired) {
        //if(portConfigurationProvided != null && portConfigurationRequired != null && !localBindings.containsKey(portConfigurationProvided) && !localBindings.containsValue(portConfigurationRequired)) {
        bridges.put(portConfigurationProvided, portConfigurationRequired);
        //}
    }

    /**
     * @param portComponentProvided
     * @param portConfigurationProvided
     * @param <T>
     */
    protected final <T> void bind(PortComponentProvided<T> portComponentProvided, PortConfigurationProvided<T> portConfigurationProvided) {
        //if (portComponentProvided != null && portConfigurationProvided != null && !bindings1.containsKey(portComponentProvided) && !bindings1.containsValue(portConfigurationProvided)) {
        bindings1.put(portComponentProvided, portConfigurationProvided);
        portComponentProvided.setConfiguration(friend, this);
        //}
    }

    /**
     * @param portConfigurationRequired
     * @param portComponentRequired
     * @param <T>
     */
    protected final <T> void bind(PortConfigurationRequired<T> portConfigurationRequired, PortComponentRequired<T> portComponentRequired) {
        //if (portConfigurationRequired != null && portComponentRequired != null && !bindings2.containsKey(portConfigurationRequired) && !bindings2.containsValue(portComponentRequired)) {
        bindings2.put(portConfigurationRequired, portComponentRequired);
        portConfigurationRequired.addConfiguration(friend, this);
        portComponentRequired.setConfiguration(friend, this);
        //}
    }

    /**
     * @param portConfigurationRequired
     * @param portComponentRequired
     * @param <T>
     */
    protected final <T> void unbind(PortConfigurationRequired<T> portConfigurationRequired, PortComponentRequired<T> portComponentRequired) {
        //TODO
    }

    /**
     * @param portComponentProvided
     * @param portConfigurationProvided
     * @param <T>
     */
    protected final <T> void unbind(PortComponentProvided<T> portComponentProvided, PortConfigurationProvided<T> portConfigurationProvided) {
        //TODO
    }

    /**
     * @param portComponentProvided
     * @param roleRequired
     * @param <T>
     */
    protected final <T> void attach(PortComponentProvided<T> portComponentProvided, RoleRequired<T> roleRequired) {
        //if (portComponentProvided != null && roleRequired != null && !attachments1.containsKey(portComponentProvided) && !attachments1.containsValue(roleRequired)) {
        attachments1.put(portComponentProvided, roleRequired);
        portComponentProvided.setConfiguration(friend, this);
        roleRequired.setConfiguration(friend, this);
        //}
    }

    /**
     * @param roleProvided
     * @param portComponentRequired
     * @param <T>
     */
    protected final <T> void attach(RoleProvided<T> roleProvided, PortComponentRequired<T> portComponentRequired) {
        //if (roleProvided != null && portComponentRequired != null && !attachments2.containsKey(roleProvided) && !attachments2.containsValue(portComponentRequired)) {
        attachments2.put(roleProvided, portComponentRequired);
        roleProvided.setConfiguration(friend, this);
        portComponentRequired.setConfiguration(friend, this);
        //}
    }

    /**
     * @param portComponentProvided
     * @param roleRequired
     * @param <T>
     */
    protected final <T> void detach(PortComponentProvided<T> portComponentProvided, RoleRequired<T> roleRequired) {
        //TODO
    }

    /**
     * @param roleProvided
     * @param portComponentRequired
     * @param <T>
     */
    protected final <T> void detach(RoleProvided<T> roleProvided, PortComponentRequired<T> portComponentRequired) {
        //TODO
    }

    /**
     * @param friend
     * @param portComponentProvided
     * @param t
     * @param <T>
     */
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

    /**
     * @param friend
     * @param portConfigurationRequired
     * @param t
     * @param <T>
     */
    public final <T> void read(PortConfigurationRequired.Friend friend, PortConfigurationRequired<T> portConfigurationRequired, T t) {
        friend.hashCode();

        /*bindings2.get(portConfigurationRequired).read(Configuration.friend, this, t);*/
        PortComponentRequired<T> portComponentRequired = bindings2.get(portConfigurationRequired);
        if (portComponentRequired != null) {
            portComponentRequired.read(Configuration.friend, this, t);
        }
    }

    /**
     * @param friend
     * @param roleProvided
     * @param t
     * @param <T>
     */
    public final <T> void read(RoleProvided.Friend friend, RoleProvided<T> roleProvided, T t) {
        friend.hashCode();

        /*attachments2.get(roleProvided).read(Configuration.friend, this, t);*/
        PortComponentRequired<T> portComponentRequired = attachments2.get(roleProvided);
        if (portComponentRequired != null) {
            portComponentRequired.read(Configuration.friend, this, t);
        }
    }

    public final <T> void read(PortConfigurationProvided.Friend friend, PortConfigurationProvided<T> portConfigurationProvided, T t) {
        friend.hashCode();

        /*localBindings.get(portConfigurationProvided).read(Configuration.friend, this, t);*/
        PortConfigurationRequired<T> portConfigurationRequired = bridges.get(portConfigurationProvided);
        if (portConfigurationRequired != null) {
            portConfigurationRequired.read(Configuration.friend, t);
        }
    }

    /**
     *
     */
    public static final class Friend {
        private Friend() {
        }
    }
}

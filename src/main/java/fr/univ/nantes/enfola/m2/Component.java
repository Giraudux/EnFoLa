package fr.univ.nantes.enfola.m2;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author Alexis Giraudet
 * @author Pierre Gaultier
 */
public abstract class Component implements ArchitecturalObject {
    private static final Friend friend = new Friend();
    private Collection<PortComponentProvided> portComponentProvideds;
    private Collection<PortComponentRequired> portComponentRequireds;

    /**
     *
     */
    protected Component() {
        super();

        portComponentProvideds = new HashSet<PortComponentProvided>();
        portComponentRequireds = new HashSet<PortComponentRequired>();
    }

    /**
     * @param friend
     * @param portComponentProvided
     * @param <T>
     * @return
     */
    public final <T> boolean addPortComponentProvided(PortComponentProvided.Friend friend, PortComponentProvided<T> portComponentProvided) {
        friend.hashCode();

        return portComponentProvideds.add(portComponentProvided);
    }

    /**
     * @param friend
     * @param portComponentRequired
     * @param <T>
     * @return
     */
    public final <T> boolean addPortComponentRequired(PortComponentRequired.Friend friend, PortComponentRequired<T> portComponentRequired) {
        friend.hashCode();

        return portComponentRequireds.add(portComponentRequired);
    }

    /**
     * @param friend
     * @param portComponentRequired
     * @param t
     * @param <T>
     */
    public final <T> void read(PortComponentRequired.Friend friend, PortComponentRequired<T> portComponentRequired, T t) {
        friend.hashCode();

        if (portComponentRequireds.contains(portComponentRequired)) {
            read(portComponentRequired, t);
        }
    }

    /**
     * @param portComponentRequired
     * @param t
     * @param <T>
     */
    protected abstract <T> void read(PortComponentRequired<T> portComponentRequired, T t);

    /**
     * @param portComponentProvided
     * @param t
     * @param <T>
     */
    protected final <T> boolean write(PortComponentProvided<T> portComponentProvided, T t) {
        if (portComponentProvideds.contains(portComponentProvided)) {
            return portComponentProvided.read(friend, this, t);
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

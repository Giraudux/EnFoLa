package fr.univ.nantes.enfola.m2;

import java.util.ArrayList;
import java.util.Collection;

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

        portComponentProvideds = new ArrayList<PortComponentProvided>();
        portComponentRequireds = new ArrayList<PortComponentRequired>();
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
     * @param portComponentProvided
     * @param <T>
     * @return
     */
    public final <T> boolean removePortComponentProvided(PortComponentProvided.Friend friend, PortComponentProvided<T> portComponentProvided) {
        friend.hashCode();

        return portComponentProvideds.remove(portComponentProvided);
    }

    /**
     * @param friend
     * @param portComponentRequired
     * @param <T>
     * @return
     */
    public final <T> boolean removePortComponentRequired(PortComponentRequired.Friend friend, PortComponentRequired<T> portComponentRequired) {
        friend.hashCode();

        return portComponentRequireds.remove(portComponentRequired);
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
    protected final <T> void write(PortComponentProvided<T> portComponentProvided, T t) {
        if (portComponentProvideds.contains(portComponentProvided)) {
            portComponentProvided.read(friend, this, t);
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

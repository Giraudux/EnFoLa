package fr.univ.nantes.enfola.m2;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public abstract class Component implements ArchitecturalObject {
    private static final Friend friend = new Friend();
    private Collection<PortComponentProvided> portComponentProvideds;
    private Collection<PortComponentRequired> portComponentRequireds;

    protected Component() {
        portComponentProvideds = new ArrayList<PortComponentProvided>();
        portComponentRequireds = new ArrayList<PortComponentRequired>();
    }

    public final <T> void addPortComponentProvided(PortComponentProvided.Friend friend, PortComponentProvided<T> portComponentProvided) {
        friend.hashCode();

        portComponentProvideds.add(portComponentProvided);
    }

    public final <T> void addPortComponentRequired(PortComponentRequired.Friend friend, PortComponentRequired<T> portComponentRequired) {
        friend.hashCode();

        portComponentRequireds.add(portComponentRequired);
    }

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

    public static final class Friend {
        private Friend() {
        }
    }
}

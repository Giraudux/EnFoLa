package fr.univ.nantes.enfola.m2.interfaces.ports.configuration;

import fr.univ.nantes.enfola.m2.core.Configuration;
import fr.univ.nantes.enfola.m2.util.Writer;

/**
 * @author Alexis Giraudet
 * @author Pierre Gaultier
 */
public class PortConfigurationProvided<T> implements Writer<T>, PortConfiguration<T> {
    private static final Friend friend = new Friend();
    private Configuration configuration;

    /**
     *
     */
    public PortConfigurationProvided(Configuration configuration) {
        super();

        this.configuration = configuration;
    }

    /**
     * @param friend
     * @param t
     */
    public final boolean read(Configuration.Friend friend, T t) {
        friend.hashCode();

        if (configuration != null) {
            return configuration.read(PortConfigurationProvided.friend, this, t);
        }

        return false;
    }

    /**
     * @param t
     */
    @Override
    public void write(T t) {
        configuration.read(friend, this, t);
    }

    /**
     *
     */
    public static final class Friend {
        private Friend() {
        }
    }
}

package fr.univ.nantes.enfola.m2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * @author Alexis Giraudet
 * @author Pierre Gaultier
 */
public class PortConfigurationRequired<T> implements PortConfiguration<T> {
    private static final Friend friend = new Friend();
    private final Configuration configuration;
    private Collection<Reader<T>> readers;
    private Collection<Configuration> configurations;

    /**
     * @param configuration
     */
    public PortConfigurationRequired(Configuration configuration) {
        super();

        this.configuration = configuration;
        readers = new ArrayList<Reader<T>>();
        configurations = new HashSet<Configuration>();
    }

    /**
     * @param friend
     * @param t
     */
    public void read(Configuration.Friend friend, T t) {
        friend.hashCode();

        for (Configuration configuration : configurations) {
            configuration.read(PortConfigurationRequired.friend, this, t);
        }

        for (Reader<T> reader : readers) {
            reader.read(t);
        }
    }

    /**
     * @param friend
     * @param configuration
     * @return
     */
    public final boolean addConfiguration(Configuration.Friend friend, Configuration configuration) {
        friend.hashCode();

        return configurations.add(configuration);
    }

    /**
     * @param friend
     * @param configuration
     * @return
     */
    public final boolean removeConfiguration(Configuration.Friend friend, Configuration configuration) {
        friend.hashCode();

        return configurations.remove(configuration);
    }

    /**
     * @param reader
     * @return
     */
    public final boolean addReader(Reader<T> reader) {
        return readers.add(reader);
    }

    /**
     * @param reader
     * @return
     */
    public final boolean removeReader(Reader<T> reader) {
        return readers.remove(reader);
    }

    /**
     *
     */
    public static final class Friend {
        private Friend() {
        }
    }
}

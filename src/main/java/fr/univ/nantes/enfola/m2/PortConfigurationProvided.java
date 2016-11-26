package fr.univ.nantes.enfola.m2;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Alexis Giraudet
 * @author Pierre Gaultier
 */
public class PortConfigurationProvided<T> implements PortConfiguration<T> {
    private static final Friend friend = new Friend();
    private Configuration configuration;
    private Collection<Reader<T>> readers;

    /**
     *
     */
    public PortConfigurationProvided() {
        super();

        this.configuration = null;
        readers = new ArrayList<Reader<T>>();
    }

    /**
     * @param friend
     * @param configuration
     */
    public final void setConfiguration(Configuration.Friend friend, Configuration configuration) {
        friend.hashCode();

        this.configuration = configuration;
    }

    /**
     * @param friend
     * @param configuration
     * @param t
     */
    public final void read(Configuration.Friend friend, Configuration configuration, T t) {
        friend.hashCode();

        if (this.configuration == configuration) {
            for (Reader<T> reader : readers) {
                reader.read(t);
            }
        }
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

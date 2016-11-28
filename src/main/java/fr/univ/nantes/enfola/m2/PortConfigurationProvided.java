package fr.univ.nantes.enfola.m2;

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
     * @param configuration
     * @param t
     */
    public final void read(Configuration.Friend friend, Configuration configuration, T t) {
        friend.hashCode();

        this.configuration.read(PortConfigurationProvided.friend, this, t);
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

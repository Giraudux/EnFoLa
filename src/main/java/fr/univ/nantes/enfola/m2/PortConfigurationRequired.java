package fr.univ.nantes.enfola.m2;

/**
 * @author Alexis Giraudet
 * @author Pierre Gaultier
 */
public class PortConfigurationRequired<T> implements Writer<T>, PortConfiguration<T> {
    private static final Friend friend = new Friend();
    private Configuration configuration;

    /**
     *
     */
    public PortConfigurationRequired() {
        super();

        this.configuration = null;
    }

    /**
     * @param friend
     * @param configuration
     */
    public final void setConfiguration(Configuration.Friend friend, Configuration configuration) {
        friend.hashCode();

        if (this.configuration != null) {
            this.configuration = configuration;
        }
    }

    /**
     * @param t
     */
    public final void write(T t) {
        if (configuration != null) {
            configuration.read(friend, this, t);
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

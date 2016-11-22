package fr.univ.nantes.enfola.m2;

/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public abstract class PortConfigurationRequired<T> implements PortConfiguration<T> {
    private static final Friend friend = new Friend();
    private Configuration configuration;

    protected PortConfigurationRequired() {
        super();

        this.configuration = null;
    }

    public final void setConfiguration(Configuration.Friend friend, Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * @param t
     */
    public final void write(T t) {
        configuration.read(friend, this, t);
    }

    public static final class Friend {
        private Friend() {
        }
    }
}

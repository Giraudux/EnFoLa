package fr.univ.nantes.enfola.m2;

/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public abstract class PortConfigurationProvided<T> implements PortConfiguration<T> {

    private static final Friend friend = new Friend();
    private Configuration configuration;
    public PortConfigurationProvided() {
        super();

        this.configuration = null;
    }

    public final void setConfiguration(Configuration.Friend friend, Configuration configuration) {
        this.configuration = configuration;
    }

    public final void read(Configuration.Friend friend, Configuration configuration, T t) {
        friend.hashCode();

        if (this.configuration == configuration) {
            read(t);
        }
    }

    protected abstract void read(T t);

    public static final class Friend {
        private Friend() {
        }
    }
}

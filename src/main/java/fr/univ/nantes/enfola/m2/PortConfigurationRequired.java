package fr.univ.nantes.enfola.m2;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public class PortConfigurationRequired<T> extends Observable implements Observer, PortConfiguration<T> {
    private static final Friend friend = new Friend();
    private Configuration configuration;
    public PortConfigurationRequired() {
        super();

        this.configuration = null;
    }

    public final void setConfiguration(Configuration.Friend friend, Configuration configuration) {
        this.configuration = configuration;
    }

    public final void update(Observable o, Object arg) {
        setChanged();
        notifyObservers(arg);
    }

    public static final class Friend {
        private Friend() {
        }
    }
}

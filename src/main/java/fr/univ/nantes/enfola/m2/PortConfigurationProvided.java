package fr.univ.nantes.enfola.m2;

import java.util.Observable;

/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public abstract class PortConfigurationProvided<T> extends Observable implements PortConfiguration<T> {
    public void write(T t) {
        setChanged();
        notifyObservers(t);
    }
}

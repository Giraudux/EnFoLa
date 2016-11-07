package fr.univ.nantes.enfola.m2;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public abstract class RoleProvided extends Observable implements Observer, Role {
    public final void update(Observable o, Object arg) {
        setChanged();
        notifyObservers(arg);
    }
}

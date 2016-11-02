package fr.univ.nantes.enfola.m2;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public abstract class RoleRequired<T> implements Role<T>, Observer {
    public abstract void read(T t);

    public void update(Observable o, Object arg) {
        read((T) arg);
    }
}

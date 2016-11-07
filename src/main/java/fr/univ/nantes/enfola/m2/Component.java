package fr.univ.nantes.enfola.m2;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public abstract class Component extends Observable implements Observer, ArchitecturalObject {
    public void addPortComponentRequired(PortComponentRequired portComponentRequired) {
        portComponentRequired.addObserver(this);
    }
}

package fr.univ.nantes.enfola.m2;

import java.util.*;

/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public class Configuration implements ArchitecturalObject, Observer {
    private Map<Object, Observer> map = new HashMap<Object, Observer>(); // bindings + attachments

    public final void bind(PortConfigurationRequired portConfigurationRequired, PortComponentRequired portComponentRequired) {
        map.put(portConfigurationRequired, portComponentRequired);
        portConfigurationRequired.addObserver(this);
    }

    public final void bind(PortComponentProvided portComponentProvided, PortConfigurationProvided portConfigurationProvided) {
        map.put(portComponentProvided, portConfigurationProvided);
        portComponentProvided.addObserver(this);
    }

    public final void attach(RoleProvided roleProvided, PortComponentRequired portComponentRequired) {
        map.put(roleProvided, portComponentRequired);
        roleProvided.addObserver(this);
    }

    public final void attach(PortComponentProvided portComponentProvided, RoleRequired roleRequired) {
        map.put(portComponentProvided, roleRequired);
        portComponentProvided.addObserver(this);
    }

    public final void update(Observable o, Object arg) {
        map.get(o).update(o, arg);
    }
}

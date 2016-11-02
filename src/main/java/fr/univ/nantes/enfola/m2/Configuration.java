package fr.univ.nantes.enfola.m2;

import java.util.Collection;

/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public class Configuration extends ArchitecturalObject {
    public static final class Friend {
        private Friend() {
        }
    }

    private static final Friend friend = new Friend();
    private Collection<ArchitecturalObject> architecturalObjects; // components and connectors
    private Collection attachments;
    private Collection bindings;

    public boolean addArchitecturalObject(ArchitecturalObject architecturalObject) {
        return architecturalObjects.add(architecturalObject);
    }

    public boolean removeArchitecturalObject(ArchitecturalObject architecturalObject) {
        return architecturalObjects.remove(architecturalObject);
    }

    public <T> void bind(PortComponentRequired<T> requiredComponentPort, PortConfigurationRequired<T> requiredConfigurationPort) {
        ;
    }

    public <T> void bind(PortComponentProvided<T> providedComponentPort, PortConfigurationProvided<T> providedConfigurationPort) {
        ;
    }

    public <T> void attach(PortComponentProvided<T> providedComponentPort, RoleRequired<T> requiredRole) {
        ;
    }

    public <T> void attach(PortComponentRequired<T> requiredComponentPort, RoleProvided<T> providedRole) {
        ;
    }
}

package fr.univ.nantes.enfola.m2;

import java.util.Collection;

/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public abstract class Configuration extends ArchitecturalObject {
    private Collection<ArchitecturalObject> architecturalObjects; // components and connectors
    private Collection attachments;
    private Collection bindings;

    public boolean addArchitecturalObject(ArchitecturalObject architecturalObject) {
        return architecturalObjects.add(architecturalObject);
    }

    public boolean removeArchitecturalObject(ArchitecturalObject architecturalObject) {
        return architecturalObjects.remove(architecturalObject);
    }

    public void bind(PortComponentRequired requiredComponentPort, PortConfigurationRequired requiredConfigurationPort) {
        ;
    }

    public void bind(PortComponentProvided providedComponentPort, PortConfigurationProvided providedConfigurationPort) {
        ;
    }

    public void attach(PortComponentProvided providedComponentPort, RoleRequired requiredRole) {
        ;
    }

    public void attach(PortComponentRequired requiredComponentPort, RoleProvided providedRole) {
        ;
    }
}

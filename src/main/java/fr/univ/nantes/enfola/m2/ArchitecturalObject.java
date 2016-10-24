package fr.univ.nantes.enfola.m2;

import java.util.Collection;

/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */

public abstract class ArchitecturalObject {
    private Collection<Interface> interfaces;
    private Collection<Property> properties;
    private Collection<Constraint> constraints;

    public Collection<Interface> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Collection<Interface> interfaces) {
        this.interfaces = interfaces;
    }

    public Collection<Property> getProperties() {
        return properties;
    }

    public void setProperties(Collection<Property> properties) {
        this.properties = properties;
    }

    public Collection<Constraint> getConstraints() {
        return constraints;
    }

    public void setConstraints(Collection<Constraint> constraints) {
        this.constraints = constraints;
    }
}

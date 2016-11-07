package fr.univ.nantes.enfola.m2;

import java.util.Collection;

/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public abstract class Connector implements ArchitecturalObject {
    public static final class Friend {
        private Friend() {}
    }

    private static final Friend friend = new Friend();

    private Collection<Glue> glues;
    private Collection<Role> roles;

    public Collection<Glue> getGlues() {
        return glues;
    }

    public void setGlues(Collection<Glue> glues) {
        this.glues = glues;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}

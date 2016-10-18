package fr.univ.nantes.enfola.m0;

import java.util.Collection;

/**
 * @author Alexis Giraudet
 * @date 17/10/16
 */
public abstract class Configuration extends ArchitecturalObject {
    private Collection<ArchitecturalObject> architecturalObjects;
    private Collection<Attachment> attachments;
    private Collection<Binding> bindings;

    public Collection<ArchitecturalObject> getArchitecturalObjects() {
        return architecturalObjects;
    }

    public void setArchitecturalObjects(Collection<ArchitecturalObject> architecturalObjects) {
        this.architecturalObjects = architecturalObjects;
    }

    public Collection<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Collection<Attachment> attachments) {
        this.attachments = attachments;
    }

    public Collection<Binding> getBindings() {
        return bindings;
    }

    public void setBindings(Collection<Binding> bindings) {
        this.bindings = bindings;
    }
}

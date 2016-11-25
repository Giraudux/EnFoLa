package fr.univ.nantes.enfola.m1.component;

import fr.univ.nantes.enfola.m2.Component;
import fr.univ.nantes.enfola.m2.PortComponentProvided;
import fr.univ.nantes.enfola.m2.PortComponentRequired;

/**
 * @author Alexis Giraudet
 */
public class ClientComponent extends Component {
    private final PortComponentProvided<String> queryOut;
    private final PortComponentRequired<String> replyIn;

    public ClientComponent() {
        super();

        queryOut = new PortComponentProvided<String>(this);
        replyIn = new PortComponentRequired<String>(this);
    }

    protected <T> void read(PortComponentRequired<T> portComponentRequired, T t) {
        ;
    }

    public PortComponentProvided<String> getQueryOut() {
        return queryOut;
    }

    public PortComponentRequired<String> getReplyIn() {
        return replyIn;
    }
}

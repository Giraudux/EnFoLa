package fr.univ.nantes.enfola.m1.component;

import fr.univ.nantes.enfola.m2.Component;
import fr.univ.nantes.enfola.m2.PortComponentProvided;
import fr.univ.nantes.enfola.m2.PortComponentRequired;

/**
 * Created by E115911Q on 24/10/16.
 */
public class ConnectionComponent extends Component {
    private final PortComponentProvided<String> portClearanceOut;
    private final PortComponentProvided<String> portSocketOut;
    private final PortComponentRequired<String> portClearanceIn;
    private final PortComponentRequired<String> portSocketIn;

    public ConnectionComponent() {
        super();

        portClearanceOut = new PortComponentProvided<String>(this);
        portSocketOut = new PortComponentProvided<String>(this);
        portClearanceIn = new PortComponentRequired<String>(this);
        portSocketIn = new PortComponentRequired<String>(this);
    }

    protected <T> void read(PortComponentRequired<T> portComponentRequired, T t) {
        if (portComponentRequired == portClearanceIn) {
            write(portSocketOut, (String) t);
        } else if (portComponentRequired == portSocketIn) {
            write(portClearanceOut, (String) t);
        }
    }

    public PortComponentProvided<String> getPortClearanceOut() {
        return portClearanceOut;
    }

    public PortComponentProvided<String> getPortSocketOut() {
        return portSocketOut;
    }

    public PortComponentRequired<String> getPortClearanceIn() {
        return portClearanceIn;
    }

    public PortComponentRequired<String> getPortSocketIn() {
        return portSocketIn;
    }
}

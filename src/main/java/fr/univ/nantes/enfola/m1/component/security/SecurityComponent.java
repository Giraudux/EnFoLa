package fr.univ.nantes.enfola.m1.component.security;

import fr.univ.nantes.enfola.m2.Component;
import fr.univ.nantes.enfola.m2.PortComponentProvided;
import fr.univ.nantes.enfola.m2.PortComponentRequired;

/**
 * Created by E115911Q on 24/10/16.
 */
public class SecurityComponent extends Component {
    private final PortComponentProvided<String> portClearanceOut;
    private final PortComponentProvided<String> portSQLOut;
    private final PortComponentRequired<String> portClearanceIn;
    private final PortComponentRequired<String> portSQLIn;

    public SecurityComponent() {
        super();

        portClearanceIn = new PortComponentRequired<String>(this);
        portClearanceOut = new PortComponentProvided<String>(this);
        portSQLIn = new PortComponentRequired<String>(this);
        portSQLOut = new PortComponentProvided<String>(this);
    }

    protected <T> void read(PortComponentRequired<T> portComponentRequired, T t) {
        if (portComponentRequired == portClearanceIn) {
            write(portSQLOut, (String) t);
        } else if (portComponentRequired == portSQLIn) {
            write(portClearanceOut, (String) t);
        }
    }

    public PortComponentProvided getPortClearanceOut() {
        return portClearanceOut;
    }

    public PortComponentProvided getPortSQLOut() {
        return portSQLOut;
    }

    public PortComponentRequired getPortClearanceIn() {
        return portClearanceIn;
    }

    public PortComponentRequired getPortSQLIn() {
        return portSQLIn;
    }
}

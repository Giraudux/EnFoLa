package fr.univ.nantes.enfola.m1.component.database;

import fr.univ.nantes.enfola.m2.Component;
import fr.univ.nantes.enfola.m2.PortComponentProvided;
import fr.univ.nantes.enfola.m2.PortComponentRequired;

/**
 * Created by E115911Q on 24/10/16.
 */
public class DatabaseComponent extends Component {
    private final PortComponentProvided<String> portSecurityOut;
    private final PortComponentProvided<String> portSQLOut;
    private final PortComponentRequired<String> portSecurityIn;
    private final PortComponentRequired<String> portSQLIn;

    public DatabaseComponent() {
        super();

        portSecurityOut = new PortComponentProvided<String>(this);
        portSQLOut = new PortComponentProvided<String>(this);
        portSecurityIn = new PortComponentRequired<String>(this);
        portSQLIn = new PortComponentRequired<String>(this);
    }

    protected <T> void read(PortComponentRequired<T> portComponentRequired, T t) {
        if (portComponentRequired == portSecurityIn) {
            write(portSQLOut, (String) t);
        } else if (portComponentRequired == portSQLIn) {
            write(portSecurityOut, (String) t);
        }
    }

    public PortComponentProvided<String> getPortSecurityOut() {
        return portSecurityOut;
    }

    public PortComponentProvided<String> getPortSQLOut() {
        return portSQLOut;
    }

    public PortComponentRequired<String> getPortSecurityIn() {
        return portSecurityIn;
    }

    public PortComponentRequired<String> getPortSQLIn() {
        return portSQLIn;
    }
}

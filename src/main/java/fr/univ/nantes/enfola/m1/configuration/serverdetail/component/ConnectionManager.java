package fr.univ.nantes.enfola.m1.configuration.serverdetail.component;

import fr.univ.nantes.enfola.m2.Component;
import fr.univ.nantes.enfola.m2.PortComponentProvided;
import fr.univ.nantes.enfola.m2.PortComponentRequired;

import java.util.logging.Logger;

/**
 * @author Alexis Giraudet
 */
public class ConnectionManager extends Component {
    private final static Logger LOGGER = Logger.getLogger(ConnectionManager.class.getName());
    private final PortComponentProvided<String> portServerDetailProvided;
    private final PortComponentRequired<String> portServerDetailRequired;
    private final PortComponentProvided<String> portClearanceRequestProvided;
    private final PortComponentRequired<String> portClearanceRequestRequired;
    private final PortComponentProvided<String> portSqlRequestProvided;
    private final PortComponentRequired<String> portSqlRequestRequired;

    public ConnectionManager() {
        super();

        portServerDetailProvided = new PortComponentProvided<String>(this);
        portServerDetailRequired = new PortComponentRequired<String>(this);
        portClearanceRequestProvided = new PortComponentProvided<String>(this);
        portClearanceRequestRequired = new PortComponentRequired<String>(this);
        portSqlRequestProvided = new PortComponentProvided<String>(this);
        portSqlRequestRequired = new PortComponentRequired<String>(this);
    }

    protected <T> void read(PortComponentRequired<T> portComponentRequired, T t) {
        LOGGER.info(t.toString());

        if(portComponentRequired == portClearanceRequestRequired) {
            write(portServerDetailProvided, (String) t);
        } else if(portComponentRequired == portServerDetailRequired) {
            write(portClearanceRequestProvided, (String) t);
        } else if(portComponentRequired == portSqlRequestRequired) {
            write(portServerDetailProvided, (String) t);
        }
    }

    public PortComponentProvided<String> getPortServerDetailProvided() {
        return portServerDetailProvided;
    }

    public PortComponentRequired<String> getPortServerDetailRequired() {
        return portServerDetailRequired;
    }

    public PortComponentProvided<String> getPortClearanceRequestProvided() {
        return portClearanceRequestProvided;
    }

    public PortComponentRequired<String> getPortClearanceRequestRequired() {
        return portClearanceRequestRequired;
    }

    public PortComponentProvided<String> getPortSqlRequestProvided() {
        return portSqlRequestProvided;
    }

    public PortComponentRequired<String> getPortSqlRequestRequired() {
        return portSqlRequestRequired;
    }
}

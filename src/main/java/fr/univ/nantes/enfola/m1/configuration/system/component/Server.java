package fr.univ.nantes.enfola.m1.configuration.system.component;

import fr.univ.nantes.enfola.m2.Component;
import fr.univ.nantes.enfola.m2.PortComponentProvided;
import fr.univ.nantes.enfola.m2.PortComponentRequired;

import java.util.logging.Logger;

/**
 * @author Alexis Giraudet
 */
public class Server extends Component {
    private final static Logger LOGGER = Logger.getLogger(Server.class.getName());
    private final PortComponentProvided<String> portRpcProvided;
    private final PortComponentRequired<String> portRpcRequired;
    private final PortComponentProvided<String> portServerDetailProvided;
    private final PortComponentRequired<String> portServerDetailRequired;

    public Server() {
        super();

        portRpcProvided = new PortComponentProvided<String>(this);
        portRpcRequired = new PortComponentRequired<String>(this);
        portServerDetailProvided = new PortComponentProvided<String>(this);
        portServerDetailRequired = new PortComponentRequired<String>(this);
    }

    protected <T> void read(PortComponentRequired<T> portComponentRequired, T t) {
        LOGGER.info(t.toString());

        if (portComponentRequired == portRpcRequired) {
            write(portServerDetailProvided, (String) t);
        } else if (portComponentRequired == portServerDetailRequired) {
            write(portRpcProvided, (String) t);
        }
    }

    public PortComponentProvided<String> getPortRpcProvided() {
        return portRpcProvided;
    }

    public PortComponentRequired<String> getPortRpcRequired() {
        return portRpcRequired;
    }

    public PortComponentProvided<String> getPortServerDetailProvided() {
        return portServerDetailProvided;
    }

    public PortComponentRequired<String> getPortServerDetailRequired() {
        return portServerDetailRequired;
    }
}

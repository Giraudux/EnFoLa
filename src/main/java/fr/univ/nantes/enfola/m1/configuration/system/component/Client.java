package fr.univ.nantes.enfola.m1.configuration.system.component;

import fr.univ.nantes.enfola.m2.Component;
import fr.univ.nantes.enfola.m2.PortComponentProvided;
import fr.univ.nantes.enfola.m2.PortComponentRequired;

import java.util.logging.Logger;

/**
 * @author Alexis Giraudet
 */
public class Client extends Component {
    private final static Logger LOGGER = Logger.getLogger(Client.class.getName());
    private final PortComponentProvided<String> portRpcProvided;
    private final PortComponentRequired<String> portRpcRequired;
    private final PortComponentProvided<String> portSystemProvided;
    private final PortComponentRequired<String> portSystemRequired;

    public Client() {
        super();

        portRpcProvided = new PortComponentProvided<String>(this);
        portRpcRequired = new PortComponentRequired<String>(this);
        portSystemProvided = new PortComponentProvided<String>(this);
        portSystemRequired = new PortComponentRequired<String>(this);
    }

    protected <T> void read(PortComponentRequired<T> portComponentRequired, T t) {
        LOGGER.info(t.toString());

        if(portComponentRequired == portRpcRequired) {
            write(portSystemProvided, (String) t);
        } else if(portComponentRequired == portSystemRequired) {
            write(portRpcProvided, (String) t);
        }
    }

    public PortComponentProvided<String> getPortRpcProvided() {
        return portRpcProvided;
    }

    public PortComponentRequired<String> getPortRpcRequired() {
        return portRpcRequired;
    }

    public PortComponentProvided<String> getPortSystemProvided() {
        return portSystemProvided;
    }

    public PortComponentRequired<String> getPortSystemRequired() {
        return portSystemRequired;
    }
}

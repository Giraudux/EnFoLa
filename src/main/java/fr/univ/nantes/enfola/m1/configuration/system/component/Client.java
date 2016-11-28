package fr.univ.nantes.enfola.m1.configuration.system.component;

import fr.univ.nantes.enfola.m2.core.Component;
import fr.univ.nantes.enfola.m2.interfaces.ports.component.PortComponentProvided;
import fr.univ.nantes.enfola.m2.interfaces.ports.component.PortComponentRequired;

import java.util.logging.Logger;

/**
 * @author Alexis Giraudet
 * @author Pierre Gaultier
 */
public class Client extends Component {
    private final static Logger LOGGER = Logger.getLogger(Client.class.getName());
    private final PortComponentProvided<String[]> portRpcProvided;
    private final PortComponentRequired<String> portRpcRequired;
    private final PortComponentProvided<String> portSystemProvided;
    private final PortComponentRequired<String[]> portSystemRequired;

    /**
     *
     */
    public Client() {
        super();

        portRpcProvided = new PortComponentProvided<String[]>(this);
        portRpcRequired = new PortComponentRequired<String>(this);
        portSystemProvided = new PortComponentProvided<String>(this);
        portSystemRequired = new PortComponentRequired<String[]>(this);
    }

    /**
     * @param portComponentRequired
     * @param t
     * @param <T>
     */
    protected <T> void read(PortComponentRequired<T> portComponentRequired, T t) {
        LOGGER.info(t.toString());

        if (portComponentRequired == portRpcRequired) {
            write(portSystemProvided, (String) t);
        } else if (portComponentRequired == portSystemRequired) {
            write(portRpcProvided, (String[]) t);
        }
    }

    /**
     * @return
     */
    public PortComponentProvided<String[]> getPortRpcProvided() {
        return portRpcProvided;
    }

    /**
     * @return
     */
    public PortComponentRequired<String> getPortRpcRequired() {
        return portRpcRequired;
    }

    /**
     * @return
     */
    public PortComponentProvided<String> getPortSystemProvided() {
        return portSystemProvided;
    }

    /**
     * @return
     */
    public PortComponentRequired<String[]> getPortSystemRequired() {
        return portSystemRequired;
    }
}

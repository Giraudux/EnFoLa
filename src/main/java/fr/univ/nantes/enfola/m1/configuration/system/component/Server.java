package fr.univ.nantes.enfola.m1.configuration.system.component;

import fr.univ.nantes.enfola.m1.bean.Query;
import fr.univ.nantes.enfola.m1.bean.Reply;
import fr.univ.nantes.enfola.m2.Component;
import fr.univ.nantes.enfola.m2.PortComponentProvided;
import fr.univ.nantes.enfola.m2.PortComponentRequired;

import java.util.logging.Logger;

/**
 * @author Alexis Giraudet
 */
public class Server extends Component {
    private final static Logger LOGGER = Logger.getLogger(Server.class.getName());
    private final PortComponentProvided<Reply> portRpcProvided;
    private final PortComponentRequired<Query> portRpcRequired;
    private final PortComponentProvided<Query> portServerDetailProvided;
    private final PortComponentRequired<Reply> portServerDetailRequired;

    public Server() {
        super();

        portRpcProvided = new PortComponentProvided<Reply>(this);
        portRpcRequired = new PortComponentRequired<Query>(this);
        portServerDetailProvided = new PortComponentProvided<Query>(this);
        portServerDetailRequired = new PortComponentRequired<Reply>(this);
    }

    protected <T> void read(PortComponentRequired<T> portComponentRequired, T t) {
        LOGGER.info(t.toString());

        if (portComponentRequired == portRpcRequired) {
            write(portServerDetailProvided, (Query) t);
        } else if (portComponentRequired == portServerDetailRequired) {
            write(portRpcProvided, (Reply) t);
        }
    }

    public PortComponentProvided<Reply> getPortRpcProvided() {
        return portRpcProvided;
    }

    public PortComponentRequired<Query> getPortRpcRequired() {
        return portRpcRequired;
    }

    public PortComponentProvided<Query> getPortServerDetailProvided() {
        return portServerDetailProvided;
    }

    public PortComponentRequired<Reply> getPortServerDetailRequired() {
        return portServerDetailRequired;
    }
}

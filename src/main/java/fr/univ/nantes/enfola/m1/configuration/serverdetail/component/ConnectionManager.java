package fr.univ.nantes.enfola.m1.configuration.serverdetail.component;

import fr.univ.nantes.enfola.m1.bean.Query;
import fr.univ.nantes.enfola.m1.bean.Reply;
import fr.univ.nantes.enfola.m2.Component;
import fr.univ.nantes.enfola.m2.PortComponentProvided;
import fr.univ.nantes.enfola.m2.PortComponentRequired;

import java.util.logging.Logger;

/**
 * @author Alexis Giraudet
 */
public class ConnectionManager extends Component {
    private final static Logger LOGGER = Logger.getLogger(ConnectionManager.class.getName());
    private final PortComponentProvided<Reply> portServerDetailProvided;
    private final PortComponentRequired<Query> portServerDetailRequired;
    private final PortComponentProvided<Query> portClearanceRequestProvided;
    private final PortComponentRequired<Reply> portClearanceRequestRequired;
    private final PortComponentProvided<Query> portSqlRequestProvided;
    private final PortComponentRequired<Reply> portSqlRequestRequired;
    private Query query;

    public ConnectionManager() {
        super();

        portServerDetailProvided = new PortComponentProvided<Reply>(this);
        portServerDetailRequired = new PortComponentRequired<Query>(this);
        portClearanceRequestProvided = new PortComponentProvided<Query>(this);
        portClearanceRequestRequired = new PortComponentRequired<Reply>(this);
        portSqlRequestProvided = new PortComponentProvided<Query>(this);
        portSqlRequestRequired = new PortComponentRequired<Reply>(this);
    }

    protected <T> void read(PortComponentRequired<T> portComponentRequired, T t) {
        LOGGER.info(t.toString());

        if (portComponentRequired == portClearanceRequestRequired) {
            Reply reply = (Reply) t;

            if(reply.getStatus() == 0) {
                write(portSqlRequestProvided, query);
            } else {
                write(portServerDetailProvided, (Reply) t);
            }
        } else if (portComponentRequired == portServerDetailRequired) {
            query = (Query) t;
            write(portClearanceRequestProvided, query);
        } else if (portComponentRequired == portSqlRequestRequired) {
            write(portServerDetailProvided, (Reply) t);
        }
    }

    public PortComponentProvided<Reply> getPortServerDetailProvided() {
        return portServerDetailProvided;
    }

    public PortComponentRequired<Query> getPortServerDetailRequired() {
        return portServerDetailRequired;
    }

    public PortComponentProvided<Query> getPortClearanceRequestProvided() {
        return portClearanceRequestProvided;
    }

    public PortComponentRequired<Reply> getPortClearanceRequestRequired() {
        return portClearanceRequestRequired;
    }

    public PortComponentProvided<Query> getPortSqlRequestProvided() {
        return portSqlRequestProvided;
    }

    public PortComponentRequired<Reply> getPortSqlRequestRequired() {
        return portSqlRequestRequired;
    }
}

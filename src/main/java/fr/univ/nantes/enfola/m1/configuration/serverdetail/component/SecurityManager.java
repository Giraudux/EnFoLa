package fr.univ.nantes.enfola.m1.configuration.serverdetail.component;

import fr.univ.nantes.enfola.m1.bean.Query;
import fr.univ.nantes.enfola.m1.bean.Reply;
import fr.univ.nantes.enfola.m2.core.Component;
import fr.univ.nantes.enfola.m2.interfaces.ports.component.PortComponentProvided;
import fr.univ.nantes.enfola.m2.interfaces.ports.component.PortComponentRequired;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Alexis Giraudet
 * @author Pierre Gaultier
 */
public class SecurityManager extends Component {
    private final static Logger LOGGER = Logger.getLogger(SecurityManager.class.getName());
    private final PortComponentProvided<Query> portSecurityQueryProvided;
    private final PortComponentRequired<Reply> portSecurityQueryRequired;
    private final PortComponentProvided<Reply> portClearanceRequestProvided;
    private final PortComponentRequired<Query> portClearanceRequestRequired;
    private Map<String, String> users;

    /**
     *
     */
    public SecurityManager() {
        super();

        portSecurityQueryProvided = new PortComponentProvided<Query>(this);
        portSecurityQueryRequired = new PortComponentRequired<Reply>(this);
        portClearanceRequestProvided = new PortComponentProvided<Reply>(this);
        portClearanceRequestRequired = new PortComponentRequired<Query>(this);

        users = new HashMap<String, String>();
        users.put("anonymous", "anonymous");
        users.put("username", "password");
        users.put("pierre", "gaultier");
        users.put("alexis", "giraudet");
    }

    /**
     * @param portComponentRequired
     * @param t
     * @param <T>
     */
    protected <T> void read(PortComponentRequired<T> portComponentRequired, T t) {
        LOGGER.info(t.toString());
        Query query = (Query) t;
        Reply reply = new Reply();

        if (portComponentRequired == portClearanceRequestRequired) {
            if (users.containsKey(query.getUsername())) {
                if (users.get(query.getUsername()).equals(query.getPassword())) {
                    write(portSecurityQueryProvided, (Query) t);
                } else {
                    reply.setStatus(45);
                    reply.setMessage("security FAILURE: invalid password");
                    write(portClearanceRequestProvided, reply);
                }
            } else {
                reply.setStatus(25);
                reply.setMessage("security FAILURE: invalid username");
                write(portClearanceRequestProvided, reply);
            }
        } else if (portComponentRequired == portSecurityQueryRequired) {
            write(portClearanceRequestProvided, (Reply) t);
        }
    }

    /**
     * @return
     */
    public PortComponentProvided<Query> getPortSecurityQueryProvided() {
        return portSecurityQueryProvided;
    }

    /**
     * @return
     */
    public PortComponentRequired<Reply> getPortSecurityQueryRequired() {
        return portSecurityQueryRequired;
    }

    /**
     * @return
     */
    public PortComponentProvided<Reply> getPortClearanceRequestProvided() {
        return portClearanceRequestProvided;
    }

    /**
     * @return
     */
    public PortComponentRequired<Query> getPortClearanceRequestRequired() {
        return portClearanceRequestRequired;
    }
}

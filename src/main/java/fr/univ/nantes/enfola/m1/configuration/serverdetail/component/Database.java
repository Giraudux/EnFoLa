package fr.univ.nantes.enfola.m1.configuration.serverdetail.component;

import fr.univ.nantes.enfola.m1.bean.Query;
import fr.univ.nantes.enfola.m1.bean.Reply;
import fr.univ.nantes.enfola.m2.core.Component;
import fr.univ.nantes.enfola.m2.interfaces.ports.component.PortComponentProvided;
import fr.univ.nantes.enfola.m2.interfaces.ports.component.PortComponentRequired;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Alexis Giraudet
 * @author Pierre Gaultier
 */
public class Database extends Component {
    private final static Logger LOGGER = Logger.getLogger(Database.class.getName());
    private final PortComponentProvided<Reply> portSqlRequestProvided;
    private final PortComponentRequired<Query> portSqlRequestRequired;
    private final PortComponentProvided<Reply> portSecurityQueryProvided;
    private final PortComponentRequired<Query> portSecurityQueryRequired;
    private Map<String, String> data;
    private Collection<String> authorizedUsers;

    /**
     *
     */
    public Database() {
        super();

        portSqlRequestProvided = new PortComponentProvided<Reply>(this);
        portSqlRequestRequired = new PortComponentRequired<Query>(this);
        portSecurityQueryProvided = new PortComponentProvided<Reply>(this);
        portSecurityQueryRequired = new PortComponentRequired<Query>(this);

        data = new HashMap<String, String>();
        data.put("A", "1");
        data.put("B", "2");
        data.put("C", "3");

        authorizedUsers = new HashSet<String>();
        authorizedUsers.add("pierre");
        authorizedUsers.add("alexis");
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

        if (portComponentRequired == portSecurityQueryRequired) {
            if (data.containsKey(query.getKey())) {
                reply.setStatus(0);
                reply.setMessage("database query SUCCESS");
                reply.setValue(data.get(query.getKey()));
            } else {
                reply.setStatus(498);
                reply.setMessage("database query FAILURE: invalid key");
            }

            write(portSqlRequestProvided, reply);
        } else if (portComponentRequired == portSqlRequestRequired) {
            if (authorizedUsers.contains(query.getUsername())) {
                reply.setMessage("database authorization SUCCESS");
                reply.setStatus(0);
            } else {
                reply.setMessage("database authorization FAILURE: user not authorized");
                reply.setStatus(457);
            }
            write(portSecurityQueryProvided, reply);
        }
    }

    /**
     * @return
     */
    public PortComponentProvided<Reply> getPortSqlRequestProvided() {
        return portSqlRequestProvided;
    }

    /**
     * @return
     */
    public PortComponentRequired<Query> getPortSqlRequestRequired() {
        return portSqlRequestRequired;
    }

    /**
     * @return
     */
    public PortComponentProvided<Reply> getPortSecurityQueryProvided() {
        return portSecurityQueryProvided;
    }

    /**
     * @return
     */
    public PortComponentRequired<Query> getPortSecurityQueryRequired() {
        return portSecurityQueryRequired;
    }
}

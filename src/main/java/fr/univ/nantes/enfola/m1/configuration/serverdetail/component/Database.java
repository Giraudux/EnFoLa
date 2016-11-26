package fr.univ.nantes.enfola.m1.configuration.serverdetail.component;

import fr.univ.nantes.enfola.m2.Component;
import fr.univ.nantes.enfola.m2.PortComponentProvided;
import fr.univ.nantes.enfola.m2.PortComponentRequired;

import java.util.logging.Logger;

/**
 * @author Alexis Giraudet
 */
public class Database extends Component {
    private final static Logger LOGGER = Logger.getLogger(Database.class.getName());
    private final PortComponentProvided<String> portSqlRequestProvided;
    private final PortComponentRequired<String> portSqlRequestRequired;
    private final PortComponentProvided<String> portSecurityQueryProvided;
    private final PortComponentRequired<String> portSecurityQueryRequired;

    public Database() {
        super();

        portSqlRequestProvided = new PortComponentProvided<String>(this);
        portSqlRequestRequired = new PortComponentRequired<String>(this);
        portSecurityQueryProvided = new PortComponentProvided<String>(this);
        portSecurityQueryRequired = new PortComponentRequired<String>(this);
    }

    protected <T> void read(PortComponentRequired<T> portComponentRequired, T t) {
        LOGGER.info(t.toString());

        if(portComponentRequired == portSecurityQueryRequired) {
            write(portSqlRequestProvided, (String) t);
        } else if(portComponentRequired == portSqlRequestRequired) {
            write(portSecurityQueryProvided, (String) t);
        }
    }

    public PortComponentProvided<String> getPortSqlRequestProvided() {
        return portSqlRequestProvided;
    }

    public PortComponentRequired<String> getPortSqlRequestRequired() {
        return portSqlRequestRequired;
    }

    public PortComponentProvided<String> getPortSecurityQueryProvided() {
        return portSecurityQueryProvided;
    }

    public PortComponentRequired<String> getPortSecurityQueryRequired() {
        return portSecurityQueryRequired;
    }
}

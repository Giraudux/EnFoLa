package fr.univ.nantes.enfola.m1.configuration.serverdetail.component;

import fr.univ.nantes.enfola.m2.Component;
import fr.univ.nantes.enfola.m2.PortComponentProvided;
import fr.univ.nantes.enfola.m2.PortComponentRequired;

import java.util.logging.Logger;

/**
 * @author Alexis Giraudet
 */
public class SecurityManager extends Component {
    private final static Logger LOGGER = Logger.getLogger(SecurityManager.class.getName());
    private final PortComponentProvided<String> portSecurityQueryProvided;
    private final PortComponentRequired<String> portSecurityQueryRequired;
    private final PortComponentProvided<String> portClearanceRequestProvided;
    private final PortComponentRequired<String> portClearanceRequestRequired;

    public SecurityManager() {
        super();

        portSecurityQueryProvided = new PortComponentProvided<String>(this);
        portSecurityQueryRequired = new PortComponentRequired<String>(this);
        portClearanceRequestProvided = new PortComponentProvided<String>(this);
        portClearanceRequestRequired = new PortComponentRequired<String>(this);
    }

    protected <T> void read(PortComponentRequired<T> portComponentRequired, T t) {
        LOGGER.info(t.toString());

        if(portComponentRequired == portClearanceRequestRequired) {
            write(portSecurityQueryProvided, (String) t);
        } else if(portComponentRequired == portSecurityQueryRequired) {
            write(portClearanceRequestProvided, (String) t);
        }
    }

    public PortComponentProvided<String> getPortSecurityQueryProvided() {
        return portSecurityQueryProvided;
    }

    public PortComponentRequired<String> getPortSecurityQueryRequired() {
        return portSecurityQueryRequired;
    }

    public PortComponentProvided<String> getPortClearanceRequestProvided() {
        return portClearanceRequestProvided;
    }

    public PortComponentRequired<String> getPortClearanceRequestRequired() {
        return portClearanceRequestRequired;
    }
}

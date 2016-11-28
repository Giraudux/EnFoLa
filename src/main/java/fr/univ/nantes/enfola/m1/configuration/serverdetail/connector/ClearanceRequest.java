package fr.univ.nantes.enfola.m1.configuration.serverdetail.connector;

import fr.univ.nantes.enfola.m2.Connector;
import fr.univ.nantes.enfola.m2.Glue;
import fr.univ.nantes.enfola.m2.RoleProvided;
import fr.univ.nantes.enfola.m2.RoleRequired;

import java.util.logging.Logger;

/**
 * @author Alexis Giraudet
 */
public class ClearanceRequest extends Connector {
    private final static Logger LOGGER = Logger.getLogger(ClearanceRequest.class.getName());
    private final RoleProvided<String> roleConnectionManagerProvided;
    private final RoleRequired<String> roleConnectionManagerRequired;
    private final RoleProvided<String> roleSecurityManagerProvided;
    private final RoleRequired<String> roleSecurityManagerRequired;
    private Glue<String, String> connectionManagerToSecurityManager;
    private Glue<String, String> securityManagerToConnectionManager;

    public ClearanceRequest() {
        super();

        roleConnectionManagerProvided = new RoleProvided<String>(this);
        roleConnectionManagerRequired = new RoleRequired<String>(this);
        roleSecurityManagerProvided = new RoleProvided<String>(this);
        roleSecurityManagerRequired = new RoleRequired<String>(this);
        connectionManagerToSecurityManager = new ConnectionManagerToSecurityManager();
        securityManagerToConnectionManager = new ConnectionManagerToSecurityManager();

        connect(roleConnectionManagerRequired, connectionManagerToSecurityManager, roleSecurityManagerProvided);
        connect(roleSecurityManagerRequired, securityManagerToConnectionManager, roleConnectionManagerProvided);
    }

    public RoleProvided<String> getRoleConnectionManagerProvided() {
        return roleConnectionManagerProvided;
    }

    public RoleRequired<String> getRoleConnectionManagerRequired() {
        return roleConnectionManagerRequired;
    }

    public RoleProvided<String> getRoleSecurityManagerProvided() {
        return roleSecurityManagerProvided;
    }

    public RoleRequired<String> getRoleSecurityManagerRequired() {
        return roleSecurityManagerRequired;
    }

    private class ConnectionManagerToSecurityManager extends Glue<String, String> {
        protected ConnectionManagerToSecurityManager() {
            super(ClearanceRequest.this);
        }

        @Override
        protected String transform(String s) {
            LOGGER.info(s);

            return s;
        }
    }

    private class SecurityManagerToConnectionManager extends Glue<String, String> {
        protected SecurityManagerToConnectionManager() {
            super(ClearanceRequest.this);
        }

        @Override
        protected String transform(String s) {
            LOGGER.info(s);

            return s;
        }
    }
}

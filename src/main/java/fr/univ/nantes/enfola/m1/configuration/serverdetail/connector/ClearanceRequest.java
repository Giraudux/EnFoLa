package fr.univ.nantes.enfola.m1.configuration.serverdetail.connector;

import fr.univ.nantes.enfola.m1.bean.Query;
import fr.univ.nantes.enfola.m1.bean.Reply;
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
    private final RoleProvided<Reply> roleConnectionManagerProvided;
    private final RoleRequired<Query> roleConnectionManagerRequired;
    private final RoleProvided<Query> roleSecurityManagerProvided;
    private final RoleRequired<Reply> roleSecurityManagerRequired;
    private Glue<Query, Query> connectionManagerToSecurityManager;
    private Glue<Reply, Reply> securityManagerToConnectionManager;

    public ClearanceRequest() {
        super();

        roleConnectionManagerProvided = new RoleProvided<Reply>(this);
        roleConnectionManagerRequired = new RoleRequired<Query>(this);
        roleSecurityManagerProvided = new RoleProvided<Query>(this);
        roleSecurityManagerRequired = new RoleRequired<Reply>(this);
        connectionManagerToSecurityManager = new ConnectionManagerToSecurityManager();
        securityManagerToConnectionManager = new SecurityManagerToConnectionManager();

        connect(roleConnectionManagerRequired, connectionManagerToSecurityManager, roleSecurityManagerProvided);
        connect(roleSecurityManagerRequired, securityManagerToConnectionManager, roleConnectionManagerProvided);
    }

    public RoleProvided<Reply> getRoleConnectionManagerProvided() {
        return roleConnectionManagerProvided;
    }

    public RoleRequired<Query> getRoleConnectionManagerRequired() {
        return roleConnectionManagerRequired;
    }

    public RoleProvided<Query> getRoleSecurityManagerProvided() {
        return roleSecurityManagerProvided;
    }

    public RoleRequired<Reply> getRoleSecurityManagerRequired() {
        return roleSecurityManagerRequired;
    }

    private class ConnectionManagerToSecurityManager extends Glue<Query, Query> {
        protected ConnectionManagerToSecurityManager() {
            super(ClearanceRequest.this);
        }

        @Override
        protected Query transform(Query s) {
            LOGGER.info(s.toString());

            return s;
        }
    }

    private class SecurityManagerToConnectionManager extends Glue<Reply, Reply> {
        protected SecurityManagerToConnectionManager() {
            super(ClearanceRequest.this);
        }

        @Override
        protected Reply transform(Reply s) {
            LOGGER.info(s.toString());

            return s;
        }
    }
}

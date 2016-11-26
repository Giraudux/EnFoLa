package fr.univ.nantes.enfola.m1.configuration.system.connector;

import fr.univ.nantes.enfola.m2.Connector;
import fr.univ.nantes.enfola.m2.Glue;
import fr.univ.nantes.enfola.m2.RoleProvided;
import fr.univ.nantes.enfola.m2.RoleRequired;

import java.util.logging.Logger;

/**
 * @author Alexis Giraudet
 */
public class Rpc extends Connector {
    private final static Logger LOGGER = Logger.getLogger(Rpc.class.getName());
    private final RoleProvided<String> roleClientProvided;
    private final RoleProvided<String> roleServerProvided;
    private final RoleRequired<String> roleClientRequired;
    private final RoleRequired<String> roleServerRequired;
    private Glue<String, String> clientToServer;
    private Glue<String, String> serverToClient;

    public Rpc() {
        super();

        roleClientProvided = new RoleProvided<String>(this);
        roleServerProvided = new RoleProvided<String>(this);
        roleClientRequired = new RoleRequired<String>(this);
        roleServerRequired = new RoleRequired<String>(this);
        clientToServer = new GlueClientToServer();
        serverToClient = new GlueServerToClient();

        connect(roleClientRequired, clientToServer, roleServerProvided);
        connect(roleServerRequired, serverToClient, roleClientProvided);
    }

    public RoleProvided<String> getRoleClientProvided() {
        return roleClientProvided;
    }

    public RoleProvided<String> getRoleServerProvided() {
        return roleServerProvided;
    }

    public RoleRequired<String> getRoleClientRequired() {
        return roleClientRequired;
    }

    public RoleRequired<String> getRoleServerRequired() {
        return roleServerRequired;
    }

    private class GlueServerToClient extends Glue<String, String> {
        private GlueServerToClient() {
            super(Rpc.this);
        }

        protected String transform(String s) {
            LOGGER.info(s);

            return s;
        }
    }

    private class GlueClientToServer extends Glue<String, String> {
        private GlueClientToServer() {
            super(Rpc.this);
        }

        protected String transform(String s) {
            LOGGER.info(s);

            return s;
        }
    }
}

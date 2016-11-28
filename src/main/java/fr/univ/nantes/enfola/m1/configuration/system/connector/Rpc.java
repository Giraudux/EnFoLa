package fr.univ.nantes.enfola.m1.configuration.system.connector;

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
public class Rpc extends Connector {
    private final static Logger LOGGER = Logger.getLogger(Rpc.class.getName());
    private final RoleProvided<String> roleClientProvided;
    private final RoleProvided<Query> roleServerProvided;
    private final RoleRequired<String[]> roleClientRequired;
    private final RoleRequired<Reply> roleServerRequired;
    private Glue<String[], Query> clientToServer;
    private Glue<Reply, String> serverToClient;

    public Rpc() {
        super();

        roleClientProvided = new RoleProvided<String>(this);
        roleServerProvided = new RoleProvided<Query>(this);
        roleClientRequired = new RoleRequired<String[]>(this);
        roleServerRequired = new RoleRequired<Reply>(this);
        clientToServer = new GlueClientToServer();
        serverToClient = new GlueServerToClient();

        connect(roleClientRequired, clientToServer, roleServerProvided);
        connect(roleServerRequired, serverToClient, roleClientProvided);
    }

    public RoleProvided<String> getRoleClientProvided() {
        return roleClientProvided;
    }

    public RoleProvided<Query> getRoleServerProvided() {
        return roleServerProvided;
    }

    public RoleRequired<String[]> getRoleClientRequired() {
        return roleClientRequired;
    }

    public RoleRequired<Reply> getRoleServerRequired() {
        return roleServerRequired;
    }

    private class GlueServerToClient extends Glue<Reply, String> {
        private GlueServerToClient() {
            super(Rpc.this);
        }

        protected String transform(Reply s) {
            LOGGER.info(s.toString());

            return s.toString();
        }
    }

    private class GlueClientToServer extends Glue<String[], Query> {
        private GlueClientToServer() {
            super(Rpc.this);
        }

        protected Query transform(String[] s) {
            LOGGER.info(s.toString());

            Query query = new Query();

            if(s.length > 0) {
                query.setUsername(s[0]);

                if(s.length > 1) {
                    query.setPassword(s[1]);

                    if(s.length > 2) {
                        query.setKey(s[2]);
                    }
                }
            }

            return query;
        }
    }
}

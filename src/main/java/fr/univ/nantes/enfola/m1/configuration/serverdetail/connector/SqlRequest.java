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
public class SqlRequest extends Connector {
    private final static Logger LOGGER = Logger.getLogger(SqlRequest.class.getName());
    private final RoleProvided<Reply> roleConnectionManagerProvided;
    private final RoleRequired<Query> roleConnectionManagerRequired;
    private final RoleProvided<Query> roleDatabaseProvided;
    private final RoleRequired<Reply> roleDatabaseRequired;
    private Glue<Query, Query> connectionManagerToDatabase;
    private Glue<Reply, Reply> databaseToConnectionManager;

    public SqlRequest() {
        super();

        roleConnectionManagerProvided = new RoleProvided<Reply>(this);
        roleConnectionManagerRequired = new RoleRequired<Query>(this);
        roleDatabaseProvided = new RoleProvided<Query>(this);
        roleDatabaseRequired = new RoleRequired<Reply>(this);
        connectionManagerToDatabase = new ConnectionManagerToDatabase();
        databaseToConnectionManager = new DatabaseToConnectionManager();

        connect(roleConnectionManagerRequired, connectionManagerToDatabase, roleDatabaseProvided);
        connect(roleDatabaseRequired, databaseToConnectionManager, roleConnectionManagerProvided);
    }

    public RoleProvided<Reply> getRoleConnectionManagerProvided() {
        return roleConnectionManagerProvided;
    }

    public RoleRequired<Query> getRoleConnectionManagerRequired() {
        return roleConnectionManagerRequired;
    }

    public RoleProvided<Query> getRoleDatabaseProvided() {
        return roleDatabaseProvided;
    }

    public RoleRequired<Reply> getRoleDatabaseRequired() {
        return roleDatabaseRequired;
    }

    private class ConnectionManagerToDatabase extends Glue<Query, Query> {
        public ConnectionManagerToDatabase() {
            super(SqlRequest.this);
        }

        @Override
        protected Query transform(Query s) {
            LOGGER.info(s.toString());

            return s;
        }
    }

    private class DatabaseToConnectionManager extends Glue<Reply, Reply> {
        public DatabaseToConnectionManager() {
            super(SqlRequest.this);
        }

        @Override
        protected Reply transform(Reply s) {
            LOGGER.info(s.toString());

            return s;
        }
    }
}

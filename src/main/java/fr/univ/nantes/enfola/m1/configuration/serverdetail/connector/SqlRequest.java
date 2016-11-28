package fr.univ.nantes.enfola.m1.configuration.serverdetail.connector;

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
    private final RoleProvided<String> roleConnectionManagerProvided;
    private final RoleRequired<String> roleConnectionManagerRequired;
    private final RoleProvided<String> roleDatabaseProvided;
    private final RoleRequired<String> roleDatabaseRequired;
    private Glue<String, String> connectionManagerToDatabase;
    private Glue<String, String> databaseToConnectionManager;

    public SqlRequest() {
        super();

        roleConnectionManagerProvided = new RoleProvided<String>(this);
        roleConnectionManagerRequired = new RoleRequired<String>(this);
        roleDatabaseProvided = new RoleProvided<String>(this);
        roleDatabaseRequired = new RoleRequired<String>(this);
        connectionManagerToDatabase = new ConnectionManagerToDatabase();
        databaseToConnectionManager = new ConnectionManagerToDatabase();

        connect(roleConnectionManagerRequired, connectionManagerToDatabase, roleDatabaseProvided);
        connect(roleDatabaseRequired, databaseToConnectionManager, roleConnectionManagerProvided);
    }

    public RoleProvided<String> getRoleConnectionManagerProvided() {
        return roleConnectionManagerProvided;
    }

    public RoleRequired<String> getRoleConnectionManagerRequired() {
        return roleConnectionManagerRequired;
    }

    public RoleProvided<String> getRoleDatabaseProvided() {
        return roleDatabaseProvided;
    }

    public RoleRequired<String> getRoleDatabaseRequired() {
        return roleDatabaseRequired;
    }

    private class ConnectionManagerToDatabase extends Glue<String, String> {
        public ConnectionManagerToDatabase() {
            super(SqlRequest.this);
        }

        @Override
        protected String transform(String s) {
            LOGGER.info(s);

            return s;
        }
    }

    private class DatabaseToConnectionManager extends Glue<String, String> {
        public DatabaseToConnectionManager() {
            super(SqlRequest.this);
        }

        @Override
        protected String transform(String s) {
            LOGGER.info(s);

            return s;
        }
    }
}

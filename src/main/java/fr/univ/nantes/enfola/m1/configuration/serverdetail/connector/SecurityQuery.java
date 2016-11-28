package fr.univ.nantes.enfola.m1.configuration.serverdetail.connector;

import fr.univ.nantes.enfola.m2.Connector;
import fr.univ.nantes.enfola.m2.Glue;
import fr.univ.nantes.enfola.m2.RoleProvided;
import fr.univ.nantes.enfola.m2.RoleRequired;

import java.util.logging.Logger;

/**
 * @author Alexis Giraudet
 */
public class SecurityQuery extends Connector {
    private final static Logger LOGGER = Logger.getLogger(SecurityQuery.class.getName());
    private final RoleProvided<String> roleDatabaseProvided;
    private final RoleRequired<String> roleDatabaseRequired;
    private final RoleProvided<String> roleSecurityManagerProvided;
    private final RoleRequired<String> roleSecurityManagerRequired;
    private Glue<String, String> databaseToSecurityManager;
    private Glue<String, String> securityManagerToDatabase;

    public SecurityQuery() {
        super();

        roleDatabaseProvided = new RoleProvided<String>(this);
        roleDatabaseRequired = new RoleRequired<String>(this);
        roleSecurityManagerProvided = new RoleProvided<String>(this);
        roleSecurityManagerRequired = new RoleRequired<String>(this);
        databaseToSecurityManager = new DatabaseToSecurityManager();
        securityManagerToDatabase = new SecurityManagerToDatabase();

        connect(roleDatabaseRequired, databaseToSecurityManager, roleSecurityManagerProvided);
        connect(roleSecurityManagerRequired, securityManagerToDatabase, roleDatabaseProvided);
    }

    public RoleProvided<String> getRoleDatabaseProvided() {
        return roleDatabaseProvided;
    }

    public RoleRequired<String> getRoleDatabaseRequired() {
        return roleDatabaseRequired;
    }

    public RoleProvided<String> getRoleSecurityManagerProvided() {
        return roleSecurityManagerProvided;
    }

    public RoleRequired<String> getRoleSecurityManagerRequired() {
        return roleSecurityManagerRequired;
    }

    private class DatabaseToSecurityManager extends Glue<String, String> {
        protected DatabaseToSecurityManager() {
            super(SecurityQuery.this);
        }

        @Override
        protected String transform(String s) {
            LOGGER.info(s);

            return s;
        }
    }

    private class SecurityManagerToDatabase extends Glue<String, String> {
        protected SecurityManagerToDatabase() {
            super(SecurityQuery.this);
        }

        @Override
        protected String transform(String s) {
            LOGGER.info(s);

            return s;
        }
    }
}

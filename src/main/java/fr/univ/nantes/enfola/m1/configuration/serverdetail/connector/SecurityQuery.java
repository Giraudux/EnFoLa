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
public class SecurityQuery extends Connector {
    private final static Logger LOGGER = Logger.getLogger(SecurityQuery.class.getName());
    private final RoleProvided<Query> roleDatabaseProvided;
    private final RoleRequired<Reply> roleDatabaseRequired;
    private final RoleProvided<Reply> roleSecurityManagerProvided;
    private final RoleRequired<Query> roleSecurityManagerRequired;
    private Glue<Reply, Reply> databaseToSecurityManager;
    private Glue<Query, Query> securityManagerToDatabase;

    public SecurityQuery() {
        super();

        roleDatabaseProvided = new RoleProvided<Query>(this);
        roleDatabaseRequired = new RoleRequired<Reply>(this);
        roleSecurityManagerProvided = new RoleProvided<Reply>(this);
        roleSecurityManagerRequired = new RoleRequired<Query>(this);
        databaseToSecurityManager = new DatabaseToSecurityManager();
        securityManagerToDatabase = new SecurityManagerToDatabase();

        connect(roleDatabaseRequired, databaseToSecurityManager, roleSecurityManagerProvided);
        connect(roleSecurityManagerRequired, securityManagerToDatabase, roleDatabaseProvided);
    }

    public RoleProvided<Query> getRoleDatabaseProvided() {
        return roleDatabaseProvided;
    }

    public RoleRequired<Reply> getRoleDatabaseRequired() {
        return roleDatabaseRequired;
    }

    public RoleProvided<Reply> getRoleSecurityManagerProvided() {
        return roleSecurityManagerProvided;
    }

    public RoleRequired<Query> getRoleSecurityManagerRequired() {
        return roleSecurityManagerRequired;
    }

    private class DatabaseToSecurityManager extends Glue<Reply, Reply> {
        protected DatabaseToSecurityManager() {
            super(SecurityQuery.this);
        }

        @Override
        protected Reply transform(Reply s) {
            LOGGER.info(s.toString());

            return s;
        }
    }

    private class SecurityManagerToDatabase extends Glue<Query, Query> {
        protected SecurityManagerToDatabase() {
            super(SecurityQuery.this);
        }

        @Override
        protected Query transform(Query s) {
            LOGGER.info(s.toString());

            return s;
        }
    }
}

package fr.univ.nantes.enfola.m1.connector.sql;

import fr.univ.nantes.enfola.m2.Connector;
import fr.univ.nantes.enfola.m2.RoleProvided;
import fr.univ.nantes.enfola.m2.RoleRequired;

/**
 * Created by E115911Q on 24/10/16.
 */
public class SQLConnector extends Connector {
    private final RoleRequired<String> roleDatabaseIn;
    private final RoleRequired<String> roleSecurityIn;
    private final RoleProvided<String> roleDatabaseOut;
    private final RoleProvided<String> roleSecurityOut;
    private final SQLGlue glueDatabaseToSecurity;
    private final SQLGlue glueSecurityToDatabase;

    public SQLConnector() {
        super();

        roleDatabaseIn = new RoleRequired<String>(this);
        roleSecurityIn = new RoleRequired<String>(this);
        roleDatabaseOut = new RoleProvided<String>(this);
        roleSecurityOut = new RoleProvided<String>(this);

        glueDatabaseToSecurity = new SQLGlue(this);
        glueSecurityToDatabase = new SQLGlue(this);

        connect(roleDatabaseIn, glueDatabaseToSecurity, roleSecurityOut);
        connect(roleSecurityIn, glueSecurityToDatabase, roleDatabaseOut);
    }

    public RoleRequired<String> getRoleDatabaseIn() {
        return roleDatabaseIn;
    }

    public RoleRequired<String> getRoleSecurityIn() {
        return roleSecurityIn;
    }

    public RoleProvided<String> getRoleDatabaseOut() {
        return roleDatabaseOut;
    }

    public RoleProvided<String> getRoleSecurityOut() {
        return roleSecurityOut;
    }
}

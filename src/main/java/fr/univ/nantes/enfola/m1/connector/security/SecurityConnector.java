package fr.univ.nantes.enfola.m1.connector.security;

import fr.univ.nantes.enfola.m2.Connector;
import fr.univ.nantes.enfola.m2.RoleProvided;
import fr.univ.nantes.enfola.m2.RoleRequired;

/**
 * Created by E115911Q on 24/10/16.
 */
public class SecurityConnector extends Connector {
    private final RoleRequired<String> roleDatabaseIn;
    private final RoleRequired<String> roleConnectionIn;
    private final RoleProvided<String> roleDatabaseOut;
    private final RoleProvided<String> roleConnectionOut;
    private final SecurityGlue glueDatabaseToConnection;
    private final SecurityGlue glueConnectionToDatabase;

    public SecurityConnector() {
        super();

        roleDatabaseIn = new RoleRequired<String>(this);
        roleConnectionIn = new RoleRequired<String>(this);
        roleDatabaseOut = new RoleProvided<String>(this);
        roleConnectionOut = new RoleProvided<String>(this);

        glueDatabaseToConnection = new SecurityGlue(this);
        glueConnectionToDatabase = new SecurityGlue(this);

        connect(roleDatabaseIn, glueDatabaseToConnection, roleConnectionOut);
        connect(roleConnectionIn, glueConnectionToDatabase, roleDatabaseOut);
    }

    public RoleRequired<String> getRoleDatabaseIn() {
        return roleDatabaseIn;
    }

    public RoleRequired<String> getRoleConnectionIn() {
        return roleConnectionIn;
    }

    public RoleProvided<String> getRoleDatabaseOut() {
        return roleDatabaseOut;
    }

    public RoleProvided<String> getRoleConnectionOut() {
        return roleConnectionOut;
    }
}

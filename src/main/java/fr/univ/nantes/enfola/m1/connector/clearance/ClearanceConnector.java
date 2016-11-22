package fr.univ.nantes.enfola.m1.connector.clearance;

import fr.univ.nantes.enfola.m2.Connector;
import fr.univ.nantes.enfola.m2.RoleProvided;
import fr.univ.nantes.enfola.m2.RoleRequired;

/**
 * Created by E115911Q on 24/10/16.
 */
public class ClearanceConnector extends Connector {
    private final RoleProvided<String> roleConnectionOut;
    private final RoleProvided<String> roleSecurityOut;
    private final RoleRequired<String> roleConnectionIn;
    private final RoleRequired<String> roleSecurityIn;
    private final ClearanceGlue glueConnectionToSecurity;
    private final ClearanceGlue glueSecurityToConnection;

    public ClearanceConnector() {
        super();

        roleConnectionOut = new RoleProvided<String>(this);
        roleSecurityOut = new RoleProvided<String>(this);
        roleConnectionIn = new RoleRequired<String>(this);
        roleSecurityIn = new RoleRequired<String>(this);

        glueConnectionToSecurity = new ClearanceGlue(this);
        glueSecurityToConnection = new ClearanceGlue(this);

        connect(roleConnectionIn, glueConnectionToSecurity, roleSecurityOut);
        connect(roleSecurityIn, glueSecurityToConnection, roleConnectionOut);
    }

    public RoleProvided<String> getRoleConnectionOut() {
        return roleConnectionOut;
    }

    public RoleProvided<String> getRoleSecurityOut() {
        return roleSecurityOut;
    }

    public RoleRequired<String> getRoleConnectionIn() {
        return roleConnectionIn;
    }

    public RoleRequired<String> getRoleSecurityIn() {
        return roleSecurityIn;
    }
}

package fr.univ.nantes.enfola.m1.connector.rpc;

import fr.univ.nantes.enfola.m2.Connector;
import fr.univ.nantes.enfola.m2.RoleProvided;
import fr.univ.nantes.enfola.m2.RoleRequired;

/**
 * Created by E115911Q on 24/10/16.
 */
public class RPCConnector extends Connector {
    private final RoleRequired<String> roleClientIn;
    private final RoleRequired<String> roleServerIn;
    private final RoleProvided<String> roleClientOut;
    private final RoleProvided<String> roleServerOut;
    private final RPCGlue glueClientToServer;
    private final RPCGlue glueServerToClient;

    public RPCConnector() {
        super();

        roleClientIn = new RoleRequired<String>(this);
        roleServerIn = new RoleRequired<String>(this);
        roleClientOut = new RoleProvided<String>(this);
        roleServerOut = new RoleProvided<String>(this);

        glueClientToServer = new RPCGlue(this);
        glueServerToClient = new RPCGlue(this);
    }

    public RoleRequired<String> getRoleClientIn() {
        return roleClientIn;
    }

    public RoleRequired<String> getRoleServerIn() {
        return roleServerIn;
    }

    public RoleProvided<String> getRoleClientOut() {
        return roleClientOut;
    }

    public RoleProvided<String> getRoleServerOut() {
        return roleServerOut;
    }
}

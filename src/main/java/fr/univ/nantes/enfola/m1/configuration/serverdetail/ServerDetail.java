package fr.univ.nantes.enfola.m1.configuration.serverdetail;

import fr.univ.nantes.enfola.m1.configuration.serverdetail.component.ConnectionManager;
import fr.univ.nantes.enfola.m1.configuration.serverdetail.component.Database;
import fr.univ.nantes.enfola.m1.configuration.serverdetail.component.SecurityManager;
import fr.univ.nantes.enfola.m1.configuration.serverdetail.connector.ClearanceRequest;
import fr.univ.nantes.enfola.m1.configuration.serverdetail.connector.SecurityQuery;
import fr.univ.nantes.enfola.m1.configuration.serverdetail.connector.SqlRequest;
import fr.univ.nantes.enfola.m2.Configuration;
import fr.univ.nantes.enfola.m2.PortConfigurationProvided;
import fr.univ.nantes.enfola.m2.PortConfigurationRequired;

/**
 * @author Alexis Giraudet
 */
public class ServerDetail extends Configuration {
    private final PortConfigurationProvided<String> portProvided;
    private final PortConfigurationRequired<String> portRequired;
    private final PortConfigurationProvided<String> portConnectionManagerProvided;
    private final PortConfigurationRequired<String> portConnectionManagerRequired;
    private ConnectionManager connectionManager;
    private Database database;
    private SecurityManager securityManager;
    private ClearanceRequest clearanceRequest;
    private SecurityQuery securityQuery;
    private SqlRequest sqlRequest;

    public ServerDetail() {
        super();

        portProvided = new PortConfigurationProvided<String>(this);
        portRequired = new PortConfigurationRequired<String>(this);
        portConnectionManagerProvided = new PortConfigurationProvided<String>(this);
        portConnectionManagerRequired = new PortConfigurationRequired<String>(this);

        connectionManager = new ConnectionManager();
        database = new Database();
        securityManager = new SecurityManager();
        clearanceRequest = new ClearanceRequest();
        securityQuery = new SecurityQuery();
        sqlRequest = new SqlRequest();

        bridge(portProvided, portConnectionManagerRequired);
        bridge(portConnectionManagerProvided, portRequired);

        // ServerDetail <=> ConnectionManager
        bind(connectionManager.getPortServerDetailProvided(), portConnectionManagerProvided);
        bind(portConnectionManagerRequired, connectionManager.getPortServerDetailRequired());

        // ConnectionManager <=> SqlRequest
        attach(connectionManager.getPortSqlRequestProvided(), sqlRequest.getRoleConnectionManagerRequired());
        attach(sqlRequest.getRoleConnectionManagerProvided(), connectionManager.getPortSqlRequestRequired());

        // SqlRequest <=> Database
        attach(sqlRequest.getRoleDatabaseProvided(), database.getPortSqlRequestRequired());
        attach(database.getPortSqlRequestProvided(), sqlRequest.getRoleDatabaseRequired());

        // Database <=> SecurityQuery
        attach(database.getPortSecurityQueryProvided(), securityQuery.getRoleDatabaseRequired());
        attach(securityQuery.getRoleDatabaseProvided(), database.getPortSecurityQueryRequired());

        // SecurityQuery <=> SecurityManager
        attach(securityQuery.getRoleSecurityManagerProvided(), securityManager.getPortSecurityQueryRequired());
        attach(securityManager.getPortSecurityQueryProvided(), securityQuery.getRoleSecurityManagerRequired());

        // SecurityManager <=> ClearanceRequest
        attach(securityManager.getPortClearanceRequestProvided(), clearanceRequest.getRoleSecurityManagerRequired());
        attach(clearanceRequest.getRoleSecurityManagerProvided(), securityManager.getPortClearanceRequestRequired());

        // ClearanceRequest <=> ConnectionManager
        attach(clearanceRequest.getRoleConnectionManagerProvided(), connectionManager.getPortClearanceRequestRequired());
        attach(connectionManager.getPortClearanceRequestProvided(), clearanceRequest.getRoleConnectionManagerRequired());

    }

    public PortConfigurationProvided<String> getPortProvided() {
        return portProvided;
    }

    public PortConfigurationRequired<String> getPortRequired() {
        return portRequired;
    }
}

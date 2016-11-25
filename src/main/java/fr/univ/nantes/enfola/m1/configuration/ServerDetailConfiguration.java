package fr.univ.nantes.enfola.m1.configuration;

import fr.univ.nantes.enfola.m1.component.ConnectionComponent;
import fr.univ.nantes.enfola.m1.component.DatabaseComponent;
import fr.univ.nantes.enfola.m1.connector.clearance.ClearanceConnector;
import fr.univ.nantes.enfola.m2.Configuration;
import fr.univ.nantes.enfola.m2.PortConfigurationProvided;
import fr.univ.nantes.enfola.m2.PortConfigurationRequired;

import java.util.logging.Logger;

/**
 * @author Alexis Giraudet
 */
public class ServerDetailConfiguration extends Configuration {
    private static final Logger LOGGER = Logger.getLogger(ServerDetailConfiguration.class.getName());

    private final PortConfigurationRequired<String> portSocketIn;
    private final PortConfigurationProvided<String> portSocketOut;

    private ConnectionComponent connectionComponent;
    private DatabaseComponent databaseComponent;

    private ClearanceConnector clearanceConnector;

    public ServerDetailConfiguration() {
        super();

        portSocketIn = new PortConfigurationRequired<String>();
        portSocketOut = new PortConfigurationProvided<String>();

        connectionComponent = new ConnectionComponent();
        databaseComponent = new DatabaseComponent();

        clearanceConnector = new ClearanceConnector();

        attach(connectionComponent.getPortClearanceOut(), clearanceConnector.getRoleConnectionIn());
        attach(clearanceConnector.getRoleConnectionOut(), connectionComponent.getPortClearanceIn());

        attach(clearanceConnector.getRoleSecurityOut(), databaseComponent.getPortSecurityIn());
        attach(databaseComponent.getPortSecurityOut(), clearanceConnector.getRoleSecurityIn());

        bind(portSocketIn, connectionComponent.getPortSocketIn());
        bind(databaseComponent.getPortSQLOut(), portSocketOut);
    }
}

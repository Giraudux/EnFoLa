package fr.univ.nantes.enfola.m1.configuration;

import fr.univ.nantes.enfola.m1.component.connection.ConnectionComponent;
import fr.univ.nantes.enfola.m1.component.database.DatabaseComponent;
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

    private final PortSocketRequired portSocketIn;
    private final PortSocketProvided portSocketOut;

    private ConnectionComponent connectionComponent;
    private DatabaseComponent databaseComponent;

    private ClearanceConnector clearanceConnector;

    public ServerDetailConfiguration() {
        super();

        portSocketIn = new PortSocketRequired();
        portSocketOut = new PortSocketProvided();

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

    public static void main(String[] args) {
        ServerDetailConfiguration serverDetailConfiguration = new ServerDetailConfiguration();
        serverDetailConfiguration.getPortSocketIn().write("Bonjour la France !");
    }

    public PortSocketRequired getPortSocketIn() {
        return portSocketIn;
    }

    public PortSocketProvided getPortSocketOut() {
        return portSocketOut;
    }

    public class PortSocketProvided extends PortConfigurationProvided<String> {
        public PortSocketProvided() {
            super();
        }

        protected void read(String s) {
            LOGGER.info(s);
        }
    }

    public class PortSocketRequired extends PortConfigurationRequired<String> {
        public PortSocketRequired() {
            super();
        }
    }
}

package fr.univ.nantes.enfola.m1.configuration.system;

import fr.univ.nantes.enfola.m1.configuration.serverdetail.ServerDetail;
import fr.univ.nantes.enfola.m1.configuration.system.component.Client;
import fr.univ.nantes.enfola.m1.configuration.system.component.Server;
import fr.univ.nantes.enfola.m1.configuration.system.connector.Rpc;
import fr.univ.nantes.enfola.m2.Configuration;
import fr.univ.nantes.enfola.m2.PortConfigurationProvided;
import fr.univ.nantes.enfola.m2.PortConfigurationRequired;

/**
 * @author Alexis Giraudet
 */
public class System extends Configuration {
    private final PortConfigurationProvided<String> portClientProvided;
    private final PortConfigurationRequired<String> portClientRequired;
    private Client client;
    private Rpc rpc;
    private Server server;
    private ServerDetail serverDetail;

    public System() {
        super();

        portClientProvided = new PortConfigurationProvided<String>();
        portClientRequired = new PortConfigurationRequired<String>();

        client = new Client();
        rpc = new Rpc();
        server = new Server();
        serverDetail = new ServerDetail();

        bind(client.getPortSystemProvided(), portClientProvided);
        bind(portClientRequired, client.getPortSystemRequired());

        attach(client.getPortRpcProvided(), rpc.getRoleClientRequired());
        attach(rpc.getRoleClientProvided(), client.getPortRpcRequired());

        attach(server.getPortRpcProvided(), rpc.getRoleServerRequired());
        attach(rpc.getRoleServerProvided(), server.getPortRpcRequired());

        bind(server.getPortServerDetailProvided(), serverDetail.getPortConnectionManagerProvided());
        bind(serverDetail.getPortConnectionManagerRequired(), server.getPortServerDetailRequired());
    }

    public PortConfigurationProvided<String> getPortClientProvided() {
        return portClientProvided;
    }

    public PortConfigurationRequired<String> getPortClientRequired() {
        return portClientRequired;
    }
}

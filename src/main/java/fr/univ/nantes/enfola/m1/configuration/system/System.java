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
    private final PortConfigurationProvided<String> portProvided;
    private final PortConfigurationRequired<String> portRequired;
    private final PortConfigurationProvided<String> portClientProvided;
    private final PortConfigurationRequired<String> portClientRequired;
    private Client client;
    private Rpc rpc;
    private Server server;
    private ServerDetail serverDetail;

    public System() {
        super();

        portProvided = new PortConfigurationProvided<String>(this);
        portRequired = new PortConfigurationRequired<String>(this);
        portClientProvided = new PortConfigurationProvided<String>(this);
        portClientRequired = new PortConfigurationRequired<String>(this);

        client = new Client();
        rpc = new Rpc();
        server = new Server();
        serverDetail = new ServerDetail();

        bridge(portProvided, portClientRequired);
        bridge(portClientProvided, portRequired);

        bind(client.getPortSystemProvided(), portClientProvided);
        bind(portClientRequired, client.getPortSystemRequired());

        // Client <=> RPC
        attach(client.getPortRpcProvided(), rpc.getRoleClientRequired());
        attach(rpc.getRoleClientProvided(), client.getPortRpcRequired());

        // RPC <=> Server
        attach(server.getPortRpcProvided(), rpc.getRoleServerRequired());
        attach(rpc.getRoleServerProvided(), server.getPortRpcRequired());

        // Server <=> ServerDetail
        bind(server.getPortServerDetailProvided(), serverDetail.getPortProvided());
        bind(serverDetail.getPortRequired(), server.getPortServerDetailRequired());
    }

    public PortConfigurationProvided<String> getPortProvided() {
        return portProvided;
    }

    public PortConfigurationRequired<String> getPortRequired() {
        return portRequired;
    }
}

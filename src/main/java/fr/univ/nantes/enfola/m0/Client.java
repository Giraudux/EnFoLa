package fr.univ.nantes.enfola.m0;

import fr.univ.nantes.enfola.m1.configuration.system.System;
import fr.univ.nantes.enfola.m2.Reader;
import fr.univ.nantes.enfola.m2.Writer;

import java.util.logging.Logger;

/**
 * @author Alexis Giraudet
 */
public class Client implements Reader<String>, Writer<String> {
    private final static Logger LOGGER = Logger.getLogger(Client.class.getName());
    private System system;

    public Client() {
        system = new System();

        system.getPortClientProvided().addReader(this);
    }

    @Override
    public void read(String s) {
        LOGGER.info(s);
    }

    @Override
    public void write(String s) {
        system.getPortClientRequired().write(s);
    }

    public static void main(String[] args) {
        Client client = new Client();

        client.write("Bonjour la France!");
    }
}

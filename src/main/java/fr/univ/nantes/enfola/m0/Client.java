package fr.univ.nantes.enfola.m0;

import fr.univ.nantes.enfola.m2.util.Reader;
import fr.univ.nantes.enfola.m2.util.Writer;

/**
 * @author Alexis Giraudet
 * @author Pierre Gaultier
 */
public class Client implements Reader<String>, Writer<String[]> {
    private fr.univ.nantes.enfola.m1.configuration.system.System system;

    public Client() {
        system = new fr.univ.nantes.enfola.m1.configuration.system.System();

        system.getPortRequired().addReader(this);
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();

        args = new String[]{"alexis", "giraudet", "P"};
        client.write(args);
    }

    @Override
    public void read(String s) {
        System.out.println(s);
    }

    @Override
    public void write(String[] args) {
        system.getPortProvided().write(args);
    }
}

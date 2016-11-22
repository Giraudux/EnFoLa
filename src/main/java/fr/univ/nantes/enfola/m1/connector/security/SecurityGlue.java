package fr.univ.nantes.enfola.m1.connector.security;

import fr.univ.nantes.enfola.m2.Connector;
import fr.univ.nantes.enfola.m2.Glue;

/**
 * Created by E115911Q on 24/10/16.
 */
public class SecurityGlue extends Glue<String, String> {
    protected SecurityGlue(Connector connector) {
        super(connector);
    }

    protected String transform(String s) {
        return s;
    }
}

package fr.univ.nantes.enfola.m2;

/**
 * @author Alexis Giraudet
 * @author Pierre Gaultier
 */
public interface Reader<T> {

    /**
     * @param t
     */
    void read(T t);
}

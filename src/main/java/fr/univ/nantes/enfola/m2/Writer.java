package fr.univ.nantes.enfola.m2;

/**
 * @author Alexis Giraudet
 * @author Pierre Gaultier
 */
public interface Writer<T> {

    /**
     * @param t
     */
    void write(T t);
}

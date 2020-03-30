package model.data_structures;


/**
 * Interfaz que representa una pila/stack
 * @author bernal
 * @param <T>
 */
public interface IStack<T> {

	public boolean push(T pItem); 	//Inserta un elemento en la pila

	public T pop();				// Saca el elemento que se encuentra en el tope de la pila

	public int getSize();		// Retorna el tamaño de la pila

	public boolean isEmpty();	// Retorna si la pila está vacía o no

	public T getTop();			// Retorna el elemento que se encuentra en el tope sin sacarlo
}

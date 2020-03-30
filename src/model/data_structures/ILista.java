package model.data_structures;

public interface ILista<T extends Comparable<T>> extends Iterable<T>{

	public boolean agregar(T elem); //agrega un elemento al final de la lista
	
	public T buscar(T elem); // Busca un elemento dado por parametro
	
	public int darTamano(); // Retorna la longitud 
	
	public T eliminar(T elem); //Elimina un nodo
	
	public void retroceder();	// Retrocede al anterior del actual
	
	public void avanzar(); 	// Avanza en la lista al siguiente nodo
}

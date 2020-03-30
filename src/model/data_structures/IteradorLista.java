package model.data_structures;

import java.util.Iterator;

public class IteradorLista<T extends Comparable<T>> implements Iterator<T>
{

	//ATRIBUTOS
	
	private Nodo<T> proximo;
	
	
	//METODOS
	
	/**
	 * Constructor
	 * @param primero
	 */
	public IteradorLista(Nodo<T>primero) {
		proximo = primero;
	}
	
	/**
	 * Retorna si el elemento actual tiene un siguiente
	 */
	public boolean hasNext() {
		return proximo != null;
	}

	/**
	 * Retorna el elemento del siguiente nodo, null en caso de no tenerlo
	 */
	public T next() {
		if(proximo == null)
		{
			return null;
		}
		else
		{
			T elemento = proximo.darElementoActual();
			proximo = proximo.darSiguiente();
			return elemento;
		}
	}

}

package model.data_structures;
import model.data_structures.Nodo;

public class Stack<T extends Comparable<T>> implements IStack<T>{

	//ATRIBUTOS

	private int tamano;

	private Nodo<T> tope;

	//METODOS

	public Stack() 		// constructor de la pila
	{
		tamano = 0;
		tope = null;
	}

	@Override
	public boolean isEmpty() // Retorna si la pila esta vacia
	{
		return tamano == 0;
	}
	
	public boolean push(T pItem) 	// Apila un nuevo elemento
	{	
		boolean agregado = false;
		Nodo<T> cambio = new Nodo<T>();
		
		if(tope == null)
		{
			tope = cambio;
			cambio.cambiarElemento(pItem);
			tamano++;
			agregado = true;
		}
		else
		{
			cambio.cambiarSiguiente(tope);
			tope = cambio;
			cambio.cambiarElemento(pItem);
			tamano++;
			agregado = true;
		}
		return agregado;
	}

	@Override
	public T pop() // Saca de la pila un elemento y lo retorna
	{	
		T eliminado = null;

		if (!isEmpty()) 
		{
			eliminado = tope.darElementoActual();
			tope = tope.darSiguiente();
			tamano--;
		}
		return eliminado;
	}

	@Override
	public int getSize() // Retorna el tamaño de la pila
	{
		return tamano;
	}


	@Override
	public T getTop() // Retorna el elemento que esta en el tope de la pila
	{
		if(tope==null)
		{
			return null;
		}
		else
		{
			return tope.darElementoActual();	
		}
	}
}

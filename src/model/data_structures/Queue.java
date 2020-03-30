package model.data_structures;

public class Queue <T extends Comparable<T>> implements IQueue<T>{

	//ATRIBUTOS

	private Nodo<T> primero;

	private Nodo<T> ultimo;

	private int tamano;

	//METODOS

	public Queue() 	// Constructor de la cola
	{
		primero = null;
		ultimo = null;
		tamano = 0;
	}


	@Override
	public boolean isEmpty() // Retorna si la cola esta vacia
	{
		return tamano == 0;	
	}


	@Override
	public void enqueue(T pItem) 	// Encola un nuevo elemento al final 
	{
		Nodo<T> nuevo = new Nodo<T>();

		if (primero == null) 
		{
			primero = nuevo;
			ultimo = nuevo;
			primero.cambiarElemento(pItem);
		} 
		else 
		{
			ultimo.cambiarSiguiente(nuevo);
			ultimo = nuevo;
			ultimo.cambiarElemento(pItem);
		}
		tamano++;
	}

	@Override
	public T dequeue() 		// Saca el primer elemento en haber entrado a la cola
	{
		if (primero == null)
		{
			return null;	
		}

		T elem = primero.darElementoActual();
		primero = primero.darSiguiente();
		tamano--;

		return elem;
	}

	@Override
	public int getSize() // Retorna el tamaño de la cola
	{
		return tamano;
	}

	@Override
	public T getFirst() // Retorna el primer elemento de la cola
	{
		if(primero==null)
		{
			return null;
		}
		else
		{
			return primero.darElementoActual();
		}
	}
}

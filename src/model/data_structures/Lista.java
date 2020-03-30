package model.data_structures;

import java.util.Iterator;

public class Lista <T extends Comparable<T>> implements ILista<T>{

	
	//ATRIBUTOS 
	
	private Nodo<T> primerNodo;
	
	private int tamano;
	
	private Nodo<T>ultimoNodo;
	
	private Nodo<T>actual;


	//METODOS
	
	public Lista()
	{
		primerNodo = null;
		ultimoNodo = null;
		actual = null;
		tamano = 0;
	}

	public boolean agregar(T dato)
	{
		boolean agregado = false;
		Nodo<T> elementoActual = primerNodo;
		Nodo<T> nuevo = new Nodo<T>();

		if (primerNodo == null)
		{
			primerNodo = nuevo;
			ultimoNodo = nuevo;
			primerNodo.cambiarElemento(dato);
			tamano++;
			agregado = true;
		}
		else 
		{
			ultimoNodo.cambiarSiguiente(nuevo);
			ultimoNodo=nuevo;
			ultimoNodo.cambiarElemento(dato);
			tamano++;
			agregado = true;
		}	
		return agregado;
	}


	public T buscar(T dato) 
	{
		T resp = null;
		
		if(primerNodo != null)
		{
			IteradorLista<T> actual = new IteradorLista<T>(primerNodo);
			Nodo<T> nodoActual = (Nodo<T>) actual.next();
			boolean encontrado = false;
			
			while(actual.hasNext() && encontrado!=true)
			{
				if (nodoActual.darElementoActual().compareTo(dato)==0) 
				{
					resp = nodoActual.darElementoActual();
					encontrado = true;
				}
				else
				{
					actual.next();
				}
			}	
		}
		return resp;
	}
	
	
	public T eliminar(T dato) 
	{
		Nodo<T> nodoActual = primerNodo;
		if (dato.compareTo(nodoActual.darElementoActual())==0)
		{
			primerNodo = primerNodo.darSiguiente();
			tamano--;
		}
		else
		{
			nodoActual =primerNodo.darSiguiente();
			while(nodoActual != null)
			{
				if (dato.compareTo(nodoActual.darSiguiente().darElementoActual())==0)
				{
					nodoActual.cambiarSiguiente(nodoActual.darSiguiente());
					tamano--;
				}
				actual=nodoActual;
				nodoActual = nodoActual.darSiguiente();

			}
		}
		return nodoActual.darElementoActual();
	}
	
	
	public T darElemento(int i) 
	{
		Nodo<T> nodoActual = primerNodo;

		for (int j=0;j<i;j++)
		{
			nodoActual=nodoActual.darSiguiente();
		}
		return nodoActual.darElementoActual();
	}
	
	
	public int darTamano()
	{
		return tamano;
	}
	
	
	public void avanzar()
	{
		actual.darSiguiente();

	}
	
	
	public T darNodoActual()
	{
		return actual.darElementoActual();
	}
	
	
	public void retroceder()
	{
		Nodo<T> act=primerNodo;
		while(act.darSiguiente()!=null)
		{
			if (act.darSiguiente().darElementoActual().compareTo(actual.darElementoActual())==0)
			{
				actual=act;
			}
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new IteradorLista<T>(primerNodo);
	}
}

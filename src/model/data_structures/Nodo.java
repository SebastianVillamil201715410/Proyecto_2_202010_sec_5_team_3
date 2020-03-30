package model.data_structures;

public class Nodo<T extends Comparable<T>>{

	//ATRIBUTOS
	
	private T elem; //elemento contenido
	
	private Nodo<T> siguiente; // Referencia al siguiente Nodo
	
	
	//METODOS
	
	public Nodo()
	{
		elem = null;
		
		siguiente = null;
	}
	
	public T darElementoActual() // Retorna el elemento que contiene el Nodo
	{
		return elem;
	}
	
	public void cambiarElemento(T elemento)
	{
		elem = elemento;
	}
	
	public Nodo<T> darSiguiente() // Retorna el Nodo siguiente
	{
		return siguiente;
	}
	
	public void cambiarSiguiente(Nodo<T> sig)
	{
		this.siguiente = sig;
	}
}

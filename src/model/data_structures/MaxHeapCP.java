package model.data_structures;

import java.util.Comparator;

public class MaxHeapCP<T extends Comparable<T>>
{
	private Object [] elementos;   
    private int nElementos ;       
    private Comparator cmp;        
	    
    public MaxHeapCP (Comparator c, Object [] datos) {
    	nElementos = 0;
    	elementos = new Object[datos.length*2];
    	cmp = c;

    	for (int i = 0; i < datos.length; i++)
    	    agregar(datos[i]);
        }
    
    public int tamanio() 
    { 
    	return 	nElementos;
    }

    public Object obtenerPrimero() 
    {  
    	return elementos[0];
    }
    
    public void agregar(Object valor) { 
    	int posicion = nElementos; // Va agregar el dato al final
            int posPadre = (nElementos -1)/2;
    	Object valorPadre = null;  

    	if (posicion == elementos.length) crecerArreglo();

    	elementos[posicion] = valor;
    	if (posPadre >=0)
    	    valorPadre = elementos[posPadre];
    	while((posicion > 0) && (cmp.compare(valor, valorPadre) < 0)) {
    	    elementos[posicion] = valorPadre;
    	    posicion = posPadre;
    	    posPadre = (posicion -1)/2;
    	    if (posPadre >= 0)
    		valorPadre = elementos[posPadre];
    	}
    	elementos[posicion] = valor;
    	nElementos++;
        }
    
    private void crecerArreglo() {
    	System.out.println("Voy a aumentar el tamano del arreglo");
    	Object [] tmp = new Object[elementos.length+10];
    	for (int i = 0; i < elementos.length; i++)
    	    tmp[i] = elementos[i];
    	elementos = tmp;
        }
    
    private void ajustarHeap(int pos, int tamano) {      //YA
    	Object valor = elementos[pos];
    	while (pos < tamano) {
    	    int posHijo = pos *2 + 1;
    	    if (posHijo < tamano) {
    		if (((posHijo + 1) < tamano) && 
    		    cmp.compare(elementos[posHijo+1], elementos[posHijo]) <0)
    		    posHijo++;
    		if (cmp.compare(valor, elementos[posHijo]) < 0) {
    		    elementos[pos] = valor;
    		    return;
    		} else {
    		    elementos[pos] = elementos[posHijo];
    		    pos = posHijo;
    		}
    	    } else {
    		elementos[pos] = valor;
    		return;
    	    }
    	}
    }
    
    
    public T darMax()
    {
    	return (T) elementos[0];
    }
    
    public int sacarMax()
    {
    	int ultimaPos = tamanio() - 1;

    	if (ultimaPos != 0)    // Elimina el primer elemento
    	    elementos[0] = elementos[ultimaPos];//[elementos]];

    	nElementos--;  // Elimina el ultimo elemento
    	ajustarHeap(0, nElementos);
    	
    	return ultimaPos;
    }
}

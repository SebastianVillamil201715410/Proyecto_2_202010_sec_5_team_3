package view;

import model.logic.Modelo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("Taller 4 - Daniel Bernal \nSebastian Villamil\n");
			System.out.println("1. Cargar datos de los comparendos");
			System.out.println("2. Req1: N comparendos mas al norte con determinado vehiculo (MaxCola");
			System.out.println("3. Req2: N comparendos mas al norte con determinado vehiculo (MaxHeap)");
			System.out.println("4. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(Modelo modelo)
		{
			// TODO implementar
		}
}

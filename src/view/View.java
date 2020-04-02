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
			System.out.println("Proyecto 5 - Sebastian Villamil\n");
			System.out.println(" 1. Obtener los M comparendos con mayor gravedad ");
			System.out.println(" 2. Buscar los comparendos por mes y d�a de la semana. ");
			System.out.println(" 3. Buscar los comparendos que tienen una fecha-hora en un rango y que son de una\n" + 
					"localidad dada.");
			System.out.println("Parte B");
			System.out.println(" 4. Buscar los M comparendos m�s cercanos a la estaci�n de polic�a.");
			System.out.println(" 5. - Buscar los comparendos por medio de detecci�n, clase de veh�culo, tipo de servicio y\n" + 
					"localidad. ");
			System.out.println(" 6.Buscar los comparendos que tienen una latitud en un rango dado y que involucraron\n" + 
					"un tipo de veh�culo particular.");
			System.out.println("Parte C");
			System.out.println(" 7.  Visualizar Datos en una Tabla ASCII");
			System.out.println(" 8. El costo de los tiempos de espera hoy en d�a (cola)");
			System.out.println(" 9. El costo de los tiempos de espera usando el nuevo sistema\n" + 
					"");
			System.out.println("10. Conclusion");
			System.out.println("11. Salir"); 
			System.out.println("Elegir la opcion deseada escribiendo el n�mero");
	    }

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(Modelo modelo)
		{
			// TODO implementar
		}
}

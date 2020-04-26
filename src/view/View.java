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
			System.out.println("Proyecto 2 - Sebastian Villamil - Daniel Bernal\n");
			System.out.println("1. Cargar datos\n");
			System.out.println("Parte A");
			System.out.println(" 2. Req 1: Obtener los M comparendos con mayor gravedad");
			System.out.println(" 3. Req 2: Buscar los comparendos por mes y día de la semana. ");
			System.out.println(" 4. Req 3: Buscar los comparendos que tienen una fecha-hora en un rango y que son de una\n" + 
					"localidad dada.");
			System.out.println("\nParte B");
			System.out.println(" 5. Req 4: Buscar los M comparendos más cercanos a la estación de policía.");
			System.out.println(" 6. - Req 5:Buscar los comparendos por medio de detección, clase de vehículo, tipo de servicio y\n" + 
					"localidad.");
			System.out.println(" 7.Req 6: Buscar los comparendos que tienen una latitud en un rango dado y que involucraron\n" + 
					"un tipo de vehículo particular.");
			System.out.println("\nParte C");
			System.out.println(" 8. Req 7: Visualizar Datos en una Tabla ASCII");
			System.out.println(" 9. Req 8: El costo de los tiempos de espera hoy en día (cola)");
			System.out.println(" 10. Req 9:El costo de los tiempos de espera usando el nuevo sistema" + 
					"");
			System.out.println("11. Conclusion");
			System.out.println("12. Salir"); 
			System.out.println("Elegir la opcion deseada escribiendo el número");
	    }

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(Modelo modelo)
		{
			// TODO implementar
		}
}

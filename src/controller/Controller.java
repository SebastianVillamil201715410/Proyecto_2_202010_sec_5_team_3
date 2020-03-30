package controller;

import java.util.List;
import java.util.Scanner;

import model.data_structures.Lista;
import model.data_structures.Queue;
import model.data_structures.Stack;
import model.logic.Comparendo;
import model.logic.Modelo;
import view.View;

public class Controller {


	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";
		Lista<Comparendo> lista = new Lista<Comparendo>();
		Stack<Comparendo> pila = new Stack<Comparendo>();
		Queue<Comparendo> cola = new Queue<Comparendo>();

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
			case 1:
				view.printMessage("--------- \nIniciar carga de datos: ");
				modelo = new Modelo();

				long start = System.currentTimeMillis();
				modelo.cargarDatos();
				long end = System.currentTimeMillis();
				view.printMessage("Tiempo de carga (seg): " + (end-start)/1000.0);
				view.printMessage("Datos cargados: " + modelo.darTamano()+ "\n");
				view.printMessage("Primer dato cargado: " +modelo.primero() + "\n"); //imprime el primer elemento de la cola
				break;

			case 2:
				view.printMessage("--------- \nCluster mas grande por infraccion: ");
				System.out.println(respuesta);	
				System.out.println("Total elementos lista: "+ cola.getSize());
				break;

			case 3:
				view.printMessage("--------- \nIngrese cantidad a buscar: ");
				int num = Integer.parseInt(lector.next());
				view.printMessage("--------- \nIngrese codigo de infraccion: ");
				String string = lector.next();
				view.printMessage("--------- \nComparendos por infraccion "+string+": ");
				System.out.println(respuesta);
			case 4: 
				view.printMessage("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;	

			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
}

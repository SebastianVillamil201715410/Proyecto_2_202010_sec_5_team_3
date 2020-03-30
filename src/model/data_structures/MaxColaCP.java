package model.data_structures;

import javax.swing.JOptionPane;

public class MaxColaCP <T extends Comparable<T>>  
{
    private int MAX;
    private int[] COLA;
    private int FRENTE;
    private int FINAL;
 
    public MaxColaCP(int MAX)
    {
        this.MAX = MAX;
        this.FRENTE = 0;
        this.FINAL = 0;  
        this.COLA = new int [this.MAX+1];
    }  
     
    public boolean IsColaLlena()
    {
        if(this.FINAL == this.MAX)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
 
    public boolean IsColaVacia()
    {
        if(this.FRENTE == this.FINAL)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
     
    public void InsertarCola(T elemento)
    {
        if(this.IsColaLlena())
        {
            JOptionPane.showMessageDialog(null,"LA COLA ESTÁ LLENA");
        }
        else
        {
            int ITEM;
 
            ITEM = Integer.parseInt(JOptionPane.showInputDialog(null,"INGRESE EL ITEM A AGREGAR"));  
             
            this.FINAL++; 
            this.COLA[FINAL] = ITEM;
                        
        }
    }
 
    public void EliminarCola()
    {
        if(this.FRENTE == this.FINAL)
        {
            JOptionPane.showMessageDialog(null,"LA COLA ESTA VACIA");
        }
        else
        {            
            for(int i=FRENTE;i < FINAL - 1;i++)
            {
                COLA[i] = COLA[i+1];
            }
             
            FINAL--;
        }
    }
 
    public void MostrarCola()
    {
        if(this.IsColaVacia())
        {
            JOptionPane.showMessageDialog(null,"LA COLA ESTÁ VACIA\n NO HAY DATOS QUE MOSTRAR","MOSTRAR DATOS",JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            String MOSTRAR = "";
             
            for(int i=FRENTE+1;i<=FINAL;i++)
            {
                MOSTRAR = MOSTRAR + COLA[i]+"\n";
            }
             
            JOptionPane.showMessageDialog(null,"TOTAL ES : "+this.FINAL+"\n"+"LOS DATOS DE LA COLA SON : \n"+MOSTRAR,"MOSTRAR DATOS",JOptionPane.INFORMATION_MESSAGE);
        }
    }
 
    public void VaciarCola()
    {
        FRENTE = 0;
        FINAL = 0;
    }     
    public int darMax()
    {
    	return MAX;
    }
    public int sacarMax ()
    {
       return MAX = 0; 
    }
}
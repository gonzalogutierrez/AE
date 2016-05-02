/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espia;

import ec.EvolutionState;
import ec.Individual;
import ec.Problem;
import ec.simple.SimpleFitness;
import ec.simple.SimpleProblemForm;
import ec.simple.SimpleStatistics;
import ec.util.Parameter;
import ec.vector.IntegerVectorIndividual;
import java.io.File;

import java.lang.System;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author ggutierrez
 */
public class Espia extends Problem implements SimpleProblemForm{
    
    public static final String P_IN = "in";
    //public static final String P_IN0 = "in.0";

    int [][] mCostos= new int[5][5];
    int [][] mTemp= new int [3][2];
    ArrayList numeros= new ArrayList<Integer>();
    int res;
    Random rnd=new Random();
    public File matriz;
    public File temporadas;

    @Override
    public void setup(EvolutionState state, Parameter base) {
        super.setup(state, base); //To change body of generated methods, choose Tools | Templates.
        matriz = state.parameters.getFile(base.push("matriz"), null);
        temporadas = state.parameters.getFile(base.push("temporadas"), null);
        
        Scanner s = null;

        try {
                // Leemos el contenido del fichero
                System.out.println("... Leemos el contenido del fichero ...");
                s = new Scanner(matriz);
                // Leemos linea a linea el fichero
                int fila=0;
                while (s.hasNextLine()) {
                        String linea = s.nextLine(); 	// Guardamos la linea en un String
                        String [] arrayStr=linea.split(" ");
                        for (int columna=0;columna<arrayStr.length;columna++){
                            mCostos[fila][columna]=Integer.parseInt(arrayStr[columna]);
                        }
                        fila++;
                        System.out.println(linea);      // Imprimimos la linea
                }
                System.out.println("... Leemos el contenido del fichero ...");
                s = new Scanner(temporadas);
                // Leemos linea a linea el fichero
                fila=0;
                 while (s.hasNextLine()) {
                        String linea = s.nextLine(); 	// Guardamos la linea en un String
                        String [] arrayStr=linea.split(",");
                        for (int columna=0;columna<arrayStr.length;columna++){
                            mTemp[fila][columna]=Integer.parseInt(arrayStr[columna]);
                        }
                        fila++;
                        System.out.println(linea);
                        
                }

        } catch (Exception ex) {
                System.out.println("Mensaje: " + ex.getMessage());
        } finally {
                // Cerramos el fichero tanto si la lectura ha sido correcta o no
                try {
                        if (s != null)
                                s.close();
                } catch (Exception ex2) {
                        System.out.println("Mensaje 2: " + ex2.getMessage());
                }
        }
    }
    
    

    @Override
    public void evaluate(EvolutionState state, Individual ind, int subpopulation, int thread) {
    
            if (ind.evaluated) return;
            
            IntegerVectorIndividual ind2 = (IntegerVectorIndividual)ind;
            
            double valor = 0;//Calcular el fitness del individuo
            
            
            /*for(int i=0;i<4;i++){
                numeros.add(i, i+1);
            }*/
            
            ind2.genome[0]=0;
            
            
            /*int k=numeros.size();
            int n=k;
            for(int i=1;i<=k;i++){
                res=rnd.nextInt(n);            
                ind2.genome[i]=(Integer)numeros.get(res);
                numeros.remove(res);
                n--;
            }*/
     
            
            int sol = (ind2.genome.length) * (ind2.genome.length-1)/2;
            
            int solaux = 0;
            //funcion de fitness
            for (int i=1;i<ind2.genome.length;i++){
            	solaux += ind2.genome[i];
                valor+=((mCostos[ind2.genome[i-1]][ind2.genome[i]])*Porcentaje(i))+balanceo(ind2.genome[i-1],ind2.genome[i]);
            }
            
           if (solaux != sol ){
               valor = Integer.MAX_VALUE;
           }
           
           
           
           System.out.println(ind2.genome[0]+"-"+ind2.genome[1]+"-"+ind2.genome[2]+"-"+ind2.genome[3]+"-"+ind2.genome[4]);
           
           ((SimpleFitness) ind2.fitness).setFitness(state, valor*(-1), valor==0);
            ind.evaluated=true;
            
            
            }
            
    
    public double Porcentaje(int j){
        if (j==1)
            return 1;
        else if (j==2)
            return 1.1;
        else
            return 1.3;
    }
    
    public int balanceo(int i1,int i2){
        int balance=0;
        if (i1==i2)
            balance=Integer.MAX_VALUE;
        
        return balance;
    }
    
    /*public static void main(String[] args) {
        // TODO code application logic here
    }*/
    
}


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

/**
 *
 * @author ggutierrez
 */
public class Espia extends Problem implements SimpleProblemForm{
    
    @Override
    public void evaluate(EvolutionState state, Individual ind, int subpopulation, int thread) {
            if (ind.evaluated) return;
            double valor = 0;//Calcular el fitness del individuo
            ((SimpleFitness) ind.fitness).setFitness(state, valor, valor==0);
            ind.evaluated=true;
            }
    
    /*public static void main(String[] args) {
        // TODO code application logic here
    }*/
}

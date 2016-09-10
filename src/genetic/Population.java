package genetic;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Danijel on 9/7/2016.
 * This class represents the population of chromosomes.
 * */
public class Population {
    private Chromosome[] population;
    private int populationSize;
    private double totalFitness;
    private Random random;
    private int numberOfVars, bytesPerVar;
    private double lowerBound, upperBound;
    private Chromosome best, worst;

    public Population(double lowerBound, double upperBound, int populationSize, int numberOfVars, int bytesPerVar, Random random, boolean construct) {
        this.populationSize = populationSize;
        this.random = random;
        this.numberOfVars = numberOfVars;
        this.bytesPerVar = bytesPerVar;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        population = new Chromosome[populationSize];
        if (construct) {
            constructPopulation();
        }
    }

    public void constructPopulation() {
        for (int i = 0; i<populationSize; i++) {
            population[i] = new Chromosome(numberOfVars,bytesPerVar,upperBound,lowerBound,true,random);
        }
    }

    public Chromosome[] chooseBestChromosomes(int amount) {
        sortChromosomes();
        Chromosome[] bestOnes = new Chromosome[amount];
        for (int i = 0; i<amount; i++) {
            bestOnes[i] = population[i];
        }
        return bestOnes;
    }

    public Chromosome[] proportionalSelection(int amount) {
        Chromosome[] bestOnes = new Chromosome[amount];
        double[] fitnesses = new double[populationSize];
        for (int i = 0; i<populationSize; i++) {
            if (i == 0) {
                fitnesses[i] = population[i].getFitness()/totalFitness;
            } else {
                fitnesses[i] = population[i].getFitness()/totalFitness + fitnesses[i-1];
            }
        }
        for (int i = 0; i<amount; i++) {
            double d = random.nextDouble();
            for (int j = 0; j<populationSize; j++) {
                if (d<fitnesses[j]) {
                    if (containsChromosome(population[j], bestOnes)) {
                        i--;
                    } else {
                        bestOnes[i] = population[j];

                    }
                    break;
                }
            }
        }
        return bestOnes;
    }

    private void sortChromosomes() {
        Arrays.sort(population);
    }

    private boolean containsChromosome(Chromosome c, Chromosome[] pop) {
        for (int i = 0; i<pop.length; i++) {
            if (pop[i].equals(c)) {
                return true;
            }
        }
        return false;
    }

    public void updateVariables() {
        for (int i = 0; i<populationSize; i++) {
            population[i].calculateVariables();
        }
    }

    public Chromosome[] getPopulation() {
        return population;
    }

    public double getTotalFitness() {
        return totalFitness;
    }

    public void setTotalFitness(double totalFitness) {
        this.totalFitness = totalFitness;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i<populationSize; i++) {
            s += i+": "+"["+population[i].getFitness()+"]\n";
        }
        return s;
    }

}

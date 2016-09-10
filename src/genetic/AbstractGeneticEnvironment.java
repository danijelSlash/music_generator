package genetic;

import java.util.Random;

/**
 * Created by Danijel on 9/7/2016.
 * Represents the class which user have to extend in order to use the functionality
 * genetic algorithm.
 */
public abstract class AbstractGeneticEnvironment implements ICrossover, IMutation{
    private static final int MIN_VARS = 1;
    private static final int MIN_POP_SIZE = 10;
    private int populationSize;
    private Random random;
    private int numberOfVars, bytesPerVar;
    private double lowerBound, upperBound;
    private Population population;
    private boolean elitism;
    private CrossoverConstants crossover;
    private MutationConstants mutation;
    private SelectionConstants selection;
    public double mutationChance = 0.1;
    public double crossoverChance = 0.95;
    public double percentageOfBest = 0.5;
    public int pointsInCrossover;
    private Chromosome[] bestChromosomes;
    private Chromosome theBest;

    public AbstractGeneticEnvironment(Random random, int populationSize, int numberOfVars, int bytesPerVar, double lowerBound, double upperBound, boolean elitism, SelectionConstants selection, CrossoverConstants crossover, MutationConstants mutation) {
        if (numberOfVars<MIN_VARS) {
            numberOfVars = MIN_VARS;
        }
        if (populationSize<MIN_POP_SIZE) {
            populationSize = MIN_POP_SIZE;
        }
        this.random = random;
        this.numberOfVars = numberOfVars;
        this.bytesPerVar = bytesPerVar;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.populationSize = populationSize;
        this.crossover = crossover;
        this.mutation = mutation;
        this.elitism = elitism;
        this.selection = selection;
        pointsInCrossover = (int)(0.5*(numberOfVars*bytesPerVar));
    }

    public abstract double calculateFitness(double[] vars);

    public void processSingleGeneration() {
        if (population == null) {
            makePopulation(true);
        }
        population.updateVariables();
        gradeChromosomes(population);
        if (theBest == null) {
            theBest = getBestChromosomeFrom(population.getPopulation());
        }
        select(population);
        if (random.nextFloat()<crossoverChance) {
            crossover();
        }
        mutate();
    }

    private void makePopulation(boolean initPop) {
        population = new Population(lowerBound,upperBound,populationSize,numberOfVars,bytesPerVar,random,initPop);
    }

    private void crossover() {
        int childCounter = 0;
        int firstIndex = Math.abs(random.nextInt())%bestChromosomes.length;
        int secondIndex;
        do {
            secondIndex = Math.abs(random.nextInt())%bestChromosomes.length;
        } while (firstIndex == secondIndex);
        Chromosome firstParent = bestChromosomes[firstIndex];
        Chromosome secondParent = bestChromosomes[secondIndex];
        Chromosome[] children;
        while (childCounter<populationSize) {
            if (crossover.equals(CrossoverConstants.K_POINTS)) {
                children = kPointsCrossover(firstParent,secondParent,pointsInCrossover,random);
            } else {
                children = uniformCrossover(firstParent,secondParent,random);
            }
            population.getPopulation()[childCounter++] = children[0];
            if (childCounter >= populationSize) {
                break;
            } else {
                population.getPopulation()[childCounter++] = children[1];
            }
        }
    }

    private void mutate() {
        if (mutation.equals(MutationConstants.NORMAL)) {
            for (Chromosome c : population.getPopulation()) {
                if (!c.equals(theBest)) {
                    regularMutation(c,random,mutationChance);
                }
            }
        }
    }

    private void select(Population pop) {
        Chromosome[] selected;
        if (selection.equals(SelectionConstants.BEST)) {
            selected = pop.chooseBestChromosomes((int)(percentageOfBest*populationSize));
            if (elitism) {
                Chromosome c = getBestChromosomeFrom(selected);
                if (c.compareTo(theBest) == -1) {
                    theBest = c;
                }
            }
        } else {
            selected = pop.proportionalSelection((int)(percentageOfBest*populationSize));
            if (elitism) {
                Chromosome c = getBestChromosomeFrom(selected);
                if (c.compareTo(theBest) == -1) {
                    theBest = c;
                }
            }
        }
        bestChromosomes = selected;
    }

    private void gradeChromosomes(Population pop) {
        Chromosome[] c = pop.getPopulation();
        double totalFitness = 0;
        for (int i = 0; i<populationSize; i++) {
            double fitness = calculateFitness(c[i].getVariables());
            totalFitness += fitness;
            c[i].setFitness(fitness);
        }
        pop.setTotalFitness(totalFitness);
    }

    public Chromosome getBestChromosomeFrom(Chromosome[] c) {
        Chromosome best = c[0];
        for (int i = 0; i<c.length; i++) {
            if (best.getFitness()>c[i].getFitness()) {
                best = c[i];
            }
        }
        return best;
    }

    public Chromosome getTheBest() {
        return theBest;
    }

    public double[] getVariables() {
        return theBest.getVariables();
    }

    private void printChromosomes() {
        for (int i = 0; i<population.getPopulation().length; i++) {
            System.out.println(population.getPopulation()[i]+"->"+population.getPopulation()[i].getFitness());
        }
    }

}

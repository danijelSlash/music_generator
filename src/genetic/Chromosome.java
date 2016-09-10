package genetic;

import java.util.Random;

/**
 * Created by Danijel on 9/7/2016.
 */
public class Chromosome implements Comparable<Chromosome>{
    private byte[] genes;
    private double[] vars;
    private int bytesPerVar;
    private int size;
    private int numberOfVars;
    private double fitness;
    private double upperBound, lowerBound;

    public Chromosome(int numberOfVars, int bytesPerVar, double upperBound, double lowerBound, boolean randomFill, Random random) {
        size = numberOfVars * bytesPerVar;
        this.bytesPerVar = bytesPerVar;
        this.numberOfVars = numberOfVars;
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        vars = new double[numberOfVars];
        initialize();
        if (randomFill) {
            randomFillGenes(random);
        }
    }

    public Chromosome(Chromosome chromosome, boolean copyGenes) {
        upperBound = chromosome.upperBound;
        lowerBound = chromosome.lowerBound;
        size = chromosome.size;
        genes = new byte[size];
        bytesPerVar = chromosome.bytesPerVar;
        numberOfVars = chromosome.numberOfVars;
        vars = new double[numberOfVars];
        if (copyGenes) {
            copyGenes(chromosome.genes, this.genes);
        }
    }

    private void copyGenes(byte[] original, byte[] destination) {
        for (int i = 0; i<size; i++) {
            destination[i] = original[i];
        }
    }

    public void initialize() {
        genes = new byte[size];
    }

    public void randomFillGenes(Random random) {
        for (int i = 0; i<size; i++) {
            if (random.nextInt() % 2 == 0) {
                genes[i] = 0;
            } else {
                genes[i] = 1;
            }
        }
    }

    public byte[] getGenes() {
        return genes;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public void calculateVariables() {
        double diff = upperBound - lowerBound;
        double con = Math.pow(2,bytesPerVar)-1;
        for (int i = 0; i<numberOfVars; i++) {
            int k = 0;
            for (int j = (i+1)*bytesPerVar-1, exp = 0; j>=i*bytesPerVar && j<genes.length; j--) {
                k += Math.pow(2,exp++)*genes[j];
            }
            vars[i] = lowerBound + ((k+0.0)/con)*diff;
        }
    }

    public double getVariableAt(int index) {
        return vars[index];
    }

    public void setGene(byte gene, int index) {
        genes[index] = gene;
    }

    public byte getGeneAt(int index) {
        return genes[index];
    }

    public void setGenes(byte[] genes) {
        this.genes = genes;
    }

    public double[] getVariables() {
        return vars;
    }

    /**
     * It overrides compareTo for sorting purpouses.
     * This method will help to sort chromosomes in such way that lowest fitness is going to be
     * at smaller index in the array.
     * */
    @Override
    public int compareTo(Chromosome o) {
        if(this.fitness < o.fitness) {
            return -1;
        }
        if(this.fitness > o.fitness) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chromosome that = (Chromosome) o;

        return Double.compare(that.fitness, fitness) == 0;

    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(fitness);
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i<genes.length; i++) {
            s += ""+genes[i];
        }
        return s;
    }
}

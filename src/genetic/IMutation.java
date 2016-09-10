package genetic;

import java.util.Random;

/**
 * Created by Danijel on 9/7/2016.
 */
public interface IMutation {

    /**
     * It processes the mutation in the classic way. It takes the chromosome and
     * then it goes trough its genes and changes the gene value.
     * @param c chromosome whose genes are going to be mutated
     * @param r random number generator
     * @param mutationChance the real number from interval (0,1), it has obviusly a small value, typically around 20%
     * @return mutated chromosome
     * */
    default Chromosome regularMutation(Chromosome c, Random r, double mutationChance) {
        byte[] genes = c.getGenes();
        for (int i = 0; i<genes.length; i++) {
            if (r.nextFloat()<mutationChance) {
                switch (genes[i]) {
                    case 0 : genes[i] = 1; break;
                    default: genes[i] = 0; break;
                }
            }
        }
        return c;
    }

}

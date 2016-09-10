package genetic;

/**
 * Created by Danijel on 9/8/2016.
 */
public enum SelectionConstants {
    /**
     * Selects the best chromosomes.
     * */
    BEST,

    /**
     * Selects the parents where probability of each chromosome selection is
     * proportional to their fitness value.
     * */
    ROULETTE
}

package composition;

import composition.scale.AbstractScale;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Danijel on 6/2/2016.
 */
public class Melody {
    private AbstractScale scale;
    private String[] melodyVector;
    private int[] chordProgression;
    private Random random;

    public Melody(AbstractScale scale, Random random) {
        this.scale = scale;
        this.random = random;
    }

    /**
     * It constructs melody using the random number generator.
     * */
    //to treba gen. algoritmom
    public void constructMelody(int numberOfTones) {
        melodyVector = new String[numberOfTones];
        for (int i = 0; i<numberOfTones; i++) {
            melodyVector[i] = scale.getScale().get(Math.abs(random.nextInt()%numberOfTones));
        }
    }

    //to isto
    public void constructRandomProgression(int progressionSize) {
        chordProgression = new int[progressionSize];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i<progressionSize; i++) {
            int number = Math.abs(random.nextInt()%progressionSize);
            if (list.contains(number)) {
                i--;
                continue;
            } else {
                list.add(number);
            }
        }
    }

    public void setProgression(int progression[]) {
        chordProgression = progression;
    }

}

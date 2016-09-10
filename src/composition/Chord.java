package composition;

import composition.scale.AbstractScale;

/**
 * Created by Danijel on 9/9/2016.
 */
public class Chord {
    private String[] notes = new String[6]; //dakle, akordi imaju max. 6 nota, tj. zvukova

    public void makeChord(AbstractScale scale, int[] notes) {
        for (int i = 0; i<notes.length; i++) {
            this.notes[i] = scale.getNote(notes[i]);
        }
    }

}

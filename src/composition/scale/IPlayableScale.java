package composition.scale;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Danijel on 6/1/2016.
 */
public interface IPlayableScale {

    default List<String> constructScale(ToneSize[] scaleShape, NoteOrder noteOrder, String rootNote) {
        List<String> scale = new ArrayList<>();
        int current = 1;
        int delta;
        scale.add(rootNote);
        for (int i = 0; i<scaleShape.length-1; i++) {
            ToneSize toneSize = scaleShape[i];
            if (toneSize.equals(ToneSize.HALF)) {
                delta = 1;
            } else if(toneSize.equals(ToneSize.WHOLE)) {
                delta = 2;
            } else if (toneSize.equals(ToneSize.WHOLE_AND_HALF)) {
                delta = 3;
            } else if (toneSize.equals(ToneSize.TWO_WHOLES)) {
                delta = 4;
            } else {
                delta = 5;
            }
            current += delta;
            if (noteOrder.getToneMap().containsKey(current)) {
                scale.add(noteOrder.getToneMap().get(current));
            }
        }
        return scale;
    }

}

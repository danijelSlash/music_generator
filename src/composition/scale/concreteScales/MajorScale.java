package composition.scale.concreteScales;

import composition.scale.AbstractScale;
import composition.scale.IPlayableScale;
import composition.scale.ToneSize;

/**
 * Created by Danijel on 9/9/2016.
 */
public class MajorScale extends AbstractScale implements IPlayableScale{

    public MajorScale(String rootNote) {
        super();
        ToneSize[] scaleShape = {ToneSize.WHOLE, ToneSize.WHOLE, ToneSize.HALF, ToneSize.WHOLE, ToneSize.WHOLE, ToneSize.WHOLE, ToneSize.HALF};
        noteOrder.shift(rootNote);
        scale = constructScale(scaleShape,noteOrder,rootNote);
    }

}

package composition.scale.concreteScales;

import composition.scale.AbstractScale;
import composition.scale.IPlayableScale;
import composition.scale.ToneSize;

/**
 * Created by Danijel on 6/1/2016.
 */
public class PhrygianDominantScale extends AbstractScale implements IPlayableScale {

    public PhrygianDominantScale(String rootNote){
        super();
        ToneSize[] scaleShape = {ToneSize.HALF, ToneSize.WHOLE, ToneSize.HALF, ToneSize.WHOLE, ToneSize.HALF, ToneSize.WHOLE, ToneSize.WHOLE};
        noteOrder.shift(rootNote);
        scale = constructScale(scaleShape,noteOrder,rootNote);
    }

}

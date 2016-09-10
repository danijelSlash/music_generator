package composition.scale.concreteScales;

import composition.scale.AbstractScale;
import composition.scale.IPlayableScale;
import composition.scale.ToneSize;

/**
 * Created by Danijel on 6/1/2016.
 */
public class MinorPentatonicScale extends AbstractScale implements IPlayableScale {

    public MinorPentatonicScale(String rootNote){
        super();
        ToneSize[] scaleShape = {ToneSize.WHOLE_AND_HALF, ToneSize.WHOLE, ToneSize.WHOLE, ToneSize.WHOLE_AND_HALF, ToneSize.WHOLE};
        noteOrder.shift(rootNote);
        scale = constructScale(scaleShape,noteOrder,rootNote);
    }

}

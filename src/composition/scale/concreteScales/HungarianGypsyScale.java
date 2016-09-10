package composition.scale.concreteScales;

import composition.scale.AbstractScale;
import composition.scale.IPlayableScale;
import composition.scale.ToneSize;

/**
 * Created by Danijel on 6/1/2016.
 */
public class HungarianGypsyScale extends AbstractScale implements IPlayableScale {

    public HungarianGypsyScale(String rootNote){
        super();
        ToneSize[] scaleShape = {ToneSize.WHOLE, ToneSize.HALF, ToneSize.WHOLE_AND_HALF, ToneSize.HALF, ToneSize.HALF, ToneSize.WHOLE_AND_HALF, ToneSize.HALF};
        noteOrder.shift(rootNote);
        scale = constructScale(scaleShape,noteOrder,rootNote);
    }

}

package composition.scale.concreteScales;

import composition.scale.AbstractScale;
import composition.scale.IPlayableScale;
import composition.scale.ToneSize;

/**
 * Created by Danijel on 6/1/2016.
 */
public class MelodicMinorScale extends AbstractScale implements IPlayableScale {

    public MelodicMinorScale(String rootNote){
        super();
        ToneSize[] scaleShape = {ToneSize.WHOLE, ToneSize.HALF, ToneSize.WHOLE, ToneSize.WHOLE, ToneSize.WHOLE, ToneSize.WHOLE, ToneSize.HALF};
        noteOrder.shift(rootNote);
        scale = constructScale(scaleShape,noteOrder,rootNote);
    }

}

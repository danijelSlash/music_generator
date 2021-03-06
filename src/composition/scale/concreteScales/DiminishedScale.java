package composition.scale.concreteScales;

import composition.scale.AbstractScale;
import composition.scale.IPlayableScale;
import composition.scale.ToneSize;

/**
 * Created by Danijel on 6/1/2016.
 */
public class DiminishedScale extends AbstractScale implements IPlayableScale {

    public DiminishedScale(String rootNote){
        super();
        ToneSize[] scaleShape = {ToneSize.HALF, ToneSize.WHOLE, ToneSize.HALF, ToneSize.WHOLE, ToneSize.HALF, ToneSize.WHOLE, ToneSize.HALF, ToneSize.WHOLE};
        noteOrder.shift(rootNote);
        scale = constructScale(scaleShape,noteOrder,rootNote);
    }

}

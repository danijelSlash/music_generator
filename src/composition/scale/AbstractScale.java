package composition.scale;

import java.util.List;

/**
 * Created by Danijel on 6/1/2016.
 */
public abstract class AbstractScale {
    protected NoteOrder noteOrder = new NoteOrder();
    protected List<String> scale;
    protected String rootNote;

    public List<String> getScale() {
        return scale;
    }

    public String getRootNote() {
        return rootNote;
    }

    public String getNote(int index) {
        if (index >= scale.size() || index<0) {
            return null;
        } else {
            return scale.get(index);
        }
    }

    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i<scale.size(); i++) {
            string += " "+scale.get(i);
        }
        return string;
    }

}

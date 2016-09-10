package composition.scale;

import java.util.*;

/**
 * Created by Danijel on 6/1/2016.
 */
public class NoteOrder {
    private HashMap<Integer, String> toneMap = new LinkedHashMap<>();

    public NoteOrder() {
        setToneMap();
    }

    private void setToneMap() {
        toneMap.put(1,"A");
        toneMap.put(2,"A#");
        toneMap.put(3,"B");
        toneMap.put(4,"C");
        toneMap.put(5,"C#");
        toneMap.put(6,"D");
        toneMap.put(7,"D#");
        toneMap.put(8,"E");
        toneMap.put(9,"F");
        toneMap.put(10,"F#");
        toneMap.put(11,"G");
        toneMap.put(12,"G#");
    }

    public void shift(String rootNote) {
        HashMap<Integer,String> tempMap = new LinkedHashMap<>();
        if (!rootNote.equals("A")) {
            tempMap.put(1,rootNote);
            int delta = 0;
            for (int i = 1; i<=12; i++) {
                if (toneMap.get(i).equals(rootNote)) {
                    delta = i-1;
                }
            }
            for (int i = 1; i<=12; i++) {
                String note = toneMap.get(i);
                int newIndex = i - delta;
                tempMap.put(newIndex,note);
            }
            for (int i = 0; i>-11; i--) {
                if (tempMap.containsKey(i)) {
                    String note = tempMap.get(i);
                    int newIndex = 12+i;
                    tempMap.remove(i);
                    tempMap.put(newIndex,note);
                } else {
                    break;
                }
            }
            toneMap = tempMap;
        }
    }

    public HashMap<Integer, String> getToneMap() {
        return toneMap;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 1; i<=12; i++) {
            if (toneMap.containsKey(i)) {
                s += " " + toneMap.get(i);
            }
        }
        return s;
    }

}

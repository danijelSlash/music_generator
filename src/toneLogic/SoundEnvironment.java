package toneLogic;

import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;

/**
 * Created by Danijel on 9/9/2016.
 */
public class SoundEnvironment {

    public static void main(String[] args) {
        Rhythm rhythm = new Rhythm();
        Player player = new Player();
        player.play("I[Guitar] A B C D E F G");
    }

}

package Model;

import android.media.Image;

/**
 * Created by Cedric on 04/03/2016.
 */
public class Loser extends Hole {

    public Loser(int posX, int posY, Image myRepresentation){
        setPosX(posX);
        setPosY(posY);
        setMyRepresentation(myRepresentation);
        setIsWinner(false);
    }
}

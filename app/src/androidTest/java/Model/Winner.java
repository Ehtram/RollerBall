package Model;

import android.media.Image;

/**
 * Created by Cedric on 04/03/2016.
 */
public class Winner extends Hole{

    public Winner(int posX, int posY, Image myRepresentation){
        setPosX(posX);
        setPosY(posY);
        setMyRepresentation(myRepresentation);
        setIsWinner(true);
    }

}

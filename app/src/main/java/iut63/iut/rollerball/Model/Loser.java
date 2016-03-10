package iut63.iut.rollerball.Model;

import android.graphics.Bitmap;
import android.media.Image;

/**
 * Created by Cedric on 04/03/2016.
 */
public class Loser extends Hole {

    public Loser(int posX, int posY, Bitmap myRepresentation){
        setPosX(posX);
        setPosY(posY);
        setMyRepresentation(myRepresentation);
        setIsWinner(false);
    }
}

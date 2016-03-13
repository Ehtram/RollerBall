package iut63.iut.rollerball.Model;

import android.graphics.Bitmap;
import android.media.Image;

/**
 * Created by Cedric on 04/03/2016.
 */
public class Loser extends Hole {

    private float surface;

    public Loser(int posX, int posY, Bitmap myRepresentation){
        setPosX(posX);
        setPosY(posY);
        setCenterX(posX+30);
        setCenterY(posY+30);
        setMyRepresentation(myRepresentation);
        setIsWinner(false);
        surface = (float)Math.PI * 45*45;
    }
}

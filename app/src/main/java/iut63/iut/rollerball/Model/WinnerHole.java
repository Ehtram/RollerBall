package iut63.iut.rollerball.Model;

import android.graphics.Bitmap;
import android.media.Image;

/**
 * Created by Cedric on 04/03/2016.
 */
public class WinnerHole extends Hole{

    public WinnerHole(int posX, int posY, Bitmap myRepresentation){
        setPosX(posX);
        setPosY(posY);
        setCenterX(posX + myRepresentation.getWidth()/2);
        setCenterY(posY + myRepresentation.getWidth() / 2);
        setMyRepresentation(myRepresentation);
        setIsWinner(false);
        setSurface((float)Math.PI * (myRepresentation.getWidth()/2 ) * (myRepresentation.getWidth()/2));

    }

}

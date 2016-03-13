package iut63.iut.rollerball.Model;

import android.graphics.Bitmap;
import android.graphics.RectF;
import android.media.Image;

/**
 * Created by Cedric on 04/03/2016.
 */
public class Wall extends Component {




    public Wall(int posX, int posY,int widht,int height, Bitmap myRepresentation){
        super.setPosX(posX);
        super.setPosY(posY);
        super.setHeight(height);
        super.setWidht(widht);
        super.setMyRepresentation(myRepresentation);
    }
}

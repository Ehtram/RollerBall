package iut63.iut.rollerball.Model;

import android.graphics.Bitmap;
import android.graphics.RectF;
import android.media.Image;

/**
 * Created by Cedric on 04/03/2016.
 */
public class Wall extends Component {

    private int widht;
    private int height;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidht() {
        return widht;
    }

    public void setWidht(int widht) {
        this.widht = widht;
    }

    public Wall(int posX, int posY,int widht,int height, Bitmap myRepresentation){
        super.setPosX(posX);
        super.setPosY(posY);
        this.height = height;
        this.widht = widht;
        super.setMyRepresentation(myRepresentation);
    }
}

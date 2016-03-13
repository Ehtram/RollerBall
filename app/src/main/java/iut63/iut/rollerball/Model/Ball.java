package iut63.iut.rollerball.Model;

import android.graphics.Bitmap;
import android.media.Image;
import android.util.DisplayMetrics;

/**
 * Created by Cedric on 04/03/2016.
 */
public class Ball extends Component{

    private int id, posMaxX,posMaxY;
    private float mSpeedX = 0;
    private float mSpeedY = 0;
    private float surface ;

    public float getmSpeedX() {
        return mSpeedX;
    }

    public void setmSpeedX(float mSpeedX) {
        this.mSpeedX = mSpeedX;
    }

    public float getmSpeedY() {
        return mSpeedY;
    }

    public void setmSpeedY(float mSpeedY) {
        this.mSpeedY = mSpeedY;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public float getSurface() {
        return surface;
    }

    private int centerX, centerY;

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public Ball(int id,int posX, int posY, Bitmap myRepresentation, int PosMaxX, int PosMaxY){
        setPosX(posX);
        setPosY(posY);
        centerX = posX + 17;
        centerY = posY + 17;
        posMaxX = PosMaxX;
        posMaxY = PosMaxY;

        setMyRepresentation(myRepresentation);
        mSpeedX = 0;
        mSpeedY = 0;
        surface =(float) Math.PI * 17*17;
    }
}

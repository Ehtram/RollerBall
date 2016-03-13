package iut63.iut.rollerball.Model;

import android.graphics.Bitmap;
import android.graphics.RectF;
import android.media.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Cedric on 04/03/2016.
 */
public class Wall extends Component {

    private List<Integer> xSupLeft = new ArrayList<>();
    private List<Integer> ySupLeft = new ArrayList<>();

    private List<Integer> xSupRight = new ArrayList<>();
    private List<Integer> ySupRight = new ArrayList<>();

    private List<Integer> xInfLeft = new ArrayList<>();
    private List<Integer> yInfLeft = new ArrayList<>();

    private List<Integer> xInfRight = new ArrayList<>();
    private List<Integer> yInfRight = new ArrayList<>();

    private int cercle1X;
    private int cercle1Y;
    private int cercle2X;
    private int cercle2Y;
    private int cercle3X;
    private int cercle3Y;
    private int cercle4X;
    private int cercle4Y;

    public List<Integer> getxInfRight() {
        return xInfRight;
    }

    public List<Integer> getyInfRight() {
        return yInfRight;
    }

    public List<Integer> getxInfLeft() {
        return xInfLeft;
    }

    public List<Integer> getyInfLeft() {
        return yInfLeft;
    }

    public List<Integer> getySupRight() {
        return ySupRight;
    }

    public List<Integer> getxSupRight() {
        return xSupRight;
    }

    public List<Integer> getxSupLeft() {
        return xSupLeft;
    }

    public List<Integer> getySupLeft() {
        return ySupLeft;
}


    public int getCercle1Y() {
        return cercle1Y;
    }

    public int getCercle1X() {
        return cercle1X;
    }

    public int getCercle2Y() {
        return cercle2Y;
    }

    public int getCercle2X() {
        return cercle2X;
    }

    public int getCercle3X() {
        return cercle3X;
    }

    public int getCercle4Y() {
        return cercle4Y;
    }

    public int getCercle4X() {
        return cercle4X;
    }

    public int getCercle3Y() {
        return cercle3Y;
    }

    public Wall(int posX, int posY,int widht,int height, Bitmap myRepresentation){
        super.setPosX(posX);
        super.setPosY(posY);
        super.setHeight(height);
        super.setWidht(widht);
        super.setMyRepresentation(myRepresentation);
        cercle1X = posX;
        cercle1Y = posY;
        cercle2X = posX+widht;
        cercle2Y = posY;
        cercle3X = posX;
        cercle3Y = posY+height;
        cercle4X = posX + widht;
        cercle4Y = posY + height;







        /*
        remplirGaucheSup(posX, posY);
        remplirDroiteSup(posX, posY, widht);
        remplirGaucheInf(posX, posY, height);
        remplirDroiteInf(posX, posY, height,widht);
        */
    }

    /*
    private void remplirGaucheSup(int posX, int posY){

        for(int i = 90; i<180; i++){
            xSupLeft.add((int) (posX + 17 * Math.cos(i)));
            ySupLeft.add((int) (posY + 17 * Math.sin(i)));
        }
    }

    private void remplirDroiteSup(int posX, int posY, int width){
        for(int i = 0; i<90; i++){
            xSupRight.add((int) (posX+width + 17 * Math.cos(i)));
            ySupRight.add((int) (posY + 17 * Math.sin(i)));
        }
    }

    private void remplirGaucheInf(int posX, int posY, int heigth){
        for(int i = 180; i<270; i++){
            xInfLeft.add((int) (posX + 17 * Math.cos(i)));
            yInfLeft.add((int) (posY+heigth + 17 * Math.sin(i)));
        }
    }

    private  void remplirDroiteInf(int posX, int posY, int height, int widht){
        for(int i = 270; i<360; i++){
            xInfRight.add((int) (posX+widht + 17 * Math.cos(i)));
            yInfRight.add((int) (posY+height + 17 * Math.sin(i)));

        }
    }
    */
}

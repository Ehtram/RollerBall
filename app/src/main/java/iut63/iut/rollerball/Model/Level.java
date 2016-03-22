package iut63.iut.rollerball.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.SensorManager;
import android.os.Parcel;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

import iut63.iut.rollerball.MainActivity;
import iut63.iut.rollerball.R;

/**
 * Created by Cedric on 18/03/2016.
 */
public class Level {

    private String name;

    protected Level(Parcel in) {
        name = in.readString();
        unlock = in.readByte() != 0;
        heightScreen = in.readInt();
        widhtScreen = in.readInt();
        wallRepresentationHor = in.readParcelable(Bitmap.class.getClassLoader());
        wallRepresentationVert = in.readParcelable(Bitmap.class.getClassLoader());
        hole = in.readParcelable(Bitmap.class.getClassLoader());
        winner = in.readParcelable(Bitmap.class.getClassLoader());
        background = in.readParcelable(Bitmap.class.getClassLoader());
        hypothenus = in.readDouble();
        ratio = in.readFloat();
    }


    public String getName() {return name;}

    private boolean unlock = false;

    public boolean getUnlock() {
        return unlock;
    }

    public void setUnlock(boolean unlock) {
        this.unlock = unlock;
    }

    public void setName(String name) {this.name = name;}

    private Ball myBalle;
    public Ball getMyBalle() {return myBalle;}
    public void setMyBalle(Ball myBalle) {this.myBalle = myBalle;}

    private int heightScreen, widhtScreen;
    private SurfaceHolder holder;


    private Canvas c;
    private SurfaceView surfaceV;
    private Hole holeFall;
    private  Ball ball;

    public Hole getholeFall(){return holeFall;}



    Bitmap wallRepresentationHor,wallRepresentationVert,hole,winner;

    private List<Component> listComponent = new ArrayList<>();
    private Bitmap background;
    private double hypothenus;
    private Boolean loose =false;
    private float ratio;

    public float getRatio() {
        return ratio;
    }
    public Ball getBall(){return ball;}
    public void setBall(Ball ball){this.ball=ball;}
    public Level(String name,Context context, SurfaceView surfaceView,int HeightScreen, int WidhtScreen,Ball ball){
        surfaceV = surfaceView;
        holder = surfaceView.getHolder();
        heightScreen = HeightScreen;
        widhtScreen = WidhtScreen;
        setBall(ball);
        wallRepresentationHor = BitmapFactory.decodeResource(context.getResources(), R.mipmap.wallsmallhor);
        wallRepresentationVert = BitmapFactory.decodeResource(context.getResources(), R.mipmap.wallvert);
        hole = BitmapFactory.decodeResource(context.getResources(), R.mipmap.hole);
        winner = BitmapFactory.decodeResource(context.getResources(), R.mipmap.winner);
        hypothenus = Math.sqrt(Math.pow((int) heightScreen, 2) + Math.pow((int) widhtScreen, 2));
        ratio = (float) (hypothenus / 34.0f);
        if (heightScreen > widhtScreen)
            background = BitmapFactory.decodeResource(context.getResources(), R.mipmap.backgroundvert);
        else background = BitmapFactory.decodeResource(context.getResources(), R.mipmap.background);
    }

    public void addNewComponent(Component c){
        this.listComponent.add(c);
    }


    public int run(float x, float y, int heightScreen, int widhtScreen,Boolean loose) {
        int ret = 0;
        if (!holder.getSurface().isValid())
            return 0;

        c = holder.lockCanvas();

        c.drawARGB(255, 255, 255, 255);
        c.drawBitmap(background, 0.0f, 0.0f, null);


        Paint p = new Paint();
        p.setColor(Color.BLACK);
        p.setTextSize(100);
        Bitmap myNewBitmap;
        for(Component component : listComponent){
            if (component instanceof Wall){

                c.drawBitmap(component.getMyRepresentation(),component.getPosX(),component.getPosY(),null);
            }else
                c.drawBitmap(component.getMyRepresentation(),component.getPosX(),component.getPosY(),null);
        }

        if(!loose){
            c.drawBitmap(getBall().getMyRepresentation(), x - (getBall().getMyRepresentation().getWidth() / 2), y - (getBall().getMyRepresentation().getHeight() / 2), null);
        }
        else if(holeFall instanceof LooserHole){

            float size = p.measureText("You Loose");
            c.drawARGB(180, 0, 0, 0);
            c.drawText("You Loose", widhtScreen / 2 - (size / 2), heightScreen / 4, p);
            ret = -1;
        }else{
            float size = p.measureText("You Win");
            c.drawText("You Win", widhtScreen / 2 - (size / 2), heightScreen / 4, p);
            ret = 1;
        }
        holder.unlockCanvasAndPost(c);
        return ret;

    }

    public void checkCollision(float x, float y){

        int x1,x2,y1,y2, centerX,centerY, difX, difY,centerBallX,centerBallY, cornerDistance_sq;


        for(Component w : listComponent){
            centerX = w.getWidht()/2 + w.getPosX();
            centerY = w.getHeight()/2 + w.getPosY();
            if(w instanceof Wall){
                if(x>w.getPosX() && x<w.getPosX()+w.getWidht()){
                    if(y< centerY){
                        checkCollisionYUp(x, y, (Wall) w);
                    }else{
                        checkCollisionYDown(x,y,(Wall)w);
                    }
                }
                if(y>w.getPosY() && y<w.getPosY()+w.getHeight()){
                    if(x<centerX){
                        checkCollisionXUp(x, y, (Wall) w);
                    }
                    else {
                        checkCollisionXDown(x, y, (Wall) w);
                    }
                }

            }
        }


/*
        for(Component w : listComponent){
            if(w instanceof Wall){

                centerY = w.getHeight()/2 + w.getPosY();
                centerX = w.getWidht()/2 + w.getPosX();

                difX = (int) x - ((Wall) w).getCercle1X();
                difY = (int) y - ((Wall) w).getCercle1Y();
                if(Math.sqrt(difX * difX + difY * difY) < ball.getMyRepresentation().getHeight()/2){
                    return 5;
                }
                difX = (int) x - ((Wall) w).getCercle2X();
                difY = (int) y - ((Wall) w).getCercle2Y();
                if(Math.sqrt(difX * difX + difY * difY) < ball.getMyRepresentation().getHeight()/2){
                    return 6;
                }
                difX = (int) x - ((Wall) w).getCercle3X();
                difY = (int) y - ((Wall) w).getCercle3Y();
                if(Math.sqrt(difX * difX + difY * difY) < ball.getMyRepresentation().getHeight()/2){
                    return 8;
                }
                difX = (int) x - ((Wall) w).getCercle4X();
                difY = (int) y - ((Wall) w).getCercle4Y();
                if(Math.sqrt(difX * difX + difY * difY) < ball.getMyRepresentation().getHeight()/2){
                    return 7;
                }

                    if(x>w.getPosX() && x<w.getPosX()+w.getWidht()){
                        if(y< centerY){
                            if(y>w.getPosY()-ball.getMyRepresentation().getHeight()/2){
                                return 2;
                            }
                        }else{
                            if(y<w.getPosY()+w.getHeight()+ball.getMyRepresentation().getHeight()/2){
                                return 4;
                            }
                        }
                    }
                    if(y>w.getPosY() && y<w.getPosY()+w.getHeight()){
                        if(x<centerX){
                            if(x>w.getPosX()-ball.getMyRepresentation().getHeight()/2){
                                return 1;
                            }
                        }
                        else {
                            if(x<w.getPosX()+w.getWidht()+ball.getMyRepresentation().getHeight()/2){
                                return 3;
                            }
                        }
                    }
*/






        //Check Collision angle des obstacles avec boules, Ralenti le deplacement et les frames

                    /*

                    if(y>w.getPosY()-17 && y<w.getPosY() && x<w.getPosX() && x>w.getPosX()-17){
                        for(int i : ((Wall) w).getxSupLeft()){
                            y2 = ((Wall) w).getySupLeft().get(((Wall) w).getxSupLeft().indexOf(i));
                            if(x>i && y>y2){
                                return 5;
                            }
                        }
                    }
                    if(y>w.getPosY()-17 && y<w.getPosY() && x<w.getPosX()+17+w.getWidht() && x>w.getPosX()+w.getWidht()){
                        for(int i : ((Wall) w).getxSupRight()){
                            y2 = ((Wall) w).getySupRight().get(((Wall) w).getxSupRight().indexOf(i));
                            if(y>y2 && x<i){
                                return 8;
                            }
                        }
                    }
                    if(y<w.getPosY()+w.getHeight()+17 && y>w.getPosY()+w.getHeight() && x<w.getPosX() && x>w.getPosX()-17){
                        for(int i : ((Wall) w).getxInfLeft()){
                            y2 =((Wall) w).getyInfLeft().get(((Wall) w).getxInfLeft().indexOf(i));
                            if(y<y2 && x>i){
                                return 6;
                            }
                        }
                    }
                    if(y<w.getPosY()+w.getHeight()+17 && y>w.getPosY()+w.getHeight() && x> w.getPosX()+w.getWidht() && x<w.getPosX()+w.getWidht() +17){
                        for(int i : ((Wall) w).getxInfRight()){
                            y2 = ((Wall) w).getyInfRight().get(((Wall) w).getxInfRight().indexOf(i));
                            if(x<i && y<y2){
                                return 7;
                            }
                        }
                    }
*/


                    /*

                    if(x>w.getPosX() && x<w.getPosX()+w.getWidht()){
                        if(y< centerY){
                            if(y1>w.getPosY()){
                                v.vibrate(1);
                                return 2;
                            }
                        }else{
                            if(y1-(w.getPosY()+w.getHeight())<0){
                                v.vibrate(1);
                                return 4;
                            }
                        }
                    }
                    if(y>w.getPosY() && y<w.getPosY()+w.getHeight()){
                        if(x < centerX){
                            if(x1>w.getPosX()){
                                v.vibrate(1);
                                return 1;
                            }
                        }else{
                            if(x1-(w.getWidht()+w.getPosX())<0){
                                v.vibrate(1);
                                return 3;
                            }
                        }
                    }

                    */

        // 2emm test bugé

                    /*
                    if(centerX>x1){
                        if (centerY>y1){
                            if(y1>w.getPosY() && w.getPosX()-x1<0){
                                return 1;
                            }
                            if(y1<w.getPosY() && w.getPosX()-x1<0){
                                return 2;
                            }
                        }else {
                            if(y1>w.getPosY()+w.getHeight() && w.getPosX()-x1<0 && y1<w.getPosY()+w.getHeight())
                                return 1;
                            if(y1<w.getPosY()+w.getHeight() && w.getPosX()-x1<0){
                                return 4;
                            }
                        }
                    }else {
                        if(centerY>y1){
                            if(y1>w.getPosY() && x1-(w.getPosX()+w.getWidht())<0){
                                return 2;
                            }
                            if(y1<centerY && x1-(w.getPosX()+w.getWidht())<0 && y1>w.getPosY()){
                                return 3;
                            }
                        }else{
                            if(y1 >w.getPosY()+w.getHeight() && x1-(w.getPosX()+w.getWidht())<0 && y1<w.getPosY()+w.getHeight()){
                                return 3;
                            }
                            if(y1 <w.getPosY()+w.getHeight() && x1-(w.getPosX()+w.getWidht())<0){
                                return 4;
                            }
                        }
                    }
                        */


        //1er test foncitonnel

                       /* for(int i=0;i<w.getHeight(); i++){

                           y2 = i+w.getPosY();

                           if(y1==y2 && x1 == x2){
                               return 3;
                           }
                           if(y1==y2 && x1==x2-w.getWidht()){
                                return 1;
                           }
                        }
                        y2 =w.getPosY();
                        for(int i=0 ; i< w.getWidht(); i++){

                            x2 = i+w.getPosX();

                            if(x1==x2 && y1==y2){
                                return 2;
                            }
                            if(x1==x2 && y1==y2+w.getHeight()){
                                return 4;
                            }
                        }*/
    }


    private void checkCollisionXDown(float x, float y, Wall w) {
        int difX, difY;
        if(x<w.getPosX()+w.getWidht()+ball.getMyRepresentation().getHeight()/2){
            ball.addNewCollision(3, (Wall)w);
        }
        difX = (int) x - ((Wall) w).getCercle2X();
        difY = (int) y - ((Wall) w).getCercle2Y();
        if(Math.sqrt(difX * difX + difY * difY) < ball.getMyRepresentation().getHeight()/2){
            ball.addNewCollision(6, (Wall)w);
            ball.setWallCollision((Wall) w);
        }

        difX = (int) x - ((Wall) w).getCercle4X();
        difY = (int) y - ((Wall) w).getCercle4Y();
        if(Math.sqrt(difX * difX + difY * difY) < ball.getMyRepresentation().getHeight()/2){
            ball.addNewCollision(7, (Wall)w);
            ball.setWallCollision((Wall) w);
        }
    }

    private void checkCollisionXUp(float x, float y, Wall w) {
        int difX, difY;
        if(x>w.getPosX()-ball.getMyRepresentation().getHeight()/2){
            ball.addNewCollision(1, (Wall)w);
        }
        difX = (int) x - ((Wall) w).getCercle3X();
        difY = (int) y - ((Wall) w).getCercle3Y();
        if(Math.sqrt(difX * difX + difY * difY) < ball.getMyRepresentation().getHeight()/2){
            ball.addNewCollision(8, (Wall)w);
            ball.setWallCollision((Wall) w);
        }
        difX = (int) x - ((Wall) w).getCercle1X();
        difY = (int) y - ((Wall) w).getCercle1Y();
        if(Math.sqrt(difX * difX + difY * difY) < ball.getMyRepresentation().getHeight()/2){
            ball.addNewCollision(5, (Wall)w);
            ball.setWallCollision((Wall) w);
        }
    }

    public void checkCollisionYUp(float x, float y, Wall w){
        int difX, difY;
        if(y>w.getPosY()-ball.getMyRepresentation().getHeight()/2){
            ball.addNewCollision(2, (Wall)w);
        }
        difX = (int) x - ((Wall) w).getCercle1X();
        difY = (int) y - ((Wall) w).getCercle1Y();
        if(Math.sqrt(difX * difX + difY * difY) < ball.getMyRepresentation().getHeight()/2){
            ball.addNewCollision(5, (Wall)w);
            ball.setWallCollision((Wall)w);
        }
        difX = (int) x - ((Wall) w).getCercle2X();
        difY = (int) y - ((Wall) w).getCercle2Y();
        if(Math.sqrt(difX * difX + difY * difY) < ball.getMyRepresentation().getHeight()/2){
            ball.addNewCollision(6, (Wall)w);
            ball.setWallCollision((Wall) w);
        }
    }

    public void checkCollisionYDown(float x, float y, Wall w){
        int difX, difY;
        if(y<w.getPosY()+w.getHeight()+ball.getMyRepresentation().getHeight()/2){
            ball.addNewCollision(4, (Wall)w);
        }
        difX = (int) x - ((Wall) w).getCercle3X();
        difY = (int) y - ((Wall) w).getCercle3Y();
        if(Math.sqrt(difX * difX + difY * difY) < ball.getMyRepresentation().getHeight()/2){
            ball.addNewCollision(8, (Wall)w);
            ball.setWallCollision((Wall) w);
        }
        difX = (int) x - ((Wall) w).getCercle4X();
        difY = (int) y - ((Wall) w).getCercle4Y();
        if(Math.sqrt(difX * difX + difY * difY) < ball.getMyRepresentation().getHeight()/2){
            ball.addNewCollision(7, (Wall) w);
            ball.setWallCollision((Wall) w);
        }
    }


    public boolean checkHole(float x, float y){
        float difX,difY,T1,T2,T3, distance, sum;
        for(Component c : listComponent){
            if(c instanceof  Hole){
                difX = (int) x - ((Hole)c).getCenterX();
                difY = (int) y - ((Hole)c).getCenterY();
                if(Math.sqrt(difX * difX + difY * difY) < (getBall().getMyRepresentation().getHeight()/2)+(c.getMyRepresentation().getHeight()/6)){
                    holeFall = (Hole)c;
                    return true;
                }
            }
        }
        return false;
    }

    public int checkWin(SensorManager mSensorManager, MainActivity mainActivity){
        checkCollision(ball.getPosX() - ball.getSpeedX(), ball.getPosY() + ball.getSpeedY());

        if(ball.getListOfCollision().size()==1 || ball.getListOfCollision().size()==0){

            if(((int)(ball.getPosY() + ball.getSpeedY()) <   heightScreen -(ball.getHeight()/2) && (int)(ball.getPosY() + ball.getSpeedY()) >0+ (ball.getHeight()/2)) && !(ball.getListOfCollision().containsValue(4)) && !(ball.getListOfCollision().containsValue(2)))
                ball.setPosY((int) (ball.getPosY() + ball.getSpeedY()));
            else
                ball.setPosY((int) (ball.getPosY()));

            if(((int) (ball.getPosX() - ball.getSpeedX()) > 0 + (ball.getHeight()/2) && (int) (ball.getPosX() - ball.getSpeedX()) < widhtScreen - (ball.getHeight()/2)) && !(ball.getListOfCollision().containsValue(1)) && !(ball.getListOfCollision().containsValue(3)))
                ball.setPosX((int) (ball.getPosX() - ball.getSpeedX()));
            else
                ball.setPosX((int) (ball.getPosX()));
    /*
                        if(ball.getListOfCollision().containsValue(5) || ball.getListOfCollision().containsValue(6)||ball.getListOfCollision().containsValue(7)||ball.getListOfCollision().containsValue(8)){
                            Wall w = ball.getWallCollision();
                            rayon = (float) Math.sqrt(Math.pow((w.getCercle1X()-ball.getPosX()),2)+ Math.pow((w.getCercle1Y()-ball.getPosY()),2));
                            angular = (float) Math.acos(((ball.getPosX() - ball.getSpeedX()) - w.getCercle1X()) / rayon);
                            newX = (float) (w.getCercle1X() - ball.getMyRepresentation().getHeight()/2 * Math.cos(angular));
                            newY = (float) (w.getCercle1Y() + ball.getMyRepresentation().getHeight()/2 * Math.sin(angular));
                            ball.setPosX((int) newX);
                            ball.setPosY((int) newY);
                        }*/
        }/* if(ball.getListOfCollision().size()>1){*/
    /*
                    else{

                        /*}
                    }*/



        ball.resetCollision();

    /*
                    for(Map.Entry<Wall,Integer> entry : ball.getListOfCollision().entrySet()){


                    }*/

                    /*
                        if(check == 5){
                            if(((ball.getPosX() - ball.getSpeedX()) -ball.getPosX() < 0 &&  ball.getPosY() + ball.getSpeedY() - ball.getPosY()>0)
                                    ||((ball.getPosX() - ball.getSpeedX()) -ball.getPosX() > 0 &&  ball.getPosY() + ball.getSpeedY() - ball.getPosY()>0)
                                    ||((ball.getPosX() - ball.getSpeedX()) -ball.getPosX() < 0 &&  ball.getPosY() + ball.getSpeedY() - ball.getPosY()<0)
                                    ){
                                    ball.setPosY((int) (ball.getPosY() + ball.getSpeedY()));
                                    ball.setPosX((int) (ball.getPosX() - ball.getSpeedX()));

                            }
                        }else if(check == 6){
                            if(((ball.getPosX() - ball.getSpeedX()) -ball.getPosX() > 0 &&  ball.getPosY() + ball.getSpeedY() - ball.getPosY()>0)
                                    ||((ball.getPosX() - ball.getSpeedX()) -ball.getPosX() < 0 &&  ball.getPosY() + ball.getSpeedY() - ball.getPosY()>0)
                                    ||((ball.getPosX() - ball.getSpeedX()) -ball.getPosX() > 0 &&  ball.getPosY() + ball.getSpeedY() - ball.getPosY()<0)
                                    )
                            {
                                ball.setPosY((int) (ball.getPosY() + ball.getSpeedY()));
                                ball.setPosX((int) (ball.getPosX() - ball.getSpeedX()));
                            }
                        }else if(check == 7){
                            if(((ball.getPosX() - ball.getSpeedX()) -ball.getPosX() > 0 &&  ball.getPosY() + ball.getSpeedY() - ball.getPosY()<0)
                                    ||((ball.getPosX() - ball.getSpeedX()) -ball.getPosX() > 0 &&  ball.getPosY() + ball.getSpeedY() - ball.getPosY()>0)
                                    ||((ball.getPosX() - ball.getSpeedX()) -ball.getPosX() < 0 &&  ball.getPosY() + ball.getSpeedY() - ball.getPosY()<0)
                                    )
                            {
                                ball.setPosY((int) (ball.getPosY() + ball.getSpeedY()));
                                ball.setPosX((int) (ball.getPosX() - ball.getSpeedX()));
                            }

                        }else if(check ==8 ){
                            if(((ball.getPosX() - ball.getSpeedX()) -ball.getPosX() < 0 &&  ball.getPosY() + ball.getSpeedY() - ball.getPosY()<0)
                                    ||((ball.getPosX() - ball.getSpeedX()) -ball.getPosX() < 0 &&  ball.getPosY() + ball.getSpeedY() - ball.getPosY()>0)
                                    ||((ball.getPosX() - ball.getSpeedX()) -ball.getPosX() > 0 &&  ball.getPosY() + ball.getSpeedY() - ball.getPosY()<0)
                                    )
                            {
                                ball.setPosY((int) (ball.getPosY() + ball.getSpeedY()));
                                ball.setPosX((int) (ball.getPosX() - ball.getSpeedX()));
                            }
                        }else{

                            if(((int)(ball.getPosY() + ball.getSpeedY()) <   heightScreen -(ball.getHeight()/2) && (int)(ball.getPosY() + ball.getSpeedY()) >0+ (ball.getHeight()/2)) && check!=4 && check != 2)
                                ball.setPosY((int) (ball.getPosY() + ball.getSpeedY()));
                            else
                                ball.setPosY((int) (ball.getPosY()));

                            if(((int) (ball.getPosX() - ball.getSpeedX()) > 0 + (ball.getHeight()/2) && (int) (ball.getPosX() - ball.getSpeedX()) < widhtScreen - (ball.getHeight()/2)) && check != 1 && check != 3)
                                ball.setPosX((int) (ball.getPosX() - ball.getSpeedX()));
                            else
                                ball.setPosX((int) (ball.getPosX()));
                        }*/

        run(ball.getPosX(), ball.getPosY(), heightScreen, widhtScreen, loose);
        if(checkHole(ball.getPosX(), ball.getPosY()) && !loose){
            mSensorManager.unregisterListener(mainActivity);
            loose = true;
            return run(ball.getPosX(), ball.getPosY(), heightScreen, widhtScreen, loose);
        }
        return 0;
    }


}

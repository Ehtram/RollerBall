package iut63.iut.rollerball.Model;
import android.os.Vibrator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import iut63.iut.rollerball.R;

/**
 * Created by Cedric on 04/03/2016.
 */
public class Game{

    private Player player1;
    private List<Level> levelList;

    public Player getPlayer1() {return player1;}
    public void setPlayer1(Player player1) {this.player1 = player1;}
    public List<Level> getLevelList() {return levelList;}
    public void setLevelList(List<Level> levelList) {this.levelList = levelList;}

    private SurfaceHolder holder;
    private Bitmap ballImage;

    public double getHypothenus() {
        return hypothenus;
    }

    private double hypothenus;

    private Canvas c;
    private SurfaceView surfaceV;

    private  Ball ball;

    public Ball getBall(){return ball;}

    Bitmap wallRepresentationHor,wallRepresentationVert,hole;

    private List<Component> listOfWall = new ArrayList<>();
    private Bitmap background;
    Vibrator v;
    public Game(Context context, SurfaceView surfaceView,int HeightScreen, int WidhtScreen){
        levelList = new ArrayList<>();
        player1 = new Player("toto");
        surfaceV = surfaceView;
        holder = surfaceView.getHolder();
        v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);



        ball = new Ball(1,80,80,null,HeightScreen,WidhtScreen);
        wallRepresentationHor = BitmapFactory.decodeResource(context.getResources(), R.drawable.wallsmallhor);
        wallRepresentationVert = BitmapFactory.decodeResource(context.getResources(),R.drawable.wallvert);
       hole = BitmapFactory.decodeResource(context.getResources(), R.drawable.hole);
        ballImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball);

        hypothenus = Math.sqrt(Math.pow((int) HeightScreen, 2) + Math.pow((int) WidhtScreen, 2));

        listOfWall.add(new Wall(200,80,150,50, wallRepresentationHor));
        listOfWall.add(new Wall(WidhtScreen/2,HeightScreen/2,50,150, wallRepresentationVert));
        listOfWall.add(new Wall(WidhtScreen/2,HeightScreen/2 + 285,50,150, wallRepresentationVert));
        listOfWall.add(new Loser(WidhtScreen/2 +50,HeightScreen/2+ 295,hole));
        listOfWall.add(new Loser(WidhtScreen/2 -5,HeightScreen/2 +150,hole));
        listOfWall.add(new Wall(WidhtScreen/2 +115,HeightScreen/2 + 150, 150,50, wallRepresentationHor));
       // listOfWall.add(new Wall(WidhtScreen/2 - 150,HeightScreen/2,150,50, wallRepresentationHor));
       // listOfWall.add(new Wall(WidhtScreen/2 - 300,HeightScreen/2,150,50, wallRepresentationHor));

        listOfWall.add(new Loser(0,0,hole));
        background = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);

    }

    public void addNewLevel(Level level){
        if(levelList == null){
            levelList = new ArrayList<>();
        }
        levelList.add(level);
    }


    public void run(float x, float y) {

        if (!holder.getSurface().isValid())
            return;

        c = holder.lockCanvas();

        c.drawARGB(255, 255, 255, 255);
        c.drawBitmap(background, 0.0f, 0.0f, null);


        Paint p = new Paint();
        p.setColor(Color.BLUE);
        Bitmap myNewBitmap;
        for(Component component : listOfWall){
            if (component instanceof Wall){
                myNewBitmap = Bitmap.createScaledBitmap(component.getMyRepresentation(),component.getWidht(), component.getHeight(), false);
                c.drawBitmap(myNewBitmap,component.getPosX(),component.getPosY(),null);
            }else
                c.drawBitmap(component.getMyRepresentation(),component.getPosX(),component.getPosY(),null);
        }


        c.drawBitmap(ballImage, x - ( ballImage.getWidth() / 2 ), y - (ballImage.getHeight() / 2),null);


        holder.unlockCanvasAndPost(c);


    }

    private void draw(float x, float y){

    }

    public int checkCollision(float x, float y){

        int x1,x2,y1,y2, centerX,centerY, difX, difY, cornerDistance_sq;
        List<Integer> pointsX = new ArrayList<>();
        List<Integer> pointsY = new ArrayList<>();
        for(int i= 0; i<360; i++){
            pointsX.add((int)(x + 17*Math.cos(i)));
            pointsY.add((int)(y + 17*Math.sin(i)));
        }


        for(Component w : listOfWall){
            if(w instanceof Wall){
                centerY = w.getHeight()/2 + w.getPosY();
                centerX = w.getWidht()/2 + w.getPosX();


                    if(x>w.getPosX() && x<w.getPosX()+w.getWidht()){
                        if(y< centerY){
                            if(y>w.getPosY()-17){
                                return 2;
                            }
                        }else{
                            if(y<w.getPosY()+w.getHeight()+17){
                                return 4;
                            }
                        }
                    }
                    if(y>w.getPosY() && y<w.getPosY()+w.getHeight()){
                        if(x<centerX){
                            if(x>w.getPosX()-17){
                                return 1;
                            }
                        }
                        else {
                            if(x<w.getPosX()+w.getWidht()+17){
                                return 3;
                            }
                        }
                    }
                    difX = (int) x - ((Wall) w).getCercle1X();
                    difY = (int) y - ((Wall) w).getCercle1Y();
                    if(Math.sqrt(difX * difX + difY * difY) < 17){
                        return 5;
                    }
                    difX = (int) x - ((Wall) w).getCercle2X();
                    difY = (int) y - ((Wall) w).getCercle2Y();
                    if(Math.sqrt(difX * difX + difY * difY) < 17){
                        return 6;
                    }
                    difX = (int) x - ((Wall) w).getCercle3X();
                    difY = (int) y - ((Wall) w).getCercle3Y();
                    if(Math.sqrt(difX * difX + difY * difY) < 17){
                        return 8;
                    }
                    difX = (int) x - ((Wall) w).getCercle4X();
                    difY = (int) y - ((Wall) w).getCercle4Y();
                    if(Math.sqrt(difX * difX + difY * difY) < 17){
                        return 7;
                    }






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
            }


    return 0;
    }


}

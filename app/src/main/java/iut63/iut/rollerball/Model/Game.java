package iut63.iut.rollerball.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

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

    private Canvas c;
    private SurfaceView surfaceV;

    private  Ball ball;

    public Ball getBall(){return ball;}

    Bitmap wallRepresentationHor,wallRepresentationVert;

    private List<Wall> listOfWall = new ArrayList<>();
    private Bitmap background;

    public Game(Context context, SurfaceView surfaceView,int HeightScreen, int WidhtScreen){
        levelList = new ArrayList<>();
        player1 = new Player("toto");
        surfaceV = surfaceView;
        holder = surfaceView.getHolder();

        ball = new Ball(1,80,80,null,HeightScreen,WidhtScreen);
        wallRepresentationHor = BitmapFactory.decodeResource(context.getResources(), R.drawable.wallsmallHor);
        wallRepresentationVert = BitmapFactory.decodeResource(context.getResources(),R.drawable.wallvert);
        ballImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball);


        listOfWall.add(new Wall(WidhtScreen/2,HeightScreen/2-100,50,150, wallRepresentationHor));
        listOfWall.add(new Wall(WidhtScreen/2,HeightScreen/2,150,50, wallRepresentationVert));
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
        for(Wall wall : listOfWall){

            myNewBitmap = Bitmap.createScaledBitmap(wall.getMyRepresentation(),wall.getHeight(), wall.getWidht(), false);
            //c.drawRect(wall.getRect(), p);
            c.drawBitmap(myNewBitmap,wall.getPosX(),wall.getPosY(),null);
        }


        c.drawBitmap(ballImage, x - ( ballImage.getWidth() / 2 ), y - (ballImage.getHeight() / 2),null);


        holder.unlockCanvasAndPost(c);


    }

    private void draw(float x, float y){

    }

}

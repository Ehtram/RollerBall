package iut63.iut.rollerball.Model;
import android.hardware.SensorManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
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
    private int heightScreen, widhtScreen;
    DisplayMetrics metrics = new DisplayMetrics();
    private  Ball ball;


    private double hypothenus;
    public double getHypothenus() {
        return hypothenus;
    }

    public List<Level> getLevelList(){return levelList;}
    public Game(Context context, SurfaceView surfaceView,DisplayMetrics metrics, SensorManager mSensorManager){
        levelList = new ArrayList<>();
        player1 = new Player("toto");
        heightScreen = metrics.heightPixels;
        widhtScreen = metrics.widthPixels;
        hypothenus = Math.sqrt(Math.pow((int) heightScreen, 2) + Math.pow((int) widhtScreen, 2));

        ball = new Ball(1, 80, 80, BitmapFactory.decodeResource(context.getResources(), R.mipmap.ball));
        addNewLevel(new Level1("1",context,surfaceView,widhtScreen,heightScreen,ball));
        addNewLevel(new level2("2", context, surfaceView, widhtScreen, heightScreen, ball));
    }
    public Ball getBall(){return ball;}

    public void addNewLevel(Level level) {
        if(levelList == null){
            levelList = new ArrayList<>();
        }
        levelList.add(level);
    }

    private void draw(float x, float y){
    }



}

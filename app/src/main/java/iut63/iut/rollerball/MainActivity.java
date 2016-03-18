package iut63.iut.rollerball;

import android.content.Context;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Map;

import iut63.iut.rollerball.Model.Ball;
import iut63.iut.rollerball.Model.Game;
import iut63.iut.rollerball.Model.Wall;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private ImageView monImage;
    private Boolean test = true;
    private double xa, oldValueX;
    private double ya, oldValueY;
    DisplayMetrics metrics = new DisplayMetrics();
    private float ratio;
    private int heightScreen, widhtScreen;
    private Boolean loose =false;

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_GAME);
       // monImage = (ImageView)findViewById(R.id.myBall);

        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        heightScreen = metrics.heightPixels;
        widhtScreen = metrics.widthPixels;

        game = new Game(this,(SurfaceView)findViewById(R.id.surfaceView), heightScreen,widhtScreen);
        ratio = (float) (game.getHypothenus() / 34.0f);

    }

    public void onSensorChanged (SensorEvent event){

        Sensor sensor = event.sensor;
        float [] values = event.values;
        Ball ball = game.getBall();
        float rayon, angular, newX,newY;

                float x = event.values[0];
                float y = event.values[1];
                ball.setmSpeedX(ball.getmSpeedX() + x);
                if(ball.getmSpeedX() > 0.1*ratio)
                    ball.setmSpeedX((float) (0.1*ratio));
                if(ball.getmSpeedX() < -0.1*ratio)
                    ball.setmSpeedX((float) (-0.1 * ratio));

                ball.setmSpeedY(ball.getmSpeedY()+ y ) ;
                if(ball.getmSpeedY() > 0.1*ratio)
                    ball.setmSpeedY((float) (0.1*ratio));
                if(ball.getmSpeedY() < -0.1*ratio)
                    ball.setmSpeedY((float) (-0.1 * ratio));



                game.checkCollision(ball.getPosX() - ball.getmSpeedX(), ball.getPosY() + ball.getmSpeedY());

                if(ball.getListOfCollision().size()==1 || ball.getListOfCollision().size()==0){

                    if(((int)(ball.getPosY() + ball.getmSpeedY()) <   heightScreen -(ball.getHeight()/2) && (int)(ball.getPosY() + ball.getmSpeedY()) >0+ (ball.getHeight()/2)) && !(ball.getListOfCollision().containsValue(4)) && !(ball.getListOfCollision().containsValue(2)))
                        ball.setPosY((int) (ball.getPosY() + ball.getmSpeedY()));
                    else
                        ball.setPosY((int) (ball.getPosY()));

                    if(((int) (ball.getPosX() - ball.getmSpeedX()) > 0 + (ball.getHeight()/2) && (int) (ball.getPosX() - ball.getmSpeedX()) < widhtScreen - (ball.getHeight()/2)) && !(ball.getListOfCollision().containsValue(1)) && !(ball.getListOfCollision().containsValue(3)))
                        ball.setPosX((int) (ball.getPosX() - ball.getmSpeedX()));
                    else
                        ball.setPosX((int) (ball.getPosX()));
/*
                    if(ball.getListOfCollision().containsValue(5) || ball.getListOfCollision().containsValue(6)||ball.getListOfCollision().containsValue(7)||ball.getListOfCollision().containsValue(8)){
                        Wall w = ball.getWallCollision();
                        rayon = (float) Math.sqrt(Math.pow((w.getCercle1X()-ball.getPosX()),2)+ Math.pow((w.getCercle1Y()-ball.getPosY()),2));
                        angular = (float) Math.acos(((ball.getPosX() - ball.getmSpeedX()) - w.getCercle1X()) / rayon);
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
                        if(((ball.getPosX() - ball.getmSpeedX()) -ball.getPosX() < 0 &&  ball.getPosY() + ball.getmSpeedY() - ball.getPosY()>0)
                                ||((ball.getPosX() - ball.getmSpeedX()) -ball.getPosX() > 0 &&  ball.getPosY() + ball.getmSpeedY() - ball.getPosY()>0)
                                ||((ball.getPosX() - ball.getmSpeedX()) -ball.getPosX() < 0 &&  ball.getPosY() + ball.getmSpeedY() - ball.getPosY()<0)
                                ){
                                ball.setPosY((int) (ball.getPosY() + ball.getmSpeedY()));
                                ball.setPosX((int) (ball.getPosX() - ball.getmSpeedX()));

                        }
                    }else if(check == 6){
                        if(((ball.getPosX() - ball.getmSpeedX()) -ball.getPosX() > 0 &&  ball.getPosY() + ball.getmSpeedY() - ball.getPosY()>0)
                                ||((ball.getPosX() - ball.getmSpeedX()) -ball.getPosX() < 0 &&  ball.getPosY() + ball.getmSpeedY() - ball.getPosY()>0)
                                ||((ball.getPosX() - ball.getmSpeedX()) -ball.getPosX() > 0 &&  ball.getPosY() + ball.getmSpeedY() - ball.getPosY()<0)
                                )
                        {
                            ball.setPosY((int) (ball.getPosY() + ball.getmSpeedY()));
                            ball.setPosX((int) (ball.getPosX() - ball.getmSpeedX()));
                        }
                    }else if(check == 7){
                        if(((ball.getPosX() - ball.getmSpeedX()) -ball.getPosX() > 0 &&  ball.getPosY() + ball.getmSpeedY() - ball.getPosY()<0)
                                ||((ball.getPosX() - ball.getmSpeedX()) -ball.getPosX() > 0 &&  ball.getPosY() + ball.getmSpeedY() - ball.getPosY()>0)
                                ||((ball.getPosX() - ball.getmSpeedX()) -ball.getPosX() < 0 &&  ball.getPosY() + ball.getmSpeedY() - ball.getPosY()<0)
                                )
                        {
                            ball.setPosY((int) (ball.getPosY() + ball.getmSpeedY()));
                            ball.setPosX((int) (ball.getPosX() - ball.getmSpeedX()));
                        }

                    }else if(check ==8 ){
                        if(((ball.getPosX() - ball.getmSpeedX()) -ball.getPosX() < 0 &&  ball.getPosY() + ball.getmSpeedY() - ball.getPosY()<0)
                                ||((ball.getPosX() - ball.getmSpeedX()) -ball.getPosX() < 0 &&  ball.getPosY() + ball.getmSpeedY() - ball.getPosY()>0)
                                ||((ball.getPosX() - ball.getmSpeedX()) -ball.getPosX() > 0 &&  ball.getPosY() + ball.getmSpeedY() - ball.getPosY()<0)
                                )
                        {
                            ball.setPosY((int) (ball.getPosY() + ball.getmSpeedY()));
                            ball.setPosX((int) (ball.getPosX() - ball.getmSpeedX()));
                        }
                    }else{

                        if(((int)(ball.getPosY() + ball.getmSpeedY()) <   heightScreen -(ball.getHeight()/2) && (int)(ball.getPosY() + ball.getmSpeedY()) >0+ (ball.getHeight()/2)) && check!=4 && check != 2)
                            ball.setPosY((int) (ball.getPosY() + ball.getmSpeedY()));
                        else
                            ball.setPosY((int) (ball.getPosY()));

                        if(((int) (ball.getPosX() - ball.getmSpeedX()) > 0 + (ball.getHeight()/2) && (int) (ball.getPosX() - ball.getmSpeedX()) < widhtScreen - (ball.getHeight()/2)) && check != 1 && check != 3)
                            ball.setPosX((int) (ball.getPosX() - ball.getmSpeedX()));
                        else
                            ball.setPosX((int) (ball.getPosX()));
                    }*/

            game.run(ball.getPosX(),ball.getPosY(), heightScreen,widhtScreen, loose);
            if(game.checkHole(ball.getPosX(),ball.getPosY()) && !loose){
                mSensorManager.unregisterListener(this);loose = true;
                game.run(ball.getPosX(), ball.getPosY(), heightScreen, widhtScreen, loose);
            }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


}


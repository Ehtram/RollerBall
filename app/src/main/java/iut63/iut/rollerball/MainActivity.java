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

import iut63.iut.rollerball.Model.Ball;
import iut63.iut.rollerball.Model.Game;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private ImageView monImage;
    private Boolean test= true;
    private double xa, oldValueX;
    private double ya, oldValueY;
    DisplayMetrics metrics = new DisplayMetrics();

    private int heightScreen, widhtScreen;


    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_UI);
       // monImage = (ImageView)findViewById(R.id.myBall);

        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        heightScreen = metrics.heightPixels;
        widhtScreen = metrics.widthPixels;

        game = new Game(this,(SurfaceView)findViewById(R.id.surfaceView), heightScreen,widhtScreen);


    }

    public void onSensorChanged ( SensorEvent event){

        Sensor sensor = event.sensor;
        float [] values = event.values;
        Ball ball = game.getBall();
        synchronized (this) {
            Log.d("onsensorchanged", "onSensorChanged: " + sensor + ", x: " +
                    values[0] + ", y: " + values[1] + ", z: " + values[2]);
            if (sensor.getType() == Sensor.TYPE_GYROSCOPE ) {

                float x = event.values[1];
                float y = event.values[0];
                ball.setmSpeedX(ball.getmSpeedX() + x);
                if(ball.getmSpeedX() > 20)
                    ball.setmSpeedX(20);
                if(ball.getmSpeedX() < -20)
                    ball.setmSpeedX(-20);

                ball.setmSpeedY(ball.getmSpeedY()+ y ) ;
                if(ball.getmSpeedY() > 20)
                    ball.setmSpeedY(20);
                if(ball.getmSpeedY() < -20)
                    ball.setmSpeedY(-20);

               // if((int)(ball.getPosY() + ball.getmSpeedY()) <   heightScreen&& (int)(ball.getPosY() + ball.getmSpeedY()) >0)
                    ball.setPosY((int) (ball.getPosY() + ball.getmSpeedY()));
              //  else
              //     ball.setmSpeedY(0);
              //  if((int) (ball.getPosX() + ball.getmSpeedX()) > 0 && (int) (ball.getPosX() + ball.getmSpeedX()) < widhtScreen)
                    ball.setPosX((int) (ball.getPosX() + ball.getmSpeedX()));
            //    else
               //     ball.setmSpeedX(0);

                game.run(ball.getPosX(),ball.getPosY());
            }
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


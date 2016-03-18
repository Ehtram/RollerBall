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


        game = new Game(this,(SurfaceView)findViewById(R.id.surfaceView),metrics, mSensorManager);
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

                ball.setmSpeedY(ball.getmSpeedY() + y) ;
                if(ball.getmSpeedY() > 0.1*ratio)
                    ball.setmSpeedY((float) (0.1*ratio));
                if(ball.getmSpeedY() < -0.1*ratio)
                    ball.setmSpeedY((float) (-0.1 * ratio));
        game.getLevelList().get(0).checkWin(mSensorManager, this);
       // mSensorManager.unregisterListener(this);
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


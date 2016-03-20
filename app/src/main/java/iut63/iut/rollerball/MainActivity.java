package iut63.iut.rollerball;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import iut63.iut.rollerball.Model.Ball;
import iut63.iut.rollerball.Model.Game;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private float ratio;
    private int levelChoice;
    private Game game;
    private int fall, degrees;
    final String EXTRA_CHOICE = "choiceLevel";
    private Button retry,back,next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retry = (Button) findViewById(R.id.retry);
        back = (Button) findViewById(R.id.back);
        next = (Button) findViewById(R.id.next);

        addEventButton();

        Intent intent = getIntent();
        if(intent != null)
            levelChoice =  Integer.valueOf(intent.getStringExtra(EXTRA_CHOICE)).intValue();

        Sensor mSensor;
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_GAME);
       // monImage = (ImageView)findViewById(R.id.myBall);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);


        game = new Game(this,(SurfaceView)findViewById(R.id.surfaceView),metrics, mSensorManager);
        ratio = (float) (game.getHypothenus() / 34.0f);
    }


    public void onSensorChanged (SensorEvent event){

        Ball ball = game.getBall();
        float x, y;
        if (degrees == 0) {
            x = event.values[0];
            y = event.values[1];
        } else {
            x = -event.values[1];
            y = event.values[0];
        }
        ball.setmSpeedX(ball.getmSpeedX() + x);
        if (ball.getmSpeedX() > 0.1 * ratio)
            ball.setmSpeedX((float) (0.1 * ratio));
        if (ball.getmSpeedX() < -0.1 * ratio)
            ball.setmSpeedX((float) (-0.1 * ratio));

        ball.setmSpeedY(ball.getmSpeedY() + y);
        if (ball.getmSpeedY() > 0.1 * ratio)
            ball.setmSpeedY((float) (0.1 * ratio));
        if (ball.getmSpeedY() < -0.1 * ratio)
            ball.setmSpeedY((float) (-0.1 * ratio));

        fall = game.getLevelList().get(levelChoice - 1).checkWin(mSensorManager, this);
        affichage(fall);
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

    private void affichage(int fall){
        if(fall==1){
            retry.setVisibility(View.VISIBLE);
            back.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
        }else if(fall == -1){
            retry.setVisibility(View.VISIBLE);
            back.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        int rotationEcran = this.getWindowManager().getDefaultDisplay().getRotation();
        switch (rotationEcran) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }
    }

    private void addEventButton() {
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


}


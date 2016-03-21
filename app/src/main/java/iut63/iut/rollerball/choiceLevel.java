package iut63.iut.rollerball;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;

import iut63.iut.rollerball.Model.Game;

public class choiceLevel extends AppCompatActivity implements SensorEventListener {

    final String EXTRA_CHOICE = "choiceLevel";
    private TableLayout tableLayout;
    private Button b;
    private int heightScreen, widhtScreen;
    private Bitmap disableButton, enableButton;
    private List<Boolean> myListOfUnlock = new ArrayList<>();
    private int ratio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_level);

        loadArray(this);

        tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        disableButton = BitmapFactory.decodeResource(this.getResources(), R.mipmap.rayure);
        enableButton = BitmapFactory.decodeResource(this.getResources(), R.mipmap.tick);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        heightScreen = metrics.heightPixels;
        widhtScreen = metrics.widthPixels;

        int hypothenus = (int) Math.sqrt(Math.pow((int) heightScreen, 2) + Math.pow((int) widhtScreen, 2));
        ratio = (int) (hypothenus / 30.0f);
        loadButton();
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    protected void onResume() {

        super.onResume();


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadArray(this);
        loadButton();
    }

    private void loadButton() {
        int nbButtonPossible = 0;
        for (int i = 0; i < widhtScreen / (ratio * 3); i++) {
            nbButtonPossible++;
        }


        for (int i = 0; i < heightScreen / (ratio * 3); i++) {
            TableRow tr = new TableRow(this);
            for (int j = 0; j < nbButtonPossible; j++) {
                b = new Button(this);

                b.setLayoutParams(new TableRow.LayoutParams((int) (ratio * 2.8), (int) (ratio * 3)));
                b.setText(String.valueOf((j + 1) + (nbButtonPossible * i)));
                if (myListOfUnlock.size() >= ((j + 1) + (nbButtonPossible * i)) || ((j + 1) + (nbButtonPossible * i)) == 1) {
                    if (myListOfUnlock.size() != 0) {
                        if (myListOfUnlock.get((j) + (nbButtonPossible * i))){
                            b.setBackgroundResource(R.mipmap.tick);
                            b.setEnabled(true);
                        }
                        else b.setBackgroundResource(R.mipmap.rayure);
                    } else if (((j + 1) + (nbButtonPossible * i)) == 1) {
                        b.setBackgroundResource(R.mipmap.tick);
                        b.setEnabled(false);
                    }
                } else {b.setBackgroundResource(R.mipmap.rayure);b.setEnabled(false);}

                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent;
                        intent = new Intent(choiceLevel.this, MainActivity.class);
                        intent.putExtra(EXTRA_CHOICE, ((Button) v).getText().toString());
                        startActivity(intent);
                    }
                });
                tr.addView(b);
            }
            tableLayout.addView(tr);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void loadArray(Context mContext) {
        SharedPreferences mSharedPreference1 = PreferenceManager.getDefaultSharedPreferences(mContext);
        if (mSharedPreference1 != null) {
            int size = mSharedPreference1.getInt("Status_size", 0);
            for (int i = 0; i < size; i++) {
                myListOfUnlock.add(mSharedPreference1.getBoolean("Status_" + i, false));
            }
        }
    }
}
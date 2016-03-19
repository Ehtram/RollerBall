package iut63.iut.rollerball;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;

public class choiceLevel extends AppCompatActivity {

    final String EXTRA_CHOICE = "choiceLevel";
    private TableLayout tableLayout;
    private Button b;
    private int heightScreen, widhtScreen;
    private Bitmap disableButton, enableButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_level);
        tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        disableButton = BitmapFactory.decodeResource(this.getResources(), R.drawable.rayure);
        enableButton = BitmapFactory.decodeResource(this.getResources(), R.drawable.tick);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        heightScreen = metrics.heightPixels;
        widhtScreen = metrics.widthPixels;

        int hypothenus = (int) Math.sqrt(Math.pow((int) heightScreen, 2) + Math.pow((int) widhtScreen, 2));
        int ratio = (int) (hypothenus / 30.0f);

        int nbButtonPossible = 0;
        for (int i = 0; i < widhtScreen / (ratio * 3); i++) {
            nbButtonPossible++;
        }


        for (int i = 0; i < heightScreen / (ratio * 3); i++) {
            TableRow tr = new TableRow(this);
            for (int j = 0; j < nbButtonPossible; j++) {
                b = new Button(this);
                b.setLayoutParams(new TableRow.LayoutParams((int)(ratio * 2.8), (int)(ratio * 3)));
                b.setText(String.valueOf((j + 1) + (nbButtonPossible * i)));
                b.setBackgroundResource(R.drawable.rayure);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(choiceLevel.this, MainActivity.class);
                        intent.putExtra(EXTRA_CHOICE, ((Button) v).getText().toString());
                        startActivity(intent);
                    }
                });
                tr.addView(b);
            }
            tableLayout.addView(tr);
        }
    }
}
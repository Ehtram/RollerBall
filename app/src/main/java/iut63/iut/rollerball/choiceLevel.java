package iut63.iut.rollerball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;

public class choiceLevel extends AppCompatActivity {

    final String EXTRA_CHOICE = "choiceLevel";
    private TableLayout tableLayout;
    private List<String> myButtons;
    private Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_level);
        tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        myButtons = new ArrayList();
     /*   b =(Button) findViewById(R.id.button111);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(choiceLevel.this, MainActivity.class);
                startActivity(intent);
            }
        });*/
        for(int i= 0; i< tableLayout.getChildCount(); i++){
            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);
            for(int j=0; j < tableRow.getChildCount(); j++){
                b = (Button) tableRow.getChildAt(j);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(choiceLevel.this, MainActivity.class);
                        intent.putExtra(EXTRA_CHOICE, ((Button)v).getText().toString());
                        startActivity(intent);
                    }
                });
            }
        }
    }
}

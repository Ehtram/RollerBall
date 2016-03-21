package iut63.iut.rollerball;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.BaseGameActivity;

public class Menu extends BaseGameActivity implements View.OnClickListener {
    private Button start;
    private Button leaderboard;
    private static String TAG = Menu.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        start =(Button) findViewById(R.id.gameStart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, choiceLevel.class);
                startActivity(intent);
            }
        });

        setLeaderboardOnClickListener();
        setGoogleSignButton();
    }

    private void setGoogleSignButton() {
        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.sign_out_button).setOnClickListener(this);
    }

    private void setLeaderboardOnClickListener() {
        leaderboard = (Button) findViewById(R.id.buttonLeaderboard);
        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getApiClient().isConnected()) {
                    startActivityForResult(Games.Leaderboards.getLeaderboardIntent(
                                    getApiClient(), getString(R.string.leaderboard_id)),
                            2);
                }
                if(getApiClient().isConnected()){
                    Games.Leaderboards.submitScore(getApiClient(),
                            getString(R.string.leaderboard_id),
                            2);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sign_in_button) {
            beginUserInitiatedSignIn();
        }
        else if (v.getId() == R.id.sign_out_button) {
            signOut();
            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_button).setVisibility(View.GONE);
        }
    }
    @Override
    public void onSignInSucceeded() {
        findViewById(R.id.sign_in_button).setVisibility(View.GONE);
        findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
    }

    @Override
    public void onSignInFailed() {
        findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
        findViewById(R.id.sign_out_button).setVisibility(View.GONE);
    }

}

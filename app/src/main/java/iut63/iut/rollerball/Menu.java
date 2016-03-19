package iut63.iut.rollerball;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.games.Games;

public class Menu extends FragmentActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private Button start;
    private Button leaderboard;
    private GoogleApiClient mGoogleApiClient;
    private boolean mSignInClicked;

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
        setGoogleApiClient();
        setGoogleSignButton();
    }

    private void setGoogleSignButton() {
        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.sign_out_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sign_in_button) {
            // start the asynchronous sign in flow
            mSignInClicked = true;
            mGoogleApiClient.connect();
            Log.d("googleAPI", String.valueOf(mGoogleApiClient.isConnected()));
        }
        else if (v.getId() == R.id.sign_out_button) {
            // sign out.
            mSignInClicked = false;
            Games.signOut(mGoogleApiClient);

            // show sign-in button, hide the sign-out button
            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_button).setVisibility(View.GONE);
        }
    }

    private void setGoogleApiClient(){
        // Create a GoogleApiClient instance
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,
                        this /* OnConnectionFailedListener */)
                .addApi(Games.API)
                .addScope(Games.SCOPE_GAMES)
                .build();

    }

    private void setLeaderboardOnClickListener() {
        leaderboard = (Button) findViewById(R.id.buttonLeaderboard);
        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(Games.Leaderboards.getLeaderboardIntent(mGoogleApiClient,
                        String.valueOf(R.string.leaderboard_id)),9002 );
            }
        });
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("googleAPI",connectionResult.toString());
    }

}

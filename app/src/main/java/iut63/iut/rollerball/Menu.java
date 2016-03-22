package iut63.iut.rollerball;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.BaseGameActivity;


/**
 * Classe de Menu, premiere Activity ouverte,
 * extends de BaseGameActivity ( librairie de Google ),
 * Afin de permettre la connexion au google play services.
 */
public class Menu extends BaseGameActivity implements View.OnClickListener {
    private Button start;
    private Button leaderboard;
    private static String TAG = Menu.class.getSimpleName();

    /**
     * Méthode appelée à chaque création de l'activité
     * @param savedInstanceState le Bundle
     */
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

    /**
     * Méthode qui set les listener pour les boutons de connexions de Google
     * Ici ce Listener est notre activité, car elle extends BaseGameActivity,
     * et possede ainsi tous ce qu'il faut pour se connecter efficacement.
     */
    private void setGoogleSignButton() {
        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.sign_out_button).setOnClickListener(this);
    }

    /**
     * Définit le listener du bouton LeaderBoard,
     * qui permet de Visualiser le classement.
     * Pour cela, celui ci réalise un appel à startActivityForResult en lui passant l'apiGoogle et l'id du leaderboard voulu.
     */
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
            }
        });
    }

    /**
     * Méthode appelé seulement par les boutons de connexions de Google.
     * Si la View sender est le Sign-In Button, on lance le protocole de connexion du Google Play
     * @param v View sender du onClick
     */
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

    /**
     * Appelé lorsque le joueur s'est correctement connecté.
     * Fait alors disparaitre le Bouton Sign-In.
     * Et fait apparaitre le bouton de déconnexion.
     */
    @Override
    public void onSignInSucceeded() {
        findViewById(R.id.sign_in_button).setVisibility(View.GONE);
        findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
    }

    /**
     * Appelé lorsque le joueur n'a pas réussi à se connecter.
     * Mets le Bouton de Sign-In visible, et fait disparaitre le bouton de déconnexion
     */
    @Override
    public void onSignInFailed() {
        findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
        findViewById(R.id.sign_out_button).setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        putScoreInLeaderBoard(this);
        super.onDestroy();
    }

    /**
     * Chargement de la sauvegarde du score via les SharedPreferences
     * Et Ajout dans le classement Général si l'utilisateur est connecté
     * @param mContext
     */
    public void putScoreInLeaderBoard(Context mContext) {
        SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(mContext);
        if (mSharedPreference != null) {
            int score = mSharedPreference.getInt("Score", 0);
            if (getApiClient().isConnected()) {
                Games.Leaderboards.submitScore(getApiClient(), getString(R.string.leaderboard_id), score);
            }
        }
    }
}

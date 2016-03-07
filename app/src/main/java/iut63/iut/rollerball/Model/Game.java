package iut63.iut.rollerball.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cedric on 04/03/2016.
 */
public class Game {

    private Player player1;
    private List<Level> levelList;

    public Player getPlayer1() {return player1;}
    public void setPlayer1(Player player1) {this.player1 = player1;}
    public List<Level> getLevelList() {return levelList;}
    public void setLevelList(List<Level> levelList) {this.levelList = levelList;}

    public Game(){
        levelList = new ArrayList<>();
        player1 = new Player("toto");
    }

    public void addNewLevel(Level level){
        if(levelList == null){
            levelList = new ArrayList<>();
        }
        levelList.add(level);
    }

}

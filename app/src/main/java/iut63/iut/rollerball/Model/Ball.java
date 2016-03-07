package iut63.iut.rollerball.Model;

import android.media.Image;

/**
 * Created by Cedric on 04/03/2016.
 */
public class Ball extends Component{

    private int id;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public Ball(int id,int posX, int posY, Image myRepresentation){
        setPosX(posX);
        setPosX(posY);
        setMyRepresentation(myRepresentation);
    }
}

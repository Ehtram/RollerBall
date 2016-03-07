package iut63.iut.rollerball.Model;

/**
 * Created by Cedric on 04/03/2016.
 */
public class Level {

    private String name;
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    private Ball myBalle;
    public Ball getMyBalle() {return myBalle;}
    public void setMyBalle(Ball myBalle) {this.myBalle = myBalle;}


    public Level(String Name){
        name = Name;
    }

}

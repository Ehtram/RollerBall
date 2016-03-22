package iut63.iut.rollerball.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cedric on 04/03/2016.
 */
public class Plateau {

    private List<Component> myComponent;

    public List<Component> getMyComponent() {return myComponent;}

    public void setMyComponent(List<Component> myComponent) {this.myComponent = myComponent;}

    public Plateau(){
        myComponent = new ArrayList<>();
    }

}

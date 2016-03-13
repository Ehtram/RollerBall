package iut63.iut.rollerball.Model;

/**
 * Created by Cedric on 04/03/2016.
 */
public abstract class Hole extends Component {

    private Boolean isWinner;
    private int centerX;
    private int centerY;

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public Boolean getIsWinner() {return isWinner;}
    public void setIsWinner(Boolean isWinner) {this.isWinner = isWinner;}

}

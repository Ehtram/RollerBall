package iut63.iut.rollerball.Model;

import android.content.Context;
import android.view.SurfaceView;

/**
 * Created by Cedric on 20/03/2016.
 */
public class level2 extends Level {

    public level2(String name, Context context, SurfaceView surfaceView, int HeightScreen, int WidhtScreen, Ball ball) {
        super(name, context, surfaceView, HeightScreen, WidhtScreen, ball);
        addNewComponent(new WinnerHole(0, HeightScreen / 2 + wallRepresentationHor.getHeight(), winner));
    }
}

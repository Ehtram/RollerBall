package iut63.iut.rollerball.Model;

import android.content.Context;
import android.view.SurfaceView;

/**
 * Created by Cedric on 04/03/2016.
 */
public class Level1 extends Level {

    public Level1(String Name,Context context,SurfaceView surfaceView, int WidhtScreen, int HeightScreen, Ball ball){
        super(Name, context, surfaceView, HeightScreen, WidhtScreen, ball);
        setUnlock(true);

        addNewComponent(new Wall(WidhtScreen / 2, HeightScreen / 2, wallRepresentationVert.getWidth(), wallRepresentationVert.getHeight(), wallRepresentationVert));
        addNewComponent(new Wall(WidhtScreen / 2, HeightScreen / 2 - wallRepresentationVert.getHeight(), wallRepresentationVert.getWidth(), wallRepresentationVert.getHeight(), wallRepresentationVert));
        addNewComponent(new Wall(WidhtScreen / 2, HeightScreen / 2 - wallRepresentationVert.getHeight() * 2, wallRepresentationVert.getWidth(), wallRepresentationVert.getHeight(), wallRepresentationVert));
        addNewComponent(new Wall(WidhtScreen / 2, HeightScreen / 2 - wallRepresentationVert.getHeight() * 3, wallRepresentationVert.getWidth(), wallRepresentationVert.getHeight(), wallRepresentationVert));

        addNewComponent(new Wall(0, HeightScreen / 2, wallRepresentationHor.getWidth(), wallRepresentationHor.getHeight(), wallRepresentationHor));
        addNewComponent(new Wall(wallRepresentationHor.getWidth() - wallRepresentationVert.getWidth(), HeightScreen / 2 + wallRepresentationHor.getHeight(), wallRepresentationVert.getWidth(), wallRepresentationVert.getHeight(), wallRepresentationVert));
        addNewComponent(new LooserHole(wallRepresentationHor.getWidth() - wallRepresentationVert.getWidth(), HeightScreen / 2 + wallRepresentationHor.getHeight() + wallRepresentationVert.getHeight(), hole));
        addNewComponent(new WinnerHole(0, HeightScreen / 2 + wallRepresentationHor.getHeight(), winner));

    }


}

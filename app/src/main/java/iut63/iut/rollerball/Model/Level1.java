package iut63.iut.rollerball.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

/**
 * Created by Cedric on 04/03/2016.
 */
public class Level1 extends Level {




    public Level1(String Name,Context context,SurfaceView surfaceView, int WidhtScreen, int HeightScreen, Ball ball){
        super(Name,context,surfaceView,HeightScreen,WidhtScreen,ball);


        //addNewComponent(new Wall(200, 80, wallRepresentationHor.getWidth(), wallRepresentationHor.getHeight(), wallRepresentationHor));
        addNewComponent(new Wall(WidhtScreen / 2, HeightScreen / 2, wallRepresentationVert.getWidth(), wallRepresentationVert.getHeight(), wallRepresentationVert));
        addNewComponent(new Wall(WidhtScreen / 2, HeightScreen / 2 + wallRepresentationVert.getHeight() + hole.getHeight() + getBall().getMyRepresentation().getHeight() * 2, wallRepresentationVert.getWidth(), wallRepresentationVert.getHeight(), wallRepresentationVert));
        addNewComponent(new Loser(WidhtScreen / 2, HeightScreen / 2 + wallRepresentationVert.getHeight(), hole));
        addNewComponent(new Loser(WidhtScreen / 2 + wallRepresentationVert.getWidth(), HeightScreen / 2 + wallRepresentationVert.getHeight() + hole.getHeight(), hole));
        //listOfWall.add(new Wall(WidhtScreen/2 +115,HeightScreen/2 + wallRepresentationHor.getWidth(),wallRepresentationHor.getWidth(),wallRepresentationHor.getHeight(), wallRepresentationHor));
        addNewComponent(new Wall(WidhtScreen / 2 - wallRepresentationHor.getWidth(), HeightScreen / 2, wallRepresentationHor.getWidth(), wallRepresentationHor.getHeight(), wallRepresentationHor));
        addNewComponent(new Wall(WidhtScreen / 2 - 2 * wallRepresentationHor.getWidth(), HeightScreen / 2, wallRepresentationHor.getWidth(), wallRepresentationHor.getHeight(), wallRepresentationHor));
        addNewComponent(new Winner(WidhtScreen / 2, 0, winner));
        //listOfWall.add(new Loser(0,0,hole));

    }


}

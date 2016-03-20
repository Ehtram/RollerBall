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
        super(Name, context, surfaceView, HeightScreen, WidhtScreen, ball);
        setUnlock(true);

        addNewComponent(new Wall(WidhtScreen / 2, HeightScreen / 2, wallRepresentationVert.getWidth(), wallRepresentationVert.getHeight(), wallRepresentationVert));
        addNewComponent(new Wall(WidhtScreen / 2, HeightScreen / 2 - wallRepresentationVert.getHeight(), wallRepresentationVert.getWidth(), wallRepresentationVert.getHeight(), wallRepresentationVert));
        addNewComponent(new Wall(WidhtScreen / 2, HeightScreen / 2 - wallRepresentationVert.getHeight() * 2, wallRepresentationVert.getWidth(), wallRepresentationVert.getHeight(), wallRepresentationVert));
        addNewComponent(new Wall(WidhtScreen / 2, HeightScreen / 2 - wallRepresentationVert.getHeight() * 3, wallRepresentationVert.getWidth(), wallRepresentationVert.getHeight(), wallRepresentationVert));

        addNewComponent(new Wall(0, HeightScreen / 2, wallRepresentationHor.getWidth(), wallRepresentationHor.getHeight(), wallRepresentationHor));
        addNewComponent(new Wall(wallRepresentationHor.getWidth() - wallRepresentationVert.getWidth(), HeightScreen / 2 + wallRepresentationHor.getHeight(), wallRepresentationVert.getWidth(), wallRepresentationVert.getHeight(), wallRepresentationVert));
        addNewComponent(new Loser(wallRepresentationHor.getWidth() - wallRepresentationVert.getWidth(), HeightScreen / 2 + wallRepresentationHor.getHeight() + wallRepresentationVert.getHeight(), hole));
        addNewComponent(new Winner(0, HeightScreen / 2 + wallRepresentationHor.getHeight(), winner));
/*
        //addNewComponent(new Wall(200, 80, wallRepresentationHor.getWidth(), wallRepresentationHor.getHeight(), wallRepresentationHor));

        addNewComponent(new Wall(WidhtScreen / 2 , HeightScreen / 2 +  wallRepresentationVert.getHeight(), wallRepresentationVert.getWidth(), wallRepresentationVert.getHeight(), wallRepresentationVert));

        addNewComponent(new Wall(WidhtScreen / 2 - wallRepresentationHor.getWidth() - wallRepresentationVert.getWidth(), HeightScreen / 2  , wallRepresentationHor.getWidth(), wallRepresentationHor.getHeight(), wallRepresentationHor));
        addNewComponent(new Wall(WidhtScreen / 2 - wallRepresentationHor.getWidth()*2 - wallRepresentationVert.getWidth(), HeightScreen / 2  , wallRepresentationHor.getWidth(), wallRepresentationHor.getHeight(), wallRepresentationHor));
        addNewComponent(new Loser(WidhtScreen / 2, HeightScreen / 2 - hole.getHeight(), hole));
        addNewComponent(new Loser(WidhtScreen / 2 - wallRepresentationVert.getWidth() - hole.getHeight(), HeightScreen / 2 - hole.getHeight(), hole));
        addNewComponent(new Loser(WidhtScreen / 2 - wallRepresentationVert.getWidth() - hole.getHeight(), HeightScreen / 2 + hole.getHeight(), hole));
        addNewComponent(new Loser(WidhtScreen / 2 - hole.getHeight(), HeightScreen / 2 + 3*hole.getHeight(), hole));
        addNewComponent(new Loser(WidhtScreen / 2 - wallRepresentationVert.getWidth()-wallRepresentationHor.getWidth(), HeightScreen / 2 + hole.getHeight(), hole));
        addNewComponent(new Winner(WidhtScreen / 2 - wallRepresentationHor.getWidth()*2, HeightScreen / 2 + winner.getHeight()*3, winner));
       addNewComponent(new Wall(WidhtScreen / 2, 0, wallRepresentationVert.getWidth(), wallRepresentationVert.getHeight(), wallRepresentationVert));
        addNewComponent(new Wall(WidhtScreen / 2 + wallRepresentationVert.getHeight(), 0, wallRepresentationVert.getWidth(), wallRepresentationVert.getHeight(), wallRepresentationVert));
        addNewComponent(new Wall(WidhtScreen / 2 + wallRepresentationVert.getHeight()*2, 0, wallRepresentationVert.getWidth(), wallRepresentationVert.getHeight(), wallRepresentationVert));
      */


        //  addNewComponent(new Wall(WidhtScreen / 2, HeightScreen / 2 + wallRepresentationVert.getHeight() + hole.getHeight() + getBall().getMyRepresentation().getHeight() * 2, wallRepresentationVert.getWidth(), wallRepresentationVert.getHeight(), wallRepresentationVert));
        // addNewComponent(new Loser(WidhtScreen / 2, HeightScreen / 2 + wallRepresentationVert.getHeight(), hole));
        //addNewComponent(new Loser(WidhtScreen / 2 + wallRepresentationVert.getWidth(), HeightScreen / 2 + wallRepresentationVert.getHeight() + hole.getHeight(), hole));
        //listOfWall.add(new Wall(WidhtScreen/2 +115,HeightScreen/2 + wallRepresentationHor.getWidth(),wallRepresentationHor.getWidth(),wallRepresentationHor.getHeight(), wallRepresentationHor));
        //addNewComponent(new Wall(WidhtScreen / 2 - wallRepresentationHor.getWidth(), HeightScreen / 2, wallRepresentationHor.getWidth(), wallRepresentationHor.getHeight(), wallRepresentationHor));
        //addNewComponent(new Wall(WidhtScreen / 2 - 2 * wallRepresentationHor.getWidth(), HeightScreen / 2, wallRepresentationHor.getWidth(), wallRepresentationHor.getHeight(), wallRepresentationHor));
        //
        //listOfWall.add(new Loser(0,0,hole));

    }


}

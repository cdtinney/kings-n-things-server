package com.kingsandthings.model.things;
import javafx.scene.image.Image;
import java.util.*;
import java.io.*;

//import com.kingsandthings.game.events.PropertyChangeDispatcher;
//import com.kingsandthings.model.board.Tile;


public abstract class Thing {
	private String name;
    private File image;
    private int value;
    //private Image tile;


    public Thing(String iName, int iValue,File imageLocation) {
        this.name = iName;
        this.value = iValue;
        this.image = imageLocation;
        //this.tile = image;

    }



    public String toString() {
        return "\n\nName:" + name +"\nValue: "+ value +"\nFile:"+image;

    }

}

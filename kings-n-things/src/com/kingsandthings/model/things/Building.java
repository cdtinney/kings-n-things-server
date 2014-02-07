package com.kingsandthings.model.things;
import java.util.*;
import java.io.*;
import java.io.File;

public class Building extends Thing{
	private String state;


    public Building(String iName, int iCombatValue, File imageLocation) {
        super(iName,iCombatValue,imageLocation);
        if (iCombatValue == 0)
            this.state = "Neutralized";
        else
            this.state = "Active";

    }

    public String getType() { return this.state; }

    public String toString() {
        return super.toString() + "\nState:" + state;

    }
}

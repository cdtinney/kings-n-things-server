package com.kingsandthings.model.things;

import java.io.File;

public class Creature extends Thing {
	 private String type;
	    private String ability;


	    public Creature(String iName, String iType, String iAbility, int iCombatValue, File imageLocation) {
	        super(iName,iCombatValue,imageLocation);
	        this.type = iType;
	        this.ability = iAbility;
	    }

	    public Creature(String iName, String iType, int iCombatValue, File imageLocation) {
	        super(iName,iCombatValue,imageLocation);
	        this.type = iType;
	        this.ability = "None";
	    }

	    public String getType() { return this.type; }

	    public String toString() {
	        return super.toString() + "\nType:" + type + "\nAbility:"+ability;

	    }
	
	
}

package com.kingsandthings.model.things;
import java.util.*;
import java.io.File;



public class ThingManager {
    public List<Creature> creatures = new ArrayList<Creature>();
    public List<Building> buildings = new ArrayList<Building>();

    public ThingManager() {
        setupCreatures();
        setupBuildings();
        //System.out.println(creatures);
        //System.out.println(buildings);
        	
    }




    
    //Import Buildings
    private void setupBuildings() {
        //Import all creature locations
        List<File> listf = listf("resources\\things\\Building");
        String[] temp;
        for (int i=0;i < listf.size();i++) {
            temp = listf.get(i).getName().split(" ");
            if (temp[3].charAt(0) == 'N')
                buildings.add(new Building(temp[1],0,listf.get(i)));
            else
                buildings.add(new Building(temp[1],Character.getNumericValue(temp[3].charAt(0)),listf.get(i)));
        }
    }

    //Import Creatures
    private void setupCreatures() {
        //Import all creature locations
        List<File> listf = listf("resources\\things\\Defenders");
        List<File> thingLocations = new ArrayList<File>(); //contains list of all images needed for things

        //remove any files that aren't images as well as directories
        for (int i=0;i < listf.size();i++) {
            if((listf.get(i)).getName().endsWith("jpg") || (listf.get(i)).getName().endsWith("png"))  {
                thingLocations.add(listf.get(i));
            }
        }
        //test
        String[] temp;
        for (int i=0;i < thingLocations.size();i++) {
            temp = thingLocations.get(i).getName().split(" ");
            if (temp.length == 8) //has ability
                creatures.add(new Creature(temp[1],temp[3], temp[5],Character.getNumericValue(temp[7].charAt(0)),thingLocations.get(i)));
            if (temp.length == 6) //no ability
                creatures.add(new Creature(temp[1],temp[3], Character.getNumericValue(temp[5].charAt(0)),thingLocations.get(i)));

        }
    }

    public static List<File> listf(String directoryName) {
        File directory = new File(directoryName);

        List<File> resultList = new ArrayList<File>();

        // get all the files from a directory
        File[] fList = directory.listFiles();
        resultList.addAll(Arrays.asList(fList));
        for (File file : fList) {
            if (file.isFile()) {
                //System.out.println(file.getAbsolutePath());
            } else if (file.isDirectory()) {
                resultList.addAll(listf(file.getAbsolutePath()));
            }
        }
        //System.out.println(fList);
        return resultList;
    }


}

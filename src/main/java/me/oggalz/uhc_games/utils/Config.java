package me.oggalz.uhc_games.utils;

public class Config {

    private int sizeDiamond = 0 ;
    private int sizeGaps;
    private int timePvp;
    private int borderSize;
    private int borderTime ;


    public void increaseDiamond(){
        sizeDiamond += 1;
    }

    public void reduceDiamond(){
        sizeDiamond -= 1;
    }



    public int getSizeDiamond() {
        return sizeDiamond;
    }

    public int getSizeGaps() {
        return sizeGaps;
    }

    public int getTimePvp() {
        return timePvp;
    }

    public int getBorderSize() {
        return borderSize;
    }

    public int getBorderTime() {
        return borderTime;
    }
}

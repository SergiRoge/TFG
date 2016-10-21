package Classes;

import java.io.Serializable;
import java.util.ArrayList;

import DatabaseAuxiliar.DatabaseObject;

/**
 * Created by Llango on 16/10/2016.
 */

public class Item extends DatabaseObject implements Serializable {




    private ArrayList<Coordinate>  arrayListCoordsAdded;
    private String strItemType = "";
    private String strItemColor = "";
    private String strItemBrand = "";
    private String strItemMaterial = "";
    private int intWhen = 0;
    private String strDescription= "";
    private String strStatus;


    public  Item()
    {

    }
    public Item(String pstrItemType, String pstrItemColor, String pstrItemBrand, String pstrItemMaterial, int pintWhen, String pstrStatus)
    {
        strItemType     = pstrItemType;
        strItemColor    = pstrItemColor;
        strItemBrand    = pstrItemBrand;
        strItemMaterial = pstrItemMaterial;
        intWhen         = pintWhen;
        strStatus      = pstrStatus;
        arrayListCoordsAdded = new ArrayList<Coordinate>(3);
    }


    public void save()
    {

    }


    /**
     *
     *    Getters and Setters
     *
     */

    public String getStrItemType() {
        return strItemType;
    }

    public void setStrItemType(String strItemType) {
        this.strItemType = strItemType;
    }

    public String getStrItemColor() {
        return strItemColor;
    }

    public void setStrItemColor(String strItemColor) {
        this.strItemColor = strItemColor;
    }

    public String getStrItemBrand() {
        return strItemBrand;
    }

    public void setStrItemBrand(String strItemBrand) {
        this.strItemBrand = strItemBrand;
    }

    public String getStrItemMaterial() {
        return strItemMaterial;
    }

    public void setStrItemMaterial(String strItemMaterial) {
        this.strItemMaterial = strItemMaterial;
    }

    public int getIntWhen() {
        return intWhen;
    }

    public void setIntWhen(int intWhen) {
        this.intWhen = intWhen;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }
    public ArrayList<Coordinate> getArrayListCoordsAdded() {
        return arrayListCoordsAdded;
    }

    public void setArrayListCoordsAdded(ArrayList<Coordinate> arrayListCoordsAdded) {
        this.arrayListCoordsAdded = arrayListCoordsAdded;
    }

    public void addCoordinateToArray(Coordinate pCoordinate)

    {
        arrayListCoordsAdded.add(pCoordinate);
    }

    public String getStrStatus() {
        return strStatus;
    }

    public void setStrStatus(String strStatus) {
        this.strStatus = strStatus;
    }

}

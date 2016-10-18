package Classes;

import java.io.Serializable;

import DatabaseAuxiliar.DatabaseObject;

/**
 * Created by Llango on 16/10/2016.
 */

public class Item extends DatabaseObject implements Serializable {



    private String strItemType      = "";
    private String strItemColor     = "";
    private String strItemBrand     = "";
    private String strItemMaterial  = "";
    private int intWhen             = 0;

    public Item(String pstrItemType, String pstrItemColor, String pstrItemBrand, String pstrItemMaterial, int pintWhen)
    {
        strItemType     = pstrItemType;
        strItemColor    = pstrItemColor;
        strItemBrand    = pstrItemBrand;
        strItemMaterial = pstrItemMaterial;
        intWhen         = pintWhen;
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


}

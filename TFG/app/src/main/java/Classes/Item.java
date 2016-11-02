package Classes;

import android.util.Log;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import Connection.SQLObject;
import DatabaseAuxiliar.DatabaseObject;

import static Auxiliar.Constants.URL_SAVE_USER;

/**
 * Created by Llango on 16/10/2016.
 */

public class Item extends SQLObject implements Serializable {




    private ArrayList<Coordinate>  arrayListCoordsAdded;
    private String strItemType = "";
    private String strItemColor = "";
    private String strItemBrand = "";
    private String strItemMaterial = "";
    private int intWhen = 0;
    private String strDescription= "";
    private String strStatus;
    private String FoundLost;


    public  Item()
    {

    }
    public Item(String pstrItemType, String pstrItemColor, String pstrItemBrand, String pstrItemMaterial, int pintWhen, String pstrStatus, String pstrDescription, String pFoundLost)
    {
        strItemType     = pstrItemType;
        strItemColor    = pstrItemColor;
        strItemBrand    = pstrItemBrand;
        strItemMaterial = pstrItemMaterial;
        intWhen         = pintWhen;
        strStatus       = pstrStatus;
        strDescription  = pstrDescription;
        FoundLost       = pFoundLost;
        arrayListCoordsAdded = new ArrayList<Coordinate>(3);
    }

    public int save() throws IOException, InterruptedException {

        Log.d("SAVE","ITEM");
        String content = "";
        content += "foundLost="+ "" +
                    "type="+ strItemType + "&" +
                    "color="+ strItemColor + "&" +
                    "brand="+ strItemBrand + "&" +
                    "material="+ strItemMaterial + "&" +
                    "when="+ intWhen+ "&" +
                    "description="+strDescription + "&" +
                    "status="+strStatus;

        Coordinate coord = new Coordinate();

        for(int i = 0; i < arrayListCoordsAdded.size(); i++)
        {
            coord = arrayListCoordsAdded.get(i);
            content +=  "&coordX"+i+"=" + coord.getDblXCoordinate() + "&coordY"+i+"=" + coord.getDblYCoordinate();

        }

        Log.d("ITEM","content : "+content);

        //String strReturn =  ExecuteQuery(URL_SAVE_USER, content);

        //An intenger expected, the SQLCode
        //return Integer.parseInt(strReturn.trim());
        return 1;

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

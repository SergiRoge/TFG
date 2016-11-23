package Classes;

import android.util.Log;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import Connection.SQLObject;

import static Auxiliar.Constants.URL_SAVE_ITEM;

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
    private int intStatus;
    private String strFoundLost;


    public  Item()
    {

    }

    public int getIntStatus() {
        return intStatus;
    }

    public void setIntStatus(int intStatus) {
        this.intStatus = intStatus;
    }

    public String getStrFoundLost() {
        return strFoundLost;
    }

    public void setStrFoundLost(String strFoundLost) {
        this.strFoundLost = strFoundLost;
    }

    public Item(String pstrItemType, String pstrItemColor, String pstrItemBrand, String pstrItemMaterial, int pintWhen, int pintStatus, String pstrDescription, String pstrFoundLost)
    {
        strItemType     = pstrItemType;
        strItemColor    = pstrItemColor;
        strItemBrand    = pstrItemBrand;
        strItemMaterial = pstrItemMaterial;
        intWhen         = pintWhen;
        intStatus       = pintStatus;
        strDescription  = pstrDescription;
        strFoundLost       = pstrFoundLost;
        arrayListCoordsAdded = new ArrayList<Coordinate>();
    }

    public int save(User user) throws IOException, InterruptedException {

        String content = "";
        content += "type="+ strItemType + "&" +
                    "color="+ strItemColor + "&" +
                    "brand="+ strItemBrand + "&" +
                    "material="+ strItemMaterial + "&" +
                    "when="+ intWhen+ "&" +
                    "description="+strDescription + "&" +
                    "status="+intStatus + "&" +
                    "foundLost="+strFoundLost;


        Coordinate coord = new Coordinate();

        for(int i = 0; i < arrayListCoordsAdded.size(); i++)
        {
            coord = arrayListCoordsAdded.get(i);
            content +=  "&coordX"+i+"=" + coord.getDblXCoordinate() + "&coordY"+i+"=" + coord.getDblYCoordinate();

        }
        content += "&user="+user.getStrEmail();
        content += "&coordsnumber="+arrayListCoordsAdded.size();
        Log.d("ITEM","content : "+content);

        String strReturn =  ExecuteQuery(URL_SAVE_ITEM, content);

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

    public int getStrStatus() {
        return intStatus;
    }

    public void setStrStatus(int intStatus) {
        this.intStatus = intStatus;
    }


}

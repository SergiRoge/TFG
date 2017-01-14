package Classes;

import android.util.Log;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import Connection.SQLObject;

import static Auxiliar.Constants.URL_SAVE_ITEM;

/**
 * Created by Llango on 16/10/2016.
 *
 * Class that contains all the information of an item.
 * Extends SQLObject to get the inherit methods.
 * Implements Serializable in order to be able to get through activities.
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


    /**
     * Constructor with parameters
     * @param pstrItemType
     * @param pstrItemColor
     * @param pstrItemBrand
     * @param pstrItemMaterial
     * @param pintWhen
     * @param pintStatus
     * @param pstrDescription
     * @param pstrFoundLost
     */
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


    /**
     *  Method that saves the information of an item to database.
     *
     * @param user
     * @return
     * @throws IOException
     * @throws InterruptedException
     */

    public int save(User user) throws IOException, InterruptedException {


        //We prepare all parameters of an item
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

        //We prepare the coordinate section
        for(int i = 0; i < arrayListCoordsAdded.size(); i++)
        {
            coord = arrayListCoordsAdded.get(i);
            content +=  "&coordX"+i+"=" + coord.getDblXCoordinate() + "&coordY"+i+"=" + coord.getDblYCoordinate();

        }

        //The user section
        content += "&user="+user.getStrEmail();

        //And the number of coordinates added
        content += "&coordsnumber="+arrayListCoordsAdded.size();
        Log.d("ITEM","content : "+content);

        //We call the method that will save the item to the database
        String strReturn =  ExecuteQuery(URL_SAVE_ITEM, content);

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

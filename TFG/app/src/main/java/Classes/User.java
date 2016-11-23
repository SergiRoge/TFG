package Classes;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import Auxiliar.ErrorCode;
import Connection.ConnectionThread;
import Connection.SQLObject;

import static Auxiliar.Auxiliar.setErrorCode;
import static Auxiliar.Auxiliar.showMessageError;
import static Auxiliar.Constants.*;

/**
 * Created by Llango on 16/10/2016.
 */

public class User extends SQLObject implements Serializable {


    String strEmail;
    String strUserName = "";
    String strPassword;

    ArrayList<Item> listOfItems;




    public User(String pstrTxtEmail, String pstrTxtPassword)
    {
        super();
        strEmail = pstrTxtEmail;
        strPassword = pstrTxtPassword;
        listOfItems = new ArrayList<Item>();
    }

    public  User(String pstrTxtEmail, String pstrUserName, String pstrTxtPassword)
    {
        super();
        strEmail = pstrTxtEmail;
        strUserName = pstrUserName;
        strPassword = pstrTxtPassword;
        listOfItems = new ArrayList<Item>();

    }



    public int save() throws IOException, InterruptedException {

        String content = "";
        content += "name="+strUserName + "&" +
                "password="+strPassword + "&" +
                "email="+strEmail;

        String strReturn =  ExecuteQuery(URL_SAVE_USER, content);

        //An intenger expected, the SQLCode
        return Integer.parseInt(strReturn.trim());

    }

    public int retrieveUserData() throws IOException, InterruptedException, JSONException
    {

        String content = "";
        content +="password=" + strPassword + "&" +
                "email=" + strEmail;

        String JSONstrReturn =  ExecuteQuery(URL_CHECK_USER, content);

        fillUserFields(JSONstrReturn);

        return OK;
    }

    /**
     *
     * @param pstrJSONObject   The JSON received from the server
     * @return              int :   1, There was no user matching the email/password
     *                              0, User retrieved succesfully from JSON
     * @throws JSONException
     */
    public int fillUserFields(String pstrJSONObject) throws JSONException
    {
        //Getting the username of the user

        Log.d("jsonObject ,", "-->" + pstrJSONObject);




        JSONArray jsonArray = new JSONArray (pstrJSONObject);


        strUserName = jsonArray.getJSONObject(0).getString("UserName");
        if(strUserName.equals(""))
        {
            return 1;
        }
        //Getting the list of items listOfItems

        Item item;

        for(int i = 1; i < jsonArray.length(); i ++)
        {
            //{"ItemType":"Gafas","Brand":"RayBan","Material":"Cristal","Color":"Negro","When":"0","FoundLost":"Lost","Description":"Tipicas gafas RayBan","Status":0,

            String itemType = jsonArray.getJSONObject(i).getString("ItemType");
            String Brand = jsonArray.getJSONObject(i).getString("Brand");
            String Material = jsonArray.getJSONObject(i).getString("Material");
            String Color = jsonArray.getJSONObject(i).getString("Color");
            String When = jsonArray.getJSONObject(i).getString("When");
            String FoundLost = jsonArray.getJSONObject(i).getString("FoundLost");
            String Description = jsonArray.getJSONObject(i).getString("Description");
            String Status = jsonArray.getJSONObject(i).getString("Status");

            String strArrayListCoordsAdded = jsonArray.getJSONObject(i).getString("CoordinatesList");

            item = new Item(itemType, Color, Brand, Material, Integer.parseInt(When), Integer.parseInt(Status), Description, FoundLost);

            // "CoordinatesList":[{"XCoord":"0","YCoord":"0"},{"XCoord":"1","YCoord":"1"},{"XCoord":"2","YCoord":"2"}]}
            JSONArray CoordsJSONArray = new JSONArray (strArrayListCoordsAdded);
            Coordinate coord;
            for(int j = 0; j < CoordsJSONArray.length(); j++)
            {
                String XCoord = CoordsJSONArray.getJSONObject(j).getString("XCoord");
                String YCoord = CoordsJSONArray.getJSONObject(j).getString("YCoord");
                try
                {
                    coord = new Coordinate(Double.parseDouble(XCoord),Double.parseDouble(YCoord));
                    item.addCoordinateToArray(coord);
                }
                catch(Exception e)
                {

                }

            }
            listOfItems.add(item);


        }
        return OK;
    }


    /**
     *
     *  Getters and Setters
     *
     */

    public String getStrEmail() {
        return strEmail;
    }

    public void setStrEmail(String strEmail) {
        this.strEmail = strEmail;
    }

    public String getStrPassword() {
        return strPassword;
    }

    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword;
    }

    public String getStrUserName() {
        return strUserName;
    }

    public void setStrUserName(String strUserName) {
        this.strUserName = strUserName;
    }

    public ArrayList<Item> getListOfItems() {
        return listOfItems;
    }

    public void setListOfItems(ArrayList<Item> listOfItems) {
        this.listOfItems = listOfItems;
    }

}

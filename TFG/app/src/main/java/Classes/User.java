package Classes;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import Auxiliar.ErrorCode;
import Connection.ConnectionThread;
import Connection.SQLObject;

import static Auxiliar.Auxiliar.setErrorCode;
import static Auxiliar.Auxiliar.showMessageError;
import static Auxiliar.Constants.*;
import Chat.Chat;
/**
 * Created by Llango on 16/10/2016.
 *
 *
 * Class that contains all the information of an user.
 * Extends SQLObject to get the inherit methods.
 * Implements Serializable in order to be able to get through activities.
 */

public class User extends SQLObject implements Serializable {


    String strEmail;
    String strUserName = "";
    String strPassword;

    ArrayList<Item> listOfItems;

    public ArrayList<Chat> lst_chats;

    /**
     * Empty constructor
     */
    public User()
    {
    }

    /**
     * Constructor with parameters
     *
     * @param pstrTxtEmail
     * @param pstrTxtPassword
     */
    public User(String pstrTxtEmail, String pstrTxtPassword)
    {
        super();
        strEmail = pstrTxtEmail;
        strPassword = pstrTxtPassword;
        listOfItems = new ArrayList<Item>();
        lst_chats = new ArrayList<Chat>();
    }

    /**
     * Constructor with parameters
     *
     * @param pstrTxtEmail
     * @param pstrUserName
     * @param pstrTxtPassword
     */
    public  User(String pstrTxtEmail, String pstrUserName, String pstrTxtPassword)
    {
        super();
        strEmail = pstrTxtEmail;
        strUserName = pstrUserName;
        strPassword = pstrTxtPassword;
        listOfItems = new ArrayList<Item>();
        lst_chats = new ArrayList<Chat>();


    }


    /**
     *
     * Method that saves the information of an user to database.
     *
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public int save() throws IOException, InterruptedException {

        //We prepare all the contents to be sent to the server
        String content = "";
        content += "name="+strUserName + "&" +
                "password="+strPassword + "&" +
                "email="+strEmail;

        //And we call the method
        String strReturn =  ExecuteQuery(URL_SAVE_USER, content);

        //An intenger expected, the SQLCode
        return Integer.parseInt(strReturn.trim());

    }


    /**
     * Method that asks for the all data of an user.
     *
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws JSONException
     */
    public int retrieveUserData() throws IOException, InterruptedException, JSONException
    {

        //We prepare all the contents to be sent to the server

        String content = "";
        content +="password=" + strPassword + "&" +
                "email=" + strEmail;

        String JSONstrReturn =  ExecuteQuery(URL_CHECK_USER, content);
        return fillUserFields(JSONstrReturn);


    }

    /**
     *  Method that fills all the data of an user from the server
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

        int a = 1;



        //First of all,  we get the user name of the user
        strUserName = jsonArray.getJSONObject(0).getString("UserName");

        //if it does not exist, return an error
        if(strUserName.equals("") || strUserName == null || strUserName.equals("null"))
        {
            return INCORRECT_USER;
        }

        /** Item Section */

        Item item;
        JSONArray ItemList = new JSONArray (jsonArray.getJSONObject(1).getString("ItemList").toString());

        //we get the number of items to iterate
        for(int i = 0; i < ItemList.length(); i ++)
        {
            //{"ItemType":"Gafas","Brand":"RayBan","Material":"Cristal","Color":"Negro","When":"0","FoundLost":"Lost","Description":"Tipicas gafas RayBan","Status":0,

            //Get all the data of an item
            String itemType = ItemList.getJSONObject(i).getString("ItemType");
            String Brand = ItemList.getJSONObject(i).getString("Brand");
            String Material = ItemList.getJSONObject(i).getString("Material");
            String Color = ItemList.getJSONObject(i).getString("Color");
            String When = ItemList.getJSONObject(i).getString("When");
            String FoundLost = ItemList.getJSONObject(i).getString("FoundLost");
            String Description = ItemList.getJSONObject(i).getString("Description");
            String Status = ItemList.getJSONObject(i).getString("Status");

            String strArrayListCoordsAdded = ItemList.getJSONObject(i).getString("CoordinatesList");

            //Create the object
            item = new Item(itemType, Color, Brand, Material, Integer.parseInt(When), Integer.parseInt(Status), Description, FoundLost);

            // "CoordinatesList":[{"XCoord":"0","YCoord":"0"},{"XCoord":"1","YCoord":"1"},{"XCoord":"2","YCoord":"2"}]}

            //we get the array of coordinates
            JSONArray CoordsJSONArray = new JSONArray (strArrayListCoordsAdded);
            Coordinate coord;
            for(int j = 0; j < CoordsJSONArray.length(); j++)
            {
                String XCoord = CoordsJSONArray.getJSONObject(j).getString("XCoord");
                String YCoord = CoordsJSONArray.getJSONObject(j).getString("YCoord");
                try
                {
                    //we add the new coordinate
                    coord = new Coordinate(Double.parseDouble(XCoord),Double.parseDouble(YCoord));
                    item.addCoordinateToArray(coord);
                }
                catch(Exception e)
                {
                    //if there is an error
                    //return INCORRECT_USER;

                }

            }
            //the iten is added to the itemlist of an user
            listOfItems.add(item);


        }

        /** Chats Section */

        //we get the chatlist of an user
        JSONArray ChatList = new JSONArray (jsonArray.getJSONObject(2).getString("ChatList").toString());
        Chat chat;

        //for each chat
        for(int i = 0; i < ChatList.length(); i ++)
        {


            String UserName= ChatList.getJSONObject(i).getString("ChatUserName");
            String Email = ChatList.getJSONObject(i).getString("ChatEmail");

            //we create an chat object and add it to te chatlist of an user
            chat = new Chat(UserName, Email);
            Log.d("Chat añadido : ", "-> " + chat.getUser().getStrUserName() );
            lst_chats.add(chat);
        }
        //return ok if everything went okey
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

        for(int i = 0; i < listOfItems.size(); i++ )
        {
            Log.d("item numero " + (i+1)," -> " + listOfItems.get(i).getStrItemType());
        }

        return listOfItems;
    }

    public void setListOfItems(ArrayList<Item> listOfItems) {
        this.listOfItems = listOfItems;
    }

    public void saveChat(Chat chat)  throws IOException, InterruptedException {

        String content = "";
        content += "email="+ this.strEmail+ "&" +
                "otherEmail="+ chat.getUser().getStrEmail();

        String strReturn =  ExecuteQuery(URL_SAVE_CHAT, content);
        Log.d("save chat ", "-> " + strReturn);

        lst_chats.add(chat);


    }
}

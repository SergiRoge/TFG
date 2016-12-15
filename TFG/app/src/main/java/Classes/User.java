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
 */

public class User extends SQLObject implements Serializable {


    String strEmail;
    String strUserName = "";
    String strPassword;

    ArrayList<Item> listOfItems;

    public ArrayList<Chat> lst_chats;


    public User()
    {
    }

    public User(String pstrTxtEmail, String pstrTxtPassword)
    {
        super();
        strEmail = pstrTxtEmail;
        strPassword = pstrTxtPassword;
        listOfItems = new ArrayList<Item>();
        lst_chats = new ArrayList<Chat>();
    }

    public  User(String pstrTxtEmail, String pstrUserName, String pstrTxtPassword)
    {
        super();
        strEmail = pstrTxtEmail;
        strUserName = pstrUserName;
        strPassword = pstrTxtPassword;
        listOfItems = new ArrayList<Item>();
        lst_chats = new ArrayList<Chat>();


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
        return fillUserFields(JSONstrReturn);


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

        int a = 1;
        //strUserName = jsonArray.getJSONObject(0).getString("chat");








        strUserName = jsonArray.getJSONObject(0).getString("UserName");
        Log.d("strUserName : ", "---> " + strUserName);
        if(strUserName.equals("") || strUserName == null || strUserName.equals("null"))
        {
            return INCORRECT_USER;
        }
        //Getting the list of items listOfItems

        Item item;
        JSONArray ItemList = new JSONArray (jsonArray.getJSONObject(1).getString("ItemList").toString());
        for(int i = 0; i < ItemList.length(); i ++)
        {
            //{"ItemType":"Gafas","Brand":"RayBan","Material":"Cristal","Color":"Negro","When":"0","FoundLost":"Lost","Description":"Tipicas gafas RayBan","Status":0,

            String itemType = ItemList.getJSONObject(i).getString("ItemType");
            String Brand = ItemList.getJSONObject(i).getString("Brand");
            String Material = ItemList.getJSONObject(i).getString("Material");
            String Color = ItemList.getJSONObject(i).getString("Color");
            String When = ItemList.getJSONObject(i).getString("When");
            String FoundLost = ItemList.getJSONObject(i).getString("FoundLost");
            String Description = ItemList.getJSONObject(i).getString("Description");
            String Status = ItemList.getJSONObject(i).getString("Status");

            String strArrayListCoordsAdded = ItemList.getJSONObject(i).getString("CoordinatesList");

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
                    return INCORRECT_USER;

                }

            }
            listOfItems.add(item);


        }

        JSONArray ChatList = new JSONArray (jsonArray.getJSONObject(2).getString("ChatList").toString());
        Chat chat;
        for(int i = 0; i < ChatList.length(); i ++)
        {

            String UserName= ChatList.getJSONObject(i).getString("ChatUserName");
            String Email = ChatList.getJSONObject(i).getString("ChatEmail");
            chat = new Chat(UserName, Email);
            Log.d("Chat añadido : ", "-> " + chat.getUser().getStrUserName() );
            lst_chats.add(chat);
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

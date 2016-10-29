package Classes;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import Auxiliar.ErrorCode;
import Connection.ConnectionThread;
import Connection.SQLObject;
import DatabaseAuxiliar.DatabaseObject;

import static Auxiliar.Auxiliar.setErrorCode;
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

    public String checkEmailPasswprdMatches() throws IOException, InterruptedException, JSONException {

        String content = "";
        content +="password=" + strPassword + "&" +
                "email=" + strEmail;


        String JSONstrReturn =  ExecuteQuery(URL_CHECK_USER, content);
        Log.d("JSONstrReturn ", " -> " + JSONstrReturn);
        JSONObject jsonObject = new JSONObject(JSONstrReturn);
        Log.d("jsonObject ", " -> " + jsonObject.toString());
        String a = jsonObject.getString("UserName");
        Log.d("Username ", " -> " + a);
        return a;

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

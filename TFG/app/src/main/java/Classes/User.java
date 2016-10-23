package Classes;

import java.io.IOException;

import Connection.ConnectionThread;
import Connection.SQLObject;
import DatabaseAuxiliar.DatabaseObject;

import static Auxiliar.Constants.*;

/**
 * Created by Llango on 16/10/2016.
 */

public class User extends SQLObject {
    String strEmail;
    String strUserName;
    String strPassword;




    public  User(String pstrTxtEmail, String pstrUserName, String pstrTxtPassword)
    {
        super();
        strEmail = pstrTxtEmail;
        strUserName = pstrUserName;
        strPassword = pstrTxtPassword;

    }


    public int save() throws IOException
    {
        String content = "";
        content += "name="+strUserName + "&" +
                "password="+strPassword + "&" +
                "email="+strEmail;

        return super.save(URL_SAVE_USER, content);

    }

}

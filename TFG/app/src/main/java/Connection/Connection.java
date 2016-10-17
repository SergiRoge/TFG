package Connection;

import com.google.firebase.auth.api.model.StringList;

import java.util.ArrayList;

/**
 * Created by Llango on 16/10/2016.
 */

public class Connection {

    public Connection()
    {

    }


    /**
     *  Method that will create a thread with the parameters username and password
     *
     * @param pstrUserName  String  The username of the user
     * @param pstrPassword  String  The password of the user
     * @return
     */
    public int checkUser(String pstrUserName, String pstrPassword)
    {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(pstrUserName);
        arrayList.add(pstrPassword);

        ConnectionThread connectionThread = new ConnectionThread("checkUser",arrayList);
        connectionThread.run();

        return 0;
    }

}

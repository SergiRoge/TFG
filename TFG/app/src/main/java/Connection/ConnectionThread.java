package Connection;

import com.google.firebase.auth.api.model.StringList;

import java.util.ArrayList;

/**
 * Created by Llango on 16/10/2016.
 *
 * Class that make the calls to the server, must be a thread in order to work properly
 *
 */

public class ConnectionThread implements Runnable {

    private String strMethod;
    private ArrayList<String> arrayParams;

    /**
     * Constructor of the ConnectionThread class
     *
     * @param pstrMethod    String      Method that will be executed
     * @param parrayParams StringList  The parameters needed by the method
     */
    public ConnectionThread(String pstrMethod, ArrayList<String> parrayParams)
    {
        if(!pstrMethod.isEmpty())
        {
            strMethod = pstrMethod;
            arrayParams = parrayParams;
        }
    }


    @Override
    public void run()
    {
        switch (strMethod)
        {
            case "checkUser":
                String strUserName = arrayParams.get(0);
                String strPassword = arrayParams.get(1);


                break;
            default:
                break;
        }


    }
}

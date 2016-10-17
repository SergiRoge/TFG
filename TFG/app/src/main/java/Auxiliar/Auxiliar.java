package Auxiliar;

import Connection.Connection;

/**
 * Created by Llango on 16/10/2016.
 */

public final class Auxiliar {

    public static final int SERVER_ERROR = 1;


    private Auxiliar()
    {

    }


    /*public static int checkUser(String pstrUserName, String pstrPassword)
    {
        Connection con = new Connection();
        int error = con.checkUser(pstrUserName,pstrPassword);

        return error;

    }
*/
    public static void showMessageError(int pintError)
    {
        switch (pintError)
        {
            case SERVER_ERROR:
                break;
            default:
                break;
        }


    }


}

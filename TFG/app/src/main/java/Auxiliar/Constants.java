package Auxiliar;

/**
 * Created by Llango on 18/10/2016.
 */

public final class Constants {

    //Errors
    public final static int OK                          = 0;
    public final static int SERVER_ERROR                = 1;
    public final static int EMAIL_ALREADY_REGISTERED    = 1062;
    public final static int INCORRECT_USER_PASSWORD     = 3;
    public final static int IO_EXCEPTION                = 4;
    public final static int Interrupted_Exception       = 7;

    public final static int REGISTRATION_SUCCESFULLY     = 5;
    public final static int Protocol_Exception          = 6;



    //Strings
    public final static String ERROR    = "Error";
    public final static String WARNING  = "Warning";
    public final static int BUTTON_1 = 1;
    public final static int BUTTON_2 = 2;
    public final static int BUTTON_3 = 3;

     //When

    public final static int TODAY           = 0;
    public final static int YESTERDAY       = 1;
    public final static int TWO_DAYS_AGO    = 2;
    public final static int ONE_WEEK_AGO    = 3;
    public final static int ONE_MONTH_AGO   = 4;


    //Connection

    public final static String IP               = "10.0.2.2";
    //public final static String PORT             = "8080";

    public final static String URL_SAVE_ITEM    = "http://10.0.2.2/TFG/ServerSide/Functions/SaveItem.php";
    public final static String URL_SAVE_USER    = "http://10.0.2.2/TFG/ServerSide/Functions/SaveUser.php";






    private Constants()
    {

    }
}

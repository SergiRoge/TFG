package Chat;

import android.util.Log;

import java.io.IOException;
import java.io.Serializable;

import Classes.Coordinate;
import Classes.User;

import static Auxiliar.Constants.URL_SAVE_ITEM;

/**
 * Created by Llango on 03/12/2016.
 */

public class Chat implements Serializable
{

    User theOtherUser;

    public Chat(String userName, String email)
    {
        theOtherUser = new User();
        theOtherUser.setStrEmail(email);
        theOtherUser.setStrUserName(userName);

    }





    public User getUser() {
        return theOtherUser;
    }

    public void setUser(User user) {
        this.theOtherUser = user;
    }

}

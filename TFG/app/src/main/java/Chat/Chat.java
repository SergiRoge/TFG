package Chat;

import android.util.Log;

import java.io.IOException;
import java.io.Serializable;

import Classes.Coordinate;
import Classes.User;

import static Auxiliar.Constants.URL_SAVE_ITEM;

/**
 * Created by Llango on 03/12/2016.
 *
 *  Class that contains the information of a chat between two users.
 */

public class Chat implements Serializable
{

    User theOtherUser;


    /**
     * Constructor with parameters
     *
     * @param userName
     * @param email
     */
    public Chat(String userName, String email)
    {
        theOtherUser = new User();
        theOtherUser.setStrEmail(email);
        theOtherUser.setStrUserName(userName);

    }


    /* Setters and Getters */
    public User getUser() {
        return theOtherUser;
    }

    public void setUser(User user) {
        this.theOtherUser = user;
    }

}

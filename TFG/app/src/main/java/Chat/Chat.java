package Chat;

import java.io.Serializable;

import Classes.User;

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

package Classes;

/**
 * Created by Llango on 03/12/2016.
 */

public class ChatViewList
{
    public String title;



    public User theOtherUser;

    public ChatViewList(){
        super();
    }

    public ChatViewList(String title) {
        super();
        this.title = title;

    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getTheOtherUser() {
        return theOtherUser;
    }

    public void setTheOtherUser(User theOtherUser) {
        this.theOtherUser = theOtherUser;
    }
}

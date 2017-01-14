package Classes;

/**
 * Created by Llango on 03/12/2016.
 */


/**
 * Class that contains the information shown in the elements of the chat view list.
 */
public class ChatViewList
{
    public String title;



    public User theOtherUser;

    public ChatViewList(){
        super();
    }

    /**
     * Constructor with parameters
     *
     * @param title
     */
    public ChatViewList(String title) {
        super();
        this.title = title;

    }


        /* Setters and Getters */

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}

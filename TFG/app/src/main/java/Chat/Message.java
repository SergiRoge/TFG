package Chat;

/**
 * Created by Llango on 01/12/2016.
 */

public class Message {

    private String message;
    private String date;
    private String user;

    public Message()
    {

    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


}

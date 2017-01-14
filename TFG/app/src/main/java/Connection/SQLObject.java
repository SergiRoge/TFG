package Connection;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import Auxiliar.ErrorCode;

import static Auxiliar.Auxiliar.setErrorCode;

/**
 * Created by Sergi on 13/03/2016.
 *
 * Class SQLObject, has the method ExecuteQuery, which creates and establishes a new connection with
 * the server, waits until the thread has finished and return the result of the server.
 */
public class SQLObject {


    Connection connection;

    public SQLObject(){

    }

    /**
     * Creates the new connection object and starts the thread.
     *
     * @param URL
     * @param contents
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public String ExecuteQuery(String URL, String contents) throws IOException, InterruptedException
    {
        connection = new Connection(URL, contents);
        connection.start();
        connection.join();
        return connection.SQLResult;
    }

}

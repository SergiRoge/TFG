package Connection;

import android.util.Log;

import com.google.firebase.auth.api.model.StringList;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import Auxiliar.ErrorCode;

import static Auxiliar.Auxiliar.*;
import static Auxiliar.Constants.*;

/**
 * Created by Llango on 16/10/2016.
 */

public class Connection extends Thread{


    String SQLResult;
    URL strURL;
    HttpURLConnection httpsURLConnection;
    OutputStream OS;
    InputStream IS;
    BufferedWriter bufferedWriter;
    BufferedReader bufferedReader;

    //ErrorCode error;


    String URL;
    String contents;
    
    public Connection(String pstrURL, String pstrContents) throws IOException
    {

        this.strURL = new URL(pstrURL);
        contents = pstrContents;
        httpsURLConnection = (HttpURLConnection)strURL.openConnection();
    }


    public void execute() throws IOException
    {
        httpsURLConnection.setReadTimeout(10000);
        httpsURLConnection.setConnectTimeout(15000);
        httpsURLConnection.setRequestMethod("POST");
        httpsURLConnection.setDoInput(true);
        httpsURLConnection.setDoOutput(true);
        httpsURLConnection.setRequestProperty("Content-Length", Integer.toString(contents.length()));
        httpsURLConnection.connect();



        OS = httpsURLConnection.getOutputStream();
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

        bufferedWriter.write(contents);
        bufferedWriter.flush();
        bufferedWriter.close();
        OS.close();

        IS = httpsURLConnection.getInputStream();

        SQLResult = convertinputStreamToString(IS);


        IS.close();

        httpsURLConnection.disconnect();

    }


    @Override
    public void run()
    {
        try
        {
            execute();
        }
        catch (IOException e)
        {
            setErrorCode(IO_EXCEPTION);
        }

    }
}

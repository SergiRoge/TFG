package Connection;

import android.util.Log;

import com.google.firebase.auth.api.model.StringList;

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

import static Auxiliar.Auxiliar.*;

/**
 * Created by Llango on 16/10/2016.
 */

public class Connection implements Runnable {

    URL strURL;
    HttpURLConnection httpsURLConnection;
    OutputStream OS;
    InputStream IS;
    BufferedWriter bufferedWriter;
    BufferedReader bufferedReader;


    String URL;
    String contents;
    
    public Connection(String pstrURL, String pstrContents) throws IOException {
        this.strURL = new URL(pstrURL);
        contents = pstrContents;
        httpsURLConnection = (HttpURLConnection)strURL.openConnection();
    }


    /**
     *  Method that will create a thread with the parameters username and password
     *
     * @param pstrUserName  String  The username of the user
     * @param pstrPassword  String  The password of the user
     * @return
     */
    public int checkUser(String pstrUserName, String pstrPassword)
    {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(pstrUserName);
        arrayList.add(pstrPassword);

        String METHOD_NAME = "price";
        String URL = "10.0.2.2/service.php";
        String SOAP_ACTION = "http://www.{{mywebservicetest}}.com/demourn:demo/price";
        String NAMESPACE = "http://www.{{mywebservicetest}}.com/demourn:demo";



        ConnectionThread connectionThread = new ConnectionThread("checkUser",arrayList);
        connectionThread.run();

        return 0;
    }

    public void save() throws MalformedURLException {


        Log.d("Length : ",""+Integer.toString(contents.length()));
        Log.d("URL", " - " + URL);
        Log.d("contents", " - " + contents);
        try {


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
            String popo = convertinputStreamToString(IS);
            Log.d("POPO","------------------"+popo);
            IS.close();


            httpsURLConnection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*

        Realizar conexion
         */


        // si la conexion fue bien, guarda


        //si no fue bien, retorna error




    }


    @Override
    public void run() {

        try {
            save();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}

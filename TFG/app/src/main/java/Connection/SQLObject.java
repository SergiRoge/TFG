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

/**
 * Created by Sergi on 13/03/2016.
 */
public class SQLObject {

    /*
        Todas las propiedades necesarias para la conexion


     */
    String date;

    Connection Conn;

    public SQLObject(){
        /*

            Inicializar todo lo necesario para acceder a base de datos
         */
    }
    public int save(String URL, String contents) throws IOException
    {

        (new Thread(new Connection(URL, contents))).start();
        return 1;

    }
}

package tfg.lostandfound;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import Connection.SQLObject;

public class NewCoordActivity extends FragmentActivity implements OnMapReadyCallback {

    /**
     * All the components needed
     */
    private GoogleMap mMap;
    private TextView txtXCoord;
    private TextView txtYCoord;
    private Button btnSave;
    private String strJSON;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_coord);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        final Bundle bundle = getIntent().getExtras();
        final String strButtonPressed = bundle.getString("button");



        txtXCoord = (TextView) findViewById(R.id.txt_x_coord);
        txtYCoord = (TextView) findViewById(R.id.txt_y_coord);
        btnSave = (Button) findViewById(R.id.btn_save_coord);

        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {

                //Getting the button we came from


                Intent returnIntent = new Intent();
                returnIntent.putExtra("XCoord", ""+txtXCoord.getText().toString());
                returnIntent.putExtra("YCoord", ""+txtYCoord.getText().toString());
                returnIntent.putExtra("button", ""+strButtonPressed);

                setResult(1,returnIntent);
                finish();

            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        LatLng sydney = new LatLng(41.386493, 2.164129);
       // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        txtXCoord.setText("41.386493");
        txtYCoord.setText("2.164129");

        //TODO Buscar otra manera, pero de momento me sirve
        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            public void onCameraChange(CameraPosition arg0) {
                mMap.clear();

                final MarkerOptions marker = new MarkerOptions().position(arg0.target);

                //marker.getPosition();

                Log.d("posicion : ", "---->" + marker.getPosition().latitude);



                //This code below gets the full adress of the position
                //It will be an imrpovement
                /*
                Thread thread = new Thread()
                {
                    public void run()
                    {
                        try
                        {
                            URL url = new URL(
                                    "http://maps.googleapis.com/maps/api/geocode/json?latlng="+ marker.getPosition().latitude +"," + marker.getPosition().longitude);
                            Log.d("URL ", "-> " + url.toString());

                            URLConnection con = url.openConnection();
                            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                            strJSON = " ";
                            strJSON  += br.readLine();
                            String a = "";
                            boolean found = false;
                            while (strJSON != null && !found)
                            {

                                a = br.readLine();
                                Log.d("A : ", " -> " + a);
                                if(a.contains("formatted_address"))
                                {
                                    strJSON = a;
                                    found = true;
                                    Log.d("encontro : ", " -> " + a);
                                }

                            }

                            //Log.d("JSON ", "-> "+ strJSON);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }



                            String direccion = strJSON.split(":")[1];
                            strJSON = direccion;
                            Log.d("adress ","tostring   " + direccion);




                    }
                };

                thread.start();

                */



                Double x = (double)Math.round(marker.getPosition().latitude * 10000d) / 10000d;
                Double y = (double)Math.round(marker.getPosition().longitude * 10000d) / 10000d;
                txtXCoord.setText(""+x);
                txtYCoord.setText(""+y);

                mMap.addMarker(marker);

            }
        });
        mMap.addMarker(new MarkerOptions().position(sydney)
                .title("Your Title")
                .snippet("Please move the marker if needed.")
                .draggable(true));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));




    }
}

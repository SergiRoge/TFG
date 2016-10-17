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

public class NewCoordActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    TextView txtXCoord;
    TextView txtYCoord;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_coord);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Getting the button we came from
        final Bundle bundle = getIntent().getExtras();
        final String strButtonPressed = bundle.getString("button");



        txtXCoord = (TextView) findViewById(R.id.txt_x_coord);
        txtYCoord = (TextView) findViewById(R.id.txt_y_coord);
        btnSave = (Button) findViewById(R.id.btn_save_coord);

        /*
            Listener for Save button
         */
        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                //Launch Register User Activity

                /*Intent I = new Intent(NewCoordActivity.this, CoordsActivity.class);
                I.putExtra("XCoord", ""+txtXCoord.getText().toString());
                I.putExtra("YCoord", ""+txtYCoord.getText().toString());
                I.putExtra("button", ""+strButtonPressed);
                Log.d("Boton en new cord :", ""+strButtonPressed );*/

                Intent returnIntent = new Intent();
                returnIntent.putExtra("XCoord", ""+txtXCoord.getText().toString());
                returnIntent.putExtra("YCoord", ""+txtYCoord.getText().toString());
                returnIntent.putExtra("button", ""+strButtonPressed);
                Log.d("Boton en new cord :", ""+strButtonPressed );
                setResult(CoordsActivity.RESULT_OK,returnIntent);
                finish();


                //startActivity(I);
            }
        });

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
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

                MarkerOptions marker = new MarkerOptions().position(arg0.target);

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

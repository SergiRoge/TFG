package tfg.lostandfound;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import Classes.Coordinate;
import Classes.Item;

import static Auxiliar.Auxiliar.showMessageError;
import static Auxiliar.Constants.*;


/**
 * Class CoordsActivity
 *
 * The java class related to activity_coords.xml
 *
 * It manages all the behaviour of the widgets on the screen.
 *
 */
public class CoordsActivity extends AppCompatActivity
{
    private Item item;

    private Button btnnew1;
    private Button btnnew2;
    private Button btnnew3;
    private Button btnNext;

    private String strItemTypeStatus;


    private Coordinate coordinate1;
    private Coordinate coordinate2;
    private Coordinate coordinate3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d("OnCreate","OnCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coords);

        //We get the item through the intent
        item = (Item) getIntent().getSerializableExtra("Item");

        /**
         * Calling the methods that initialize the components and the listeners
         */
        initializeComponents();
        initializeListeners();
    }

    /**
     *  Method that initialize all components that exist in the activity
     */
    private void initializeComponents()
    {
        coordinate1 = null;
        coordinate2 = null;
        coordinate3 = null;

        btnnew1 = (Button) findViewById(R.id.btn_New1);
        btnnew2 = (Button) findViewById(R.id.btn_New2);
        btnnew3 = (Button) findViewById(R.id.btn_New3);
        btnNext = (Button) findViewById(R.id.btn_next);

        try
        {
            //If the registered item is Found, the label of the button is Save
            //(By default is Next)
            if(item.getStrFoundLost().equals("Found"))
            {
                btnNext.setText("Save");
            }
        }
        catch(Exception e)
        {
            Log.d("Exception","->" + e.toString());
        }

    }
    /**
     *  Method that initialize all the listeners related to the components
     */
    private void initializeListeners()
    {
         /*
            Listener for the first button that adds a coordinate
         */
        btnnew1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                manageNewCoordinate(BUTTON_1);
            }
        });
        /*
            Listener for the second button that adds a coordinate
         */
        btnnew2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                manageNewCoordinate(BUTTON_2);
            }
        });
        /*
            Listener for the third button that adds a coordinate
         */
        btnnew3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                manageNewCoordinate(BUTTON_3);
            }
        });

        /*
            Listener for Next button leading the user to ExtraInfoActivity
         */
        btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {

                if (coordinate1 != null)
                {
                    item.addCoordinateToArray(coordinate1);
                }
                if (coordinate2 != null)
                {
                    item.addCoordinateToArray(coordinate2);
                }
                if (coordinate3 != null)
                {
                    item.addCoordinateToArray(coordinate3);
                }


                //If we registered a lost item, the next screen is the ExtraInfoActivity
                if(item.getStrFoundLost().equals("Lost"))
                {
                    Intent I = new Intent(CoordsActivity.this, ExtraInfoActivity.class);
                    I.putExtra("Item", item);
                    startActivity(I);
                }
                //If not, we go to MainActivity
                else if(item.getStrFoundLost().equals("Found"))
                {
                    showMessageError(CoordsActivity.this,ITEM_REGISTERED_ALERT);

                    Intent I = new Intent(CoordsActivity.this, MainActivity.class);
                    I.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    I.putExtra("FROM"," ");

                    I.putExtra("Item", item);
                    finishAffinity();
                    startActivity(I);
                }

            }
        });

    }

    /**
     *  Method that manage the Intent when pressing any of the buttons to add a new coordinate
     *
     * @param pButtonPressed    values: 1, 2 or 3. The values corresponding to the first, second and
     *                                            third button
     *
     */
    private void manageNewCoordinate(int pButtonPressed)
    {
        Intent I = new Intent(CoordsActivity.this,NewCoordActivity.class);
        I.putExtra("button",""+pButtonPressed);
        startActivityForResult(I,1);
    }


    /**
     *  Method that is called when coming back from the NewCoordActivity, updates the screen with
     *  the new coordinate.
     *
     * @param strBtnPressed values: 1, 2 or 3. Corresponding to the buttons.
     * @param xCoord        The value of the X coord.
     * @param yCoord        The values of the Y coord.
     */
    private void updateCoords(String strBtnPressed, String xCoord, String yCoord)
    {
        TextView txtCoordX = null;
        TextView txtCoordY = null;

        switch (Integer.parseInt(strBtnPressed))
        {
            //Depending by the button pressed, it
            case BUTTON_1:
                txtCoordX = (TextView) findViewById(R.id.txtCoordX1);
                txtCoordY = (TextView) findViewById(R.id.txtCoordY1);
                coordinate1 = new Coordinate(Double.parseDouble(xCoord),Double.parseDouble(yCoord));
                break;

            case BUTTON_2:
                txtCoordX = (TextView) findViewById(R.id.txtCoordX2);
                txtCoordY = (TextView) findViewById(R.id.txtCoordY2);
                coordinate2 = new Coordinate(Double.parseDouble(xCoord),Double.parseDouble(yCoord));
                break;

            case BUTTON_3:
                txtCoordX = (TextView) findViewById(R.id.txtCoordX3);
                txtCoordY = (TextView) findViewById(R.id.txtCoordY3);
                coordinate3 = new Coordinate(Double.parseDouble(xCoord),Double.parseDouble(yCoord));
                break;

            default:
                break;
        }
        txtCoordX.setText(xCoord);
        txtCoordY.setText(yCoord);
    }

    /**
     *  The callback called when coming from the NewCoordActivity.
     *
     * @param requestCode   The code received from the NewCoordActivity.
     * @param resultCode    The code received from the NewCoordActivity.
     * @param data          The data to be retreived.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        String strBtnPressed=data.getStringExtra("button");
        String XCoord=data.getStringExtra("XCoord");
        String YCoord=data.getStringExtra("YCoord");
        updateCoords(strBtnPressed,XCoord,YCoord);

        //TODO mirar porque esto ahora no funciona
        /*
        if (requestCode == 1) {
            if(resultCode == CoordsActivity.RESULT_OK){
                String strBtnPressed=data.getStringExtra("button");
                String XCoord=data.getStringExtra("XCoord");
                String YCoord=data.getStringExtra("YCoord");
                updateCoords(strBtnPressed,XCoord,YCoord);
            }
            if (resultCode == CoordsActivity.RESULT_CANCELED) {

            }
        }*/
    }
}

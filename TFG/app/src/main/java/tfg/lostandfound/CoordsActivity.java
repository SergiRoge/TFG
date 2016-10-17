package tfg.lostandfound;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CoordsActivity extends AppCompatActivity {



    LinearLayout linearLayout1;
    LinearLayout linearLayout2;
    LinearLayout linearLayout3;

    Button btnnew1;
    Button btnnew2;
    Button btnnew3;
    Button btnNext;

    String strFromActivity = "FoundItem";

    private final static int OK = 1;

    private final static int BUTTON_1 = 1;
    private final static int BUTTON_2 = 2;
    private final static int BUTTON_3 = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("OnCreate","OnCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coords);
        final Bundle bundle = getIntent().getExtras();

        String getExtraActivityFrom = bundle.getString("Activity");

        try
        {
            if(getExtraActivityFrom.equals("LostItem")) {
                strFromActivity = "LostItem";
            }

        }
        catch(Exception e)
        {

        }







        linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout);
        linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        linearLayout3 = (LinearLayout) findViewById(R.id.linearLayout3);

        //view.VISIBLE
        //linearLayout2.setVisibility(View.GONE);
        //linearLayout3.setVisibility(View.GONE);

        btnnew1 = (Button) findViewById(R.id.btn_New1);
        btnnew2 = (Button) findViewById(R.id.btn_New2);
        btnnew3 = (Button) findViewById(R.id.btn_New3);
        btnNext = (Button) findViewById(R.id.btn_next);

        /*
            Listener for New 1 button
         */
        btnnew1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent I = new Intent(CoordsActivity.this,NewCoordActivity.class);
                I.putExtra("button",""+BUTTON_1);
                startActivityForResult(I,OK);
            }
        });

        /*
            Listener for New 2 button
         */
        btnnew2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent I = new Intent(CoordsActivity.this,NewCoordActivity.class);
                I.putExtra("button",""+BUTTON_2);
                startActivityForResult(I,OK);
            }
        });



        /*
            Listener for New 3 button
         */
        btnnew3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent I = new Intent(CoordsActivity.this,NewCoordActivity.class);
                I.putExtra("button",""+BUTTON_3);
                startActivityForResult(I,OK);
            }
        });

        /*
            Listener for Next button leading the user to ExtraInfoActivity
         */
        btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                //Launch Register User Activity


                if(strFromActivity.equals("LostItem"))
                {
                    Intent I = new Intent(CoordsActivity.this, ExtraInfoActivity.class);
                    startActivity(I);
                }
                else if(strFromActivity.equals("FoundItem"))
                {
                    Intent I = new Intent(CoordsActivity.this, MainActivity.class);
                    I.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(I);
                }

            }
        });

    }


    /**
     *
     * @param strBtnPressed
     * @param xCoord
     * @param yCoord
     */
    private void updateCoords(String strBtnPressed, String xCoord, String yCoord)
    {
        TextView txtCoordX = null;
        TextView txtCoordY = null;

        switch (Integer.parseInt(strBtnPressed))
        {
            case BUTTON_1:
                txtCoordX = (TextView) findViewById(R.id.txtCoordX1);
                txtCoordY = (TextView) findViewById(R.id.txtCoordY1);
                break;
            case BUTTON_2:
                txtCoordX = (TextView) findViewById(R.id.txtCoordX2);
                txtCoordY = (TextView) findViewById(R.id.txtCoordY2);
                break;
            case BUTTON_3:
                txtCoordX = (TextView) findViewById(R.id.txtCoordX3);
                txtCoordY = (TextView) findViewById(R.id.txtCoordY3);
                break;
            default:
                break;
        }
        txtCoordX.setText(xCoord);
        txtCoordY.setText(yCoord);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d("onActivityResult","onActivityResult");
        if (requestCode == 1) {
            if(resultCode == CoordsActivity.RESULT_OK){
                String strBtnPressed=data.getStringExtra("button");
                Log.d("Button ",strBtnPressed+"");
                String XCoord=data.getStringExtra("XCoord");
                String YCoord=data.getStringExtra("YCoord");
                updateCoords(strBtnPressed,XCoord,YCoord);
            }
            if (resultCode == CoordsActivity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult
}

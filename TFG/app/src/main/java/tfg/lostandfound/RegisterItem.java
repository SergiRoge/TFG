package tfg.lostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.Serializable;
import java.util.ArrayList;

import Classes.Item;
import Controller.Controller;

public class RegisterItem extends AppCompatActivity {

    /**
     * All the components needed
     */
    private Item item = null;
    private Controller controller;
    private EditText txtLost;
    private EditText txtColor;
    private EditText txtBrand;
    private EditText txtMaterial;
    private Button btnNext;
    private Spinner spinnerWhen;
    private String strItemTypeStatus;   //Found or Lost

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_item);

        controller = new Controller();

        final Bundle bundle = getIntent().getExtras();
        strItemTypeStatus = bundle.getString("ItemType");



        initializeComponents();
        initializeListeners();
    }




    /**
     *  Method that initialize all components that exist in the activity
     */
    private void initializeComponents()
    {
        txtLost = (EditText) findViewById(R.id.txt_ItemType);
        txtColor = (EditText) findViewById(R.id.txt_ItemColor);
        txtBrand = (EditText) findViewById(R.id.txt_ItemBrand);
        txtMaterial = (EditText) findViewById(R.id.txt_ItemMaterial);
        btnNext = (Button) findViewById(R.id.btn_Next);

        ArrayList<String> spinnerArray =  new ArrayList<String>();

        spinnerArray.add(0,"When did you lost it ?");
        spinnerArray.add(1,"Today");
        spinnerArray.add(2,"Yesterday");
        spinnerArray.add(3,"2 days ago");
        spinnerArray.add(4,"1 week ago");
        spinnerArray.add(5,"1 month ago");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWhen = (Spinner) findViewById(R.id.spin_when);
        spinnerWhen.setAdapter(adapter);
    }

    /**
     *  Method that initialize all the listeners related to the components
     */
    private void initializeListeners()
    {
        /*
            Listener for Next button
         */
        btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {

                String strItemType = txtLost.getText().toString();
                String strItemColor = txtColor.getText().toString();
                String strItemBrand = txtBrand.getText().toString();
                String strItemMaterial = txtMaterial.getText().toString();
                int intOption = spinnerWhen.getSelectedItemPosition();

                item = controller.createItem(strItemType, strItemColor, strItemBrand,
                        strItemMaterial, intOption, strItemTypeStatus);


                Intent I = new Intent(RegisterItem.this,CoordsActivity.class);
                I.putExtra("Item", (Serializable) item);
                startActivity(I);
            }
        });
    }


}

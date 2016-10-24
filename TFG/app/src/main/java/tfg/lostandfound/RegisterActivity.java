package tfg.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import Auxiliar.ErrorCode;
import Classes.User;
import Controller.Controller;

import static Auxiliar.Auxiliar.*;
import static Auxiliar.Constants.*;


public class RegisterActivity extends AppCompatActivity {

    /**
     * All the components needed
     */

    Button btnSave;
    EditText txtUsername;
    EditText txtPassword;
    EditText txtEmail;

    Controller controller;

    ErrorCode error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initializeComponents();
        initializeListeners();

    }

    /**
     *  Method that initialize all the listeners related to the components
     */
    public void initializeListeners()
    {
         /*
            Listener for Save button
         */
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                txtUsername = (EditText) findViewById(R.id.txt_username);
                txtPassword = (EditText) findViewById(R.id.txt_password);
                txtEmail = (EditText) findViewById(R.id.txt_email);

                String strTxtUsername = txtUsername.getText().toString();
                String strTxtPassword = txtPassword.getText().toString();
                String strTxtEmail = txtEmail.getText().toString();


                try
                {
                    User user = new User(strTxtEmail, strTxtUsername, strTxtPassword);
                    int error = user.save();
                    if(error != OK)
                    {
                        showMessageError(RegisterActivity.this,error);
                    }
                    else
                    {
                        Intent I = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(I);
                    }


                }

                catch (IOException e)
                {
                    showMessageError(RegisterActivity.this,IO_EXCEPTION);
                }
                catch (InterruptedException e)
                {
                    showMessageError(RegisterActivity.this,IO_EXCEPTION);
                }


            }
        });
    }
    /**
     *  Method that initialize all components that exist in the activity
     */
    public void initializeComponents()
    {
        controller = new Controller();
        btnSave = (Button) findViewById(R.id.btn_save);
        error = new ErrorCode();

    }
}

package tfg.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

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

                //int intError = controller.checkIfUserExists(strTxtEmail, strTxtPassword);

                //If an OK is returned, the user already exists, an error is shown
                int intError = 231231;
                if(intError == OK)
                {
                    showMessageError(RegisterActivity.this,intError);
                }
                //If something different than Server Error is returned, the user does not exist and we generate a new one with the provided data.
                else if(intError != SERVER_ERROR)
                {
                    User user = new User(strTxtEmail, strTxtUsername, strTxtPassword);
                    try
                    {
                        user.save();
                        //TODO lanzar algo para que indicar al usuario que se está creando su usuario
                    }
                    catch (IOException e)
                    {
                        showMessageError(RegisterActivity.this,IO_EXCEPTION);

                        //showMessageError();
                    }

                    if(intError == OK)
                    {
                        //Launch Register User Activity
                        Intent I = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(I);
                    }
                    else
                    {
                        showMessageError(RegisterActivity.this,intError);
                    }
                }
                else
                {
                    showMessageError(RegisterActivity.this,intError);
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
    }
}

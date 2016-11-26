package tfg.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Auxiliar.ErrorCode;
import Classes.User;

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
    User user;
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

                String strTxtUsername = txtUsername.getText().toString();
                String strTxtPassword = txtPassword.getText().toString();
                String strTxtEmail = txtEmail.getText().toString();

                if(verifyNormalField(strTxtUsername) && verifyNormalField(strTxtPassword) && verifyEmailField(strTxtEmail))
                {
                    int error = OK;
                    try
                    {
                        user = new User(strTxtEmail, strTxtUsername, strTxtPassword);
                        error = user.save();

                        if(error == 1)
                        {
                            showMessageError(RegisterActivity.this,REGISTRATION_SUCCESFULLY);
                            Intent I = new Intent(RegisterActivity.this, MainActivity.class);
                            I.putExtra("User", (Serializable) user);
                            startActivity(I);
                        }
                        else
                        {
                            showMessageError(RegisterActivity.this,error);
                        }
                    }

                    catch (IOException e)
                    {
                        showMessageError(RegisterActivity.this,IO_EXCEPTION);
                    }
                    catch (InterruptedException e)
                    {
                        showMessageError(RegisterActivity.this,INTERRUPTION_EXCEPTION);
                    }
                }
                else
                {
                    showMessageError(RegisterActivity.this,INCORRECT_DATA);
                }




            }
        });
    }
    /**
     *  Method that initialize all components that exist in the activity
     */
    public void initializeComponents()
    {
        btnSave = (Button) findViewById(R.id.btn_save);
        txtUsername = (EditText) findViewById(R.id.txt_username);
        txtPassword = (EditText) findViewById(R.id.txt_password);
        txtEmail = (EditText) findViewById(R.id.txt_email);
    }
}

package tfg.lostandfound;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;

import java.io.IOException;
import java.io.Serializable;

import Classes.User;
import Controller.Controller;

import static Auxiliar.Auxiliar.*;
import static Auxiliar.Constants.*;


public class LogInActivity extends AppCompatActivity {

    /**
     * All the components needed
     */
    Button btnRegister;
    Button btnLogin;
    Controller controller;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        initializeComponents();
        initializeListeners();
    }

    /**
     *  Method that initialize all components that exist in the activity
     */
    public void initializeComponents()
    {
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnLogin = (Button) findViewById(R.id.btn_login);
        controller = new Controller();
    }

    /**
     *  Method that initialize all the listeners related to the components
     */
    public void initializeListeners()
    {
        /**
         *  Listener for Login button
         */
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {

                EditText txtEmail = (EditText) findViewById(R.id.txt_email);
                EditText txtPassword = (EditText) findViewById(R.id.txt_password);

                String strTxtEmail = txtEmail.getText().toString();
                String strTxtPassword = txtPassword.getText().toString();

                //Call to controller to check if user exists or not


                strTxtEmail = "aaaa@gmail.com";
                strTxtPassword = "aaaa";

                int error = OK;
                if(verifyNormalField(strTxtPassword) && verifyEmailField(strTxtEmail))
                {
                    User user = new User(strTxtEmail, strTxtPassword);
                    try
                    {

                        String strUserName = user.checkEmailPasswprdMatches();
                        user.setStrUserName(strUserName);
                        //the result of the query is a COUNT(*), if the return is 1, there have been
                        //a match with the email and password given, if the return is 0, there have
                        //not been matching. Other results are managed by the else statement.
                        if(strUserName != null && !strUserName.isEmpty())
                        {
                            Intent I = new Intent(LogInActivity.this, MainActivity.class);
                            I.putExtra("User", (Serializable) user);
                            startActivity(I);
                        }
                        else
                        {
                            showMessageError(LogInActivity.this,INCORRECT_DATA);
                        }
                    }
                    catch (IOException e)
                    {
                        showMessageError(LogInActivity.this,IO_EXCEPTION);
                    }
                    catch (InterruptedException e)
                    {
                        showMessageError(LogInActivity.this,INTERRUPTION_EXCEPTION);
                    }
                    catch (JSONException e)
                    {
                        showMessageError(LogInActivity.this,JSON_EXCEPTION);
                    }


                }

            }
        });


        /**
         * Listener for Register button
         */
        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                //Launch Register User Activity
                Intent I = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivity(I);
            }
        });
    }
}

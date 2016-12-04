
package tfg.lostandfound;


import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;

import java.io.IOException;
import java.io.Serializable;

import Classes.User;

import static Auxiliar.Auxiliar.*;
import static Auxiliar.Constants.*;

public class LogInActivity extends AppCompatActivity {

    /**
     * All the components needed
     */
    Button btnRegister;
    Button btnLogin;
    User user;

    String strEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Log.d("Oncreate","sa");
        initializeComponents();
        initializeListeners();






    }
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(LogInActivity.this.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void onResume()
    {
        super.onResume();
        if( isMyServiceRunning(MatchingService.class))
        {
            Intent I = new Intent(LogInActivity.this, MainActivity.class);
            startActivity(I);
            finish();
        }

    }

    /**
     *  Method that initialize all components that exist in the activity
     */
    public void initializeComponents()
    {
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnLogin = (Button) findViewById(R.id.btn_login);
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
                    try
                    {
                        user = new User(strTxtEmail, strTxtPassword);
                        error = user.retrieveUserData();


                        if(error == OK)
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
                        Log.d("IOException ","-->" + e.toString());
                        showMessageError(LogInActivity.this,IO_EXCEPTION);
                    }
                    catch (InterruptedException e)
                    {
                        Log.d("InterruptedException ","-->" + e.toString());
                        showMessageError(LogInActivity.this,INTERRUPTION_EXCEPTION);
                    }
                    catch (JSONException e)
                    {
                        Log.d("JSONException ","-->" + e.toString());
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
                //launchNotification();
                Intent I = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivity(I);
            }
        });
    }





}

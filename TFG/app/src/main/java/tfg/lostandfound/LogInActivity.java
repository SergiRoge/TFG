package tfg.lostandfound;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import Controller.Controller;

import static Auxiliar.Auxiliar.*;
import static Auxiliar.Constants.*;


public class LogInActivity extends AppCompatActivity {


    Button btnRegister;
    Button btnLogin;
    Controller controller;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);



        btnRegister = (Button) findViewById(R.id.btn_register);
        btnLogin = (Button) findViewById(R.id.btn_login);
        controller = new Controller();


        /*
            Listener for Login button
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

                int intError = controller.checkIfUserExists(strTxtEmail, strTxtPassword);


                //intError = SERVER_ERROR;
                //intError = INCORRECT_USER_PASSWORD;
                if(intError == OK)
                {
                    Intent I = new Intent(LogInActivity.this, MainActivity.class);
                    startActivity(I);
                }
                else
                {
                    showMessageError(LogInActivity.this,intError);
                }
            }
        });


        /*
            Listener for Register button
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

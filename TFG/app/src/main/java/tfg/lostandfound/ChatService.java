package tfg.lostandfound;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import Classes.User;
import Connection.SQLObject;

import static Auxiliar.Constants.URL_CHECK_FOUND_ITEMS;
import static Auxiliar.Constants.URL_CHECK_NEW_CHATS;

public class ChatService extends Service
{
    User user;
    ChatTask myTask;

    public ChatService(User u)
    {
        user = u;
    }
    public ChatService()
    {

    }
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("CHAT SERVICE --------->","onBind");
        // TODO: Return the communication channel to the service.
        user = (User) intent.getSerializableExtra("User");

        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("CHAT SERVICE --------->","onStartCommand");
        user = (User) intent.getSerializableExtra("User");



        myTask.tUser = user;

        //myTask.execute();
        myTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");


        return super.onStartCommand(intent, flags, startId);
        //return 1;
    }

    @Override
    public void onCreate() {
        Log.d("CHAT SERVICE --------->","onCreate");
        super.onCreate();


        myTask = new ChatTask(user);

    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();



    }
    private class ChatTask extends AsyncTask<String, String, String> {

        private boolean cent;
        private User tUser;


        public ChatTask(User pUser)
        {
            tUser = pUser;
        }
        @Override
        protected void onPreExecute()
        {
            Log.d("CHAT SERVICE TASK ->","onPreExecute");

            super.onPreExecute();


            cent = true;
        }

        @Override
        protected String doInBackground(String... params) {
//            Toast.makeText(getApplicationContext(), "doInBackground", Toast.LENGTH_SHORT).show();
            Log.d("CHAT SERVICE TASK ->","doInBackground");
            while (cent)
            {
                try
                {
                    Thread.sleep(5000);
                    checkNewChat();
                    publishProgress();
                    // Stop 5s

                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            return null;
        }


        private void checkNewChat()
        {
            Log.d("CHAT SERVICE: ", "--> checkNewChat ");
            SQLObject sql = new SQLObject();

            String content = "email="+ tUser.getStrEmail();


/*

            try
            {
                //String strReturn = sql.ExecuteQuery(URL_CHECK_NEW_CHATS, "");

                //Log.d("JSON DEVUELTO : ", "--> " + strReturn );
                //JSONObject jsonObject = new JSONObject(strReturn);


                //String IDItemLost = jsonObject.getString("IDItemLost");


            }/*
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
*/
        }


        @Override
        protected void onProgressUpdate(String... values) {
            Toast.makeText(getApplicationContext(), "Chat Service", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            cent = false;
        }


    }

}

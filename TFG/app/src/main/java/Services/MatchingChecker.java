package Services;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MatchingChecker extends Service {


    public static final String NEW_DATA = "com.imaginagroup.MyService.NEW_DATA";
    private static final String stringUrl = "http://www.imaginaformacion.com/recursos/rand.php";
    private static final int updateInterval = 1000;
    private Timer updateTimer;
    private TimerTask doRefresh;
    private String data;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Aquí las operaciones que queremos realizar
        Log.d("-> ", " HOLA");
        return Service.START_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent)
    {
        //Para mantener comunicación con el Service se debe devolver un IBinder
        Log.d("-> ", " HOLA 2");
        return null;
    }
}

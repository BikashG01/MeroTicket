import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by bikas on 7/18/2017.
 */

public class VolleyClass {
    private static VolleyClass mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;
    private VolleyClass(Context context){
        mCtx=context;
        requestQueue=getRequestQueue();
    }

    public RequestQueue getRequestQueue()
    {
        if (requestQueue==null){
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }
    public static synchronized VolleyClass getInstance(Context context){
        if (mInstance==null){
            mInstance= new VolleyClass(context);

        }
        return mInstance;
    }
    public <T> void addToRequestque(Request<T> request){
        requestQueue.add(request);
    }
}

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.infobrain.meroticket.Networks.CheckConnection;
import com.infobrain.meroticket.R;
import com.infobrain.meroticket.SqliteDB.DBHelper;
import com.infobrain.meroticket.SqliteDB.SQLiteOperations;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Timer;
import java.util.TimerTask;


public class SplashSceen extends Activity {
    private final int SPLASH_DISPLAY_LENGTH = 3500;
    String URL, URL2;
    DBHelper dbObject;
    View view;
    private SharedPreferences userPref;
    private String User_Name, PASS, isLogin;

    private static final String DATABASE_NAME = "locationData.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_sceen);
        userPref = getSharedPreferences("LOGIN", 0);
        isLogin = userPref.getString("isLogin", "");
        final Singleton c_code = (Singleton) getApplicationContext();

        //Set name and email in global/application context
        c_code.setC_code("1001");
        String com_code = c_code.getC_code();


        dbObject = new DBHelper(this);
        URL = "..............." + com_code;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                checkInternetCon();

            }
        }, SPLASH_DISPLAY_LENGTH);
    }


    public void checkInternetCon() {
        if (!CheckConnection.checkInternetConnection(this)) {
            AlertMessage();
            //Toast.makeText(getApplicationContext(), "No Internet!", Toast.LENGTH_SHORT).show();
        } else {
            SaveCityList();
            if (isLogin.equals("0")) {
                checkLogin();
            } else {
                Intent mainIntent = new Intent(SplashSceen.this, MainActivity.class);
                SplashSceen.this.startActivity(mainIntent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                SplashSceen.this.finish();
            }


        }
    }
//ALERT MESSAGE FOR SHOWING CONNECTION PROBLEM
    public void AlertMessage() {
        final AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("No Internet Connection");
        builder.setCancelable(false)
                .setPositiveButton(R.string.TryAgain, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        checkInternetCon();


                    }
                })
                .setNegativeButton(R.string.Exit, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .show();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            // notification is pulled up
        } else {
            checkInternetCon();
        }
        super.onWindowFocusChanged(hasFocus);
    }

//DOWNLOAD CITY LIST FROM SERVER
    private void SaveCityList() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray check_response = jsonObject.getJSONArray("Table1");
                    JSONObject check = check_response.getJSONObject(0);
                    Integer checkresponse = check.getInt("Response_Code");
                    DBHelper hlpr = new DBHelper(SplashSceen.this);
                    SQLiteDatabase db = hlpr.getWritableDatabase();
                    SQLiteOperations sqlte = new SQLiteOperations(hlpr, db);
                    if (checkresponse == 100) {

                        JSONArray array = jsonObject.getJSONArray("Table");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject contain = array.getJSONObject(i);
                            String location_name = contain.getString("City_Name");
                            Log.e("DAATA ADDED", location_name);
                            sqlte.addLocation(location_name);

                        }
                        db.close();
                    }
                } catch (JSONException e) {
                    Log.e("ERROR:", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("NETWORK PROBLEM", error.getMessage());

            }

        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);

    }

//CHECK LOGIN CREDITIONAL
    public void checkLogin() {
        final Singleton c_code = (Singleton) getApplicationContext();
        try {
            User_Name = userPref.getString("user_name", "");
            PASS = userPref.getString("user_pass", "");

        } catch (Exception ex) {
            Log.e("NULL ERROR", ex.getMessage());

        }
        URL = "" + User_Name + "&Password=" + PASS + "&C_Code=" + c_code.getC_code();
        URL2 = URL.replaceAll(" ", "+" + "%20" + "+");
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, URL2, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("Table1");
                    JSONObject contain = array.getJSONObject(0);
                    String code = contain.getString("Response_Code");

                    Log.e("CODE", code);
                    if (code.equals("100")) {
                        Intent mainIntent = new Intent(SplashSceen.this, MainActivity.class);
                        SplashSceen.this.startActivity(mainIntent);
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                        SplashSceen.this.finish();
                        Toast.makeText(getApplicationContext(), "Namaste " + User_Name, Toast.LENGTH_SHORT).show();
                    } else if (code.equals("103")) {
                        Intent mainIntent = new Intent(SplashSceen.this, MainActivity.class);
                        SplashSceen.this.startActivity(mainIntent);
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                        SplashSceen.this.finish();
                        Toast.makeText(getApplicationContext(), "No user found.", Toast.LENGTH_SHORT).show();
                    } else if (code.equals("101")) {
                        Intent mainIntent = new Intent(SplashSceen.this, MainActivity.class);
                        SplashSceen.this.startActivity(mainIntent);
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                        SplashSceen.this.finish();
                        Toast.makeText(getApplicationContext(), "Incorrect username or password.", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Log.e("ERROR", e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }


}




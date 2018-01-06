import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.infobrain.meroticket.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Timer;
import java.util.TimerTask;

public class Late_Login extends AppCompatActivity {
    Button late_btn;
    private EditText userName;
    private EditText userPass;
    private String URL, URL2;
    private ProgressDialog progressDialog;
    private String user_Name, user_Pass;
    private SharedPreferences userPref;
    private SharedPreferences.Editor editor;
    private String USERNAME, USERTYPE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_late__login);
        late_btn = findViewById(R.id.late_loginbtn);
        userName = findViewById(R.id.firstnameEditText);
        userPass = findViewById(R.id.PasswordEditText);
        userPref = this.getSharedPreferences("LOGIN", 0);

        late_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog = new ProgressDialog(Late_Login.this);
                progressDialog.setMessage("Please Wait...");
                progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(true);
                progressDialog.show();
                final Singleton c_code = (Singleton) getApplicationContext();
                String com_code = c_code.getC_code();
                user_Name = userName.getText().toString();
                user_Pass = userPass.getText().toString();
                URL = "" + user_Name + "&Password=" + user_Pass + "&C_Code=" + com_code;
                URL2 = URL.replaceAll("..................", "+" + "%20" + "+");
                Log.e("URL", URL2);
                if (!checkValidation()) {
                    progressDialog.dismiss();

                } else {
                    checkLogin();
                    // push_device_token();


                }
            }

            public boolean checkValidation() {
                boolean validation_flag = true;
                user_Name = userName.getText().toString();
                user_Pass = userPass.getText().toString();

                if (user_Name.isEmpty()) {
                    userName.setError("Please enter your username.");
                    validation_flag = false;
                } else if (user_Pass.isEmpty()) {
                    userPass.setError("Please enter your password.");
                    validation_flag = false;
                }
                return validation_flag;
            }

            public void checkLogin() {
                final StringRequest stringRequest = new StringRequest(Request.Method.GET, URL2, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("Table1");
                            JSONObject contain = array.getJSONObject(0);
                            String code = contain.getString("Response_Code");
                            String message = contain.getString("Response_Msg");
                            JSONArray array2 = jsonObject.getJSONArray("Table");
                            JSONObject contains = array2.getJSONObject(0);
                            String user_name = contains.getString("Name");
                            USERNAME = user_name;
                            String user_Type = contains.getString("UserType");
                            USERTYPE = user_Type;
                            Log.e("CODE", code);
                            Log.e("MESSAGE", message);
                            if (code.equals("100")) {
                                Toast.makeText(getApplicationContext(), "Welcome, " + user_name, Toast.LENGTH_SHORT).show();
                                final Timer t = new Timer();
                                t.schedule(new TimerTask() {
                                    public void run() {
                                        editor = userPref.edit();
                                        editor.putString("user_name", USERNAME);
                                        editor.putString("user_id_name", user_Name);
                                        editor.putString("user_type", USERTYPE);
                                        editor.putString("user_pass", user_Pass);
                                        editor.putString("isLogin", "1");
                                        editor.commit();
                                        progressDialog.dismiss();
                                        t.cancel();
                                        Intent intent = new Intent(getApplicationContext(), BordingPoint.class);
                                        startActivity(intent);
                                        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);

                                    }
                                }, 2000);


                            } else if (code.equals("103")) {
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();

                            } else if (code.equals("101")) {
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();

                            }

                        } catch (JSONException e) {
                            Log.e("ERROR:", e.getMessage());
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(stringRequest);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);

        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Late_Login.this, ChooseBusSeat.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

}


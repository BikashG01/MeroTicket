import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.infobrain.meroticket.Activities.MainActivity;
import com.infobrain.meroticket.Activities.Singleton;
import com.infobrain.meroticket.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by bikas on 11/24/2017.
 */

public class frag_account extends Fragment {
    private EditText userName;
    private EditText userPass;
    private Button login_btn;
    private String URL, URL2;
    private ProgressDialog progressDialog;
    private String user_Name, user_Pass;
    private SharedPreferences userPref;
    private SharedPreferences.Editor editor;
    private String USERNAME,USERTYPE;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Login");
        userPref = this.getActivity().getSharedPreferences("LOGIN", 0);
        /*final Singleton c_code = (Singleton) getActivity();*/
        userName = view.findViewById(R.id.firstnameEditText);
        userPass = view.findViewById(R.id.PasswordEditText);
        login_btn = view.findViewById(R.id.login_btn);


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Please Wait...");
                progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(true);
                progressDialog.show();
                final Singleton c_code = (Singleton) getActivity().getApplicationContext();
                String com_code = c_code.getC_code();
                user_Name = userName.getText().toString();
                user_Pass = userPass.getText().toString();
                URL = "..........." + user_Name + "&Password=" + user_Pass + "&C_Code=" + com_code;
                URL2 = URL.replaceAll(" ", "+" + "%20" + "+");
                Log.e("URL", URL2);
                if (!checkValidation()) {
                    progressDialog.dismiss();

                } else {
                    checkLogin();
                    // push_device_token();


                }
            }
        });
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
                    USERNAME=user_name;
                    String user_Type = contains.getString("UserType");
                    USERTYPE=user_Type;


                 
                    Log.e("CODE", code);
                    Log.e("MESSAGE", message);
                    if (code.equals("100")) {


                        Toast.makeText(getContext(), "Welcome, " + user_name, Toast.LENGTH_SHORT).show();
                        final Timer t = new Timer();
                        t.schedule(new TimerTask() {
                            public void run() {
                                editor = userPref.edit();
                                editor.putString("user_name", USERNAME);
                                editor.putString("user_id_name",user_Name);
                                editor.putString("user_type", USERTYPE);
                                editor.putString("user_pass", user_Pass);
                                editor.putString("isLogin","1");
                                editor.commit();
                                progressDialog.dismiss();

                                t.cancel();

                                Intent intent= new Intent(getContext(),MainActivity.class);
                                startActivity(intent);
                                getActivity().overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);


                            }
                        }, 3000);


                    } else if (code.equals("103")) {
                        progressDialog.dismiss();

                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    } else if (code.equals("101")) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }

    public void alertDialog() {

    }
}

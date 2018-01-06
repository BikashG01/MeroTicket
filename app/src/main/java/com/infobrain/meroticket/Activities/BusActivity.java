import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.infobrain.meroticket.Adapters.BusAdapter;
import com.infobrain.meroticket.DataModels.BusLayout_DataModel;
import com.infobrain.meroticket.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class BusActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String yer, mnth, dy;
    private String date_values;
    private ListView bus_listView;
    Date to_days, obtain_days;
    private BusAdapter busAdapter;
    String from_title, to_title, dates, day_name, month_name;
    private TextView date, backbtn, nextbtn;
    private ProgressDialog progressDialog;
    private List<BusLayout_DataModel> busLayout_dataModels = new ArrayList<>();
    SharedPreferences pref_TO;
    SharedPreferences pref_FROM;
    SharedPreferences pref_date;
    Calendar calendar = Calendar.getInstance();
    Calendar cal = Calendar.getInstance();
    int year, month, day;
    String sourceDate, to_day;
    SimpleDateFormat sdf;
    String today_date,from,to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_list);
        bus_listView = (ListView) findViewById(R.id.bus_list);
        date = (TextView) findViewById(R.id.d_date);
        nextbtn = (TextView) findViewById(R.id.next);
        backbtn = (TextView) findViewById(R.id.back);
        busAdapter = new BusAdapter(busLayout_dataModels, getApplicationContext());
        pref_TO = getSharedPreferences("FROMNAME", 0);
        pref_FROM = getSharedPreferences("TONAME", 0);
        pref_date = this.getSharedPreferences("DATE", 0);
        from_title = pref_FROM.getString("from_name", "");
        to_title = pref_TO.getString("to_name", "");


        Bundle extras = getIntent().getExtras();
        from = extras.getString("EXTRA_FROM");
        Log.e("FROM Location",from);
        to = extras.getString("EXTRA_TO");
        Log.e("TO Location",to);
        year = extras.getInt("YEAR");
        yer = Integer.toString(year);
        month = extras.getInt("MONTH")+1;
        mnth = Integer.toString(month);
        day = extras.getInt("DAY");
        dy = Integer.toString(day);
        to_day = extras.getString("TODAY_DATE");
        Log.e("TODAY KO VALUE", to_day);
        System.out.println("INTENT PASS TODAY_DATE:"+to_day);
        System.out.println("INTENT PASS MONTH VALUE:"+month);
        sourceDate = year + "-" + month + "-" + day;
        date.setText(sourceDate);
        checkDate(sourceDate);
        load_bus_list(from,to,sourceDate);
        this.setTitle(from + " to " + to);
        bus_listView.setAdapter(busAdapter);
        bus_listView.setOnItemClickListener(this);
        //CALENDER SET
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            calendar.setTime(sdf.parse(sourceDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calendar.add(Calendar.DATE, 1);  // number of days to add
                String destDate = sdf.format(calendar.getTime());  // End date
                date.setText(destDate);
                checkDate(destDate);
                Log.e("NEXTBUTTON",destDate);
                String temp;
                temp=sourceDate;
                sourceDate=destDate;
                destDate=temp;
                busLayout_dataModels.clear();
                load_bus_list(from,to,destDate);
                bus_listView = findViewById(R.id.bus_list);
                busAdapter = new BusAdapter(busLayout_dataModels, getApplicationContext());
                bus_listView.setAdapter(busAdapter);
                busAdapter.notifyDataSetChanged();


            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.add(Calendar.DATE, -1);  // number of days to add
                String destDate = sdf.format(calendar.getTime());  // End date
                Log.e("BACKBUTTON",destDate);
                date.setText(destDate);
                checkDate(destDate);
                String temp;
                temp=sourceDate;
                sourceDate=destDate;
                destDate=temp;
                busLayout_dataModels.clear();
                load_bus_list(from,to,destDate);
                bus_listView = findViewById(R.id.bus_list);
                busAdapter = new BusAdapter(busLayout_dataModels, getApplicationContext());
                bus_listView.setAdapter(busAdapter);
                busAdapter.notifyDataSetChanged();


            }
        });

    }

    public void load_bus_list(String froms,String tos,String sourceDate) {
        String url="..............."+froms+"&T_City="+tos+"&Date="+sourceDate+"&"+"B_Type="+"Day";
        Log.e("URL",url);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    //Toast.makeText(BusActivity.this, response, Toast.LENGTH_SHORT).show();
                    JSONArray array = jsonObject.getJSONArray("Table");
                    //Toast.makeText(BusActivity.this, String.valueOf(array), Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject contain = array.getJSONObject(i);
                        BusLayout_DataModel busLayout_dataModel = new BusLayout_DataModel(
                                contain.getString("Bus_No"),
                                contain.getString("Luxury_Items"),
                                contain.getString("Point_Time"),
                                contain.getString("Seats"),
                                contain.getString("Fare"),
                                contain.getString("Type_Desc"),
                                contain.getString("Seat_Layout"),
                                contain.getString("Point_Time"),
                                contain.getString("Bus_Id"),
                                contain.getString("Route_Id")

                        );
                        busLayout_dataModels.add(busLayout_dataModel);
                        //Toast.makeText(MainActivity.this, String.valueOf(model.add(data_data)), Toast.LENGTH_SHORT).show();
                    }
                    if (busLayout_dataModels != null) {
                        progressDialog.dismiss();

                    }

                } catch (JSONException e) {
                    Log.e("ERROR:", e.getMessage());
                    progressDialog.dismiss();
                }
                busAdapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(BusActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }

        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(90000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        final Singleton c_code = (Singleton) getApplicationContext();
        String date,routID,busId,seatPrice,bus_name,bus_layout,com_code,boarding_point;

        BusLayout_DataModel busLayout_dataModel = (BusLayout_DataModel) adapterView.getItemAtPosition(i);
        Intent intent = new Intent(getApplicationContext(),ChooseBusSeat.class);
        c_code.setDate(sourceDate);
        c_code.setBus_id(busLayout_dataModel.getBus_id());
        c_code.setRoute_id(busLayout_dataModel.getRoute_id());
        c_code.setSeat_price(busLayout_dataModel.getBus_seat_price());
        c_code.setBus_name(busLayout_dataModel.getBus_name());
        c_code.setBus_layout(busLayout_dataModel.getBus_layout());
        c_code.setBoarding_point(busLayout_dataModel.getBus_borading_point());
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BusActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(BusActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                return true;
        }
        return false;
    }


    public void checkDate(String obtain_date) {
        today_date = to_day;
        /*System.out.println("BEFORE CONVERSION TODAY DATE:"+today_date);
        System.out.println("BEFORE CONVERSION OBTAIN DATE:"+obtain_date);*/

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            to_days = df.parse(today_date);
            obtain_days = df.parse(obtain_date);
           /* System.out.println("AFTER CONVERSION TODAY DATE : " + df.format(to_days));
            System.out.println("AFTER CONVERSION OBTAIN DATE : " + df.format(obtain_days));*/
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (to_days.equals(obtain_days)){
            backbtn.setVisibility(View.INVISIBLE);

        }else if (obtain_days.before(to_days)) {
            backbtn.setVisibility(View.INVISIBLE);
        }
        else if (obtain_days.after(to_days)) {
            backbtn.setVisibility(View.VISIBLE);
        }
        else{
            backbtn.setVisibility(View.VISIBLE);
        }

    }
}



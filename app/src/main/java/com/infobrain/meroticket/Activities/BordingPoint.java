import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.infobrain.meroticket.Adapters.BordingPointAdapter;
import com.infobrain.meroticket.DataModels.BordingPointModel;
import com.infobrain.meroticket.DataModels.BusLayout_DataModel;
import com.infobrain.meroticket.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BordingPoint extends AppCompatActivity {
    Button next;
    ListView boarding_point_list_View;
    private List<BordingPointModel> bordingPointModels = new ArrayList<>();
    private String boarding_point;
    private String[] boarding_point_array;
    private BordingPointAdapter bordingPointAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bording_point);
        final Singleton c_code = (Singleton) getApplicationContext();
        boarding_point=c_code.getBoarding_point();
        String converted=boarding_point.toUpperCase();
        Log.e("Boarding Point",converted);
        String split = ",";
        boarding_point_array = converted.split(split);
        List<String> values = new ArrayList<String>(Arrays.asList(boarding_point_array));
        boarding_point_list_View=(ListView)findViewById(R.id.boarding_point_list);
        final ArrayAdapter board_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,values);
        boarding_point_list_View.setAdapter(board_adapter);

        boarding_point_list_View.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(BordingPoint.this,BookingDetails.class);
                Object o = boarding_point_list_View.getItemAtPosition(i);
                String str=o.toString();
                intent.putExtra("boarding_point",str);
                Log.e("BOARDING POINT",str);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
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
}

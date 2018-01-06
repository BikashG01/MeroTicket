import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.infobrain.meroticket.Fragments.frag_account;
import com.infobrain.meroticket.Fragments.frag_bus_search;
import com.infobrain.meroticket.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ChooseBusSeat extends AppCompatActivity implements View.OnClickListener {
    private String layout_id = "2";
    private TextView A01, A02, A03, A04, A05, A06, A07, A08, A09, A10, A11, A12, A13, A14, A15, A16, A17, A0A, A0B, A0C;
    private TextView B01, B02, B03, B04, B05, B06, B07, B08, B09, B10, B11, B12, B13, B14, B15, B16, B17, B18;
    private TextView C01, C02, C03;
    private TextView seat_all, price, proceed;
    private String[] seat_id = {"A0A", "A0B", "A0C", "A01", "A02", "A03", "A04", "A05", "A06", "A07", "A08", "A09", "A10", "A11", "A12", "A13", "A14", "A15", "A16", "A17", "A18", "B01", "B02", "B03", "B04", "B05", "B06", "B07", "B08", "B09", "B10", "B11", "B12", "B13", "B14", "B15", "B16", "B17", "B18", "C1", "C2", "C3"};
    private String[] booked_Seats_no, booked_Seats_NO;
    private ArrayList<String> seat_list = new ArrayList<String>();
    private ArrayList<String> seat_booked = new ArrayList<String>();
    float SeatPrice, total_price;
    private RelativeLayout card_snack;
    private Button late_login;
    private String date, routID, busId, bus_name, bus_layout, com_code, bus_price, bookedSeat;
    private SharedPreferences userPref;

    private String tempBookedSeat;

    private Boolean seat_A1_flag = false;
    private Boolean seat_A2_flag = false;
    private Boolean seat_A3_flag = false;
    private Boolean seat_A4_flag = false;
    private Boolean seat_A5_flag = false;
    private Boolean seat_A6_flag = false;
    private Boolean seat_A7_flag = false;
    private Boolean seat_A8_flag = false;
    private Boolean seat_A9_flag = false;
    private Boolean seat_A10_flag = false;
    private Boolean seat_A11_flag = false;
    private Boolean seat_A12_flag = false;
    private Boolean seat_A13_flag = false;
    private Boolean seat_A14_flag = false;
    private Boolean seat_A15_flag = false;
    private Boolean seat_A16_flag = false;
    private Boolean seat_A17_flag = false;
    private Boolean seat_A18_flag = false;


    private Boolean seat_B1_flag = false;
    private Boolean seat_B2_flag = false;
    private Boolean seat_B3_flag = false;
    private Boolean seat_B4_flag = false;
    private Boolean seat_B5_flag = false;
    private Boolean seat_B6_flag = false;
    private Boolean seat_B7_flag = false;
    private Boolean seat_B8_flag = false;
    private Boolean seat_B9_flag = false;
    private Boolean seat_B10_flag = false;
    private Boolean seat_B11_flag = false;
    private Boolean seat_B12_flag = false;
    private Boolean seat_B13_flag = false;
    private Boolean seat_B14_flag = false;
    private Boolean seat_B15_flag = false;
    private Boolean seat_B16_flag = false;
    private Boolean seat_B17_flag = false;
    private Boolean seat_B18_flag = false;


    private Boolean seat_A0A_flag = false;
    private Boolean seat_A0B_flag = false;
    private Boolean seat_A0C_flag = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_busseat);
        final Singleton c_code = (Singleton) getApplicationContext();
        date = c_code.getDate();
        routID = c_code.getRoute_id();
        busId = c_code.getBus_id();
        bus_price = c_code.getSeat_price();
        com_code = c_code.getC_code();
        bus_layout = c_code.getBus_layout();
        bus_name = c_code.getBus_name();
        SeatPrice = Float.parseFloat(bus_price);
        inflate_layout(bus_layout);
        loadSeatBooked(busId, routID, date, com_code);


    }


    private void inflate_layout(String code) {
        switch (code) {
            case "35 Seat Layout":
                setContentView(R.layout.seat35_layout);
                card_snack = findViewById(R.id.bottom_snack_bar);
                proceed = (findViewById(R.id.proceed));
                price = findViewById(R.id.price);
                seat_all = findViewById(R.id.txt1);
                proceed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        userPref = getSharedPreferences("LOGIN", 0);
                        String login = userPref.getString("isLogin", "");
                        Intent intent = new Intent(getApplicationContext(), BordingPoint.class);
                        final Singleton c_code = (Singleton) getApplicationContext();
                        String selected_seat = String.valueOf(seat_list).replaceAll("\\[|\\]", "");
                        String total = Float.toString(total_price);
                        c_code.setTotal_price(total);
                        c_code.setSelected_no(selected_seat);
                        if (login.equals("1")) {
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

                        } else {
                            Intent login_intent = new Intent(getApplicationContext(), Late_Login.class);
                            startActivity(login_intent);
                            finish();
                            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                        }


                    }
                });
                A0A = findViewById(R.id.A0A);
                A0B = findViewById(R.id.A0B);
                A0C = findViewById(R.id.A0C);


                //A sides
                A01 = findViewById(R.id.A01);
                A02 = findViewById(R.id.A02);
                A03 = findViewById(R.id.A03);
                A04 = findViewById(R.id.A04);
                A05 = findViewById(R.id.A05);
                A06 = findViewById(R.id.A06);
                A07 = findViewById(R.id.A07);
                A08 = findViewById(R.id.A08);
                A09 = findViewById(R.id.A09);
                A10 = findViewById(R.id.A10);
                A11 = findViewById(R.id.A11);
                A12 = findViewById(R.id.A12);
                A13 = findViewById(R.id.A13);
                A14 = findViewById(R.id.A14);
                A15 = findViewById(R.id.A15);
                A16 = findViewById(R.id.A16);
                A17 = findViewById(R.id.A17);

                //B side seats
                B01 = findViewById(R.id.B01);
                B02 = findViewById(R.id.B02);
                B03 = findViewById(R.id.B03);
                B04 = findViewById(R.id.B04);
                B05 = findViewById(R.id.B05);
                B06 = findViewById(R.id.B06);
                B07 = findViewById(R.id.B07);
                B08 = findViewById(R.id.B08);
                B09 = findViewById(R.id.B09);
                B10 = findViewById(R.id.B10);
                B11 = findViewById(R.id.B11);
                B12 = findViewById(R.id.B12);
                B13 = findViewById(R.id.B13);
                B14 = findViewById(R.id.B14);
                B15 = findViewById(R.id.B15);
                B16 = findViewById(R.id.B16);
                B17 = findViewById(R.id.B17);
                B18 = findViewById(R.id.B18);
                //click listener
                A01.setOnClickListener(this);
                A02.setOnClickListener(this);
                A03.setOnClickListener(this);
                A04.setOnClickListener(this);
                A05.setOnClickListener(this);
                A06.setOnClickListener(this);
                A07.setOnClickListener(this);
                A08.setOnClickListener(this);
                A09.setOnClickListener(this);
                A10.setOnClickListener(this);
                A11.setOnClickListener(this);
                A12.setOnClickListener(this);
                A13.setOnClickListener(this);
                A14.setOnClickListener(this);
                A15.setOnClickListener(this);
                A16.setOnClickListener(this);
                A17.setOnClickListener(this);
                B01.setOnClickListener(this);
                B02.setOnClickListener(this);
                B03.setOnClickListener(this);
                B04.setOnClickListener(this);
                B05.setOnClickListener(this);
                B06.setOnClickListener(this);
                B07.setOnClickListener(this);
                B08.setOnClickListener(this);
                B09.setOnClickListener(this);
                B10.setOnClickListener(this);
                B11.setOnClickListener(this);
                B12.setOnClickListener(this);
                B13.setOnClickListener(this);
                B14.setOnClickListener(this);
                B15.setOnClickListener(this);
                B16.setOnClickListener(this);
                B17.setOnClickListener(this);
                B18.setOnClickListener(this);
                //checkSeat();
                break;
            case "35 Seat 2 Cabin Layout":
                setContentView(R.layout.seat35_cabin2_layout);
                card_snack = findViewById(R.id.bottom_snack_bar);
                proceed = (findViewById(R.id.proceed));
                price = findViewById(R.id.price);
                seat_all = findViewById(R.id.txt1);
                proceed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        userPref = getSharedPreferences("LOGIN", 0);
                        String login = userPref.getString("isLogin", "");
                        Intent intent = new Intent(getApplicationContext(), BordingPoint.class);
                        final Singleton c_code = (Singleton) getApplicationContext();
                        String selected_seat = String.valueOf(seat_list).replaceAll("\\[|\\]", "");
                        String total = Float.toString(total_price);
                        c_code.setTotal_price(total);
                        c_code.setSelected_no(selected_seat);
                        if (login.equals("1")) {
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

                        } else {
                            Intent login_intent = new Intent(getApplicationContext(), Late_Login.class);
                            startActivity(login_intent);
                            finish();
                            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                        }


                    }
                });
                //Cabinet SEATS
                A0A = findViewById(R.id.A0A);
                A0B = findViewById(R.id.A0B);

                //A sides
                A01 = findViewById(R.id.A01);
                A02 = findViewById(R.id.A02);
                A03 = findViewById(R.id.A03);
                A04 = findViewById(R.id.A04);
                A05 = findViewById(R.id.A05);
                A06 = findViewById(R.id.A06);
                A07 = findViewById(R.id.A07);
                A08 = findViewById(R.id.A08);
                A09 = findViewById(R.id.A09);
                A10 = findViewById(R.id.A10);
                A11 = findViewById(R.id.A11);
                A12 = findViewById(R.id.A12);
                A13 = findViewById(R.id.A13);
                A14 = findViewById(R.id.A14);
                A15 = findViewById(R.id.A15);
                A16 = findViewById(R.id.A16);
                A17 = findViewById(R.id.A17);

                //B side seats
                B01 = findViewById(R.id.B01);
                B02 = findViewById(R.id.B02);
                B03 = findViewById(R.id.B03);
                B04 = findViewById(R.id.B04);
                B05 = findViewById(R.id.B05);
                B06 = findViewById(R.id.B06);
                B07 = findViewById(R.id.B07);
                B08 = findViewById(R.id.B08);
                B09 = findViewById(R.id.B09);
                B10 = findViewById(R.id.B10);
                B11 = findViewById(R.id.B11);
                B12 = findViewById(R.id.B12);
                B13 = findViewById(R.id.B13);
                B14 = findViewById(R.id.B14);
                B15 = findViewById(R.id.B15);
                B16 = findViewById(R.id.B16);
                B17 = findViewById(R.id.B17);
                B18 = findViewById(R.id.B18);

                A01.setOnClickListener(this);
                A02.setOnClickListener(this);
                A03.setOnClickListener(this);
                A04.setOnClickListener(this);
                A05.setOnClickListener(this);
                A06.setOnClickListener(this);
                A07.setOnClickListener(this);
                A08.setOnClickListener(this);
                A09.setOnClickListener(this);
                A10.setOnClickListener(this);
                A11.setOnClickListener(this);
                A12.setOnClickListener(this);
                A13.setOnClickListener(this);
                A14.setOnClickListener(this);
                A15.setOnClickListener(this);
                A16.setOnClickListener(this);
                A17.setOnClickListener(this);


                B01.setOnClickListener(this);
                B02.setOnClickListener(this);
                B03.setOnClickListener(this);
                B04.setOnClickListener(this);
                B05.setOnClickListener(this);
                B06.setOnClickListener(this);
                B07.setOnClickListener(this);
                B08.setOnClickListener(this);
                B09.setOnClickListener(this);
                B10.setOnClickListener(this);
                B11.setOnClickListener(this);
                B12.setOnClickListener(this);
                B13.setOnClickListener(this);
                B14.setOnClickListener(this);
                B15.setOnClickListener(this);
                B16.setOnClickListener(this);
                B17.setOnClickListener(this);
                B18.setOnClickListener(this);
                //Cabin seat
                A0A.setOnClickListener(this);
                A0B.setOnClickListener(this);

                //checkSeat();
                break;
            case "31 Seat 3 Cabin Layout":
                setContentView(R.layout.seat31_cabin3_layout);
                card_snack = findViewById(R.id.bottom_snack_bar);
                proceed = (findViewById(R.id.proceed));
                price = findViewById(R.id.price);
                seat_all = findViewById(R.id.txt1);
                proceed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        userPref = getSharedPreferences("LOGIN", 0);
                        String login = userPref.getString("isLogin", "");
                        Intent intent = new Intent(getApplicationContext(), BordingPoint.class);
                        final Singleton c_code = (Singleton) getApplicationContext();
                        String selected_seat = String.valueOf(seat_list).replaceAll("\\[|\\]", "");
                        String total = Float.toString(total_price);
                        c_code.setTotal_price(total);
                        c_code.setSelected_no(selected_seat);
                        if (login.equals("1")) {
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

                        } else {
                            Intent login_intent = new Intent(getApplicationContext(), Late_Login.class);
                            startActivity(login_intent);
                            finish();
                            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                        }


                    }
                });

                //Cabinet SEATS
                A0A = findViewById(R.id.A0A);
                A0B = findViewById(R.id.A0B);
                A0C = findViewById(R.id.A0C);

                //A sides
                A01 = findViewById(R.id.A01);
                A02 = findViewById(R.id.A02);
                A03 = findViewById(R.id.A03);
                A04 = findViewById(R.id.A04);
                A05 = findViewById(R.id.A05);
                A06 = findViewById(R.id.A06);
                A07 = findViewById(R.id.A07);
                A08 = findViewById(R.id.A08);
                A09 = findViewById(R.id.A09);
                A10 = findViewById(R.id.A10);
                A11 = findViewById(R.id.A11);
                A12 = findViewById(R.id.A12);
                A13 = findViewById(R.id.A13);
                A14 = findViewById(R.id.A14);
                A15 = findViewById(R.id.A15);

                //B side seats
                B01 = findViewById(R.id.B01);
                B02 = findViewById(R.id.B02);
                B03 = findViewById(R.id.B03);
                B04 = findViewById(R.id.B04);
                B05 = findViewById(R.id.B05);
                B06 = findViewById(R.id.B06);
                B07 = findViewById(R.id.B07);
                B08 = findViewById(R.id.B08);
                B09 = findViewById(R.id.B09);
                B10 = findViewById(R.id.B10);
                B11 = findViewById(R.id.B11);
                B12 = findViewById(R.id.B12);
                B13 = findViewById(R.id.B13);
                B14 = findViewById(R.id.B14);
                B15 = findViewById(R.id.B15);
                B16 = findViewById(R.id.B16);

                A01.setOnClickListener(this);
                A02.setOnClickListener(this);
                A03.setOnClickListener(this);
                A04.setOnClickListener(this);
                A05.setOnClickListener(this);
                A06.setOnClickListener(this);
                A07.setOnClickListener(this);
                A08.setOnClickListener(this);
                A09.setOnClickListener(this);
                A10.setOnClickListener(this);
                A11.setOnClickListener(this);
                A12.setOnClickListener(this);
                A13.setOnClickListener(this);
                A14.setOnClickListener(this);
                A15.setOnClickListener(this);


                B01.setOnClickListener(this);
                B02.setOnClickListener(this);
                B03.setOnClickListener(this);
                B04.setOnClickListener(this);
                B05.setOnClickListener(this);
                B06.setOnClickListener(this);
                B07.setOnClickListener(this);
                B08.setOnClickListener(this);
                B09.setOnClickListener(this);
                B10.setOnClickListener(this);
                B11.setOnClickListener(this);
                B12.setOnClickListener(this);
                B13.setOnClickListener(this);
                B14.setOnClickListener(this);
                B15.setOnClickListener(this);
                B16.setOnClickListener(this);

                //Cabin seat
                A0A.setOnClickListener(this);
                A0B.setOnClickListener(this);
                A0C.setOnClickListener(this);


                //checkSeat();
                break;
            case "35 Seat 3 Cabin Layout":
                setContentView(R.layout.seat35_cabin3_layout);
                card_snack = findViewById(R.id.bottom_snack_bar);
                proceed = (findViewById(R.id.proceed));
                price = findViewById(R.id.price);
                seat_all = findViewById(R.id.txt1);
                proceed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        userPref = getSharedPreferences("LOGIN", 0);
                        String login = userPref.getString("isLogin", "");
                        Intent intent = new Intent(getApplicationContext(), BordingPoint.class);
                        final Singleton c_code = (Singleton) getApplicationContext();
                        String selected_seat = String.valueOf(seat_list).replaceAll("\\[|\\]", "");
                        String total = Float.toString(total_price);
                        c_code.setTotal_price(total);
                        c_code.setSelected_no(selected_seat);
                        if (login.equals("1")) {
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

                        } else {
                            Intent login_intent = new Intent(getApplicationContext(), Late_Login.class);
                            startActivity(login_intent);
                            finish();
                            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                        }


                    }
                });

                //Cabinet SEATS
                A0A = findViewById(R.id.A0A);
                A0B = findViewById(R.id.A0B);
                A0C = findViewById(R.id.A0C);

                //A sides
                A01 = findViewById(R.id.A01);
                A02 = findViewById(R.id.A02);
                A03 = findViewById(R.id.A03);
                A04 = findViewById(R.id.A04);
                A05 = findViewById(R.id.A05);
                A06 = findViewById(R.id.A06);
                A07 = findViewById(R.id.A07);
                A08 = findViewById(R.id.A08);
                A09 = findViewById(R.id.A09);
                A10 = findViewById(R.id.A10);
                A11 = findViewById(R.id.A11);
                A12 = findViewById(R.id.A12);
                A13 = findViewById(R.id.A13);
                A14 = findViewById(R.id.A14);
                A15 = findViewById(R.id.A15);
                A16 = findViewById(R.id.A16);
                A17 = findViewById(R.id.A17);

                //B side seats
                B01 = findViewById(R.id.B01);
                B02 = findViewById(R.id.B02);
                B03 = findViewById(R.id.B03);
                B04 = findViewById(R.id.B04);
                B05 = findViewById(R.id.B05);
                B06 = findViewById(R.id.B06);
                B07 = findViewById(R.id.B07);
                B08 = findViewById(R.id.B08);
                B09 = findViewById(R.id.B09);
                B10 = findViewById(R.id.B10);
                B11 = findViewById(R.id.B11);
                B12 = findViewById(R.id.B12);
                B13 = findViewById(R.id.B13);
                B14 = findViewById(R.id.B14);
                B15 = findViewById(R.id.B15);
                B16 = findViewById(R.id.B16);
                B17 = findViewById(R.id.B17);
                B18 = findViewById(R.id.B18);

                A01.setOnClickListener(this);
                A02.setOnClickListener(this);
                A03.setOnClickListener(this);
                A04.setOnClickListener(this);
                A05.setOnClickListener(this);
                A06.setOnClickListener(this);
                A07.setOnClickListener(this);
                A08.setOnClickListener(this);
                A09.setOnClickListener(this);
                A10.setOnClickListener(this);
                A11.setOnClickListener(this);
                A12.setOnClickListener(this);
                A13.setOnClickListener(this);
                A14.setOnClickListener(this);
                A15.setOnClickListener(this);
                A16.setOnClickListener(this);
                A17.setOnClickListener(this);


                B01.setOnClickListener(this);
                B02.setOnClickListener(this);
                B03.setOnClickListener(this);
                B04.setOnClickListener(this);
                B05.setOnClickListener(this);
                B06.setOnClickListener(this);
                B07.setOnClickListener(this);
                B08.setOnClickListener(this);
                B09.setOnClickListener(this);
                B10.setOnClickListener(this);
                B11.setOnClickListener(this);
                B12.setOnClickListener(this);
                B13.setOnClickListener(this);
                B14.setOnClickListener(this);
                B15.setOnClickListener(this);
                B16.setOnClickListener(this);
                B17.setOnClickListener(this);
                B18.setOnClickListener(this);
                //Cabin seat
                A0A.setOnClickListener(this);
                A0B.setOnClickListener(this);
                A0C.setOnClickListener(this);


                //checkSeat();
                break;

        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_image) {
            DialogImage();
        }
        return super.onOptionsItemSelected(item);
    }


    public void priceAdd(float seat_price) {
        total_price = total_price + seat_price;
        price.setText(String.valueOf(total_price));
        //Toast.makeText(this,String.valueOf(total_price), Toast.LENGTH_SHORT).show();
    }

    public void priceSub(float seat_price) {
        total_price = total_price - seat_price;
        price.setText(String.valueOf(total_price));

        //Toast.makeText(this,String.valueOf(total_price), Toast.LENGTH_SHORT).show();
    }

    public void addData(String value) {
        seat_list.add(value);
        seat_all.setText(seat_list.toString().replaceAll("\\[|\\]", ""));
        if (String.valueOf(seat_list.size()).equals(0)) {
            card_snack.setVisibility(View.GONE);
        } else {
            card_snack.setVisibility(View.VISIBLE);
        }
    }

    public void removeData(String value) {

        seat_list.remove(value);
        seat_all.setText(seat_list.toString().replaceAll("\\[|\\]", ""));
        if ((seat_list.isEmpty())) {
            card_snack.setVisibility(View.GONE);
        } else {
            card_snack.setVisibility(View.VISIBLE);
        }
    }


    public void checkSeat() {
        List<String> values = new ArrayList<String>(Arrays.asList(booked_Seats_no));
        for (String string : seat_id) {
            if (values.contains(string)) {
                int resID = getResources().getIdentifier(string, "id", getPackageName());
                TextView v = (TextView) findViewById(resID);
                v.setBackgroundResource(R.drawable.ic_seat_booked);
                v.setEnabled(false);
                v.setClickable(false);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.A01:
                String seat_A1 = "A01";
                if (seat_A1_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_A1);
                    priceAdd(SeatPrice);
                    seat_A1_flag = true;

                } else {
                    removeData(seat_A1);
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    priceSub(SeatPrice);
                    seat_A1_flag = false;
                }
                break;

            case R.id.A02:
                String seat_A2 = "A02";
                if (seat_A2_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_A2);
                    priceAdd(SeatPrice);
                    seat_A2_flag = true;
                } else {
                    removeData(seat_A2);
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    priceSub(SeatPrice);
                    seat_A2_flag = false;
                }

                break;
            case R.id.A03:
                String seat_A3 = "A03";
                if (seat_A3_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_A3);
                    priceAdd(SeatPrice);
                    seat_A3_flag = true;
                } else {
                    removeData(seat_A3);
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    priceSub(SeatPrice);
                    seat_A3_flag = false;
                }
                break;
            case R.id.A04:
                String seat_A4 = "A04";
                if (seat_A4_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_A4);
                    priceAdd(SeatPrice);
                    seat_A4_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_A4);
                    priceSub(SeatPrice);
                    seat_A4_flag = false;
                }
                break;
            case R.id.A05:
                String seat_A5 = "A05";
                if (seat_A5_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_A5);
                    priceAdd(SeatPrice);
                    seat_A5_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_A5);
                    priceSub(SeatPrice);
                    seat_A5_flag = false;
                }
                break;
            case R.id.A06:
                String seat_A6 = "A06";
                if (seat_A6_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_A6);
                    priceAdd(SeatPrice);
                    seat_A6_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_A6);
                    priceSub(SeatPrice);
                    seat_A6_flag = false;
                }
                break;
            case R.id.A07:
                String seat_A7 = "A07";
                if (seat_A7_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_A7);
                    priceAdd(SeatPrice);
                    seat_A7_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_A7);
                    priceSub(SeatPrice);
                    seat_A7_flag = false;
                }
                break;
            case R.id.A08:
                String seat_A8 = "A08";
                if (seat_A8_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_A8);
                    priceAdd(SeatPrice);
                    seat_A8_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_A8);
                    priceSub(SeatPrice);
                    seat_A8_flag = false;
                }
                break;
            case R.id.A09:
                String seat_A9 = "A09";
                if (seat_A9_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_A9);
                    priceAdd(SeatPrice);
                    seat_A9_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_A9);
                    priceSub(SeatPrice);
                    seat_A9_flag = false;
                }
                break;
            case R.id.A10:
                String seat_A10 = "A10";
                if (seat_A10_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_A10);
                    priceAdd(SeatPrice);
                    seat_A10_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_A10);
                    priceSub(SeatPrice);
                    seat_A10_flag = false;
                }
                break;
            case R.id.A11:
                String seat_A11 = "A11";
                if (seat_A11_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_A11);
                    priceAdd(SeatPrice);
                    seat_A11_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_A11);
                    priceSub(SeatPrice);
                    seat_A11_flag = false;
                }
                break;
            case R.id.A12:
                String seat_A12 = "A12";
                if (seat_A12_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_A12);
                    priceAdd(SeatPrice);
                    seat_A12_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_A12);
                    priceSub(SeatPrice);
                    seat_A12_flag = false;
                }
                break;
            case R.id.A13:
                String seat_A13 = "A13";
                if (seat_A13_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_A13);
                    priceAdd(SeatPrice);
                    seat_A13_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_A13);
                    priceSub(SeatPrice);
                    seat_A13_flag = false;
                }
                break;
            case R.id.A14:
                String seat_A14 = "A14";
                if (seat_A14_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_A14);
                    priceAdd(SeatPrice);
                    seat_A14_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_A14);
                    priceSub(SeatPrice);
                    seat_A14_flag = false;
                }
                break;
            case R.id.A15:
                String seat_A15 = "A15";
                if (seat_A15_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_A15);
                    priceAdd(SeatPrice);
                    seat_A15_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_A15);
                    priceSub(SeatPrice);
                    seat_A15_flag = false;
                }
                break;
            case R.id.A16:
                String seat_A16 = "A16";
                if (seat_A16_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_A16);
                    priceAdd(SeatPrice);
                    seat_A16_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_A16);
                    priceSub(SeatPrice);
                    seat_A16_flag = false;
                }
                break;
            case R.id.A17:
                String seat_A17 = "A17";
                if (seat_A17_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_A17);
                    priceAdd(SeatPrice);
                    seat_A17_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_A17);
                    priceSub(SeatPrice);
                    seat_A17_flag = false;
                }
                break;

            case R.id.B01:
                String seat_B1 = "B01";
                if (seat_B1_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_B1);
                    priceAdd(SeatPrice);
                    seat_B1_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_B1);
                    priceSub(SeatPrice);
                    seat_B1_flag = false;
                }
                break;
            case R.id.B02:
                String seat_B2 = "B02";
                if (seat_B2_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_B2);
                    priceAdd(SeatPrice);
                    seat_B2_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_B2);
                    priceSub(SeatPrice);
                    seat_B2_flag = false;
                }
                break;
            case R.id.B03:
                String seat_B3 = "B03";
                if (seat_B3_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_B3);
                    priceAdd(SeatPrice);
                    seat_B3_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_B3);
                    priceSub(SeatPrice);
                    seat_B3_flag = false;
                }
                break;
            case R.id.B04:
                String seat_B4 = "B04";
                if (seat_B4_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_B4);
                    priceAdd(SeatPrice);
                    seat_B4_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_B4);
                    priceSub(SeatPrice);
                    seat_B4_flag = false;
                }
                break;
            case R.id.B05:
                String seat_B5 = "B05";
                if (seat_B5_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_B5);
                    priceAdd(SeatPrice);
                    seat_B5_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_B5);
                    priceSub(SeatPrice);
                    seat_B5_flag = false;
                }
                break;
            case R.id.B06:
                String seat_B6 = "B06";
                if (seat_B6_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_B6);
                    priceAdd(SeatPrice);
                    seat_B6_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_B6);
                    priceSub(SeatPrice);
                    seat_B6_flag = false;
                }
                break;
            case R.id.B07:
                String seat_B7 = "B07";
                if (seat_B7_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_B7);
                    priceAdd(SeatPrice);
                    seat_B7_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_B7);
                    priceSub(SeatPrice);
                    seat_B7_flag = false;
                }
                break;
            case R.id.B08:
                String seat_B8 = "B08";
                if (seat_B8_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_B8);
                    priceAdd(SeatPrice);
                    seat_B8_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_B8);
                    priceSub(SeatPrice);
                    seat_B8_flag = false;
                }
                break;
            case R.id.B09:
                String seat_B9 = "B09";
                if (seat_B9_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_B9);
                    priceAdd(SeatPrice);
                    seat_B9_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_B9);
                    priceSub(SeatPrice);
                    seat_B9_flag = false;
                }
                break;
            case R.id.B10:
                String seat_B10 = "B10";
                if (seat_B10_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_B10);
                    priceAdd(SeatPrice);
                    seat_B10_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_B10);
                    priceSub(SeatPrice);
                    seat_B10_flag = false;
                }
                break;
            case R.id.B11:
                String seat_B11 = "B11";
                if (seat_B11_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_B11);
                    priceAdd(SeatPrice);
                    seat_B11_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_B11);
                    priceSub(SeatPrice);
                    seat_B11_flag = false;
                }
                break;
            case R.id.B12:
                String seat_B12 = "B12";
                if (seat_B12_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_B12);
                    priceAdd(SeatPrice);
                    seat_B12_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_B12);
                    priceSub(SeatPrice);
                    seat_A2_flag = false;
                }
                break;
            case R.id.B13:
                String seat_B13 = "B13";
                if (seat_B13_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_B13);
                    priceAdd(SeatPrice);
                    seat_B13_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_B13);
                    priceSub(SeatPrice);
                    seat_B13_flag = false;
                }
                break;
            case R.id.B14:
                String seat_B14 = "B14";
                if (seat_B14_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_B14);
                    priceAdd(SeatPrice);
                    seat_B14_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_B14);
                    priceSub(SeatPrice);
                    seat_B14_flag = false;
                }
                break;
            case R.id.B15:
                String seat_B15 = "B15";
                if (seat_B15_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_B15);
                    priceAdd(SeatPrice);
                    seat_B15_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_B15);
                    priceSub(SeatPrice);
                    seat_B15_flag = false;
                }
                break;
            case R.id.B16:
                String seat_B16 = "B16";
                if (seat_B16_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_B16);
                    priceAdd(SeatPrice);
                    seat_B16_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_B16);
                    priceSub(SeatPrice);
                    seat_B16_flag = false;
                }
                break;
            case R.id.B17:
                String seat_B17 = "B17";
                if (seat_B17_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_B17);
                    priceAdd(SeatPrice);
                    seat_B17_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_B17);
                    priceSub(SeatPrice);
                    seat_B17_flag = false;
                }
                break;

            case R.id.B18:
                String seat_B18 = "B18";
                if (seat_B18_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_B18);
                    priceAdd(SeatPrice);
                    seat_B18_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_B18);
                    priceSub(SeatPrice);
                    seat_B18_flag = false;
                }
                break;
            case R.id.A0A:
                String seat_C1 = "A0A";
                if (seat_A0A_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_C1);
                    priceAdd(SeatPrice);
                    seat_A0A_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_C1);
                    priceSub(SeatPrice);
                    seat_A0A_flag = false;
                }
                break;
            case R.id.A0C:
                String seat_C3 = "A0C";
                if (seat_A0C_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_C3);
                    priceAdd(SeatPrice);
                    seat_A0C_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_C3);
                    priceSub(SeatPrice);
                    seat_A0C_flag = false;
                }
                break;
            case R.id.A0B:
                String seat_C2 = "AOB";
                if (seat_A0B_flag.equals(false)) {
                    view.setBackgroundResource(R.drawable.ic_seat_selected);
                    addData(seat_C2);
                    priceAdd(SeatPrice);
                    seat_A0B_flag = true;
                } else {
                    view.setBackgroundResource(R.drawable.ic_seat_available);
                    removeData(seat_C2);
                    priceSub(SeatPrice);
                    seat_A0B_flag = false;
                }
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.image_menu, menu);
        MenuItem item = menu.findItem(R.id.action_image);
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return false;
    }


    public void DialogImage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.show_image_viewer, null);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();

        dialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ChooseBusSeat.this, BusActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);

    }

    public void loadSeatBooked(String bus_id, String route_id, String bus_date, String company_code) {
        String url = " " + bus_id + "&Route_Id=" + route_id + "&Date=" + bus_date + "&C_Code=" + company_code;
        Log.e("URL", url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("Table");
                    JSONObject contain = array.getJSONObject(0);
                    tempBookedSeat = contain.getString("Seat_No");
                    bookedSeat = tempBookedSeat.replaceAll(" ", "");

                    Log.e("BOOKED SEAT", tempBookedSeat);
                    Log.e("BOOKED SEAT", bookedSeat);

                    String split = ",";
                    booked_Seats_no = bookedSeat.split(split);
                    checkSeat();


                } catch (JSONException e) {
                    Log.e("ERROR:", e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(BusActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(90000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);

    }


}

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.infobrain.meroticket.Activities.BookingDetails;
import com.infobrain.meroticket.Activities.Singleton;
import com.infobrain.meroticket.Adapters.Bus_challanAdapter;
import com.infobrain.meroticket.DataModels.BusChallanModel;
import com.infobrain.meroticket.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by bikas on 1/5/2018.
 */

public class frag_bus_challan extends Fragment {
    private List<String> bus_no_list;
    private List<String> bus_id_list;
    private Spinner bus_list_spin;
    private Button datePicker, searchBtn;
    private Calendar calendar;
    private String InstantBusId, NepDate, C_CODE;
    private int year, month, day;
    private String toDate;
    private ListView busTickeList;
    private Bus_challanAdapter challanAdapter;
    private List<BusChallanModel> busChallanModels = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_home, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Bus Challan Details");
        busTickeList = view.findViewById(R.id.booking_list);
        challanAdapter = new Bus_challanAdapter(busChallanModels, getContext());
        bus_no_list = new ArrayList<>();
        bus_id_list = new ArrayList<>();
        bus_list_spin = view.findViewById(R.id.bus_list_spin);
        datePicker = view.findViewById(R.id.pick_date);
        searchBtn = view.findViewById(R.id.search_btn);
        final Singleton c_code = (Singleton) getActivity().getApplicationContext();
        C_CODE = c_code.getC_code();
        spinner_load();

        Date currentDate = new Date();

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        toDate = year + "-" + new StringBuilder().append(month + 1) + "-" + day;
        datePicker.setText(toDate);
        loadNepaliDate(toDate);

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickeNep();
            }
        });



        bus_list_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                InstantBusId = bus_id_list.get(i);
                Log.e("BUS ID", InstantBusId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                busChallanModels.clear();
                loadData();
                challanAdapter = new Bus_challanAdapter(busChallanModels, getContext());
                busTickeList.setAdapter(challanAdapter);
                challanAdapter.notifyDataSetChanged();

            }
        });
        busTickeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BusChallanModel datamodel = (BusChallanModel) adapterView.getItemAtPosition(i);
                Log.e("Alert","open alert");
                String bookingNo,operatorName,fullName,Remarks,PaymentMode,MobileNo,SeatNo,TotalAmt,Route,boardingPoint,TicketNo;
                operatorName = datamodel.getOperatorName();
                fullName=datamodel.getFullname();
                boardingPoint=datamodel.getBoardingPoint();
                Remarks=datamodel.getRemarks();
                Route=datamodel.getRoute();
                MobileNo=datamodel.getMobileNo();
                PaymentMode=datamodel.getPaymentMode();
                SeatNo=datamodel.getSeatNo();
                TicketNo=datamodel.getTicketNo();
                TotalAmt=datamodel.getTotalAmt();
                bookingNo=datamodel.getBookingNo();
                Log.e("Operator Name",operatorName);
                Log.e("Passenger Name",fullName);
                Log.e("Boarding Point",boardingPoint);
                Log.e("Remarks",Remarks);
                Log.e("Route",Route);
                Log.e("Mobile No",MobileNo);
                Log.e("Seat No",SeatNo);
                Log.e("Ticket No",TicketNo);
                Log.e("Total Amount",TotalAmt);
                Log.e("Booking No",bookingNo);
                Log.e("Payment Mode",PaymentMode);



                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_more_info, null);
                TextView booking_no = dialogView.findViewById(R.id.booking_no);
                booking_no.setText(bookingNo);
                TextView ticket_no = dialogView.findViewById(R.id.ticket_num);
                ticket_no.setText(TicketNo);
                TextView payment_mode = dialogView.findViewById(R.id.cash_mode);
                payment_mode.setText(PaymentMode);
                TextView boarding_point = dialogView.findViewById(R.id.boarding_point);
                boarding_point.setText(boardingPoint);
                TextView mobileNo = dialogView.findViewById(R.id.mobile_no);
                mobileNo.setText(MobileNo);
                TextView route = dialogView.findViewById(R.id.route);
                route.setText(Route);
                TextView seat_no = dialogView.findViewById(R.id.booked_Seats);
                seat_no.setText(SeatNo);
                TextView FullName= dialogView.findViewById(R.id.pass_name);
                FullName.setText(fullName);
                TextView operator_name = dialogView.findViewById(R.id.operator_name);
                operator_name.setText(operatorName);
                TextView total_amt = dialogView.findViewById(R.id.total_amt);
                total_amt.setText(TotalAmt);
                TextView remarks = dialogView.findViewById(R.id.pass_remarks);
                remarks.setText(Remarks);
                builder.setTitle("More Info");
                builder.setView(dialogView);

                final AlertDialog dialog = builder.create();

                Button btn_confirm =dialogView.findViewById(R.id.ok);
                btn_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();

//                alertDialog();

                    }
                });

                dialog.show();

            }
        });
    }
    public void spinner_load() {
        String c_code = "1001";
        final String login_url = "...................." + c_code;
        final StringRequest stringRequest2 = new StringRequest(Request.Method.GET, login_url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array1 = jsonObject.getJSONArray("Table");


                    for (int i = 0; i < array1.length(); i++) {
                        JSONObject object = array1.getJSONObject(i);
                        String bus_name = object.getString("Bus_Name");
                        String bus_no = object.getString("Bus_No");
                        String bus_id = object.getString("Bus_Id");
                        bus_no_list.add(bus_name + "--" + bus_no);
                        bus_id_list.add(bus_id);

                    }
                    bus_list_spin.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, bus_no_list));

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
        requestQueue.add(stringRequest2);
    }

    public void loadData() {
        String URL = "..........................." + C_CODE + "&Bus_Id=" + InstantBusId + "&NDate=" + NepDate;
        Log.e("MAIN_URL", URL);
        final StringRequest busresult_Request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array1 = jsonObject.getJSONArray("Table");
                    for (int i = 0; i < array1.length(); i++) {
                        JSONObject object = array1.getJSONObject(i);
                        BusChallanModel busChallanModel = new BusChallanModel(
                                object.getString("Booking_No"),
                                object.getString("Booking_RefNo"),
                                object.getString("Full_Name"),
                                object.getString("Mobile_No1"),
                                object.getString("Boarding_PointTime"),
                                object.getString("Payment_Mode"),
                                object.getString("Total_Amt"),
                                object.getString("Seat_No"),
                                object.getString("Remarks"),
                                object.getString("Route_Desc"),
                                object.getString("Name")
                        );
                        busChallanModels.add(busChallanModel);
                    }
                } catch (JSONException e) {
                    Log.e("ERROR:", e.getMessage());
                }
                challanAdapter.notifyDataSetChanged();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(busresult_Request);
    }
    public void loadNepaliDate(String EngDate) {
        final String login_url = ".........................." + EngDate;
        Log.e("URL", login_url);
        final StringRequest stringRequest2 = new StringRequest(Request.Method.GET, login_url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray value = jsonObject.getJSONArray("Table");
                    JSONObject contain = value.getJSONObject(0);
                    NepDate = contain.getString("Val");
                    //datePicker.setText(nepDate);
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
        requestQueue.add(stringRequest2);
    }

    public void DatePickeNep() {
        DialogFragment dialogFragments = new SelectDateFragment();
        dialogFragments.show(getActivity().getFragmentManager(),"ad");
        //dialogFragments.show(getActivity().getFragmentManager(), "d");
    }


    @SuppressLint("ValidFragment")
    public class SelectDateFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
            return dialog;
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            year = yy;
            month = mm;
            day = dd;
            toDate = year + "-" + new StringBuilder().append(month + 1) + "-" + day;
            Log.e("TO SELECT GARDA", toDate);
            datePicker.setText(toDate);
            loadNepaliDate(toDate);
        }

    }
}

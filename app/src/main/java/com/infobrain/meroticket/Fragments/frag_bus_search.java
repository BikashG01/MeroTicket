import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.infobrain.meroticket.Activities.BusActivity;
import com.infobrain.meroticket.Activities.FromLocationActivity;
import com.infobrain.meroticket.Activities.ToLocationActivity;
import com.infobrain.meroticket.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by bikas on 11/12/2017.
 */

public class frag_bus_search extends Fragment {
    Boolean fab_flag = false;
    RadioButton daybtn, nightbtn, bothbtn;
    RadioGroup radioGroup;
    ListView list_place;
    CardView date_picker;
    EditText from_txtview, to_txtview;
    TextView get_date, warntxt;
    String stringDate;
    Calendar calendar;
    Button search_btn;
    FloatingActionButton myFab;
    String day_night = "BOTH";
    public static String datevalue;
    String location_from, location_to;
    private String my_location;
    ArrayAdapter<String> adapter;
    SharedPreferences pref_title, pref_to, pref_from, pref_date, pref_state;
    SharedPreferences.Editor titleeditors, dateditor, stateEditor;
    private int yy1, mm1, dd1, days;
    String day_name, month_name, dayofweek;
    String to_day_date;
    String loc = "";
    int change_month;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Toast.makeText(getContext(), "I am onCreateView", Toast.LENGTH_LONG).show();
        View some = inflater.inflate(R.layout.frag_bus_search, container, false);
        return some;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Bus Search");
        pref_from = this.getActivity().getSharedPreferences("FROMNAME", 0);
        pref_to = this.getActivity().getSharedPreferences("TONAME", 0);
        //pref_title = this.getActivity().getSharedPreferences("TITLENAME", 0);
        pref_date = this.getActivity().getSharedPreferences("DATE", 0);
        pref_state = this.getActivity().getSharedPreferences("STATE", 0);
        fab_open = AnimationUtils.loadAnimation(getContext(), R.anim.fb_open);
        fab_close = AnimationUtils.loadAnimation(getContext(), R.anim.fb_close);
        rotate_forward = AnimationUtils.loadAnimation(getContext(), R.anim.fb_forward);
        rotate_backward = AnimationUtils.loadAnimation(getContext(), R.anim.fb_backward);

        date_picker = (CardView) view.findViewById(R.id.calender);
        get_date = (TextView) view.findViewById(R.id._date);
        search_btn = (Button) view.findViewById(R.id.search_bus_btn);
        from_txtview = (EditText) view.findViewById(R.id.location_from);
        to_txtview = (EditText) view.findViewById(R.id.location_to);
        warntxt = (TextView) view.findViewById(R.id.id_warning_txt);
        radioGroup = (RadioGroup) view.findViewById(R.id.radio_btn);
        daybtn = (RadioButton) view.findViewById(R.id.day);
        nightbtn = (RadioButton) view.findViewById(R.id.night);
        bothbtn = (RadioButton) view.findViewById(R.id.both);
        warntxt.setVisibility(View.GONE);
        location_from = pref_from.getString("from_name", "");
        from_txtview.setText(location_from);
        location_to = pref_to.getString("to_name", "");
        to_txtview.setText(location_to);
        day_night = pref_state.getString("day_night", "");
        /*try{
            loc=getArguments().getString("location");
            from_txtview.setText(loc);
        }catch(Exception e){

        }
*/


        // Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
        checkRadio(day_night);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);

                // Add logic here

                switch (index) {
                    case 0: // first button
                        day_night = "DAY";


                        //Toast.makeText(getContext(), "Day " + index, 500).show();
                        break;
                    case 1: // secondbutton
                        day_night = "NIGHT";
                        //Toast.makeText(getContext(), "Night" + index, 500).show();
                        break;
                    case 2://thirdbutton
                        day_night = "BOTH";
                        //Toast.makeText(getContext(), "Both" + index, 500).show();
                        break;
                }
            }
        });

        calendar = Calendar.getInstance();
        yy1 = calendar.get(Calendar.YEAR);
        mm1 = calendar.get(Calendar.MONTH);
        dd1 = calendar.get(Calendar.DAY_OF_MONTH);
        days = calendar.get(Calendar.DAY_OF_WEEK);
        change_month = mm1 + 1;

        to_day_date = yy1 + "-" + change_month + "-" + dd1;
        System.out.println("AJA KO DATE:" + to_day_date);

        day_name = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        month_name = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault());
        get_date.setText(dd1 + " "
                + month_name + ","
                + day_name + " " + yy1);

        stringDate = Integer.toString(dd1) + " " + month_name + "," + day_name + " " + yy1;


        /*bothbtn.setOnClickListener(this);
        daybtn.setOnClickListener(this);
        nightbtn.setOnClickListener(this)*/
        ;


        //Flip Action
        myFab = (FloatingActionButton) view.findViewById(R.id.flip_btn);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                flipfiled();

            }
        });


        //search button event
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(getContext(), day_night, Toast.LENGTH_LONG).show();

                String from_name = String.valueOf(from_txtview.getText());
                String to_name = String.valueOf(to_txtview.getText());
                if (from_name.isEmpty()) {
                    from_txtview.setError("");
                    warntxt.setVisibility(View.VISIBLE);
                    warntxt.setText("Please enter your origin");

                } else if (to_name.isEmpty()) {
                    to_txtview.setError("");
                    warntxt.setVisibility(View.VISIBLE);
                    warntxt.setText("Please enter your destination");


                } else if (from_name.isEmpty() && to_name.isEmpty()) {
                    from_txtview.setError("");
                    warntxt.setVisibility(View.VISIBLE);
                    warntxt.setText("Please enter your origin and destination");

                } else if (from_name.equals(to_name)) {
                    from_txtview.setError("");
                    to_txtview.setError("");
                    warntxt.setVisibility(View.VISIBLE);
                    warntxt.setText("Sorry,origin and destination name can't be same");


                } else {
                    int monthValue = mm1 + 1;
                    Intent intent = new Intent(getContext(), BusActivity.class);
                    Bundle extras = new Bundle();
                    Log.e("SEND FROM LOCATION", location_from);
                    Log.e("SEND TO LOCATION", location_to);
                    extras.putString("EXTRA_FROM", location_from);
                    extras.putString("EXTRA_TO", location_to);
                    extras.putInt("YEAR", yy1);
                    extras.putInt("MONTH", mm1);
                    extras.putInt("DAY", dd1);
                    extras.putString("TODAY_DATE", to_day_date);
                    intent.putExtras(extras);

                    stateEditor = pref_state.edit();
                    dateditor = pref_date.edit();
    
                    stateEditor.putString("day_night", day_night);
                    dateditor.putString("date", stringDate);
                    Log.e(stringDate, "DATE");

                    Log.e(day_night, "DAY_NIGHT:");
                    // editors.commit();
                    stateEditor.commit();
                    dateditor.commit();
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                }


            }
        });
        from_txtview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                from_txtview.setError(null);
                to_txtview.setError(null);
                //int i = view.getId();
                // Show_alertdialog_places(i);
                Intent intent = new Intent(getContext(), FromLocationActivity.class);
                // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        
            }
        });
        to_txtview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                from_txtview.setError(null);
                to_txtview.setError(null);
                int i = view.getId();
                // Show_alertdialog_places(i);
                Intent intent = new Intent(getContext(), ToLocationActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                //getActivity().finishAffinity();

            }
        });


        date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
    }


    public void flipfiled() {
        String temp;
        String temp2;
        if (from_txtview.getText().toString().isEmpty() || to_txtview.getText().toString().isEmpty()) {

        } else {
            if (fab_flag.equals(false)) {

                fab_flag = true;
                from_txtview.setText(location_to);
                to_txtview.setText(location_from);
                myFab.startAnimation(rotate_forward);

                temp2 = location_from;
                location_from = location_to;
                location_to = temp2;
                stateEditor = pref_state.edit();
                dateditor = pref_date.edit();
            
                SharedPreferences.Editor editor1 = pref_from.edit();
                SharedPreferences.Editor editor2 = pref_to.edit();
                editor1.putString("from_name", location_from);
                editor2.putString("to_name", location_to);
                editor1.commit();
                editor2.commit();

            

            } else if(fab_flag.equals(true)) {
                fab_flag = false;
                from_txtview.setText(location_to);
                to_txtview.setText(location_from);
                myFab.startAnimation(rotate_forward);


                temp = location_from;
                location_from = location_to;
                location_to = temp;
                SharedPreferences.Editor editor1 = pref_from.edit();
                SharedPreferences.Editor editor2 = pref_to.edit();
                editor1.putString("from_name", location_from);
                editor2.putString("to_name", location_to);
                editor1.commit();
                editor2.commit();

                Log.e("AFTER_FLIP_from", location_from);
                Log.e("AFTER_FLIP_to", location_to);


            }
        }
    }

    public void checkRadio(String day_night) {
        switch (day_night) {
            case "DAY":
                daybtn.setChecked(true);
                break;
            case "NIGHT":
                nightbtn.setChecked(true);
                break;
            case "BOTH":
                bothbtn.setChecked(true);
                break;
            default:
                bothbtn.setChecked(true);
        }
    }


    @Override
    public void onStop() {
        //Toast.makeText(getContext(), "I am STOPED", Toast.LENGTH_LONG).show();
       /* from_txtview.setText("");*/
        super.onStop();
    }

    @Override
    public void onDestroy() {
        //Toast.makeText(getContext(), "I am Destroy BUS SEARCH", Toast.LENGTH_LONG).show();
        pref_from = this.getActivity().getSharedPreferences("FROMNAME", 0);
        pref_from.edit().remove("from_name").commit();

        pref_to = this.getActivity().getSharedPreferences("TONAME", 0);
        pref_to.edit().remove("to_name").commit();
        // pref_to.edit().clear();
        pref_state = this.getActivity().getSharedPreferences("STATE", 0);
        pref_state.edit().remove("day_night").commit();
        pref_date = this.getActivity().getSharedPreferences("DATE", 0);
        pref_date.edit().remove("date").commit();

        super.onDestroy();
    }

    @Override
    public void onResume() {
        //Toast.makeText(getContext(), "I am Resume", Toast.LENGTH_LONG).show();
        super.onResume();
    }


    @Override
    public void onStart() {
        // Toast.makeText(getContext(), "I am started", Toast.LENGTH_LONG).show();
        super.onStart();
    }


    public void date_update() {
        if (getArguments() != null) {
            datevalue = getArguments().getString("params");
            Log.e("DATEVALUE", datevalue);

        }

    }

    public void showDatePickerDialog() {
        DialogFragment dialogFragment = new SelectDateFragment();
        dialogFragment.show(getActivity().getFragmentManager(), "Khai");
    }


    @SuppressLint("ValidFragment")
    public class SelectDateFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, yy1, mm1, dd1);
            dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
            return dialog;
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            String months;
            SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
            SimpleDateFormat dateFormat = new SimpleDateFormat("LLL", Locale.getDefault());
            Date date = new Date(yy, mm, dd - 1);
            Date month = new Date(yy, mm, dd);
            months = dateFormat.format(month);
            //Toast.makeText(getContext(), months, Toast.LENGTH_SHORT).show();
            dayofweek = simpledateformat.format(date);
            //Toast.makeText(getContext(), dayofweek, Toast.LENGTH_SHORT).show();
            yy1 = yy;
            mm1 = mm;
            dd1 = dd;
        
            stringDate = Integer.toString(dd) + " " + months + "," + dayofweek + " " + Integer.toString(yy);
            Log.e("DATE CHAIYAAKO", stringDate);
            get_date.setText(dd + " " + months + "," + dayofweek + " " + yy);
        }


    }
}

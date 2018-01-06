import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.infobrain.meroticket.Fragments.frag_about_us;
import com.infobrain.meroticket.Fragments.frag_account;
import com.infobrain.meroticket.Fragments.frag_bus_booking;
import com.infobrain.meroticket.Fragments.frag_bus_challan;
import com.infobrain.meroticket.Fragments.frag_bus_search;
import com.infobrain.meroticket.Fragments.frag_home;
import com.infobrain.meroticket.Fragments.frag_setting;
import com.infobrain.meroticket.R;
import com.infobrain.meroticket.SqliteDB.DBHelper;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private SharedPreferences pref_from, pref_to, pref_state, pref_date;
    private static long back_pressed;
    private String userType = "Guest";
    private String login = "0";
    private NavigationView navigationView;
    private SharedPreferences userPref;
    private TextView welcome, userName;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("onCreate", "onCreate");
        //loadUserData();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

        };


        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);
        userName = (TextView) headerview.findViewById(R.id.user_name);
        welcome = (TextView) headerview.findViewById(R.id.welcome_txt);
        CheckUser();
        // CheckUser();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        displayFragment(R.id.nav_search);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (back_pressed + 2000 > System.currentTimeMillis()) {
                super.onBackPressed();
                finishAffinity();


            } else {
                Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
                back_pressed = System.currentTimeMillis();

            }


        }

    }

    public void loadUserData() {
        userPref = getSharedPreferences("LOGIN", 0);
        try {
            String username = userPref.getString("user_name", "");
            if (username.isEmpty()) {
                welcome.setVisibility(View.INVISIBLE);
                userName.setVisibility(View.INVISIBLE);
            } else {
                welcome.setVisibility(View.VISIBLE);
                userName.setVisibility(View.VISIBLE);
                userName.setText(username);
            }

            String usrpass = userPref.getString("user_pass", "");
            userType = userPref.getString("user_type", "");
            login = userPref.getString("isLogin", "");
            Log.e("USERTYPE", userType);
            Log.e("ISLOGIN", login);
        } catch (Exception ex) {
            Log.e("NULL ERROR", ex.getMessage());

        }


    }

    public void CheckUser() {
        loadUserData();
        // userType = user;
        switch (userType) {
            case "Admin":
                navigationView.getMenu().clear();
                navigationView.inflateMenu(R.menu.activity_admin_drawer);
                if (login.equals("1")) {
                    navigationView.getMenu().findItem(R.id.nav_account).setVisible(false);
                    navigationView.getMenu().findItem(R.id.nav_bus_challan).setVisible(true);
                    navigationView.getMenu().findItem(R.id.nav_signout).setVisible(true);
                } else {
                    navigationView.getMenu().findItem(R.id.nav_bus_challan).setVisible(false);
                    navigationView.getMenu().findItem(R.id.nav_account).setVisible(true);
                    navigationView.getMenu().findItem(R.id.nav_signout).setVisible(false);
                }
                break;
            case "Operator":
                navigationView.getMenu().clear();
                navigationView.inflateMenu(R.menu.activity_guest_drawer);
                if (login.equals("1")) {
                    navigationView.getMenu().findItem(R.id.nav_account).setVisible(false);
                    navigationView.getMenu().findItem(R.id.nav_signout).setVisible(true);
                } else {
                    navigationView.getMenu().findItem(R.id.nav_account).setVisible(true);
                    navigationView.getMenu().findItem(R.id.nav_signout).setVisible(false);
                }
                break;
            default:
                navigationView.getMenu().clear();
                navigationView.inflateMenu(R.menu.activity_operator_drawer);
                if (login.equals("1")) {
                    navigationView.getMenu().findItem(R.id.nav_account).setVisible(false);
                    navigationView.getMenu().findItem(R.id.nav_signout).setVisible(true);
                } else {
                    navigationView.getMenu().findItem(R.id.nav_account).setVisible(true);
                    navigationView.getMenu().findItem(R.id.nav_signout).setVisible(false);
                }
                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displayFragment(item.getItemId());
        return true;
    }

    public void displayFragment(int itemId) {
        Fragment fragment = null;
        switch (itemId) {
            case R.id.nav_home:
                fragment = new frag_home();
                break;
            case R.id.nav_search:
                fragment = new frag_bus_search();
                CheckUser();
                break;
            case R.id.nav_booking:
                fragment = new frag_bus_booking();
                break;
            case R.id.nav_setting:
                fragment = new frag_setting();
                break;
            case R.id.nav_about_us:
                fragment = new frag_about_us();
                break;
            case R.id.nav_account:
                fragment = new frag_account();
                break;
            case R.id.nav_bus_challan:
                fragment = new frag_bus_challan();
                break;
            case R.id.nav_signout:
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Please Wait...");
                progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(false);
                progressDialog.show();
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(MainActivity.this);
                }
                builder.setMessage("Do you want to Sign Out?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                final Timer t = new Timer();
                                t.schedule(new TimerTask() {
                                    public void run() {

                                        progressDialog.dismiss();

                                        t.cancel();
                                    }
                                }, 3000);
                                // DELETE USER CREDENTIALS
                                SharedPreferences.Editor editor = userPref.edit();
                                userPref = getSharedPreferences("LOGIN", 0);
                                userPref.edit().remove("user_name").commit();
                                userPref.edit().remove("user_type").commit();
                                userPref.edit().remove("user_pass").commit();
                                userPref.edit().remove("user_id_name").commit();
                                editor.putString("isLogin", "0");
                                editor.commit();
                                Fragment fragment = new frag_bus_search();
                                FragmentManager fragmentManager = getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.frame_layout, fragment);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
                                CheckUser();
                            }

                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        //.setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                break;

        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_layout, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @Override
    protected void onDestroy() {
        DBHelper hlpr = new DBHelper(MainActivity.this);
        SQLiteDatabase db = hlpr.getWritableDatabase();
        hlpr.onUpgrade(db, 0, 0);
        pref_from = this.getApplicationContext().getSharedPreferences("FROMNAME", 0);
        pref_from.edit().remove("from_name").commit();

        pref_to = this.getApplicationContext().getSharedPreferences("TONAME", 0);
        pref_to.edit().remove("to_name").commit();
        // pref_to.edit().clear();
        pref_state = this.getApplicationContext().getSharedPreferences("STATE", 0);
        pref_state.edit().remove("day_night").commit();
        pref_date = this.getApplicationContext().getSharedPreferences("DATE", 0);
        pref_date.edit().remove("date").commit();
        super.onDestroy();
    }
}

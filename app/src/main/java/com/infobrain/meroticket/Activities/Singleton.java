import android.app.Application;
public class Singleton extends Application {
    private String c_code;
    private String date;
    private String bus_id;
    private String route_id;
    private String seat_price;
    private String bus_layout;
    private String bus_name;
    private String boarding_point;
    private String selected_no;
    private String total_price;
    private String user_name;
    private String user_pass;
    private String user_Type;
    private String user_Login;

    public String getUser_Login() {
        return user_Login;
    }

    public void setUser_Login(String user_Login) {
        this.user_Login = user_Login;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public String getUser_Type() {
        return user_Type;
    }

    public void setUser_Type(String user_Type) {
        this.user_Type = user_Type;
    }

    public String getSelected_no() {
        return selected_no;
    }

    public void setSelected_no(String selected_no) {
        this.selected_no = selected_no;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getBoarding_point() {
        return boarding_point;
    }

    public void setBoarding_point(String boarding_point) {
        this.boarding_point = boarding_point;
    }

    public String getBus_layout() {
        return bus_layout;
    }

    public void setBus_layout(String bus_layout) {
        this.bus_layout = bus_layout;
    }

    public String getBus_name() {
        return bus_name;
    }

    public void setBus_name(String bus_name) {
        this.bus_name = bus_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBus_id() {
        return bus_id;
    }

    public void setBus_id(String bus_id) {
        this.bus_id = bus_id;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public String getSeat_price() {
        return seat_price;
    }

    public void setSeat_price(String seat_price) {
        this.seat_price = seat_price;
    }

    public String getC_code() {
        return c_code;
    }

    public void setC_code(String c_code) {
        this.c_code = c_code;
    }
}

public class BusLayout_DataModel {
    String bus_name, bus_features, bus_time, bus_seat_no, bus_seat_price, bus_type, bus_layout, bus_borading_point,bus_id,route_id;


    public BusLayout_DataModel(String bus_name, String bus_features, String bus_time, String bus_seat_no, String bus_seat_price, String bus_type, String bus_layout, String bus_borading_point,String bus_id,String route_id) {
        this.bus_name = bus_name;
        this.bus_features = bus_features;
        this.bus_time = bus_time;
        this.bus_seat_no = bus_seat_no;
        this.bus_seat_price = bus_seat_price;
        this.bus_type = bus_type;
        this.bus_layout = bus_layout;
        this.bus_borading_point = bus_borading_point;
        this.bus_id=bus_id;
        this.route_id=route_id;

    }

    public String getBus_type() {
        return bus_type;
    }

    public String getBus_name() {
        return bus_name;
    }

    public String getBus_features() {
        return bus_features;
    }

    public String getBus_time() {
        return bus_time;
    }

    public String getBus_seat_no() {
        return bus_seat_no;
    }

    public String getBus_seat_price() {
        return bus_seat_price;
    }

    public String getBus_layout() {
        return bus_layout;
    }

    public String getBus_borading_point() {
        return bus_borading_point;
    }

    public String getBus_id() {
        return bus_id;
    }

    public String getRoute_id() {

        return route_id;
    }
}

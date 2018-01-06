public class Seat_DataModel {

    String seat_name, seat_price, seat_category, seat_position;

    public Seat_DataModel(String seat_name,String seat_price,String seat_category,String seat_position) {
        this.seat_name = seat_name;
        this.seat_price = seat_price;
        this.seat_category = seat_category;
        this.seat_position = seat_position;
    }

    public String getSeat_name() {
        return seat_name;
    }

    public void setSeat_name(String seat_name) {
        this.seat_name = seat_name;
    }

    public String getSeat_price() {
        return seat_price;
    }

    public void setSeat_price(String seat_price) {
        this.seat_price = seat_price;
    }

    public String getSeat_category() {
        return seat_category;
    }

    public void setSeat_category(String seat_category) {
        this.seat_category = seat_category;
    }

    public String getSeat_position() {
        return seat_position;
    }

    public void setSeat_position(String seat_position) {
        this.seat_position = seat_position;
    }
}

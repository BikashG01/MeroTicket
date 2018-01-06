import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.infobrain.meroticket.R;


public class SeatSelectionAdapter extends BaseAdapter {
    private Context mContext;

    public SeatSelectionAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return Seat_Items.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            GridView.LayoutParams params = new GridView.LayoutParams(GridView.LayoutParams.WRAP_CONTENT, GridView.LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(new GridView.LayoutParams(params));
            imageView.setPadding(5, 5, 5, 5);
            //imageView.setAdjustViewBounds(true);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setBackgroundResource(Seat_Items[i]);
        return imageView;
    }

    @Override
    public boolean isEnabled(int position) {
        switch (Seat_Items[position].intValue()) {
            case 0:
                return false;
            case R.drawable.icon_bus_steering:
                return false;
            default:
                return true;
        }
    }

    public Integer[] Seat_Items = {
            0, 0
            , 0, 0
            , R.drawable.icon_bus_steering, 0
            , 0, 0
            , 0, 0
            , 0, 0
            , 0, 0
            , 0, 0
            , 0, 0
            , R.drawable.icon_bus_seat_normal, R.drawable.icon_bus_seat_normal
            , R.drawable.icon_bus_seat_normal, R.drawable.icon_bus_seat_normal
            , 0, R.drawable.icon_bus_seat_normal
            , R.drawable.icon_bus_seat_normal, R.drawable.icon_bus_seat_normal
            , R.drawable.icon_bus_seat_normal, 0
            , R.drawable.icon_bus_seat_normal, R.drawable.icon_bus_seat_normal
            , R.drawable.icon_bus_seat_normal, R.drawable.icon_bus_seat_normal
            , 0, R.drawable.icon_bus_seat_normal
            , R.drawable.icon_bus_seat_normal, R.drawable.icon_bus_seat_normal
            , R.drawable.icon_bus_seat_normal, 0
            , R.drawable.icon_bus_seat_normal, R.drawable.icon_bus_seat_normal
            , R.drawable.icon_bus_seat_normal, R.drawable.icon_bus_seat_normal
            , 0, R.drawable.icon_bus_seat_normal
            , R.drawable.icon_bus_seat_normal, R.drawable.icon_bus_seat_normal,
            R.drawable.icon_bus_seat_normal
            , 0, R.drawable.icon_bus_seat_normal
            , R.drawable.icon_bus_seat_normal, R.drawable.icon_bus_seat_normal
            , R.drawable.icon_bus_seat_normal, R.drawable.icon_bus_seat_normal
            , R.drawable.icon_bus_seat_normal, R.drawable.icon_bus_seat_normal
    };


}

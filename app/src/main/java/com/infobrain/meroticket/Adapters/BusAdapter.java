import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.infobrain.meroticket.DataModels.BusLayout_DataModel;
import com.infobrain.meroticket.R;

import java.util.List;

/**
 * Created by bikas on 11/15/2017.
 */

public class BusAdapter extends BaseAdapter {
    List<BusLayout_DataModel> busLayout_dataModels;
    Context context;

    public BusAdapter(List<BusLayout_DataModel> busLayout_dataModels, Context context) {
        this.busLayout_dataModels = busLayout_dataModels;
        this.context = context;
    }

    @Override
    public int getCount() {
        return busLayout_dataModels.size();
    }

    @Override
    public Object getItem(int i) {
        return busLayout_dataModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return busLayout_dataModels.indexOf(getItem(i));
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        BusViewHolder mViewholder = null;
        LayoutInflater minflator = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = minflator.inflate(R.layout.custom_buslist_layout, null);
            mViewholder = new BusViewHolder();

            mViewholder.busName = (TextView) view.findViewById(R.id.bus_name);
            mViewholder.busTime = (TextView) view.findViewById(R.id.bus_time);
            mViewholder.busFeatures = (TextView) view.findViewById(R.id.bus_features);
            mViewholder.busSeat = (TextView) view.findViewById(R.id.bus_seats);
            mViewholder.busSeatPrice = (TextView) view.findViewById(R.id.bus_price);
            mViewholder.busType=(TextView)view.findViewById(R.id.bus_type);
            view.setTag(mViewholder);
        } else {
            mViewholder = (BusViewHolder) view.getTag();
        }
        BusLayout_DataModel data_model = (BusLayout_DataModel) getItem(i);
        mViewholder.busName.setText(data_model.getBus_name());
        mViewholder.busTime.setText(data_model.getBus_time());
        mViewholder.busSeatPrice.setText(data_model.getBus_seat_price());
        mViewholder.busSeat.setText(data_model.getBus_seat_no());
        mViewholder.busFeatures.setText(data_model.getBus_features());
        mViewholder.busType.setText(data_model.getBus_type());
        return view;
    }

    public class BusViewHolder {
        TextView busName, busTime, busSeat, busSeatPrice, busFeatures,busType;
    }
}

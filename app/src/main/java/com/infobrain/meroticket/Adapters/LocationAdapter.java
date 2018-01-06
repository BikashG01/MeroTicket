import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.infobrain.meroticket.DataModels.Location_DataModel;
import com.infobrain.meroticket.R;
import java.util.List;

/**
 * Created by bikas on 11/15/2017.
 */

public class LocationAdapter extends BaseAdapter {
    List<Location_DataModel> location_name_list;
    Context context;
    //LayoutInflater layoutInflater;

    public LocationAdapter(Context context, List<Location_DataModel> locationList) {
        this.context = context;
        this.location_name_list = locationList;
       /* this.layoutInflater = layoutInflater;*/
    }

    @Override
    public int getCount() {
        return location_name_list.size();
    }

    @Override
    public Object getItem(int position) {
        return location_name_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return location_name_list.indexOf(getItem(position));
    }

    public class MyViewHolder {
        TextView nameView;
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        MyViewHolder mViewholder = null;
        LayoutInflater minflator = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = minflator.inflate(R.layout.location_custom_list, null);
            mViewholder = new MyViewHolder();
            mViewholder.nameView = (TextView) view.findViewById(R.id.location_name);
            view.setTag(mViewholder);
        } else {
            mViewholder = (MyViewHolder) view.getTag();
        }
        Location_DataModel model = (Location_DataModel) getItem(position);
        mViewholder.nameView.setText(model.getLocation_name());
        return view;
    }


}

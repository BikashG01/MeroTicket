import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.infobrain.meroticket.Activities.BookingDetails;
import com.infobrain.meroticket.DataModels.BordingPointModel;
import com.infobrain.meroticket.DataModels.BusLayout_DataModel;
import com.infobrain.meroticket.R;


import java.util.List;

public class BordingPointAdapter extends BaseAdapter{
    List<BordingPointModel> bordingPointModels;
    Context context;

    public BordingPointAdapter(List<BordingPointModel> bordingPointModels, Context context) {
        this.bordingPointModels = bordingPointModels;
        this.context = context;
    }


    @Override
    public int getCount() {
        return 0;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
    public class BoardingViewHolder {
        TextView busName;
    }
}

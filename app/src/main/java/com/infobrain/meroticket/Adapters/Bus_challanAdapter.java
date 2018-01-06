import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.infobrain.meroticket.DataModels.BusChallanModel;
import com.infobrain.meroticket.DataModels.BusLayout_DataModel;
import com.infobrain.meroticket.R;
import java.util.List;



public class Bus_challanAdapter extends BaseAdapter {
    List<BusChallanModel> busChallanModels;
    Context context;

    public Bus_challanAdapter(List<BusChallanModel> busChallanModels, Context context) {
        this.busChallanModels = busChallanModels;
        this.context = context;
    }

    @Override
    public int getCount() {
        return busChallanModels.size();
    }

    @Override
    public Object getItem(int i) {
        return busChallanModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return busChallanModels.indexOf(getItem(i));
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Bus_challanAdapter.BusViewHolder mViewholder = null;
        LayoutInflater minflator = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = minflator.inflate(R.layout.custom_ticket_details, null);
            mViewholder = new Bus_challanAdapter.BusViewHolder();
            mViewholder.serialNo=view.findViewById(R.id.serial_id);
            mViewholder.ticketNo = view.findViewById(R.id.ticket_no);
            mViewholder.seatNo = view.findViewById(R.id.seat_NO);
            mViewholder.paymentMode = view.findViewById(R.id.payment_mode);
            mViewholder.totalAmt = view.findViewById(R.id.total_amount);
            view.setTag(mViewholder);
        } else {
            mViewholder = (Bus_challanAdapter.BusViewHolder) view.getTag();
        }
        BusChallanModel data_model = (BusChallanModel) getItem(i);
        mViewholder.serialNo.setText(""+(i+1+"."));
        mViewholder.ticketNo.setText(data_model.getTicketNo());
        mViewholder.seatNo.setText(data_model.getSeatNo());
        mViewholder.paymentMode.setText(data_model.getPaymentMode());
        mViewholder.totalAmt.setText(data_model.getTotalAmt());

        return view;
    }

    public class BusViewHolder {
        TextView ticketNo, seatNo, paymentMode, totalAmt,serialNo;
    }
}

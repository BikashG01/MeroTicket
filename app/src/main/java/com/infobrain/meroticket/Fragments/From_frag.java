import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.infobrain.meroticket.R;

/**
 * Created by bikas on 12/17/2017.
 */

public class From_frag extends Fragment {
    TextView from_loc;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dummy_from, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("FROM");
        from_loc = (TextView) view.findViewById(R.id.id_location);
        from_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = from_loc.getText().toString();
                Fragment fragment = new frag_bus_search();
                Bundle bundle = new Bundle();
                bundle.putString("location", location);
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction.commit();

            }
        });


    }

}

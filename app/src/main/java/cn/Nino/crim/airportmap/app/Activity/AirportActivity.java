package cn.Nino.crim.airportmap.app.Activity;

import android.app.Fragment;
import cn.Nino.crim.airportmap.app.Fragment.AirportFragment;

public class AirportActivity extends SingleFragmentActivity {

    @Override
    protected Fragment creatFragment() {
        return new AirportFragment();
    }

}

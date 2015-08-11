package cn.Nino.crim.airportmap.app.Activity;

import android.app.Fragment;
import cn.Nino.crim.airportmap.app.Fragment.LogoFragment;

/**
 * Created by Administrator on 2015/8/11 0011.
 */
public class LogoActivity extends SingleFragmentActivity {
    @Override
    protected Fragment creatFragment() {
        return new LogoFragment();
    }
}

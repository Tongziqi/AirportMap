package cn.Nino.crim.airportmap.app.Activity;

import android.app.Fragment;
import cn.Nino.crim.airportmap.app.Fragment.SearchFragment;

/**
 * Created by Administrator on 2015/3/31 0031.
 */
public class SearchActivity extends SingleFragmentActivity {
    @Override
    protected Fragment creatFragment() {
        String endPlace = getIntent().getStringExtra(SearchFragment.EXTRA_END_PLACE);
        String startPointX = getIntent().getStringExtra(SearchFragment.EXTRA_START_PLACE_X);
        String startPointY = getIntent().getStringExtra(SearchFragment.EXTRA_START_PLACE_Y);
        String startPointZ = getIntent().getStringExtra(SearchFragment.EXTRA_START_PLACE_Z);

        return SearchFragment.newInstance(endPlace, startPointX, startPointY, startPointZ);
    }

}

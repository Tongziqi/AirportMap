package cn.Nino.crim.airportmap.app.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import cn.Nino.crim.airportmap.app.Point.Point;
import cn.Nino.crim.airportmap.app.Point.PointLab;
import cn.Nino.crim.airportmap.app.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/3/31 0031.
 */
public class SearchFragment extends Fragment {
    public static final String EXTRA_END_PLACE = "endPlace";
    private ArrayList<Point> mPointList;
    private EditText mStartPlace, mEndPlace;
    private String mEndPlaceString;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //不用这种方法，因为这种方法每个fragment绑定了一个activity
        // mEndPlaceString = getActivity().getIntent().getStringExtra(AirportFragment.SEARCH_BUTTON);
        //这里我们将传过来的信息保存在argument bundle里面
        mEndPlaceString = getArguments().getString(EXTRA_END_PLACE);
        Toast.makeText(getActivity(), mEndPlaceString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        mStartPlace = (EditText) view.findViewById(R.id.start_place);
        mStartPlace.setText(R.string.myPlace);
        mStartPlace.setFocusable(false);
        mStartPlace.setEnabled(false);
        mEndPlace = (EditText) view.findViewById(R.id.end_place);
        mEndPlace.setText(mEndPlaceString);
        mEndPlace.setFocusable(false);
        mEndPlace.setEnabled(false);

        mPointList = PointLab.getmPointLab(getActivity(), mEndPlaceString).getmPointList();

        ArrayAdapter<Point> arrayAdapter = new ArrayAdapter<Point>(getActivity(), android.R.layout.simple_list_item_1, mPointList);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mEndPlace.setText(mPointList.get(position).getmTittle());
                Toast.makeText(getActivity().getApplicationContext(), mPointList.get(position).getmTittle(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public static SearchFragment newInstance(String endPlace) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_END_PLACE, endPlace);
        SearchFragment searchFragment = new SearchFragment();
        searchFragment.setArguments(args);
        return searchFragment;
    }
}

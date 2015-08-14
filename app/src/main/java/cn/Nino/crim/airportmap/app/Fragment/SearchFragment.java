package cn.Nino.crim.airportmap.app.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import cn.Nino.crim.airportmap.app.Activity.AirportActivity;
import cn.Nino.crim.airportmap.app.Point.Point;
import cn.Nino.crim.airportmap.app.Point.PointLab;
import cn.Nino.crim.airportmap.app.R;
import cn.Nino.crim.airportmap.app.Tools.AutoCompleteAdapter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Administrator on 2015/3/31 0031.
 */
public class SearchFragment extends Fragment {
    public static final String EXTRA_START_PLACE_X = "startx";
    public static final String EXTRA_START_PLACE_Y = "starty";
    public static final String EXTRA_START_PLACE_Z = "startz";
    public static final String EXTRA_END_PLACE_X = "endx";
    public static final String EXTRA_END_PLACE_Y = "endy";
    public static final String EXTRA_END_PLACE_Z = "endz";
    public static final String EXTRA_MID_PLACE_X = "midx";
    public static final String EXTRA_MID_PLACE_Y = "midy";
    public static final String EXTRA_MID_PLACE_Z = "midz";
    public static final String EXTRA_END_PLACE_NAME = "endPlaceName";
    public static final String EXTRA_MID_PLACE_NAME = "midPlaceName";
    public static final String EXTRA_END_Point_BOOL = "endPoint";
    private EditText mStartPlace;
    private AutoCompleteTextView mEndPlace;
    private AutoCompleteTextView mMidPlace1;
    private ImageButton navigationButton;
    private ImageButton middlePlaceButton;
    private Point endPoint;
    private Point midPoint;
    private LinearLayout findmid_linearLayout1;
    double startPointX, startPointY, startPointZ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //不用这种方法，因为这种方法每个fragment绑定了一个activity
        // mEndPlaceString = getActivity().getIntent().getStringExtra(AirportFragment.SEARCH_BUTTON);
        //这里我们将传过来的信息保存在argument bundle里面
        startPointX = Double.parseDouble(getArguments().getString(EXTRA_START_PLACE_X));
        startPointY = Double.parseDouble(getArguments().getString(EXTRA_START_PLACE_Y));
        startPointZ = Double.parseDouble(getArguments().getString(EXTRA_START_PLACE_Z));

        midPoint = new Point("test", 0.0, 0.0, 0.0);
        //mPointList = PointLab.getmPointLab(getActivity(), mEndPlaceString).getmPointList();
        //arrayAdapter = new ArrayAdapter<Point>(getActivity(), android.R.layout.simple_list_item_1, mPointList);
    }

    @Override
    public void onResume() {
        //arrayAdapter.notifyDataSetChanged();
        super.onResume();
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.search_fragment, container, false);
        final String[] points = getResources().getStringArray(R.array.allPoints);
        final ArrayList<String> arrayListPoint = new ArrayList<String>();
        arrayListPoint.addAll(Arrays.asList(points).subList(0, points.length - 1));

        mStartPlace = (EditText) view.findViewById(R.id.start_place);
        mStartPlace.setText(R.string.myPlace);
        mStartPlace.setFocusable(false);
        mStartPlace.setEnabled(false);
        mEndPlace = (AutoCompleteTextView) view.findViewById(R.id.end_place);
        mEndPlace.setText(R.string.end_place_you_want_to_go);
        mEndPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEndPlace.getText().toString().length() != 0) {
                    mEndPlace.setText(null);
                } else {
                    mEndPlace.setText(R.string.end_place_you_want_to_go);
                }
            }
        });
        AutoCompleteAdapter autoCompleteAdapter = new AutoCompleteAdapter(getActivity(), arrayListPoint, 100);//配置Adaptor
        mEndPlace.setAdapter(autoCompleteAdapter);
        mEndPlace.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String text = mEndPlace.getText().toString();
                endPoint = PointLab.getmPointLab(getActivity(), text).getmPointList().get(0);
                Toast.makeText(getActivity(), "您要去的终点为:" + endPoint.getmTittle(), Toast.LENGTH_SHORT).show();
            }
        });
        navigationButton = (ImageButton) view.findViewById(R.id.navigation_button);
        navigationButton.setEnabled(true);

        middlePlaceButton = (ImageButton) view.findViewById(R.id.find_mid_place);
        middlePlaceButton.setEnabled(true);

        findmid_linearLayout1 = (LinearLayout) view.findViewById(R.id.mid_place_first);


        mMidPlace1 = (AutoCompleteTextView) view.findViewById(R.id.mid_place_1);
        mMidPlace1.setText(R.string.middle_place_you_want_to_go);
        mMidPlace1.setEnabled(false);
        mMidPlace1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMidPlace1.getText().toString().length() != 0) {
                    mMidPlace1.setText(null);
                } else {
                    mMidPlace1.setText(R.string.middle_place_you_want_to_go);
                }
            }
        });
        mMidPlace1.setAdapter(autoCompleteAdapter);
        mMidPlace1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String text = mMidPlace1.getText().toString();
                midPoint = PointLab.getmPointLab(getActivity(), text).getmPointList().get(0);
                Toast.makeText(getActivity(), "您要去的中点为:" + midPoint.getmTittle(), Toast.LENGTH_SHORT).show();
            }
        });


        middlePlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findmid_linearLayout1.setVisibility(View.VISIBLE);
                mMidPlace1.setEnabled(true);
            }
        });


        navigationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AirportActivity.class);
                intent.putExtra(EXTRA_END_PLACE_NAME, endPoint.getmTittle());
                intent.putExtra(EXTRA_END_PLACE_X, endPoint.getPointX());
                intent.putExtra(EXTRA_END_PLACE_Y, endPoint.getPointY());
                intent.putExtra(EXTRA_END_PLACE_Z, endPoint.getPointZ());

                intent.putExtra(EXTRA_MID_PLACE_NAME, midPoint.getmTittle());
                intent.putExtra(EXTRA_MID_PLACE_X, midPoint.getPointX());
                intent.putExtra(EXTRA_MID_PLACE_Y, midPoint.getPointY());
                intent.putExtra(EXTRA_MID_PLACE_Z, midPoint.getPointZ());

                intent.putExtra(EXTRA_START_PLACE_X, startPointX);
                intent.putExtra(EXTRA_START_PLACE_Y, startPointY);
                intent.putExtra(EXTRA_START_PLACE_Z, startPointZ);
                intent.putExtra(EXTRA_END_Point_BOOL, false);  //这里面改变notHaveEndPoint里面的值
                getActivity().setResult(Activity.RESULT_OK, intent);
                getActivity().finish();  //finished这个activity
                startActivity(intent);
            }
        });
        return view;
    }

    public static SearchFragment newInstance(String pointX, String pointY, String pointZ) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_START_PLACE_X, pointX);
        args.putSerializable(EXTRA_START_PLACE_Y, pointY);
        args.putSerializable(EXTRA_START_PLACE_Z, pointZ);
        SearchFragment searchFragment = new SearchFragment();
        searchFragment.setArguments(args);
        return searchFragment;
    }


}

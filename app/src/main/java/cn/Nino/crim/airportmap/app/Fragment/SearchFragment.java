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

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/3/31 0031.
 */
public class SearchFragment extends Fragment {
    public static final String EXTRA_END_PLACE = "endPlace";
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
    private ArrayList<Point> mPointList;
    private EditText mStartPlace, mEndPlace, mMidPlace;
    private String mEndPlaceString;
    private Button navigationButton;
    private Button middlePlaceButton;
    private Button confirmmiddlePlaceButton;
    private Point endPoint;
    private Point midPoint;
    double startPointX, startPointY, startPointZ;
    ArrayAdapter<Point> arrayAdapter;
    boolean hasmid = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //不用这种方法，因为这种方法每个fragment绑定了一个activity
        // mEndPlaceString = getActivity().getIntent().getStringExtra(AirportFragment.SEARCH_BUTTON);
        //这里我们将传过来的信息保存在argument bundle里面
        mEndPlaceString = getArguments().getString(EXTRA_END_PLACE);
        startPointX = Double.parseDouble(getArguments().getString(EXTRA_START_PLACE_X));
        startPointY = Double.parseDouble(getArguments().getString(EXTRA_START_PLACE_Y));
        startPointZ = Double.parseDouble(getArguments().getString(EXTRA_START_PLACE_Z));

        midPoint = new Point("test", 0.0, 0.0, 0.0);
        mPointList = PointLab.getmPointLab(getActivity(), mEndPlaceString).getmPointList();
        arrayAdapter = new ArrayAdapter<Point>(getActivity(), android.R.layout.simple_list_item_1, mPointList);

    }

    @Override
    public void onResume() {
        arrayAdapter.notifyDataSetChanged();
        super.onResume();
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.search_fragment, container, false);
        mStartPlace = (EditText) view.findViewById(R.id.start_place);
        mStartPlace.setText(R.string.myPlace);
        mStartPlace.setFocusable(false);
        mStartPlace.setEnabled(false);
        mEndPlace = (EditText) view.findViewById(R.id.end_place);
        mEndPlace.setText(mEndPlaceString);
        mEndPlace.setFocusable(false);
        mEndPlace.setEnabled(false);
        mMidPlace = (EditText) view.findViewById(R.id.mid_place);
        mMidPlace.setText(R.string.middle_place_you_want_to_go);
        mEndPlace.setFocusable(false);
        mMidPlace.setEnabled(false);
        navigationButton = (Button) view.findViewById(R.id.navigation_button);
        navigationButton.setEnabled(false);
        middlePlaceButton = (Button) view.findViewById(R.id.find_mid_place);
        middlePlaceButton.setEnabled(false);
        confirmmiddlePlaceButton = (Button) view.findViewById(R.id.confirm_mid_place);
        confirmmiddlePlaceButton.setEnabled(false);

        mMidPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMidPlace.getText().toString().length() != 0) {
                    mMidPlace.setText(null);
                } else {
                    mMidPlace.setText(R.string.middle_place_you_want_to_go);
                }
            }
        });

        middlePlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMidPlace.setFocusable(true);
                mMidPlace.setEnabled(true);
                navigationButton.setEnabled(false);
                confirmmiddlePlaceButton.setEnabled(true);
            }
        });


        final ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!hasmid) {
                    endPoint = mPointList.get(position);
                    mEndPlace.setText(mPointList.get(position).getmTittle());
                } else {
                    midPoint = mPointList.get(position);
                    mMidPlace.setText(mPointList.get(position).getmTittle());
                    navigationButton.setEnabled(true);
                    confirmmiddlePlaceButton.setEnabled(false);
                }
                navigationButton.setEnabled(true);
                middlePlaceButton.setEnabled(true);
            }
        });

        confirmmiddlePlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPointList = PointLab.getmPointLab(getActivity(), String.valueOf(mMidPlace.getText())).getmPointList();
                arrayAdapter = new ArrayAdapter<Point>(getActivity(), android.R.layout.simple_list_item_1, mPointList);
                hasmid = true;
                arrayAdapter.notifyDataSetChanged();
                listView.setAdapter(arrayAdapter);
                //Log.e("新的List", String.valueOf(mPointList));
            }
        });
        arrayAdapter.notifyDataSetChanged();

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

    public static SearchFragment newInstance(String endPlace, String pointX, String pointY, String pointZ) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_END_PLACE, endPlace);
        args.putSerializable(EXTRA_START_PLACE_X, pointX);
        args.putSerializable(EXTRA_START_PLACE_Y, pointY);
        args.putSerializable(EXTRA_START_PLACE_Z, pointZ);
        SearchFragment searchFragment = new SearchFragment();
        searchFragment.setArguments(args);
        return searchFragment;
    }


}

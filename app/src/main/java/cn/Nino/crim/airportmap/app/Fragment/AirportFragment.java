package cn.Nino.crim.airportmap.app.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import cn.Nino.crim.airportmap.app.Activity.SearchActivity;
import cn.Nino.crim.airportmap.app.Map.Map;
import cn.Nino.crim.airportmap.app.Point.Point;
import cn.Nino.crim.airportmap.app.R;
import cn.Nino.crim.airportmap.app.net.NetConnection;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/3/26 0026.
 */
public class AirportFragment extends Fragment {
    public static final String TAG = "MapActivity";
    public static final int SEARCH_CODE = 1;
    private ZoomControls mZoomControls;
    private Button mButtonB1, mButtonF1, mButtonF2;
    private ImageButton mImageButtonLocation, mImageButtonSearch;
    private EditText mThePlaceYouWantGo;
    private Handler refurbishHandler = new Handler();
    private Point endPoint;

    ArrayList<Point> mPoints;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String name = getActivity().getIntent().getStringExtra(SearchFragment.EXTRA_END_PLACE_NAME);
        double x = getActivity().getIntent().getDoubleExtra(SearchFragment.EXTRA_END_PLACE_X, 0);
        double y = getActivity().getIntent().getDoubleExtra(SearchFragment.EXTRA_END_PLACE_Y, 0);
        double z = getActivity().getIntent().getDoubleExtra(SearchFragment.EXTRA_END_PLACE_Z, 0);
        endPoint = new Point(name, x, y, z);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.airport_fragemnt, container, false);
        mZoomControls = (ZoomControls) view.findViewById(R.id.zoomControls);
        mButtonB1 = (Button) view.findViewById(R.id.button_b1);
        mButtonF1 = (Button) view.findViewById(R.id.button_f1);
        mButtonF2 = (Button) view.findViewById(R.id.button_f2);
        mThePlaceYouWantGo = (EditText) view.findViewById(R.id.search_place);
        mThePlaceYouWantGo.setText(R.string.search_place);
        mImageButtonLocation = (ImageButton) view.findViewById(R.id.location_button);
        mImageButtonSearch = (ImageButton) view.findViewById(R.id.search_place_button);

        mZoomControls.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "放大图片", Toast.LENGTH_SHORT).show();
            }
        });

        mZoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "缩小图片", Toast.LENGTH_SHORT).show();
            }
        });

        mImageButtonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new NetTask().execute();
                refurbishHandler.removeCallbacks(runnable);
                refurbishHandler.postDelayed(runnable, 1000);  // 定时刷新任务
                Log.e(TAG,
                        "name:" + endPoint.getmTittle()
                                + "  x:" + String.valueOf(endPoint.getPointX())
                                + "  y:" + String.valueOf(endPoint.getPointY())
                                + "  sz:" + String.valueOf(endPoint.getPointZ()));
            }
        });
        mImageButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refurbishHandler.removeCallbacks(runnable);
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra(SearchFragment.EXTRA_END_PLACE, String.valueOf(mThePlaceYouWantGo.getText()));
                startActivityForResult(intent, SEARCH_CODE);
            }
        });

        mButtonB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map.getMapActivity().setBackground(0);
            }
        });
        mButtonF1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map.getMapActivity().setBackground(1);
            }
        });
        mButtonF2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map.getMapActivity().setBackground(2);
            }
        });
        mThePlaceYouWantGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mThePlaceYouWantGo.getText().toString().length() != 0) {
                    mThePlaceYouWantGo.setText(null);
                } else {
                    mThePlaceYouWantGo.setText(R.string.search_place);
                }
            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        refurbishHandler.removeCallbacks(runnable);
    }

    private class NetTask extends AsyncTask<Void, Void, ArrayList<Point>> {

        @Override
        protected ArrayList<Point> doInBackground(Void... params) {
            return new NetConnection().getPoint();
        }


        @Override
        protected void onPostExecute(ArrayList<Point> points) {
            mPoints = points;
            if (mPoints.size() == 1) {
                Map.getMapActivity().cleanPoint();
                Map.getMapActivity().checkFloorZ(mPoints.get(0).getPointZ());
                Map.getMapActivity().redrawPoint(mPoints.get(0).getPointX(), mPoints.get(0).getPointY(), mPoints.get(0).getPointZ());
            } else {
                Map.getMapActivity().redrawLine(mPoints);
            }
        }
    }

    private Runnable runnable = new Runnable() {
        public void run() {
            new NetTask().execute();
            refurbishHandler.postDelayed(this, 1000);
        }
    };


}

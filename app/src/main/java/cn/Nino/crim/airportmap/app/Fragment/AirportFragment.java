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
import cn.Nino.crim.airportmap.app.Map.MapInSize;
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
    private Point startPoint;
    private String startPointAndendPoint;
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
                // refurbishHandler.removeCallbacks(runnable);
                // initMap();
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
                                + "  z:" + String.valueOf(endPoint.getPointZ()));
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
                MapInSize.getMapActivity().setBackground(0);
            }
        });
        mButtonF1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapInSize.getMapActivity().setBackground(1);
            }
        });
        mButtonF2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapInSize.getMapActivity().setBackground(2);
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
            if (endPoint.getmTittle() == null) {
                return new NetConnection().getPoint();
            } else {
                startPointAndendPoint="0.1,0.1,0.0,0.9,0.2,0.0";
                return new NetConnection().getPathPoint(startPointAndendPoint);
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Point> points) {
            mPoints = points;
            if (mPoints.size() == 1) {
                startPoint = mPoints.get(0);
                MapInSize.getMapActivity().cleanPoint();
                MapInSize.getMapActivity().checkFloorZ(mPoints.get(0).getPointZ());
                MapInSize.getMapActivity().redrawPoint(mPoints.get(0).getPointX(), mPoints.get(0).getPointY(), mPoints.get(0).getPointZ());
            } else {
                MapInSize.getMapActivity().redrawLine(mPoints);
            }
        }
    }

    private Runnable runnable = new Runnable() {
        public void run() {
            new NetTask().execute();
            refurbishHandler.postDelayed(this, 1000);
        }
    };

    private void initMap() {
        if (startPoint != null && endPoint != null) {
            String startPointString = startPoint.getPointString(startPoint);
            String endPointString = endPoint.getPointString(endPoint);
            startPointAndendPoint = startPointString + "," + endPointString;
            Log.e(TAG, startPointAndendPoint);
        }

    }
}

package cn.Nino.crim.airportmap.app.Fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
    public static final String TAG = " ";
    public static final int SEARCH_CODE = 1;
    private int firstStepDrawLines = 1;
    private Button mButtonB1, mButtonF1, mButtonF2;
    private ImageButton mImageButtonLocation, mImageButtonSearch;
    private EditText mThePlaceYouWantGo;
    private Handler refurbishHandler = new Handler();
    private Point endPoint = null;
    private Point startPoint = null;
    private Point midPoint = null;
    private String startPointAndEndPoint;
    private String startPointAndMidPoint;
    private String midPointAndEndPoint;
    Boolean notHaveEndPoint = true;
    Boolean hadStepIntoMidPoint = false;
    Boolean youDonnotMove = false;
    ArrayList<Point> mPoints;
    ArrayList<Point> pointArrayListLine = new ArrayList<Point>(); //存储得到的路径 保证不每次都重绘


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        String endPointName = getActivity().getIntent().getStringExtra(SearchFragment.EXTRA_END_PLACE_NAME);
        double endPointX = getActivity().getIntent().getDoubleExtra(SearchFragment.EXTRA_END_PLACE_X, 0);
        double endPointY = getActivity().getIntent().getDoubleExtra(SearchFragment.EXTRA_END_PLACE_Y, 0);
        double endPointZ = getActivity().getIntent().getDoubleExtra(SearchFragment.EXTRA_END_PLACE_Z, 0);
        notHaveEndPoint = getActivity().getIntent().getBooleanExtra(SearchFragment.EXTRA_END_Point_BOOL, true);
        endPoint = new Point(endPointName, endPointX, endPointY, endPointZ);

        String midPointName = getActivity().getIntent().getStringExtra(SearchFragment.EXTRA_END_PLACE_NAME);
        double midPointX = getActivity().getIntent().getDoubleExtra(SearchFragment.EXTRA_MID_PLACE_X, 0);
        double midPointY = getActivity().getIntent().getDoubleExtra(SearchFragment.EXTRA_MID_PLACE_Y, 0);
        double midPointZ = getActivity().getIntent().getDoubleExtra(SearchFragment.EXTRA_MID_PLACE_Z, 0);
        midPoint = new Point(midPointName, midPointX, midPointY, midPointZ);


        double startPointX = getActivity().getIntent().getDoubleExtra(SearchFragment.EXTRA_START_PLACE_X, 0);
        double startPointY = getActivity().getIntent().getDoubleExtra(SearchFragment.EXTRA_START_PLACE_Y, 0);
        double startPointZ = getActivity().getIntent().getDoubleExtra(SearchFragment.EXTRA_START_PLACE_Z, 0);
        startPoint = new Point("startPoint", startPointX, startPointY, startPointZ);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.airport_fragemnt, container, false);
        mButtonB1 = (Button) view.findViewById(R.id.button_b1);
        mButtonF1 = (Button) view.findViewById(R.id.button_f1);
        mButtonF2 = (Button) view.findViewById(R.id.button_f2);
        mThePlaceYouWantGo = (EditText) view.findViewById(R.id.search_place);
        mThePlaceYouWantGo.setText(R.string.search_place);
        mImageButtonLocation = (ImageButton) view.findViewById(R.id.location_button);
        mImageButtonSearch = (ImageButton) view.findViewById(R.id.search_place_button);


        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 100) {
                    pointArrayListLine = (ArrayList<Point>) msg.obj;
                    Log.e("获得的路径:", pointArrayListLine.toString());
                }
            }
        };

        //定位是每秒都需要
        //但是规划路径只需要特定的时候才需要
        new NetTask(handler).execute();
        refurbishHandler.removeCallbacks(runnable);
        refurbishHandler.postDelayed(runnable, 50);  // 定时刷新任务  //看服务器的情况很可能设定为20刷新不出来

        //new NetGetPoint().execute();

/*        mImageButtonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new NetTask(refurbishHandler).execute();
                refurbishHandler.removeCallbacks(runnable);
                refurbishHandler.postDelayed(runnable, 50);  // 定时刷新任务
            }
        });*/
        mImageButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refurbishHandler.removeCallbacks(runnable);
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra(SearchFragment.EXTRA_END_PLACE, String.valueOf(mThePlaceYouWantGo.getText()));
                intent.putExtra(SearchFragment.EXTRA_START_PLACE_X, String.valueOf(startPoint.getPointX()));
                intent.putExtra(SearchFragment.EXTRA_START_PLACE_Y, String.valueOf(startPoint.getPointY()));
                intent.putExtra(SearchFragment.EXTRA_START_PLACE_Z, String.valueOf(startPoint.getPointZ()));
                startActivityForResult(intent, SEARCH_CODE);
                firstStepDrawLines = 1; //每次搜索的时候把firstStepDrawLines参数设置为1，就可以保证画一次路径
                getActivity().finish();
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

    private class NetTask extends AsyncTask<Void, Void, ArrayList<Point>> {
        private Handler handler;

        private NetTask(Handler handler) {
            this.handler = handler;
        }

        @Override
        protected ArrayList<Point> doInBackground(Void... params) {
            if (notHaveEndPoint) {
                return new NetConnection().getPoint();
            } else {
                initMap();
                ArrayList<Point> points = new ArrayList<Point>();
                if (pointArrayListLine.size() == 0) { //如果没有路径 即第一次取得数据 那么开始规划数据
                    if (startPointAndEndPoint == null) {  //处理有没有中间点 这里面是有中间点
                        ArrayList<Point> pointsStartAndMid = new NetConnection().getPathPoint(startPointAndMidPoint, false);
                        ArrayList<Point> pointsMidAndEnd = new NetConnection().getPathPoint(midPointAndEndPoint, true);

                        points.addAll(pointsStartAndMid);
                        points.addAll(pointsMidAndEnd);

                    } else { //没有中间点
                        points = new NetConnection().getPathPoint(startPointAndEndPoint, false);
                    }
                    Message msg = new Message();
                    msg.what = 100;
                    msg.obj = points;
                    handler.sendMessage(msg);  //把值传出去
                }
                if (pointArrayListLine.size() != 0) {
                    ArrayList<Point> startPointsList = new ArrayList<Point>();
                    startPointsList = new NetConnection().getPoint();
                    pointArrayListLine.set(0, startPointsList.get(0));  //把开头的节点改变 中间的点不变 形成新路线
                    points = pointArrayListLine;
                }
                return points;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Point> points) {
            mPoints = points;
            if (notHaveEndPoint) {
                if (!whetherYouMove(startPoint, points.get(0))) {
                    startPoint = points.get(0);
                    MapInSize.getMapActivity().cleanPoint();
                    MapInSize.getMapActivity().checkFloorZ(mPoints.get(0).getPointZ());
                    MapInSize.getMapActivity().redrawPoint(mPoints.get(0).getPointX(), mPoints.get(0).getPointY(), mPoints.get(0).getPointZ());
                }
            } else {
                if (firstStepDrawLines >= 1) {
                    startPoint = points.get(0);
                    mThePlaceYouWantGo.setText("您要去：" + endPoint.getmTittle()); //就是从搜索界面返回的情形
                    MapInSize.getMapActivity().cleanPoint();
                    MapInSize.getMapActivity().checkFloorZ(divideLayePoint(points).get(0).getPointZ());
                    MapInSize.getMapActivity().redrawLine(divideLayePoint(points));
                    firstStepDrawLines--;
                }
                if (!whetherYouMove(startPoint, points.get(0))) { //如果移动
                    if (hadStepIntoMidPoint) {
                        new AlertDialog.Builder(getActivity()).setTitle("你已经走到了中间点").setPositiveButton("懂得", null).show();
                        hadStepIntoMidPoint = false;
                    }
                    startPoint = points.get(0);
                    MapInSize.getMapActivity().cleanPoint();
                    MapInSize.getMapActivity().checkFloorZ(divideLayePoint(points).get(0).getPointZ());
                    MapInSize.getMapActivity().redrawLine(divideLayePoint(points));
                }
            }
        }

    }

    private Runnable runnable = new Runnable() {
        public void run() {
            new NetTask(refurbishHandler).execute();
            refurbishHandler.postDelayed(this, 50);
        }
    };

    private void initMap() {
        if (midPoint.getPointX() == 0.0 && midPoint.getPointY() == 0.0
                && midPoint.getPointZ() == 0.0 || judgeMidPoint(startPoint, midPoint)) {
            if (startPoint != null && endPoint != null) {
                midPoint.setPointX(0.0);
                midPoint.setPointY(0.0);
                midPoint.setPointZ(0.0);  //这样写是因为走过中间点后，就把中间点变成0
                String startPointString = startPoint.getPointString(startPoint);
                String endPointString = endPoint.getPointString(endPoint);
                startPointAndEndPoint = startPointString + "/" + endPointString;
            }
        } else {
            String startPointString = startPoint.getPointString(startPoint);
            String midPointString = midPoint.getPointString(midPoint);
            String endPointString = endPoint.getPointString(endPoint);
            startPointAndEndPoint = null;
            startPointAndMidPoint = startPointString + "/" + midPointString;
            midPointAndEndPoint = midPointString + "/" + endPointString;
        }
    }

    private boolean judgeMidPoint(Point startPoint, Point midPoint) { //判断是否到中点
        double startPointX = startPoint.getPointX();
        double startPointY = startPoint.getPointY();
        double midPointX = midPoint.getPointX();
        double midPointY = midPoint.getPointY();
        hadStepIntoMidPoint = Math.abs(startPointX - midPointX) < 0.05 && Math.abs(startPointY - midPointY) < 0.05;
        return hadStepIntoMidPoint;

    }


    private ArrayList<Point> divideLayePoint(ArrayList<Point> points) { //得到第一层的点 // 每次都获得第一层的点
        ArrayList<Point> firstListPoints = new ArrayList<Point>();
        firstListPoints.add(points.get(0));
        for (int i = 1; i < points.size(); i++) {
            if (points.get(i).getPointZ() == points.get(i - 1).getPointZ()) {
                firstListPoints.add(points.get(i));
            } else {
                return firstListPoints;
            }
        }
        return firstListPoints;
    }


    private boolean whetherYouMove(Point startPoint, Point endPoint) {
        youDonnotMove = ((startPoint.getPointX() == endPoint.getPointX() &&  //如果相等 则没有移动 youDonnotMove为真
                startPoint.getPointY() == endPoint.getPointY() &&
                startPoint.getPointZ() == endPoint.getPointZ()));
        return youDonnotMove;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        refurbishHandler.removeCallbacks(runnable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        refurbishHandler.removeCallbacks(runnable);
    }

    /**
     * 暂时没用到这个方法，因为用到这个方法后定位还需要再点击一次 待解决
     *
     * @param requestCode requestCode
     * @param resultCode  resultCode
     * @param data        传递的数据
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SEARCH_CODE) {
            String name = data.getStringExtra(SearchFragment.EXTRA_END_PLACE_NAME);
            double endPointX = data.getDoubleExtra(SearchFragment.EXTRA_END_PLACE_X, 0);
            double endPointY = data.getDoubleExtra(SearchFragment.EXTRA_END_PLACE_Y, 0);
            double endPointZ = data.getDoubleExtra(SearchFragment.EXTRA_END_PLACE_Z, 0);
            double startPointX = data.getDoubleExtra(SearchFragment.EXTRA_START_PLACE_X, 0);
            double startPointY = data.getDoubleExtra(SearchFragment.EXTRA_START_PLACE_Y, 0);
            double startPointZ = data.getDoubleExtra(SearchFragment.EXTRA_START_PLACE_Z, 0);
            notHaveEndPoint = data.getBooleanExtra(SearchFragment.EXTRA_END_Point_BOOL, true);
            startPoint = new Point("startPoint", startPointX, startPointY, startPointZ);
            endPoint = new Point(name, endPointX, endPointY, endPointZ);
        }
    }
}

package cn.Nino.crim.airportmap.app.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import cn.Nino.crim.airportmap.app.Activity.SearchActivity;
import cn.Nino.crim.airportmap.app.Map.MapInSize;
import cn.Nino.crim.airportmap.app.Point.Point;
import cn.Nino.crim.airportmap.app.Point.PointEstimate;
import cn.Nino.crim.airportmap.app.R;
import cn.Nino.crim.airportmap.app.ResideMenu.ResideMenu;
import cn.Nino.crim.airportmap.app.ResideMenu.ResideMenuItem;
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
    private ImageButton mImageButtonLocation, mLeftMenuButton;
    private ImageView mImageButtonSearch;
    private TextView mThePlaceYouWantGo;
    private Handler refurbishHandler = new Handler();
    private Point endPoint = null;
    private Point startPoint = null;
    private Point midPoint = null;
    private String startPointAndEndPoint;
    private String startPointAndMidPoint;
    private String midPointAndEndPoint;
    private ResideMenu resideMenu;
    Boolean notHaveEndPoint = true;
    Boolean hadStepIntoMidPoint = false;
    Boolean hadStepIntoEndPoint = false;
    Boolean youDonnotMove = false;
    ArrayList<Point> pointArrayListLine = new ArrayList<Point>(); //存储得到的路径 保证不每次都重绘
    ArrayList<Point> pathFromOld = new ArrayList<Point>(); //存储原始的路径

    private ResideMenuItem resideMenuItem1, resideMenuItem2, resideMenuItem3, resideMenuItem4, resideMenuItem5,
            resideMenuItem6, resideMenuItem7, resideMenuItem8, resideMenuItem9, resideMenuItem10, resideMenuItem11; //左侧的图片


    @Override
    public void onCreate(Bundle savedInstanceState) {
        setUpMenu();

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
        mThePlaceYouWantGo = (TextView) view.findViewById(R.id.search_place);
        mThePlaceYouWantGo.setTextColor(0xffffffff);
        mLeftMenuButton = (ImageButton) view.findViewById(R.id.left_menu);
        mImageButtonLocation = (ImageButton) view.findViewById(R.id.location_button);
        mImageButtonSearch = (ImageView) view.findViewById(R.id.search_place_button);

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                getPathFromHandle(msg);
            }
        };

        //定位是每秒都需要
        //但是规划路径只需要特定的时候才需要
        new NetTask(handler).execute();
        refurbishHandler.removeCallbacks(runnable);
        refurbishHandler.postDelayed(runnable, 20);  // 定时刷新任务  //看服务器的情况很可能设定为20刷新不出来

        mImageButtonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new NetTask(handler).execute();
                refurbishHandler.removeCallbacks(runnable);
                refurbishHandler.postDelayed(runnable, 20);  // 定时刷新任务
            }
        });

        mLeftMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        mImageButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refurbishHandler.removeCallbacks(runnable);
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra(SearchFragment.EXTRA_START_PLACE_X, String.valueOf(startPoint.getPointX()));
                intent.putExtra(SearchFragment.EXTRA_START_PLACE_Y, String.valueOf(startPoint.getPointY()));
                intent.putExtra(SearchFragment.EXTRA_START_PLACE_Z, String.valueOf(startPoint.getPointZ()));
                startActivityForResult(intent, SEARCH_CODE);
                firstStepDrawLines = 1; //每次搜索的时候把firstStepDrawLines参数设置为1，就可以保证画一次路径
                getActivity().finish();
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
        return view;
    }


    private ArrayList<Point> getPathFromHandle(Message msg) {
        if (msg.what == 100) {
            pointArrayListLine = (ArrayList<Point>) msg.obj;
        }
        return pointArrayListLine;
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
                    points = getPath();
                    Message msg = new Message();
                    msg.what = 100;
                    msg.obj = points;
                    handler.obtainMessage();
                    handler.sendMessage(msg);  //把值传出去
                }
                if (pointArrayListLine.size() != 0) {
                    ArrayList<Point> startPointsList;
                    startPointsList = new NetConnection().getPoint();

                    boolean isInLine = PointEstimate.isOnAllPath(startPointsList.get(0), pointArrayListLine, 0.1);
                    boolean isInSameLayer = startPointsList.get(0).getPointZ() == pointArrayListLine.get(1).getPointZ(); // 是否在同一层
                    if (isInLine && isInSameLayer) {
                        pointArrayListLine.set(0, startPointsList.get(0));  //把开头的节点改变 中间的点不变 形成新路线
                        points = pointArrayListLine;
                        pathFromOld = (ArrayList<Point>) pointArrayListLine.clone();
                    } else {
                        // Log.e("不在这个位置上面，目前的点是:", startPointsList.get(0).toString());
                        points.clear(); //删除points
                        pointArrayListLine.clear();
                        startPoint = startPointsList.get(0);
                        initMap();  //重新获得起点和终点
                        points = getPath();
                        pointArrayListLine = points; // 这时候赋值新的pointArrayListLine
                        Message msg = new Message();
                        msg.what = 100;
                        msg.obj = points;
                        handler.sendMessage(msg);  //把值传出去
                    }
                }
                return points;
            }
        }

        @Override
        protected void onPostExecute(final ArrayList<Point> points) {
            if (points.size() > 0) {
                if (notHaveEndPoint) {
                    if (!whetherYouMove(startPoint, points.get(0))) {
                        startPoint = points.get(0);
                        if (points.size() != 0)
                            MapInSize.getMapActivity().cleanPoint();
                        MapInSize.getMapActivity().checkFloorZ(points.get(0).getPointZ());
                        MapInSize.getMapActivity().redrawPoint(points.get(0).getPointX(), points.get(0).getPointY(), points.get(0).getPointZ());
                    }
                } else {
                    if (firstStepDrawLines >= 1) {
                        startPoint = points.get(0);
                        mThePlaceYouWantGo.setText("您要去：" + endPoint.getmTittle()); //就是从搜索界面返回的情形
                        if (points.size() != 0)
                            MapInSize.getMapActivity().cleanPoint();
                        MapInSize.getMapActivity().checkFloorZ(divideLayePoint(points).get(0).getPointZ());
                        MapInSize.getMapActivity().redrawLine(divideLayePoint(points));
                        firstStepDrawLines--;
                    }
                    if (points.size() > 0) {
                        if (!whetherYouMove(startPoint, points.get(0))) { //如果移动
                            if (hadStepIntoMidPoint) {  //如果到达中间点
                                new AlertDialog.Builder(getActivity()).setTitle("你已经走到了中间点").setPositiveButton("确定", null).show();
                                hadStepIntoMidPoint = false;
                            }
                            if (hadStepIntoEndPoint) {  //如果到达终点
                                new AlertDialog.Builder(getActivity()).setTitle("你已经走到了终点").setPositiveButton("结束导航", null).show();
                                hadStepIntoMidPoint = false;
                                if (points.size() != 0)
                                    MapInSize.getMapActivity().cleanPoint();  // 这里面结束导航
                                notHaveEndPoint = true;
                                mThePlaceYouWantGo.setText(R.string.search_place);
                                MapInSize.getMapActivity().checkFloorZ(divideLayePoint(points).get(0).getPointZ());
                                MapInSize.getMapActivity().redrawLine(divideLayePoint(points));
                            } else {   //正常范围内移动
                                if (!points.equals(pathFromOld)) {  //就是如果偏离了路径
                                    notHaveEndPoint = true;
                                    if (points.size() != 0)
                                        MapInSize.getMapActivity().cleanPoint();  // 这里面结束导航
                                    new AlertDialog.Builder(getActivity()).setTitle("您已经偏离路径,是否重新规划").setPositiveButton("好的", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            notHaveEndPoint = false;
                                            MapInSize.getMapActivity().cleanPoint();
                                            // refurbishHandler.removeCallbacks(runnable);
                                            // refurbishHandler.postDelayed(runnable, 20);
                                            MapInSize.getMapActivity().redrawLine(divideLayePoint(points));

                                        }
                                    }).setNegativeButton("不用了", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (points.size() != 0)
                                                MapInSize.getMapActivity().cleanPoint();
                                            notHaveEndPoint = true;
                                            mThePlaceYouWantGo.setText(R.string.search_place);
                                        }
                                    }).show();
                                } else { //还在路径上
                                    startPoint = points.get(0);
                                    MapInSize.getMapActivity().cleanPoint();
                                    MapInSize.getMapActivity().checkFloorZ(divideLayePoint(points).get(0).getPointZ());
                                    MapInSize.getMapActivity().redrawLine(divideLayePoint(points));
                                }
                            }
                        }
                    }

                }
            } else {
                Toast.makeText(getActivity(), "服务器没有打开", Toast.LENGTH_SHORT).show();
                refurbishHandler.removeCallbacks(runnable);
            }
        }


    }

    private Runnable runnable = new Runnable() {
        public void run() {
            new NetTask(refurbishHandler).execute();
            refurbishHandler.postDelayed(this, 20);
        }
    };

    private ArrayList<Point> getPath() {
        ArrayList<Point> points = new ArrayList<Point>();
        if (startPointAndEndPoint == null) {  //如果有中间点
            ArrayList<Point> pointsStartAndMid = new NetConnection().getPathPoint(startPointAndMidPoint, false);
            ArrayList<Point> pointsMidAndEnd = new NetConnection().getPathPoint(midPointAndEndPoint, true);//后面参数为true 就是不加开始节点

            points.addAll(pointsStartAndMid);
            points.addAll(pointsMidAndEnd);

        } else { //没有中间点
            points = new NetConnection().getPathPoint(startPointAndEndPoint, false);
        }
        return points;

    }

    private void initMap() {
        judgeEndPoint(startPoint, endPoint);
        if (midPoint.getPointX() == 0.0 && midPoint.getPointY() == 0.0
                && midPoint.getPointZ() == 0.0 || judgeMidPoint(startPoint, midPoint)) {
            if (startPoint != null && endPoint != null) {   //这里面是没有中间点
                midPoint.setPointX(0.0);
                midPoint.setPointY(0.0);
                midPoint.setPointZ(0.0);  //这样写是因为走过中间点后，就把中间点变成0
                String startPointString = startPoint.getPointString(startPoint);
                String endPointString = endPoint.getPointString(endPoint);
                startPointAndEndPoint = startPointString + "/" + endPointString;
            }
        } else { //如果有中间点
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

    private boolean judgeEndPoint(Point startPoint, Point endPoint) { //判断是否到终点
        double startPointX = startPoint.getPointX();
        double startPointY = startPoint.getPointY();
        double midPointX = endPoint.getPointX();
        double midPointY = endPoint.getPointY();
        hadStepIntoEndPoint = Math.abs(startPointX - midPointX) < 0.03 && Math.abs(startPointY - midPointY) < 0.05;
        return hadStepIntoEndPoint;
    }

    private ArrayList<Point> divideLayePoint(ArrayList<Point> points) { //得到第一层的点 // 每次都获得第一层的点
        ArrayList<Point> firstListPoints = new ArrayList<Point>();
        if (points.size() > 0) {
            firstListPoints.add(points.get(0));
            for (int i = 1; i < points.size(); i++) {
                if (points.get(i).getPointZ() == points.get(i - 1).getPointZ()) {
                    firstListPoints.add(points.get(i));
                } else {
                    return firstListPoints;
                }
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
        refurbishHandler.removeCallbacks(runnable);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        refurbishHandler.removeCallbacks(runnable);
        super.onDestroy();
    }

    private void setUpMenu() {
        resideMenu = new ResideMenu(getActivity());
        resideMenu.setBackground(R.drawable.bgmap);
        resideMenu.attachToActivity(getActivity());
        resideMenu.setMenuListener(new ResideMenu.OnMenuListener() {
            @Override
            public void openMenu() {

            }

            @Override
            public void closeMenu() {

            }
        });
        resideMenu.setScaleValue(0.6f);

        // create left menu items;
        resideMenuItem1 = new ResideMenuItem(getActivity(), R.drawable.leftmap1, "出入口(D)");
        resideMenuItem2 = new ResideMenuItem(getActivity(), R.drawable.leftmap2, "登机口(E)");
        resideMenuItem3 = new ResideMenuItem(getActivity(), R.drawable.leftmap3, "电梯/扶梯(EL/ES)");
        resideMenuItem4 = new ResideMenuItem(getActivity(), R.drawable.leftmap4, "直机柜台(C)");
        resideMenuItem5 = new ResideMenuItem(getActivity(), R.drawable.leftmap5, "安检口(SC)");
        resideMenuItem6 = new ResideMenuItem(getActivity(), R.drawable.leftmap6, "饮水处/卫生间(WT/W)");
        resideMenuItem7 = new ResideMenuItem(getActivity(), R.drawable.leftmap7, "问讯处/票务(Q)");
        resideMenuItem8 = new ResideMenuItem(getActivity(), R.drawable.leftmap8, "商店(S)");
        resideMenuItem9 = new ResideMenuItem(getActivity(), R.drawable.leftmap9, "行李盘(B)");
        resideMenuItem10 = new ResideMenuItem(getActivity(), R.drawable.leftmap10, "立柱");
        resideMenuItem11 = new ResideMenuItem(getActivity(), R.drawable.leftmap11, "墙体");
        resideMenu.addMenuItem(resideMenuItem1, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(resideMenuItem2, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(resideMenuItem3, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(resideMenuItem4, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(resideMenuItem5, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(resideMenuItem6, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(resideMenuItem7, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(resideMenuItem8, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(resideMenuItem9, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(resideMenuItem10, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(resideMenuItem11, ResideMenu.DIRECTION_LEFT);
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT); //设置向右滑动
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_LEFT);  //设置向左滑动
    }

    private void changeActivity(Activity targetActivity) {  //点击转向不同的activity
        resideMenu.clearIgnoredViewList();
        Intent intent = new Intent(getActivity(), targetActivity.getClass());
        startActivity(intent);
    }

}

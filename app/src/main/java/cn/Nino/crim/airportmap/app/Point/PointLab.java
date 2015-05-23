package cn.Nino.crim.airportmap.app.Point;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/3/31 0031.
 * 单例，point数组存在单例里面
 */
public class PointLab {
    private ArrayList<Point> mPoints;
    private static PointLab sPointLab;
    private Context mContext;

    DefaultPoint defaultPoint = new DefaultPoint();
    ArrayList<Point> defaultB1Points = defaultPoint.initialPoint();


    public PointLab(Context mContext, String mThePlaceYouWantGo) {
        this.mContext = mContext;
        mPoints = new ArrayList<Point>();

        for (int i = 0; i < defaultB1Points.size(); i++) {
            if (defaultB1Points.get(i).getmTittle().contains(mThePlaceYouWantGo)) {
                mPoints.add(defaultB1Points.get(i));
            }
        }

    }

    public static PointLab getmPointLab(Context context, String mThePlaceYouWantGo) {
        if (sPointLab == null) {

            sPointLab = new PointLab(context.getApplicationContext(), mThePlaceYouWantGo);
        }
        return sPointLab;
    }

    public ArrayList<Point> getmPointList() {
        sPointLab = null;
        return mPoints;
    }

}

package cn.Nino.crim.airportmap.app.Map;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;
import cn.Nino.crim.airportmap.app.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/3/30 0030.
 */
public class MapInSize extends SurfaceView {
    private SurfaceHolder mHolder;
    private static MapInSize mapActivity = null;
    int map_x, map_y;

    public int getMap_x() {
        map_x = mHolder.getSurfaceFrame().width();
        return map_x;
    }

    public int getMap_y() {
        map_y = mHolder.getSurfaceFrame().height();
        return map_y;
    }

    public MapInSize(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHolder = getHolder();
        setZOrderOnTop(true);
        mHolder.setFormat(PixelFormat.TRANSPARENT);//设置背景透明
        setBackground(1);
        mapActivity = this;
    }


    public void setBackground(double layer) {
        if (layer == 0.0) {
            setBackgroundResource(R.drawable.b1_map);
        }
        if (layer == 1.0) {
            setBackgroundResource(R.drawable.f1_map);
        }
        if (layer == 2.0) {
            setBackgroundResource(R.drawable.f2_map);
        }

    }

    public static MapInSize getMapActivity() {
        return mapActivity;
    }


    public void redrawPoint(double pointX, double pointY, double pointZ) {
        Canvas mCanvas = mHolder.lockCanvas();
        Paint mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);//抗拒次
        mPaint.setColor(Color.RED);
        if (pointZ != 0.0) {
            mCanvas.drawCircle((float) (getMap_x() * pointX), (float) (getMap_y() * (1 - pointY)), 10, mPaint);
        } else if (pointY > 0.38) {
            //Toast.makeText(getContext().getApplicationContext(), "对不起已经超出范围", Toast.LENGTH_SHORT).show();
        } else {
            mCanvas.drawCircle((float) (getMap_x() * pointX), (float) ((getMap_y() * (1 - pointY / 0.38))), 10, mPaint);
        }
        try {
            if (mHolder != null) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void redrawLine(ArrayList<cn.Nino.crim.airportmap.app.Point.Point> points) {
        double pointX = points.get(0).getPointX();
        double pointY = points.get(0).getPointY();
        double pointEndX = points.get(points.size() - 1).getPointX();
        double pointEndY = points.get(points.size() - 1).getPointY();
        Canvas mCanvas = mHolder.lockCanvas();
        Paint mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);//抗拒次
        mPaint.setColor(Color.GREEN);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(7);


        Paint mPaintCircleStart = new Paint();
        mPaintCircleStart.setFlags(Paint.ANTI_ALIAS_FLAG);//抗拒次
        mPaintCircleStart.setColor(Color.RED);
        Paint mPaintCircleEnd = new Paint();
        mPaintCircleEnd.setFlags(Paint.ANTI_ALIAS_FLAG);//抗拒次
        mPaintCircleEnd.setColor(Color.BLUE);

        for (int i = 1; i < points.size() - 1; i++) {
            checkFloorZ(points.get(i).getPointZ());
            if (points.get(i).getPointZ() != 0.0 && points.get(i + 1).getPointZ() != 0.0) {
                mCanvas.drawCircle((float) (getMap_x() * pointX), (float) (getMap_y() * (1 - pointY)), 10, mPaintCircleStart);
                mCanvas.drawLine((float) (points.get(i).getPointX() * getMap_x()), (float) (getMap_y() * (1 - points.get(i).getPointY())),
                        (float) (points.get(i + 1).getPointX() * getMap_x()), (float) (getMap_y() * (1 - points.get(i + 1).getPointY())), mPaint);
                mCanvas.drawCircle((float) (getMap_x() * pointEndX), (float) (getMap_y() * (1 - pointEndY)), 10, mPaintCircleEnd);


            } else if (points.get(i).getPointY() > 0.38 || points.get(i + 1).getPointY() > 0.38) {
                Toast.makeText(getContext().getApplicationContext(), "对不起已经超出范围", Toast.LENGTH_SHORT).show();
            } else {
                mCanvas.drawCircle((float) (getMap_x() * pointX), (float) ((getMap_y() * (1 - pointY / 0.38))), 10, mPaintCircleStart);
                mCanvas.drawLine((float) (points.get(i).getPointX() * getMap_x()), (float) (getMap_y() * (1 - points.get(i).getPointY() / 0.38)),
                        (float) (points.get(i + 1).getPointX() * getMap_x()), (float) (getMap_y() * (1 - points.get(i + 1).getPointY() / 0.38)), mPaint);
                mCanvas.drawCircle((float) (getMap_x() * pointEndX), (float) ((getMap_y() * (1 - pointEndY / 0.38))), 10, mPaintCircleEnd);
            }
        }
        try {
            if (mHolder != null) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void cleanPoint() {
        Canvas canvas = mHolder.lockCanvas();
        if (canvas != null) {
            Paint paint = new Paint();
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            canvas.drawPaint(paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        }
        //主要原因：当点击back按钮时Activity退出视野。此时并不会出现画面更新情况，但是用于更新动画的线程仍然在工作，即flag = true。由于Activity已退出解锁操作holder.unlockCanvasAndPost(canvas);不能完成因此就会引发上述异常。
        try {
            if (mHolder != null) {
                mHolder.unlockCanvasAndPost(canvas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkFloorZ(double pointZ) {
        if (pointZ == 0.0) {
            setBackground(0);
        }
        if (pointZ == 1.0) {
            setBackground(1);
        }
        if (pointZ == 2.0) {
            setBackground(2);
        }
    }
}

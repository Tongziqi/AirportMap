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
            setBackgroundResource(R.drawable.b1);
        }
        if (layer == 1.0) {
            setBackgroundResource(R.drawable.f1);
        }
        if (layer == 2.0) {
            setBackgroundResource(R.drawable.f2);
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
            Toast.makeText(getContext().getApplicationContext(), "对不起已经超出范围", Toast.LENGTH_SHORT).show();
        } else {
            mCanvas.drawCircle((float) (getMap_x() * pointX), (float) ((getMap_y() * (1 - pointY / 0.38))), 10, mPaint);
        }
        mHolder.unlockCanvasAndPost(mCanvas);
    }


    public void redrawLine(ArrayList<cn.Nino.crim.airportmap.app.Point.Point> points) {
        double pointX = points.get(0).getPointX();
        double pointY = points.get(0).getPointY();
        Canvas mCanvas = mHolder.lockCanvas();
        Paint mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);//抗拒次
        mPaint.setColor(Color.RED);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(7);
        Shader mShader = new LinearGradient(0, 0, 20, 20,
                new int[]{
                        Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW},
                null, Shader.TileMode.REPEAT);
        mPaint.setShader(mShader);

        Paint mPaintCircle = new Paint();
        mPaintCircle.setFlags(Paint.ANTI_ALIAS_FLAG);//抗拒次
        mPaintCircle.setColor(Color.RED);

        for (int i = 0; i < points.size() - 1; i++) {
            checkFloorZ(points.get(i).getPointZ());
            if (points.get(i).getPointZ() != 0.0 && points.get(i + 1).getPointZ() != 0.0) {
                mCanvas.drawCircle((float) (getMap_x() * pointX), (float) (getMap_y() * (1 - pointY)), 10, mPaintCircle);
                mCanvas.drawLine((float) (points.get(i).getPointX() * getMap_x()), (float) (getMap_y() * (1 - points.get(i).getPointY())),
                        (float) (points.get(i + 1).getPointX() * getMap_x()), (float) (getMap_y() * (1 - points.get(i + 1).getPointY())), mPaint);
            } else if (points.get(i).getPointY() > 0.38 || points.get(i + 1).getPointY() > 0.38) {
                Toast.makeText(getContext().getApplicationContext(), "对不起已经超出范围", Toast.LENGTH_SHORT).show();
            } else {
                mCanvas.drawCircle((float) (getMap_x() * pointX), (float) ((getMap_y() * (1 - pointY / 0.38))), 10, mPaintCircle);
                mCanvas.drawLine((float) (points.get(i).getPointX() * getMap_x()), (float) (getMap_y() * (1 - points.get(i).getPointY() / 0.38)),
                        (float) (points.get(i + 1).getPointX() * getMap_x()), (float) (getMap_y() * (1 - points.get(i + 1).getPointY() / 0.38)), mPaint);
            }
        }
        mHolder.unlockCanvasAndPost(mCanvas);
    }


    public void cleanPoint() {

        Canvas mCanvas = mHolder.lockCanvas();
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        mCanvas.drawPaint(paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        mHolder.unlockCanvasAndPost(mCanvas);
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
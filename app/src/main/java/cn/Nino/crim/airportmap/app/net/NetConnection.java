package cn.Nino.crim.airportmap.app.net;

import android.net.Uri;
import android.util.Log;
import cn.Nino.crim.airportmap.app.Point.Point;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Administrator on 2015/4/3 0003.
 */
public class NetConnection {
    public static final String TAG = "AirporMap";
    public static final String SEVER_URL_TEST = "http://172.18.4.184:8080/Access";
    public static final String SEVER_URL_FORMAL = "http://172.18.4.166:8080/Access";
    public static final String SEVER_URL_OPEN = "http://172.21.14.44:8080/user/Access";
    public static final String SEVER_URL_MOHANWEN = "http://172.18.4.46:8080/Access";
    public static final String ACTION_LOCATE = "locate";
    public static final String Test = "test";
    public static final String Path = "getPath";
    public static final String PartPath = "getPartPath";


    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            InputStream inputStream = connection.getInputStream();  //获得inputStream
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, bytesRead);     //获取读取的全部数据
            }
            outputStream.close();
            return outputStream.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getUrl(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public ArrayList<Point> downloadPoint(String url) {
        ArrayList<Point> points = new ArrayList<Point>();
        try {
            String pointString = getUrl(url);
            String array[] = pointString.split(",");

            for (int i = 0; i < array.length; ) {
                Point item = new Point();
                item.setPointX(Double.valueOf(array[i]));
                item.setPointY(Double.valueOf(array[i + 1]));
                item.setPointZ(Double.valueOf(array[i + 2]));
                i = i + 3;
                points.add(item);
            }

        } catch (IOException i) {
            Log.e(TAG, "Failed to  connection", i);
        }
        return points;
    }

    public ArrayList<Point> getPoint() {
        String url = Uri.parse(SEVER_URL_OPEN).buildUpon()
                .appendQueryParameter("action", ACTION_LOCATE)
                .build().toString();
        return downloadPoint(url);
    }

    public ArrayList<Point> getPathPoint(String nativePoints, boolean hasMidPoint) {
        String allPathString; //产生的路径
        String urlPoint = Uri.parse(SEVER_URL_OPEN).buildUpon()
                .appendQueryParameter("action", ACTION_LOCATE)
                .build().toString();
        String url = Uri.parse(SEVER_URL_OPEN).buildUpon()
                .appendQueryParameter("action", Path)
                .appendQueryParameter("Points", nativePoints)
                .build().toString();
        ArrayList<Point> points = new ArrayList<Point>();
        try {
            String pointString = getUrl(url);
            String pointStringUrlPoint = getUrl(urlPoint);

            if (hasMidPoint) {
                allPathString = pointString;    //不加开始节点
            } else {
                allPathString = pointStringUrlPoint + "," + pointString; //这里面把开始节点加进去
            }
            String array[] = allPathString.split(",");
            // Log.e("MapActivity", Arrays.toString(array));

            for (int i = 0; i < array.length; ) {
                Point item = new Point();
                item.setPointX(Double.valueOf(array[i]));
                item.setPointY(Double.valueOf(array[i + 1]));
                item.setPointZ(Double.valueOf(array[i + 2]));
                i = i + 3;
                points.add(item);
            }

        } catch (IOException i) {
            Log.e(TAG, "Failed to  connection", i);
        }
        return points;
    }

}



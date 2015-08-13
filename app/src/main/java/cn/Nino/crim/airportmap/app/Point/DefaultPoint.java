package cn.Nino.crim.airportmap.app.Point;

import java.util.ArrayList;

/**
 * 默认的类
 * Created by Administrator on 2015/4/13 0013.
 */
public class DefaultPoint {
    public DefaultPoint defaultPoint = null;
    public ArrayList<Point> allPoints = new ArrayList<Point>();

    public DefaultPoint() {
        defaultPoint = this;
    }


    public ArrayList<Point> initialPoint() {

        //floor_B1 Points

        allPoints.add(new Point("电梯(ES1)							地下1层", 0.11, 0.1, 0.0));
        allPoints.add(new Point("扶梯(EL1)							地下1层", 0.17, 0.05, 0.0));
        allPoints.add(new Point("出入口(D1)							地下1层", 0.27, 0.05, 0.0));
        allPoints.add(new Point("出入口(D2)							地下1层", 0.5, 0.05, 0.0));
        allPoints.add(new Point("出入口(D3)							地下1层", 0.73, 0.05, 0.0));
        allPoints.add(new Point("扶梯(EL2)							地下1层", 0.83, 0.05, 0.0));
        allPoints.add(new Point("电梯(ES2)							地下1层", 0.87, 0.1, 0.0));
        allPoints.add(new Point("扶梯(EL3)							地下1层", 0.26, 0.37, 0.0));
        allPoints.add(new Point("电梯(ES3)							地下1层", 0.31, 0.3, 0.0));
        allPoints.add(new Point("电梯(ES4)							地下1层", 0.61, 0.3, 0.0));
        allPoints.add(new Point("扶梯(EL4)  						    地下1层", 0.72, 0.37, 0.0));


        //F1SubBottom Points

        allPoints.add(new Point("饮水处(WT5)					    	1层", 0.2702, 0.3498, 1.0));
        allPoints.add(new Point("卫生间(W5)							1层", 0.2490, 0.3344, 1.0));
        allPoints.add(new Point("扶梯(EL3)							1层", 0.3087, 0.3344, 1.0));
        allPoints.add(new Point("电梯(ES3)							1层", 0.3298, 0.3151, 1.0));
        allPoints.add(new Point("商店(S9)							1层", 0.2490, 0.2593, 1.0));
        allPoints.add(new Point("商店(S10)							1层", 0.2490, 0.2247, 1.0));
        allPoints.add(new Point("商店(S11)							1层", 0.2490, 0.1843, 1.0));
        allPoints.add(new Point("商店(S12)							1层", 0.1836, 0.1535, 1.0));
        allPoints.add(new Point("商店(S5)							1层", 0.3587, 0.2497, 1.0));
        allPoints.add(new Point("商店(S6)							1层", 0.3953, 0.2497, 1.0));
        allPoints.add(new Point("询问处/票务(Q1)					    1层", 0.3722, 0.1746, 1.0));
        allPoints.add(new Point("商店(S7)							1层", 0.5973, 0.2497, 1.0));
        allPoints.add(new Point("商店(S8)							1层", 0.6358, 0.2497, 1.0));
        allPoints.add(new Point("询问处/票务(Q2)				    	1层", 0.6223, 0.1746, 1.0));
        allPoints.add(new Point("卫生间(W6)							1层", 0.7493, 0.3324, 1.0));
        allPoints.add(new Point("饮水处(WT6)						    1层", 0.7263, 0.3498, 1.0));
        allPoints.add(new Point("扶梯(EL4)							1层", 0.6916, 0.3344, 1.0));
        allPoints.add(new Point("电梯(ES4)							1层", 0.6666, 0.3151, 1.0));
        allPoints.add(new Point("商店(S13)							1层", 0.7493, 0.2593, 1.0));
        allPoints.add(new Point("商店(S14)							1层", 0.7493, 0.2247, 1.0));
        allPoints.add(new Point("商店(S15)							1层", 0.7493, 0.1843, 1.0));
        allPoints.add(new Point("商店(S16)							1层", 0.8167, 0.1535, 1.0));
        allPoints.add(new Point("饮水处(WT1)						    1层", 0.0489, 0.0900, 1.0));
        allPoints.add(new Point("卫生间(W1)							1层", 0.0681, 0.0707, 1.0));
        allPoints.add(new Point("电梯(ES1)							1层", 0.1220, 0.0900, 1.0));
        allPoints.add(new Point("扶梯(EL1)							1层", 0.1393, 0.0553, 1.0));
        allPoints.add(new Point("出入口(D1)							1层", 0.2606, 0.0419, 1.0));
        allPoints.add(new Point("商店(S1)							1层", 0.3606, 0.0746, 1.0));
        allPoints.add(new Point("商店(S2)							1层", 0.3991, 0.0746, 1.0));
        allPoints.add(new Point("出入口(D2)							1层", 0.4992, 0.0419, 1.0));
        allPoints.add(new Point("商店(S3)							1层", 0.6012, 0.0746, 1.0));
        allPoints.add(new Point("商店(S4)							1层", 0.6358, 0.0746, 1.0));
        allPoints.add(new Point("出入口(D3)							1层", 0.7378, 0.0419, 1.0));
        allPoints.add(new Point("扶梯(EL2)							1层", 0.8552, 0.0553, 1.0));
        allPoints.add(new Point("电梯(ES2)							1层", 0.8764, 0.0900, 1.0));
        allPoints.add(new Point("卫生间(W2)							1层", 0.9302, 0.0707, 1.0));
        allPoints.add(new Point("饮水处(WT2)						    1层", 0.9456, 0.0900, 1.0));


        //F1SubLeft Points

        allPoints.add(new Point("登机口(E9)							1层", 0.4395, 0.9713, 1.0));
        allPoints.add(new Point("饮水处(WT611)						1层", 0.3799, 0.9502, 1.0));
        allPoints.add(new Point("登机口(E8)							1层", 0.3183, 0.9713, 1.0));
        allPoints.add(new Point("登机口(E7)							1层", 0.1990, 0.9713, 1.0));
        allPoints.add(new Point("扶梯(EL7)							1层", 0.1432, 0.9502, 1.0));
        allPoints.add(new Point("电梯(ES7)							1层", 0.1182, 0.9155, 1.0));
        allPoints.add(new Point("卫生间(W7)							1层", 0.0604, 0.9329, 1.0));
        allPoints.add(new Point("饮水处(WT9)						    1层", 0.0450, 0.9329, 1.0));
        allPoints.add(new Point("登机口(E6)							1层", 0.0316, 0.8636, 1.0));
        allPoints.add(new Point("登机口(E5)							1层", 0.0316, 0.7423, 1.0));
        allPoints.add(new Point("饮水处(WT7)						    1层", 0.0450, 0.6808, 1.0));
        allPoints.add(new Point("登机口(E4)							1层", 0.0316, 0.6211, 1.0));
        allPoints.add(new Point("登机口(E3)							1层", 0.0316, 0.5018, 1.0));
        allPoints.add(new Point("登机口(E2)							1层", 0.0316, 0.3825, 1.0));
        allPoints.add(new Point("饮水处(WT3)					     	1层", 0.0450, 0.3190, 1.0));
        allPoints.add(new Point("登机口(E1)							1层", 0.0316, 0.2632, 1.0));
        allPoints.add(new Point("卫生间(W3)							1层", 0.0604, 0.1939, 1.0));
        allPoints.add(new Point("商店(S19)							1层", 0.1085, 0.2863, 1.0));
        allPoints.add(new Point("商店(S18)							1层", 0.1085, 0.3267, 1.0));
        allPoints.add(new Point("商店(S17)							1层", 0.1085, 0.3632, 1.0));
        allPoints.add(new Point("商店(S20)							1层", 0.1797, 0.3998, 1.0));
        allPoints.add(new Point("商店(S27)							1层", 0.1509, 0.7404, 1.0));
        allPoints.add(new Point("商店(S26)							1层", 0.1509, 0.7808, 1.0));
        allPoints.add(new Point("商店(S25)							1层", 0.1509, 0.8212, 1.0));
        allPoints.add(new Point("商店(S28)							1层", 0.2529, 0.8212, 1.0));
        allPoints.add(new Point("商店(S29)							1层", 0.2529, 0.7808, 1.0));
        allPoints.add(new Point("商店(S30)							1层", 0.2529, 0.7404, 1.0));
        allPoints.add(new Point("商店(S31)							1层", 0.4511, 0.8212, 1.0));
        allPoints.add(new Point("商店(S32)							1层", 0.4511, 0.7808, 1.0));
        allPoints.add(new Point("商店(S33)							1层", 0.4511, 0.7404, 1.0));
        allPoints.add(new Point("电梯(ES5)							1层", 0.3279, 0.6942, 1.0));
        allPoints.add(new Point("扶梯(EL5)							1层", 0.3048, 0.6750, 1.0));
        allPoints.add(new Point("行李盘(B1)							1层", 0.2086, 0.5711, 1.0));
        allPoints.add(new Point("行李盘(B2)							1层", 0.3279, 0.5691, 1.0));
        allPoints.add(new Point("行李盘(B3)							1层", 0.4511, 0.6288, 1.0));


        //F1SubRight

        allPoints.add(new Point("商店(S24)							1层", 0.8167, 0.3998, 1.0));
        allPoints.add(new Point("商店(S21)							1层", 0.8860, 0.3632, 1.0));
        allPoints.add(new Point("商店(S22)							1层", 0.8860, 0.3267, 1.0));
        allPoints.add(new Point("商店(S23)							1层", 0.8860, 0.2882, 1.0));
        allPoints.add(new Point("卫生间(W4)							1层", 0.9302, 0.1939, 1.0));
        allPoints.add(new Point("登机口(E18)						    1层", 0.9630, 0.2632, 1.0));
        allPoints.add(new Point("饮水处(WT4)						    1层", 0.9514, 0.3190, 1.0));
        allPoints.add(new Point("登机口(E17)						    1层", 0.9630, 0.3825, 1.0));
        allPoints.add(new Point("登机口(E16)						    1层", 0.9630, 0.5018, 1.0));
        allPoints.add(new Point("登机口(E15)						    1层", 0.9630, 0.6211, 1.0));
        allPoints.add(new Point("饮水处(WT8)						    1层", 0.9514, 0.6808, 1.0));
        allPoints.add(new Point("登机口(E14)						    1层", 0.9630, 0.7404, 1.0));
        allPoints.add(new Point("登机口(E13)						    1层", 0.9630, 0.8636, 1.0));
        allPoints.add(new Point("饮水处(WT10)						1层", 0.9514, 0.9329, 1.0));
        allPoints.add(new Point("卫生间(W8)							1层", 0.9283, 0.9329, 1.0));
        allPoints.add(new Point("电梯(ES8)							1层", 0.8764, 0.9155, 1.0));
        allPoints.add(new Point("扶梯(EL8)							1层", 0.8552, 0.9502, 1.0));
        allPoints.add(new Point("登机口(E12)						    1层", 0.7975, 0.9713, 1.0));
        allPoints.add(new Point("登机口(E11)						    1层", 0.6782, 0.9713, 1.0));
        allPoints.add(new Point("饮水处(WT12)						1层", 0.6166, 0.9502, 1.0));
        allPoints.add(new Point("登机口(E10)						    1层", 0.5625, 0.9713, 1.0));
        allPoints.add(new Point("商店(S34)							1层", 0.5492, 0.8212, 1.0));
        allPoints.add(new Point("商店(S35)							1层", 0.5492, 0.7808, 1.0));
        allPoints.add(new Point("商店(S36)							1层", 0.5492, 0.7404, 1.0));
        allPoints.add(new Point("商店(S37)							1层", 0.7436, 0.8212, 1.0));
        allPoints.add(new Point("商店(S38)							1层", 0.7436, 0.7808, 1.0));
        allPoints.add(new Point("商店(S39)							1层", 0.7436, 0.7404, 1.0));
        allPoints.add(new Point("商店(S40)							1层", 0.8436, 0.8212, 1.0));
        allPoints.add(new Point("商店(S41)							1层", 0.8436, 0.7808, 1.0));
        allPoints.add(new Point("商店(S42)							1层", 0.8436, 0.7404, 1.0));
        allPoints.add(new Point("行李盘(B4)							1层", 0.5473, 0.6288, 1.0));
        allPoints.add(new Point("行李盘(B5)							1层", 0.6685, 0.5711, 1.0));
        allPoints.add(new Point("行李盘(B6)							1层", 0.7878, 0.5711, 1.0));
        allPoints.add(new Point("电梯(ES6)							1层", 0.6666, 0.6942, 1.0));
        allPoints.add(new Point("扶梯(EL6)							1层", 0.6878, 0.6750, 1.0));


        //F2SubTop Points
        allPoints.add(new Point("安检口(SC2)					    	2层", 0.4992, 0.8001, 2.0));
        allPoints.add(new Point("登机口(E9)							2层", 0.4395, 0.9713, 2.0));
        allPoints.add(new Point("饮水处(WT11)						2层", 0.3799, 0.9502, 2.0));
        allPoints.add(new Point("登机口(E8)							2层", 0.3183, 0.9713, 2.0));
        allPoints.add(new Point("登机口(E7)							2层", 0.1990, 0.9713, 2.0));
        allPoints.add(new Point("扶梯(EL7)							2层", 0.1432, 0.9502, 2.0));
        allPoints.add(new Point("电梯(ES7)							2层", 0.1182, 0.9155, 2.0));
        allPoints.add(new Point("卫生间(W7)							2层", 0.0604, 0.9329, 2.0));
        allPoints.add(new Point("饮水处(WT9)						    2层", 0.0450, 0.9329, 2.0));
        allPoints.add(new Point("登机口(E6)							2层", 0.0316, 0.8636, 2.0));
        allPoints.add(new Point("登机口(E5)							2层", 0.0316, 0.7404, 2.0));
        allPoints.add(new Point("饮水处(WT7)						    2层", 0.0450, 0.6808, 2.0));
        allPoints.add(new Point("登机口(E4)							2层", 0.0316, 0.6211, 2.0));
        allPoints.add(new Point("登机口(E3)							2层", 0.0316, 0.5018, 2.0));
        allPoints.add(new Point("登机口(E2)							2层", 0.0316, 0.3825, 2.0));
        allPoints.add(new Point("饮水处(WT3)						    2层", 0.0450, 0.3190, 2.0));
        allPoints.add(new Point("登机口(E1)							2层", 0.0316, 0.2632, 2.0));
        allPoints.add(new Point("卫生间(W3)							2层", 0.0604, 0.1939, 2.0));
        allPoints.add(new Point("商店(S19)							2层", 0.1085, 0.2863, 2.0));
        allPoints.add(new Point("商店(S18)							2层", 0.1085, 0.3190, 2.0));
        allPoints.add(new Point("商店(S17)							2层", 0.1085, 0.3632, 2.0));
        allPoints.add(new Point("商店(S20)							2层", 0.1797, 0.3998, 2.0));
        allPoints.add(new Point("安检口(SC1)					    	2层", 0.2009, 0.5018, 2.0));
        allPoints.add(new Point("商店(S27)							2层", 0.1509, 0.7404, 2.0));
        allPoints.add(new Point("商店(S26)							2层", 0.1509, 0.7808, 2.0));
        allPoints.add(new Point("商店(S25)							2层", 0.1509, 0.9232, 2.0));
        allPoints.add(new Point("商店(S28)							2层", 0.2163, 0.8520, 2.0));
        allPoints.add(new Point("商店(S29)							2层", 0.2548, 0.8520, 2.0));

        allPoints.add(new Point("商店(S33)							2层", 0.7416, 0.8520, 2.0));
        allPoints.add(new Point("商店(S34)							2层", 0.7763, 0.8520, 2.0));
        allPoints.add(new Point("商店(S35)							2层", 0.8436, 0.8212, 2.0));
        allPoints.add(new Point("商店(S31)							2层", 0.8436, 0.7808, 2.0));
        allPoints.add(new Point("商店(S32)							2层", 0.8436, 0.7404, 2.0));
        allPoints.add(new Point("安检口(SC3)						    2层", 0.7975, 0.5018, 2.0));
        allPoints.add(new Point("商店(S24)							2层", 0.8186, 0.3960, 2.0));
        allPoints.add(new Point("商店(S21)							2层", 0.8860, 0.3632, 2.0));
        allPoints.add(new Point("商店(S22)							2层", 0.8860, 0.3190, 2.0));
        allPoints.add(new Point("商店(S23)							2层", 0.8860, 0.2863, 2.0));
        allPoints.add(new Point("卫生间(W4)							2层", 0.9302, 0.1939, 2.0));
        allPoints.add(new Point("登机口(E18)						    2层", 0.9630, 0.2632, 2.0));
        allPoints.add(new Point("饮水处(WT4)						    2层", 0.9514, 0.3190, 2.0));
        allPoints.add(new Point("登机口(E17)					     	2层", 0.9630, 0.3825, 2.0));
        allPoints.add(new Point("登机口(E16)						    2层", 0.9630, 0.5018, 2.0));
        allPoints.add(new Point("登机口(E15)						    2层", 0.9630, 0.6211, 2.0));
        allPoints.add(new Point("饮水处(WT8)						    2层", 0.9514, 0.6808, 2.0));
        allPoints.add(new Point("登机口(E14)						    2层", 0.9630, 0.7404, 2.0));
        allPoints.add(new Point("登机口(E13)						    2层", 0.9630, 0.8636, 2.0));
        allPoints.add(new Point("饮水处(WT10)						2层", 0.9514, 0.9329, 2.0));
        allPoints.add(new Point("卫生间(W8)							2层", 0.9283, 0.9329, 2.0));
        allPoints.add(new Point("电梯(ES8)							2层", 0.8764, 0.9155, 2.0));
        allPoints.add(new Point("扶梯(EL8)							2层", 0.8552, 0.9502, 2.0));
        allPoints.add(new Point("登机口(E12)						    2层", 0.7975, 0.9713, 2.0));
        allPoints.add(new Point("登机口(E11)						    2层", 0.6782, 0.9713, 2.0));
        allPoints.add(new Point("饮水处(WT12)						2层", 0.6166, 0.9502, 2.0));
        allPoints.add(new Point("登机口(E10)						    2层", 0.5625, 0.9713, 2.0));


        //F2SubCenter  Points

        allPoints.add(new Point("值机柜台(C1)						2层", 0.3279, 0.5711, 2.0));
        allPoints.add(new Point("值机柜台(C2)						2层", 0.4280, 0.5711, 2.0));
        allPoints.add(new Point("值机柜台(C3)						2层", 0.4491, 0.5711, 2.0));
        allPoints.add(new Point("值机柜台(C4)						2层", 0.5473, 0.5711, 2.0));
        allPoints.add(new Point("值机柜台(C5)						2层", 0.5665, 0.5711, 2.0));
        allPoints.add(new Point("值机柜台(C6)						2层", 0.6666, 0.5711, 2.0));
        allPoints.add(new Point("扶梯(EL5)							2层", 0.3029, 0.6750, 2.0));
        allPoints.add(new Point("电梯(ES5)							2层", 0.3279, 0.7000, 2.0));
        allPoints.add(new Point("电梯(ES6)							2层", 0.6666, 0.7000, 2.0));
        allPoints.add(new Point("扶梯(EL6)							2层", 0.6897, 0.6750, 2.0));


        //F2SubBottom  Points

        allPoints.add(new Point("饮水处(WT5)						    2层", 0.2702, 0.3498, 2.0));
        allPoints.add(new Point("卫生间(W5)							2层", 0.2490, 0.3324, 2.0));
        allPoints.add(new Point("扶梯(EL3)							2层", 0.3087, 0.3344, 2.0));
        allPoints.add(new Point("电梯(ES3)							2层", 0.3279, 0.3151, 2.0));
        allPoints.add(new Point("商店(S9)							2层", 0.2490, 0.2593, 2.0));
        allPoints.add(new Point("商店(S10)							2层", 0.2490, 0.2247, 2.0));
        allPoints.add(new Point("商店(S11)							2层", 0.2490, 0.1843, 2.0));
        allPoints.add(new Point("商店(S12)							2层", 0.1836, 0.1535, 2.0));
        allPoints.add(new Point("商店(S5)							2层", 0.3606, 0.2497, 2.0));
        allPoints.add(new Point("商店(S6)							2层", 0.3991, 0.2497, 2.0));
        allPoints.add(new Point("询问处/票务(Q1)					    2层", 0.3799, 0.1746, 2.0));
        allPoints.add(new Point("商店(S7)							2层", 0.5973, 0.2497, 2.0));
        allPoints.add(new Point("商店(S8)							2层", 0.6358, 0.2497, 2.0));
        allPoints.add(new Point("询问处/票务(Q2)					    2层", 0.6185, 0.1746, 2.0));
        allPoints.add(new Point("卫生间(W6)							2层", 0.7493, 0.3344, 2.0));
        allPoints.add(new Point("饮水处(WT6)						    2层", 0.7263, 0.3498, 2.0));
        allPoints.add(new Point("扶梯(EL4)							2层", 0.6916, 0.3344, 2.0));
        allPoints.add(new Point("电梯(ES4)							2层", 0.6666, 0.3151, 2.0));
        allPoints.add(new Point("商店(S13)							2层", 0.7493, 0.2593, 2.0));
        allPoints.add(new Point("商店(S14)							2层", 0.7493, 0.2247, 2.0));
        allPoints.add(new Point("商店(S15)							2层", 0.7493, 0.1843, 2.0));
        allPoints.add(new Point("商店(S16)							2层", 0.8167, 0.1535, 2.0));
        allPoints.add(new Point("饮水处(WT1)						    2层", 0.0489, 0.0900, 2.0));
        allPoints.add(new Point("卫生间(W1)							2层", 0.0681, 0.0707, 2.0));
        allPoints.add(new Point("电梯(ES1)							2层", 0.1220, 0.0900, 2.0));
        allPoints.add(new Point("扶梯(EL1)							2层", 0.1393, 0.0553, 2.0));
        allPoints.add(new Point("出入口(D1)							2层", 0.2606, 0.0419, 2.0));
        allPoints.add(new Point("商店(S1)							2层", 0.3606, 0.0746, 2.0));
        allPoints.add(new Point("商店(S2)							2层", 0.3991, 0.0746, 2.0));
        allPoints.add(new Point("出入口(D2)							2层", 0.4992, 0.0419, 2.0));
        allPoints.add(new Point("商店(S3)							2层", 0.6012, 0.0746, 2.0));
        allPoints.add(new Point("商店(S4)							2层", 0.6358, 0.0746, 2.0));
        allPoints.add(new Point("出入口(D3)							2层", 0.7378, 0.0419, 2.0));
        allPoints.add(new Point("扶梯(EL2)							2层", 0.8552, 0.0553, 2.0));
        allPoints.add(new Point("电梯(ES2)							2层", 0.8764, 0.0900, 2.0));
        allPoints.add(new Point("卫生间(W2)							2层", 0.9302, 0.0707, 2.0));
        allPoints.add(new Point("饮水处(WT2)						    2层", 0.9456, 0.0900, 2.0));

        return allPoints;
    }
}

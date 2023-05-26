/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ktdh_nhom3;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Administrator
 */
public class MyCircle {
    private Point p;
    private int R;

    public MyCircle() {
    }

    public MyCircle(Point p, int R) {
        this.p = p;
        this.R = R;
    }

    public Point getP() {
        return p;
    }

    public void setP(Point p) {
        this.p = p;
    }

    public int getR() {
        return R;
    }

    public void setR(int R) {
        this.R = R;
    }
    
}

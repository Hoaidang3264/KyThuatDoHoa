/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ktdh_nhom3;

import java.awt.Point;

/**
 *
 * @author Administrator
 */
public class MyEclipse {
    private Point p;
    private int a, b;

    public MyEclipse() {
    }

    public MyEclipse(Point p, int a, int b) {
        this.p = p;
        this.a = a;
        this.b = b;
    }

    public Point getP() {
        return p;
    }

    public void setP(Point p) {
        this.p = p;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
    
}

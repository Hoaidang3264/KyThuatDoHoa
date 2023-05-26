/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ktdh_nhom3;

/**
 *
 * @author Administrator
 */
public class HinhDacBiet {
    private MyCircle tron;
    private MyEclipse elip;
    private MyParallelogram hbh;
    private MyLine line1;
    private MyLine line2;
    private MyLine line3;
    private MyLine line4;

    public HinhDacBiet() {
    }

    public HinhDacBiet(MyCircle tron, MyEclipse elip, MyParallelogram hbh, MyLine line1, MyLine line2, MyLine line3, MyLine line4) {
        this.tron = tron;
        this.elip = elip;
        this.hbh = hbh;
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.line4 = line4;
    }

    public MyCircle getTron() {
        return tron;
    }

    public void setTron(MyCircle tron) {
        this.tron = tron;
    }

    public MyEclipse getElip() {
        return elip;
    }

    public void setElip(MyEclipse elip) {
        this.elip = elip;
    }

    public MyParallelogram getHbh() {
        return hbh;
    }

    public void setHbh(MyParallelogram hbh) {
        this.hbh = hbh;
    }

    public MyLine getLine1() {
        return line1;
    }

    public void setLine1(MyLine line1) {
        this.line1 = line1;
    }

    public MyLine getLine2() {
        return line2;
    }

    public void setLine2(MyLine line2) {
        this.line2 = line2;
    }

    public MyLine getLine3() {
        return line3;
    }

    public void setLine3(MyLine line3) {
        this.line3 = line3;
    }

    public MyLine getLine4() {
        return line4;
    }

    public void setLine4(MyLine line4) {
        this.line4 = line4;
    }
}

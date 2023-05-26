/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ktdh_nhom3;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class HinhTraiTim {

    private Point[] zigZag;
    private Point tamTT, tamTrai, tamPhai, muiTT;
    private int banKinh;

    public HinhTraiTim() {
    }

    public HinhTraiTim(Point[] zigZag, Point tamTT, Point tamTrai, Point tamPhai, Point muiTT, int banKinh) {
        this.zigZag = zigZag;
        this.tamTT = tamTT;
        this.tamTrai = tamTrai;
        this.tamPhai = tamPhai;
        this.muiTT = muiTT;
        this.banKinh = banKinh;
    }

    public Point[] getZigZag() {
        return zigZag;
    }

    public void setZigZag(Point[] zigZag) {
        this.zigZag = zigZag;
    }

    public Point getTamTrai() {
        return tamTrai;
    }

    public void setTamTrai(Point tamTrai) {
        this.tamTrai = tamTrai;
    }

    public Point getTamPhai() {
        return tamPhai;
    }

    public void setTamPhai(Point tamPhai) {
        this.tamPhai = tamPhai;
    }

    public Point getMuiTT() {
        return muiTT;
    }

    public void setMuiTT(Point muiTT) {
        this.muiTT = muiTT;
    }

    public int getBanKinh() {
        return banKinh;
    }

    public void setBanKinh(int banKinh) {
        this.banKinh = banKinh;
    }

    public Point getTamTT() {
        return tamTT;
    }

    public void setTamTT(Point tamTT) {
        this.tamTT = tamTT;
    }

}

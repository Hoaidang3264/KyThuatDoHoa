/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ktdh_nhom3;

import com.sun.xml.internal.ws.transport.http.HttpAdapter;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Color;
import static java.awt.Color.BLACK;
import static java.awt.Color.GREEN;
import static java.awt.Color.RED;
import static java.awt.Color.WHITE;
import java.awt.Graphics2D;
import java.awt.Point;
import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class KTDH_Nhom3 extends javax.swing.JFrame {

    HinhDacBiet hd1;
    HinhTraiTim htt;
    int chonHinh = 0;
    boolean play = false;
    int backward = 0;
    int change = 0;
    int change1 = 0;
    int hieuUng = -1;
    boolean isHeart = false;
    ArrayList<Point> heart = new ArrayList<Point>();

    public KTDH_Nhom3() {
        initComponents();
        setTitle("Vẽ hình cơ bản nhóm 3");
        setLocationRelativeTo(null);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g = jPanel_draw.getGraphics();
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.translate(350, 300);
        g2d.scale(1.0, -1.0); //Đổi hướng trục Oy
        trucOxy(g2d);
        if (chonHinh == 1) {
            drawHD1(g2d);
        }
        if (chonHinh == 2) {
            drawCar(g2d);
        }
        if (chonHinh == 3) {
            drawHTT(g2d);
        }
    }

    public void trucOxy(Graphics2D g2d) {
        g2d.setColor(Color.RED);

        //vẽ hệ tọa độ
        g2d.drawLine(-350, 0, 450, 0); //vẽ Ox
        g2d.drawLine(0, 300, 0, -400); //vẽ Oy

        g2d.scale(1.0, -1.0);
        g2d.drawString("O", -10, 15);
        g2d.drawString("X", 350, 15);
        g2d.drawString("Y", 10, -280);
        g2d.scale(1.0, -1.0);
    }

    public void putPixel(Graphics2D g2d, int x, int y) {
        g2d.fillRect(x, y, 5, 5);
        if (isHeart == true) {
            heart.add(new Point(x, y));
        }
    }

    public void drawHD1(Graphics2D g2d) {
        //ve hbh
        g2d.setColor(Color.WHITE);
        Bresenham(g2d, hd1.getHbh().getA().x, hd1.getHbh().getA().y, hd1.getHbh().getB().x, hd1.getHbh().getB().y);
        Bresenham(g2d, hd1.getHbh().getC().x, hd1.getHbh().getC().y, hd1.getHbh().getA().x, hd1.getHbh().getA().y);
        Bresenham(g2d, hd1.getHbh().getC().x, hd1.getHbh().getC().y, hd1.getHbh().getD().x, hd1.getHbh().getD().y);
        Bresenham(g2d, hd1.getHbh().getD().x, hd1.getHbh().getD().y, hd1.getHbh().getB().x, hd1.getHbh().getB().y);
        //ve chan ban
        Bresenham(g2d, hd1.getLine1().getA().x, hd1.getHbh().getC().y, hd1.getLine1().getB().x, hd1.getLine1().getB().y);
        drawLineNetDut(g2d, hd1.getLine1().getA().x, hd1.getLine1().getA().y, hd1.getLine1().getB().x, hd1.getHbh().getC().y);
        Bresenham(g2d, hd1.getLine2().getA().x, hd1.getLine2().getA().y, hd1.getLine2().getB().x, hd1.getLine2().getB().y);
        Bresenham(g2d, hd1.getLine3().getA().x, hd1.getLine3().getA().y, hd1.getLine3().getB().x, hd1.getLine3().getB().y);
        Bresenham(g2d, hd1.getLine4().getA().x, hd1.getLine4().getA().y, hd1.getLine4().getB().x, hd1.getLine4().getB().y);
        toaDoHD1(g2d);
        if (play == false) {
            //ve hinh tron
            g2d.setColor(Color.red);
            hd1.getTron().getP();
            drawCircle(g2d, 0, hd1.getTron().getR(), hd1.getTron().getR(), hd1.getTron().getP());
            //ve bong hinh elip
            g2d.setColor(Color.blue);
            drawEclipse(g2d, hd1.getElip().getA(), hd1.getElip().getB(), hd1.getElip().getP());
        } else {
            if (change % 10 == 0) {
                change = 0;
                hd1.getElip().setA(20);
                hd1.getElip().setB(10);
            }
            Point tt, tl_tamE, dA, dB;
            dA = new Point(hd1.getElip().getP().x + hd1.getElip().getA(), hd1.getElip().getP().y);
            dB = new Point(hd1.getElip().getP().x, hd1.getElip().getP().y + hd1.getElip().getB());
            if (change < 5) {
                tt = tinhTien(hd1.getTron().getP(), 0, -20);
                tl_tamE = bienDoiTyLe(hd1.getElip().getP(), 1.2, 1.2);
                dA = bienDoiTyLe(dA, 1.2, 1.2);
                dB = bienDoiTyLe(dB, 1.2, 1.2);
            } else {
                tt = tinhTien(hd1.getTron().getP(), 0, 20);
                tl_tamE = bienDoiTyLe(hd1.getElip().getP(), 1 / 1.2, 1 / 1.2);
                dA = bienDoiTyLe(dA, 1 / 1.2, 1 / 1.2);
                dB = bienDoiTyLe(dB, 1 / 1.2, 1 / 1.2);
            }
            change++;
            hd1.getElip().setA(dA.x - tl_tamE.x);
            hd1.getElip().setB(dB.y - tl_tamE.y);
            hd1.getTron().setP(tt);
            g2d.setColor(Color.red);
            drawCircle(g2d, 0, hd1.getTron().getR(), hd1.getTron().getR(), hd1.getTron().getP());
            hd1.getElip().setP(tinhTien(tl_tamE, hd1.getElip().getP().x - tl_tamE.x, hd1.getElip().getP().y - tl_tamE.y));
            g2d.setColor(Color.blue);
            drawEclipse(g2d, hd1.getElip().getA(), hd1.getElip().getB(), hd1.getElip().getP());
            chiTietHD1(g2d);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(KTDH_Nhom3.class.getName()).log(Level.SEVERE, null, ex);
            }
            repaint();
        }
    }

    public void toaDoHD1(Graphics2D g2d) {
        g2d.scale(1.0, -1.0);
        g2d.drawString("A(" + hd1.getHbh().getA().x / 5 + "," + hd1.getHbh().getA().y / 5 + ")",
                hd1.getHbh().getA().x, -hd1.getHbh().getA().y - 5);
        g2d.drawString("B(" + hd1.getHbh().getB().x / 5 + "," + hd1.getHbh().getB().y / 5 + ")",
                hd1.getHbh().getB().x, -hd1.getHbh().getB().y - 5);
        g2d.drawString("C(" + hd1.getHbh().getC().x / 5 + "," + hd1.getHbh().getC().y / 5 + ")",
                hd1.getHbh().getC().x - 50, -hd1.getHbh().getC().y);
        g2d.drawString("D(" + hd1.getHbh().getD().x / 5 + "," + hd1.getHbh().getD().y / 5 + ")",
                hd1.getHbh().getD().x + 5, -hd1.getHbh().getD().y + 5);
        g2d.drawString("A1(" + hd1.getLine1().getB().x / 5 + "," + hd1.getLine1().getB().y / 5 + ")",
                hd1.getLine1().getB().x, -hd1.getLine1().getB().y + 5);
        g2d.drawString("B1(" + hd1.getLine2().getB().x / 5 + "," + hd1.getLine2().getB().y / 5 + ")",
                hd1.getLine2().getB().x, -hd1.getLine2().getB().y + 5);
        g2d.drawString("C1(" + hd1.getLine3().getB().x / 5 + "," + hd1.getLine3().getB().y / 5 + ")",
                hd1.getLine3().getB().x, -hd1.getLine3().getB().y + 10);
        g2d.drawString("D1(" + hd1.getLine4().getB().x / 5 + "," + hd1.getLine4().getB().y / 5 + ")",
                hd1.getLine4().getB().x, -hd1.getLine4().getB().y + 10);
        g2d.scale(1.0, -1.0);
    }

    public void chiTietHD1(Graphics2D g2d) {
        jLabel_tamO.setText("(" + hd1.getTron().getP().x / 5 + "," + hd1.getTron().getP().y / 5 + ")");
        jLabel_bk.setText(hd1.getTron().getR() / 5 + "");
        jLabel_tamE.setText("(" + hd1.getElip().getP().x / 5 + "," + hd1.getElip().getP().y / 5 + ")");
        jLabel_A.setText(hd1.getElip().getA() + "");
        jLabel_B.setText(hd1.getElip().getB() + "");
    }

    public void drawHTT(Graphics2D g2d) {
        //draw zig zag
        g2d.setColor(GREEN);
        for (int i = 0; i < 5; i++) {
            if (hieuUng == i && play == true) {
                g2d.setColor(WHITE);
            } else {
                g2d.setColor(GREEN);
            }
            Bresenham(g2d, htt.getZigZag()[i].x, htt.getZigZag()[i].y, htt.getZigZag()[i + 1].x, htt.getZigZag()[i + 1].y);
            if (play == true) {
                //Ox
                Bresenham(g2d, doixung(htt.getZigZag()[i], "Ox").x, doixung(htt.getZigZag()[i], "Ox").y, doixung(htt.getZigZag()[i + 1], "Ox").x, doixung(htt.getZigZag()[i + 1], "Ox").y);
                //Oy
                Bresenham(g2d, doixung(htt.getZigZag()[i], "Oy").x, doixung(htt.getZigZag()[i], "Oy").y, doixung(htt.getZigZag()[i + 1], "Oy").x, doixung(htt.getZigZag()[i + 1], "Oy").y);
                //O
                Bresenham(g2d, doixung(htt.getZigZag()[i], "O").x, doixung(htt.getZigZag()[i], "O").y, doixung(htt.getZigZag()[i + 1], "O").x, doixung(htt.getZigZag()[i + 1], "O").y);
            }
        }
        hieuUng++;
        if (hieuUng == 5) {
            hieuUng = 0;
        }
        //draw Heart
        if (play == false) {
            g2d.setColor(Color.PINK);
            veTraiTim(g2d);
        } else {
            if (change1 == 2) {
                change1 = 0;
            }
            Point tamTT_tl, Trai, diemDuoi;
            if (change1 == 0) {
                tamTT_tl = bienDoiTyLe(htt.getTamTT(), 1.5, 1.5);
                Trai = bienDoiTyLe(htt.getTamTrai(), 1.5, 1.5);
                diemDuoi = bienDoiTyLe(htt.getMuiTT(), 1.5, 1.5);
            } else {
                tamTT_tl = bienDoiTyLe(htt.getTamTT(), 1 / 1.5, 1 / 1.5);
                Trai = bienDoiTyLe(htt.getTamTrai(), 1 / 1.5, 1 / 1.5);
                diemDuoi = bienDoiTyLe(htt.getMuiTT(), 1 / 1.5, 1 / 1.5);
            }
            change1++;
            int R = tamTT_tl.x - Trai.x;
            int dis = Math.abs(tamTT_tl.y - diemDuoi.y);
            htt.setBanKinh(R);
            htt.setTamTT(tinhTien(tamTT_tl, htt.getTamTT().x - tamTT_tl.x, htt.getTamTT().y - tamTT_tl.y));
            htt.setTamTrai(new Point(htt.getTamTT().x - htt.getBanKinh(), htt.getTamTT().y));
            htt.setTamPhai(new Point(htt.getTamTT().x + htt.getBanKinh(), htt.getTamTT().y));
            htt.setMuiTT(new Point(htt.getTamTT().x, htt.getTamTT().y - dis));
            // draw Heart
            g2d.setColor(Color.PINK);
            veTraiTim(g2d);
            /*//Ox
            Point tamTT_Ox = doixung(htt.getTamTT(), "Ox");
            veDx_TT(g2d, tamTT_Ox, dis);
            //Oy
            Point tamTT_Oy = doixung(htt.getTamTT(), "Oy");
            veDx_TT(g2d, tamTT_Oy, dis);
            //O
            Point tamTT_O = doixung(htt.getTamTT(), "O");
            veDx_TT(g2d, tamTT_O, dis);
            //ghi label chi tiet
            chiTietTraiTim(g2d, tamTT_Ox, tamTT_Oy, tamTT_O);*/

            //ox_oy_o
            for (Point h : heart) {
                Point tmp_Ox = doixung(h, "Ox");
                putPixel(g2d, tmp_Ox.x, tmp_Ox.y);
                Point tmp_Oy = doixung(h, "Oy");
                putPixel(g2d, tmp_Oy.x, tmp_Oy.y);
                Point tmp_O = doixung(h, "O");
                putPixel(g2d, tmp_O.x, tmp_O.y);
            }
            try {
                TimeUnit.MILLISECONDS.sleep(700);
            } catch (InterruptedException ex) {
                Logger.getLogger(KTDH_Nhom3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        jLabel_tamTrai.setText("(" + htt.getTamTrai().x / 5 + "," + htt.getTamTrai().y / 5 + ")");
        jLabel_tamPhai.setText("(" + htt.getTamPhai().x / 5 + "," + htt.getTamPhai().y / 5 + ")");
        jLabel_muiTT.setText("(" + htt.getMuiTT().x / 5 + "," + htt.getMuiTT().y / 5 + ")");
        jLabel_bkTT.setText(htt.getBanKinh() / 5 + "");
        if (play == true) {
            repaint();
        }
    }

    public void veTraiTim(Graphics2D g2d) {
        isHeart = true;
        heart.clear();
        drawHalfCircle(g2d, 0, htt.getBanKinh(), htt.getBanKinh(), htt.getTamTrai());
        drawHalfCircle(g2d, 0, htt.getBanKinh(), htt.getBanKinh(), htt.getTamPhai());
        Bresenham(g2d, htt.getMuiTT().x, htt.getMuiTT().y, htt.getTamTrai().x - htt.getBanKinh(), htt.getTamTrai().y);
        Bresenham(g2d, htt.getMuiTT().x, htt.getMuiTT().y, htt.getTamPhai().x + htt.getBanKinh(), htt.getTamPhai().y);
        isHeart = false;
    }

    public void veDx_TT(Graphics2D g2d, Point tamTT_dx, int dis) {
        Point tamTrai_dx = new Point(tamTT_dx.x - htt.getBanKinh(), tamTT_dx.y);
        Point tamPhai_dx = new Point(tamTT_dx.x + htt.getBanKinh(), tamTT_dx.y);
        Point muiTT_dx = new Point(tamTT_dx.x, tamTT_dx.y - dis);
        drawHalfCircle(g2d, 0, htt.getBanKinh(), htt.getBanKinh(), tamTrai_dx);
        drawHalfCircle(g2d, 0, htt.getBanKinh(), htt.getBanKinh(), tamPhai_dx);
        Bresenham(g2d, muiTT_dx.x, muiTT_dx.y, tamTrai_dx.x - htt.getBanKinh(), tamTrai_dx.y);
        Bresenham(g2d, muiTT_dx.x, muiTT_dx.y, tamPhai_dx.x + htt.getBanKinh(), tamPhai_dx.y);
    }

    public void chiTietTraiTim(Graphics2D g2d, Point tamTT_Ox, Point tamTT_Oy, Point tamTT_O) {
        //label zig zag
        g2d.scale(1.0, -1.0);
        for (int i = 0; i < 6; i++) {
            String chuoi = "Z" + (i + 1) + "(" + htt.getZigZag()[i].x / 5 + "," + htt.getZigZag()[i].y / 5 + ")";
            g2d.drawString(chuoi, htt.getZigZag()[i].x - 20, -htt.getZigZag()[i].y + 20);
        }
        g2d.drawString("T(" + htt.getTamTT().x / 5 + "," + htt.getTamTT().y / 5 + ")", htt.getTamTT().x - 10, -htt.getTamTT().y + 20);
        g2d.drawString("T_Ox(" + tamTT_Ox.x / 5 + "," + tamTT_Ox.y / 5 + ")", tamTT_Ox.x - 10, -tamTT_Ox.y + 20);
        g2d.drawString("T_Oy(" + tamTT_Oy.x / 5 + "," + tamTT_Oy.y / 5 + ")", tamTT_Oy.x - 10, -tamTT_Oy.y + 20);
        g2d.drawString("T_O(" + tamTT_O.x / 5 + "," + tamTT_O.y / 5 + ")", tamTT_O.x - 10, -tamTT_O.y + 20);
        g2d.scale(1.0, -1.0);
    }

    //Bresenham
    public void Bresenham(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        //đường chéo
        if (x2 >= x1 && y2 < y1) {
            int Dx = Math.abs(x2 - x1);
            int Dy = Math.abs(y2 - y1);
            int p = 2 * Dy - Dx;
            int c1 = 2 * Dy;
            int c2 = 2 * (Dy - Dx);
            int x = x1;
            int y = y1;
            putPixel(g2d, x, y);
            while (x != x2 && y != y2) {
                if (p < 0) {
                    p += c1;
                } else {
                    p += c2;
                    y--;
                }
                x++;
                putPixel(g2d, x, y);
            }
        } else if (x2 < x1 && y2 < y1) {
            int Dx = Math.abs(x2 - x1);
            int Dy = Math.abs(y2 - y1);
            int p = 2 * Dy - Dx;
            int c1 = 2 * Dy;
            int c2 = 2 * (Dy - Dx);
            int x = x1;
            int y = y1;
            putPixel(g2d, x, y);
            while (x != x2 && y != y2) {
                if (p < 0) {
                    p += c1;
                } else {
                    p += c2;
                    y--;
                }
                x--;
                putPixel(g2d, x, y);
            }
        } else if (x2 < x1 && y2 > y1) {
            int Dx = Math.abs(x2 - x1);
            int Dy = Math.abs(y2 - y1);
            int p = 2 * Dy - Dx;
            int c1 = 2 * Dy;
            int c2 = 2 * (Dy - Dx);
            int x = x1;
            int y = y1;
            putPixel(g2d, x, y);
            while (x != x2 && y != y2) {
                if (p < 0) {
                    p += c1;
                } else {
                    p += c2;
                    y++;
                }
                x--;
                putPixel(g2d, x, y);
            }
        } else if (x2 >= x1 && y2 > y1) {
            int Dx = Math.abs(x2 - x1);
            int Dy = Math.abs(y2 - y1);
            int p = 2 * Dy - Dx;
            int c1 = 2 * Dy;
            int c2 = 2 * (Dy - Dx);
            int x = x1;
            int y = y1;
            putPixel(g2d, x, y);
            while (x != x2 && y != y2) {
                if (p < 0) {
                    p += c1;
                } else {
                    p += c2;
                    y++;
                }
                x++;
                putPixel(g2d, x, y);
            }
        }
        //đường thẳng
        if (x1 == x2) {
            if (y1 > y2) {
                int Dx = Math.abs(x2 - x1);
                int Dy = Math.abs(y2 - y1);
                int p = 2 * Dy - Dx;
                int c1 = 2 * Dy;
                int c2 = 2 * (Dy - Dx);
                int x = x1;
                int y = y2;
                putPixel(g2d, x, y);
                while (y != y1) {
                    if (p < 0) {
                        p += c1;
                    } else {
                        p += c2;
                        y++;
                    }
                    putPixel(g2d, x, y);
                }
            }
            if (y1 < y2) {
                int Dx = Math.abs(x2 - x1);
                int Dy = Math.abs(y2 - y1);
                int p = 2 * Dy - Dx;
                int c1 = 2 * Dy;
                int c2 = 2 * (Dy - Dx);
                int x = x1;
                int y = y1;
                putPixel(g2d, x, y);
                while (y != y2) {
                    if (p < 0) {
                        p += c1;
                    } else {
                        p += c2;
                        y++;
                    }
                    putPixel(g2d, x, y);
                }
            }
        }
        if (y1 == y2) {
            if (x1 > x2) {
                int Dx = Math.abs(x2 - x1);
                int Dy = Math.abs(y2 - y1);
                int p = 2 * Dy - Dx;
                int c1 = 2 * Dy;
                int c2 = 2 * (Dy - Dx);
                int x = x2;
                int y = y1;
                putPixel(g2d, x, y);
                while (x != x1) {
                    if (p < 0) {
                        p += c1;
                    } else {
                        p += c2;
                    }
                    x++;
                    putPixel(g2d, x, y);
                }
            }
            if (x1 < x2) {
                int Dx = Math.abs(x2 - x1);
                int Dy = Math.abs(y2 - y1);
                int p = 2 * Dy - Dx;
                int c1 = 2 * Dy;
                int c2 = 2 * (Dy - Dx);
                int x = x1;
                int y = y1;
                putPixel(g2d, x, y);
                while (x != x2) {
                    if (p < 0) {
                        p += c1;
                    } else {
                        p += c2;
                    }
                    x++;
                    putPixel(g2d, x, y);
                }
            }
        }
    }

    public void drawLineNetDut(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        int Dx, Dy, p, Const1, Const2;
        int x, y;

        Dx = x2 - x1;
        Dy = y2 - y1;
        Dx = Math.abs(Dx);
        Dy = Math.abs(Dy);
        p = 2 * Dy - Dx;
        Const1 = 2 * Dy;
        Const2 = 2 * (Dy - Dx);
        x = x1;
        y = y1;
        putPixel(g2d, x, y);
        int dem = 0;
        while (x < x2) {
            if (p < 0) {
                p += Const1;
            } else {
                p += Const2;
                y++;
            }
            x++;
            if (dem % 10 == 0) {
                dem = 0;
            }
            if (dem < 5) {
                putPixel(g2d, x, y);
                dem++;
            }
        }
        dem = 0;
        if (x1 == x2) {
            int ymax = y1;
            if (y1 < y2) {
                y = y1;
                ymax = y2;
            } else {
                y = y2;
                ymax = y1;
            }
            while (y != ymax) {
                y++;
                if (y % 10 == 0) {
                    dem = 0;
                }
                if (dem < 5) {
                    putPixel(g2d, x1, y);
                    dem++;
                }
            }
        }
    }

    public void draw8Point(Graphics2D g2d, int x, int y, Point tamO) {
        putPixel(g2d, x + tamO.x, y + tamO.y);
        putPixel(g2d, -x + tamO.x, -y + tamO.y); //O
        putPixel(g2d, x + tamO.x, -y + tamO.y); //ox
        putPixel(g2d, -x + tamO.x, y + tamO.y); //oy
        putPixel(g2d, y + tamO.x, x + tamO.y);
        putPixel(g2d, -y + tamO.x, -x + tamO.y);
        putPixel(g2d, -y + tamO.x, x + tamO.y);
        putPixel(g2d, y + tamO.x, -x + tamO.y);
    }

    public void drawCircle(Graphics2D g2d, int x0, int y0, int R, Point tamO) {
        int x, y;
        int p = 3 - 2 * R;
        x = x0;
        y = y0;
        double limit = R * Math.sqrt(2) / 2;
        while (x <= limit) {
            if (p >= 0) {
                p = p + 4 * (x - y) + 10;
                y--;
            } else {
                p = p + 4 * x + 6;
            }
            draw8Point(g2d, x, y, tamO);
            x++;
        }
    }

    public void ve2DiemNuaDuoi(Graphics2D g2d, int x, int y, Point tamO) {
        putPixel(g2d, x + tamO.x, -y + tamO.y);
        putPixel(g2d, -x + tamO.x, -y + tamO.y);
    }

    public void ve2DiemNuaTren(Graphics2D g2d, int x, int y, Point tamO) {
        putPixel(g2d, x + tamO.x, y + tamO.y);
        putPixel(g2d, -x + tamO.x, y + tamO.y);
    }

    public void drawEclipse(Graphics2D g2d, int a, int b, Point tamO) {
        int x, y;
        float p, a2, b2;
        a2 = (float) pow(a, 2);
        b2 = (float) pow(b, 2);
        x = 0;
        y = b;
        p = 2 * ((float) b2 / a2) - (2 * b) + 1;
        //nua duoi
        //ve nhanh thu 1(tu tren xuong )
        while (((float) b2 / a2) * x <= y) {
            ve2DiemNuaDuoi(g2d, x, y, tamO);
            if (p < 0) {
                p = p + 2 * ((float) b2 / a2) * (2 * x + 3);
            } else {
                p = p - 4 * y + 2 * ((float) b2 / a2) * (2 * x + 3);
                y--;
            }
            x++;
        }
        //ve nhanh thu 2(tu duoi len )
        y = 0;
        x = a;
        p = 2 * ((float) a2 / b2) - 2 * a + 1;
        while (((float) a2 / b2) * y <= x) {
            ve2DiemNuaDuoi(g2d, x, y, tamO);
            if (p < 0) {
                p = p + 2 * ((float) a2 / b2) * (2 * y + 3);
            } else {
                p = p - 4 * x + 2 * ((float) a2 / b2) * (2 * y + 3);
                x = x - 1;
            }
            y = y + 1;
        }

        //nua tren
        x = 0;
        y = b;
        p = 2 * ((float) b2 / a2) - (2 * b) + 1;
        //ve nhanh thu 1(tu tren xuong )
        while (((float) b2 / a2) * x <= y) {
            if (p < 0) {
                p = p + 2 * ((float) b2 / a2) * (2 * x + 3);
            } else {
                p = p - 4 * y + 2 * ((float) b2 / a2) * (2 * x + 3);
                y--;
            }
            ve2DiemNuaTren(g2d, x, y, tamO);
            x++;
        }
        //ve nhanh thu 2(tu duoi len )
        y = 0;
        x = a;
        p = 2 * ((float) a2 / b2) - 2 * a + 1;
        while (((float) a2 / b2) * y <= x) {
            if (p < 0) {
                p = p + 2 * ((float) a2 / b2) * (2 * y + 3);
            } else {
                p = p - 4 * x + 2 * ((float) a2 / b2) * (2 * y + 3);
                x = x - 1;
            }
            ve2DiemNuaTren(g2d, x, y, tamO);
            y++;
        }
    }

    public Point tinhTien(Point p, int trX, int trY) {
        int[] maTranA = {p.x, p.y, 1};
        int[][] maTranTinhTien = {{1, 0, 0}, {0, 1, 0}, {trX, trY, 1}};
        int[] maTranB = new int[3];
        int dem = 0;
        for (int i = 0; i < 3; i++) {
            maTranB[i] = maTranA[0] * maTranTinhTien[0][dem]
                    + maTranA[1] * maTranTinhTien[1][dem] + maTranA[2] * maTranTinhTien[2][dem];
            dem++;
        }
        Point p1 = new Point(maTranB[0], maTranB[1]);
        return p1;
    }

    public Point bienDoiTyLe(Point p, double Sx, double Sy) {
        int[] maTranA = {p.x, p.y, 1};
        double[][] maTranTyLe = {{Sx, 0, 0}, {0, Sy, 0}, {0, 0, 1}};
        int[] maTranB = new int[3];
        int dem = 0;
        for (int i = 0; i < 3; i++) {
            maTranB[i] = (int) (maTranA[0] * maTranTyLe[0][dem]
                    + maTranA[1] * maTranTyLe[1][dem] + maTranA[2] * maTranTyLe[2][dem]);
            dem++;
        }
        Point p1 = new Point(maTranB[0], maTranB[1]);
        return p1;
    }

    //Hình động 2///////////////////////////////////////////////////////////////////////////////////
    //Xoay hinh
    public Point xoayHinh(Point p, Point diemXoay, double Anpha) {
        int[][] T1 = {{1, 0, 0}, {0, 1, 0}, {-(p.x), -(p.y), 1}};//T1 tịnh tiến tâm bánh xe về 0
        //Q(O,anpha)
        int[][] Q = {{(int) Math.cos(Anpha), (int) Math.sin(Anpha), 0}, {-((int) Math.sin(Anpha)), (int) Math.cos(Anpha), 0}, {0, 0, 1}};
        //T2
        int[][] T2 = {{1, 0, 0}, {0, 1, 0}, {p.x, p.y, 1}};
        //Điểm quay
        int[] maTranB = {diemXoay.x, diemXoay.y, 1};

        int[][] maTranKQ1 = new int[3][3];
        int[][] maTranKQ2 = new int[3][3];
        int[] maTranKQ3 = new int[3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    maTranKQ1[i][j] = maTranKQ1[i][j] + T1[i][k] * Q[k][j];
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    maTranKQ2[i][j] = maTranKQ2[i][j] + maTranKQ1[i][k] * T2[k][j];
                }
            }
        }
        int dem = 0;
        for (int i = 0; i < 3; i++) {
            maTranKQ3[i] = maTranB[0] * maTranKQ2[0][dem]
                    + maTranB[1] * maTranKQ2[1][dem] + maTranB[2] * maTranKQ2[2][dem];
            dem++;
        }
        Point p1 = new Point(maTranKQ3[0], maTranKQ3[1]);
        return p1;
    }

    //Hàm vẽ ô tô
    int xwheel1 = -175, ywheel1 = 20, xwheel2 = -50, ywheel2 = 20, r = 15,
            xa = -155, ya = 69, xb = -85, yb = 69, xc = -55, yc = 39, xd = -185, yd = 39,
            xe = -205, ye = 39, xf = -35, yf = 39, xg = -15, yg = 19, xh = -225, yh = 19,
            xi = -110, yi = 69, xn = -110, yn = 19, xj = -135, yj = 69, xm = -135, ym = 19,
            xk = -85, yk = 39, v1x = -182, v1y = 29, v2x = -165, v2y = 29, v3x = xwheel1, v3y = 10,
            v4x = -57, v4y = 29, v5x = -40, v5y = 29, v6x = xwheel2, v6y = 10;
    Point a = new Point(xa, ya);
    Point b = new Point(xb, yb);
    Point c = new Point(xc, yc);
    Point d = new Point(xd, yd);
    Point e = new Point(xe, ye);
    Point f = new Point(xf, yf);
    Point g = new Point(xg, yg);
    Point h = new Point(xh, yh);
    Point i = new Point(xi, yi);
    Point n = new Point(xn, yn);
    Point j = new Point(xj, yj);
    Point m = new Point(xm, ym);
    Point k = new Point(xk, yk);
    Point w1 = new Point(xwheel1, ywheel1);
    Point w2 = new Point(xwheel2, ywheel2);
    Point w01 = new Point(xwheel1, ywheel1);
    Point w02 = new Point(xwheel2, ywheel2);
    Point v1 = new Point(v1x, v1y);
    Point v2 = new Point(v2x, v2y);
    Point v3 = new Point(v3x, v3y);
    Point v4 = new Point(v4x, v4y);
    Point v5 = new Point(v5x, v5y);
    Point v6 = new Point(v6x, v6y);

//      ???????????????????????????????????????????
    //Hàm vẽ tịnh tiến
    public void drawCarRun(Graphics2D g2d, Point a, Point b, Point c, Point d, Point e, Point f, Point g, Point h, Point i, Point n, Point j, Point m, Point k, Point w1, Point w2, Point v1, Point v2, Point v3, Point v4, Point v5, Point v6, int xrun, double anpha) {
        a = tinhTien(this.a, xrun, 0);
        this.a = a;
        b = tinhTien(this.b, xrun, 0);
        this.b = b;
        c = tinhTien(this.c, xrun, 0);
        this.c = c;
        d = tinhTien(this.d, xrun, 0);
        this.d = d;
        e = tinhTien(this.e, xrun, 0);
        this.e = e;
        f = tinhTien(this.f, xrun, 0);
        this.f = f;
        g = tinhTien(this.g, xrun, 0);
        this.g = g;
        h = tinhTien(this.h, xrun, 0);
        this.h = h;
        j = tinhTien(this.j, xrun, 0);
        this.j = j;
        i = tinhTien(this.i, xrun, 0);
        this.i = i;
        n = tinhTien(this.n, xrun, 0);
        this.n = n;
        m = tinhTien(this.m, xrun, 0);
        this.m = m;
        k = tinhTien(this.k, xrun, 0);
        this.k = k;
        w1 = tinhTien(this.w1, xrun, 0);
        this.w1 = w1;
        w2 = tinhTien(this.w2, xrun, 0);
        this.w2 = w2;
        v1 = tinhTien(this.v1, xrun, 0);
        this.v1 = v1;
        v2 = tinhTien(this.v2, xrun, 0);
        this.v2 = v2;
        v3 = tinhTien(this.v3, xrun, 0);
        this.v3 = v3;
        v4 = tinhTien(this.v4, xrun, 0);
        this.v4 = v4;
        v5 = tinhTien(this.v5, xrun, 0);
        this.v5 = v5;
        v6 = tinhTien(this.v6, xrun, 0);
        this.v6 = v6;
        //xoay
        //sau
        v1 = xoayHinh(w1, v1, anpha);
        this.v1 = v1;
        v2 = xoayHinh(w1, v2, anpha);
        this.v2 = v2;
        v3 = xoayHinh(w1, v3, anpha);
        this.v3 = v3;
        //truoc
        v4 = xoayHinh(w2, v4, anpha);
        this.v4 = v4;
        v5 = xoayHinh(w2, v5, anpha);
        this.v5 = v5;
        v6 = xoayHinh(w2, v6, anpha);
        this.v6 = v6;

        if (a.x == 165) {
            backward = 1;
        }
        if (a.x == -155) {
            backward = 0;
        }

        Bresenham(g2d, this.a.x, this.a.y, this.b.x, this.b.y);//d1:AB
        Bresenham(g2d, this.d.x, this.d.y, this.c.x, this.c.y);//d2:DC
        Bresenham(g2d, this.a.x, this.a.y, this.d.x, this.d.y);//nối A-D
        Bresenham(g2d, this.b.x, this.b.y, this.c.x, this.c.y);//nối B-C

        //Hình thang EFGH
        Bresenham(g2d, this.d.x, this.d.y, this.e.x, this.e.y);//nối D-E
        Bresenham(g2d, this.c.x, this.c.y, this.f.x, this.f.y);//nối C-F
        Bresenham(g2d, this.h.x, this.h.y, this.g.x, this.g.y);//nối H-G

        Bresenham(g2d, this.e.x, this.e.y, this.h.x, this.h.y);//nối E-H
        Bresenham(g2d, this.f.x, this.f.y, this.g.x, this.g.y);//nối F-G

        //Vẽ bánh xe
        drawCircle(g2d, 0, 12, 15, this.w1);
        drawCircle(g2d, 0, 12, 15, this.w2);

        //vẽ cánh cửa hcn
        Bresenham(g2d, this.i.x, this.i.y, this.n.x, this.n.y);//I-N
        Bresenham(g2d, this.j.x, j.y, this.m.x, this.m.y);//J-M

        //vẽ cửa kính tam giác
        Bresenham(g2d, this.b.x, this.b.y, this.k.x, this.k.y);

        //vẽ nan xe
        //sau
        g2d.setColor(Color.WHITE);
        Bresenham(g2d, this.v1.x, this.v1.y, w1.x, w1.y);
        g2d.setColor(Color.MAGENTA);
        Bresenham(g2d, this.v2.x, this.v2.y, w1.x, w1.y);
        g2d.setColor(Color.cyan);
        Bresenham(g2d, this.v3.x, this.v3.y, w1.x, w1.y);

        //trc
        g2d.setColor(Color.white);
        Bresenham(g2d, this.v4.x, this.v4.y, w2.x, w2.y);
        g2d.setColor(Color.MAGENTA);
        Bresenham(g2d, this.v5.x, this.v5.y, w2.x, w2.y);
        g2d.setColor(Color.CYAN);
        Bresenham(g2d, this.v6.x, this.v6.y, w2.x, w2.y);

        jLabel_w1.setText("(" + Integer.toString(this.w1.x / 5) + "," + Integer.toString(this.w1.y / 5) + ")");
        jLabel_w2.setText("(" + Integer.toString(this.w2.x / 5) + "," + Integer.toString(this.w2.y / 5) + ")");
        jLabel_r.setText(Integer.toString(r / 5));
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(KTDH_Nhom3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//      ???????????????????????????????????????????????????????????????
    public void drawCar(Graphics2D g2d) {
        if (play == false) {
            a = new Point(xa, ya);
            b = new Point(xb, yb);
            c = new Point(xc, yc);
            d = new Point(xd, yd);
            e = new Point(xe, ye);
            f = new Point(xf, yf);
            g = new Point(xg, yg);
            h = new Point(xh, yh);
            i = new Point(xi, yi);
            n = new Point(xn, yn);
            j = new Point(xj, yj);
            m = new Point(xm, ym);
            k = new Point(xk, yk);
            w1 = new Point(xwheel1, ywheel1);
            w2 = new Point(xwheel2, ywheel2);
            v1 = new Point(v1x, v1y);
            v2 = new Point(v2x, v2y);
            v3 = new Point(v3x, v3y);
            v4 = new Point(v4x, v4y);
            v5 = new Point(v5x, v5y);
            v6 = new Point(v6x, v6y);
            //Hình thang ABCD
            Bresenham(g2d, xa, ya, xb, yb);//d1:AB
            Bresenham(g2d, xd, yd, xc, yc);//d2:DC
            Bresenham(g2d, xa, ya, xd, yd);//nối A-D
            Bresenham(g2d, xb, yb, xc, yc);//nối B-C

            //Hình thang EFGH
            Bresenham(g2d, xd, yd, xe, ye);//nối D-E
            Bresenham(g2d, xc, yc, xf, yf);//nối C-F
            Bresenham(g2d, xh, yh, xg, yg);//nối H-G

            Bresenham(g2d, xe, ye, xh, yh);//nối E-H
            Bresenham(g2d, xf, yf, xg, yg);//nối F-G

            //Vẽ bánh xe
            drawCircle(g2d, 0, 12, 15, w01);
            drawCircle(g2d, 0, 12, 15, w02);

            //vẽ cánh cửa hcn
            Bresenham(g2d, xi, yi, xn, yn);//I-N
            Bresenham(g2d, xj, yj, xm, ym);//J-M

            //vẽ cửa kính tam giác
            Bresenham(g2d, xb, yb, xk, yk);

            //vẽ nan xe
            //sau
            g2d.setColor(Color.WHITE);
            Bresenham(g2d, v1x, v1y, w1.x, w1.y);
            g2d.setColor(Color.MAGENTA);
            Bresenham(g2d, v2x, v2y, w1.x, w1.y);
            g2d.setColor(Color.cyan);
            Bresenham(g2d, v3x, v3y, w1.x, w1.y);

            //trc
            g2d.setColor(Color.WHITE);
            Bresenham(g2d, v4x, v4y, w2.x, w2.y);
            g2d.setColor(Color.MAGENTA);
            Bresenham(g2d, v5x, v5y, w2.x, w2.y);
            g2d.setColor(Color.CYAN);
            Bresenham(g2d, v6x, v6y, w2.x, w2.y);
            try {
                TimeUnit.SECONDS.sleep((long) 3);
            } catch (InterruptedException ex) {
            }
            synchronized (KTDH_Nhom3.this) {
                KTDH_Nhom3.this.notify();
            }
        } else {
            Point a = new Point(xa, ya);
            Point b = new Point(xb, yb);
            Point c = new Point(xc, yc);
            Point d = new Point(xd, yd);
            Point e = new Point(xe, ye);
            Point f = new Point(xf, yf);
            Point g = new Point(xg, yg);
            Point h = new Point(xh, yh);
            Point i = new Point(xi, yi);
            Point n = new Point(xn, yn);
            Point j = new Point(xj, yj);
            Point m = new Point(xm, ym);
            Point k = new Point(xk, yk);
            Point w1 = new Point(xwheel1, ywheel1);
            Point w2 = new Point(xwheel2, ywheel2);
            Point v1 = new Point(v1x, v1y);
            Point v2 = new Point(v2x, v2y);
            Point v3 = new Point(v3x, v3y);
            Point v4 = new Point(v4x, v4y);
            Point v5 = new Point(v5x, v5y);
            Point v6 = new Point(v6x, v6y);
            g2d.setColor(Color.blue);
            if (backward == 0) {
                drawCarRun(g2d, a, b, c, d, e, f, g, h, i, n, j, m, k, w1, w2, v1, v2, v3, v4, v5, v6, 20, -Math.PI / 2);
            } else if (backward == 1) {
                drawCarRun(g2d, a, b, c, d, e, f, g, h, i, n, j, m, k, w1, w2, v1, v2, v3, v4, v5, v6, -20, Math.PI / 2);
            }
        }
        repaint();
    }

    /////////hinh dong 3//////////////////////
    public void draw4Point(Graphics2D g2d, int x, int y, Point tamO) {
        putPixel(g2d, x + tamO.x, y + tamO.y);
        putPixel(g2d, -x + tamO.x, y + tamO.y);
        putPixel(g2d, y + tamO.x, x + tamO.y);
        putPixel(g2d, -y + tamO.x, x + tamO.y);
    }

    public void drawHalfCircle(Graphics2D g2d, int x0, int y0, int R, Point tamO) {
        int x, y;
        int p = 3 - 2 * R;
        x = x0;
        y = y0;
        double limit = R * Math.sqrt(2) / 2;
        while (x <= limit) {
            if (p >= 0) {
                p = p + 4 * (x - y) + 10;
                y--;
            } else {
                p = p + 4 * x + 6;
            }
            draw4Point(g2d, x, y, tamO);
            x++;
        }
    }

    public Point doixung(Point p, String dx) {
        int[] maTranA = {p.x, p.y, 1};
        int[][] maTranDoiXung = {{0, 0, 0}, {0, 0, 0}, {0, 0, 1}};
        if (dx.equals("Ox")) {
            maTranDoiXung[0][0] = 1;
            maTranDoiXung[1][1] = -1;
        } else if (dx.equals("Oy")) {
            maTranDoiXung[0][0] = -1;
            maTranDoiXung[1][1] = 1;
        } else if (dx.equals("O")) {
            maTranDoiXung[0][0] = -1;
            maTranDoiXung[1][1] = -1;
        }
        int[] maTranB = new int[3];
        int dem = 0;
        for (int i = 0; i < 3; i++) {
            maTranB[i] = maTranA[0] * maTranDoiXung[0][dem]
                    + maTranA[1] * maTranDoiXung[1][dem] + maTranA[2] * maTranDoiXung[2][dem];
            dem++;
        }
        Point p1 = new Point(maTranB[0], maTranB[1]);
        return p1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup_2D = new javax.swing.ButtonGroup();
        jPanel_choose = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton_play = new javax.swing.JButton();
        jButton_clear = new javax.swing.JButton();
        jRadioButton_HD1 = new javax.swing.JRadioButton();
        jRadioButton_HD2 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel_tamO = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel_bk = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel_tamE = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel_A = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel_B = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel_w1 = new javax.swing.JLabel();
        jLabel_w2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel_r = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel_tamTrai = new javax.swing.JLabel();
        jLabel_tamPhai = new javax.swing.JLabel();
        jLabel_muiTT = new javax.swing.JLabel();
        jLabel_bkTT = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton3D = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jPanel_draw = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel_choose.setBackground(new java.awt.Color(51, 51, 51));
        jPanel_choose.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 255, 255));
        jLabel1.setText("CƠ BẢN");

        jButton_play.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ktdh_nhom3/video.png"))); // NOI18N
        jButton_play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_playActionPerformed(evt);
            }
        });

        jButton_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ktdh_nhom3/delete.png"))); // NOI18N
        jButton_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_clearActionPerformed(evt);
            }
        });

        buttonGroup_2D.add(jRadioButton_HD1);
        jRadioButton_HD1.setForeground(new java.awt.Color(255, 0, 0));
        jRadioButton_HD1.setText("Hình động 1");
        jRadioButton_HD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_HD1ActionPerformed(evt);
            }
        });

        buttonGroup_2D.add(jRadioButton_HD2);
        jRadioButton_HD2.setForeground(new java.awt.Color(255, 0, 0));
        jRadioButton_HD2.setText("Hình động 2");
        jRadioButton_HD2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_HD2ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setText("    Nhóm 3");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 153)));

        jLabel3.setText("               Chi tiết");

        jLabel4.setText("Tâm hình tròn");

        jLabel_tamO.setText("(0,0)");

        jLabel5.setText("Bán kính R = ");

        jLabel_bk.setText("0");

        jLabel6.setText("Tâm hình elip");

        jLabel_tamE.setText("(0,0)");

        jLabel7.setText("cạnh a=       ");

        jLabel_A.setText("0");

        jLabel8.setText("cạnh b=       ");

        jLabel_B.setText("0");

        jLabel11.setText("Tâm bánh xe sau");

        jLabel_w1.setText("(0,0)");

        jLabel_w2.setText("(0,0)");

        jLabel12.setText("Tâm bánh xe trước");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Hình ô tô");

        jLabel14.setText("Bán kính = ");

        jLabel_r.setText("0");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Hình trái tim");

        jLabel16.setText("Tâm trái= ");

        jLabel17.setText("Tâm phải= ");

        jLabel18.setText("Mũi trái tim= ");

        jLabel19.setText("Bán kính= ");

        jLabel_tamTrai.setText("(0,0)");

        jLabel_tamPhai.setText("(0,0)");

        jLabel_muiTT.setText("(0,0)");

        jLabel_bkTT.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19))
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel_muiTT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel_tamPhai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel_tamTrai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel_bkTT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jLabel15)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_tamO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_bk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_tamE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_A, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_B, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel_r, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12)
                                        .addComponent(jLabel11)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel_w1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                                    .addComponent(jLabel_w2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel_tamO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel_bk))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel_tamE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel_A))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel_B))
                .addGap(7, 7, 7)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel_w1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_w2)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_r)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel_tamTrai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel_tamPhai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel_muiTT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel_bkTT))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Hình 2D");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 255, 255));
        jLabel10.setText("VẼ HÌNH ");

        jButton3D.setBackground(new java.awt.Color(0, 51, 153));
        jButton3D.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton3D.setForeground(new java.awt.Color(255, 255, 255));
        jButton3D.setText("3D");
        jButton3D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3DActionPerformed(evt);
            }
        });

        buttonGroup_2D.add(jRadioButton1);
        jRadioButton1.setForeground(new java.awt.Color(255, 0, 0));
        jRadioButton1.setText("Hình động 3");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_chooseLayout = new javax.swing.GroupLayout(jPanel_choose);
        jPanel_choose.setLayout(jPanel_chooseLayout);
        jPanel_chooseLayout.setHorizontalGroup(
            jPanel_chooseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_chooseLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel_chooseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_chooseLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
            .addGroup(jPanel_chooseLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel_chooseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton_HD2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButton_HD1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel_chooseLayout.createSequentialGroup()
                .addGroup(jPanel_chooseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_chooseLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jButton_play)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_clear))
                    .addGroup(jPanel_chooseLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jButton3D, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel_chooseLayout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel_chooseLayout.setVerticalGroup(
            jPanel_chooseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_chooseLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton_HD1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton_HD2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton1)
                .addGap(15, 15, 15)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_chooseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_play, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton_clear, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3D, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        jPanel_draw.setBackground(new java.awt.Color(10, 10, 10));
        jPanel_draw.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel_draw.setPreferredSize(new java.awt.Dimension(645, 2));

        javax.swing.GroupLayout jPanel_drawLayout = new javax.swing.GroupLayout(jPanel_draw);
        jPanel_draw.setLayout(jPanel_drawLayout);
        jPanel_drawLayout.setHorizontalGroup(
            jPanel_drawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 741, Short.MAX_VALUE)
        );
        jPanel_drawLayout.setVerticalGroup(
            jPanel_drawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_choose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_draw, javax.swing.GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_choose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel_draw, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton_HD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_HD1ActionPerformed
        //Hinh binh hanh - mat ban
        play = false;
        Point a = new Point(100, 130);
        Point b = new Point(300, 130);
        Point c = new Point(50, 80);
        Point d = new Point(0, 0);
        d.x = b.x - a.x + c.x; // tinh diem thu 4 cua hbh
        d.y = c.y;
        MyParallelogram hbh = new MyParallelogram(a, b, c, d);
        int x_hbh = (a.x + d.x) / 2;
        int y_hbh = (a.y + d.y) / 2;
        Point tamHbh = new Point(x_hbh, y_hbh);
        //4 chan ban - 4 duong thang
        MyLine line1 = new MyLine(a, new Point(a.x, 30));
        MyLine line2 = new MyLine(b, new Point(b.x, 30));
        MyLine line3 = new MyLine(c, new Point(c.x, 0));
        MyLine line4 = new MyLine(d, new Point(d.x, 0));
        //hinh tron
        Point tamO = new Point(tamHbh.x, 240);
        int R = 50;
        MyCircle tron = new MyCircle(tamO, R);
        //hinh elip
        Point tam_elip = new Point(tamHbh.x, tamHbh.y);
        int a_elip = 20;
        int b_elip = 10;
        MyEclipse elip = new MyEclipse(tam_elip, a_elip, b_elip);
        hd1 = new HinhDacBiet(tron, elip, hbh, line1, line2, line3, line4);
        chonHinh = 1;
        repaint();
    }//GEN-LAST:event_jRadioButton_HD1ActionPerformed

    private void jButton_playActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_playActionPerformed
        // TODO add your handling code here:
        if (play == true) {
            play = false;
        } else {
            play = true;
        }
        repaint();
    }//GEN-LAST:event_jButton_playActionPerformed

    private void jButton_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_clearActionPerformed
        // TODO add your handling code here:
        play = false;
        buttonGroup_2D.clearSelection();
        chonHinh = 0;
        change = 0;
        repaint();
    }//GEN-LAST:event_jButton_clearActionPerformed

    private void jRadioButton_HD2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_HD2ActionPerformed
        // TODO add your handling code here:
        play = false;
        chonHinh = 2;
        repaint();
    }//GEN-LAST:event_jRadioButton_HD2ActionPerformed

    private void jButton3DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3DActionPerformed
        new Frame3D().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton3DActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        play = false;
        chonHinh = 3;
        Point[] zigZag = {new Point(20, 50), new Point(100, 50), new Point(150, 10), new Point(220, 80), new Point(270, 50), new Point(350, 50)};
        Point tamTT = new Point(190, 200);
        int bk = 40;
        Point tamTrai = new Point(tamTT.x - bk, tamTT.y);
        Point tamPhai = new Point(tamTT.x + bk, tamTT.y);
        Point muiTT = new Point(tamTT.x, tamTT.y - 2 * bk);
        htt = new HinhTraiTim(zigZag, tamTT, tamTrai, tamPhai, muiTT, bk);
        repaint();
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KTDH_Nhom3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KTDH_Nhom3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KTDH_Nhom3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KTDH_Nhom3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KTDH_Nhom3().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup_2D;
    private javax.swing.JButton jButton3D;
    private javax.swing.JButton jButton_clear;
    private javax.swing.JButton jButton_play;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_A;
    private javax.swing.JLabel jLabel_B;
    private javax.swing.JLabel jLabel_bk;
    private javax.swing.JLabel jLabel_bkTT;
    private javax.swing.JLabel jLabel_muiTT;
    private javax.swing.JLabel jLabel_r;
    private javax.swing.JLabel jLabel_tamE;
    private javax.swing.JLabel jLabel_tamO;
    private javax.swing.JLabel jLabel_tamPhai;
    private javax.swing.JLabel jLabel_tamTrai;
    private javax.swing.JLabel jLabel_w1;
    private javax.swing.JLabel jLabel_w2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_choose;
    private javax.swing.JPanel jPanel_draw;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton_HD1;
    private javax.swing.JRadioButton jRadioButton_HD2;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ktdh_nhom3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import javax.swing.ButtonGroup;

/**
 *
 * @author Admin
 */
public class Frame3D extends javax.swing.JFrame {

    /**
     * Creates new form Frame3D
     */
    boolean pixel = false;
    
    BufferedImage savingpaint = null;
    private Point pointTemp = new Point(0, 0);

    ArrayList<Point> arrPoint = new ArrayList<Point>();
    boolean xoa = false;

    public Frame3D() {
        initComponents();
        setLocationRelativeTo(null);
        ButtonGroup buttonGroup = new ButtonGroup();
//        new KTDH_Nhom3().setVisible(false);
        buttonGroup.add(jRBHinhHop);
        buttonGroup.add(jRBHinhTru);
        savingpaint = new BufferedImage(DrawPanel.getWidth(), DrawPanel.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        //HeToaDo(null);
        txtX.setText("0");
        txtY.setText("0");
        txtZ.setText("0");
        txtBanKinh.setText("0");
        txtCaoHinhTru.setText("0");
        txtDai.setText("0");
        txtRong.setText("0");
        txtCaoHinhCN.setText("0");

        jRBHinhHop.setSelected(true);
        txtCaoHinhTru.setEnabled(false);
        txtBanKinh.setEnabled(false);
        txtDai.setEnabled(true);
        txtRong.setEnabled(true);
        txtCaoHinhCN.setEnabled(true);
        jBtnHinhHopCN.setEnabled(true);
    }

    //Vẽ hệ tọa độ 3D
    private void HeToaDo(Graphics2D g) {
        super.paint(g);
        Graphics2D grphs = (Graphics2D) g;

        if (pixel == true) {
            g.setColor(Color.BLUE);
            int temp = 0;
            for (int i = 0; i < 500; i++) {
                g.drawLine(temp, 0, temp, 1000); //Oy
                g.drawLine(0, temp, 1000, temp);//Ox
                temp+=5;
                System.out.println("draw" + temp);
            }
        }
        
        ///set color for paintDraw
        g.setColor(Color.RED);

        //Tạo Gốc tọa độ mới
        g.translate(300, 300);
        g.scale(1.0, -1.0); //Đỏi hướng trục Oy
        g.fillRect(0, 0, 5, 5);

        //Vẽ hệ tọa độ
        g.drawLine(0, 0, 0, 275); //Oy
        g.drawLine(0, 0, 275, 0); //Ox
        g.drawLine(0, 0, -225, -225); //Oz
        //g.scale(1.0, -1.0);

        //Vẽ đơn vị Oy
        for (int i = 0; i <= 275; i += 5) {
            g.drawLine(i, -2, i, 2);
        }

        //Vẽ đơn vị Ox
        for (int i = 0; i <= 275; i += 5) {
            g.drawLine(-2, i, 2, i);
        }

        //Vẽ đơn vị Oz
        for (double i = 0; i >= -225; i -= (5 * sqrt(2) / 2)) {
            g.drawLine((int) i - 1, (int) i - 1, (int) i + 1, (int) i - 1);
        }

        //Cài đặt điểm
        g.drawString("O", -10, 10);
        g.drawString("x", 275, 10);
        g.scale(1.0, -1.0);
        g.drawString("y", 10, -275);
        g.drawString("z", -230, 230);

        g.scale(1.0, -1.0);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g = DrawPanel.getGraphics();
        Graphics2D grphs = (Graphics2D) g;
        System.out.println("Da goi ham Repaint");

        //grphs.setColor(Color.blue);
        HeToaDo(grphs);

        if (xoa == false) {
            for (Point p : arrPoint) {
                g.fillRect(p.x, p.y, 5, 5);
            }

        } else {
            arrPoint.clear();
            xoa = false;

        }

    }

    public void putPixel(int x, int y) {
        pointTemp = new Point(0, 0);
        pointTemp.x = x;
        pointTemp.y = y;
        arrPoint.add(pointTemp);
        repaint();
        //g.fillRect(pointTemp.x, pointTemp.y, 2, 2);

    }

    public void VeNetLien(int x1, int y1, int x2, int y2) {
        if (x1 == x2) { //Đường thẳng hướng đứng
            if (y1 > y2) {
                int Dx = Math.abs(x2 - x1);
                int Dy = Math.abs(y2 - y1);
                int p = 2 * Dy - Dx;
                int c1 = 2 * Dy;
                int c2 = 2 * (Dy - Dx);
                int x = x1;
                int y = y2;
                putPixel(x, y);
                while (y != y1) {
                    if (p < 0) {
                        p += c1;
                    } else {
                        p += c2;
                        y++;
                    }
                    putPixel(x, y);
                }
            } else {
                System.out.println("123");
                int Dx = Math.abs(x2 - x1);
                int Dy = Math.abs(y2 - y1);
                int p = 2 * Dy - Dx;
                int c1 = 2 * Dy;
                int c2 = 2 * (Dy - Dx);
                int x = x1;
                int y = y1;
                putPixel(x, y);
                while (y != y2) {
                    if (p < 0) {
                        p += c1;
                    } else {
                        p += c2;
                        y++;
                    }
                    putPixel(x, y);
                }
            }
        } else if (y1 == y2) { //Đường thẳng ngang
            if (x1 > x2) {
                int Dx = Math.abs(x2 - x1);
                int Dy = Math.abs(y2 - y1);
                int p = 2 * Dy - Dx;
                int c1 = 2 * Dy;
                int c2 = 2 * (Dy - Dx);
                int x = x2;
                int y = y1;
                putPixel(x, y);
                while (x != x1) {

                    if (p < 0) {
                        p += c1;
                    } else {
                        p += c2;
                    }
                    x++;
                    putPixel(x, y);
                }
            } else {
                int Dx = Math.abs(x2 - x1);
                int Dy = Math.abs(y2 - y1);
                int p = 2 * Dy - Dx;
                int c1 = 2 * Dy;
                int c2 = 2 * (Dy - Dx);
                int x = x1;
                int y = y1;
                putPixel(x, y);
                while (x != x2) {

                    if (p < 0) {
                        p += c1;
                    } else {
                        p += c2;
                    }
                    x++;
                    putPixel(x, y);
                }
            }
        }

        //Đường thẳng chéo///////
        if (x2 > x1) {
            int Dx = x2 - x1; //200
            int Dy = y2 - y1; //0
            int x = x1;
            int y = y1;

            putPixel(x1, y1);
            float D = Dy - (Dx >> 1); // ~ float D = Dy - Dx/2;
            while (x <= x2) {
                x++;
                if (D < 0) {
                    D = D + Dy;
                } else {
                    D = D + (Dy - Dx);
                    y++;
                }
                putPixel(x, y);

                //putPixel(g2d, x, y);
            }
        } else if (x2 < x1) {
            int Dx = x1 - x2;
            int Dy = y1 - y2;
            int x = x2;
            int y = y2;
            putPixel(x, y);
            float D = Dy - (Dx >> 1); // ~ float D = Dy - Dx/2;
            while (x <= x1) {
                x++;
                if (D < 0) {
                    D = D + Dy;
                } else {
                    D = D + (Dy - Dx);
                    y++;
                }
                putPixel(x, y);

            }
        }
    }

     public void VeNetDut(int x1, int y1, int x2, int y2) {
        //Nét đứt chéo
        if (x2 > x1) {
            int Dx = x2 - x1; //200
            int Dy = y2 - y1; //0
            int x = x1;
            int y = y1;

            putPixel(x1, y1);
            float D = Dy - (Dx >> 1); // ~ float D = Dy - Dx/2;
            while (x <= x2) {
                x+=5;
                if (D < 0) {
                    D = D + Dy;
                } else {
                    D = D + (Dy - Dx);
                    y++;
                }
                putPixel(x, y);
                x+=5;
                if (D < 0) {
                    D = D + Dy;
                } else {
                    D = D + (Dy - Dx);
                    y++;
                }
                putPixel(x, y);
                x+=5;
                if (D < 0) {
                    D = D + Dy;
                } else {
                    D = D + (Dy - Dx);
                    y++;
                }
                //putPixel(x, y);
                x+=5;
                if (D < 0) {
                    D = D + Dy;
                } else {
                    D = D + (Dy - Dx);
                    y++;
                }
            }
        } else if (x2 < x1) {
            int Dx = x1 - x2;
            int Dy = y1 - y2;
            int x = x2;
            int y = y2;
            putPixel(x, y);
            float D = Dy - (Dx >> 1); // ~ float D = Dy - Dx/2;
            while (x <= x1) {
                x+=3;
                if (D < 0) {
                    D = D + Dy;
                } else {
                    D = D + (Dy - Dx);
                    y+=3;
                }
                putPixel(x, y);
                x+=3;
                if (D < 0) {
                    D = D + Dy;
                } else {
                    D = D + (Dy - Dx);
                    y+=3;
                }
                putPixel(x, y);
                x+=3;
                if (D < 0) {
                    D = D + Dy;
                } else {
                    D = D + (Dy - Dx);
                    y+=3;
                }
                //putPixel(x, y);
                x+=3;
                if (D < 0) {
                    D = D + Dy;
                } else {
                    D = D + (Dy - Dx);
                    y+=3;
                }
            }
        }

        //Nét đứt thẳng đứng
        if (x1 == x2) {
            int Dx = x2 - x1; //200
            int Dy = y2 - y1; //0
            int x = x1;
            int y = y1;

            putPixel(x1, y1);
            float D = Dy - (Dx >> 1); // ~ float D = Dy - Dx/2;
            while (y <= y2) {
                y+=5;
                if (D < 0) {
                    D = D + Dx;
                } else {
                    D = D + (Dx - Dy);
                    x++;
                }
                putPixel(x, y);
                y+=5;
                if (D < 0) {
                    D = D + Dx;
                } else {
                    D = D + (Dx - Dy);
                    x++;
                }
                putPixel(x, y);
                y+=5;
                if (D < 0) {
                    D = D + Dx;
                } else {
                    D = D + (Dx - Dy);
                    x++;
                }
                //putPixel(x, y);
                y+=5;
                if (D < 0) {
                    D = D + Dx;
                } else {
                    D = D + (Dx - Dy);
                    x++;
                }
                //putPixel(x, y);
            }
        } //Nét đứt nằm ngang
        else if (y1 == y2) {
            int Dx = x1 - x2;
            int Dy = y1 - y2;
            int x = x2;
            int y = y2;
            putPixel(x, y);
            float D = Dy - (Dx >> 1); // ~ float D = Dy - Dx/2;
            while (x <= x1) {
                x+=5;
                if (D < 0) {
                    D = D + Dy;
                } else {
                    D = D + (Dy - Dx);
                    y++;
                }
                putPixel(x, y);
                x+=5;
                if (D < 0) {
                    D = D + Dy;
                } else {
                    D = D + (Dy - Dx);
                    y++;
                }
                putPixel(x, y);
                x+=5;
                if (D < 0) {
                    D = D + Dy;
                } else {
                    D = D + (Dy - Dx);
                    y++;
                }
                //putPixel(x, y);
                x+=5;
                if (D < 0) {
                    D = D + Dy;
                } else {
                    D = D + (Dy - Dx);
                    y++;
                }
            }
        }
    }

    
    //Chuyển tọa độ điểm từ 3D thành 2D (Cavalier)
    public float Chuyen3DSang2DChoX(float X, float Y, float Z) {
        float Xp, Yp;
        Xp = (float) (X - (float) Z * sqrt(2) / 2);
        //Yp = (float) (Y - (float) Z * sqrt(2) / 2);

        return Xp;
    }

    public float Chuyen3DSang2DChoY(float X, float Y, float Z) {
        float Xp, Yp;
        //Xp = (float) (X - (float) Z * sqrt(2) / 2);
        Yp = (float) (Y - (float) Z * sqrt(2) / 2);

        return Yp;
    }

    //Hàm vẽ hình hộp chữ nhật
    public void HinhHopChuNhat(int x, int y, int z, int chieuDai, int chieuRong, int chieuCao) {
        //Lay toa do 8 diem can ve
        Point point1 = new Point(0, 0);
        Point point2 = new Point(0, 0);
        Point point3 = new Point(0, 0);
        Point point4 = new Point(0, 0);
        Point point5 = new Point(0, 0);
        Point point6 = new Point(0, 0);
        Point point7 = new Point(0, 0);
        Point point8 = new Point(0, 0);
        //Bảng quy định:
        //////Chiều dài sẽ song song với trục Ox trong hệ tọa độ 3D/////// 
        //////Chiều rộng sẽ song song với trục Oz trong hệ tọa độ 3D///////
        //////Chiều cao sẽ song song với trục Oy trong hệ tọa độ 3D///////
        //4 điểm mặt dưới sẽ có tên A,B,C,D với điểm A là điểm nằm trong và dưới cùng bên trái (Trùng gốc tọa độ)

        //Điểm A
        int xA = x, yA = y, zA = z;
        //Điểm B
        int xB = x + chieuDai, yB = y, zB = z;
        //Điểm C
        int xC = x + chieuDai, yC = y, zC = z + chieuRong;
        //Điểm D
        int xD = x, yD = y, zD = z + chieuRong;
        //4 điểm mặt trên sẽ có tên E,F,G,H
        //Điểm E
        int xE = x, yE = y + chieuCao, zE = z;
        //Điểm F
        int xF = x + chieuDai, yF = y + chieuCao, zF = z;
        //Điểm G
        int xG = x + chieuDai, yG = y + chieuCao, zG = z + chieuRong;
        //Điểm H
        int xH = x, yH = y + chieuCao, zH = z + chieuRong;

        ////////////////Chuyển các điểm từ 3D sang 2D
        point1 = new Point(0, 0);
        point2 = new Point(0, 0);
        point3 = new Point(0, 0);
        point4 = new Point(0, 0);
        point5 = new Point(0, 0);
        point6 = new Point(0, 0);
        point7 = new Point(0, 0);
        point8 = new Point(0, 0);
        //Điểm A
        point1.x = (int) Chuyen3DSang2DChoX(xA, yA, zA);
        point1.y = (int) Chuyen3DSang2DChoY(xA, yA, zA);

        System.out.println("Toa do điểm A la : " + point1.x + "; " + point1.y);
        //Điểm B
        point2.x = (int) Chuyen3DSang2DChoX(xB, yB, zB);
        point2.y = (int) Chuyen3DSang2DChoY(xB, yB, zB);
        System.out.println("Toa do điểm B la : " + point2.x + "; " + point2.y);
        //Điểm C
        point3.x = (int) Chuyen3DSang2DChoX(xC, yC, zC);
        point3.y = (int) Chuyen3DSang2DChoY(xC, yC, zC);
        System.out.println("Toa do điểm C la : " + point3.x + "; " + point3.y);
        //Điểm D
        point4.x = (int) Chuyen3DSang2DChoX(xD, yD, zD);
        point4.y = (int) Chuyen3DSang2DChoY(xD, yD, zD);
        System.out.println("Toa do điểm D la : " + point4.x + "; " + point4.y);
        //Điểm E
        point5.x = (int) Chuyen3DSang2DChoX(xE, yE, zE);
        point5.y = (int) Chuyen3DSang2DChoY(xE, yE, zE);
        System.out.println("Toa do điểm E la : " + point5.x + "; " + point5.y);
        //Điểm F
        point6.x = (int) Chuyen3DSang2DChoX(xF, yF, zF);
        point6.y = (int) Chuyen3DSang2DChoY(xF, yF, zF);
        System.out.println("Toa do điểm F la : " + point6.x + "; " + point6.y);
        //Điểm G
        point7.x = (int) Chuyen3DSang2DChoX(xG, yG, zG);
        point7.y = (int) Chuyen3DSang2DChoY(xG, yG, zG);
        System.out.println("Toa do điểm G la : " + point7.x + "; " + point7.y);
        //Điểm H
        point8.x = (int) Chuyen3DSang2DChoX(xH, yH, zH);
        point8.y = (int) Chuyen3DSang2DChoY(xH, yH, zH);
        System.out.println("Toa do điểm H la : " + point8.x + "; " + point8.y);

        //Thêm vào ArrayList
        //HopChuNhat(p chieuDai, chieuRong, chieuCao);
        //Vẽ nét đứt (AB, AC. AE)
        VeNetDut(point1.x, point1.y, point2.x, point2.y);
        VeNetDut(point1.x, point1.y, point4.x, point4.y);
        VeNetDut(point1.x, point1.y, point5.x, point5.y);
        //Vẽ nét liền(BC, CD, BF, CG, DH, EF, FG, GH, HF)
        VeNetLien(point2.x, point2.y, point3.x, point3.y);
        VeNetLien(point3.x, point3.y, point4.x, point4.y);
        VeNetLien(point2.x, point2.y, point6.x, point6.y);
        VeNetLien(point3.x, point3.y, point7.x, point7.y);
        VeNetLien(point4.x, point4.y, point8.x, point8.y);
        VeNetLien(point5.x, point5.y, point6.x, point6.y);
        VeNetLien(point6.x, point6.y, point7.x, point7.y);
        VeNetLien(point7.x, point7.y, point8.x, point8.y);
        VeNetLien(point8.x, point8.y, point5.x, point5.y);
    }

    /////////////////////////////HÌNH TRỤ///////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public void ve2DiemNuaDuoi(int xCenter, int yCenter, int xO, int yO) {
        putPixel(xO + xCenter, -yO + yCenter); //ox
        putPixel(-xO + xCenter, -yO + yCenter); //oy                  
    }

    public void ve2DiemNuaTren(int xCenter, int yCenter, int xO, int yO) {
        putPixel(xO + xCenter, yO + yCenter);
        putPixel(-xO + xCenter, yO + yCenter); //O                
    }

    public void ElipNuaDuoiNetLienMidPoint(int xCenter, int yCenter, int a, int b) {
        int x, y, fx, fy, a2, b2, p;
        x = 0;
        y = b;
        a2 = a * a;
        b2 = b * b;
        fx = 0;
        fy = 2 * a2 * y;
        ve2DiemNuaDuoi(xCenter, yCenter, x, y);
        p = (int) (b2 - (a2 * b) + (0.25 * a2));//p=b2 - a2*b +a2/4
        while (fx < fy) {
            x++;
            fx += 2 * b2;

            if (p < 0) {
                p += b2 * (2 * x + 3);//p=p + b2*(2x +3)
            } else {
                y--;
                p += b2 * (2 * x + 3) + a2 * (2 - 2 * y);//p=p +b2(2x +3) +a2(2-2y)
                fy -= 2 * a2;
            }
            ve2DiemNuaDuoi(xCenter, yCenter, x, y);

        }
        p = (int) (b2 * (x + 0.5) * (x + 0.5) + a2 * (y - 1) * (y - 1) - a2 * b2);
        //
        while (y > 0) {
            y--;
            fy -= 2 * a2;

            if (p >= 0) {
                p += a2 * (3 - 2 * y); //p=p +a2(3-2y)
            } else {
                x++;
                fx += 2 * b2;
                p += b2 * (2 * x + 2) + a2 * (3 - 2 * y);//p=p+ b2(2x +2) + a2(3-2y)
            }
            ve2DiemNuaDuoi(xCenter, yCenter, x, y);
        }
    }

    public void ElipNuaTrenNetDutMidPoint(int xCenter, int yCenter, int a, int b) {
        int x, y, fx, fy, a2, b2, p;
        x = 0;
        y = b;
        a2 = a * a;
        b2 = b * b;
        fx = 0;
        fy = 2 * a2 * y;
        ve2DiemNuaTren(xCenter, yCenter, x, y);
        p = (int) (b2 - (a2 * b) + (0.25 * a2));//p=b2 - a2*b +a2/4
        while (fx < fy) {
            x++;
            fx += 2 * b2;

            if (p < 0) {
                p += b2 * (2 * x + 3);//p=p + b2*(2x +3)
            } else {
                y--;
                p += b2 * (2 * x + 3) + a2 * (2 - 2 * y);//p=p +b2(2x +3) +a2(2-2y)
                fy -= 2 * a2;
            }
            ve2DiemNuaTren(xCenter, yCenter, x, y);
            x++;
            fx += 2 * b2;

            if (p < 0) {
                p += b2 * (2 * x + 3);//p=p + b2*(2x +3)
            } else {
                y--;
                p += b2 * (2 * x + 3) + a2 * (2 - 2 * y);//p=p +b2(2x +3) +a2(2-2y)
                fy -= 2 * a2;
            }
            ve2DiemNuaTren(xCenter, yCenter, x, y);
            x++;
            fx += 2 * b2;

            if (p < 0) {
                p += b2 * (2 * x + 3);//p=p + b2*(2x +3)
            } else {
                y--;
                p += b2 * (2 * x + 3) + a2 * (2 - 2 * y);//p=p +b2(2x +3) +a2(2-2y)
                fy -= 2 * a2;
            }
            ve2DiemNuaTren(xCenter, yCenter, x, y);
            x++;
            fx += 2 * b2;

            if (p < 0) {
                p += b2 * (2 * x + 3);//p=p + b2*(2x +3)
            } else {
                y--;
                p += b2 * (2 * x + 3) + a2 * (2 - 2 * y);//p=p +b2(2x +3) +a2(2-2y)
                fy -= 2 * a2;
            }
            //ve2DiemNuaTren(xCenter, yCenter, x, y);
            x++;
            fx += 2 * b2;

            if (p < 0) {
                p += b2 * (2 * x + 3);//p=p + b2*(2x +3)
            } else {
                y--;
                p += b2 * (2 * x + 3) + a2 * (2 - 2 * y);//p=p +b2(2x +3) +a2(2-2y)
                fy -= 2 * a2;
            }
            //ve2DiemNuaTren(xCenter, yCenter, x, y);
            x++;
            fx += 2 * b2;

            if (p < 0) {
                p += b2 * (2 * x + 3);//p=p + b2*(2x +3)
            } else {
                y--;
                p += b2 * (2 * x + 3) + a2 * (2 - 2 * y);//p=p +b2(2x +3) +a2(2-2y)
                fy -= 2 * a2;
            }
            x++;
            fx += 2 * b2;

            if (p < 0) {
                p += b2 * (2 * x + 3);//p=p + b2*(2x +3)
            } else {
                y--;
                p += b2 * (2 * x + 3) + a2 * (2 - 2 * y);//p=p +b2(2x +3) +a2(2-2y)
                fy -= 2 * a2;
            }
            x++;
            fx += 2 * b2;

            if (p < 0) {
                p += b2 * (2 * x + 3);//p=p + b2*(2x +3)
            } else {
                y--;
                p += b2 * (2 * x + 3) + a2 * (2 - 2 * y);//p=p +b2(2x +3) +a2(2-2y)
                fy -= 2 * a2;
            }
            x++;
            fx += 2 * b2;

            if (p < 0) {
                p += b2 * (2 * x + 3);//p=p + b2*(2x +3)
            } else {
                y--;
                p += b2 * (2 * x + 3) + a2 * (2 - 2 * y);//p=p +b2(2x +3) +a2(2-2y)
                fy -= 2 * a2;
            }
            //ve2DiemNuaTren(xCenter, yCenter, x, y);
            x++;
            fx += 2 * b2;

            if (p < 0) {
                p += b2 * (2 * x + 3);//p=p + b2*(2x +3)
            } else {
                y--;
                p += b2 * (2 * x + 3) + a2 * (2 - 2 * y);//p=p +b2(2x +3) +a2(2-2y)
                fy -= 2 * a2;
            }
            //ve2DiemNuaTren(xCenter, yCenter, x, y);
        }
        p = (int) (b2 * (x + 0.5) * (x + 0.5) + a2 * (y - 1) * (y - 1) - a2 * b2);
        //
        while (y > 0) {
            y--;
            fy -= 2 * a2;

            if (p >= 0) {
                p += a2 * (3 - 2 * y); //p=p +a2(3-2y)
            } else {
                x++;
                fx += 2 * b2;
                p += b2 * (2 * x + 2) + a2 * (3 - 2 * y);//p=p+ b2(2x +2) + a2(3-2y)
            }
            ve2DiemNuaTren(xCenter, yCenter, x, y);
            y--;
            fy -= 2 * a2;

            if (p >= 0) {
                p += a2 * (3 - 2 * y); //p=p +a2(3-2y)
            } else {
                x++;
                fx += 2 * b2;
                p += b2 * (2 * x + 2) + a2 * (3 - 2 * y);//p=p+ b2(2x +2) + a2(3-2y)
            }
            ve2DiemNuaTren(xCenter, yCenter, x, y);
            y--;
            fy -= 2 * a2;

            if (p >= 0) {
                p += a2 * (3 - 2 * y); //p=p +a2(3-2y)
            } else {
                x++;
                fx += 2 * b2;
                p += b2 * (2 * x + 2) + a2 * (3 - 2 * y);//p=p+ b2(2x +2) + a2(3-2y)
            }
            ve2DiemNuaTren(xCenter, yCenter, x, y);
            y--;
            fy -= 2 * a2;

            if (p >= 0) {
                p += a2 * (3 - 2 * y); //p=p +a2(3-2y)
            } else {
                x++;
                fx += 2 * b2;
                p += b2 * (2 * x + 2) + a2 * (3 - 2 * y);//p=p+ b2(2x +2) + a2(3-2y)
            }
            //ve2DiemNuaTren(xCenter, yCenter, x, y);
            y--;
            fy -= 2 * a2;

            if (p >= 0) {
                p += a2 * (3 - 2 * y); //p=p +a2(3-2y)
            } else {
                x++;
                fx += 2 * b2;
                p += b2 * (2 * x + 2) + a2 * (3 - 2 * y);//p=p+ b2(2x +2) + a2(3-2y)
            }
            //ve2DiemNuaTren(xCenter, yCenter, x, y);
            y--;
            fy -= 2 * a2;

            if (p >= 0) {
                p += a2 * (3 - 2 * y); //p=p +a2(3-2y)
            } else {
                x++;
                fx += 2 * b2;
                p += b2 * (2 * x + 2) + a2 * (3 - 2 * y);//p=p+ b2(2x +2) + a2(3-2y)
            }
            //ve2DiemNuaTren(xCenter, yCenter, x, y);
            y--;
            fy -= 2 * a2;

            if (p >= 0) {
                p += a2 * (3 - 2 * y); //p=p +a2(3-2y)
            } else {
                x++;
                fx += 2 * b2;
                p += b2 * (2 * x + 2) + a2 * (3 - 2 * y);//p=p+ b2(2x +2) + a2(3-2y)
            }
            y--;
            fy -= 2 * a2;

            if (p >= 0) {
                p += a2 * (3 - 2 * y); //p=p +a2(3-2y)
            } else {
                x++;
                fx += 2 * b2;
                p += b2 * (2 * x + 2) + a2 * (3 - 2 * y);//p=p+ b2(2x +2) + a2(3-2y)
            }
            y--;
            fy -= 2 * a2;

            if (p >= 0) {
                p += a2 * (3 - 2 * y); //p=p +a2(3-2y)
            } else {
                x++;
                fx += 2 * b2;
                p += b2 * (2 * x + 2) + a2 * (3 - 2 * y);//p=p+ b2(2x +2) + a2(3-2y)
            }
            y--;
            fy -= 2 * a2;

            if (p >= 0) {
                p += a2 * (3 - 2 * y); //p=p +a2(3-2y)
            } else {
                x++;
                fx += 2 * b2;
                p += b2 * (2 * x + 2) + a2 * (3 - 2 * y);//p=p+ b2(2x +2) + a2(3-2y)
            }
            y--;
            
        }
    }

    public void Ve4diem(int xc, int yc, int x, int y)//ve 4 diem doi xung
    {
        putPixel(xc + x, yc + y);
        putPixel(xc - x, yc + y);
        putPixel(xc - x, yc - y);
        putPixel(xc + x, yc - y);

    }

    public void ElipMidPoint(int xc, int yc, int a, int b) {
        int x, y, fx, fy, a2, b2, p;
        x = 0;
        y = b;
        a2 = a * a;
        b2 = b * b;
        fx = 0;
        fy = 2 * a2 * y;
        Ve4diem(xc, yc, x, y);
        p = (int) (b2 - (a2 * b) + (0.25 * a2));//p=b2 - a2*b +a2/4
        while (fx < fy) {
            x++;
            fx += 2 * b2;

            if (p < 0) {
                p += b2 * (2 * x + 3);//p=p + b2*(2x +3)
            } else {
                y--;
                p += b2 * (2 * x + 3) + a2 * (2 - 2 * y);//p=p +b2(2x +3) +a2(2-2y)
                fy -= 2 * a2;
            }
            Ve4diem(xc, yc, x, y);
        }
        p = (int) (b2 * (x + 0.5) * (x + 0.5) + a2 * (y - 1) * (y - 1) - a2 * b2);
        //
        while (y > 0) {
            y--;
            fy -= 2 * a2;

            if (p >= 0) {
                p += a2 * (3 - 2 * y); //p=p +a2(3-2y)
            } else {
                x++;
                fx += 2 * b2;
                p += b2 * (2 * x + 2) + a2 * (3 - 2 * y);//p=p+ b2(2x +2) + a2(3-2y)
            }
            Ve4diem(xc, yc, x, y);
        }
    }

    //Hàm vẽ hình trụ
    public void HinhTru(int x, int y, int z, int chieuCao, int banKinhDay) {
        //Lấy toa độ các điểm cần vẽ và bán kính hình tròn và elip
        //Chọn điểm gốc là tâm hình trụ nằm ở dưới (Điểm tâm O), suy ra 5 điểm cơ sở còn lại
        //Tâm O
        int xO = x, yO = y, zO = z;
        //Điểm A nằm trong bán kính ở DƯỚI bên TRÁI
        int xA = x - banKinhDay, yA = y, zA = z;
        //Điểm B nằm trong bán kính ở DƯỚI bên PHẢI
        int xB = x + banKinhDay, yB = y, zB = z;
        //Điểm tâm nằm ở trên (Tâm O1)
        int xO1 = x, yO1 = y + chieuCao, zO1 = z;
        //Điểm C nằm trong bán kính ở TRÊN bên TRÁI
        int xC = x - banKinhDay, yC = y + chieuCao, zC = z;
        //Điểm D nằm trong bán kính ở TRÊN bên PHẢI
        int xD = x + banKinhDay, yD = y + chieuCao, zD = z;
        Point point1 = new Point(0, 0);
        Point point2 = new Point(0, 0);
        Point point3 = new Point(0, 0);
        Point point4 = new Point(0, 0);
        Point point5 = new Point(0, 0);
        Point point6 = new Point(0, 0);
        //Chuyển tọa độ từ 3D sang 2D
        point1 = new Point(0, 0);
        point2 = new Point(0, 0);
        point3 = new Point(0, 0);
        point4 = new Point(0, 0);
        point5 = new Point(0, 0);
        point6 = new Point(0, 0);
        //Tâm O
        point5.x = (int) Chuyen3DSang2DChoX(xO, yO, zO);
        point5.y = (int) Chuyen3DSang2DChoY(xO, yO, zO);
        System.out.println("Toa do tâm O la : " + point5.x + "; " + point5.y);
        //Tâm O1
        point6.x = (int) Chuyen3DSang2DChoX(xO1, yO1, zO1);
        point6.y = (int) Chuyen3DSang2DChoY(xO1, yO1, zO1);
        System.out.println("Toa do tâm O' la : " + point6.x + "; " + point6.y);
        //Điểm A
        point1.x = (int) Chuyen3DSang2DChoX(xA, yA, zA);
        point1.y = (int) Chuyen3DSang2DChoY(xA, yA, zA);
        System.out.println("Toa do điểm A la : " + point1.x + "; " + point1.y);
        //Điểm B
        point2.x = (int) Chuyen3DSang2DChoX(xB, yB, zB);
        point2.y = (int) Chuyen3DSang2DChoY(xB, yB, zB);
        System.out.println("Toa do điểm B la : " + point2.x + "; " + point2.y);
        //Điểm C
        point3.x = (int) Chuyen3DSang2DChoX(xC, yC, zC);
        point3.y = (int) Chuyen3DSang2DChoY(xC, yC, zC);
        System.out.println("Toa do điểm C la : " + point3.x + "; " + point3.y);
        //Điểm D
        point4.x = (int) Chuyen3DSang2DChoX(xD, yD, zD);
        point4.y = (int) Chuyen3DSang2DChoY(xD, yD, zD);
        System.out.println("Toa do điểm D la : " + point4.x + "; " + point4.y);

        /*/Ghi ra các điểm cơ sở
         putPixel(point1.x, point1.y);
         putPixel(point2.x, point2.y);
         putPixel(point3.x, point3.y);
         putPixel(point5.x, point5.y);
         putPixel(point4.x, point4.y);
         putPixel(point6.x, point6.y);
         */
        //Vẽ nét đứt bao gồm: đoạn thẳng OO', nửa hình elip ở dưới bên trong 
        VeNetDut(point5.x, point5.y, point6.x, point6.y); //OO'
        VeNetDut(point3.x, point3.y, point4.x, point4.y); //CD
        VeNetDut(point1.x, point1.y, point2.x, point2.y); //CD

        //Vẽ nét liền: đoạn thẳng AC, BD, hình Elip ở trên và nửa hình Elip ở dưới
        VeNetLien(point1.x, point1.y, point3.x, point3.y); // AC
        VeNetLien(point2.x, point2.y, point4.x, point4.y); //BD
        //Elip tâm O
        ElipNuaTrenNetDutMidPoint(point5.x, point5.y, banKinhDay, (int) (banKinhDay * sqrt(2) / 2)); //Eelip ở dưới bên trong
        ElipNuaDuoiNetLienMidPoint(point5.x, point5.y, banKinhDay, (int) (banKinhDay * sqrt(2) / 2));//Elip nửa dưới bên ngoài

        //Elip tâm O1
        System.out.println("Toa do tâm O' la : " + point6.x + "; " + point6.y);
        ElipMidPoint(point6.x, point6.y, banKinhDay, (int) (banKinhDay * sqrt(2) / 2));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DrawPanel = new javax.swing.JPanel();
        ChucNangPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jRBHinhHop = new javax.swing.JRadioButton();
        jRBHinhTru = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtX = new javax.swing.JTextField();
        txtY = new javax.swing.JTextField();
        txtZ = new javax.swing.JTextField();
        txtDai = new javax.swing.JTextField();
        txtRong = new javax.swing.JTextField();
        txtCaoHinhCN = new javax.swing.JTextField();
        txtCaoHinhTru = new javax.swing.JTextField();
        txtBanKinh = new javax.swing.JTextField();
        jBtnHinhHopCN = new javax.swing.JButton();
        jBtnXoa = new javax.swing.JButton();
        jButton2DBack = new javax.swing.JButton();
        jButtonMesh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("3D Draw");

        DrawPanel.setBackground(new java.awt.Color(10, 10, 10));
        DrawPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        DrawPanel.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout DrawPanelLayout = new javax.swing.GroupLayout(DrawPanel);
        DrawPanel.setLayout(DrawPanelLayout);
        DrawPanelLayout.setHorizontalGroup(
            DrawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 674, Short.MAX_VALUE)
        );
        DrawPanelLayout.setVerticalGroup(
            DrawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        ChucNangPanel.setBackground(new java.awt.Color(51, 51, 51));
        ChucNangPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Hình muốn vẽ");

        jRBHinhHop.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRBHinhHop.setForeground(new java.awt.Color(255, 255, 255));
        jRBHinhHop.setText("Hình hộp chữ nhật");
        jRBHinhHop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBHinhHopActionPerformed(evt);
            }
        });
        jRBHinhHop.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jRBHinhHopKeyReleased(evt);
            }
        });

        jRBHinhTru.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRBHinhTru.setForeground(new java.awt.Color(255, 255, 255));
        jRBHinhTru.setText("Hình trụ");
        jRBHinhTru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBHinhTruActionPerformed(evt);
            }
        });
        jRBHinhTru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jRBHinhTruKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tọa độ X:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tọa độ Y:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tọa độ Z:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Hộp chữ nhật");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Dài:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Rộng:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Cao:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Hình trụ");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Chiều cao:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Bán kinh đáy:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Tọa độ gốc");

        txtX.setText("10");
        txtX.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtXFocusLost(evt);
            }
        });

        txtY.setText("10");
        txtY.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtYFocusLost(evt);
            }
        });

        txtZ.setText("10");
        txtZ.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtZFocusLost(evt);
            }
        });

        txtDai.setText("10");
        txtDai.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDaiFocusLost(evt);
            }
        });
        txtDai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDaiActionPerformed(evt);
            }
        });

        txtRong.setText("10");
        txtRong.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRongFocusLost(evt);
            }
        });

        txtCaoHinhCN.setText("10");
        txtCaoHinhCN.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCaoHinhCNFocusLost(evt);
            }
        });

        txtCaoHinhTru.setText("10");
        txtCaoHinhTru.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCaoHinhTruFocusLost(evt);
            }
        });

        txtBanKinh.setText("10");
        txtBanKinh.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBanKinhFocusLost(evt);
            }
        });

        jBtnHinhHopCN.setBackground(new java.awt.Color(0, 0, 0));
        jBtnHinhHopCN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jBtnHinhHopCN.setForeground(new java.awt.Color(204, 255, 255));
        jBtnHinhHopCN.setText("VẼ");
        jBtnHinhHopCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnHinhHopCNActionPerformed(evt);
            }
        });

        jBtnXoa.setBackground(new java.awt.Color(255, 0, 0));
        jBtnXoa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnXoa.setForeground(new java.awt.Color(255, 255, 255));
        jBtnXoa.setText("XÓA");
        jBtnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnXoaActionPerformed(evt);
            }
        });

        jButton2DBack.setBackground(new java.awt.Color(102, 102, 102));
        jButton2DBack.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2DBack.setForeground(new java.awt.Color(255, 255, 255));
        jButton2DBack.setText("2D");
        jButton2DBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2DBackActionPerformed(evt);
            }
        });

        jButtonMesh.setBackground(new java.awt.Color(0, 102, 255));
        jButtonMesh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonMesh.setForeground(new java.awt.Color(255, 255, 255));
        jButtonMesh.setText("Pixel");
        jButtonMesh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMeshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ChucNangPanelLayout = new javax.swing.GroupLayout(ChucNangPanel);
        ChucNangPanel.setLayout(ChucNangPanelLayout);
        ChucNangPanelLayout.setHorizontalGroup(
            ChucNangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChucNangPanelLayout.createSequentialGroup()
                .addGroup(ChucNangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ChucNangPanelLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(ChucNangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(ChucNangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(ChucNangPanelLayout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addGroup(ChucNangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jRBHinhHop)
                                        .addGroup(ChucNangPanelLayout.createSequentialGroup()
                                            .addComponent(jBtnHinhHopCN, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(45, 45, 45)
                                            .addComponent(jBtnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jRBHinhTru)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChucNangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ChucNangPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtZ, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ChucNangPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDai, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ChucNangPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtRong, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ChucNangPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCaoHinhCN, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ChucNangPanelLayout.createSequentialGroup()
                                        .addGroup(ChucNangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(ChucNangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtBanKinh, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCaoHinhTru, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(ChucNangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ChucNangPanelLayout.createSequentialGroup()
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtX, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ChucNangPanelLayout.createSequentialGroup()
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtY, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(ChucNangPanelLayout.createSequentialGroup()
                                    .addGap(69, 69, 69)
                                    .addGroup(ChucNangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton2DBack, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                        .addComponent(jButtonMesh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addComponent(jLabel12)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        ChucNangPanelLayout.setVerticalGroup(
            ChucNangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChucNangPanelLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRBHinhHop)
                .addGap(17, 17, 17)
                .addComponent(jRBHinhTru)
                .addGap(18, 18, 18)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ChucNangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtX, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ChucNangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtY, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ChucNangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtZ, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ChucNangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDai, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ChucNangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRong, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ChucNangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCaoHinhCN, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ChucNangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCaoHinhTru, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ChucNangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBanKinh, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ChucNangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jBtnHinhHopCN)
                    .addComponent(jBtnXoa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2DBack)
                .addGap(16, 16, 16)
                .addComponent(jButtonMesh))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(DrawPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ChucNangPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DrawPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ChucNangPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRBHinhHopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBHinhHopActionPerformed
        // TODO add your handling code here:
        txtCaoHinhTru.setEnabled(false);
        txtBanKinh.setEnabled(false);
        txtDai.setEnabled(true);
        txtRong.setEnabled(true);
        txtCaoHinhCN.setEnabled(true);
        jBtnHinhHopCN.setEnabled(true);
    }//GEN-LAST:event_jRBHinhHopActionPerformed

    private void jBtnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnXoaActionPerformed
        // TODO add your handling code here:
        arrPoint.clear();
        repaint();

    }//GEN-LAST:event_jBtnXoaActionPerformed

    private void jRBHinhHopKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jRBHinhHopKeyReleased

    }//GEN-LAST:event_jRBHinhHopKeyReleased

    private void jRBHinhTruKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jRBHinhTruKeyReleased

    }//GEN-LAST:event_jRBHinhTruKeyReleased

    private void jRBHinhTruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBHinhTruActionPerformed
        // TODO add your handling code here:
        txtDai.setEnabled(false);
        txtRong.setEnabled(false);
        txtCaoHinhCN.setEnabled(false);
//        jBtnHinhHopCN.setEnabled(false);
        txtCaoHinhTru.setEnabled(true);
        txtBanKinh.setEnabled(true);
    }//GEN-LAST:event_jRBHinhTruActionPerformed

    private void jBtnHinhHopCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnHinhHopCNActionPerformed
        // TODO add your handling code here:
        if (jRBHinhHop.isSelected() == true) {
            String X = txtX.getText();
            String Y = txtY.getText();
            String Z = txtZ.getText();
            String d = txtDai.getText();
            String r = txtRong.getText();
            String c = txtCaoHinhCN.getText();

            int fX = Integer.parseInt(X) * 5;
            int fY = Integer.parseInt(Y) * 5;
            int fZ = Integer.parseInt(Z) * 5;
            int fDai = Integer.parseInt(d) * 5;
            int fRong = Integer.parseInt(r) * 5;
            int fCao = Integer.parseInt(c) * 5;

            HinhHopChuNhat(fX, fY, fZ, fDai, fRong, fCao);
        } else {
            String X = txtX.getText();
            String Y = txtY.getText();
            String Z = txtZ.getText();
            String h = txtCaoHinhTru.getText();
            String R = txtBanKinh.getText();

            int fX = Integer.parseInt(X) * 5;
            int fY = Integer.parseInt(Y) * 5;
            int fZ = Integer.parseInt(Z) * 5;
            int fH = Integer.parseInt(h) * 5;
            int fR = Integer.parseInt(R) * 5;

            HinhTru(fX, fY, fZ, fH, fR);
        }


    }//GEN-LAST:event_jBtnHinhHopCNActionPerformed

    private void txtDaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDaiActionPerformed

    private void jButton2DBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2DBackActionPerformed

        new KTDH_Nhom3().setVisible(true);// TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jButton2DBackActionPerformed

    private void txtXFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtXFocusLost
        if (txtX.getText().trim().toUpperCase().equals("")) {
            txtX.setText("0");
        }
        
        
    }//GEN-LAST:event_txtXFocusLost

    private void txtYFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtYFocusLost
        if (txtY.getText().trim().toUpperCase().equals("")) {
            txtY.setText("0");
        }
    }//GEN-LAST:event_txtYFocusLost

    private void txtZFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtZFocusLost
        if (txtZ.getText().trim().toUpperCase().equals("")) {
            txtZ.setText("0");
        }
    }//GEN-LAST:event_txtZFocusLost

    private void txtDaiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDaiFocusLost
        if (txtDai.getText().trim().toUpperCase().equals("")) {
            txtDai.setText("0");
        }
    }//GEN-LAST:event_txtDaiFocusLost

    private void txtRongFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRongFocusLost
        if (txtRong.getText().trim().toUpperCase().equals("")) {
            txtRong.setText("0");
        }
    }//GEN-LAST:event_txtRongFocusLost

    private void txtCaoHinhCNFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCaoHinhCNFocusLost
        if (txtCaoHinhCN.getText().trim().toUpperCase().equals("")) {
            txtCaoHinhCN.setText("0");
        }
    }//GEN-LAST:event_txtCaoHinhCNFocusLost

    private void txtCaoHinhTruFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCaoHinhTruFocusLost
        if (txtCaoHinhTru.getText().trim().toUpperCase().equals("")) {
            txtCaoHinhTru.setText("0");
        }
    }//GEN-LAST:event_txtCaoHinhTruFocusLost

    private void txtBanKinhFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBanKinhFocusLost
        if (txtBanKinh.getText().trim().toUpperCase().equals("")) {
            txtBanKinh.setText("0");
        }
    }//GEN-LAST:event_txtBanKinhFocusLost

    private void jButtonMeshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMeshActionPerformed
        if (pixel == false) {
            pixel = true;
        }
        else pixel = false;
// TODO add your handling code here:
    }//GEN-LAST:event_jButtonMeshActionPerformed

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
            java.util.logging.Logger.getLogger(Frame3D.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame3D.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame3D.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame3D.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame3D().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ChucNangPanel;
    private javax.swing.JPanel DrawPanel;
    private javax.swing.JButton jBtnHinhHopCN;
    private javax.swing.JButton jBtnXoa;
    private javax.swing.JButton jButton2DBack;
    private javax.swing.JButton jButtonMesh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRBHinhHop;
    private javax.swing.JRadioButton jRBHinhTru;
    private javax.swing.JTextField txtBanKinh;
    private javax.swing.JTextField txtCaoHinhCN;
    private javax.swing.JTextField txtCaoHinhTru;
    private javax.swing.JTextField txtDai;
    private javax.swing.JTextField txtRong;
    private javax.swing.JTextField txtX;
    private javax.swing.JTextField txtY;
    private javax.swing.JTextField txtZ;
    // End of variables declaration//GEN-END:variables
}

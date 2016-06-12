/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appicontools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author fsp
 */
public class RePanel extends JPanel {

    DrawSize dSize;
    double scale;

    RePanel(DrawSize ds) {
        this.dSize = ds;
        scale=(double)dSize.dpi/160;
    }
    
    public void setIconSize(IconSize _iconSize) {
        dSize.SetIconSize(_iconSize);
        scale=(double)dSize.dpi/160;
        this.setSize((int)(dSize.iSize.picSize*scale), (int)(dSize.iSize.picSize*scale));
        this.setPreferredSize(new java.awt.Dimension((int)(dSize.iSize.picSize*scale), (int)(dSize.iSize.picSize*scale)));
        repaint();
    }
    
    public IconSize getIconSize(){return dSize.iSize;};

    public String getColor() {
        return dSize.color;
    }

    public void setColor(String c) {
        dSize.color = c;
        repaint();
    }

    public Color getBGColor() {
        return StaticTools.String2Color(dSize.bgcolor);
    }

    public void setBGColor(Color c) {
        dSize.bgcolor = StaticTools.Color2String(c);
        repaint();
    }

    public void setX(int _xx) {
        dSize.MoveX(_xx);
        repaint();
    }

    public void setY(int _yy) {
        dSize.MoveY(_yy);
        repaint();
    }

    public void setSize(int _s) {
        dSize.MoveSize(_s);
        repaint();
    }

    Color getColor2() {
        Color c2 = StaticTools.String2Color(dSize.color);
        return StaticTools.Color2Contrary2(c2);
    }

    @Override
    protected void paintComponent(Graphics g) {//重写paintComponent方法以实现jPanel加背景
        super.paintComponent(g);
        BufferedImage bi = DrawIconHelper.makeBI(dSize);
        g.clearRect(0, 0, getWidth(), getHeight());
        g.setColor(getBGColor());
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(bi, (int)(dSize.iSize.getOuterSpeaceWidth() * scale), (int)(dSize.iSize.getOuterSpeaceWidth() * scale), null);
        g.setColor(getColor2());
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        g.drawRect(
               (int)(dSize.iSize.getOuterSpeaceWidth() * scale),
                (int)(dSize.iSize.getOuterSpeaceWidth() * scale),
                (int)(dSize.iSize.outerSize * scale),
                (int)(dSize.iSize.outerSize * scale)
        );
        g.drawRect(
                (int)(dSize.iSize.getBordOuterSpeaceWidth() * scale),
                (int)(dSize.iSize.getBordOuterSpeaceWidth() * scale), 
                (int)(dSize.iSize.insideSize * scale),
                (int)(dSize.iSize.insideSize * scale)
        );
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appicontools;

import java.awt.Color;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.StringReader;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.w3c.dom.svg.SVGDocument;

/**
 *
 * @author Administrator
 */
public class CellRenderer extends JLabel implements ListCellRenderer {

    OffScreenSVGRenderer rnd = new OffScreenSVGRenderer();
    SAXSVGDocumentFactory df = new SAXSVGDocumentFactory("org.apache.xerces.parsers.SAXParser");

    private String color;
    private Color bgColor;
    private String color2;
    private int size;
    
    public void setColor(String c){
        color=c;
        Color c2 = StaticTools.String2Color(color);
        bgColor=StaticTools.Color2Contrary(c2);
        Color c3 = StaticTools.Color2Contrary2(c2);
        color2=StaticTools.Color2String(c3);
    }
    
    /**/
    CellRenderer(String c, int s) {
        setOpaque(true);
        setColor(c);
        setVerticalTextPosition(JLabel.BOTTOM);
        setHorizontalTextPosition(JLabel.CENTER);
        size=s;
    }

    /*从这里到结束：实作getListCellRendererComponent()方法*/
    @Override
    public Component getListCellRendererComponent(JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {
        String uri = "file:/fake.svg";
        CellInfo ci = (CellInfo) value;
        if(isSelected){
            ci.svgPath = ci.svgPath.replace(color,color2);
        }
        setHorizontalAlignment(JLabel.CENTER);
        this.setBackground(bgColor);
        try {
            SVGDocument document = df.createSVGDocument(uri, new StringReader(ci.svgPath));
            BufferedImage bi = rnd.renderToImage(document, size, size);
            ImageUtils.drawBord(bi, Color.BLACK);
            Icon icon = new ImageIcon(bi);
            this.setIcon(icon);
            this.setText(ci.iconName);
        } catch (Exception e) {            
            this.setIcon(null);
            this.setText(String.valueOf(index));
        }
        return this;
    }
}

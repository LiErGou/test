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

    private int size;

    /**/
    CellRenderer(int s) {
        setOpaque(true);
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
        String svgPath = ci.svgPath;

        setHorizontalAlignment(JLabel.CENTER);
        this.setBackground(Color.WHITE);
        try {
            SVGDocument document = df.createSVGDocument(uri, new StringReader(svgPath));
            BufferedImage bi = rnd.renderToImage(document, size, size);
            bi = ImageUtils.drawBackground(bi, ci.bgColor, isSelected?Color.RED:Color.BLACK);
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

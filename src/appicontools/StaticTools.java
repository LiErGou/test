/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appicontools;

import java.awt.Color;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Administrator
 */
public class StaticTools {

    private static HashMap<String, Integer> dpiMap;

    public static HashMap<String, Integer> getDipMap() {
        if (dpiMap == null) {
            dpiMap = new HashMap<String, Integer>();
            dpiMap.put("mdpi", 160);
            dpiMap.put("hdpi", 240);
            dpiMap.put("xhdpi", 320);
            dpiMap.put("xxhdpi", 480);
            dpiMap.put("xxxhdpi", 640);
        }
        return dpiMap;
    }

    public static int getDpiPx(String dpi, int dp) {
        return 32 * dpiMap.get(dpi) / 160;
    }

    public static Setting getSetting(){
         try {
            DBHelper db = DBHelper.getDB();
            if (db.getInitState()) {
                List<Setting> lts = db.selectByClassName(Setting.class);
                if(lts.size()>0)
                    return lts.get(0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static DefaultComboBoxModel getIconTypes() {
        try {
            DBHelper db = DBHelper.getDB();
            if (db.getInitState()) {
                List<IconType> lts = db.selectByClassName(IconType.class);
                String[] tps = new String[lts.size()];
                for (int i = 0; i < tps.length; i++) {
                    tps[i] = lts.get(i).iconType;
                }
                return new DefaultComboBoxModel(tps);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new DefaultComboBoxModel(new String[0]);
    }


    public static String makeSvgXml(DrawSize d) {
        /*原图宽高1024
        缩放比例=目标图完高/1024
        向下移动像素=896×绽放比例*/
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" standalone=\"no\"?>\n");
        sb.append("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\"\n");
        sb.append("\"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n");
        sb.append("<svg width=\"").append(d.size).append("\" height=\"").append(d.size).append("\" viewBox=\"0 0 ").append(d.size).append(" ").append(d.size).append("\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">\n");
        sb.append("<g class=\"transform-group\">\n");
        sb.append("<g transform=\"translate(").append(d.x).append(", ").append(d.y).append(") scale(1, -1) scale(").append(d.scale).append(", ").append(d.scale).append(")\">");
        //fill="#737383"
        String svgPath = d.oSize.svgPath.replaceAll("fill=\"#[^\"]*\"", "fill=\""+d.color+"\"" );
        sb.append(svgPath);
        sb.append("</g></g></svg>");
        return sb.toString();
    }

    public static Color String2Color(String str) {
        int i = Integer.parseInt(str.substring(1), 16);
        return new Color(i);
    }

    public static String Color2String(Color color) {
        String R = Integer.toHexString(color.getRed());
        R = R.length() < 2 ? ('0' + R) : R;
        String B = Integer.toHexString(color.getBlue());
        B = B.length() < 2 ? ('0' + B) : B;
        String G = Integer.toHexString(color.getGreen());
        G = G.length() < 2 ? ('0' + G) : G;
        return '#' + R + G + B;
    }

    public static Color Color2Contrary(Color color) {
        return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
    }

    public static int CC(int c){
        int cc = 255 - c;
        if(cc>64 && cc<128)
            cc-=64;
        else if(cc>=128 && cc<192)
            cc+=64;
        return cc;
    }
    public static Color Color2Contrary2(Color color) {
        return new Color(CC(color.getRed()), CC(color.getGreen()), CC(color.getBlue()));
    }
}

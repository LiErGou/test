/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appicontools;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fsp
 */
public class IconSize  extends DBObjectBaseClass{

    public String iconType;
    public int picSize;
    public int outerSize;
    public int insideSize;

    private IconSize() {

    }

    public IconSize(String _iconType, int _picSize, int _outerSize, int _insideSize) {
        this.iconType = _iconType;
        this.picSize = _picSize;
        this.outerSize = _outerSize;
        this.insideSize = _insideSize;
    }

    public int getBordWidth() {
        return (outerSize - insideSize) / 2;
    }

    public int getOuterSpeaceWidth() {
        return (picSize - outerSize) / 2;
    }

    public int getBordOuterSpeaceWidth() {
        return (picSize - insideSize) / 2;
    }

    private static HashMap<String, IconSize> iconSizeMap;

    public static IconSize getIconSize(String iconType) {
        if (iconSizeMap == null) {
            iconSizeMap = new HashMap<String, IconSize>();

            try {
                DBHelper db = DBHelper.getDB();
                if (db.getInitState()) {
                    List<IconSize> lts = db.selectByClassName(IconSize.class);
                    for (IconSize i : lts) {
                        iconSizeMap.put(i.iconType, i);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return iconSizeMap.get(iconType);
    }
}

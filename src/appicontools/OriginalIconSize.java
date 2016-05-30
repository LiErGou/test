/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appicontools;

/**
 * 原始图标信息
 * @author fsp
 */
public class OriginalIconSize  extends DBObjectBaseClass{
    public String svgPath;
    public int w;
    public int h;
    public int y;
    
    public OriginalIconSize(){}
    
    public OriginalIconSize(String _svgPath, int _w, int _h, int _y){
        this.svgPath=_svgPath;
        this.w=_w;
        this.h=_h;
        this.y=_y;
    }
}

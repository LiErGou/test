/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appicontools;

/**
 *
 * @author fsp
 */
public class DrawSize  extends DBObjectBaseClass{

    /*
    再加上一个图标名称，数据就保存全面了
    由这一个类就可以生成选定类型的图标了
    */
    
    //初始化时要传入的四个量
    //原始图标尺寸
    public OriginalIconSize oSize;
    //选择的图标类型，以及图标尺寸比例
    public IconSize iSize;
    //图标的分辨率
    public int dpi;
    //图标的名称
    public String iconName;
    //图标的名称
    public int objectId;

    public String color="#000000";
    public String bgcolor="#FFFFFF";
    
    //下面是调整的数据记录
    //最后调整偏移量，左右位置
    private int _xx=0;
    //最后调整偏移量，上下位置
    private int _yy=0;
    //最后调整偏移量，大小调整
    private int _ss=0;
    
    public DrawSize(int objectId, OriginalIconSize oSize, IconSize iSize, int dpi) {
        this.objectId=objectId;
        this.dpi=dpi;
        this.oSize=oSize;
        this.iSize=iSize;
        this.iconName="";
        recalculate();
    }
    
    //下面的数据都可以根据上面的数据计算出来
    public double scale;
    public int size;
    public int x;
    public int y;
    
    public void MoveX(int _xx){
        this._xx=_xx;
        recalculate();
    }
    public void MoveY(int _yy){
        this._yy=_yy;
        recalculate();
    }
    public void MoveSize(int _ss){
        this._ss = _ss;
        recalculate();
    }
    public void SetDPI(int _dpi){
        this.dpi = _dpi;
        this._ss=0;
        this._xx=0;
        this._yy=0;
        recalculate();
    }
    public void SetIconSize(IconSize _dpi){
        this.iSize = _dpi;
        this._ss=0;
        this._xx=0;
        this._yy=0;
        recalculate();
    }
    
    public DrawSize cloneByDpi(){
        return cloneByDpi(dpi);
    }
    public DrawSize cloneByDpi(int newDpi){
        double dpiScale = (double)newDpi/dpi;
        DrawSize  ds = new DrawSize();
        ds.iSize=iSize;
        ds.oSize=oSize;
        ds.dpi = newDpi;
        ds.scale=scale*dpiScale;
        ds.size=(int)(size*dpiScale);
        ds.x=(int)(x*dpiScale);
        ds.y=(int)(y*dpiScale);
        ds._ss=(int)(_ss*dpiScale);
        ds._xx=(int)(_xx*dpiScale);
        ds._yy=(int)(_yy*dpiScale);
        ds.objectId=objectId;
        ds.iconName=iconName;        
        ds.color=color;
        ds.bgcolor=bgcolor;
    
        return ds;
    }
    
    private DrawSize(){
        
    }
    
    private void recalculate(){
        if (oSize.h > oSize.w) {
            scale = ((double) iSize.insideSize) / (oSize.h);
            y = (int) (oSize.y * scale) + iSize.getBordWidth();
            int w = (int) (oSize.h * scale);
            x = (iSize.insideSize-w)/2 + iSize.getBordWidth();
        } else {
            scale = ((double) iSize.insideSize) / (oSize.w);
            int h = (int) (oSize.h * scale);
            y = (int) (oSize.y * scale) + iSize.getBordWidth() + (iSize.insideSize - h) / 2;
            x = iSize.getBordWidth();
        }
        size = iSize.outerSize;

        //上面为原始数据

        double dpiScale = (double)dpi/160;

        scale = ((double) iSize.insideSize*dpiScale+_ss) / (oSize.w);

        size=(int)(size*dpiScale);
        x=(int)(x*dpiScale)+_xx-_ss/2;
        y=(int)(y*dpiScale)+_yy+_ss/2;
        
    }
    
    
}

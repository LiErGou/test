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
public class CellInfo {
    public String svgPath;
    public String iconName;
    
    public CellInfo(String svg, String nm){
        this.svgPath=svg;
        this.iconName=nm;
    }
}

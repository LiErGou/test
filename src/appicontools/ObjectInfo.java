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
public class ObjectInfo  extends DBObjectBaseClass{
    public String objectName;
    public String savePath;
    public int objectId;
    public ObjectInfo(){
        
    }
    public ObjectInfo(String name, String path){
        objectName=name;
        savePath=path;
    }
}

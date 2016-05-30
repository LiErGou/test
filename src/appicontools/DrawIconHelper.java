/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appicontools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.w3c.dom.svg.SVGDocument;

/**
 *
 * @author fsp
 */
public class DrawIconHelper {
    
    private static OffScreenSVGRenderer rnd = new OffScreenSVGRenderer();
    private static SAXSVGDocumentFactory df = new SAXSVGDocumentFactory("org.apache.xerces.parsers.SAXParser");
    public static BufferedImage makeBI(DrawSize dSize) {
        //缩放计算以        iconSize.insideSize
        //图片尺寸计算以        iconSize.outerSize
        
        String xmlContent = StaticTools.makeSvgXml(dSize);
        String uri = "file:/fake.svg";
        try {
            SVGDocument document = df.createSVGDocument(uri, new StringReader(xmlContent));
            BufferedImage bi = rnd.renderToImage(document, dSize.size,  dSize.size);
            return bi;
        } catch (IOException ex) {
            Logger.getLogger(MakeJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static boolean createIcon(String savePath, DrawSize dSize) {
        //mdpi hdpi xhdpi xxhdpi xxxhdpi
        HashMap<String, Integer> dpiMap = StaticTools.getDipMap();
        for (String dpi : dpiMap.keySet()) {
            try {
                
                Path path = Paths.get(savePath,"res", "mipmap-"+dpi);
                File pf = path.toFile();
                if(!pf.exists())
                    pf.mkdirs();
                int ndpi = dpiMap.get(dpi);
                DrawSize nds = dSize.cloneByDpi(ndpi);
                BufferedImage bi = makeBI(nds);

                if(dSize.iSize.picSize>dSize.iSize.outerSize){
                    int nsize = dSize.iSize.picSize*ndpi/160;
                    bi = ImageUtils.drawSpeace(bi, nsize);
                }
                
                path = Paths.get(path.toString(), dSize.iconName+".png");
                //mipmap-hdpi
                ImageIO.write(bi, "png", path.toFile());// 输出到文件流
            } catch (Exception ex) {
                Logger.getLogger(RePanel.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage(), "出错", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
    
}

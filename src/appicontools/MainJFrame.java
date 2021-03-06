/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appicontools;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.svg.SVGDocument;

/**
 *
 * @author Administrator
 */
public class MainJFrame extends javax.swing.JFrame {

    //应用,按扭,状态栏,标签,对话框,列表
    private Pattern p;
    private Pattern pp;

    private List<OriginalIconSize> oSizes;
    private CellRenderer cellRenderer;

    private String searchText = "";
    private int resultCount = 0;
    private int pageSize = 70;
    private int curPage = 0;
    private int pages = 0;

    private IconSize iSize = IconSize.getIconSize("顶部菜单");

    List<ObjectInfo> objectInfoList;

    /**
     * Creates new form MainJFrame
     */
    public MainJFrame() {

        this.oSizes = new LinkedList<>();

        try {
            Image icon = ImageIO.read(this.getClass().getResource("/ic_icon.png"));
            this.setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        p = Pattern.compile("<path .*?</path>");
        pp = Pattern.compile("<h3>共为你找到<em>(\\d*)</em>个结果</h3>");
        initComponents();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        cellRenderer = new CellRenderer(50);
        list.setCellRenderer(cellRenderer);
        list_project.setCellRenderer(cellRenderer);

        list.setBackground(Color.WHITE);
        list_project.setBackground(Color.WHITE);

        cb_object.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_objectActionPerformed(evt);
            }
        });

        initObject();
    }


    private void initObject(){

        try {
            DBHelper db = DBHelper.getDB();
            if (db.getInitState()) {
                objectInfoList = db.selectByClassName(ObjectInfo.class);
                cb_object.removeAllItems();
                for (ObjectInfo i : objectInfoList) {
                    cb_object.addItem(i.objectName);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(objectInfoList.size()==0){
            if(createObject()){
                initObject();
            } else {
                dispose();
            }
        } else {
            Setting st = StaticTools.getSetting();
            cb_object.setSelectedItem(st.selectObject);

            resetObjectList();
        }
    }

    boolean createObject(){
        CreateIconObjedt cioDlg = new CreateIconObjedt();
        cioDlg.setTitle("创建项目");
        cioDlg.setSize(500,200);
        cioDlg.setModal(true);
        cioDlg.setVisible(true);
        return cioDlg.successCreated;
    }

    private void formWindowClosed(java.awt.event.WindowEvent evt) {
        // TODO add your handling code here:
        DBHelper db = DBHelper.getDB();
        try {
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        tb_searchText = new javax.swing.JTextField();
        bt_load = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        bt_load_svg_font = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        bt_creat = new javax.swing.JButton();
        panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        list_project = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cb_object = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tb_savePath = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        bt_createProject = new javax.swing.JButton();
        bt_delProject = new javax.swing.JButton();
        bt_delIcon = new javax.swing.JButton();
        bt_edit = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lb_msg = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lb_object = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("APP图标神器");
        setBounds(new java.awt.Rectangle(0, 0, 500, 500));
        setMinimumSize(new java.awt.Dimension(500, 500));
        setPreferredSize(new java.awt.Dimension(500, 500));

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        tb_searchText.setMinimumSize(new java.awt.Dimension(80, 25));
        tb_searchText.setPreferredSize(new java.awt.Dimension(80, 25));
        tb_searchText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_searchTextActionPerformed(evt);
            }
        });
        jToolBar1.add(tb_searchText);

        bt_load.setText("搜索");
        bt_load.setFocusable(false);
        bt_load.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_load.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_loadActionPerformed(evt);
            }
        });
        jToolBar1.add(bt_load);

        jButton1.setText("上一页");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton2.setText("下一页");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);
        jToolBar1.add(jSeparator1);

        bt_load_svg_font.setText("载入SVG字体");
        bt_load_svg_font.setFocusable(false);
        bt_load_svg_font.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_load_svg_font.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_load_svg_font.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_load_svg_fontActionPerformed(evt);
            }
        });
        jToolBar1.add(bt_load_svg_font);
        jToolBar1.add(jSeparator5);

        bt_creat.setText("添加");
        bt_creat.setFocusable(false);
        bt_creat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_creat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_creat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_creatActionPerformed(evt);
            }
        });
        jToolBar1.add(bt_creat);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        panel.setLayout(new java.awt.BorderLayout());

        list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        list.setToolTipText("图标");
        list.setFixedCellHeight(120);
        list.setFixedCellWidth(120);
        list.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);
        jScrollPane1.setViewportView(list);

        panel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.BorderLayout());

        list_project.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        list_project.setToolTipText("项目图标");
        list_project.setFixedCellHeight(100);
        list_project.setFixedCellWidth(100);
        list_project.setVisibleRowCount(-1);
        list_project.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        jScrollPane2.setViewportView(list_project);

        jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.GridLayout(4, 1));

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel2.setText("项目");
        jPanel4.add(jLabel2);

        cb_object.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_object.setPreferredSize(new java.awt.Dimension(136, 27));
        jPanel4.add(cb_object);

        jPanel2.add(jPanel4);

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel5.setText("路径");
        jPanel6.add(jLabel5);

        tb_savePath.setText("jTextField2");
        tb_savePath.setPreferredSize(new java.awt.Dimension(136, 27));
        jPanel6.add(tb_savePath);

        jPanel2.add(jPanel6);

        bt_createProject.setText("新建项目");
        bt_createProject.setFocusable(false);
        bt_createProject.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_createProject.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_createProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_createProjectActionPerformed(evt);
            }
        });
        jPanel7.add(bt_createProject);

        bt_delProject.setText("删除项目");
        bt_delProject.setFocusable(false);
        bt_delProject.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_delProject.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_delProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_delProjectActionPerformed(evt);
            }
        });
        jPanel7.add(bt_delProject);

        jPanel2.add(jPanel7);

        bt_delIcon.setText("删除图标");
        bt_delIcon.setFocusable(false);
        bt_delIcon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_delIcon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_delIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_delIconActionPerformed(evt);
            }
        });
        jPanel5.add(bt_delIcon);

        bt_edit.setText("编辑图标");
        bt_edit.setFocusable(false);
        bt_edit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_edit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_editActionPerformed(evt);
            }
        });
        jPanel5.add(bt_edit);

        jPanel2.add(jPanel5);



        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        panel.add(jPanel1, java.awt.BorderLayout.LINE_END);

        getContentPane().add(panel, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setText("状态：");
        jPanel3.add(jLabel1);

        lb_msg.setText("                    ");
        jPanel3.add(lb_msg);

        jLabel3.setText("当前项目：");
        jPanel3.add(jLabel3);

        lb_object.setText("                                          ");
        jPanel3.add(lb_object);

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>

    void loadIcon() {

        if (searchText.length() > 0) {
            HttpUrl url;
            if (curPage > 1) {
                url = new HttpUrl.Builder()
                        .scheme("http")
                        .host("www.iconfont.cn")
                        .addPathSegment("search")
                        .addQueryParameter("q", tb_searchText.getText())
                        .addQueryParameter("page", String.valueOf(curPage))
                        .build();
            } else {
                url = new HttpUrl.Builder()
                        .scheme("http")
                        .host("www.iconfont.cn")
                        .addPathSegment("search")
                        .addQueryParameter("q", tb_searchText.getText())
                        .build();
            }

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {

                @Override
                public void onFailure(Call call, IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "搜索出错", JOptionPane.ERROR_MESSAGE);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String html = new String(response.body().bytes(), "utf-8");

                    Matcher mm = pp.matcher(html);
                    if (mm.find()) {
                        resultCount = Integer.parseInt(mm.group(1));
                        if (curPage == 0) {
                            curPage = 1;
                            pages = (int) Math.ceil(((double) resultCount) / pageSize);
                        }
                        lb_msg.setText("共" + String.valueOf(pages) + "页，第" + String.valueOf(curPage) + "页");
                    }

                    Matcher m = p.matcher(html);
                    oSizes.clear();

                    while (m.find()) {
                        oSizes.add(new OriginalIconSize(m.group(), 1024, 1024, 896));
                    }
                    resetList();
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "请输入搜索内容", "关键词为空", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void bt_loadActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        //图片列表
        startNewSearch();
    }

    private void startNewSearch() {
        searchText = tb_searchText.getText().trim();
        curPage = 0;
        resultCount = 0;
        pageSize = 70;
        curPage = 0;
        loadIcon();
    }

    private void bt_creatActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int sind = list.getSelectedIndex();
        if (sind >= 0 && oSizes.size() > sind) {
            String selectObjectName = (String) this.cb_object.getSelectedItem();
            ObjectInfo obj = null;

            for (ObjectInfo oi : this.objectInfoList) {
                if (oi.objectName.equals(selectObjectName)) {
                    obj = oi;
                    break;
                }
            }
            if (obj != null) {
                DrawSize ds = new DrawSize(obj.id, oSizes.get(sind), iSize, 1600);
                openMakeJPanel(ds, obj);
            }
        } else {
            lb_msg.setText("请选择图标");
        }
    }

    void openMakeJPanel(DrawSize ds, ObjectInfo obj){

        JFrame jf = new JFrame();
        jf.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        MakeJPanel mjp = new MakeJPanel(ds, obj, new IconChange() {
            @Override
            public void changed() {
                resetObjectList();
            }
        });
        jf.setContentPane(mjp);
        jf.setSize(750, 580);
        jf.setTitle("生成图标");
        jf.setVisible(true);
    }

    private void tb_searchTextActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        startNewSearch();
    }

    //上一页
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (resultCount > 0) {
            if (curPage > 1) {
                curPage--;
                loadIcon();
            }
        }
    }

    //下一页
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (resultCount > 0) {
            if (curPage < pages) {
                curPage++;
                loadIcon();
            }
        }
    }

    int getFontWidth(SVGDocument doc) {
        NodeList fnl = doc.getElementsByTagName("font");
        if (fnl.getLength() > 0) {
            Node fn = fnl.item(0);
            NamedNodeMap fnnm = fn.getAttributes();
            Node fd = fnnm.getNamedItem("horiz-adv-x");
            if (fd != null) {
                return Integer.parseInt(fd.getNodeValue());
            }
        }
        return -1;
    }

    int getFontEM(SVGDocument doc) {
        NodeList fnl = doc.getElementsByTagName("font-face");
        if (fnl.getLength() > 0) {
            Node fn = fnl.item(0);
            NamedNodeMap fnnm = fn.getAttributes();
            Node fd = fnnm.getNamedItem("units-per-em");
            if (fd != null) {
                return Integer.parseInt(fd.getNodeValue());
            }
        }
        return -1;
    }

    int getFontY(SVGDocument doc) {
        NodeList fnl = doc.getElementsByTagName("font-face");
        if (fnl.getLength() > 0) {
            Node fn = fnl.item(0);
            NamedNodeMap fnnm = fn.getAttributes();
            Node fd = fnnm.getNamedItem("ascent");
            if (fd != null) {
                return Integer.parseInt(fd.getNodeValue());
            }
        }
        return -1;
    }

    class SVGFileFilter extends FileFilter {

        public String getDescription() {
            return "*.svg";
        }

        public boolean accept(File file) {
            String name = file.getName();
            return name.toLowerCase().endsWith(".svg");
        }
    }

    boolean loadSvgFont() {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.setFileFilter(new SVGFileFilter());
        int showOpenDialog = jfc.showOpenDialog(this);
        if (showOpenDialog == JFileChooser.APPROVE_OPTION) {

            OffScreenSVGRenderer rnd = new OffScreenSVGRenderer();
            SAXSVGDocumentFactory df = new SAXSVGDocumentFactory("org.apache.xerces.parsers.SAXParser");
            File f = jfc.getSelectedFile();
            try {
                SVGDocument doc = df.createSVGDocument(f.toURI().toString());

                /*
在font标签内获取默认字符宽度horiz-adv-x
<font id="fontawesomeregular" horiz-adv-x="1536" >
在font-face标签内获取默认高度units-per-em 和下移量 ascent
<font-face units-per-em="1792" ascent="1536" descent="-256" />
在每个符号中查询是否存在告别宽度horiz-adv-x，有则替换
<glyph unicode="&#xf00b;" horiz-adv-x="1792"*/
                OriginalIconSize oSize = new OriginalIconSize("", 0, 0, 0);
                oSize.w = getFontWidth(doc);
                if (oSize.w == -1) {
                    return false;
                }

                oSize.h = getFontEM(doc);
                if (oSize.h == -1) {
                    return false;
                }

                oSize.y = getFontY(doc);
                if (oSize.y == -1) {
                    return false;
                }

                NodeList nl = doc.getElementsByTagName("glyph");
                oSizes.clear();

                resultCount = 0;
                lb_msg.setText("");

                for (int i = 0; i < nl.getLength(); i++) {
                    Node n = nl.item(i);
                    NamedNodeMap nnm = n.getAttributes();

                    OriginalIconSize nSize = new OriginalIconSize("", oSize.w, oSize.h, oSize.y);

                    Node d = nnm.getNamedItem("d");
                    if (d != null) {
                        String nm = d.getNodeName();
                        String nv = d.getNodeValue();

                        Node fd = nnm.getNamedItem("horiz-adv-x");
                        if (fd != null) {
                            nSize.w = Integer.parseInt(fd.getNodeValue());
                        }
                        //                                                                            #737383 作为特别标记被替换
                        nSize.svgPath = "<path d=\"" + nv + "\" fill=\"#737383\"></path>";
                        oSizes.add(nSize);
                    }
                }
                resetList();
            } catch (IOException ex) {

            }
        }
        return true;
    }

    private void bt_load_svg_fontActionPerformed(java.awt.event.ActionEvent evt) {
        if (!loadSvgFont()) {
            lb_msg.setText("载入字体出错");
        }
    }

    boolean checkNewObjectInfo(String nm, String p){

        if(nm.isEmpty()){
            lb_msg.setText("项目名称不能为空");
            return false;
        }
        for(ObjectInfo oi : this.objectInfoList) {
            if (oi.objectName.equals(nm)) {
                lb_msg.setText("项目名称已存在");
                return false;
            }
        }

        if(!p.equals("object")) {
            File f = new File(p);
            if (!f.exists()) {
                lb_msg.setText("项目路径不存在");
                return false;
            }
        }
        return true;
    }
    private void bt_createProjectActionPerformed(java.awt.event.ActionEvent evt) {
        if(createObject()){
            initObject();
        }
    }
    private void cb_objectActionPerformed(java.awt.event.ActionEvent evt) {
        resetObjectList();
    }

    private void bt_delIconActionPerformed(java.awt.event.ActionEvent evt) {
        int sind = this.list_project.getSelectedIndex();
        if (sind >= 0) {
            Object ic = this.list_project.getSelectedValue();
            CellInfo ci = (CellInfo) ic;
            DBHelper db = DBHelper.getDB();
            try {
                if (db.getInitState()) {
                    db.deleteContent(ci.id);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            resetObjectList();
        } else {
            lb_msg.setText("请选择图标");
        }
    }

    private void bt_delProjectActionPerformed(java.awt.event.ActionEvent evt) {
        ObjectInfo obj = getSelectObjectInfo();

        if(obj!=null) {
            try {
                DBHelper db = DBHelper.getDB();
                if (db.getInitState()) {
                    List<DrawSize> dss = db.selectByObjectId(obj.id, DrawSize.class);
                    for (DrawSize d : dss) {
                        db.deleteContent(d.id);
                    }
                    db.deleteContent(obj.id);
                    this.objectInfoList.remove(obj);
                    this.cb_object.removeItem(obj.objectName);

                    if(this.objectInfoList.size()>0) {
                        this.cb_object.setSelectedItem(this.objectInfoList.get(this.objectInfoList.size()-1).objectName);
                    }else {
                        if(createObject()){
                            initObject();
                        } else {
                            dispose();
                        }
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void bt_editActionPerformed(java.awt.event.ActionEvent evt) {
        int sind = this.list_project.getSelectedIndex();
        if (sind >= 0) {
            Object ic = this.list_project.getSelectedValue();
            CellInfo ci = (CellInfo) ic;
            DBHelper db = DBHelper.getDB();
            try {
                if (db.getInitState()) {
                    List<DrawSize> dss = db.selectByID(ci.id, DrawSize.class);
                    if(dss.size()>0){
                        openMakeJPanel(dss.get(0), getSelectObjectInfo());
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            lb_msg.setText("请选择图标");
        }
    }


    ObjectInfo getSelectObjectInfo(){
        String selectObjectName = (String) this.cb_object.getSelectedItem();
        ObjectInfo obj = null;

        for (ObjectInfo oi : this.objectInfoList) {
            if (oi.objectName.equals(selectObjectName)) {
                obj = oi;
                break;
            }
        }
        return obj;
    }

    private void resetObjectList() {
        ObjectInfo obj = getSelectObjectInfo();
        if (obj != null) {

            tb_savePath.setText(obj.savePath);

            DefaultListModel dlm = new DefaultListModel();
            try {
                DBHelper db = DBHelper.getDB();
                if (db.getInitState()) {
                    List<DrawSize> dss = db.selectByObjectId(obj.id, DrawSize.class);
                    for (DrawSize d : dss) {
                        String svg = StaticTools.makeSvgXml(d);
                        dlm.addElement(new CellInfo(d.id, svg, d.iconName,  StaticTools.String2Color(d.bgcolor)));
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            list_project.setModel(dlm);
        }
    }

    private void resetList() {
        if (oSizes.size() > 0) {
            DefaultListModel dlm = new DefaultListModel();

            for (int i = 0; i < oSizes.size(); i++) {
                DrawSize ds = new DrawSize(0, oSizes.get(i), iSize, 160);
                String svg = StaticTools.makeSvgXml(ds);

                dlm.addElement(new CellInfo(0, svg, String.valueOf(i), Color.WHITE));
            }

            //list1.setListData(paths.toArray());
            list.setModel(dlm);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainJFrame mf = new MainJFrame();
                mf.setSize(1024, 700);
                mf.setExtendedState(MAXIMIZED_BOTH);
                mf.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify
    private javax.swing.JButton bt_creat;
    private javax.swing.JButton bt_createProject;
    private javax.swing.JButton bt_delProject;
    private javax.swing.JButton bt_delIcon;
    private javax.swing.JButton bt_edit;
    private javax.swing.JButton bt_load;
    private javax.swing.JButton bt_load_svg_font;
    private javax.swing.JComboBox<String> cb_object;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lb_msg;
    private javax.swing.JLabel lb_object;
    private javax.swing.JList<String> list;
    private javax.swing.JList<String> list_project;
    private javax.swing.JPanel panel;
    private javax.swing.JLabel tb_savePath;
    private javax.swing.JTextField tb_searchText;
    // End of variables declaration
}

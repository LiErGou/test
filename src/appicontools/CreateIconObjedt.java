package appicontools;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateIconObjedt extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField tb_objectName;
    private JTextField tb_savePath;
    private JButton bt_browserPath;
    private JLabel lb_msg;

    public boolean successCreated = false;
    List<ObjectInfo> objectInfoList;

    public CreateIconObjedt() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        bt_browserPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File basePath = jfc.getSelectedFile();
                    tb_savePath.setText(basePath.getAbsolutePath());
                }
            }
        });
    }

    private void onOK() {
        //检测是否合法
        String nm = tb_objectName.getText().trim();
        String p = tb_savePath.getText().trim();

        //保存更新DBHelper db = DBHelper.getDB();
        DBHelper db = DBHelper.getDB();
        if (db.getInitState()) {
            try {
                objectInfoList = db.selectByClassName(ObjectInfo.class);
                if (checkNewObjectInfo(nm, p)) {
                    db.insertContent(0, new ObjectInfo(nm, p));
                    successCreated = true;
                    Setting st = StaticTools.getSetting();
                    st.selectObject = nm;
                    db.updateContent(st.id, st);
                    dispose();
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }


    boolean checkNewObjectInfo(String nm, String p){

        if(nm.isEmpty()){
            lb_msg.setText("项目名称不能为空");
            return false;
        }


        for(ObjectInfo oi : objectInfoList) {
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
}

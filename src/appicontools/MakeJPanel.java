/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appicontools;

import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author fsp 图标大小计算，图标大小32dp，内图24dp
 */
public class MakeJPanel extends javax.swing.JPanel {
    DrawSize ds;
    ObjectInfo objInfo;
    IconChange iChange;
    /**
     * Creates new form MakeJPanel
     */
    MakeJPanel(DrawSize ds, ObjectInfo objInfo, IconChange iChange) {
        this.iChange=iChange;
        this.ds = ds;
        this.objInfo = objInfo;
        initComponents();        
        cb_iconType.setSelectedItem(ds.iSize.iconType);
        iconPanel = new RePanel(ds);
        jPanel1.add(iconPanel, java.awt.BorderLayout.CENTER);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        sld_up_down = new javax.swing.JSlider();
        spin_up_down = new javax.swing.JSpinner();
        sld_left_right = new javax.swing.JSlider();
        spin_left_right = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        sld_enlarge_narrow = new javax.swing.JSlider();
        spin_enlarge_narrow = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tb_iconName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cb_iconType = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        lb_msg = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        bt_color = new javax.swing.JButton();
        bt_bgcolor = new javax.swing.JButton();
        bt_creat = new javax.swing.JButton();

        jCheckBox1.setText("jCheckBox1");

        setToolTipText("");
        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());
        add(jPanel1, java.awt.BorderLayout.CENTER);

        jLabel1.setText("上下位置：");

        sld_up_down.setMinimum(-100);
        sld_up_down.setValue(0);
        sld_up_down.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sld_up_downStateChanged(evt);
            }
        });

        spin_up_down.setValue(0);
        spin_up_down.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spin_up_downStateChanged(evt);
            }
        });

        sld_left_right.setMinimum(-100);
        sld_left_right.setValue(0);
        sld_left_right.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sld_left_rightStateChanged(evt);
            }
        });

        spin_left_right.setValue(0);
        spin_left_right.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spin_left_rightStateChanged(evt);
            }
        });

        jLabel2.setText("左右位置：");

        sld_enlarge_narrow.setMinimum(-100);
        sld_enlarge_narrow.setValue(0);
        sld_enlarge_narrow.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sld_enlarge_narrowStateChanged(evt);
            }
        });

        spin_enlarge_narrow.setValue(0);
        spin_enlarge_narrow.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spin_enlarge_narrowStateChanged(evt);
            }
        });

        jLabel3.setText("放大缩小：");

        jLabel4.setText("图标名称：");

        jLabel5.setText("图标类型：");

        cb_iconType.setModel(StaticTools.getIconTypes());
        cb_iconType.setMinimumSize(new java.awt.Dimension(40, 21));
        cb_iconType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_iconTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(spin_up_down, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(sld_up_down, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(spin_left_right, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(sld_left_right, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(spin_enlarge_narrow, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(sld_enlarge_narrow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(tb_iconName, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(cb_iconType, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spin_up_down, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sld_up_down, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spin_left_right, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sld_left_right, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spin_enlarge_narrow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sld_enlarge_narrow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tb_iconName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_iconType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(133, Short.MAX_VALUE))
        );

        add(jPanel3, java.awt.BorderLayout.LINE_END);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lb_msg.setText("lb_msg");
        jPanel2.add(lb_msg);

        add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jToolBar1.setRollover(true);

        bt_color.setText("设置颜色");
        bt_color.setFocusable(false);
        bt_color.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_color.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_color.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_colorActionPerformed(evt);
            }
        });
        jToolBar1.add(bt_color);

        bt_bgcolor.setText("设置背景色");
        bt_bgcolor.setFocusable(false);
        bt_bgcolor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_bgcolor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_bgcolor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_bgcolorActionPerformed(evt);
            }
        });
        jToolBar1.add(bt_bgcolor);

        bt_creat.setText("保存并生成");
        bt_creat.setFocusable(false);
        bt_creat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_creat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_creat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_creatActionPerformed(evt);
            }
        });
        jToolBar1.add(bt_creat);

        add(jToolBar1, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void bt_creatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_creatActionPerformed
        // TODO add your handling code here:
        sld_up_down.getValue();
        String iconName = this.tb_iconName.getText().trim();
        
        if (iconName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "图标名为空！", "出错", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(!Pattern.matches("^[a-z][a-z0-9_]*", iconName)){
            JOptionPane.showMessageDialog(null, "图标名必须为以字母开头，由字母、数字、下划线组成", "出错", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        ds.iconName=iconName;
        
        if (DrawIconHelper.createIcon(objInfo.savePath, ds)) {
            DBHelper db = DBHelper.getDB();
            try {
                if(ds.id>0){
                    db.updateContent(ds.id, ds);
                }else{
                    db.insertContent(objInfo.id, ds);
                }
                iChange.changed();
            } catch (SQLException ex) {
                Logger.getLogger(MakeJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            lb_msg.setText("生成完毕");
        }
    }//GEN-LAST:event_bt_creatActionPerformed

    private void bt_bgcolorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_bgcolorActionPerformed
        
        Color c = JColorChooser.showDialog(this, "选择背景颜色", iconPanel.getBGColor());
        if (!c.equals(iconPanel.getBGColor())) {
            iconPanel.setBGColor(c);
        }
    }//GEN-LAST:event_bt_bgcolorActionPerformed

    private void bt_colorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_colorActionPerformed
        // TODO add your handling code here:
        String color = iconPanel.getColor();
        Color c = JColorChooser.showDialog(this, "选择图标颜色", StaticTools.String2Color(color));
        String cc = StaticTools.Color2String(c);
        if (!cc.equals(color)) {
            iconPanel.setColor(cc);
        }
    }//GEN-LAST:event_bt_colorActionPerformed

    private void spin_enlarge_narrowStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spin_enlarge_narrowStateChanged
        // TODO add your handling code here:
        sld_enlarge_narrow.setValue((int) spin_enlarge_narrow.getValue());
        iconPanel.setSize((int) spin_enlarge_narrow.getValue());
    }//GEN-LAST:event_spin_enlarge_narrowStateChanged

    private void sld_enlarge_narrowStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sld_enlarge_narrowStateChanged
        // TODO add your handling code here:
        spin_enlarge_narrow.setValue(sld_enlarge_narrow.getValue());
    }//GEN-LAST:event_sld_enlarge_narrowStateChanged

    private void spin_left_rightStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spin_left_rightStateChanged
        // TODO add your handling code here:
        sld_left_right.setValue((int) spin_left_right.getValue());
        iconPanel.setX((int) spin_left_right.getValue());
    }//GEN-LAST:event_spin_left_rightStateChanged

    private void sld_left_rightStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sld_left_rightStateChanged
        // TODO add your handling code here:
        spin_left_right.setValue(sld_left_right.getValue());
    }//GEN-LAST:event_sld_left_rightStateChanged

    private void spin_up_downStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spin_up_downStateChanged
        // TODO add your handling code here:
        sld_up_down.setValue((int) spin_up_down.getValue());
        iconPanel.setY((int) spin_up_down.getValue());
    }//GEN-LAST:event_spin_up_downStateChanged

    private void sld_up_downStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sld_up_downStateChanged
        // TODO add your handling code here:
        spin_up_down.setValue(sld_up_down.getValue());
        //lb_msg.setText(String.valueOf(sld_up_down.getValue()));
    }//GEN-LAST:event_sld_up_downStateChanged

    private void cb_iconTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_iconTypeActionPerformed
        // TODO add your handling code here:
        String str_iconType = (String) cb_iconType.getSelectedItem();
        if (iconPanel != null) {
            iconPanel.setIconSize(IconSize.getIconSize(str_iconType));
            this.sld_enlarge_narrow.setValue(0);
            this.sld_left_right.setValue(0);
            this.sld_up_down.setValue(0);
            this.spin_enlarge_narrow.setValue(0);
            this.spin_left_right.setValue(0);
            this.spin_up_down.setValue(0);
        }
    }//GEN-LAST:event_cb_iconTypeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_bgcolor;
    private javax.swing.JButton bt_color;
    private javax.swing.JButton bt_creat;
    private javax.swing.JComboBox<String> cb_iconType;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lb_msg;
    private javax.swing.JSlider sld_enlarge_narrow;
    private javax.swing.JSlider sld_left_right;
    private javax.swing.JSlider sld_up_down;
    private javax.swing.JSpinner spin_enlarge_narrow;
    private javax.swing.JSpinner spin_left_right;
    private javax.swing.JSpinner spin_up_down;
    private javax.swing.JTextField tb_iconName;
    // End of variables declaration//GEN-END:variables
    
    private RePanel iconPanel;
    
}

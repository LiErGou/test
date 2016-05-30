/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appicontools;

import com.alibaba.fastjson.JSON;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fsp
 */
public class DBHelper {

    final String databaseName = "objectdb";
    final String tableName = "TB";

    PreparedStatement insertPre;
    PreparedStatement updatePre;
    PreparedStatement deletePre;
    PreparedStatement selectByClassNamePre;
    PreparedStatement selectByIDPre;
    PreparedStatement selectByObjectNamePre;

    Connection con;

    Derby derby;

    private boolean initOK;

    private static DBHelper db;
    public static DBHelper getDB(){
        if(db==null){
            try {
                db = new DBHelper();
            } catch (SQLException ex) {
                Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return db;
    }
    
    public boolean getInitState() {
        return initOK;
    }

    private DBHelper() throws SQLException {
        initOK = init();
    }

    public void close() throws SQLException {
        con.close();
        initOK=false;
    }

    private boolean init() throws SQLException {

        derby = new Derby();
        derby.loadDriver();

        con = derby.getConnection(databaseName, "", "");
        if (con == null) {
            con = derby.createDatabaseAndGetConnection(databaseName, "", "");
        }

        if (con == null) {
            return false;
        }

        con.setAutoCommit(false);

        Statement s = con.createStatement();
        //判断表是否存在,                                          表名要大写
        //select count(*) as c from SYS.SYSTABLES where TABLENAME='TABLE1'
        ResultSet rs = s.executeQuery("select count(*) as c from SYS.SYSTABLES where TABLENAME='" + tableName + "'");
        rs.next();
        int c = rs.getInt("c");
        if (c == 0) {
            createTable(s);
            con.commit();
            insertPre = con.prepareStatement("insert into " + tableName + "(className,objectId,content) values(?,?,?)");
            
            insertContent(0, new IconType("TAB标签"));
            insertContent(0, new IconType("应用"));
            insertContent(0, new IconType("底部菜单"));
            insertContent(0, new IconType("TAB标签"));
            insertContent(0, new IconType("顶部菜单"));
            insertContent(0, new IconType("列表项"));
            insertContent(0, new IconType("对话框顶部"));
            insertContent(0, new IconType("状态栏"));
            insertContent(0, new IconType("小图标"));
            
            insertContent(0, new IconSize("TAB标签", 12, 12, 12));
            insertContent(0, new IconSize("应用", 48, 48, 48));
            insertContent(0, new IconSize("底部菜单", 48, 32, 30));
            insertContent(0, new IconSize("TAB标签", 32, 32, 28));
            insertContent(0, new IconSize("顶部菜单", 32, 32, 24));
            insertContent(0, new IconSize("列表项", 32, 32, 24));
            insertContent(0, new IconSize("对话框顶部", 32, 32, 24));
            insertContent(0, new IconSize("状态栏", 24, 24, 18));
            insertContent(0, new IconSize("小图标", 16, 16, 12));
            
            insertContent(0, new ObjectInfo("默认项目","object"));
            
            Setting st = new Setting();
            st.selectObject="默认项目";
            insertContent(0, st);
            
            con.commit();
        }
        s.close();

        
        con.setAutoCommit(true);
        
        insertPre = con.prepareStatement("insert into " + tableName + "(className,objectId,content) values(?,?,?)");
        updatePre = con.prepareStatement("update " + tableName + " set content=? where id=?");
        deletePre = con.prepareStatement("delete from " + tableName + " where id=?");
        selectByClassNamePre = con.prepareStatement("select * from " + tableName + " WHERE className=?");
        selectByIDPre = con.prepareStatement("select * from " + tableName + " WHERE id=?");
        selectByObjectNamePre = con.prepareStatement("select * from " + tableName + " WHERE objectId=?");

        return true;
    }

    private void createTable(Statement s) throws SQLException {
        s.execute("create table " + tableName + "("
                + "id INTEGER NOT NULL generated always as identity,"
                + "className varchar(100),"
                + "objectId INTEGER,"
                + "content varchar(10240))"
        );
    }

    public <T extends DBObjectBaseClass> List<T> selectByClassName(Class<T> clazz) throws SQLException {
        List<T> data = new LinkedList<>();
        selectByClassNamePre.setString(1, clazz.toString());
        ResultSet rs = selectByClassNamePre.executeQuery();
        while (rs.next()) {
            String c = rs.getString("content");
            T o = JSON.parseObject(c, clazz);
            o.id = rs.getInt("id");
            data.add(o);
        }
        return data;
    }

    public <T extends DBObjectBaseClass> List<T> selectByObjectId(int objectId, Class<T> clazz) throws SQLException {
        List<T> data = new LinkedList<>();
        selectByObjectNamePre.setInt(1, objectId);
        ResultSet rs = selectByObjectNamePre.executeQuery();
        while (rs.next()) {
            String c = rs.getString("content");
            T o = JSON.parseObject(c, clazz);
            o.id = rs.getInt("id");
            data.add(o);
        }
        return data;
    }

    public <T extends DBObjectBaseClass> List<T> selectByID(int id, Class<T> clazz) throws SQLException {
        List<T> data = new LinkedList<>();
        selectByIDPre.setInt(1, id);
        ResultSet rs = selectByIDPre.executeQuery();
        while (rs.next()) {
            String c = rs.getString("content");
            T o = JSON.parseObject(c, clazz);
            o.id = rs.getInt("id");
            data.add(o);
        }
        return data;
    }

    public void deleteContent(int id) throws SQLException {
        deletePre.setInt(1, id);
        deletePre.executeUpdate();
    }

    public void updateContent(int id, Object obj) throws SQLException {
        updatePre.setString(1, JSON.toJSONString(obj));
        updatePre.setInt(2, id);
        updatePre.executeUpdate();
    }

    public void insertContent(int objectId, Object obj) throws SQLException {
        String className = obj.getClass().toString();
        insertPre.setString(1, className);
        insertPre.setInt(2, objectId);
        String jsonString = JSON.toJSONString(obj);
        insertPre.setString(3, jsonString);
        insertPre.executeUpdate();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appicontools;

import java.util.prefs.Preferences;

/**
 *
 * @author fsp
 */
public class TestPreferences {
        public static void main(String[] args) {
	// write your code here
        TestPreferences m = new TestPreferences();
        String sp = m.ReadPreferences();

               
        m.WritePreferences();
        m.ReadPreferences();
    }
    public void test() {
	// write your code here
        TestPreferences m = new TestPreferences();
        String sp = m.ReadPreferences();

        m.WritePreferences();
        m.ReadPreferences();
    }

    private void WritePreferences(){
        Preferences prefs = Preferences.userNodeForPackage(this.getClass());
        prefs.put("test", "他开股");
    }
    private String ReadPreferences(){
        Preferences prefs = Preferences.userNodeForPackage(this.getClass());
        String sp = prefs.get("test", "空");
        System.out.println(sp);
        return sp;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gervai;

import javax.swing.*;

/**
 *
 * @author Sam
 */
public class SurveyGraph {
    
    public static void main() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SurveyGraph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        JFrame f = new JFrame();
        f.setTitle(ViewUser.username+"'s stats");
        //System.out.println("3" + ViewUser.username);
        f.add(new SurveyPanel());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(500, 500);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}

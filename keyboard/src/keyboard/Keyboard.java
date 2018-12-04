package keyboard;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.MidiUnavailableException;

public class Keyboard {

    public static void main(String[] args) {
         /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KeyboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        KeyboardView view = new KeyboardView();
        KeyboardModel model = null;
        
        try 
        {
            model = new KeyboardModel();
        } 
        catch (MidiUnavailableException ex) 
        {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getStackTrace(), "Unable to open up MIDI: " + ex, 0);
            Logger.getLogger(Keyboard.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
        KeyboardController controller = new KeyboardController(model, view);
        view.addMouseListener(controller);
        view.addActionListener(controller);
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable(){ 
            public void run() { view.setVisible(true); } 
        });//end of EventQueue.invokeLater()
        
    }//end of main()
    
}

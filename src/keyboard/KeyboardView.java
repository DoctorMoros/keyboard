/*
 * JFrame for main keyboard component
 * 
 */
package keyboard;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.JToggleButton;

public class KeyboardView extends javax.swing.JFrame {
    /**
     * Creates new form keyboard
     */
    public KeyboardView() {
        initComponents();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        c = new java.awt.Canvas();
        d = new java.awt.Canvas();
        e = new java.awt.Canvas();
        f = new java.awt.Canvas();
        g = new java.awt.Canvas();
        a = new java.awt.Canvas();
        b = new java.awt.Canvas();
        dSharp = new java.awt.Canvas();
        cSharp = new java.awt.Canvas();
        gSharp = new java.awt.Canvas();
        fSharp = new java.awt.Canvas();
        aSharp = new java.awt.Canvas();
        imgTreble = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        recordButton = new javax.swing.JToggleButton();
        playButton = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        c.setBackground(new java.awt.Color(255, 255, 255));

        d.setBackground(new java.awt.Color(255, 255, 255));

        e.setBackground(new java.awt.Color(255, 255, 255));

        f.setBackground(new java.awt.Color(255, 255, 255));

        g.setBackground(new java.awt.Color(255, 255, 255));

        a.setBackground(new java.awt.Color(255, 255, 255));

        b.setBackground(new java.awt.Color(255, 255, 255));

        dSharp.setBackground(new java.awt.Color(0, 0, 0));
        dSharp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        cSharp.setBackground(new java.awt.Color(0, 0, 0));
        cSharp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        gSharp.setBackground(new java.awt.Color(0, 0, 0));

        fSharp.setBackground(new java.awt.Color(0, 0, 0));

        aSharp.setBackground(new java.awt.Color(0, 0, 0));

        imgTreble.setIcon(new javax.swing.ImageIcon(getClass().getResource("/keyboard/treble.jpg"))); // NOI18N
        imgTreble.setToolTipText("image file: treble.jpg");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jLabel3.setText("Pianoforte");

        recordButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/keyboard/recordbuttonRS1.png"))); // NOI18N
        recordButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/keyboard/recordbuttonRS.png"))); // NOI18N
        recordButton.setRolloverEnabled(false);
        recordButton.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/keyboard/recordbuttonRS.png"))); // NOI18N

        playButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/keyboard/playbuttonRS1.png"))); // NOI18N
        playButton.setFocusPainted(false);
        playButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/keyboard/playbuttonRS2.png"))); // NOI18N
        playButton.setRolloverEnabled(false);
        playButton.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/keyboard/playbuttonRS2.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imgTreble)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(87, 87, 87)
                            .addComponent(cSharp, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(39, 39, 39)
                            .addComponent(dSharp, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(147, 147, 147)
                            .addComponent(fSharp, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(37, 37, 37)
                            .addComponent(gSharp, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(45, 45, 45)
                            .addComponent(aSharp, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(65, 65, 65))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(recordButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(playButton))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(c, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(d, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(e, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(f, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(g, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imgTreble)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dSharp, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cSharp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(gSharp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fSharp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(aSharp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(g, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(f, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(e, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(d, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(c, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(recordButton)
                    .addComponent(playButton))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Canvas a;
    private java.awt.Canvas aSharp;
    private java.awt.Canvas b;
    private java.awt.Canvas c;
    private java.awt.Canvas cSharp;
    private java.awt.Canvas d;
    private java.awt.Canvas dSharp;
    private java.awt.Canvas e;
    private java.awt.Canvas f;
    private java.awt.Canvas fSharp;
    private java.awt.Canvas g;
    private java.awt.Canvas gSharp;
    private javax.swing.JLabel imgTreble;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JToggleButton playButton;
    private javax.swing.JToggleButton recordButton;
    // End of variables declaration//GEN-END:variables
    
    @Override
    public void addMouseListener(MouseListener listen){
        c.addMouseListener(listen);
        cSharp.addMouseListener(listen);
        d.addMouseListener(listen);
        dSharp.addMouseListener(listen);
        e.addMouseListener(listen);
        f.addMouseListener(listen);
        fSharp.addMouseListener(listen);
        g.addMouseListener(listen);
        gSharp.addMouseListener(listen);
        a.addMouseListener(listen);
        aSharp.addMouseListener(listen);
        b.addMouseListener(listen);
    }
    
    public void addActionListener(ActionListener listen){
        recordButton.addActionListener(listen);
        playButton.addActionListener(listen);
    }
       
    public boolean isPlay(Object source){
        return source == playButton;
    }
    
    public boolean isRecord(Object source){
        return source == recordButton;
    }
    
    public boolean isRecordEnabled(){
        return recordButton.isSelected();
    }
    
    public boolean isPlayEnabled(){
        return playButton.isSelected();
    }
    
    //set key color when pressed
    public void setKeyColor(KeyboardModel.Note note, Color color){
        Canvas canvas = null;
        switch(note){
            case C:
                canvas = c;
                break;
            case Csharp:
                canvas = cSharp;
                break;
            case D:
                canvas = d;
                break;
            case Dsharp:
                canvas = dSharp;
                break;
            case E:
                canvas = e;
                break;
            case F:
                canvas = f;
                break;
            case Fsharp:
                canvas = fSharp;
                break;
            case G:
                canvas = g;
                break;
            case Gsharp:
                canvas = gSharp;
                break;
            case A:
                canvas = a;
                break;
            case Asharp:
                canvas = aSharp;
                break;
            case B:
                canvas = b;
                break;
        } 
        canvas.setBackground(color);
    }
    
    public KeyboardModel.Note getNote(Object source){
        if (source == a) {
            return KeyboardModel.Note.A;
        }
        if (source == b) {
            return KeyboardModel.Note.B;
        }
        if (source == c) {
            return KeyboardModel.Note.C;
        }
        if (source == d) {
            return KeyboardModel.Note.D;
        }
        if (source == e) {
            return KeyboardModel.Note.E;
        }
        if (source == f) {
            return KeyboardModel.Note.F;
        }
        if (source == g) {
            return KeyboardModel.Note.G;
        }
        if (source == aSharp) {
            return KeyboardModel.Note.Asharp;
        }
        if (source == cSharp) {
            return KeyboardModel.Note.Csharp;
        }
        if (source == dSharp) {
            return KeyboardModel.Note.Dsharp;
        }
        if (source == fSharp) {
            return KeyboardModel.Note.Fsharp;
        }
        if (source == gSharp) {
            return KeyboardModel.Note.Gsharp;
        }
        return null;
    }
    private boolean getNoteTest(){
        boolean testPassed;
        testPassed = getNote(c) == KeyboardModel.Note.C;
        testPassed &= getNote(cSharp) == KeyboardModel.Note.Csharp;
        testPassed &= getNote(d) == KeyboardModel.Note.D;
        testPassed &= getNote(dSharp) == KeyboardModel.Note.Dsharp;
        testPassed &= getNote(e) == KeyboardModel.Note.E;
        testPassed &= getNote(f) == KeyboardModel.Note.F;
        testPassed &= getNote(fSharp) == KeyboardModel.Note.Fsharp;
        testPassed &= getNote(g) == KeyboardModel.Note.G;
        testPassed &= getNote(gSharp) == KeyboardModel.Note.Gsharp;
        testPassed &= getNote(a) == KeyboardModel.Note.A;
        testPassed &= getNote(aSharp) == KeyboardModel.Note.Asharp;
        testPassed &= getNote(b) == KeyboardModel.Note.B;
        return testPassed;
    }
                
    public static void main(String args[]) {
        KeyboardView view = new KeyboardView();
        if(view.getNoteTest()) {
            System.out.println("View test passed.");
        }
        else {
            System.out.println("getNote test failed");
        }
    }
}

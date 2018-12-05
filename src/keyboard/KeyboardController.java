package keyboard;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayDeque;

public class KeyboardController implements MouseListener, ActionListener, ChangeListener, KeyListener {
    private final KeyboardModel keyboard;
    private final KeyboardView view;
    private RecordedPlaybackModel recording;
    private Thread recordingPlayback = new Thread();
    private int volume = 64;
    private int octave = 4;
    private boolean isRecording = false;
    
    public KeyboardController(KeyboardModel keyboard, KeyboardView view){
        this.keyboard = keyboard;
        this.view = view;
    }
    
    private void playNote(KeyboardModel.Note note) {
        view.setKeyColor(note, Color.YELLOW);
        keyboard.startNote(octave, note, volume);
        if(isRecording) {
            recording.startNote(octave, note, volume);
        }
    }
    private void stopNote(KeyboardModel.Note note) {
        switch(note){
            case Csharp:    
            case Dsharp:    
            case Fsharp:    
            case Gsharp:    
            case Asharp:    
                view.setKeyColor(note, Color.BLACK);
                break;
            case C:
            case D:
            case E:
            case F:
            case G:
            case A:
            case B:
                view.setKeyColor(note, Color.WHITE);
                break;
        }
        keyboard.stopNote(octave, note, volume);
        if(isRecording) {
            recording.stopNote(octave, note, volume);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e){
        playNote(view.getNote(e.getSource()));
    }

    @Override
    public void mouseReleased(MouseEvent e){
        stopNote(view.getNote(e.getSource()));
    }

    @Override 
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (view.isRecord(e.getSource())) {
            if (view.isRecordEnabled()) {
                isRecording = true;
                recording = new RecordedPlaybackModel();
            } else {
                isRecording = false;
            }
        } else if(view.isPlay(e.getSource())) {
            if(view.isPlayEnabled()){
                recordingPlayback = new Thread(new PlaybackModel(keyboard, recording));
                recordingPlayback.start();
            } else{
                recordingPlayback.interrupt(); 
            }
        }        
        if(view.isInstrument(e.getSource())){
            keyboard.setInstrument(view.getSelectedInstrument());
        }
        if(view.isOctave(e.getSource())){
            octave = view.getSelectedOctave();
        }
    }
    
    @Override
    public void stateChanged(ChangeEvent event){
        if (event.getSource() instanceof javax.swing.JSlider) {
            volume = ((JSlider)event.getSource()).getValue();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()){
            case 'a':
            case 'A':
                playNote(KeyboardModel.Note.C);
                break;
            case 'w':
            case 'W':
                playNote(KeyboardModel.Note.Csharp);
                break;
            case 's':
            case 'S':
                playNote(KeyboardModel.Note.D);
                break;
            case 'e':
            case 'E':
                playNote(KeyboardModel.Note.Dsharp);
                break;
            case 'd':
            case 'D':
                playNote(KeyboardModel.Note.E);
                break;
            case 'f':
            case 'F':
                playNote(KeyboardModel.Note.F);
                break;
            case 't':
            case 'T':
                playNote(KeyboardModel.Note.Fsharp);
                break;
            case 'g':
            case 'G':
                playNote(KeyboardModel.Note.G);
                break;
            case 'y':
            case 'Y':
                playNote(KeyboardModel.Note.Gsharp);
                break;
            case 'h':
            case 'H':
                playNote(KeyboardModel.Note.A);
                break;
            case 'u':
            case 'U':
                playNote(KeyboardModel.Note.Asharp);
                break;
            case 'j':
            case 'J':
                playNote(KeyboardModel.Note.B);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyChar()){
            case 'a':
            case 'A':
                stopNote(KeyboardModel.Note.C);
                break;
            case 'w':
            case 'W':
                stopNote(KeyboardModel.Note.Csharp);
                break;
            case 's':
            case 'S':
                stopNote(KeyboardModel.Note.D);
                break;
            case 'e':
            case 'E':
                stopNote(KeyboardModel.Note.Dsharp);
                break;
            case 'd':
            case 'D':
                stopNote(KeyboardModel.Note.E);
                break;
            case 'f':
            case 'F':
                stopNote(KeyboardModel.Note.F);
                break;
            case 't':
            case 'T':
                stopNote(KeyboardModel.Note.Fsharp);
                break;
            case 'g':
            case 'G':
                stopNote(KeyboardModel.Note.G);
                break;
            case 'y':
            case 'Y':
                stopNote(KeyboardModel.Note.Gsharp);
                break;
            case 'h':
            case 'H':
                stopNote(KeyboardModel.Note.A);
                break;
            case 'u':
            case 'U':
                stopNote(KeyboardModel.Note.Asharp);
                break;
            case 'j':
            case 'J':
                stopNote(KeyboardModel.Note.B);
                break;
        }
    }
    
    public static class FakeKeyboardModel extends KeyboardModel{
        private final ArrayDeque<Expectation> q;
        public FakeKeyboardModel(){
            super(new KeyboardModel.FakeMidiChannel());
            q = new ArrayDeque<>();
        }
        
        @Override
        public void startNote(int octave, Note note, int volume){
            Expectation actual = new Expectation(note, octave, true);
            Expectation expected = q.poll();
            if(!actual.equals(expected)){
                throw new RuntimeException("Actual does not match expected: " + expected +", actual: " + actual);
            }
        }
        @Override
        public void stopNote(int octave, Note note, int volume){
            Expectation actual = new Expectation(note, octave, false);
            Expectation expected = q.poll();
            if(!actual.equals(expected)){
                throw new RuntimeException("Actual does not match expected: " + expected +", actual: " + actual);
            }
        }
        public void expect(Note note, int octave, boolean noteOn) {
            q.add(new Expectation(note, octave, noteOn));
        }
        public boolean allExpectationsMet() {
            return q.isEmpty();
        }
        public class Expectation {
            private final int octave;
            private final KeyboardModel.Note note;
            private final boolean noteOn;
            public Expectation(KeyboardModel.Note note, int octave, boolean noteOn) {
                this.note = note;
                this.octave = octave;
                this.noteOn = noteOn;
            }
            @Override
            public boolean equals(Object other){
                if (!(other instanceof Expectation)) return false;
                Expectation e = (Expectation)other;
                return this.octave == e.octave && this.note == e.note && this.noteOn == e.noteOn;
            }
            @Override
            public String toString(){
                return "Expectation{note="+note+",,octave="+octave+",noteOn="+noteOn;
            }
        }
    }
    
    private static class FakeKeyboardView extends KeyboardView{
        private KeyboardModel.Note note, expectedNote;
        private Color color;
        private Object source;
        @Override
        public void setKeyColor(KeyboardModel.Note note, Color color){
            this.note = note;
            this.color = color;
        }
        @Override
        public KeyboardModel.Note getNote(Object source){
            this.source = source;
            return expectedNote;
        }
        public void setExpectedNote(KeyboardModel.Note note){
            this.expectedNote = note;
        }
        public boolean expect(Object source){
            return this.source == source;
        }
        public boolean expect(KeyboardModel.Note note, Color color){
            return this.note == note && this.color == color;
        }
    }
    
    public static void main(String args[]){
        FakeKeyboardModel model = new FakeKeyboardModel();
        FakeKeyboardView view = new FakeKeyboardView();
        KeyboardController controller = new KeyboardController(model, view);
        Component c = new Button();
        
        // Expect the notes to be called in order on the keyboard.
        model.expect(KeyboardModel.Note.C, 4, true);
        model.expect(KeyboardModel.Note.C, 4, false);
        model.expect(KeyboardModel.Note.Fsharp, 4, false);
        
        // C Key is pressed.
        view.setExpectedNote(KeyboardModel.Note.C);
        controller.mousePressed(new MouseEvent(c, 0, 0, 0, 0, 0, 1, false));
        System.out.println(view.expect(KeyboardModel.Note.C, Color.YELLOW));
        System.out.println(view.expect(c));
        
        // C Key is released.
        view.setExpectedNote(KeyboardModel.Note.C);
        controller.mouseReleased(new MouseEvent(c, 0, 0, 0, 0, 0, 1, false));
        System.out.println(view.expect(KeyboardModel.Note.C, Color.WHITE));
        System.out.println(view.expect(c));
        
        // F-sharp is released.
        view.setExpectedNote(KeyboardModel.Note.Fsharp);
        controller.mouseReleased(new MouseEvent(c, 0, 0, 0, 0, 0, 1, false));
        System.out.println(view.expect(KeyboardModel.Note.Fsharp, Color.BLACK));
        System.out.println(view.expect(c));
        if(!model.allExpectationsMet()) {
            System.out.println("TEST FAILED: Not all expected model calls occurred.");
        } else {
            System.out.println("All expected model calls occurred.");
        }
    }
}
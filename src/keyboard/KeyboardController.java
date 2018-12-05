package keyboard;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayDeque;

public class KeyboardController implements MouseListener, ActionListener, ChangeListener{
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
    
    @Override
    public void mousePressed(MouseEvent e){
        KeyboardModel.Note note = view.getNote(e.getSource());
        view.setKeyColor(note, Color.YELLOW);
        keyboard.startNote(octave, note, volume);
        if(isRecording) {
            recording.startNote(octave, note, volume);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e){
        KeyboardModel.Note note = view.getNote(e.getSource());
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
    
    public static class FakeKeyboardModel extends KeyboardModel{
        private final ArrayDeque<Expectation> q;
        public FakeKeyboardModel(){
            super(new KeyboardModel.FakeMidiChannel());
            q = new ArrayDeque<>();
        }
        
        @Override
        public void startNote(int octave, Note note, int volume){
            Expectation expected = q.poll();
            if(!new Expectation(note, 0, octave, true).equals(expected)){
                throw new RuntimeException();
            }
        }
        @Override
        public void stopNote(int octave, Note note, int volume){
            Expectation expected = q.poll();
            if(!new Expectation(note, 0, octave, false).equals(expected)){
                throw new RuntimeException();
            }
        }
        public void expect(Note note, long timestamp, int octave, boolean noteOn) {
            q.add(new Expectation(note, timestamp, octave, noteOn));
        }
        public boolean allExpectationsMet() {
            return q.isEmpty();
        }
        public class Expectation {
            private final int octave;
            private final KeyboardModel.Note note;
            private final boolean noteOn;
            private final long timestamp;
            public Expectation(KeyboardModel.Note note, long timestamp ,int octave, boolean noteOn) {
                this.note = note;
                this.timestamp = timestamp;
                this.octave = octave;
                this.noteOn = noteOn;
            }
            public boolean equals(Expectation other){
                return this.octave == other.octave || this.note == other.note || this.noteOn == other.noteOn  || this.timestamp == other.timestamp;
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
        model.expect(KeyboardModel.Note.C, 0, 4, true);
        model.expect(KeyboardModel.Note.C, 0, 4, false);
        model.expect(KeyboardModel.Note.Fsharp, 0, 4, false);
        
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
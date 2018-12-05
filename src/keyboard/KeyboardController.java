package Keyboard;

import Keyboard.KeyboardModel;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.*;
import java.util.ArrayDeque;


public class KeyboardController implements MouseListener{
    public static final int DEFAULT_OCTAVE = 4;
    private KeyboardModel model;
    private KeyboardView view;

    //Constructor for tests
    public KeyboardController(KeyboardModel model, KeyboardView view){
        this.model = model;
        this.view = view;
    }
    
    public void mousePressed(MouseEvent e){
        KeyboardModel.Note note = view.getNote(e.getSource() );
        model.startNote(DEFAULT_OCTAVE, note);
        view.setKeyColor(note, Color.YELLOW);
    }//end of mousePressed

    public void mouseReleased(MouseEvent e){
        KeyboardModel.Note note = view.getNote(e.getSource() );
        model.stopNote(DEFAULT_OCTAVE, note);
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
    }//end of mousereleased

    
    
    @Override 
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    public static class FakeKeyboardModel extends KeyboardModel{
        private ArrayDeque<Expectation> q = new ArrayDeque<>();
        public FakeKeyboardModel(){
            super(new KeyboardModel.FakeMidiChannel() );
        }
        
        public void startNote(int octave, Note note){
            Expectation expected = q.poll();
            if(!new Expectation(note, 0, octave, true).equals(expected)){
                throw new RuntimeException();
            }
        }
        public void stopNote(int octave, Note note){
            Expectation expected = q.poll();
            if(!new Expectation(note, 0, octave, false).equals(expected)){
                throw new RuntimeException();
            }
        }
        public void expect(Note note, long timestamp, int octave, boolean noteOn) {
            q.add(new Expectation(note, timestamp, octave, noteOn));
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
        public void setKeyColor(KeyboardModel.Note note, Color color){
            this.note = note;
            this.color = color;
        }
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
        
        model.expect(KeyboardModel.Note.C, 0, 4, true);
        model.expect(KeyboardModel.Note.C, 0, 4, false);
        model.expect(KeyboardModel.Note.Fsharp, 0, 4, false);
        
        // C Key is pressed.
        view.setExpectedNote(KeyboardModel.Note.C);
        controller.mousePressed(new MouseEvent(c, 0, 0, 0, 0, 0, 1, false));
        System.out.println("Correct key was pressed: " + view.expect(KeyboardModel.Note.C, Color.YELLOW));
        System.out.println("Key color is correct: " + view.expect(c));
        
        // C Key is released.
        view.setExpectedNote(KeyboardModel.Note.C);
        controller.mouseReleased(new MouseEvent(c, 0, 0, 0, 0, 0, 1, false));
        System.out.println("Correct key was pressed: " + view.expect(KeyboardModel.Note.C, Color.WHITE));
        System.out.println("Key color is correct: " + view.expect(c));
        
        // F-sharp is released.
        view.setExpectedNote(KeyboardModel.Note.Fsharp);
        controller.mouseReleased(new MouseEvent(c, 0, 0, 0, 0, 0, 1, false));
        System.out.println("Correct key was pressed: " + view.expect(KeyboardModel.Note.Fsharp, Color.BLACK));
        System.out.println("Key color is correct: " + view.expect(c));

    }//end of main
    
}//end of class KeyboardController
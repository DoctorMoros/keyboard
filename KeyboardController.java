package Keyboard;
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
    
    private static class FakeKeyboardModel extends KeyboardModel{
        private int octave;
        private Note note;
        private boolean noteOn;
        private ArrayDeque q = new ArrayDeque();
        public FakeKeyboardModel(){
            super(new KeyboardModel.FakeMidiChannel() );
        }
        
        public void startNote(int octave, Note note){
            if(this.octave != octave || this.note!=note || !this.noteOn){
                throw new RuntimeException();
            }
        }
        public void stopNote(int octave, Note note){
            if(this.octave != octave || this.note!=note || this.noteOn){
                throw new RuntimeException();
            }
        }
        public void expect(int octave, Note note, boolean noteOn) {
            this.octave = octave;
            this.note = note;
            this.noteOn = noteOn;
        }
        public class Expectation {
            private int octave;
            private KeyboardModel.Note note;
            private boolean noteOn;

            public Expectation(int octave, KeyboardModel.Note note, boolean noteOn) {
                    this.octave = octave;
                    this.note = note;
                    this.noteOn = noteOn;    
            }
            
            public String equals
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
        
        // C Key is pressed.
        view.setExpectedNote(KeyboardModel.Note.C);
        model.expect(4, KeyboardModel.Note.C, true);
        controller.mousePressed(new MouseEvent(c, 0, 0, 0, 0, 0, 1, false));
        System.out.println("Correct key was pressed: " + view.expect(KeyboardModel.Note.C, Color.YELLOW));
        System.out.println("Key color is correct: " + view.expect(c));
        
        // C Key is released.
        view.setExpectedNote(KeyboardModel.Note.C);
        model.expect(4, KeyboardModel.Note.C, false);
        controller.mouseReleased(new MouseEvent(c, 0, 0, 0, 0, 0, 1, false));
        System.out.println("Correct key was pressed: " + view.expect(KeyboardModel.Note.C, Color.WHITE));
        System.out.println("Key color is correct: " + view.expect(c));
        
        // F-sharp is released.
        view.setExpectedNote(KeyboardModel.Note.Fsharp);
        model.expect(4, KeyboardModel.Note.Fsharp, false);
        controller.mouseReleased(new MouseEvent(c, 0, 0, 0, 0, 0, 1, false));
        System.out.println("Correct key was pressed: " + view.expect(KeyboardModel.Note.Fsharp, Color.BLACK));
        System.out.println("Key color is correct: " + view.expect(c));

    }//end of main
    
}//end of class KeyboardController
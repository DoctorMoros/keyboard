package keyboard;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.*;


public class KeyboardController implements MouseListener, ActionListener{
    public static final int DEFAULT_OCTAVE = 4;
    private KeyboardModel keyboard;
    private KeyboardView view;
    private RecordedPlaybackModel recording;
    
    public KeyboardController(KeyboardModel keyboard, KeyboardView view){
        this.keyboard = keyboard;
        this.view = view;
    }
    
    public void mousePressed(MouseEvent e){
        KeyboardModel.Note note = view.getNote(e.getSource() );
        keyboard.startNote(DEFAULT_OCTAVE, note);
        if(view.isRecordEnabled())
            recording.startNote(DEFAULT_OCTAVE, note);
        view.setKeyColor(note, Color.YELLOW);
    }//end of mousePressed

    public void mouseReleased(MouseEvent e){
        KeyboardModel.Note note = view.getNote(e.getSource() );
        keyboard.stopNote(DEFAULT_OCTAVE, note);
        if(view.isRecordEnabled())
            recording.stopNote(DEFAULT_OCTAVE, note);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(view.isRecord(e.getSource())){
            if(view.isRecordEnabled()){
                recording = new RecordedPlaybackModel();
            }
        }
        else if(view.isPlay(e.getSource())){
            System.out.println(recording);
        }        
        if(view.isInstrument(e.getSource())){
            keyboard.setInstrument(view.getSelectedInstrument());
        }
    }
    
    private static class FakeKeyboardModel extends KeyboardModel{
        private int octave;
        private Note note;
        private boolean noteOn;
        public FakeKeyboardModel(){
            super(new KeyboardModel.FakeMidiChannel() );
        }
        
        public void startNote(int octave, Note note){
            this.octave = octave;
            this.note = note;
            this.noteOn = true;
        }
        public void stopNote(int octave, Note note){
            this.octave = octave;
            this.note = note;
            this.noteOn = false;
        }
        public boolean expect(int octave, Note note, boolean noteOn) {
            return this.octave == octave && this.note==note && this.noteOn == this.noteOn;
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
        controller.mousePressed(new MouseEvent(c, 0, 0, 0, 0, 0, 1, false));
        System.out.println(model.expect(4, KeyboardModel.Note.C, true));
        System.out.println(view.expect(KeyboardModel.Note.C, Color.YELLOW));
        System.out.println(view.expect(c));
        
        // C Key is released.
        view.setExpectedNote(KeyboardModel.Note.C);
        controller.mouseReleased(new MouseEvent(c, 0, 0, 0, 0, 0, 1, false));
        System.out.println(model.expect(4, KeyboardModel.Note.C, false));
        System.out.println(view.expect(KeyboardModel.Note.C, Color.WHITE));
        System.out.println(view.expect(c));
        
        // F-sharp is released.
        view.setExpectedNote(KeyboardModel.Note.Fsharp);
        controller.mouseReleased(new MouseEvent(c, 0, 0, 0, 0, 0, 1, false));
        System.out.println(model.expect(4, KeyboardModel.Note.Fsharp, false));
        System.out.println(view.expect(KeyboardModel.Note.Fsharp, Color.BLACK));
        System.out.println(view.expect(c));

    }//end of main
    
}//end of class KeyboardController
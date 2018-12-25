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

    private void playNote(Note note) {
        view.setKeyColor(note, Color.YELLOW);
        keyboard.startNote(octave, note, volume);
        if (isRecording) {
            recording.startNote(octave, note, volume);
        }
    }
    private void stopNote(Note note) {
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
            if(view.isPlayEnabled()) {
                if (recording != null) {
                    recordingPlayback = new Thread(new PlaybackModel(keyboard, recording));
                    recordingPlayback.start();
                }
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
                playNote(Note.C);
                break;
            case 'w':
            case 'W':
                playNote(Note.Csharp);
                break;
            case 's':
            case 'S':
                playNote(Note.D);
                break;
            case 'e':
            case 'E':
                playNote(Note.Dsharp);
                break;
            case 'd':
            case 'D':
                playNote(Note.E);
                break;
            case 'f':
            case 'F':
                playNote(Note.F);
                break;
            case 't':
            case 'T':
                playNote(Note.Fsharp);
                break;
            case 'g':
            case 'G':
                playNote(Note.G);
                break;
            case 'y':
            case 'Y':
                playNote(Note.Gsharp);
                break;
            case 'h':
            case 'H':
                playNote(Note.A);
                break;
            case 'u':
            case 'U':
                playNote(Note.Asharp);
                break;
            case 'j':
            case 'J':
                playNote(Note.B);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyChar()){
            case 'a':
            case 'A':
                stopNote(Note.C);
                break;
            case 'w':
            case 'W':
                stopNote(Note.Csharp);
                break;
            case 's':
            case 'S':
                stopNote(Note.D);
                break;
            case 'e':
            case 'E':
                stopNote(Note.Dsharp);
                break;
            case 'd':
            case 'D':
                stopNote(Note.E);
                break;
            case 'f':
            case 'F':
                stopNote(Note.F);
                break;
            case 't':
            case 'T':
                stopNote(Note.Fsharp);
                break;
            case 'g':
            case 'G':
                stopNote(Note.G);
                break;
            case 'y':
            case 'Y':
                stopNote(Note.Gsharp);
                break;
            case 'h':
            case 'H':
                stopNote(Note.A);
                break;
            case 'u':
            case 'U':
                stopNote(Note.Asharp);
                break;
            case 'j':
            case 'J':
                stopNote(Note.B);
                break;
        }
    }

    public static void main(String args[]){
        FakeKeyboardModel model = new FakeKeyboardModel();
        FakeKeyboardView view = new FakeKeyboardView();
        KeyboardController controller = new KeyboardController(model, view);
        Component c = new Button();

        // Expect the notes to be called in order on the keyboard.
        model.expect(Note.C, 4, true);
        model.expect(Note.C, 4, false);
        model.expect(Note.Fsharp, 4, false);

        // C Key is pressed.
        view.setExpectedNote(Note.C);
        controller.mousePressed(new MouseEvent(c, 0, 0, 0, 0, 0, 1, false));
        System.out.println(view.expect(Note.C, Color.YELLOW));
        System.out.println(view.expect(c));

        // C Key is released.
        view.setExpectedNote(Note.C);
        controller.mouseReleased(new MouseEvent(c, 0, 0, 0, 0, 0, 1, false));
        System.out.println(view.expect(Note.C, Color.WHITE));
        System.out.println(view.expect(c));

        // F-sharp is released.
        view.setExpectedNote(Note.Fsharp);
        controller.mouseReleased(new MouseEvent(c, 0, 0, 0, 0, 0, 1, false));
        System.out.println(view.expect(Note.Fsharp, Color.BLACK));
        System.out.println(view.expect(c));
        if(!model.allExpectationsMet()) {
            System.out.println("TEST FAILED: Not all expected model calls occurred.");
        } else {
            System.out.println("All expected model calls occurred.");
        }
    }
}
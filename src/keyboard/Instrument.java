package Keyboard;

import Keyboard.KeyboardModel;

public interface Instrument {
        
    public void startNote(int octave, KeyboardModel.Note note);
    public void stopNote(int octave, KeyboardModel.Note note);
}
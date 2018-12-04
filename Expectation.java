/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Keyboard;

/**
 *
 * @author Yorick
 */
public class Expectation {
    private int octave;
    private KeyboardModel.Note note;
    private boolean noteOn;
    
    public Expectation(int octave, KeyboardModel.Note note, boolean noteOn) {
            this.octave = octave;
            this.note = note;
            this.noteOn = noteOn;    
    }
    
    public int getOctave(){
        return octave;
    }
    
    public KeyboardModel.Note getNote(){
        return note;
    }
    
    public boolean getNoteOn(){
        return noteOn;
    }
}

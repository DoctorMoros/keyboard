/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keyboard;


public interface Instrument {
        
    public void startNote(int octave, KeyboardModel.Note note);
    public void stopNote(int octave, KeyboardModel.Note note);
}

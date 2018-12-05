package Keyboard;

import Keyboard.RecordedPlaybackModel;


public class PlaybackModel implements Runnable{
    private static int level;
    private static RecordedPlaybackModel recording;
    private  KeyboardModel keyboard;
    public PlaybackModel(KeyboardModel keyboard, RecordedPlaybackModel recording, int level){
        this.keyboard = keyboard;
        this.recording = recording;
        this.level = level;
    }
    
    public void run(){
        
    }
    
    //Reserved in case we can implement it.
    /*
    public void setBPM(int bpm){
        this.bpm = bpm;
    } */
 
    public void setVolume(int level){
        this.level = level;
    }
    public static void main(String[] args){
        //get a recording, volume.
        //call run
        //run calls startnote and stopnote of FakeKeyboardModel
        //set expectation
        //compare output from startnote and stopnote output with expectation
        KeyboardController.FakeKeyboardModel fakeKeyboard = new KeyboardController.FakeKeyboardModel();
        PlaybackModel player = new PlaybackModel(fakeKeyboard, recording, level);
        player.run();
        recording.
    }
}
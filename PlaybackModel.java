package Keyboard;


public class PlaybackModel implements Runnable{
    private int bpm;
    private int level;
    private RecordedPlaybackModel recording;
    private KeyboardModel keyboard;
    public PlaybackModel(KeyboardModel keyboard, RecordedPlaybackModel recording, int bpm, int level){
        this.keyboard = keyboard;
        this.recording = recording;
        this.bpm = bpm;
        this.level = level;
    }
    
    public void run(){
        
    }
    
    public void setBPM(int bpm){
        this.bpm = bpm;
    }
 
    public void setVolume(int level){
        this.level = level;
    }
    public static void main(String[] args){
        
    }
}
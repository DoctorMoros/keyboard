package keyboard;

public class PlaybackModel implements Runnable{
    private static RecordedPlaybackModel recording;
    private  KeyboardModel keyboard;
    public PlaybackModel(KeyboardModel keyboard, RecordedPlaybackModel recording){
        this.keyboard = keyboard;
        this.recording = recording;
    }
    
    public void run(){
        
    }

    public static void main(String[] args){
        //get a recording, volume.
        //call run
        //run calls startnote and stopnote of FakeKeyboardModel
        //set expectation
        //compare output from startnote and stopnote output with expectation
        //KeyboardController.FakeKeyboardModel fakeKeyboard = new KeyboardController.FakeKeyboardModel();
        //PlaybackModel player = new PlaybackModel(fakeKeyboard, recording, level);
        //player.run();
        //recording.
    }
}
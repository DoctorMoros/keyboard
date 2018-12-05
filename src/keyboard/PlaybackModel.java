package keyboard;

public class PlaybackModel implements Runnable{
    private final RecordedPlaybackModel recording;
    private final KeyboardModel keyboard;
    public PlaybackModel(KeyboardModel keyboard, RecordedPlaybackModel recording){
        this.keyboard = keyboard;
        this.recording = recording;
    }
    
    @Override
    public void run(){
        if(recording.size()==0){
            return;
        }
        
        RecordedPlaybackModel.RecordedNote last = recording.getNotes(0);
        keyboard.startNote(last.octave, last.note, 64);
            System.out.println(last);
        for(int a = 1; a < recording.size(); a++){
            RecordedPlaybackModel.RecordedNote current = recording.getNotes(a);
            System.out.println(current);
            long time = current.timestamp - last.timestamp-1;
            System.out.println("Sleep: " + time);
            long start = System.currentTimeMillis();
            try {
                Thread.sleep(time);
            } catch(InterruptedException e){
                keyboard.allNotesOff();
                return;
            }
            long end = System.currentTimeMillis();
            System.out.println("Actually slept: "+(end-start));
            if(current.isStart)
                keyboard.startNote(last.octave, last.note, 64);
            else
                keyboard.stopNote(last.octave, last.note, 64);
            last = current;
        }
    }

    public static void main(String[] args) throws Exception{
        //Input
        RecordedPlaybackModel recording = new RecordedPlaybackModel();
        recording.addNote(KeyboardModel.Note.C, 0, 4, true);
        recording.addNote(KeyboardModel.Note.C, 10, 4, false);
        recording.addNote(KeyboardModel.Note.Fsharp, 20, 4, true);
        recording.addNote(KeyboardModel.Note.D, 20, 4, true);
        recording.addNote(KeyboardModel.Note.Fsharp, 30, 4, false);
        recording.addNote(KeyboardModel.Note.D, 55, 4, false);
        recording.addNote(KeyboardModel.Note.G, 57, 7, true);
        recording.addNote(KeyboardModel.Note.G, 69, 7, false);
        
        //Expected output
        KeyboardController.FakeKeyboardModel keyboard = new KeyboardController.FakeKeyboardModel();
        keyboard.expect(KeyboardModel.Note.C, 0, 4, true);
        keyboard.expect(KeyboardModel.Note.C, 10, 4, false);
        keyboard.expect(KeyboardModel.Note.Fsharp, 20, 4, true);
        keyboard.expect(KeyboardModel.Note.D, 20, 4, true);
        keyboard.expect(KeyboardModel.Note.Fsharp, 30, 4, false);
        keyboard.expect(KeyboardModel.Note.D, 55, 4, false);
        keyboard.expect(KeyboardModel.Note.G, 57, 7, true);
        keyboard.expect(KeyboardModel.Note.G, 69, 7, false);
        
        //Start playback with timeout of 1 second
        PlaybackModel player = new PlaybackModel(keyboard, recording);
        Thread thread = new Thread(player);
        thread.start();
        try{
            thread.join(1000);
        }catch(InterruptedException e){
            System.out.println("Test has failed due to timeout.");
        }
        
        if(!keyboard.allExpectationsMet()) {
            System.out.println("TEST FAILED: Not all expected model calls occurred.");
        } else {
            System.out.println("All expected model calls occurred.");
        }
        
        //Empty recording: no expectations are set
        recording = new RecordedPlaybackModel();
        keyboard = new KeyboardController.FakeKeyboardModel();
        player = new PlaybackModel(keyboard, recording);
        player.run();
        
    }
}
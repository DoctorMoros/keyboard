package keyboard;

public class PlaybackModel implements Runnable{
    private final RecordedPlaybackModel recording;
    private final KeyboardModel keyboard;
    public PlaybackModel(KeyboardModel keyboard, RecordedPlaybackModel recording){
        this.keyboard = keyboard;
        this.recording = recording;
    }
    
    @Override
    public void run() {
        if(recording.size()==0) {
            return;
        }
        
        RecordedNote last = recording.getNotes(0);
        keyboard.startNote(last.octave, last.note, 64);
        for(int a = 1; a < recording.size(); a++){
            RecordedNote current = recording.getNotes(a);
            long time = current.timestamp - last.timestamp;
            try {
                Thread.sleep(time);
            } catch(InterruptedException unused){
                keyboard.allNotesOff();
                return;
            }
            if(current.isStart)
                keyboard.startNote(current.octave, current.note, 64);
            else
                keyboard.stopNote(current.octave, current.note, 64);
            last = current;
        }
    }

    public static void main(String[] args) throws Exception{
        //Input
        RecordedPlaybackModel recording = new RecordedPlaybackModel();
        recording.addNote(Note.C, 0, 4, true);
        recording.addNote(Note.C, 10, 4, false);
        recording.addNote(Note.Fsharp, 20, 4, true);
        recording.addNote(Note.D, 20, 4, true);
        recording.addNote(Note.Fsharp, 30, 4, false);
        recording.addNote(Note.D, 55, 4, false);
        recording.addNote(Note.G, 57, 7, true);
        recording.addNote(Note.G, 69, 7, false);
        
        //Expected output
        FakeKeyboardModel keyboard = new FakeKeyboardModel();
        keyboard.expect(Note.C, 4, true);
        keyboard.expect(Note.C, 4, false);
        keyboard.expect(Note.Fsharp,  4, true);
        keyboard.expect(Note.D, 4, true);
        keyboard.expect(Note.Fsharp, 4, false);
        keyboard.expect(Note.D, 4, false);
        keyboard.expect(Note.G, 7, true);
        keyboard.expect(Note.G, 7, false);
        
        //Start playback with timeout of 1 second
        PlaybackModel player = new PlaybackModel(keyboard, recording);
        Thread thread = new Thread(player);
        thread.start();
        try {
            thread.join(1000);
        } catch(InterruptedException unused) {
            System.out.println("Test has failed due to timeout.");
        }
        
        if(!keyboard.allExpectationsMet()) {
            System.out.println("TEST FAILED: Not all expected model calls occurred.");
        } else {
            System.out.println("All expected model calls occurred.");
        }
        
        //Empty recording: no expectations are set
        recording = new RecordedPlaybackModel();
        keyboard = new FakeKeyboardModel();
        player = new PlaybackModel(keyboard, recording);
        player.run();
    }
}
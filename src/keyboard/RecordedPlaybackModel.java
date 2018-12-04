/** 
 * if recording: then store played note, time difference from mouse e
 * if play: then read note, duration of each key press from ArrayList and startNote then endNote to play
 **/

package keyboard;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;


//class
public class RecordedPlaybackModel implements Instrument{
    private Clock clock;
    public ArrayList<RecordedNote> melody = new ArrayList<RecordedNote>();
    
    public static class FakeClock extends Clock{
        
        private Instant currentTime;
        private final ZoneId DEFAULT_TZONE = ZoneId.systemDefault();
  
        //DEFAULT CONSTRUCTOR
        public FakeClock(){ 
            currentTime = Instant.parse("2018-12-03T00:00:00.00Z");
        }    
        
        
        /** OVERRIDDEN METHODS **/
        @Override
        public ZoneId getZone(){
            return DEFAULT_TZONE;
        }

        @Override
        public Clock withZone(ZoneId zone){
            return Clock.fixed(currentTime, zone);
        }

        @Override
        public Instant instant(){
            return currentTime;
        }
        
        private void advanceTime(){
            currentTime = currentTime.plusMillis(1000);
        }

        
    //END
    }//END OF CLASS FAKECLOCK
    
    public static class RecordedNote {
        KeyboardModel.Note note;
        long timestamp;
        int octave;
        boolean isStart;
        
        //initialzation constructor
        public RecordedNote(KeyboardModel.Note note, long timestamp, int octave, boolean isStart){
            this.note = note;
            this.timestamp = timestamp;
            this.octave = octave;
            this.isStart = isStart;
        }
        
        public String toString(){
            return "RecordedNote{note="+this.note+", timestamp="+this.timestamp+", octave="+this.octave+", isStart="+this.isStart+"}";
        }
        
        public boolean equals(RecordedNote other){
            if (other == null) return false;
            return this.note == other.note && this.timestamp == other.timestamp && this.octave == other.octave && this.isStart == other.isStart;
        }//end of equals
        
    }//END OF CLASS RECORDEDNOTE

    // Default constructor
    public RecordedPlaybackModel() {
        this.clock = Clock.systemDefaultZone();
    }
    
    // Tests constructor used for injecting a clock.
    protected RecordedPlaybackModel(FakeClock clock){
        this.clock = clock;
    }
    
    private RecordedNote getNotes(int index) {
        if(index >= melody.size()){
            return null;
        }
        return melody.get(index);
    }
    
    public void startNote(int octave, KeyboardModel.Note note){
        long now = this.clock.millis();
        
    }
    
    public void stopNote(int octave, KeyboardModel.Note note){
        
    }
    
    
  /** 
    * if toggleButtonRecord on then:
    * timeDifference() between each mouse event 
    * pass note array on each mouse event 
    * 
    * 
   **/
    
    public static void main(String[] args) {
        //Test FakeClock class
        FakeClock clock = new FakeClock();
        
        final boolean START_NOTE = true;
        final boolean STOP_NOTE = false;
        final int DEFAULT_OCTAVE = 4;
        
        
        // create a fake clock that extends Clock and pass it in here to control the time
        RecordedPlaybackModel recording = new RecordedPlaybackModel(clock); 
        
        recording.startNote(DEFAULT_OCTAVE, KeyboardModel.Note.E);
        clock.advanceTime();
        recording.stopNote(DEFAULT_OCTAVE, KeyboardModel.Note.E);
        clock.advanceTime();
        recording.startNote(DEFAULT_OCTAVE, KeyboardModel.Note.C);
        clock.advanceTime();
        recording.stopNote(DEFAULT_OCTAVE, KeyboardModel.Note.C);
        clock.advanceTime();
        recording.startNote(DEFAULT_OCTAVE, KeyboardModel.Note.Asharp);
        clock.advanceTime();
        recording.stopNote(DEFAULT_OCTAVE, KeyboardModel.Note.Asharp);
         
       //confirm that the notes were recorded.
       boolean testsPass = true;
       
       testsPass &= matches(new RecordedNote(KeyboardModel.Note.E, 0l, DEFAULT_OCTAVE, START_NOTE), recording.getNotes(0));
       testsPass &= matches(new RecordedNote(KeyboardModel.Note.E, 1000l, DEFAULT_OCTAVE, STOP_NOTE), recording.getNotes(1));
       testsPass &= matches(new RecordedNote(KeyboardModel.Note.C, 2000l, DEFAULT_OCTAVE, START_NOTE), recording.getNotes(2));
       testsPass &= matches(new RecordedNote(KeyboardModel.Note.C, 3000l, DEFAULT_OCTAVE, STOP_NOTE), recording.getNotes(3));
       testsPass &= matches(new RecordedNote(KeyboardModel.Note.Asharp, 4000l, DEFAULT_OCTAVE, START_NOTE), recording.getNotes(4));
       testsPass &= matches(new RecordedNote(KeyboardModel.Note.Asharp, 5000l, DEFAULT_OCTAVE, STOP_NOTE), recording.getNotes(5));
        
       if (testsPass) {
           System.out.println("ALL TESTS PASS!!!");
       }
        
    }//end of main
    
    private static boolean matches(RecordedNote expected, RecordedNote actual) {
        boolean result = expected.equals(actual);
        if (!result) {
            System.out.println("Test failed: these do not match:");
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + actual);
        }
        return result;
    }
    
    
    //test
    //logic

}//end of class RecordedPlaybackModel



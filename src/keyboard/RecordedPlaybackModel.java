package Keyboard;

/**
 * if recording: then store played note, time difference from mouse e
 * if play: then read note, duration of each key press from ArrayList and startNote then endNote to play
 **/

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import static Keyboard.RecordedPlaybackModel.melody;


//class
public class RecordedPlaybackModel implements Instrument{
    
    private static FakeClock clock;
    public static ArrayList melody = new ArrayList();
    
    public static class FakeClock extends Clock{
    private long time = 0;
    private final Instant WHEN_STARTED;
    private final ZoneId DEFAULT_TZONE = ZoneId.systemDefault();
  
        //DEFAULT CONSTRUCTOR
        public FakeClock(){ 
            WHEN_STARTED = Instant.parse("2018-12-03T00:00:00.00Z");
        }    
        
        
        /** ACCESSORS **/
        public FakeClock getClock(){
            return clock;
        }
        
        /** OVERRIDDEN METHODS **/
        @Override
        public ZoneId getZone(){
            return DEFAULT_TZONE;
        }

        @Override
        public Clock withZone(ZoneId zone){
            return Clock.fixed(WHEN_STARTED, zone);
        }

        @Override
        public Instant instant(){
            return nextInstant();
        }
        
        private Instant nextInstant(){
            return WHEN_STARTED.plusMillis(0);
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
        
        public boolean equals(RecordedNote other){
            return this.note == other.note && this.timestamp == other.timestamp && this.octave == other.octave && this.isStart == other.isStart;
        }//end of equals
        
    }//END OF CLASS RECORDEDNOTE
    
    
    // Default constructor
    public RecordedPlaybackModel() {
        this.clock = clock.getClock();
    }
    
    
    private RecordedNote getNotes(int index) {
        //returns object at given index  
        RecordedNote recNote = (RecordedNote) RecordedPlaybackModel.melody.get(index);
        return recNote;
    }
    
    public void startNote(int octave, KeyboardModel.Note note){
        //long now = this.clock.Millis(0);
        
    }
    
    public void stopNote(int octave, KeyboardModel.Note note){
        
    }
    
    
    // Tests constructor used for injecting a clock.
    protected RecordedPlaybackModel(FakeClock clock){
        this.clock = clock;
    }
    
    
    public static void main(String[] args) {
        //Test FakeClock class
        FakeClock test = new FakeClock();
        
        final boolean START_NOTE = true;
        final boolean STOP_NOTE = false;
        final int DEFAULT_OCTAVE = 4;
        
        
        // create a fake clock that extends Clock and pass it in here to control the time
        RecordedPlaybackModel recording = new RecordedPlaybackModel(null); 
        
        recording.startNote(DEFAULT_OCTAVE, KeyboardModel.Note.E);
        recording.stopNote(DEFAULT_OCTAVE, KeyboardModel.Note.E);
        recording.startNote(DEFAULT_OCTAVE, KeyboardModel.Note.C);
        recording.stopNote(DEFAULT_OCTAVE, KeyboardModel.Note.C);
        recording.startNote(DEFAULT_OCTAVE, KeyboardModel.Note.Asharp);
        recording.stopNote(DEFAULT_OCTAVE, KeyboardModel.Note.Asharp);
         
       //confirm that the notes were recorded
        if (!recording.getNotes(0).equals(new RecordedNote(KeyboardModel.Note.E, 0l, DEFAULT_OCTAVE, START_NOTE))) System.out.println("PASS");
        if (!recording.getNotes(1).equals(new RecordedNote(KeyboardModel.Note.E, 1000l, DEFAULT_OCTAVE, STOP_NOTE))) System.out.println("PASS");
        if (!recording.getNotes(2).equals(new RecordedNote(KeyboardModel.Note.C, 2000l, DEFAULT_OCTAVE, START_NOTE))) System.out.println("PASS");
        if (!recording.getNotes(3).equals(new RecordedNote(KeyboardModel.Note.C, 3000l, DEFAULT_OCTAVE, STOP_NOTE))) System.out.println("PASS");
        if (!recording.getNotes(4).equals(new RecordedNote(KeyboardModel.Note.Asharp, 4000l, DEFAULT_OCTAVE, START_NOTE))) System.out.println("PASS");
        if (!recording.getNotes(5).equals(new RecordedNote(KeyboardModel.Note.Asharp, 5000l, DEFAULT_OCTAVE, STOP_NOTE))) System.out.println("PASS");
        //access first string in array with toString(of a RecordedNote Object with attributes :note object, time difference key duration, octave, ) 
        
    }//end of main
    
    
        
    //test
    //logic

}//end of class RecordedPlaybackModel


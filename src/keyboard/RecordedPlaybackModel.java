package keyboard;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;


//class
public class RecordedPlaybackModel implements Instrument{
    //Clock clock;
    FakeClock clock;
        
    
    public static class FakeClock extends Clock{
    //PRIVATE
    private int hr; //store hours
    private int min;  //store minutes
    private int sec; //store seconds
    //method variables
    private final Instant WHEN_STARTED = Instant.now();
    private final ZoneId DEFAULT_TZONE = ZoneId.systemDefault();
    private long count = 0;
  
        public FakeClock(){
        setTime(0, 0, 0); //0 start time
        }
        
        public FakeClock(int hours, int minutes, int seconds){
            setTime(hours, minutes, seconds);
        }
        
        public void setTime (int hours, int minutes, int seconds){
            if (0 <= hours && hours < 24)
                hr = hours;
            else
                hr = 0;
  
            if (0 <= minutes && minutes < 60)
                min = minutes;
            else
                min = 0;
  
            if (0 <= seconds && seconds < 60)
                sec = seconds;
            else
                sec = 0;
        }//end of setTime
        
        public void printTime(){
            if (hr < 10)
                System.out.print ("0");
            System.out.print (hr + ":");
            if (min < 10)
                System.out.print ("0");
            System.out.print (min + ":");
         if (sec < 10)
                System.out.print ("0");
            System.out.print (sec);
        }//printTime
 
        
        /** ACCESSORS **/
        public int getHours(){
            return hr;
        }
        
        public int getMinutes(){
            return min;
        }
        
        public int getSeconds(){
            return sec;
        }
        
        /** MODIFIERS **/
        public void incrementSeconds(){
            sec++;
            if (sec > 59)
            {
                sec = 0;
                incrementMinutes();  //increment minutes
            }
        }//incrSec
        
        
        public void incrementMinutes(){
            min++;
            if (min > 59)
            {
                min = 0;
                incrementHours();  //increment hours
            }
        }//incrMin
        
        public void incrementHours(){
            hr++;
            if (hr > 23)
                hr = 0;
        }//incrHr
        
        
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
            ++count;
            return WHEN_STARTED.plusSeconds(count);
        }
    
    }//end of class FakeClock
    
    public static class RecordedNote {
        KeyboardModel.Note note;
        ArrayList noteSignatures = new ArrayList();
        long timestamp;
        int octave;
        boolean isStart;
        
        
        public RecordedNote(KeyboardModel.Note note, long timestamp, int octave, boolean isStart){
            this.note = note;
            this.timestamp = timestamp;
            this.octave = octave;
            this.isStart = isStart;
        }
        
        public boolean equals(RecordedNote other){
            return this.note == other.note && this.timestamp == other.timestamp && this.octave == other.octave && this.isStart == other.isStart;
        }
    }//end of class RecordedNote
    
    //method headers
    
    // Default constructor
    public RecordedPlaybackModel() {
        this.clock = Clock.systemDefaultZone();
    }
    
    // Tests constructor used for injecting a clock.
    protected RecordedPlaybackModel(Clock clock){
        this.clock = clock;
    }
    
    private RecordedNote getNotes(int index) {
        return null;
    }
    
    public void startNote(int octave, KeyboardModel.Note note){
        long now = this.clock.millis();
    }
    
    public void stopNote(int octave, KeyboardModel.Note note){
        
    }
    
    public static void main(String[] args) {
        //clock class
        
        
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
    }
    
    
    //test
    //logic
}

package keyboard;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;

public class RecordedPlaybackModel implements MusicPlayer {
    private Clock clock;
    private ArrayList<RecordedNote> melody = new ArrayList<RecordedNote>();
    private long startTime = 0;

    public static class FakeClock extends Clock{
        private Instant currentTime;
        private final ZoneId DEFAULT_TZONE = ZoneId.systemDefault();

        public FakeClock(){
            currentTime = Instant.parse("2018-12-03T00:00:00.00Z");
        }

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
    }

    public static final class RecordedNote {
        public final KeyboardModel.Note note;
        public final long timestamp;
        public final int octave;
        public final boolean isStart;

        public RecordedNote(KeyboardModel.Note note, long timestamp, int octave, boolean isStart){
            this.note = note;
            this.timestamp = timestamp;
            this.octave = octave;
            this.isStart = isStart;
        }

        @Override
        public String toString(){
            return "RecordedNote{note="+this.note+", timestamp="+this.timestamp+", octave="+this.octave+", isStart="+this.isStart+"}";
        }

        public boolean equals(RecordedNote other){
            if (other == null) return false;
            return this.note == other.note && this.timestamp == other.timestamp && this.octave == other.octave && this.isStart == other.isStart;
        }
    }

    public RecordedPlaybackModel() {
        this.clock = Clock.systemDefaultZone();
    }

    // Tests constructor used for injecting a clock.
    protected RecordedPlaybackModel(FakeClock clock){
        this.clock = clock;
    }

    public RecordedNote getNotes(int index) {
        if(index >= melody.size()){
            return null;
        }
        return melody.get(index);
    }
    
    public int size(){
        return melody.size();
    }
    
    public String toString(){
        String text = "{";
        for(Object a: melody){
            text += a.toString();
        }
        return text + "}";
    }

    public void startNote(int octave, KeyboardModel.Note note, int volume){
        if(startTime == 0)
            startTime = this.clock.millis();
        melody.add(new RecordedNote(note, this.clock.millis() - startTime, octave, true));
    }

    public void stopNote(int octave, KeyboardModel.Note note, int volume){
        if(startTime == 0)
            startTime = this.clock.millis();
        melody.add(new RecordedNote(note, this.clock.millis() - startTime, octave, false));
    }

    public void addNote(KeyboardModel.Note note, long timestamp, int octave, boolean isStart){
        melody.add(new RecordedNote(note, timestamp, octave, isStart));
    }

    public static void main(String[] args) {
        FakeClock clock = new FakeClock();

        final boolean START_NOTE = true;
        final boolean STOP_NOTE = false;
        final int DEFAULT_OCTAVE = 4;

        RecordedPlaybackModel recording = new RecordedPlaybackModel(clock);

        recording.startNote(DEFAULT_OCTAVE, KeyboardModel.Note.E, 64);
        clock.advanceTime();
        recording.stopNote(DEFAULT_OCTAVE, KeyboardModel.Note.E, 64);
        clock.advanceTime();
        recording.startNote(DEFAULT_OCTAVE, KeyboardModel.Note.C, 64);
        clock.advanceTime();
        recording.stopNote(DEFAULT_OCTAVE, KeyboardModel.Note.C, 64);
        clock.advanceTime();
        recording.startNote(DEFAULT_OCTAVE, KeyboardModel.Note.Asharp, 64);
        clock.advanceTime();
        recording.stopNote(DEFAULT_OCTAVE, KeyboardModel.Note.Asharp, 64);

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
    }

    private static boolean matches(RecordedNote expected, RecordedNote actual) {
        boolean result = expected.equals(actual);
        if (!result) {
            System.out.println("Test failed: these do not match:");
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + actual);
        }
        return result;
    }
}
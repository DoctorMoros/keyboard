package keyboard;

import java.time.Clock;
import java.util.ArrayList;

public class RecordedPlaybackModel implements MusicPlayer {
    private final ArrayList<RecordedNote> melody;
    private final Clock clock;
    private long startTime = 0;

    public RecordedPlaybackModel() {
        this.melody = new ArrayList<>();
        this.clock = Clock.systemDefaultZone();
    }

    // Tests constructor used for injecting a fake Clock.
    protected RecordedPlaybackModel(Clock clock){
        this.melody = new ArrayList<>();
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

    @Override
    public String toString(){
        String text = "{";
        text = melody.stream().map((a) -> a.toString()).reduce(text, String::concat);
        return text + "}";
    }

    @Override
    public void startNote(int octave, Note note, int volume) {
        if (startTime == 0) {
            startTime = this.clock.millis();
        }
        melody.add(new RecordedNote(note, this.clock.millis() - startTime, octave, true));
    }

    @Override
    public void stopNote(int octave, Note note, int volume) {
        if (startTime == 0) {
            startTime = this.clock.millis();
        }
        melody.add(new RecordedNote(note, this.clock.millis() - startTime, octave, false));
    }

    public void addNote(Note note, long timestamp, int octave, boolean isStart){
        melody.add(new RecordedNote(note, timestamp, octave, isStart));
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

    public static void main(String[] args) {
        FakeClock clock = new FakeClock();

        final boolean START_NOTE = true;
        final boolean STOP_NOTE = false;
        final int DEFAULT_OCTAVE = 4;

        RecordedPlaybackModel recording = new RecordedPlaybackModel(clock);

        recording.startNote(DEFAULT_OCTAVE, Note.E, 64);
        clock.advanceTime();
        recording.stopNote(DEFAULT_OCTAVE, Note.E, 64);
        clock.advanceTime();
        recording.startNote(DEFAULT_OCTAVE, Note.C, 64);
        clock.advanceTime();
        recording.stopNote(DEFAULT_OCTAVE, Note.C, 64);
        clock.advanceTime();
        recording.startNote(DEFAULT_OCTAVE, Note.Asharp, 64);
        clock.advanceTime();
        recording.stopNote(DEFAULT_OCTAVE, Note.Asharp, 64);

       //confirm that the notes were recorded.
       boolean testsPass = true;
       testsPass &= matches(new RecordedNote(Note.E, 0l, DEFAULT_OCTAVE, START_NOTE), recording.getNotes(0));
       testsPass &= matches(new RecordedNote(Note.E, 1000l, DEFAULT_OCTAVE, STOP_NOTE), recording.getNotes(1));
       testsPass &= matches(new RecordedNote(Note.C, 2000l, DEFAULT_OCTAVE, START_NOTE), recording.getNotes(2));
       testsPass &= matches(new RecordedNote(Note.C, 3000l, DEFAULT_OCTAVE, STOP_NOTE), recording.getNotes(3));
       testsPass &= matches(new RecordedNote(Note.Asharp, 4000l, DEFAULT_OCTAVE, START_NOTE), recording.getNotes(4));
       testsPass &= matches(new RecordedNote(Note.Asharp, 5000l, DEFAULT_OCTAVE, STOP_NOTE), recording.getNotes(5));

       if (testsPass) {
           System.out.println("ALL TESTS PASS!!!");
       }
    }
}
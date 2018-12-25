package keyboard;

import javax.sound.midi.*;

public class KeyboardModel implements MusicPlayer {
    private static KeyboardModel keyboard;
    private static FakeMidiChannel fake;

    Synthesizer synthesizer;
    MidiChannel channel;

    // Test constructor used for injecting a fake MidiChannel.
    protected KeyboardModel(MidiChannel channel) {
        this.channel = channel;
    }

    public KeyboardModel() throws MidiUnavailableException {
        synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();
        channel = synthesizer.getChannels()[0];
    }

    public void setInstrument(Instrument instrument) {
        channel.programChange(instrument.getMidiCode());
    }

    private static void playNote(Note note, int duration) throws Exception {
        keyboard.startNote(4, note, 64);
        Thread.sleep(duration);
        keyboard.stopNote(4, note, 64);
    }

    @Override
    public void startNote(int octave, Note note, int volume) {
        //Octave number corresponds with classic theory, 1 through 8
        int key = 11 + (octave * 12) + note.getNote();
        channel.noteOn(key, volume);
    }

    @Override
    public void stopNote(int octave, Note note, int volume) {
        int key = 11 + (octave * 12) + note.getNote();
        channel.noteOff(key, volume);
    }

    public void allNotesOff() {
        channel.allNotesOff();
    }

    private static boolean startNoteTest(int oct, Note note, int expectedNote) {
        boolean notetestPassed = true;
        keyboard.startNote(oct, note, 64);
        if (!fake.expect(expectedNote, 64, true)) {
            System.out.println( note + "" +  oct + " Test failed!" ) ;
            notetestPassed = false;
        }
        return notetestPassed;
    }

    private static boolean stopNoteTest(int oct, Note note, int expectedNote) {
        boolean noteTestPassed = true;
        keyboard.stopNote(oct, note, 64);
        if (!fake.expect(expectedNote, 64, false)) {
            System.out.println( note + "" + oct + " Test failed!" );
            noteTestPassed = false;
        }
        return noteTestPassed;
    }

    private static boolean testSummary() {
        boolean testsPassed = true;
        testsPassed &= startNoteTest(1, Note.C, 24);
        testsPassed &= startNoteTest(1, Note.Csharp, 25);
        testsPassed &= startNoteTest(1, Note.D, 26);
        testsPassed &= startNoteTest(1, Note.Dsharp, 27);
        testsPassed &= startNoteTest(1, Note.E, 28);
        testsPassed &= startNoteTest(1, Note.F, 29);
        testsPassed &= startNoteTest(1, Note.Fsharp, 30);
        testsPassed &= startNoteTest(1, Note.G, 31);
        testsPassed &= startNoteTest(1, Note.Gsharp, 32);
        testsPassed &= startNoteTest(1, Note.A, 33);
        testsPassed &= startNoteTest(1, Note.Asharp, 34);
        testsPassed &= startNoteTest(1, Note.B, 35);
        testsPassed &= startNoteTest(2, Note.C, 36);
        testsPassed &= startNoteTest(3, Note.C, 48);
        testsPassed &= startNoteTest(4, Note.C, 60);
        testsPassed &= startNoteTest(5, Note.C, 72);
        testsPassed &= startNoteTest(6, Note.C, 84);
        testsPassed &= startNoteTest(7, Note.C, 96);
        testsPassed &= startNoteTest(8, Note.C, 108);
        testsPassed &= stopNoteTest(1, Note.C, 24);
        testsPassed &= stopNoteTest(1, Note.Csharp, 25);
        testsPassed &= stopNoteTest(1, Note.D, 26);
        testsPassed &= stopNoteTest(1, Note.Dsharp, 27);
        testsPassed &= stopNoteTest(1, Note.E, 28);
        testsPassed &= stopNoteTest(1, Note.F, 29);
        testsPassed &= stopNoteTest(1, Note.Fsharp, 30);
        testsPassed &= stopNoteTest(1, Note.G, 31);
        testsPassed &= stopNoteTest(1, Note.Gsharp, 32);
        testsPassed &= stopNoteTest(1, Note.A, 33);
        testsPassed &= stopNoteTest(1, Note.Asharp, 34);
        testsPassed &= stopNoteTest(1, Note.B, 35);
        testsPassed &= stopNoteTest(2, Note.C, 36);
        testsPassed &= stopNoteTest(3, Note.C, 48);
        testsPassed &= stopNoteTest(4, Note.C, 60);
        testsPassed &= stopNoteTest(5, Note.C, 72);
        testsPassed &= stopNoteTest(6, Note.C, 84);
        testsPassed &= stopNoteTest(7, Note.C, 96);
        testsPassed &= stopNoteTest(8, Note.C, 108);

        return testsPassed;
    }

    public static void main(String[] args) throws Exception {
        fake = new FakeMidiChannel();
        keyboard = new KeyboardModel(fake);
        if(!testSummary()) {
            return;
        }
        System.out.println("Tests passed! Hooray! Hooray!");
        final int HALF = 500;
        final int WHOLE = 1000;

        keyboard = new KeyboardModel();
        // Measure 1
        playNote(Note.E, HALF);
        playNote(Note.D, HALF);
        playNote(Note.C, HALF);
        playNote(Note.D, HALF);
        playNote(Note.E, HALF);
        playNote(Note.E, HALF);
        playNote(Note.E, WHOLE);

        // Measure 2
        playNote(Note.D, HALF);
        playNote(Note.D, HALF);
        playNote(Note.D, WHOLE);
        playNote(Note.E, HALF);
        playNote(Note.G, HALF);
        playNote(Note.G, WHOLE);
    }
}
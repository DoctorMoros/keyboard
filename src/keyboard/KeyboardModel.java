package keyboard;
import javax.sound.midi.*;
import keyboard.KeyboardModel.Instrument;

public class KeyboardModel implements MusicPlayer {
    private static final int VELOCITY = 64; //Value to be used for volume.

    private static FakeMidiChannel fake;
    private static KeyboardModel keyboard;

    Synthesizer synthesizer;
    MidiChannel channel;

    public static enum Instrument{
        Piano(1, "Piano"), BrightAcousticPiano(2, "Bright Acoustic Piano"), ElectricGrandPiano(3, "Electic Grand Piano"), HonkyTonk(4, "Honky Tonk"),
        ElectricPiano1(5, "Electric Piano 1"), Harpsichord(7, "Harpsichord"), DrawbarOrgan(17, "Drawbar Organ"), PercussiveOrgan(18, "Percussive Organ"),
        RockOrgan(19, "Rock Organ"), ChurchOrgan(20, "Church Organ"), ReedOrgan(21, "Reed Organ"), Accordion(22, "Acordion"),Harmonica(23, "Harmonica"),
        TangoAccordion(24, "Tango Accordion"), PizzicatoStrings(46, "Pizzicato Strings"), Helicopter(126,"Helicopter"), Gunshots(128, "Gunshot");

        private final int midiCode;
        private final String name;

        Instrument(int midiCode, String name){
            this.midiCode = midiCode;
            this.name = name;
        }
        public String toString(){
            return this.name;
        }
        public int getMidiCode(){
            return midiCode;
        }       
    }

    public static enum Note{
        C(1), Csharp(2), D(3), Dsharp(4), E(5), F(6), Fsharp(7), G(8), Gsharp(9), A(10), Asharp(11), B(12);
        private final int note;

        Note(int note){
            this.note = note;
        }
        int getNote() {
            return note;
        }
    }

    // Only used for tests.
    protected KeyboardModel(MidiChannel channel) {
        this.channel = channel;
    }

    public KeyboardModel() throws MidiUnavailableException {
      synthesizer = MidiSystem.getSynthesizer();
      synthesizer.open();
      channel = synthesizer.getChannels()[0];
    }    
    public void setInstrument(Instrument instrument){
        channel.programChange(instrument.getMidiCode());
    }

    private static void playNote(Note note, int duration) throws Exception {
        keyboard.startNote(4, note);
        Thread.sleep(duration);
        keyboard.stopNote(4, note);
    }

    public void startNote(int octave, Note note){
        //Octave number corresponds with classic theory, 1 through 8
        int key = 11 + (octave * 12) + note.getNote();
        channel.noteOn(key, VELOCITY);
    }
    public void stopNote(int octave, Note note){
        int key = 11 + (octave * 12) + note.getNote();
        channel.noteOff(key, VELOCITY);
    }

    public static class FakeMidiChannel implements MidiChannel {
        private int key, vel;
        private boolean noteOn;

        public void allNotesOff(){}
        public void allSoundOff(){}
        public void controlChange(int controller, int value){}
        public int getChannelPressure(){return 0;}
        public int getController(int controller){return 0;}
        public boolean getMono(){return false;}
        public boolean getMute(){return false;}
        public boolean getOmni(){return false;}
        public int getPitchBend(){return 0;}
        public int getPolyPressure(int noteNumber){return 0;}
        public int getProgram(){return 0;}
        public boolean getSolo(){return false;}
        public boolean localControl(boolean on){return false;}
        public void programChange(int program){}
        public void programChange(int bank, int program){}
        public void resetAllControllers(){}
        public void setChannelPressure(int pressure){}
        public void setMono(boolean on){}
        public void setMute(boolean mute){}
        public void setOmni(boolean on){}
        public void setPitchBend(int bend){}
        public void setPolyPressure(int noteNumber, int pressure){}
        public void setSolo(boolean soloState){}

        public void noteOn(int key){}
        public void noteOff(int key){}

        // Initialization Constructors -2 param
        public void noteOn(int key, int vel) {
            this.key = key;
            this.vel = vel;
            this.noteOn = true;
        }
        public void noteOff(int key, int vel) {
            this.key = key;
            this.vel = vel;
            this.noteOn = false;
        }

        public boolean expect(int key, int vel, boolean noteOn) {
            return this.key == key && this.vel==vel && this.noteOn == this.noteOn;
        }
    }

    // Debug overloaded methods.
    private static boolean startNoteTest(int oct, Note note, int expectedNote){
        boolean notetestPassed = true;
        keyboard.startNote(oct, note);
        if (!fake.expect(expectedNote, 64, true)){
            System.out.println( note + "" +  oct + " Test failed!" ) ;
            notetestPassed = false;
        }
        return notetestPassed;
    }
    private static boolean stopNoteTest(int oct, Note note, int expectedNote){
        boolean noteTestPassed = true;
        keyboard.stopNote(oct, note);
        if (!fake.expect(expectedNote, 64, false)) {
            System.out.println( note + "" + oct + " Test failed!" );
            noteTestPassed = false;
        }
        return noteTestPassed;
    }

    private static boolean testSummary(){
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
        if(!testSummary()){
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
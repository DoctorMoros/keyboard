package keyboard;

import javax.sound.midi.MidiChannel;

public class FakeMidiChannel implements MidiChannel {    
    private int key;
    private int vel;
    private boolean noteOn;

    @Override
    public void allNotesOff() {}
    @Override
    public void allSoundOff() {}
    @Override
    public void controlChange(int controller, int value) {}
    @Override
    public int getChannelPressure() { return 0; }
    @Override
    public int getController(int controller) { return 0; }
    @Override
    public boolean getMono() { return false; }
    @Override
    public boolean getMute() { return false; }
    @Override
    public boolean getOmni() { return false; }
    @Override
    public int getPitchBend() { return 0; }
    @Override
    public int getPolyPressure(int noteNumber) { return 0; }
    @Override
    public int getProgram() { return 0; }
    @Override
    public boolean getSolo() { return false; }
    @Override
    public boolean localControl(boolean on) { return false; }
    @Override
    public void programChange(int program) {}
    @Override
    public void programChange(int bank, int program) {}
    @Override
    public void resetAllControllers() {}
    @Override
    public void setChannelPressure(int pressure) {}
    @Override
    public void setMono(boolean on) {}
    @Override
    public void setMute(boolean mute) {}
    @Override
    public void setOmni(boolean on) {}
    @Override
    public void setPitchBend(int bend) {}
    @Override
    public void setPolyPressure(int noteNumber, int pressure) {}
    @Override
    public void setSolo(boolean soloState) {}
    @Override
    public void noteOff(int key) {}

    @Override
    public void noteOn(int key, int vel) {
        this.key = key;
        this.vel = vel;
        this.noteOn = true;
    }

    @Override
    public void noteOff(int key, int vel) {
        this.key = key;
        this.vel = vel;
        this.noteOn = false;
    }

    public boolean expect(int key, int vel, boolean noteOn) {
        return this.key == key && this.vel == vel && this.noteOn == this.noteOn;
    }
}

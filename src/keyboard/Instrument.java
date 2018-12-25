package keyboard;

public enum Instrument {
    Piano(1, "Piano"),
    BrightAcousticPiano(2, "Bright Acoustic Piano"),
    ElectricGrandPiano(3, "Electic Grand Piano"),
    HonkyTonk(4, "Honky Tonk"),
    ElectricPiano1(5, "Electric Piano 1"),
    Harpsichord(7, "Harpsichord"),
    DrawbarOrgan(17, "Drawbar Organ"),
    PercussiveOrgan(18, "Percussive Organ"),
    RockOrgan(19, "Rock Organ"),
    ChurchOrgan(20, "Church Organ"),
    ReedOrgan(21, "Reed Organ"),
    Accordion(22, "Acordion"),
    Harmonica(23, "Harmonica"),
    TangoAccordion(24, "Tango Accordion"),
    PizzicatoStrings(46, "Pizzicato Strings"),
    Helicopter(126, "Helicopter"),
    Gunshots(128, "Gunshot");

    private final int midiCode;
    private final String name;

    Instrument(int midiCode, String name) {
        this.midiCode = midiCode;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int getMidiCode() {
        return midiCode;
    }
}

package keyboard;

import java.util.Objects;

public final class RecordedNote {    
    public final Note note;
    public final long timestamp;
    public final int octave;
    public final boolean isStart;

    public RecordedNote(Note note, long timestamp, int octave, boolean isStart) {
        this.note = note;
        this.timestamp = timestamp;
        this.octave = octave;
        this.isStart = isStart;
    }

    @Override
    public String toString() {
        return "RecordedNote{note=" + this.note + ", timestamp=" + 
                this.timestamp + ", octave=" + this.octave + ", isStart=" + 
                this.isStart + " }";
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof RecordedNote)) {
            return false;
        }
        RecordedNote o = (RecordedNote)other;
        return this.octave == o.octave && this.note == o.note && 
                this.timestamp == o.timestamp && this.isStart == o.isStart;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.note);
        hash = 97 * hash + (int) (this.timestamp ^ (this.timestamp >>> 32));
        hash = 97 * hash + this.octave;
        hash = 97 * hash + (this.isStart ? 1 : 0);
        return hash;
    }
}

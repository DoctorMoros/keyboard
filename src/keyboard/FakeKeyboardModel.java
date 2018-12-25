package keyboard;

import java.util.ArrayDeque;

public class FakeKeyboardModel extends KeyboardModel {
    private final ArrayDeque<Expectation> queue;

    public FakeKeyboardModel() {
        super(new FakeMidiChannel());
        queue = new ArrayDeque<>();
    }

    @Override
    public void startNote(int octave, Note note, int volume) {
        Expectation actual = new Expectation(note, octave, true);
        Expectation expected = queue.poll();
        if (!actual.equals(expected)) {
            throw new RuntimeException("Actual does not match expected: " + expected + ", actual: " + actual);
        }
    }

    @Override
    public void stopNote(int octave, Note note, int volume) {
        Expectation actual = new Expectation(note, octave, false);
        Expectation expected = queue.poll();
        if (!actual.equals(expected)) {
            throw new RuntimeException("Actual does not match expected: " + expected + ", actual: " + actual);
        }
    }

    public void expect(Note note, int octave, boolean noteOn) {
        queue.add(new Expectation(note, octave, noteOn));
    }

    public boolean allExpectationsMet() {
        return queue.isEmpty();
    }

    private static class Expectation {
        private final int octave;
        private final Note note;
        private final boolean noteOn;

        public Expectation(Note note, int octave, boolean noteOn) {
            this.note = note;
            this.octave = octave;
            this.noteOn = noteOn;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Expectation)) {
                return false;
            }
            Expectation e = (Expectation) other;
            return this.octave == e.octave && this.note == e.note && this.noteOn == e.noteOn;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 23 * hash + this.octave;
            hash = 23 * hash + java.util.Objects.hashCode(this.note);
            hash = 23 * hash + (this.noteOn ? 1 : 0);
            return hash;
        }

        @Override
        public String toString() {
            return "Expectation{note=" + note + ",,octave=" + octave + ",noteOn=" + noteOn;
        }
    }
}

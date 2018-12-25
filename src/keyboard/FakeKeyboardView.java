package keyboard;

import java.awt.Color;

public class FakeKeyboardView extends KeyboardView {
    private Note note;
    private Note expectedNote;
    private Color color;
    private Object source;

    @Override
    public Note getNote(Object source) {
        this.source = source;
        return expectedNote;
    }

    @Override
    public void setKeyColor(Note note, Color color) {
        this.note = note;
        this.color = color;
    }

    public void setExpectedNote(Note note) {
        this.expectedNote = note;
    }

    public boolean expect(Object source) {
        return this.source == source;
    }

    public boolean expect(Note note, Color color) {
        return this.note == note && this.color == color;
    }
}
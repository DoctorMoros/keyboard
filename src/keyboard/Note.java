package keyboard;

public enum Note {
    C(1), Csharp(2), D(3), Dsharp(4), E(5), F(6), Fsharp(7), G(8), Gsharp(9), A(10), Asharp(11), B(12);
    private final int note;

    Note(int note) {
        this.note = note;
    }

    int getNote() {
        return note;
    }    
}

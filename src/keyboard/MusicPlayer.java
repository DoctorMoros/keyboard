package keyboard;

public interface MusicPlayer {
    public void startNote(int octave, Note note, int volume);
    public void stopNote(int octave, Note note, int volume);
}

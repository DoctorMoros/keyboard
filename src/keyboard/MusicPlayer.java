package keyboard;

public interface MusicPlayer {
    public void startNote(int octave, KeyboardModel.Note note);
    public void stopNote(int octave, KeyboardModel.Note note);
}

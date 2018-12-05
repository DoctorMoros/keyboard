package keyboard;

public interface MusicPlayer {
    public void startNote(int octave, KeyboardModel.Note note, int volume);
    public void stopNote(int octave, KeyboardModel.Note note, int volume);
}

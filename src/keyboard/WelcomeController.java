package keyboard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WelcomeController implements WindowListener, ActionListener {
    private static final boolean IS_START = true;
    private static final boolean IS_STOP = false;
    private static final int OCTAVE = 4;
    private static final long NOTE_GAP = 25;
        
    private final KeyboardView keyboardView;
    private final WelcomeScreen welcomeView;
    private final PlaybackModel boatPlayback;
    private final PlaybackModel maryPlayback;
    private final PlaybackModel birthdayPlayback;
    private final PlaybackModel jinglePlayback;
    private Thread boatThread = new Thread();
    private Thread maryThread = new Thread();
    private Thread birthdayThread = new Thread();
    private Thread jingleThread = new Thread();
    private boolean maryEnabled = false;
    private boolean boatEnabled = false;
    private boolean birthdayEnabled = false;
    private boolean jingleEnabled = false;

    public WelcomeController(KeyboardModel keyboard, KeyboardView keyboardView, WelcomeScreen welcomeView) {
        this.keyboardView = keyboardView;
        this.welcomeView = welcomeView;
        boatPlayback = new PlaybackModel(keyboard, createBoat());
        maryPlayback = new PlaybackModel(keyboard, createMary());
        birthdayPlayback = new PlaybackModel(keyboard, createBirthday());
        jinglePlayback = new PlaybackModel(keyboard, createJingle());
    }

    @Override
    public void windowOpened(WindowEvent we) {}
    @Override
    public void windowClosing(WindowEvent we) {}
    @Override
    public void windowIconified(WindowEvent we) {}
    @Override
    public void windowDeiconified(WindowEvent we) {}
    @Override
    public void windowActivated(WindowEvent we) {}
    @Override
    public void windowDeactivated(WindowEvent we) {}

    @Override
    public void windowClosed(WindowEvent we) {
        keyboardView.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(welcomeView.isBoat(ae.getSource())) {
            if(boatEnabled) {
                boatThread.interrupt();
                boatEnabled = false;
            } else {
                boatThread = new Thread(boatPlayback);
                boatThread.start();
                boatEnabled = true;
            }
        }
        if(welcomeView.isMary(ae.getSource())) {
            if(maryEnabled) {
                maryThread.interrupt();
                maryEnabled = false;
            } else {
                maryThread = new Thread(maryPlayback);
                maryThread.start();
                maryEnabled = true;
            }
        }
        if(welcomeView.isBirthday(ae.getSource())) {
            if(birthdayEnabled) {
                birthdayThread.interrupt();
                birthdayEnabled = false;
            } else {
                birthdayThread = new Thread(birthdayPlayback);
                birthdayThread.start();
                birthdayEnabled = true;
            }
        }
        if(welcomeView.isJingle(ae.getSource())) {
            if(jingleEnabled) {
                jingleThread.interrupt();
                jingleEnabled = false;
            } else {
                jingleThread = new Thread(jinglePlayback);
                jingleThread.start();
                jingleEnabled = true;
            }
        }
    }

    //https://www.musicaneo.com/sheetmusic/sm-120345_row_row_row_your_boat.html
    public final RecordedPlaybackModel createBoat() {
        final long MINIM = 1200 - NOTE_GAP;
        final long CROTCHET = 600 - NOTE_GAP;
        final long QUAVER = 300 - NOTE_GAP;

        long time = 0;
        RecordedPlaybackModel boat = new RecordedPlaybackModel();
        boat.addNote(Note.C, time, OCTAVE+1, IS_START);
        boat.addNote(Note.E, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        boat.addNote(Note.C, time, OCTAVE+1, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.C, time, OCTAVE+1, IS_START);
        time += QUAVER;
        boat.addNote(Note.C, time, OCTAVE+1, IS_STOP);
        boat.addNote(Note.E, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.G, time, OCTAVE, IS_START);
        boat.addNote(Note.C, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        boat.addNote(Note.G, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.G, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(Note.C, time, OCTAVE-1, IS_STOP);
        boat.addNote(Note.G, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.E, time, OCTAVE, IS_START);
        boat.addNote(Note.G, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        boat.addNote(Note.E, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.E, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(Note.E, time, OCTAVE, IS_STOP);
        boat.addNote(Note.G, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.C, time, OCTAVE, IS_START);
        boat.addNote(Note.E, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        boat.addNote(Note.C, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.C, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(Note.E, time, OCTAVE-1, IS_STOP);
        boat.addNote(Note.C, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.G, time, OCTAVE, IS_START);
        boat.addNote(Note.B, time, OCTAVE-1, IS_START);
        boat.addNote(Note.F, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        boat.addNote(Note.G, time, OCTAVE, IS_STOP);
        boat.addNote(Note.B, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.F, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(Note.F, time, OCTAVE-1, IS_STOP);
        boat.addNote(Note.F, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.E, time, OCTAVE, IS_START);
        boat.addNote(Note.G, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        boat.addNote(Note.E, time, OCTAVE, IS_STOP);
        boat.addNote(Note.G, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.D, time, OCTAVE, IS_START);
        boat.addNote(Note.F, time, OCTAVE-1, IS_START);
        time += QUAVER;
        boat.addNote(Note.D, time, OCTAVE, IS_STOP);
        boat.addNote(Note.F, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.C, time, OCTAVE, IS_START);
        boat.addNote(Note.C, time, OCTAVE-1, IS_START);
        boat.addNote(Note.E, time, OCTAVE-1, IS_START);
        time += MINIM;
        time += NOTE_GAP;
        time += CROTCHET;
        boat.addNote(Note.C, time, OCTAVE, IS_STOP);
        boat.addNote(Note.C, time, OCTAVE-1, IS_STOP);
        boat.addNote(Note.E, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.C, time, OCTAVE, IS_START);
        boat.addNote(Note.E, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        time += NOTE_GAP;
        time += QUAVER;
        boat.addNote(Note.C, time, OCTAVE, IS_STOP);
        boat.addNote(Note.E, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.C, time, OCTAVE, IS_START);
        boat.addNote(Note.G, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        time += NOTE_GAP;
        time += QUAVER;
        boat.addNote(Note.C, time, OCTAVE, IS_STOP);
        boat.addNote(Note.G, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.C, time, OCTAVE, IS_START);
        boat.addNote(Note.E, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        boat.addNote(Note.C, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.D, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(Note.D, time, OCTAVE, IS_STOP);
        boat.addNote(Note.E, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.E, time, OCTAVE, IS_START);
        boat.addNote(Note.G, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        time += NOTE_GAP;
        time += QUAVER;
        boat.addNote(Note.E, time, OCTAVE, IS_STOP);
        boat.addNote(Note.G, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.E, time, OCTAVE, IS_START);
        boat.addNote(Note.C, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        boat.addNote(Note.E, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.D, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(Note.D, time, OCTAVE, IS_STOP);
        boat.addNote(Note.C, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.E, time, OCTAVE, IS_START);
        boat.addNote(Note.C, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        boat.addNote(Note.E, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.F, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(Note.F, time, OCTAVE, IS_STOP);
        boat.addNote(Note.C, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.G, time, OCTAVE, IS_START);
        boat.addNote(Note.C, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        boat.addNote(Note.C, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.D, time, OCTAVE-1, IS_START);
        time += QUAVER;
        boat.addNote(Note.D, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.E, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        time += NOTE_GAP;
        time += QUAVER;
        boat.addNote(Note.G, time, OCTAVE, IS_STOP);
        boat.addNote(Note.E, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.C, time, OCTAVE+1, IS_START);
        boat.addNote(Note.E, time, OCTAVE-1, IS_START);
        boat.addNote(Note.C, time, OCTAVE-1, IS_START);
        time += QUAVER;
        boat.addNote(Note.C, time, OCTAVE+1, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.C, time, OCTAVE+1, IS_START);
        time += QUAVER;
        boat.addNote(Note.C, time, OCTAVE+1, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.C, time, OCTAVE+1, IS_START);
        time += QUAVER;
        boat.addNote(Note.C, time, OCTAVE+1, IS_STOP);
        boat.addNote(Note.E, time, OCTAVE-1, IS_STOP);
        boat.addNote(Note.C, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.G, time, OCTAVE, IS_START);
        boat.addNote(Note.F, time, OCTAVE-1, IS_START);
        boat.addNote(Note.B, time, OCTAVE-2, IS_START);
        time += QUAVER;
        boat.addNote(Note.G, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.G, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(Note.G, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.G, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(Note.G, time, OCTAVE, IS_STOP);
        boat.addNote(Note.E, time, OCTAVE-1, IS_STOP);
        boat.addNote(Note.C, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.E, time, OCTAVE, IS_START);
        boat.addNote(Note.G, time, OCTAVE-1, IS_START);
        boat.addNote(Note.C, time, OCTAVE-1, IS_START);
        time += QUAVER;
        boat.addNote(Note.E, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.E, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(Note.E, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.E, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(Note.E, time, OCTAVE, IS_STOP);
        boat.addNote(Note.G, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.C, time, OCTAVE, IS_START);
        boat.addNote(Note.E, time, OCTAVE-1, IS_START);
        time += QUAVER;
        boat.addNote(Note.C, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.C, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(Note.C, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.C, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(Note.C, time, OCTAVE, IS_STOP);
        boat.addNote(Note.E, time, OCTAVE-1, IS_STOP);
        boat.addNote(Note.C, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.G, time, OCTAVE, IS_START);
        boat.addNote(Note.C, time, OCTAVE, IS_START);
        time += CROTCHET;
        boat.addNote(Note.G, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.F, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(Note.F, time, OCTAVE, IS_START);
        boat.addNote(Note.C, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.E, time, OCTAVE, IS_START);
        boat.addNote(Note.G, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        boat.addNote(Note.E, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.D, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(Note.D, time, OCTAVE, IS_STOP);
        boat.addNote(Note.G, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.C, time, OCTAVE, IS_START);
        boat.addNote(Note.E, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        time += NOTE_GAP;
        time += QUAVER;
        boat.addNote(Note.E, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        boat.addNote(Note.C, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        time += NOTE_GAP;
        time += QUAVER;
        boat.addNote(Note.C, time, OCTAVE, IS_START);
        boat.addNote(Note.C, time, OCTAVE-1, IS_START);
        return boat;
    }
    //https://makingmusicfun.net/pdf/sheet_music/happy-birthday-easy-piano.pdf
    public final RecordedPlaybackModel createBirthday() {
        final long CROTCHET = 800 - NOTE_GAP;
        final long QUAVER = 400 - NOTE_GAP;

        long time = 0;
        RecordedPlaybackModel birthday = new RecordedPlaybackModel();
        birthday.addNote(Note.D, time, OCTAVE, IS_START);
        time += QUAVER;
        birthday.addNote(Note.D, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        birthday.addNote(Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        birthday.addNote(Note.E, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        birthday.addNote(Note.D, time, OCTAVE, IS_START);
        time += CROTCHET;
        birthday.addNote(Note.D, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        birthday.addNote(Note.G, time, OCTAVE, IS_START);
        time += CROTCHET;
        birthday.addNote(Note.G, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        birthday.addNote(Note.Fsharp, time, OCTAVE, IS_START);
        time += CROTCHET;
        time += NOTE_GAP;
        birthday.addNote(Note.D, time, OCTAVE, IS_START);
        birthday.addNote(Note.C, time, OCTAVE, IS_START);
        time += CROTCHET;
        birthday.addNote(Note.Fsharp, time, OCTAVE, IS_STOP);
        birthday.addNote(Note.D, time, OCTAVE, IS_STOP);
        birthday.addNote(Note.C, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        birthday.addNote(Note.D, time, OCTAVE, IS_START);
        time += QUAVER;
        birthday.addNote(Note.D, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        birthday.addNote(Note.D, time, OCTAVE, IS_START);
        time += QUAVER;
        birthday.addNote(Note.D, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        birthday.addNote(Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        birthday.addNote(Note.E, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        birthday.addNote(Note.D, time, OCTAVE, IS_START);
        time += CROTCHET;
        birthday.addNote(Note.D, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        birthday.addNote(Note.A, time, OCTAVE, IS_START);
        time += CROTCHET;
        birthday.addNote(Note.A, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        birthday.addNote(Note.G, time, OCTAVE, IS_START);
        time += CROTCHET;
        time += NOTE_GAP;
        birthday.addNote(Note.D, time, OCTAVE, IS_START);
        birthday.addNote(Note.B, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        birthday.addNote(Note.G, time, OCTAVE, IS_STOP);
        birthday.addNote(Note.D, time, OCTAVE, IS_STOP);
        birthday.addNote(Note.B, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        birthday.addNote(Note.D, time, OCTAVE, IS_START);
        time += QUAVER;
        birthday.addNote(Note.D, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        birthday.addNote(Note.D, time, OCTAVE, IS_START);
        time += QUAVER;
        birthday.addNote(Note.D, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        birthday.addNote(Note.D, time, OCTAVE+1, IS_START);
        time += CROTCHET;
        birthday.addNote(Note.D, time, OCTAVE+1, IS_STOP);
        time += NOTE_GAP;
        birthday.addNote(Note.B, time, OCTAVE, IS_START);
        birthday.addNote(Note.D, time, OCTAVE, IS_STOP);
        time += CROTCHET;
        birthday.addNote(Note.B, time, OCTAVE, IS_START);
        time += NOTE_GAP;
        birthday.addNote(Note.G, time, OCTAVE, IS_START);
        time += CROTCHET;
        birthday.addNote(Note.G, time, OCTAVE, IS_START);
        birthday.addNote(Note.D, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        birthday.addNote(Note.F, time, OCTAVE, IS_START);
        birthday.addNote(Note.C, time, OCTAVE, IS_START);
        time += CROTCHET;
        birthday.addNote(Note.F, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        birthday.addNote(Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        birthday.addNote(Note.E, time, OCTAVE, IS_STOP);
        birthday.addNote(Note.C, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        birthday.addNote(Note.C, time, OCTAVE+1, IS_START);
        time += QUAVER;
        birthday.addNote(Note.C, time, OCTAVE+1, IS_STOP);
        time += NOTE_GAP;
        birthday.addNote(Note.C, time, OCTAVE+1, IS_START);
        time += QUAVER;
        birthday.addNote(Note.C, time, OCTAVE+1, IS_STOP);
        time += NOTE_GAP;
        birthday.addNote(Note.B, time, OCTAVE, IS_START);
        time += CROTCHET;
        birthday.addNote(Note.B, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        birthday.addNote(Note.G, time, OCTAVE, IS_START);
        time += CROTCHET;
        birthday.addNote(Note.G, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        birthday.addNote(Note.A, time, OCTAVE, IS_START);
        time += CROTCHET;
        birthday.addNote(Note.A, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        birthday.addNote(Note.G, time, OCTAVE, IS_START);
        time += CROTCHET;
        time += NOTE_GAP;
        birthday.addNote(Note.D, time, OCTAVE, IS_START);
        birthday.addNote(Note.B, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        birthday.addNote(Note.G, time, OCTAVE, IS_STOP);
        birthday.addNote(Note.D, time, OCTAVE, IS_STOP);
        birthday.addNote(Note.B, time, OCTAVE-1, IS_STOP);
        return birthday;
    }
    //https://www.music-for-music-teachers.com/mary-had-a-little-lamb.html
    public final RecordedPlaybackModel createMary() {
        final long SEMIBREVE = 1920 - NOTE_GAP;
        final long MINIM = 960 - NOTE_GAP;
        final long CROTCHET = 480 - NOTE_GAP;

        long time = 0;
        RecordedPlaybackModel mary = new RecordedPlaybackModel();
        mary.addNote(Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(Note.E, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        mary.addNote(Note.D, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(Note.D, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        mary.addNote(Note.C, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(Note.C, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        mary.addNote(Note.D, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(Note.D, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        mary.addNote(Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(Note.E, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        mary.addNote(Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(Note.E, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        mary.addNote(Note.E, time, OCTAVE, IS_START);
        time += MINIM;
        mary.addNote(Note.E, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        mary.addNote(Note.D, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(Note.D, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        mary.addNote(Note.D, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(Note.D, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        mary.addNote(Note.D, time, OCTAVE, IS_START);
        time += MINIM;
        mary.addNote(Note.D, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        mary.addNote(Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(Note.E, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        mary.addNote(Note.G, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(Note.G, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        mary.addNote(Note.G, time, OCTAVE, IS_START);
        time += MINIM;
        mary.addNote(Note.G, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        mary.addNote(Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(Note.E, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        mary.addNote(Note.D, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(Note.D, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        mary.addNote(Note.C, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(Note.C, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        mary.addNote(Note.D, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(Note.D, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        mary.addNote(Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(Note.E, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        mary.addNote(Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(Note.E, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        mary.addNote(Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(Note.E, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        mary.addNote(Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(Note.E, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        mary.addNote(Note.D, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(Note.D, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        mary.addNote(Note.D, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(Note.D, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        mary.addNote(Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(Note.E, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        mary.addNote(Note.D, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(Note.D, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        mary.addNote(Note.C, time, OCTAVE, IS_START);
        time += SEMIBREVE;
        mary.addNote(Note.C, time, OCTAVE, IS_STOP);
        return mary;
    }

    public final RecordedPlaybackModel createJingle() {
        final long SEMIBREVE = 1920 - NOTE_GAP;
        final long MINIM = 960 - NOTE_GAP;
        final long CROTCHET = 480 - NOTE_GAP;

        long time = 0;
        RecordedPlaybackModel jingle = new RecordedPlaybackModel();
        
        // Jingle (C)
        jingle.addNote(Note.E, time, OCTAVE, IS_START);
        jingle.addNote(Note.E, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.G, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.C, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.E, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        jingle.addNote(Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.E, time, OCTAVE, IS_STOP);
        jingle.addNote(Note.E, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.G, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.C, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        // Bells, (D)
        jingle.addNote(Note.E, time, OCTAVE, IS_START);
        jingle.addNote(Note.F, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.A, time, OCTAVE-1, IS_START);
        jingle.addNote(Note.D, time, OCTAVE-1, IS_START);
        time += MINIM;
        jingle.addNote(Note.E, time, OCTAVE, IS_STOP);
        jingle.addNote(Note.F, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.A, time, OCTAVE-1, IS_STOP);
        jingle.addNote(Note.D, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        // Jingle (E)
        jingle.addNote(Note.E, time, OCTAVE, IS_START);
        jingle.addNote(Note.E, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.G, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.B, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.E, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        jingle.addNote(Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.E, time, OCTAVE, IS_STOP);
        jingle.addNote(Note.E, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.G, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.B, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        // Bells, (D)
        jingle.addNote(Note.E, time, OCTAVE, IS_START);
        jingle.addNote(Note.F, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.A, time, OCTAVE-1, IS_START);
        jingle.addNote(Note.D, time, OCTAVE-1, IS_START);
        time += MINIM;
        jingle.addNote(Note.E, time, OCTAVE, IS_STOP);
        jingle.addNote(Note.F, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.A, time, OCTAVE-1, IS_STOP);
        jingle.addNote(Note.D, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        // Jingle (C)
        jingle.addNote(Note.E, time, OCTAVE, IS_START);
        jingle.addNote(Note.E, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.G, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.C, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.E, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        jingle.addNote(Note.G, time, OCTAVE, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.G, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        // all
        jingle.addNote(Note.C, time, OCTAVE, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.C, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        // the
        jingle.addNote(Note.D, time, OCTAVE, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.D, time, OCTAVE, IS_STOP);
        jingle.addNote(Note.E, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.G, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.C, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        // way; (C)
        jingle.addNote(Note.E, time, OCTAVE, IS_START);
        jingle.addNote(Note.E, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.G, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.C, time, OCTAVE-1, IS_START);
        time += SEMIBREVE;
        jingle.addNote(Note.E, time, OCTAVE, IS_STOP);
        jingle.addNote(Note.E, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.G, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.C, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;

        // Oh (F)
        jingle.addNote(Note.F, time, OCTAVE, IS_START);
        jingle.addNote(Note.F, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.A, time, OCTAVE-1, IS_START);
        jingle.addNote(Note.C, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.F, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        // what
        jingle.addNote(Note.F, time, OCTAVE, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.F, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        // fun
        jingle.addNote(Note.F, time, OCTAVE, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.F, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        // it
        jingle.addNote(Note.F, time, OCTAVE, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.F, time, OCTAVE, IS_STOP);
        jingle.addNote(Note.F, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.A, time, OCTAVE-1, IS_STOP);
        jingle.addNote(Note.C, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        // is (C)
        jingle.addNote(Note.F, time, OCTAVE, IS_START);
        jingle.addNote(Note.E, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.G, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.C, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.F, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        // to
        jingle.addNote(Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.E, time, OCTAVE, IS_STOP);
        jingle.addNote(Note.E, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.G, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.C, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        // ride (A)
        jingle.addNote(Note.E, time, OCTAVE, IS_START);
        jingle.addNote(Note.E, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.A, time, OCTAVE-1, IS_START);
        jingle.addNote(Note.C, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.E, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        // a
        jingle.addNote(Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.E, time, OCTAVE, IS_STOP);
        jingle.addNote(Note.E, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.A, time, OCTAVE-1, IS_STOP);
        jingle.addNote(Note.C, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        // one (D)
        jingle.addNote(Note.E, time, OCTAVE, IS_START);
        jingle.addNote(Note.F, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.A, time, OCTAVE-1, IS_START);
        jingle.addNote(Note.D, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.E, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        // horse
        jingle.addNote(Note.D, time, OCTAVE, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.D, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        // open
        jingle.addNote(Note.D, time, OCTAVE, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.D, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        jingle.addNote(Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.E, time, OCTAVE, IS_STOP);
        jingle.addNote(Note.F, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.A, time, OCTAVE-1, IS_STOP);
        jingle.addNote(Note.D, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        // sleigh! (G)
        jingle.addNote(Note.D, time, OCTAVE, IS_START);
        jingle.addNote(Note.D, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.G, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.B, time, OCTAVE-1, IS_START);
        time += MINIM;
        jingle.addNote(Note.D, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        jingle.addNote(Note.G, time, OCTAVE, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.G, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        // (rest)
        time += CROTCHET;
        jingle.addNote(Note.D, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.G, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.B, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        
        // Jingle (C)
        jingle.addNote(Note.E, time, OCTAVE, IS_START);
        jingle.addNote(Note.E, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.G, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.C, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.E, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        jingle.addNote(Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.E, time, OCTAVE, IS_STOP);
        jingle.addNote(Note.E, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.G, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.C, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        // Bells, (D)
        jingle.addNote(Note.E, time, OCTAVE, IS_START);
        jingle.addNote(Note.F, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.A, time, OCTAVE-1, IS_START);
        jingle.addNote(Note.D, time, OCTAVE-1, IS_START);
        time += MINIM;
        jingle.addNote(Note.E, time, OCTAVE, IS_STOP);
        jingle.addNote(Note.F, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.A, time, OCTAVE-1, IS_STOP);
        jingle.addNote(Note.D, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        // Jingle (E)
        jingle.addNote(Note.E, time, OCTAVE, IS_START);
        jingle.addNote(Note.E, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.G, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.B, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.E, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        jingle.addNote(Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.E, time, OCTAVE, IS_STOP);
        jingle.addNote(Note.E, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.G, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.B, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        // Bells, (D)
        jingle.addNote(Note.E, time, OCTAVE, IS_START);
        jingle.addNote(Note.F, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.A, time, OCTAVE-1, IS_START);
        jingle.addNote(Note.D, time, OCTAVE-1, IS_START);
        time += MINIM;
        jingle.addNote(Note.E, time, OCTAVE, IS_STOP);
        jingle.addNote(Note.F, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.A, time, OCTAVE-1, IS_STOP);
        jingle.addNote(Note.D, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        // Jingle (C)
        jingle.addNote(Note.E, time, OCTAVE, IS_START);
        jingle.addNote(Note.E, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.G, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.C, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.E, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        jingle.addNote(Note.G, time, OCTAVE, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.G, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        // all
        jingle.addNote(Note.C, time, OCTAVE, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.C, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        // the
        jingle.addNote(Note.D, time, OCTAVE, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.D, time, OCTAVE, IS_STOP);
        jingle.addNote(Note.E, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.G, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.C, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        // way; (C)
        jingle.addNote(Note.E, time, OCTAVE, IS_START);
        jingle.addNote(Note.E, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.G, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.C, time, OCTAVE-1, IS_START);
        time += SEMIBREVE;
        jingle.addNote(Note.E, time, OCTAVE, IS_STOP);
        jingle.addNote(Note.E, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.G, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.C, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;

        // Oh (F)
        jingle.addNote(Note.F, time, OCTAVE, IS_START);
        jingle.addNote(Note.F, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.A, time, OCTAVE-1, IS_START);
        jingle.addNote(Note.C, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.F, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        // what
        jingle.addNote(Note.F, time, OCTAVE, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.F, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        // fun
        jingle.addNote(Note.F, time, OCTAVE, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.F, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        // it
        jingle.addNote(Note.F, time, OCTAVE, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.F, time, OCTAVE, IS_STOP);
        jingle.addNote(Note.F, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.A, time, OCTAVE-1, IS_STOP);
        jingle.addNote(Note.C, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        // is (C)
        jingle.addNote(Note.F, time, OCTAVE, IS_START);
        jingle.addNote(Note.E, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.G, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.C, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.F, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        // to
        jingle.addNote(Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.E, time, OCTAVE, IS_STOP);
        jingle.addNote(Note.E, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.G, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.C, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        // ride (A)
        jingle.addNote(Note.E, time, OCTAVE, IS_START);
        jingle.addNote(Note.E, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.A, time, OCTAVE-1, IS_START);
        jingle.addNote(Note.C, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.E, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        // a
        jingle.addNote(Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.E, time, OCTAVE, IS_STOP);
        jingle.addNote(Note.E, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.A, time, OCTAVE-1, IS_STOP);
        jingle.addNote(Note.C, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        // one (D)
        jingle.addNote(Note.G, time, OCTAVE, IS_START);
        jingle.addNote(Note.F, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.A, time, OCTAVE-1, IS_START);
        jingle.addNote(Note.D, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.G, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        // horse
        jingle.addNote(Note.G, time, OCTAVE, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.G, time, OCTAVE, IS_STOP);
        jingle.addNote(Note.F, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.A, time, OCTAVE-1, IS_STOP);
        jingle.addNote(Note.D, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        // open (B)
        jingle.addNote(Note.F, time, OCTAVE, IS_START);
        jingle.addNote(Note.F, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.B, time, OCTAVE-1, IS_START);
        jingle.addNote(Note.D, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.F, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        jingle.addNote(Note.D, time, OCTAVE, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.D, time, OCTAVE, IS_STOP);
        jingle.addNote(Note.F, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.B, time, OCTAVE-1, IS_STOP);
        jingle.addNote(Note.D, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        // sleigh! (Csus6)
        jingle.addNote(Note.C, time, OCTAVE, IS_START);
        jingle.addNote(Note.E, time, OCTAVE-2, IS_START);
        jingle.addNote(Note.A, time, OCTAVE-1, IS_START);
        jingle.addNote(Note.C, time, OCTAVE-1, IS_START);
        time += MINIM;
        jingle.addNote(Note.C, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        jingle.addNote(Note.C, time, OCTAVE, IS_START);
        time += CROTCHET;
        jingle.addNote(Note.C, time, OCTAVE, IS_STOP);
        time += NOTE_GAP;
        // (rest)
        time += CROTCHET;
        jingle.addNote(Note.E, time, OCTAVE-2, IS_STOP);
        jingle.addNote(Note.A, time, OCTAVE-1, IS_STOP);
        jingle.addNote(Note.C, time, OCTAVE-1, IS_STOP);
        time += NOTE_GAP;
        
        return jingle;
    }
}

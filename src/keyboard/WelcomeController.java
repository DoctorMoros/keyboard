package keyboard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WelcomeController implements WindowListener, ActionListener {
    private final KeyboardView keyboardView;
    private final WelcomeScreen welcomeView;
    private final KeyboardModel keyboard;
    private final PlaybackModel boatPlayback;
    private final PlaybackModel maryPlayback;
    private final PlaybackModel birthdayPlayback;
    private final PlaybackModel jinglePlayback;
    private Thread jingleThread = new Thread();
    private Thread boatThread = new Thread();
    private Thread maryThread = new Thread();
    private Thread birthdayThread = new Thread();
    private boolean maryEnabled = false;
    private boolean jingleEnabled = false;
    private boolean boatEnabled = false;
    private boolean birthdayEnabled = false;
    
    public WelcomeController(KeyboardModel keyboard, KeyboardView keyboardView, WelcomeScreen welcomeView){
        this.keyboardView = keyboardView;
        this.welcomeView = welcomeView;
        this.keyboard = keyboard;
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
        if(welcomeView.isBoat(ae.getSource())){
            if(boatEnabled){
                boatThread.interrupt();
                boatEnabled = false;
            } else{ 
                boatThread = new Thread(boatPlayback);
                boatThread.start();
                boatEnabled = true;
            }
        }
        if(welcomeView.isMary(ae.getSource())){
            if(maryEnabled){
                maryThread.interrupt();
                maryEnabled = false;
            } else{ 
                maryThread = new Thread(maryPlayback);
                maryThread.start();
                maryEnabled = true;
            }
        }
        if(welcomeView.isBirthday(ae.getSource())){
            if(birthdayEnabled){
                birthdayThread.interrupt();
                birthdayEnabled = false;
            } else{ 
                birthdayThread = new Thread(birthdayPlayback);
                birthdayThread.start();
                birthdayEnabled = true;
            }
        }
        if(welcomeView.isJingle(ae.getSource())){
            if(jingleEnabled){
                jingleThread.interrupt();
                jingleEnabled = false;
            } else{ 
                jingleThread = new Thread(jinglePlayback);
                jingleThread.start();
                jingleEnabled = true;
            }
        }
    }
        
    
    //https://www.musicaneo.com/sheetmusic/sm-120345_row_row_row_your_boat.html
    public final RecordedPlaybackModel createBoat(){
        final boolean IS_START = true;
        final boolean IS_STOP = false;
        final long GAP = 25;
        final long CROTCHET = 600 - GAP;
        final long MINIM = 1200 - GAP;
        final long QUAVER = 300 - GAP;
        final int OCTAVE = 4;
        
        long time = 0;
        RecordedPlaybackModel boat = new RecordedPlaybackModel();
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE+1, IS_START);
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE+1, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE+1, IS_START);
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE+1, IS_STOP);
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE-1, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_START);
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        boat.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE-1, IS_STOP);
        boat.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_START);
        boat.addNote(KeyboardModel.Note.G, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_STOP);
        boat.addNote(KeyboardModel.Note.G, time, OCTAVE-1, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_START);
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE-1, IS_STOP);
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_START);
        boat.addNote(KeyboardModel.Note.B, time, OCTAVE-1, IS_START);
        boat.addNote(KeyboardModel.Note.F, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        boat.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_STOP);
        boat.addNote(KeyboardModel.Note.B, time, OCTAVE-1, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.F, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.F, time, OCTAVE-1, IS_STOP);
        boat.addNote(KeyboardModel.Note.F, time, OCTAVE, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_START);
        boat.addNote(KeyboardModel.Note.G, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_STOP);
        boat.addNote(KeyboardModel.Note.G, time, OCTAVE-1, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_START);
        boat.addNote(KeyboardModel.Note.F, time, OCTAVE-1, IS_START);
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_STOP);
        boat.addNote(KeyboardModel.Note.F, time, OCTAVE-1, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_START);
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE-1, IS_START);
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE-1, IS_START);
        time += MINIM;
        time += GAP;
        time += CROTCHET;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_STOP);
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE-1, IS_STOP);
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE-1, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_START);
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        time += GAP;
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_STOP);
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE-1, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_START);
        boat.addNote(KeyboardModel.Note.G, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        time += GAP;
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_STOP);
        boat.addNote(KeyboardModel.Note.G, time, OCTAVE-1, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_START);
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_STOP);
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE-1, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_START);
        boat.addNote(KeyboardModel.Note.G, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        time += GAP;
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_STOP);
        boat.addNote(KeyboardModel.Note.G, time, OCTAVE-1, IS_STOP);
        time += GAP;        
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_START);
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_STOP);
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE-1, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_START);
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.F, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.F, time, OCTAVE, IS_STOP);
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE-1, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_START);
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE-1, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.D, time, OCTAVE-1, IS_START);
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.D, time, OCTAVE-1, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        time += GAP;
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_STOP);
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE-1, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE+1, IS_START);
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE-1, IS_START);
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE-1, IS_START);
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE+1, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE+1, IS_START);
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE+1, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE+1, IS_START);
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE+1, IS_STOP);
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE-1, IS_STOP);
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE-1, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_START);
        boat.addNote(KeyboardModel.Note.F, time, OCTAVE-1, IS_START);
        boat.addNote(KeyboardModel.Note.B, time, OCTAVE-2, IS_START);
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_STOP);
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE-1, IS_STOP);
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE-1, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_START);
        boat.addNote(KeyboardModel.Note.G, time, OCTAVE-1, IS_START);
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE-1, IS_START);
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_STOP);
        boat.addNote(KeyboardModel.Note.G, time, OCTAVE-1, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_START);
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE-1, IS_START);
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_STOP);
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE-1, IS_STOP);
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE-1, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_START);
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_START);
        time += CROTCHET;
        boat.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.F, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.F, time, OCTAVE, IS_START);
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_START);
        boat.addNote(KeyboardModel.Note.G, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_START);
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_STOP);
        boat.addNote(KeyboardModel.Note.G, time, OCTAVE-1, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_START);
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        time += GAP;
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.E, time, OCTAVE-1, IS_STOP);
        time += GAP;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        time += GAP;
        time += QUAVER;
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_START);
        boat.addNote(KeyboardModel.Note.C, time, OCTAVE-1, IS_START);
        return boat;
    }
    //https://makingmusicfun.net/pdf/sheet_music/happy-birthday-easy-piano.pdf
    public final RecordedPlaybackModel createBirthday(){
        final boolean IS_START = true;
        final boolean IS_STOP = false;
        final long GAP = 25;
        final long CROTCHET = 800 - GAP;
        final long MINIM = 1600 - GAP;
        final long QUAVER = 400 - GAP;
        final int OCTAVE = 4;
        
        long time = 0;
        RecordedPlaybackModel birthday = new RecordedPlaybackModel();
        birthday.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_START);
        time += QUAVER;
        birthday.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_STOP);
        time += GAP;
        birthday.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        birthday.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_STOP);
        time += GAP;
        birthday.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_START);
        time += CROTCHET;
        birthday.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_STOP);
        time += GAP;
        birthday.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_START);
        time += CROTCHET;
        birthday.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_STOP);
        time += GAP;
        birthday.addNote(KeyboardModel.Note.Fsharp, time, OCTAVE, IS_START);
        time += CROTCHET;
        time += GAP;
        birthday.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_START);
        birthday.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_START);
        time += CROTCHET;
        birthday.addNote(KeyboardModel.Note.Fsharp, time, OCTAVE, IS_STOP);
        birthday.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_STOP);
        birthday.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_STOP);
        time += GAP;
        birthday.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_START);
        time += QUAVER;
        birthday.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_STOP);
        time += GAP;
        birthday.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_START);
        time += QUAVER;
        birthday.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_STOP);
        time += GAP;
        birthday.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        birthday.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_STOP);
        time += GAP;
        birthday.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_START);
        time += CROTCHET;
        birthday.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_STOP);
        time += GAP;
        birthday.addNote(KeyboardModel.Note.A, time, OCTAVE, IS_START);
        time += CROTCHET;
        birthday.addNote(KeyboardModel.Note.A, time, OCTAVE, IS_STOP);
        time += GAP;
        birthday.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_START);
        time += CROTCHET;
        time += GAP;
        birthday.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_START);
        birthday.addNote(KeyboardModel.Note.B, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        birthday.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_STOP);
        birthday.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_STOP);
        birthday.addNote(KeyboardModel.Note.B, time, OCTAVE-1, IS_STOP);
        time += GAP;
        birthday.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_START);
        time += QUAVER;
        birthday.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_STOP);
        time += GAP;
        birthday.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_START);
        time += QUAVER;
        birthday.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_STOP);
        time += GAP;
        birthday.addNote(KeyboardModel.Note.D, time, OCTAVE+1, IS_START);
        time += CROTCHET;
        birthday.addNote(KeyboardModel.Note.D, time, OCTAVE+1, IS_STOP);
        time += GAP;
        birthday.addNote(KeyboardModel.Note.B, time, OCTAVE, IS_START);
        birthday.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_STOP);
        time += CROTCHET;
        birthday.addNote(KeyboardModel.Note.B, time, OCTAVE, IS_START);
        time += GAP;
        birthday.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_START);
        time += CROTCHET;
        birthday.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_START);
        birthday.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_STOP);
        time += GAP;
        birthday.addNote(KeyboardModel.Note.F, time, OCTAVE, IS_START);
        birthday.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_START);
        time += CROTCHET;
        birthday.addNote(KeyboardModel.Note.F, time, OCTAVE, IS_STOP);
        time += GAP;
        birthday.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        birthday.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_STOP);
        birthday.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_STOP);
        time += GAP;
        birthday.addNote(KeyboardModel.Note.C, time, OCTAVE+1, IS_START);
        time += QUAVER;
        birthday.addNote(KeyboardModel.Note.C, time, OCTAVE+1, IS_STOP);
        time += GAP;
        birthday.addNote(KeyboardModel.Note.C, time, OCTAVE+1, IS_START);
        time += QUAVER;
        birthday.addNote(KeyboardModel.Note.C, time, OCTAVE+1, IS_STOP);
        time += GAP;
        birthday.addNote(KeyboardModel.Note.B, time, OCTAVE, IS_START);
        time += CROTCHET;
        birthday.addNote(KeyboardModel.Note.B, time, OCTAVE, IS_STOP);
        time += GAP;
        birthday.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_START);
        time += CROTCHET;
        birthday.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_STOP);
        time += GAP;
        birthday.addNote(KeyboardModel.Note.A, time, OCTAVE, IS_START);
        time += CROTCHET;
        birthday.addNote(KeyboardModel.Note.A, time, OCTAVE, IS_STOP);
        time += GAP;
        birthday.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_START);
        time += CROTCHET;
        time += GAP;
        birthday.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_START);
        birthday.addNote(KeyboardModel.Note.B, time, OCTAVE-1, IS_START);
        time += CROTCHET;
        birthday.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_STOP);
        birthday.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_STOP);
        birthday.addNote(KeyboardModel.Note.B, time, OCTAVE-1, IS_STOP);
        return birthday;
    }
    //https://www.music-for-music-teachers.com/mary-had-a-little-lamb.html
    public final RecordedPlaybackModel createMary(){
        final boolean IS_START = true;
        final boolean IS_STOP = false;
        final long GAP = 25;
        final long WHOLE = 1920 - GAP;
        final long MINIM = 960 - GAP;
        final long CROTCHET = 480 - GAP;
        final long QUAVER = 240 - GAP;
        final int OCTAVE = 4;
        
        long time = 0;
        RecordedPlaybackModel mary = new RecordedPlaybackModel();
        mary.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_STOP);
        time += GAP;
        mary.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_STOP);
        time += GAP;
        mary.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_STOP);
        time += GAP;
        mary.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_STOP);
        time += GAP;
        mary.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_STOP);
        time += GAP;
        mary.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_STOP);
        time += GAP;
        mary.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_START);
        time += MINIM;
        mary.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_STOP);
        time += GAP;
        mary.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_STOP);
        time += GAP;
        mary.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_STOP);
        time += GAP;
        mary.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_START);
        time += MINIM;
        mary.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_STOP);
        time += GAP;
        mary.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_STOP);
        time += GAP;
        mary.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_STOP);
        time += GAP;
        mary.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_START);
        time += MINIM;
        mary.addNote(KeyboardModel.Note.G, time, OCTAVE, IS_STOP);
        time += GAP;
        mary.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_STOP);
        time += GAP;
        mary.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_STOP);
        time += GAP;
        mary.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_STOP);
        time += GAP;
        mary.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_STOP);
        time += GAP;
        mary.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_STOP);
        time += GAP;
        mary.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_STOP);
        time += GAP;
        mary.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_STOP);
        time += GAP;
        mary.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_STOP);
        time += GAP;
        mary.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_STOP);
        time += GAP;
        mary.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_STOP);
        time += GAP;
        mary.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_STOP);
        time += GAP;
        mary.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_START);
        time += CROTCHET;
        mary.addNote(KeyboardModel.Note.D, time, OCTAVE, IS_STOP);
        time += GAP;
        mary.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_START);
        time += WHOLE;
        mary.addNote(KeyboardModel.Note.C, time, OCTAVE, IS_STOP);
        return mary;
    }
    
    public final RecordedPlaybackModel createJingle(){
        final boolean IS_START = true;
        final boolean IS_STOP = false;
        final long GAP = 25;
        final long WHOLE = 1200 - GAP;
        final long MINIM = 600 - GAP;
        final long CROTCHET = 300 - GAP;
        final long QUAVER = 150 - GAP;
        final int OCTAVE = 4;
        
        long time = 0;
        RecordedPlaybackModel jingle = new RecordedPlaybackModel();
        jingle.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_START);
        time += CROTCHET;
        jingle.addNote(KeyboardModel.Note.E, time, OCTAVE, IS_STOP);
        time += GAP;
        return jingle;
    }
}

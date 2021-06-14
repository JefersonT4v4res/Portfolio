import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException; 


public class Sound {

    public AudioInputStream silence;
    public AudioInputStream died;
    public AudioInputStream menu;
    public AudioInputStream caveAmbience;
    public AudioInputStream combatTheme;
    public Clip clipSilence;
    public Clip clipMenu;
    public Clip clipDied;
    public Clip clipCaveAmbience;
    public Clip clipCombatTheme;
    private static Sound singleton = null; 

    private Sound(){
        carregarSons();
        
    }

    public static Sound getInstance() { 
        if(singleton == null) { 
             singleton = new Sound(); 
        } 
        return singleton; 
        }

    public void carregarSons() { 
        try {
            silence = AudioSystem.getAudioInputStream(getClass().getResource("sons/silence.wav"));
            menu = AudioSystem.getAudioInputStream(getClass().getResource("sons/ArabianNightes.wav"));
            died = AudioSystem.getAudioInputStream(getClass().getResource("sons/diedEffectsound.wav"));
            caveAmbience = AudioSystem.getAudioInputStream(getClass().getResource("sons/CaveAdventure.wav"));
            combatTheme = AudioSystem.getAudioInputStream(getClass().getResource("sons/confrontation.wav"));

            clipSilence = AudioSystem.getClip();
            clipMenu = AudioSystem.getClip();
            clipDied = AudioSystem.getClip();
            clipCaveAmbience = AudioSystem.getClip();
            clipCombatTheme = AudioSystem.getClip();

            clipSilence.open(silence);
            clipMenu.open(menu);
            clipDied.open(died); 
            clipCaveAmbience.open(caveAmbience); 
            clipCombatTheme.open(combatTheme); 

            clipSilence.start();
            clipSilence.stop();

        } catch (UnsupportedAudioFileException e) { 
         e.printStackTrace(); 
        } catch (IOException e) { 
        e.printStackTrace(); 
         } catch (LineUnavailableException e) { 
         e.printStackTrace(); 
         } 
         } 

         public void init(){
            clipSilence.setFramePosition(0);
            clipSilence.start(); 
         }

         public void startMenu(){
            clipMenu.setFramePosition(0);
            clipMenu.start(); 
         }

         public void stopMenu(){
            clipMenu.setFramePosition(0);
            clipMenu.stop(); 
         }

         public void died(){
            clipDied.setFramePosition(0);
            clipDied.start(); 
         }

         public void startCaveTheme(){
            clipCaveAmbience.setFramePosition(0);
            clipCaveAmbience.start();
         }
         public void stopCaveTheme(){
            clipCaveAmbience.setFramePosition(0);
            clipCaveAmbience.stop();
         }


         public void CombatTheme(){
            clipCombatTheme.setFramePosition(0);
            clipCombatTheme.start();
         }

}

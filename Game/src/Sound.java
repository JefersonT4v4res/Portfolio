/*import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException; 


public class Sound {

    public AudioInputStream silence;
    public AudioInputStream opening;
    public AudioInputStream caveAmbience;
    public AudioInputStream combatTheme;
    public Clip clipSilence;
    public Clip clipOpening;
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
            silence = AudioSystem.getAudioInputStream(getClass().getResource("C:/Users/cliente/Desktop/Tales_of_Kahf/Game/src/sons/silence.wav"));
            opening = AudioSystem.getAudioInputStream(getClass().getResource("C:/Users/cliente/Desktop/Tales_of_Kahf/Game/src/src/sons/abertura.wav"));
            caveAmbience = AudioSystem.getAudioInputStream(getClass().getResource("C:/Users/cliente/Desktop/Tales_of_Kahf/Game/src/src/sons/CaveAdventure.wav"));
            combatTheme = AudioSystem.getAudioInputStream(getClass().getResource("C:/Users/cliente/Desktop/Tales_of_Kahf/Game/src/src/sons/confrontation.wav"));

            clipSilence = AudioSystem.getClip();
            clipOpening = AudioSystem.getClip();
            clipCaveAmbience = AudioSystem.getClip();
            clipCombatTheme = AudioSystem.getClip();

            clipSilence.open(silence);
            clipOpening.open(opening); 
            clipCaveAmbience.open(caveAmbience); 
            clipCombatTheme.open(combatTheme); 

            clipSilence.start();

        } catch (UnsupportedAudioFileException e) { 
         e.printStackTrace(); 
        } catch (IOException e) { 
        e.printStackTrace(); 
         } catch (LineUnavailableException e) { 
         e.printStackTrace(); 
         } 
         } 

         public void Menu(){
            clipOpening.setFramePosition(0);
            clipOpening.start(); 
         }

         public void CaveTheme(){
            clipCaveAmbience.setFramePosition(0);
            clipCaveAmbience.start();
         }

         public void CombatTheme(){
            clipCombatTheme.setFramePosition(0);
            clipCombatTheme.start();
         }

}
*/
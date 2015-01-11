package UserInterface;

import java.util.HashMap;
import java.applet.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import Domain.main;
public class SoundLibrary {
    HashMap<String,AudioClip> soundLibrary;
    public SoundLibrary (){
        AudioClip select = null;
        AudioClip match = null;
       AudioClip fall = null;
        
        
        try {
        //InputStream test = new FileInputStream("file:Alaturka Game Of Thrones Jenerik Muzigi.wav");
        select = Applet.newAudioClip(new URL("file:select.wav"));
        match = Applet.newAudioClip(new URL("file:match.wav"));
       fall = Applet.newAudioClip(new URL("file:Background.wav"));
        } catch (Exception e) {System.out.println(e.getMessage());}
        soundLibrary = new HashMap(3);
        soundLibrary.put("select", select);
      //  soundLibrary.put("fall", fall);
        soundLibrary.put("match",match);
       soundLibrary.put("Background",fall);
         
         
    }
  
  
    public void playAudio(String name){
        AudioClip sample = soundLibrary.get(name);
        sample.play();
    }
//    public static synchronized void playSound(final String url) {
//  	  new Thread(new Runnable() {
//  		  // The wrapper thread is unnecessary, unless it blocks on the
//  		  // Clip finishing; see comments.
//  		    public void run() {
//  		      try {
//  		        Clip clip = AudioSystem.getClip();
//  		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
//  		          main.class.getResourceAsStream("file:Alaturka Game Of Thrones Jenerik Muzigi.wav" + url));
//  		        clip.open(inputStream);
//  		        clip.start(); 
//  		      } catch (Exception e) {
//  		        System.err.println(e.getMessage());
//  		      }
//  		    }
//  		  }).start();
//  		}
}


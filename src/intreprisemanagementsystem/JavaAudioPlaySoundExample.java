/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 *
 * @author Dosh
 */
public class JavaAudioPlaySoundExample {
    public static void main(String[] args) 
  throws Exception
  {
    // open the sound file as a Java input stream
    String gongFile = "D://DeskTop//ENGLISH//Benjamin Dube//02 Track 2.wma";
    InputStream in = new FileInputStream(gongFile);

    // create an audiostream from the inputstream
   // AudioStream audioStream = new AudioStream(in);

    // play the audio clip with the audioplayer class
   // AudioPlayer.player.start(audioStream);
  }
}

package Renderer;


import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class AudioAsset {
    public static AudioClip baitRetrieve,
            congrats,
            wrongKey,
            correctKey,
            fishCaught,
            splash,
            baitSwing,
            ping,
            click;

    public static MediaPlayer BGM,ropeTension,footsteps;

    static {
        baitSwing = loadAudioClip("Audio/bait_swing.wav");
        baitRetrieve = loadAudioClip("Audio/bait_retrieve.wav");
        congrats = loadAudioClip("Audio/congrats.wav");
        wrongKey = loadAudioClip("Audio/wrong_key.wav");
        correctKey = loadAudioClip("Audio/correct_key.wav");
        fishCaught = loadAudioClip("Audio/fish_caught.aif");
        splash = loadAudioClip("Audio/splash.mp3");
        ping = loadAudioClip("Audio/ping.mp3");
        click = loadAudioClip("Audio/click.mp3");

        BGM = loadMedia("Audio/bgm.mp3");
        ropeTension = loadMedia("Audio/rope_tension.mp3");
        footsteps = loadMedia("Audio/footsteps.mp3");

        //setup
        setLoop(BGM);
        setLoop(ropeTension);
        setLoop(footsteps);

    }
    public static void setMusicVolume(double musicVolume){
        BGM.setVolume(musicVolume);
        ropeTension.setVolume(musicVolume);
        footsteps.setVolume(musicVolume);
    }



    public static void playBGM(boolean play){
        //System.out.println(BGM.getStatus());
        playMusic(BGM,play);
    }
    public static void playFootsteps(boolean play){
        //System.out.println(BGM.getStatus());
        playMusic(footsteps,play);
    }
    public static void playRopeTension(boolean play){
        playMusic(ropeTension,play);
    }

    private static MediaPlayer loadMedia(String path){
        return new MediaPlayer(new Media(ClassLoader.getSystemResource(path).toString()));
    }

    private static AudioClip loadAudioClip(String path){
        return new AudioClip(ClassLoader.getSystemResource(path).toString());
    }


    private static void playMusic(MediaPlayer mediaPlayer, boolean play){
        if(play) {
            if(mediaPlayer.getStatus() != MediaPlayer.Status.PLAYING)
                mediaPlayer.play();
        }
        else mediaPlayer.stop();
    }
    private static void setLoop(MediaPlayer mediaPlayer){
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
                mediaPlayer.play();
            }
        });
    }
}

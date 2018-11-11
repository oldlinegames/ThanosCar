package org.firstinspires.ftc.teamcode;
import android.content.Context;
import android.media.MediaPlayer;

import java.io.IOException;

public class apagando {
    //The player handling the audio
    private static MediaPlayer mediaPlayer1 = null;
    //Start the wubs
    public static void start(Context context) {
        if (mediaPlayer1 == null) mediaPlayer1 = MediaPlayer.create(context, R.raw.apagar);
        mediaPlayer1.seekTo(0);
        mediaPlayer1.start();
    }
    //Stop the wubs
    public static void stop() {
        if (mediaPlayer1 != null) {
            mediaPlayer1.stop();
            try { mediaPlayer1.prepare(); }
            catch (IOException e) {}
        }
    }
}

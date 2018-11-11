package org.firstinspires.ftc.teamcode;
import android.content.Context;
import android.media.MediaPlayer;

import java.io.IOException;

public class OmaeTron {
    //The player handling the audio
    private static MediaPlayer mediaPlayer = null;
    //Start the wubs
    public static void start(Context context) {
        if (mediaPlayer == null) mediaPlayer = MediaPlayer.create(context, R.raw.omae);
        mediaPlayer.seekTo(0);
        mediaPlayer.start();
    }
    //Stop the wubs
    public static void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            try { mediaPlayer.prepare(); }
            catch (IOException e) {}
        }
    }
}

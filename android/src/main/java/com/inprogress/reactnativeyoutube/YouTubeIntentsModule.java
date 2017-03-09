package com.inprogress.reactnativeyoutube;

import android.app.Activity;
import android.content.Intent;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.google.android.youtube.player.YouTubeIntents;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

public class YouTubeIntentsModule extends ReactContextBaseJavaModule
{
    public static final String NAME = "YouTubeIntentsModule";

    public YouTubeIntentsModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void play(String videoId)
    {
        Activity activity = getCurrentActivity();
        if (activity != null)
        {
            Intent intent = YouTubeIntents.createPlayVideoIntent(getReactApplicationContext(), videoId);
            activity.startActivity(intent);
        }
    }

    @ReactMethod
    public void playStandalone(String apiKey, String videoId, boolean autoStart, boolean inLightbox)
    {
        Activity activity = getCurrentActivity();
        if (activity != null)
        {
            Intent intent = YouTubeStandalonePlayer.createVideoIntent(activity, apiKey, videoId, 0, autoStart, inLightbox);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
        }
    }
}

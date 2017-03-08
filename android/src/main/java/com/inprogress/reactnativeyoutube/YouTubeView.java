package com.inprogress.reactnativeyoutube;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.google.android.youtube.player.YouTubePlayerFragment;

public class YouTubeView extends RelativeLayout {

    private YouTubePlayerController youtubeController;
    private YouTubePlayerFragment youTubePlayerFragment;
    private String apiKey;

    private final Runnable measureAndLayout = new Runnable() {
        @Override
        public void run() {
        Log.d("3SC", "measure " + getId());
            measure(MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(getHeight(), MeasureSpec.EXACTLY));
            layout(getLeft(), getTop(), getRight(), getBottom());
        }
    };

    public YouTubeView(ReactContext context) {
        super(context);
        init();
    }

    private ReactContext getReactContext() {
        return (ReactContext)getContext();
    }

    public void init() {
        Log.d("3SC", "init");
        ViewGroup.LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        setLayoutParams(layoutParams);
        youtubeController = new YouTubePlayerController(YouTubeView.this);
    }

    @Override
    protected void onAttachedToWindow()
    {
        Log.d("3SC", "onAttachedToWindow " + getId());
        super.onAttachedToWindow();
        FragmentManager fragmentManager = getReactContext().getCurrentActivity().getFragmentManager();
        youTubePlayerFragment = (YouTubePlayerFragment) fragmentManager.findFragmentById(getId());
        if (youTubePlayerFragment == null)
        {
            youTubePlayerFragment = YouTubePlayerFragment.newInstance();
            fragmentManager.beginTransaction().add(getId(), youTubePlayerFragment).commit();
            setApiKey(apiKey);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        FragmentManager fragmentManager = getReactContext().getCurrentActivity().getFragmentManager();
        if (!fragmentManager.isDestroyed())
        {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.remove(youTubePlayerFragment);
            ft.commitAllowingStateLoss();
        }
        youTubePlayerFragment = null;
        super.onDetachedFromWindow();
    }

    public void seekTo(int second) {
        youtubeController.seekTo(second);
    }


    public void playerViewDidBecomeReady() {
        WritableMap event = Arguments.createMap();
        ReactContext reactContext = (ReactContext) getContext();
        event.putInt("target", getId());
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "ready", event);
    }


    public void didChangeToState(String param) {
        if (!("videoStarted".equals(param) || "buffering".equals(param) || "playing".equals(param))) {
            post(measureAndLayout);
        }
        Log.d("3SC", "didChangeToState " + param + ": " + getId());
        WritableMap event = Arguments.createMap();
        event.putString("state", param);
        event.putInt("target", getId());
        ReactContext reactContext = (ReactContext) getContext();
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "state", event);
    }


    public void didChangeToQuality(String param) {
        WritableMap event = Arguments.createMap();
        event.putString("quality", param);
        event.putInt("target", getId());
        ReactContext reactContext = (ReactContext) getContext();
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "quality", event);
    }


    public void didPlayTime(String current, String duration) {
        WritableMap event = Arguments.createMap();
        event.putString("currentTime", current);
        event.putString("duration", duration);
        event.putInt("target", getId());
        ReactContext reactContext = (ReactContext) getContext();
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "progress", event);
    }


    public void receivedError(String param) {
        WritableMap event = Arguments.createMap();
        ReactContext reactContext = (ReactContext) getContext();
        event.putString("error", param);
        event.putInt("target", getId());
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "error", event);
    }


    public void setVideoId(String str) {
        youtubeController.setVideoId(str);
    }

    public void setInline(Boolean bool) {
        youtubeController.setPlayInline(bool);
    }

    public void setShowInfo(Boolean bool) {
        youtubeController.setShowInfo(bool);
    }

    public void setModestbranding(Boolean bool) {
        youtubeController.setModestBranding(bool);
    }

    public void setControls(Integer nb) {
        youtubeController.setControls(nb);
    }

    public void setPlay(Boolean bool) {
        youtubeController.setPlay(bool);
    }

    public void setHidden(Boolean bool) {
        youtubeController.setHidden(bool);
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
        if (youTubePlayerFragment != null) {
            youTubePlayerFragment.initialize(apiKey, youtubeController);
        }
    }

    public void setLoop(Boolean loop) {
        youtubeController.setLoop(loop);
    }

    public void setRelated(Boolean related) {
        youtubeController.setRelated(related);
    }

    public void setFullscreen(Boolean bool) {
        youtubeController.setFullscreen(bool);
    }
}

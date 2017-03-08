package com.inprogress.reactnativeyoutube;

import android.support.annotation.Nullable;
import android.util.Log;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.HashMap;
import java.util.Map;

public class YouTubeThumbnailManager extends SimpleViewManager<YouTubeThumbnailView>
{
	public static final String REACT_CLASS = "ReactYouTubeThumbnail";

    public static final String PROP_VIDEO_ID = "videoId";
    public static final String PROP_API_KEY = "apiKey";

	private Map<YouTubeThumbnailView, YouTubeThumbnailController> viewControllers = new HashMap<>();

	@Override
	public String getName()
	{
		return REACT_CLASS;
	}

	@Override
	protected YouTubeThumbnailView createViewInstance(ThemedReactContext themedReactContext)
	{
		YouTubeThumbnailView thumbnailView = new YouTubeThumbnailView(themedReactContext);
		Log.d("3SC", "createViewInstance: " + thumbnailView.hashCode());
		YouTubeThumbnailController thumbnailController = new YouTubeThumbnailController(thumbnailView);
		viewControllers.put(thumbnailView, thumbnailController);
		return thumbnailView;
	}

	@Override
	public void onDropViewInstance(YouTubeThumbnailView view)
	{
		Log.d("3SC", "onDropViewInstance: " + view.hashCode());
		YouTubeThumbnailController controller = viewControllers.remove(view);
		if (controller != null)
		{
			controller.release();
		}
		super.onDropViewInstance(view);
	}

	@ReactProp(name = PROP_API_KEY)
	public void setApiKey(YouTubeThumbnailView view, @Nullable String apiKey)
	{
		Log.d("3SC", "setApiKey: " + view.hashCode());
		YouTubeThumbnailController controller = viewControllers.get(view);
		if (controller != null)
		{
			controller.setApiKey(apiKey);
		}
	}

	@ReactProp(name = PROP_VIDEO_ID)
	public void setVideoId(YouTubeThumbnailView view, @Nullable String videoId)
	{
		Log.d("3SC", "setVideoId: " + view.hashCode());
		YouTubeThumbnailController controller = viewControllers.get(view);
		if (controller != null)
		{
			controller.setVideoId(videoId);
		}
	}
}

package com.inprogress.reactnativeyoutube;

import android.util.Log;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

public class YouTubeThumbnailController
{
	private YouTubeThumbnailView view;
	private YouTubeThumbnailLoader loader;
	private String videoId;

	public YouTubeThumbnailController(YouTubeThumbnailView view)
	{
		this.view = view;
	}

	public void release()
	{
		Log.d("3SC", "release: " + view.hashCode());
		if (loader != null)
		{
			loader.release();
		}
	}

	public void setApiKey(String apiKey)
	{
		view.initialize(apiKey, new YouTubeThumbnailView.OnInitializedListener()
		{
			@Override
			public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader)
			{
				Log.d("3SC", "onInitializationSuccess: " + view.hashCode());
				loader = youTubeThumbnailLoader;
				if (videoId != null)
				{
					loader.setVideo(videoId);
				}
			}

			@Override
			public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult)
			{
				Log.e("3SC", "YouTubeThumbnailController.onInitializationFailure: " + youTubeInitializationResult);
			}
		});
	}

	public void setVideoId(String videoId)
	{
		this.videoId = videoId;
		if (loader != null)
		{
			loader.setVideo(videoId);
		}
	}
}

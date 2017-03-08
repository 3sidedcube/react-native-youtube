/**
 * @providesModule YouTube
 * @flow
 */
'use strict';

import React, {PropTypes} from "react";
import {requireNativeComponent, Platform, StyleSheet, Text, View} from 'react-native';


const YouTube = React.createClass({

	propTypes: {
		style: View.propTypes.style,
		videoId: PropTypes.string.isRequired,
		apiKey: PropTypes.string.isRequired,
		playsInline: PropTypes.bool,
		showinfo: PropTypes.bool,
		modestbranding: PropTypes.bool,
		controls: PropTypes.oneOf([0, 1, 2]),
		origin: PropTypes.string,
		play: PropTypes.bool,
		rel: PropTypes.bool,
		fs: PropTypes.bool,
		hidden: PropTypes.bool,
		onReady: PropTypes.func,
		onChangeState: PropTypes.func,
		onChangeQuality: PropTypes.func,
		onError: PropTypes.func, // TODO: Make this work on android, the native player doesn't support it right now...
		onProgress: PropTypes.func,
		loop: PropTypes.bool, ...View.propTypes,
	},

	getDefaultProps() {
		return {
			loop: false
		}
	},

	_onReady(event) {
		this.props.onReady && this.props.onReady(event.nativeEvent);
	},

	_onChangeState(event) {
		this.props.onChangeState && this.props.onChangeState(event.nativeEvent);
	},

	_onChangeQuality(event) {
		this.props.onChangeQuality && this.props.onChangeQuality(event.nativeEvent);
	},

	_onError(event) {
		this.props.onError && this.props.onError(event.nativeEvent);
	},

	_onProgress(event) {
		this.props.onProgress && this.props.onProgress(event.nativeEvent);
	},


	render() {
		return (
			<RCTYouTube
				{...this.props}
				onReady={this._onReady}
				// These override and delegate to the this.props functions
				onYoutubeVideoReady={this._onReady}
				onYoutubeVideoChangeState={this._onChangeState}
				onYoutubeVideoChangeQuality={this._onChangeQuality}
				onYoutubeVideoError={this._onError}
				onYoutubeVideoProgress={this._onProgress}
			/>
		);
	}
});

const RCTYouTube = requireNativeComponent('ReactYouTube', YouTube, {
	nativeOnly: {
		onYoutubeVideoError: true,
		onYoutubeVideoReady: true,
		onYoutubeVideoChangeState: true,
		onYoutubeVideoChangeQuality: true,
		onYoutubeVideoProgress: true,
	}
});

module.exports = YouTube;

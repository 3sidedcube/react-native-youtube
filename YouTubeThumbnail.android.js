/**
 * @providesModule YouTubeThumbnail
 * @flow
 */
'use strict';

import React from "react";
import {requireNativeComponent, Platform, StyleSheet, Text, View, ViewPropTypes} from 'react-native';
import PropTypes from "prop-types";
import createReactClass from "create-react-class";

const YouTubeThumbnail = createReactClass({

	propTypes: {
		...ViewPropTypes,
		style: ViewPropTypes.style,
		videoId: PropTypes.string.isRequired,
		apiKey: PropTypes.string.isRequired
	},

	render() {
		return (
			<RCTYouTubeThumbnail
				{...this.props}
			/>
		);
	}
});

const RCTYouTubeThumbnail = requireNativeComponent('ReactYouTubeThumbnail', YouTubeThumbnail);

module.exports = YouTubeThumbnail;

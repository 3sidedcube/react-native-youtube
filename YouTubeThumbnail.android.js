/**
 * @providesModule YouTubeThumbnail
 * @flow
 */
'use strict';

import React, {PropTypes} from "react";
import {requireNativeComponent, Platform, StyleSheet, Text, View} from 'react-native';


const YouTubeThumbnail = React.createClass({

	propTypes: {
		...View.propTypes,
		style: View.propTypes.style,
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

import YouTube from './YouTube';
import YouTubeThumbnail from './YouTubeThumbnail';
import { NativeModules } from 'react-native';
const YouTubeIntents = NativeModules.YouTubeIntentsModule;

module.exports = {YouTube, YouTubeIntents, YouTubeThumbnail};

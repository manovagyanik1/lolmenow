/**
 * Created by madki on 06/05/17.
 */
import { combineReducers } from 'redux'
import feed from "./feed";

const app = combineReducers({
  feed
});

export const getFeed = (state) => state.feed;

export default app

/**
 * Created by madki on 06/05/17.
 */
import { combineReducers } from 'redux'
import feed from "./feed";
import comments from "./comments";

const app = combineReducers({
  feed,
  comments
});

export const getFeed = (state) => state.feed;
export const getComments = (state) => state.comments;

export default app

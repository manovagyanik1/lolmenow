/**
 * Created by madki on 29/06/17.
 */
import {getComments, getFeed} from "./apis";

export const ActionTypes = {
  FETCH_FEED: "fetch_feed",
  RECEIVE_FEED: "receive_feed",
  ERROR_FEED: "error_feed",
  FETCH_COMMENTS: "fetch_comments",
  RECEIVE_COMMENTS: "receive_comments",
  ERROR_COMMENTS: "error_comments"
};

export const fetchFeed = () => (dispatch) => {
  dispatch({
    type: ActionTypes.FETCH_FEED
  });
  getFeed()
    .then(response => response.json())
    .then(data => {
      dispatch({
        type: ActionTypes.RECEIVE_FEED,
        data
      })
    })
    .catch(error => dispatch({
      type: ActionTypes.ERROR_FEED,
      error
    }))
};


export const fetchComments = (postId) => (dispatch) => {
  dispatch({
    type: ActionTypes.FETCH_COMMENTS
  });
  getComments(postId)
    .then(response => response.json())
    .then(data => dispatch({
      type: ActionTypes.RECEIVE_COMMENTS,
      data
    }))
    .catch(error => dispatch({
      type: ActionTypes.ERROR_COMMENTS,
      error
    }));
};

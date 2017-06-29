/**
 * Created by madki on 24/05/17.
 */

import {ActionTypes} from "../actions";
const feed = (prevState = {}, action) => {
  switch (action.type) {
    case ActionTypes.RECEIVE_FEED:
      console.log("receive feed");
      return action.data;
    default:
      return prevState;
  }
};

export default feed;
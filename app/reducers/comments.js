/**
 * Created by madki on 24/05/17.
 */
import {ActionTypes} from "../actions";

const comments = (prevState = {}, action) => {
  switch (action.type) {
    case ActionTypes.RECEIVE_COMMENTS:
      return action.data.items;
    default:
      return prevState;
  }
};

export default comments;
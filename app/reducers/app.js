/**
 * Created by madki on 06/05/17.
 */
import { combineReducers } from 'redux'

const name = (state = "", action) => {
  switch (action.type) {
    case "change_name":
      return action.value;
    default:
      return state;
  }
};

const app = combineReducers({
  name
});

export default app

/**
 * Created by madki on 29/06/17.
 */
import {feed} from "./samples";

export const getFeed = () => {
  return Promise.resolve({
    json() {
      return Promise.resolve(feed);
    }
  })
  // return fetch("https://quicknodeserver.herokuapp.com/feed");
};

export const getComments = (postId) => {
  return fetch("https://quicknodeserver.herokuapp.com/comment");
};

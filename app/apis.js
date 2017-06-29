/**
 * Created by madki on 29/06/17.
 */

export const getFeed = () => {
  return fetch("https://quicknodeserver.herokuapp.com/feed");
};

export const getComments = (postId) => {
  return fetch("https://quicknodeserver.herokuapp.com/comment");
};

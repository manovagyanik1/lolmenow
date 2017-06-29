/**
 * Created by madki on 26/05/17.
 */
import React from 'react';
import {Router, Scene} from "react-native-router-flux";
import FeedScreen from "./FeedScreen";
import CommentsScreen from "./CommentsScreen";

const Main = () => (
  <Router>
    <Scene key="root">
      <Scene
        key="feed"
        component={FeedScreen}
        title="Feed"
        initial
      />
    </Scene>
    <Scene
      key="comments"
      direction="vertical"
      component={CommentsScreen}
      title="Modal"
      hideNavBar
    />
  </Router>
);

export default Main;
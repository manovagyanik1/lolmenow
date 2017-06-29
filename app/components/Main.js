/**
 * Created by madki on 26/05/17.
 */
import React, {Component} from 'react';
import {Router, Scene} from "react-native-router-flux";
import FeedScreen from "./FeedScreen";
import CommentsScreen from "./CommentsScreen";
import {connect} from "react-redux";
import {ActionTypes, fetchFeed} from "../actions";

class Main extends Component {
  componentDidMount() {
    fetchFeed()(this.props.dispatch);
  }

  render() {
    return (
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
          title="Comments"
          hideNavBar
        />
      </Router>
    );
  }

}

Main = connect()(Main);

export default Main;
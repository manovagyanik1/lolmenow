/**
 * Created by madki on 29/06/17.
 */
import React, {Component} from "react";
import {
  StyleSheet,
  Text,
  View,
} from 'react-native';
import {Actions} from "react-native-router-flux";
import {getFeed} from "../reducers/app";
import * as actions from "../actions";
import {connect} from "react-redux";
import PropType from "prop-types";

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});

let FeedScreen = ({ feed, fetchComments }) => {
  console.log(feed);
  return (
    <View style={styles.container}>
      <Text style={styles.welcome}>Feed</Text>
      <Text
        style={styles.instructions}
        onPress={() => {
          fetchComments(1);
          Actions.comments()
        }} // New Code
      >
        Go to comments
      </Text>
    </View>
  );
};

const mapStateToProps = (state) => {
  return {
    feed: getFeed(state)
  }
};

FeedScreen = connect(mapStateToProps, actions)(FeedScreen);

FeedScreen.propTypes = {
  feed: PropType.object
};

export default FeedScreen;

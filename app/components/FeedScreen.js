/**
 * Created by madki on 29/06/17.
 */
import React, {Component} from "react";
import {
  StyleSheet,
  Text,
  View,
  FlatList
} from 'react-native';
import {Actions} from "react-native-router-flux";
import {getFeed} from "../reducers/app";
import {connect} from "react-redux";
import PropType from "prop-types";
import FeedCard from "./FeedCard";
import {fetchComments} from "../actions";

const styles = StyleSheet.create({
  container: {
    flex: 1,
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

let FeedScreen = ({feed, onItemClick}) => {
  console.log(feed);
  return (
    <FlatList
      style={styles.list}
      data={feed}
      renderItem={({item}) => {
        return <FeedCard
          card={item}
          onCommentClick={onItemClick}
          onLikeClick={() => console.log("like clicked")}
          onShareClick={() => console.log("share clicked")}
        />
      }}
      keyExtractor={(card, index) => index}
    />
  );
};

const mapStateToProps = (state) => {
  return {
    feed: getFeed(state)
  }
};

const mapDispatchToProps = (dispatch) => {
  return {
    onItemClick(id) {
      fetchComments(id)(dispatch);
      Actions.comments();
    }
  }
};

FeedScreen = connect(mapStateToProps, mapDispatchToProps)(FeedScreen);

FeedScreen.propTypes = {
  feed: PropType.object
};

export default FeedScreen;

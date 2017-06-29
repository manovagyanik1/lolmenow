/**
 * Created by madki on 29/06/17.
 */
import React, {Component} from "react";
import {
  FlatList,
  StyleSheet,
  Text,
  View,
} from 'react-native';
import CommentCard from "./CommentCard";
import {connect} from "react-redux";
import {getComments} from "../reducers/app";

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

let CommentsScreen = ({comments}) => {
  console.log(comments);
  return (
    <FlatList
      style={styles.list}
      data={comments}
      renderItem={({item}) => {
        return <CommentCard
          comment={item}
        />
      }}
      keyExtractor={(comment, index) => index}
    />
  );
};

const mapStateToProps = (state) => {
  return {
    comments: getComments(state)
  }
};

const mapDispatchToProps = (dispatch) => {

};

CommentsScreen = connect(mapStateToProps)(CommentsScreen);

export default CommentsScreen;

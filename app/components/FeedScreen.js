/**
 * Created by madki on 29/06/17.
 */
import React, {Component} from "react";
import {
  StyleSheet,
  Text,
  View,
} from 'react-native';

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

const FeedScreen = () => (
  <View style={styles.container}>
    <Text style={styles.welcome}>Hello</Text>
  </View>
);

export default FeedScreen;

/**
 * Created by madki on 26/05/17.
 */
import React from 'react';
import {
  StyleSheet,
  Text,
  View,
} from 'react-native';
import { connect } from 'react-redux';

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

const mapStateToProps = (state) => ({
  name: state.name
});

const mapDispatchToProps = (dispatch) => ({
  onTextChange: (text) => dispatch({
    type: "change_name",
    value: text
  })
});

let Main = ({ name, onTextChange }) => (
  <View style={styles.container}>
    <Text style={styles.welcome}>Hello</Text>
  </View>
);

Main =  connect(mapStateToProps, mapDispatchToProps)(Main);

export default Main;
/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */
import React from 'react';
import {
  AppRegistry
} from 'react-native';
import configureStore from "./app/configureStore";
import Root from "./app/components/Root";

const store = configureStore();
const App = () => <Root store={store}/> ;

AppRegistry.registerComponent('lolmenow', () => App);


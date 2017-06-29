import { createStore, applyMiddleware } from 'redux';
import logger from 'redux-logger';
import app from './reducers/app';


const configureStore = () => {
  const middlewares = [];
  if (process.env.NODE_ENV !== 'production') {
    middlewares.push(logger);
  }

  return createStore(
    app,
    applyMiddleware(...middlewares)
  );
};


export default configureStore;

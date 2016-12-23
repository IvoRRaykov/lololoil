/* eslint-disable import/default */
import 'babel-polyfill';
import React from 'react';
import ReactDom from 'react-dom';
import {Router, browserHistory} from 'react-router'
import {syncHistoryWithStore} from 'react-router-redux';
import ConfigureStore from './services/store/configure-store';
import {Provider} from 'react-redux';
import routes from './router/routes.jsx';
import * as securityActions from './services/actions/security.actions';
import 'font-awesome/css/font-awesome.css';
import 'simple-line-icons/css/simple-line-icons.css';
import '../assets/styles/ProxiadPortalSystem.scss';
import 'tether/dist/js/tether.min.js';
import 'bootstrap/dist/js/bootstrap.min.js';

const store = ConfigureStore();
const history = syncHistoryWithStore(browserHistory, store)

// Try silent authentication
store.dispatch(securityActions.refreshAuthentication());

ReactDom.render(
  <Provider store={store}>
    <Router history={history} routes={routes}/>
  </Provider>,
  document.getElementById('proxiad-extranet-app')
);

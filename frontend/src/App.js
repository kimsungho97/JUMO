import React from "react";
import { BrowserRouter, Route } from "react-router-dom";
import { config } from "dotenv";
import { RecoilRoot } from 'recoil';
import { Chart, ChartList, Home, Login, MyPage, Signup, Simulate } from "./pages";
import { RouteIf } from "./Route";
import './reset.css';

function App() {
  config();
  
  return (
    <RecoilRoot>
      <BrowserRouter>
        <Route exact path="/" component={Home} />
        <Route exact path="/login" component={Login} />
        <Route exact path="/signup" component={Signup} />
        <RouteIf exact path="/userinfo" component={MyPage}/>
        <RouteIf exact path="/chartList" component={ChartList}/>
        <RouteIf exact path="/simulate" component={Simulate}/>
        <RouteIf path="/chart" component={Chart}/>
      </BrowserRouter>
    </RecoilRoot>
  );
}

export default App;

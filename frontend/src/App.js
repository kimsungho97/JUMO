import React from "react";
import { BrowserRouter, Route } from "react-router-dom";
import { config } from "dotenv";
import { RecoilRoot, useRecoilValue } from 'recoil';
import './reset.css';
import { Chart, ChartList, Home, Login, Signup } from "./pages";
import Simulate from "./pages/simulate";
import { RouteIf } from "./Route";

function App() {
  config();
  
  return (
    <RecoilRoot>
      <BrowserRouter>
        <Route exact path="/" component={Home} />
        <Route exact path="/login" component={Login} />
        <Route exact path="/signup" component={Signup}/>
        <RouteIf exact path="/chartList" component={ChartList}/>
        <RouteIf exact path="/simulate" component={Simulate}/>
        <RouteIf path="/chart" component={Chart}/>
      </BrowserRouter>
    </RecoilRoot>
  );
}

export default App;

import React from "react";
import { BrowserRouter, Route } from "react-router-dom";
import { config } from "dotenv";
import { RecoilRoot } from 'recoil';
import './reset.css';
import { Chart, ChartList, Home, Login, Signup } from "./pages";
import Simulate from "./pages/simulate";

function App() {
  config();
  return (
    <RecoilRoot>
      <BrowserRouter>
        <Route exact path="/" component={Home} />
        <Route exact path="/login" component={Login} />
        <Route exact path="/signup" component={Signup}/>
        <Route exact path="/chartList" component={ChartList} />
        <Route exact path="/simulate" component={Simulate}/>
        <Route path="/chart" component={Chart}/>
      </BrowserRouter>
    </RecoilRoot>
  );
}

export default App;

import React from "react";
import ReactDOM from "react-dom/client";
import LoginSignUp from "./Components/LoginSignUp/LoginSignUp";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import HomePage from "./Components/HomePage/HomePage";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <Router>
      <Routes>
        <Route path="/" element={<LoginSignUp />} />
        <Route path="/homepage" element={<HomePage />} />
      </Routes>
    </Router>
  </React.StrictMode>
);

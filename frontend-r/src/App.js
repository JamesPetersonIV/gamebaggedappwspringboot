import logo from './logo.svg';
import './App.css';
import {AuthProvider} from "./context/AuthContext";
import {Route, Routes} from "react-router-dom";
import Gbindex from "./pages/Gbindex";
import Register from "./pages/Register";
import SignIn from "./pages/SignIn";

function App() {
  return (
      <AuthProvider>
        <div className="App">
          <Routes>
            <Route path="index" exact element={<Gbindex/>}/>
            <Route path="signup" exact element={<Register/>}/>
            <Route path="signin" exact element={<SignIn/>}/>
          </Routes>
        </div>
      </AuthProvider>
  );
}

export default App;

import logo from './logo.svg';
import './App.css';
import {AuthProvider} from "./context/AuthContext";
import {Route, Routes} from "react-router-dom";
import Gbindex from "./pages/Gbindex";
import Register from "./pages/Register";
import SignIn from "./pages/SignIn";
import Marketplace from "./pages/Marketplace";

function App() {
  return (
      <AuthProvider>
        <div className="App">
          <Routes>
            <Route path="index" exact element={<Gbindex/>}/>
            <Route path="signup" exact element={<Register/>}/>
            <Route path="signin" exact element={<SignIn/>}/>

              {/*Admin Routes*/}

              {/*Bagger Routes*/}

              {/*Customer Routes*/}
              <Route path="market" exact element={<Marketplace/>}/>
          </Routes>
        </div>
      </AuthProvider>
  );
}

export default App;

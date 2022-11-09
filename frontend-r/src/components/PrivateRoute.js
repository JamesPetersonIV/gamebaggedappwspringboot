import React, {useContext} from 'react';
import {AuthContext} from "../Context/AuthContext";
import {Navigate} from "react-router-dom";

function PrivateRoute({children})
{
    //test if user is logged in and exits
    //AuthContext; grabs info from when someone signs in, puts info in current user
    //
    const {currentUser} = useContext(AuthContext);
    //if currnt user is populated its gonna set item in locsl under userId
    if(currentUser != null && currentUser.length > 0 )
        localStorage.setItem("user", JSON.stringify(currentUser));
    //three statement; before and after : true or false;  if user is exits go to page, if not return to login
    return currentUser ? children : <Navigate to="/login" replace={true} />

}
export default PrivateRoute;
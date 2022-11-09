import React, {useContext} from 'react'
import {Navigate} from "react-router-dom";


function AdminRoute({children})
{
    //gettin from loacl storage and into placing userid info
    let localStorageItem = localStorage.getItem("user")

    //parsing;splitting/going inside localStorage and taking out  field info
    return (JSON.parse(localStorageItem).role === "admin") ? children : <Navigate to={"/login"}/>
}

export default AdminRoute
import React, {useContext} from 'react'
import {Navigate} from "react-router-dom";


function CustomerRoute({children})
{
    //gettin from loacl storage and into placing userid info
    let localStorageItem = localStorage.getItem("user")

    //parsing;splitting/going inside localStorage and taking out  field info
    return (JSON.parse(localStorageItem).role === "customer") ? children : <Navigate to={"/login"}/>
}

export default CustomerRoute
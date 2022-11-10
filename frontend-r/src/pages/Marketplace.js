import React, {useEffect, useState} from 'react';
import $ from "jquery";
import axios from "axios";
import {Link, Navigate} from "react-router-dom";
import ProductList from "../components/ProductList";

function Marketplace(props) {
    const [categories, setCategories] = useState([]);

    let firebaseResponse = localStorage.getItem("firebaseResponse");
    firebaseResponse = (firebaseResponse ? JSON.parse(firebaseResponse): "");

    useEffect(()=>{
        $("body").removeClass("login");
        let headers = {"Authorization": "Bearer " + firebaseResponse.token}
        axios.get("http://localhost:8080/api/category",{headers: headers})
            .then((response) =>{
                console.log(response);
                setCategories(response.data);
            })
            .catch((err)=>{
                console.log(err);

            })
    },[])
    return (
        <>
            <ProductList/>
            <div className="container">
                <div className="row row-cols-lg-4 row-cols-sm-2">

                    {
                        categories.map((category)=>{
                            let obj = {name: "hello"};
                            return (
                                <div className="col g-4" key={category.id}>
                                    <div className="card"  >
                                        <img src="https://via.placeholder.com/150" className="card-img-top" alt={category.name} />
                                        <div className="card-body">
                                            <p className="card-text">{category.description}</p>

                                        </div>
                                    </div>
                                </div>
                            )
                        })
                    }
                </div>
            </div>

        </>
    );
}

export default Marketplace;
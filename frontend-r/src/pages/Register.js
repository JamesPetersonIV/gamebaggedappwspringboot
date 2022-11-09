import React, {useContext, useEffect, useState, useRef} from "react"
import {AuthContext} from '../context/AuthContext'
import {useNavigate} from "react-router-dom";
//import {createUserWithEmailAndPassword, getAuth, onAuthStateChanged} from 'firebase/auth'

//bootstrap
import Button from 'react-bootstrap/Button'
import Form from 'react-bootstrap/Form'
//import {Card} from "react-bootstrap"


function Register() {

    const context = useContext(AuthContext);
    const emailRef = useRef("");
    const passwordRef = useRef("");
    const [errors, setErrors] = useState([]);
    let navigate = useNavigate();


    useEffect(() => {
        window.document.body.classList.add("login");
    })

    async function handleSubmit(event) {
        event.preventDefault();

        await context.signUp(emailRef.current.value, passwordRef.current.value);

        if (context.currentUser != null) {
            context.setErrors(null, false);
            navigate("signin") // change this route to whatever the homepage is going to be

        } else {
            setErrors(context.errors);
        }
        }

        return (
            <div>
                <h2>Sign Up</h2>
                <Form onSubmit={handleSubmit}>
                    <Form.Group className="mb-3" controlId="formBasicEmail">
                        <Form.Label>Email address</Form.Label>
                        <Form.Control
                            type="email"
                            placeholder="Enter email"
                            required ref={emailRef}
                        />
                        <Form.Text className="text-muted">
                            We'll never share your email with anyone else.
                        </Form.Text>
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="formBasicPassword">
                        <Form.Label>Password</Form.Label>
                        <Form.Control
                            type="password"
                            placeholder="Password"
                            required ref={passwordRef}
                        />
                    </Form.Group>
                    <Button variant="primary" type={"submit"}>
                        Submit
                    </Button>
                </Form>
            </div>

        );
}

export default Register

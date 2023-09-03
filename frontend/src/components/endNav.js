import React, {useEffect, useState} from "react";
import axios from "axios";
import Dropdown from 'react-bootstrap/Dropdown';
import DropdownButton from 'react-bootstrap/DropdownButton';

function LoginButton() {
    return (
        <div className="d-flex flex-row-reverse" id="endNav">
            <a className="btn btn-primary " id="login" href="/login" role="button">Google Login</a>
        </div>
    )
}

function DropDownButton() {
    const [account, setAccount] = useState("Email");
    const getAccount = () => {
        axios.get('account').then(function (response) {
            console.log(response);
            setAccount(response.data);
        }).catch(function (error) {
            // console.log(error);
        })
    }
    useEffect(() => {
        getAccount()
        console.log(account.email);
    }, [account.email])

    return (
        <>
            <div className="d-flex text-end">
                <DropdownButton className="col" id="dropdown-basic-button" title= {account.email}>
                    <Dropdown.Item href={'/'}>Book Search</Dropdown.Item>
                    <Dropdown.Item href={'/bookshelf'}>Bookshelf</Dropdown.Item>
                    <Dropdown.Item href={'/logout'}>Logout</Dropdown.Item>
                </DropdownButton>
            </div>
        </>
    )
}


function EndNav() {
    const [loggedIn, setLoggedIn] = useState("false");
    // let end;
    React.useEffect(() => {
        if (loggedIn) {
            axios.get('heartbeat').then(function (response) {
                console.log(response);
                setLoggedIn(response.data);
            }).catch(function (error) {
                // console.log(error);
            })
        }
    })

    return loggedIn ? <DropDownButton/> : <LoginButton/>;
}


export {EndNav};
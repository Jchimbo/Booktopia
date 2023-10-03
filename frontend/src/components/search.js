import {BookData, LoadingState} from "../App";
import React, {useContext, useState} from "react";
import { Rating } from 'react-simple-star-rating';
import {Spinner} from "./spinner.js";
import axios from "axios";
import {Toast} from "react-bootstrap";

function SearchResult(){
  let bookInfo = useContext(BookData);
    console.log("Book Info: ");
    console.log(bookInfo);
    if (bookInfo !== "{}"){
        return(
            <BookData.Provider value={bookInfo}>
                <Result />
            </BookData.Provider>
        )
    }else if(bookInfo === "{}"){
        return <NoResult />
    }else {
        return (
            <div>{}</div>
        )
    }
}
function Result(){
    let bookInfo = useContext(BookData);
    let isLoading = useContext(LoadingState);
    console.log("Book Info(Result): ");
    console.log(bookInfo);
    const [rating, setRating] = useState(0)
    // Catch Rating value
    const handleRating = (rate) => {
        setRating(rate)
    }
    // Optinal callback functions
    const onPointerEnter = () => console.log('Enter')
    const onPointerLeave = () => console.log('Leave')
    const onPointerMove = (value, index) => console.log(value, index)

    return(
        <div className="p-5 m-5 d-flex flex-row" id="book_page" >
            <div className= "d-flex flex-column" id="cover">
                {
                    isLoading ? (<Spinner/>) :(
                        <img className="rounded float-left" src={bookInfo.cover} width={215} height={322}
                                                     alt={"cover of " + bookInfo.title}/>
                    )
                }
                <div className="pt-3 mx-auto" id="add_button">
                    <BookData.Provider value={bookInfo}>
                        <AddBook/>
                    </BookData.Provider>
                </div>
            </div>

            <div className= "ps-5" id="description">
                <h2>{bookInfo.title}</h2>
                <h3>{bookInfo.author}</h3>
                <Rating
                    onClick={handleRating}
                    onPointerEnter={onPointerEnter}
                    onPointerLeave={onPointerLeave}
                    onPointerMove={onPointerMove}
                    /* Available Props */
                />
                <p>
                    {bookInfo.description}
                </p>
            </div>
        </div>
    )
}

function AddBook(){
    let bookInfo = useContext(BookData);
    console.log("Book Info(Add Book): ");
    console.log(bookInfo);
    const [loggedIn, setLoggedIn] = useState("false");
    const [showToast, setShowToast] = useState(false);
    axios.get('/heartbeat').then(function (response) {
       // console.log(response);
        setLoggedIn(response.data);
    }).catch(function (error) {
        console.log(error);
    })
    const handleClick= () => {
        fetch('/booklist/' + bookInfo.isbn, {
            method: 'POST'
        }).then(() =>
            setShowToast(true));
    }
    const toggleShowA = () => setShowToast(!showToast);

        if (loggedIn) {
            return(
                <div>
                    <button className="btn btn-primary" id="add_book" type="button" onClick={handleClick}>
                        Add Book To Bookshelf
                    </button>
                    <Toast show={showToast} onClose={toggleShowA}>
                        <Toast.Header>
                            <strong className="me-auto">Book Added</strong>
                        </Toast.Header>
                        <Toast.Body>You've added "{bookInfo.title}" to your bookshelf!</Toast.Body>
                    </Toast>
                </div>

            );
        }else {
            return (<div></div>);
        }
}

function NoResult(){
    return (
        <div className="p-5 m-5 "  id="book_page">
            <h2>How to use this site:</h2>
            <p>
                This site allows the searching of books using ISBN 13 serial numbers.
                To search for a book just enter the serial number in the search bar and hit the enter key.
                The page should then update to show you the book's information.
                If you are logged into the site using a google account you can then save a book to your book list.
                Allowing you to store it with it's cover, title and isbn in a shelf like format.
            </p>
        </div>
    );
}

export {SearchResult};



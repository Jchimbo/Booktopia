import {BookData, LoadingState} from "../App";
import {useContext} from "react";
import {Spinner} from "./spinner";

function SearchResult(){
    let bookInfo = useContext(BookData);
    console.log("Book Info " + bookInfo);
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
    return(
        <div class="p-5 m-5 d-flex flex-row" id="book_page" >
            <div id="cover">
                {
                    isLoading ? (<Spinner/>) :(
                        <img className="rounded float-left" src={bookInfo.cover} width={215} height={322}
                                                     alt={"cover of " + bookInfo.title}/>
                    )
                }

            </div>
            <div className="pt-3 mx-auto" id="add_button">
                {/*<AddBook />*/}
            </div>
            <div class= "ps-5" id="description">
                <h2>{bookInfo.title}</h2>
                <h3>{bookInfo.author}</h3>
                <p>
                    {bookInfo.description}
                </p>
            </div>
        </div>
    )
}

function NoResult(){
    return (
        <div class="p-5 m-5 "  id="book_page">
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



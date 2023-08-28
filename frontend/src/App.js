import {serverUrl} from "./components/connect.js";
import {EndNav} from './components/endNav';
import {SearchResult} from "./components/search";
import {createContext, useState} from "react";
import "./App.scss";
let bookJson = "{}";
export const BookData = createContext(bookJson);
export const LoadingState = createContext(false);

function App() {
    const [bData, setbData] = useState("{}");
    const [isLoading , setIsLoading] = useState(false);
    const fetchdata = async (url) =>{
        var xhr = new XMLHttpRequest();
        xhr.open("GET", url, true);
        xhr.responseType = "json";
        xhr.onerror = function (){
            console.log("ERROR " + xhr.response );
            setIsLoading(false);
        }
        xhr.onload = function (){
            let response = xhr.response;
            console.log(response);
            setbData(response);
            let image = new Image();
            image.src = response.cover;
            image.onload = () =>{
                setIsLoading(false);
            }
        };
        xhr.send();
    }
     const handleKeyDown =  (event) => {
         if (event.key === 'Enter') {
             event.preventDefault();
             let url = String(serverUrl + 'book/' + event.target.value);
             setIsLoading(true);
             fetchdata(url);
         }
     };

    return (
        <div id="MainPageDiv">
            <div class="px-5 pt-2 pb-2 d-flex flex-row" styles={{gap: 10 + 'px'}} id="nav">
                <div class="col" id="webName">
                    <h1>Booktopia</h1>
                </div>
                <div>
                    <form className="col" onKeyDown={handleKeyDown}>
                        <div className="row mx-auto" styles="width: 200px;">
                            <input className="form-control" type="text" id="isbn" placeholder="Search Isbn_13"/>
                        </div>
                    </form>
                </div>
                <div class="ps-5 col">
                    <EndNav/>
                </div>
            </div>
            <div id="container" >
                {/*{isLoading ? ( <Spinner/> ) : (<BookData.Provider value={bData}>*/}
                {/*    <SearchResult/>*/}
                {/*</BookData.Provider>) }*/}
                <LoadingState.Provider value={isLoading}>
                    <BookData.Provider value={bData}>
                        <SearchResult/>
                    </BookData.Provider>
                </LoadingState.Provider>
            </div>
            <footer>
                <div class="d-flex align-items-end flex-column  p-5">
                    <div>
                        <h4>Author: Jeremi Chimbo</h4>
                    </div>
                    <div>
                        <h4><a href="mailto:hege@example.com">JeremiChimbo1@gmail.com</a></h4>
                    </div>

                </div>
            </footer>
        </div>
    );
}


// function AddBook(){
//    function handleClick(){
//         axios.get(serverUrl+'heartbeat',)
//             .then(function (response){
//                 var text = response.data
//                 if (text === "true") {
//                     addBookButton.addEventListener("click", function add(){
//                         fetch('http://localhost:8080/booklist/' + inputValue, {
//                             method: 'POST'
//                         }).then(r =>
//                             console.log(r.status))
//                         // alert("Book Sent"));
//                     });
//                 }
//             }).catch(function (error){
//             console.log(error);
//         })}
//     return(
//         <button className="btn btn-primary" id="add_book" type="button" onClick={handleClick}>
//             "Add Book To Book List"
//         </button>
//     )
// }



export default App;

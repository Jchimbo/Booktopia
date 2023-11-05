import {EndNav} from './components/endNav';
import {SearchResult} from "./components/search";
import {createContext, useState} from "react";
import "./App.scss";
import Logo from "./components/logo";
import Footer from "./components/footer";
import {Form} from "react-bootstrap";
let bookJson = "{}";
export const BookData = createContext(bookJson);
export const LoadingState = createContext(false);

function App() {
    const [bData, setbData] = useState("{}");
    const [isLoading , setIsLoading] = useState(false);
      const fetchdata = async (url) =>{
        const response = await fetch(url);
        const data = await response.json();
	console.log("SETTING BDATA: ")
        console.log(bData)
        setbData(data);
        let image = new Image();
        image.src = data.cover;
        image.onload = () =>{
            setIsLoading(false);
        }
    }

	const handleKeyDown =  (event) => {
         if (event.key === 'Enter') {
             event.preventDefault();
             let url = String('/book/' + event.target.value);
             setIsLoading(true);
             fetchdata(url);
         }
     };

    return (
        <div id="MainPageDiv">
            <div class="px-5 pt-2 pb-2 d-flex " styles={{gap: 10 + 'px'}} id="nav">
               <Logo/>
                <div>
             <Form onKeyDown={handleKeyDown}>
                        <Form.Group className="mb-3" controlId="formISBN">
                            <Form.Control type="ISBN" placeholder="Enter ISBN" />
                        </Form.Group>
                    </Form>    
	    </div>
                <div class="ps-5 col">
                    <EndNav/>
                </div>
            </div>
            <div id="container" >
                <LoadingState.Provider value={isLoading}>
                    <BookData.Provider value={bData}>
                        <SearchResult/>
                    </BookData.Provider>
                </LoadingState.Provider>
            </div>
            <footer>
               <Footer/>
            </footer>
        </div>
    );
}

export default App;

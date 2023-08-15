import {nav} from "./nav.js";

nav();
document.getElementById('search').addEventListener('keydown',
    function(event) {
    const keyName =event.key;
    if(keyName === "Enter"){
        event.preventDefault();
        searchIsbn(event.target);
    }
});

function searchIsbn(form) {
    // Remove previous Result
    if(document.getElementById("book_page") != null){
        let elm = document.getElementById("book_page");
        elm.parentNode.removeChild(elm);
    }

    // Add Results to index.html
    const inputValue = form.value;
    fetch('http://localhost:8080/book/' + inputValue, {
        method: 'GET',
    }).then((response) => response.json())
        .then((json) => {
            var bookPageDiv = document.createElement("Div");
            bookPageDiv.setAttribute("class", "p-5 d-flex flex-row");
            bookPageDiv.setAttribute("id", "book_page");
            bookPageDiv.setAttribute("style","background-color: white;");

            // Image
            var coverDiv = document.createElement("div");
            coverDiv.setAttribute("id","cover");
            var img = document.createElement("IMG");
            img.setAttribute("src", json.cover);
            img.setAttribute("width", "215");
            img.setAttribute("height", "322");
            img.setAttribute("alt", "cover of " + json.title)
            img.setAttribute("class","rounded float-left");
            coverDiv.appendChild(img);

            // Button
            var buttonDiv = document.createElement("div");
            buttonDiv.setAttribute("class", "pt-3 mx-auto");
            buttonDiv.setAttribute("style", "width: 200px;");
            var addBookButton = document.createElement("button");
            addBookButton.setAttribute("type", "button");
            addBookButton.setAttribute("class", "btn btn-primary");
            var label = document.createTextNode("Add Book To Book List");
            addBookButton.appendChild(label);
            addBookButton.id = "add_book";
            fetch('http://localhost:8080/heartbeat',
                {
                    method: 'GET',
                }).then((response) => response.text())
                .then((text) => {
                    if (text === "true") {

                        buttonDiv.appendChild(addBookButton);
                        addBookButton.addEventListener("click", function add(){
                            fetch('http://localhost:8080/booklist/' + inputValue, {
                                method: 'POST'
                            }).then(r =>
                                console.log(r.status))
                            // alert("Book Sent"));
                        });
                    }
                })
            coverDiv.appendChild(buttonDiv);
            bookPageDiv.appendChild(coverDiv);

            // Title
            var descriptionDiv = document.createElement("div");
            descriptionDiv.setAttribute("class", "ps-5");
            descriptionDiv.setAttribute("id", "description");

            var title = document.createElement("h2");
            var text = document.createTextNode(json.title);
            title.appendChild(text);
            var author = document.createElement("h3");
            var authName = document.createTextNode(json.title);
            author.appendChild(authName);
            var des = document.createElement("p");
            var desText = document.createTextNode(json.description);

            descriptionDiv.appendChild(title);
            descriptionDiv.appendChild(author);
            des.appendChild(desText);
            descriptionDiv.appendChild(des);
            bookPageDiv.appendChild(descriptionDiv);
            jQuery("#container").append(bookPageDiv);
        });
}


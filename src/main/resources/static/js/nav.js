export function nav(){
    fetch('http://localhost:8080/heartbeat',
        {
            method: 'GET',
        }).then((response) => response.text())
        .then((text) => {
            if (text === "true") {
                fetch('http://localhost:8080/account', {
                    method: 'GET',
                }).then((response) => response.json())
                    .then((json) => {
                            var nameTagDiv = document.createElement("Div");
                            nameTagDiv.setAttribute("id", "nameTag");
                            var email = document.createElement("H1");
                            email.setAttribute("id", "title")
                            var text = document.createTextNode(json.email);
                            email.appendChild(text);
                            nameTagDiv.appendChild(email);
                            var nav = document.createElement("Div");
                            nav.setAttribute("id", "nav");
                            var logout = document.createElement("a");
                            logout.href='http://localhost:8080/logout';
                            logout.text = 'Logout';
                            var bookSearch = document.createElement("a");
                            bookSearch.href='http://localhost:8080/index.html';
                            bookSearch.text = 'Book Search';
                            nav.appendChild(bookSearch)
                            var bookList = document.createElement("a");
                            bookList.href='http://localhost:8080/books.html';
                            bookList.text = 'Book List';
                            nav.appendChild(bookList)
                            nav.appendChild(logout);
                            document.body.prepend(nav);
                            document.body.prepend(nameTagDiv);

                        }
                    )
            }

        })
}

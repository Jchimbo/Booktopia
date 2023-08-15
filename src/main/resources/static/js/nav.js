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
                        jQuery("#login").remove();
                        var endNav = jQuery("#endNav");
                        endNav.attr("class", "d-flex text-end");
                        var divDropDown = document.createElement("div");
                        divDropDown.setAttribute("class","col dropdown");
                        var button = document.createElement("button");
                        button.setAttribute("class", "btn btn-primary dropdown-toggle")
                        button.setAttribute("type", "button");
                        button.setAttribute("id", "dropdownMenuButton");
                        button.setAttribute("data-toggle", "dropdown");
                        button.setAttribute("aria-haspopup", "true");
                        button.setAttribute("aria-expanded", "false");
                        button.textContent =json.email;
                        divDropDown.append(button);
                        var dropDownMenu = document.createElement("div");
                        dropDownMenu.setAttribute("class", "dropdown-menu");
                        dropDownMenu.setAttribute("aria-labelledby","dropdownMenuButton");
                        var bookSearch = document.createElement("a");
                        bookSearch.setAttribute("class","dropdown-item");
                        bookSearch.href='http://localhost:8080/index.html';
                        bookSearch.text = 'Book Search';
                        dropDownMenu.appendChild(bookSearch)
                        var bookList = document.createElement("a");
                        bookList.setAttribute("class","dropdown-item");
                        bookList.href='http://localhost:8080/books.html';
                        bookList.text = 'Book List';
                        dropDownMenu.appendChild(bookList);
                        var logout = document.createElement("a");
                        logout.setAttribute("class","dropdown-item");
                        logout.href='http://localhost:8080/logout';
                        logout.text = 'Logout';
                        dropDownMenu.appendChild(logout);
                        divDropDown.appendChild(dropDownMenu);
                        endNav.append(divDropDown);
                        }
                    )
            }

        })
}

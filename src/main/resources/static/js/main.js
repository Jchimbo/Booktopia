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
                        nav.appendChild(logout);
                        document.body.prepend(nav);
                        document.body.prepend(nameTagDiv);

                    }
                )
        }

    })

function searchIsbn(form) {
    // Remove previous Result
    if(document.getElementById("results") != null){
        let elm = document.getElementById("results");
        elm.parentNode.removeChild(elm);
    }
    // Add Results to index.html
    const inputValue = form.isbn.value;
    fetch('http://localhost:8080/book/' + inputValue, {
        method: 'GET',
    }).then((response) => response.json())
        .then((json) => {
            var div = document.createElement("Div");
            div.setAttribute("id", "results");
            var img = document.createElement("IMG");
            img.setAttribute("src", json.cover);
            img.setAttribute("width", "304");
            img.setAttribute("height", "228");
            img.setAttribute("id", "cover");
            img.setAttribute("alt", "cover of " + json.title)
            div.appendChild(img);
            var title = document.createElement("H1");
            title.setAttribute("id", "title")
            var text = document.createTextNode(json.title);
            title.appendChild(text);
            div.appendChild(title);
            document.body.appendChild(div);
        });
    const stylesheet = document.styleSheets[0];
    let elementRules;

// looping through all its rules and getting your rule
    for(let i = 0; i < stylesheet.cssRules.length; i++) {
        if(stylesheet.cssRules[i].selectorText === '#container, #results') {
            elementRules = stylesheet.cssRules[i];
            elementRules.style.setProperty('height', '500px');
            break;
        }
    }

}










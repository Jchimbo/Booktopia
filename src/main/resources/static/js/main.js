import {nav} from "./nav.js";

nav();
document.getElementById('search').addEventListener('submit', function(event) {
    event.preventDefault();
    searchIsbn(event.target);
});

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



















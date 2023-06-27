
fetch('http://localhost:8080/heartbeat',
    {method: 'GET',
    }).then((response) => response.text())
    .then((text)=> {
        if (text === "true"){
            fetch('http://localhost:8080/account', {
                method: 'GET',
            }).then((response)=>response.json())
                .then((json)=>{
                        var nameTagDiv = document.createElement("Div");
                        nameTagDiv.setAttribute("id", "nameTag");
                        var email = document.createElement("H1");
                        email.setAttribute("id", "title")
                        var text = document.createTextNode(json.email);
                        email.appendChild(text);
                        nameTagDiv.appendChild(email);
                        document.body.prepend(nameTagDiv);
                    }
                )
        }else {

        }

    })
function searchIsbn(form) {
  const inputValue = form.isbn.value;
  fetch('http://localhost:8080/book/' + inputValue, {
    method: 'GET',
  }).then((response)=>response.json())
      .then((json)=>{
        var img = document.createElement("IMG");
        img.setAttribute("src", json.cover);
        img.setAttribute("width", "304");
        img.setAttribute("height", "228");
        img.setAttribute("id", "cover");
        img.setAttribute("alt", "cover of " +json.title)
        document.body.appendChild(img);

        var title = document.createElement("H1");
        title.setAttribute("id", "title")
        var text = document.createTextNode(json.title);
        title.appendChild(text);
        document.body.appendChild(title);
      });
}










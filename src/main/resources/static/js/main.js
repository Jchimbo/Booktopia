var res ={}
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






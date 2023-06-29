import {nav} from "./nav.js";

nav();
fetch('http://localhost:8080/booklist',{
    method: 'GET',
}).then((response) => response.json())
    .then((json) =>
        createTable(json));

function createTable(data){
    let tb = document.getElementById("tb");
    for (let row = 0; row < data.length; row++){
        let rowTb = tb.insertRow(row + 1)
        let cell1 = rowTb.insertCell(0);
        let cell2 = rowTb.insertCell(1);
        let cell3 = rowTb.insertCell(2);
        cell1.innerHTML = data[row].title;
        cell2.innerHTML = data[row].isbn;
        cell3.innerHTML = data[row].cover;
    }

}
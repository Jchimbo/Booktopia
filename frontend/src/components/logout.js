import {useEffect} from "react";
import axios from "axios";
import {redirect} from "react-router-dom";

function Logout(){
    useEffect(()=>{
            axios.get("/logout")
                .then(status =>console.log(status) )
                .catch(error => console.log(error));
    },[])
    return redirect("/");
}

export default Logout;

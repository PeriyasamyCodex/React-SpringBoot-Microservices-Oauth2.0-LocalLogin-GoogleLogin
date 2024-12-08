import React from "react";
import { Navigate, redirect, useNavigate, useNavigation } from "react-router-dom";

const OAuth2RedirectHandler = () => {
    const navigate = useNavigate();

    function getUrlParameter(name: string) {
        name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
        var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');

        var results = regex.exec(location.search);
        return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
    };

    const token = getUrlParameter('token');
    const error = getUrlParameter('error');

    console.log('token received ->'+token);
    console.log('token error received ->'+error);

    if(token){
        console.log('token received 1->'+token);
        localStorage.setItem('token',token);
       // navigate('/');
        return <Navigate to='/home'/>
       
    } else {
        return <Navigate to='/'/>
    }

  
    
}

export default OAuth2RedirectHandler; 
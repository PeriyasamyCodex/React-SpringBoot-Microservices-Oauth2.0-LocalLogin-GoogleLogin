import { QueryClient } from "@tanstack/react-query";

export const queryClient = new QueryClient();

export async function fetchPlaces() {
    const headers = new Headers({
        'Content-Type': 'application/json',
    })
    if (localStorage.getItem('token')) {
        headers.append('Authorization', 'Bearer ' + localStorage.getItem('token'))
    }
    const defaults = { headers: headers };
    let options = {
        url: 'http://localhost:9003/backendApi/places/allPlaces',
        method: 'GET'
    };
    options = Object.assign({}, defaults, options);

    const response = await fetch(options.url, options);

    if (!response.ok) {
        throw Error('An Error Occurred Fetching Places Data')
    }

    const responseData = await response.json();

   // const responseData=null;

    return responseData;
}

export async function fetchAttractions(placeId) {

    const headers = new Headers({
        'Content-Type': 'application/json',
    })
    if (localStorage.getItem('token')) {
        headers.append('Authorization', 'Bearer ' + localStorage.getItem('token'))
    }
    const defaults = { headers: headers };
    let options = {
        url: 'http://localhost:9003/backendApi/attractions/' + placeId,
        method: 'GET'
    };
    options = Object.assign({}, defaults, options);

    const response = await fetch(options.url, options);


  //  const response = await fetch('http://localhost:9003/backendApi/attractions/' + placeId);

    if (!response.ok) {
        throw Error('An Error Occurred Fetching Places Data')
    }

    const responseData = await response.json();

    return responseData;
}

export async function login(formData) {
    const response = await fetch('http://localhost:8080/auth/login',{method: 'POST',headers: { 'Content-Type': 'application/json',}, body: JSON.stringify(formData)});

    if (!response.ok) {
        throw Error('An Error Occurred Fetching Places Data')
    }

    const responseData = await response.json();


    return responseData;
}


export async function fetchUserDetails() {

    const headers = new Headers({
        'Content-Type': 'application/json',
    })
    if (localStorage.getItem('token')) {
        headers.append('Authorization', 'Bearer ' + localStorage.getItem('token'))
    }
    const defaults = { headers: headers };
    let options = {
        url: 'http://localhost:8080' + "/user/me",
        method: 'GET'
    };
    options = Object.assign({}, defaults, options);
    const response = await fetch(options.url, options);


    if (!response.ok) {
        throw Error('An Error Occurred Fetching Places Data')
    }

    const responseData = await response.json();

    return responseData;
}
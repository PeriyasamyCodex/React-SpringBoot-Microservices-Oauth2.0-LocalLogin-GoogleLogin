import { Outlet, redirect, useLoaderData } from "react-router-dom";
import Header from "./Header";
import React from "react";
import MainNavigation from "./MainNavigation";
import { fetchUserDetails } from "../util/http";

const RootLayout = () => {

  const userData = useLoaderData();
    return (
        <>
           <MainNavigation userData={userData}/>
          <main>
            <Outlet />
          </main>
        </>
      
    );
}

export async function loader() {

  let response: any;

  if (localStorage.getItem('token')) {
    response = await fetchUserDetails();
    console.log('response from user ->'+JSON.stringify(response));
  }else {
    console.log('Empty Token')
    return redirect('/');
  }


  return response;


}

export default RootLayout;
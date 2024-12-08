import React from "react";

const SocialLogin = () => {
    return (
        <div className="flex justify-center mt-5 border-blue-400 rounded-lg ">
        <a className="text-white mt-0 rounded-lg border-red-500 border-2 p-2 hover:bg-red-300" href="http://localhost:8080/oauth2/authorize/google?redirect_uri=http://localhost:3000/oauth2/redirect">
        <img src="images/google-icon.png" className="w-10 ml-6" alt="google icon"/>
        Google Sign In
        </a>
        </div>
    );
}

export default SocialLogin;
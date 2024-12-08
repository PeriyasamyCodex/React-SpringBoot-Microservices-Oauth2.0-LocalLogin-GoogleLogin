import React from "react";
import Input from "../../components/Input";
import SocialLogin from "./SocialLogin";
import { login } from "../../util/http";
import { Form, redirect } from "react-router-dom";

const LoginPage = () => {
  return (
    <>
   
    <Form method="POST" className="max-w-sm mx-auto mt-20">
      <div className="mb-5">
        <Input label="Email" name="email" type="email" id="email" placeholder="name@flowbite.com" required />
      </div>
      <div className="mb-5">
        <Input label="Password" name="password" type="password" id="password" placeholder="XXXXXX" required />
      </div>      
      <button type="submit" className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Submit</button>
    </Form>

    <SocialLogin/>
    </>
    
  );
}

export async function loginActions({request}) {

  const data = await request.formData();
  const eventData = {
      email: data.get('email'),
      password: data.get('password')
  };

  const response = await login(eventData);

  localStorage.setItem('token',response.accessToken);
  return redirect('/home');


}

export default LoginPage;
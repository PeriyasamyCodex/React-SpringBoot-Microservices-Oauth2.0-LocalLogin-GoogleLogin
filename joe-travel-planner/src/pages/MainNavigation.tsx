import React from "react";
import { NavLink } from "react-router-dom";

const MainNavigation = ({userData}) => {
    return (
        <nav className="bg-cyan-950 border-gray-200 dark:bg-gray-900 p-4">
           
             <ul className="flex align-middle justify-stretch gap-9">
        <li>
            <span  className="text-xl font-semibold text-blue-400">Joe Travel Planner/Guide</span>
          </li>
          {userData && 
          (
            <>
             <li className="ml-72">    
            <NavLink to="/home" className={`${({isActive}) => isActive ? 'bg-blue-600' : undefined} font-bold text-white hover:text-blue-700`}>Home</NavLink>
          </li>
          <li>
            <NavLink to="/home" className={`${({isActive}) => isActive ? 'bg-blue-600' : undefined} font-bold text-white hover:text-blue-700`}>My Travel Plans</NavLink>
          </li>
          </>
          )
          
          }
         
          <li>
            <NavLink to="/logout" className={`${({isActive}) => isActive ? 'bg-blue-600' : undefined} font-bold text-white hover:text-blue-700`}>Logout</NavLink>
          </li>
         {userData && (
          <li className="rounded-lg flex gap-2 ml-72">
            <img className="rounded-full w-10" src={userData.imageUrl} referrerPolicy="no-referrer" alt={userData.username}/>
            <h2 className="p-2 text-white">{userData.username}</h2>
          </li>
         )}
        </ul>
        </nav>
    );
}




export default MainNavigation;
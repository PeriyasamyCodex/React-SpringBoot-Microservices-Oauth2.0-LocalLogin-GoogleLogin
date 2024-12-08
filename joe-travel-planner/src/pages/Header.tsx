import React from "react";
import { Link } from "react-router-dom";

const Header = () => {
    return (
        <header className="bg-red-700 p-5 font-serif font-semibold text-white shadow-xl  grid grid-cols-2  justify-between">
           <div>
            <h3 className="text-3xl">
                Joe's Travel Planner/Guide
            </h3>
           </div>
           <div className="text-end">
            <Link to="" className="border-2 hover:bg-black hover:border-0 hover:text-white shadow-xl bg-white text-red-700 rounded p-3 shadow-black"  > Create Travel Plan</Link>
           </div>
           <div></div>
        </header>
    );
}

export default Header;
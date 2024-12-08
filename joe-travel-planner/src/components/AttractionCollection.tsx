import { Link } from "react-router-dom";
import { useAppSelector } from "../util/hooks";
import React from "react";
import AttractionItem from "./AttractionItem";
import Attraction from "../modal/Attraction";

const AttractionCollection = () => {
    
      const attractionsData = useAppSelector((state) => state.attractions.attractions);
      console.log('Attractions Rendering ...'+JSON.stringify(attractionsData));
    let content: any;

  

    if (attractionsData)  {
        content = (
            <ul className="border-2 p-3 border-white bg-red-200 grid grid-cols-3 gap-6">
                {attractionsData.map((attraction: Attraction) => (
                    <AttractionItem key={attraction._id} attraction={attraction} />
                ))}

            </ul>
        );
    }

    return (

        <>
            {content}
        </>

    );
}

export default AttractionCollection;
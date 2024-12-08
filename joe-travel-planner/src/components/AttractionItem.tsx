import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Attraction from "../modal/Attraction";
let counter: number = 0;
const AttractionItem = (props: { attraction: Attraction }) => {
console.log('AttractionItem -> Reloading'+counter);

const [dynamicImage, setDynamicImage] = useState<String>(props.attraction.images[0]);

counter++;
    let imageUrl: string = '';
    let imgIndex: number = 0;
    useEffect(() => {
        setInterval(() =>{
            if (imgIndex > props.attraction.images.length-1) {
                imgIndex = 0;
            }
            setDynamicImage(props.attraction.images[imgIndex]);
            console.log('imageUrl'+imageUrl);
            imgIndex++;
    
        },5000)
    },[]);
   

    return (
        <li className=" bg-white rounded-xl shadow-lg" key={props.attraction._id}>
            <h2 className="font-bold text-white text-xl text-center h-20 text-wrap    p-2 bg-red-500">{props.attraction.title}</h2>
            <img src={`/images/${props.attraction.name}/${dynamicImage}`} referrerPolicy="no-referrer" className="hover:shadow-2xl h-80 w-full" alt={props.attraction.name} />
            <p className="font-serif text-lg p-1 h-40">{props.attraction.description}</p>

            <div className="grid grid-cols-2 justify-between">
                <h2 className="font-serif text-xl font-extrabold p-1">{props.attraction.location}</h2>
                
            </div>




        </li>
    );
}

export default AttractionItem;
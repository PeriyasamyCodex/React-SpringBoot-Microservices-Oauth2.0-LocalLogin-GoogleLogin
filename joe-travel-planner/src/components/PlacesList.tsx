import { Link } from "react-router-dom";
import { useAppSelector } from "../util/hooks";
import React from "react";

const PlacesList = () => {
    console.log('Places Rendering ...');
      const placesData = useAppSelector((state) => state.places.places);
    
    let content: any;

    if (placesData)  {
        content = (
            <ul className="border-2 p-3 border-white bg-red-200 grid grid-cols-3 gap-6">
                {placesData.map((place) => (
                    <li className=" bg-white rounded-xl shadow-lg" key={place._id}>
                        <h2 className="font-bold text-white text-xl text-center h-20 text-wrap    p-2 bg-red-500">{place.title}</h2>
                        <img src={`images/places/${place.image}`} className="hover:shadow-2xl" alt={place.name} />
                        <p className="font-serif text-lg p-1 h-40">{place.description}</p>

                        <div className="grid grid-cols-2 justify-between">
                            <h2 className="font-serif text-xl font-extrabold p-1">{place.loacation}</h2>
                            <Link to={place._id} className="px-4 m-2 right text-center py-1 text-sm text-red-600 font-semibold rounded-full border border-red-200 hover:text-white hover:bg-red-600 hover:border-transparent focus:outline-none focus:ring-2 focus:ring-red-600 focus:ring-offset-2">View Attractions</Link>
                        </div>




                    </li>
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

export default PlacesList;
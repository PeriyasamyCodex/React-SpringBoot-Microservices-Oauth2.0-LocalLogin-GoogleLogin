
import PlacesList from "../components/PlacesList";
import { Suspense, useEffect } from "react";
import React from "react";
import { useAppDispatch } from "../util/hooks";
import { fetchPlaces } from "../util/http";
import { Await, useLoaderData } from "react-router-dom";
import { setPlaces } from "../store/places-actions";
import PlaceList from "../modal/PlaceList";
import { useQuery } from "@tanstack/react-query";
const LandingPage = () => {
    const initialData = useLoaderData<PlaceList>();
    const dispatch = useAppDispatch();
    console.log('HOme Rendering')

    const {data} = useQuery({
        queryKey: ['places'],
        queryFn: fetchPlaces,
        initialData: initialData,
        staleTime: 5000
    });
    dispatch(setPlaces(data)); 

    
    return (
        <>
            <Suspense fallback={<p className="text-white">loading ...</p>}>
                <Await resolve={data}>
                    <PlacesList />
                </Await>
            </Suspense>
        </>
    )
}

export async function loader(): Promise<PlaceList> {
    return fetchPlaces();
}



export default LandingPage;
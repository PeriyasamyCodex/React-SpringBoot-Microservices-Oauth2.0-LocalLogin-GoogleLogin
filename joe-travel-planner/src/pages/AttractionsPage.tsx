import React from "react";
import { fetchAttractions } from "../util/http";
import AttractionCollection from "../components/AttractionCollection";
import AttractionList from "../modal/AttractionList";
import { useLoaderData } from "react-router-dom";
import { useAppDispatch } from "../util/hooks";
import { setAttractions } from "../store/attractions-actions";

const AttractionsPage = () => {

    const initialData = useLoaderData();
    const dispatch = useAppDispatch();
    dispatch(setAttractions(initialData));
        

    return (
        <>
        <AttractionCollection />
        </>
    );
}

export async function loader({params}): Promise<AttractionList> {
    const placeId = params.placeId;
    const response = await fetchAttractions(placeId);
    return response;
}

export default AttractionsPage;
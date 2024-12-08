import { QueryClient, useQuery } from "@tanstack/react-query";
import { placesActions } from "./places-slice";
import { Dispatch } from "@reduxjs/toolkit";
import PlaceList from "../modal/PlaceList";



export const setPlaces = (placesData: PlaceList) => {

    return async (dispatch: Dispatch) => {
         
            dispatch(
                placesActions.populatePlaces({
                    places: placesData
                })
            );
    };



}
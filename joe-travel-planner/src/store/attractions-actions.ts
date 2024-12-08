
import { Dispatch } from "@reduxjs/toolkit";
import AttractionList from "../modal/AttractionList";
import { attractionActions } from "./attractions-slice";


export const setAttractions = (attractionsData: AttractionList) => {

    return async (dispatch: Dispatch) => {
         
            dispatch(
                attractionActions.populateAttractions({
                    attractions: attractionsData
                })
            );
    };



}
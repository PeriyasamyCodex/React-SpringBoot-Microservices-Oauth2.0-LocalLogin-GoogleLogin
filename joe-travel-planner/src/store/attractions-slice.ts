import { createSlice } from "@reduxjs/toolkit";
import AttractionList from "../modal/AttractionList";
const initialState: AttractionList = {
   attractions : []
}
const attractionsSlice =  createSlice({
    name: "attractions",
    initialState,
    reducers:  {
        
        populateAttractions(state, actions) {
            state.attractions = actions.payload.attractions;
        }
    }
});

export const attractionActions = attractionsSlice.actions;

export default attractionsSlice;
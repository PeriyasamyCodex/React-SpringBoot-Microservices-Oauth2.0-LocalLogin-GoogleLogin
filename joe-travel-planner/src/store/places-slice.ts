import { createSlice } from "@reduxjs/toolkit";
import Place from "../modal/Place";
import PlaceList from "../modal/PlaceList";
const initialState: PlaceList = {
   places : []
}
const placesSlice =  createSlice({
    name: "places",
    initialState,
    reducers:  {
        
        populatePlaces(state, actions) {
            state.places = actions.payload.places;
        },
        showTrendingDestinations(state) {
            state.places.sort((placeA, placeB) => placeA.views - placeB.views);
        },
        showSpecials(state, actions) {
            state.places = state.places.filter((place) => place.bestVisitedByMonth.includes(actions.payload.bestVisitedByMonth) );
        }
    }
});

export const placesActions = placesSlice.actions;

export default placesSlice;
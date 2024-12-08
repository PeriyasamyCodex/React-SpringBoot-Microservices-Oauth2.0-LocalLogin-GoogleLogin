import { configureStore } from "@reduxjs/toolkit";
import placesSlice from "./places-slice";
import attractionsSlice from "./attractions-slice";

const store = configureStore({
    reducer: {places: placesSlice.reducer, attractions: attractionsSlice.reducer}
})


export type AppDispatch = typeof store.dispatch
export type RootState = ReturnType<typeof store.getState>

export default store;
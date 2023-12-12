// store.js

import { configureStore } from "@reduxjs/toolkit";
import { persistStore, persistReducer } from "redux-persist";
import storage from "redux-persist/lib/storage"; // defaults to localStorage

import authReducer from "../redux/authSlice"; // Example file where you combine your reducers

const persistConfig = {
  key: "root",
  storage,
  // Add any specific configuration options here if needed
};

const authPersistedReducer = persistReducer(persistConfig, authReducer);

export const store = configureStore({
  reducer: {
    auth: authPersistedReducer,
  },
});

export const persistor = persistStore(store);

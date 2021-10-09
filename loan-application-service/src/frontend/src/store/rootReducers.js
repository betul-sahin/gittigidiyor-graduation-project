// tum stateleri topladigim yer

import { combineReducers } from "redux";
import loanListReducer from "./reducers/loanListReducer";

const rootReducer = combineReducers({
    loanListReducer: loanListReducer
})

export default rootReducer;
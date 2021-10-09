import { GET_LOANS_SUCCESS } from "../actions/loanListActions";

const initialState = {
    loans:[]
}

export default function loanListReducer(state=initialState, action){
    switch (action.type) {
        case GET_LOANS_SUCCESS:
            return {...state, loans:action.loans}
    
        default:
            return state
    }
}

// gonderdigimiz aksiyona gore state in son hali burada
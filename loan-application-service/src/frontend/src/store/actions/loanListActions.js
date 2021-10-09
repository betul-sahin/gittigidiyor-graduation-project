import LoanService from "../../services/LoanService"

export const GET_LOANS_SUCCESS = "GET_LOANS_SUCCESS"

export function getLoansSuccess(loans){
    return{
        type : GET_LOANS_SUCCESS,
        payload : loans
    }
}

export function getLoans(){
    return function(dispatch){
        let loanService = new LoanService()
        loanService.getLoans().then(response => {
            dispatch(getLoansSuccess(response.data))
        }).catch(error => {
            console.log(error)
        })
    }
}

// reducerlara gonderdigimiz aksiyon
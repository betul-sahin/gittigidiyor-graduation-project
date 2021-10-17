import axios from 'axios'

export default class LoanService{
    getLoans(){
        return axios.get("api/v1/loans")
    }

    getLoanById(loanId){
        return axios.get("api/v1/loans/" + loanId)
    }
}

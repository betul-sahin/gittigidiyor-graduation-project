import axios from 'axios'

export default class LoanService{
    getLoans(){
        return axios.get("http://localhost:8080/api/v1/loans")
    }

    getLoanById(loanId){
        return axios.get("http://localhost:8080/api/v1/loans/" + loanId)
    }
}

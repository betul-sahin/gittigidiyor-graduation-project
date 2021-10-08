import axios from 'axios'
import React from 'react'

export default class LoanService{
    getLoans(){
        return axios.get("http://localhost:8080/api/v1/loans")
    }
}

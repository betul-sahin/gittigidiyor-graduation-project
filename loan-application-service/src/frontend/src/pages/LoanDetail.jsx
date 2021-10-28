import React, { useState, useEffect } from "react";
import { useParams } from 'react-router'
import { Button, Card } from 'semantic-ui-react'
import LoanService from '../services/LoanService'

export default function LoanDetail() {
    // Loan list sayfasindan buraya gelirken eklenen id yakalaniyor
    let { id } = useParams()
    const [loan, setLoan] = useState({})

    useEffect(() => {
        let loanService = new LoanService()
        loanService
            .getLoanById(id)
            .then(result => setLoan(result.data))
    }, [])

    return (
        <div>
            <Card.Group>
                <Card fluid>
                    <Card.Content>
                        <Card.Header>{loan.identificationNumber} </Card.Header>
                        <Card.Meta>Identification number : {loan.identificationNumber}</Card.Meta>
                        <Card.Description>
                            <p><strong>Credit Amount :</strong> {loan.creditAmount}</p>
                            <p><strong>Credit Limit Multiplier :</strong> {loan.creditLimitMultiplier}</p>
                            <p><strong>Credit Result :</strong> {loan.creditResult}</p>
                        </Card.Description>
                    </Card.Content>
                </Card>
            </Card.Group>
        </div>
    )
}

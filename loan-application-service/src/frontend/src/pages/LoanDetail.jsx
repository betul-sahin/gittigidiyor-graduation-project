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
                        <Card.Header>Loan {loan.id} detay sayfasi</Card.Header>
                        <Card.Meta>Friends of Elliot</Card.Meta>
                        <Card.Description>
                            Steve wants to add you to the group <strong>best friends</strong>
                        </Card.Description>
                    </Card.Content>
                    <Card.Content extra>
                        <div className='ui two buttons'>
                            <Button basic color='green'>
                                Approve
                            </Button>
                            <Button basic color='red'>
                                Decline
                            </Button>
                        </div>
                    </Card.Content>
                </Card>
            </Card.Group>
        </div>
    )
}

import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { Icon, Menu, Table } from 'semantic-ui-react'
import LoanService from '../services/LoanService'

export default function LoanList() {
    const [loans, setLoans] = useState([])

    // yuklenirken calismasini istediklerimi buraya yaziyorum
    useEffect(() => {
        let loanService = new LoanService()
        loanService.getLoans().then(response => {
            setLoans(response.data)
        }).catch(error => {
            console.log(error)
        })
    }, [])

    return (
        <div>
            <Table celled>
                <Table.Header>
                    <Table.Row>
                        <Table.HeaderCell>#</Table.HeaderCell>
                        <Table.HeaderCell>Musteri ID</Table.HeaderCell>
                        <Table.HeaderCell>Kredi Miktarı</Table.HeaderCell>
                        <Table.HeaderCell>Kredi Limit Çarpanı</Table.HeaderCell>
                        <Table.HeaderCell>Kredi Sonucu</Table.HeaderCell>
                        <Table.HeaderCell></Table.HeaderCell>
                    </Table.Row>
                </Table.Header>

                <Table.Body>
                    {loans.map((loan) => (
                        <Table.Row key={loan.id}>
                            <Table.Cell>
                                <Link to={`/loans/${loan.id}`}>{loan.identificationNumber}</Link>
                            </Table.Cell>
                            <Table.Cell>{loan.creditAmount}</Table.Cell>
                            <Table.Cell>{loan.creditLimitMultiplier}</Table.Cell>
                            <Table.Cell>{loan.creditResult}</Table.Cell>
                            <Table.Cell></Table.Cell>
                        </Table.Row>
                    ))}
                </Table.Body>

                <Table.Footer>
                    <Table.Row>
                        <Table.HeaderCell colSpan='3'>
                            <Menu floated='right' pagination>
                                <Menu.Item as='a' icon>
                                    <Icon name='chevron left' />
                                </Menu.Item>
                                <Menu.Item as='a'>1</Menu.Item>
                                <Menu.Item as='a'>2</Menu.Item>
                                <Menu.Item as='a'>3</Menu.Item>
                                <Menu.Item as='a'>4</Menu.Item>
                                <Menu.Item as='a' icon>
                                    <Icon name='chevron right' />
                                </Menu.Item>
                            </Menu>
                        </Table.HeaderCell>
                    </Table.Row>
                </Table.Footer>
            </Table>
        </div>
    )
}

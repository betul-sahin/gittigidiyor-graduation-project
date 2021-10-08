import React, { useState, useEffect } from 'react'
import { Icon, Menu, Table } from 'semantic-ui-react'
import LoanService from '../services/loanService'

export default function LoanList() {
    const [loans, setLoans] = useState([])

    // yuklenirken calismasini istediklerimi buraya yaziyorum
    useEffect(() => {
        let loanService = new LoanService()
        loanService
        .getLoans()
        .then(result => setLoans(result.data.data))
    })

    return (
        <div>
            <Table celled>
                <Table.Header>
                    <Table.Row>
                        <Table.HeaderCell>Adı Soyadı</Table.HeaderCell>
                        <Table.HeaderCell>TC Kimlik No</Table.HeaderCell>
                        <Table.HeaderCell>Kredi Miktarı</Table.HeaderCell>
                        <Table.HeaderCell>Kredi Limit Çarpanı</Table.HeaderCell>
                        <Table.HeaderCell>Kredi Sonucu</Table.HeaderCell>
                    </Table.Row>
                </Table.Header>

                <Table.Body>
                    {loans.map((loan) => (
                        <Table.Row key={loan.id}>
                            <Table.Cell>{loan.customer.firstName}</Table.Cell>
                            <Table.Cell>{loan.identificationNumber}</Table.Cell>
                            <Table.Cell>{loan.creditAmount}</Table.Cell>
                            <Table.Cell>{loan.creditLimitMultiplier }</Table.Cell>
                            <Table.Cell>{loan.creditResult}</Table.Cell>
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

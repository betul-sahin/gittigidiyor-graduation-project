import logo from './logo.svg';
import './App.css';

import React, {useState, useEffect} from "react";
import Button from 'react-bootstrap/Button';
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import Container from "react-bootstrap/Container";
import Table from "react-bootstrap/Table";

import {deleteCustomer, getAllCustomers} from "./client";
import axios from "axios";

const Header = () =>{
    return (
        <Navbar bg="dark" variant="dark">
            <Container>
                <Navbar.Brand href="#home">Patika Loan Application System</Navbar.Brand>
                <Nav className="me-auto">
                    <Nav.Link href="#home">Home</Nav.Link>
                    <Nav.Link href="#customer">Customer</Nav.Link>
                    <Nav.Link href="#application">Application</Nav.Link>
                </Nav>
            </Container>
        </Navbar>
    );
};

const columns = fetchCustomers => [
    {
        title: 'Identification Number',
        dataIndex: 'identificationNumber',
        key: 'identificationNumber',
    },
    {
        title: 'First Name',
        dataIndex: 'firstName',
        key: 'firstName',
    },
    {
        title: 'Last Name',
        dataIndex: 'lastName',
        key: 'lastName',
    },
    {
        title: 'Phone Number',
        dataIndex: 'phoneNumber',
        key: 'phoneNumber',
    },
    {
        title: 'Monthly Income',
        dataIndex: 'monthlyIncome',
        key: 'monthlyIncome',
    }
];

const Customers = () => {
    const [customers, setCustomers] = useState([]);
    const [fetching, setFetching] = useState(true);

    const fetchCustomers = () => {
        axios.get('api/v1/customers/')
            .then(response => {
                console.log(response.data);
                setCustomers(response.data);
            })
            .catch(error => {
                console.error('There was an error!', error);
            });
    }

    useEffect(() => {
        fetchCustomers();
    }, []);

    return (
        <Container>
            <Button variant="secondary" style={{ float: 'right', margin: '20px' }}>Add a Customer</Button>
            <Table striped bordered hover>
                <thead>
                <tr>
                    <th>Identification Number</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Phone Number</th>
                    <th>Monthly Income test</th>
                </tr>
                </thead>
                <tbody>
                {
                    customers.map(customer => {
                         return <tr>
                                     <td>{customer.identificationNumber}</td>
                                     <td>{customer.identificationNumber}</td>
                                     <td>{customer.identificationNumber}</td>
                                     <td>{customer.identificationNumber}</td>
                                     <td>{customer.identificationNumber}</td>
                                </tr>
                    })
                }
                </tbody>
            </Table>
        </Container>
    )
};

function App() {
    return (
        <div className="App">
            <Header/>
            <Customers/>
        </div>
    );

}

export default App;
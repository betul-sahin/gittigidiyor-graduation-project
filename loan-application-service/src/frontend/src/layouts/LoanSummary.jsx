import React from 'react'
import { Dropdown } from 'semantic-ui-react'
import { NavLink } from 'react-router-dom';

export default function LoanSummary() {
    return (
        <div>
            <Dropdown item text='Bekleyen Başvurular'>
                <Dropdown.Menu>
                    <Dropdown.Item>Ahmet Yılmaz</Dropdown.Item>
                    <Dropdown.Item>Mehmet Yılmaz</Dropdown.Item>
                    <Dropdown.Item>Ayşe Yılmaz</Dropdown.Item>
                </Dropdown.Menu>
                <Dropdown.Divider/>
                <Dropdown.Item as={NavLink} to="/latest-loans"></Dropdown.Item>
            </Dropdown>
        </div>
    )
}

import React from 'react'
import { Menu } from 'semantic-ui-react'
import { Link } from 'react-router-dom'

export default function Categories() {
    return (
        <div>
            <Menu pointing vertical>
                <Menu.Item as={ Link } name='home' to={'/'}/>
                <Menu.Item as={ Link } name='apply for loan' to={'/apply-for-loan'}/>
            </Menu>
        </div>
    )
}

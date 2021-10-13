import React from 'react'
import { Menu } from 'semantic-ui-react'

export default function Categories() {
    return (
        <div>
            <Menu pointing vertical>
                <Menu.Item
                    name='home'
                />
                <Menu.Item
                    name='apply for loan'
                />
            </Menu>
        </div>
    )
}

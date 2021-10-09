import React, {useState} from 'react'
import { Menu, Container } from 'semantic-ui-react'
import LoanSummary from './LoanSummary'
import SignedIn from './SignedIn'
import SignedOut from './SignedOut'

export default function Navi() {
    const [isAuthenticated, setIsAuthenticated] = useState(true)

    function handleSignOut(params){
        setIsAuthenticated(false)
    }

    function handleSignIn(params){
        setIsAuthenticated(true)
    }

    return (
        <div>
            <Menu inverted fixed="top">
                <Container>
                    <Menu.Item
                        name='home'
                    />
                    <Menu.Item
                        name='messages'
                    />

                    <Menu.Menu position='right'>
                        <LoanSummary/>
                        {isAuthenticated ? <SignedIn signOut={handleSignOut} bisey="1"/> : <SignedOut signIn={handleSignIn} />}
                    </Menu.Menu>
                </Container>
            </Menu>
        </div>
    )
}

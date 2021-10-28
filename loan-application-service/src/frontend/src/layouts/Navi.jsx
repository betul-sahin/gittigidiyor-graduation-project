import React, {useState} from 'react'
import { Menu, Container } from 'semantic-ui-react'
import SignedIn from './SignedIn'
import SignedOut from './SignedOut'
import {useHistory} from 'react-router'
import {Link} from "react-router-dom";

export default function Navi() {
    const [isAuthenticated, setIsAuthenticated] = useState(true)
    const history = useHistory()

    function handleSignOut(params){
        setIsAuthenticated(false)
        // cikis yaptiginda anasayfaya yonlensin
        history.push("/")
    }

    function handleSignIn(params){
        setIsAuthenticated(true)
    }

    return (
        <div>
            <Menu inverted fixed="top">
                <Container>
                    <Menu.Item as={ Link } name='home' to={'/'}/>
                    <Menu.Item as={ Link } name='apply for loan' to={'/apply-for-loan'}/>
                    <Menu.Menu position='right'>
                        {isAuthenticated ? <SignedIn signOut={handleSignOut} bisey="1"/> : <SignedOut signIn={handleSignIn} />}
                    </Menu.Menu>
                </Container>
            </Menu>
        </div>
    )
}

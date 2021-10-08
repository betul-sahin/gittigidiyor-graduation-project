import React from 'react'
import LoanList from '../pages/LoanList'
import Categories from './Categories'
import { Grid } from 'semantic-ui-react'

function Dashboard() {
    return (
        <div>
            <Grid>
                <Grid.Row>
                    <Grid.Column width={4}>
                        <Categories />
                    </Grid.Column>
                    <Grid.Column width={12}>
                        <LoanList />
                    </Grid.Column>
                </Grid.Row>
            </Grid>
        </div>
    )
}

export default Dashboard;
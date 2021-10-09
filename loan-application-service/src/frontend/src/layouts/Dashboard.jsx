import React from 'react'
import LoanList from '../pages/LoanList'
import LoanAdd from '../pages/LoanAdd'
import LoanDetail from '../pages/LoanDetail'
import Categories from './Categories'
import { Grid } from 'semantic-ui-react'
import { Route } from "react-router"

export default function Dashboard() {
    return (
        <div>
            <Grid>
                <Grid.Row>
                    <Grid.Column width={4}>
                        <Categories />
                    </Grid.Column>
                    <Grid.Column width={12}>
                        <Route exact path="/" component={LoanList}/>
                        <Route exact path="/loans" component={LoanList}/>
                        <Route path="/loans/:id" component={LoanDetail}/>
                        <Route exact path="/loan/add" component={LoanAdd}/>
                    </Grid.Column>
                </Grid.Row>
            </Grid>
        </div>
    )
}
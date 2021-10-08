import './App.css';
import 'semantic-ui-css/semantic.min.css';
import Dashboard from './layouts/Dashboard';
import { Container } from 'semantic-ui-react';
import Navi from './layouts/Navi';

function App() {
  return (
    <div className="App">
      <Navi />
      <Container className="main">
        <Dashboard />
      </Container>
    </div>
  );
}

export default App;

/*
Typescript =
Javascripti tip güvenli hale getirmek için
microsoft taraftından geliştirildi. Extra özelliklerde
eklendi. Üst seviye bir dil.

React, angular, vue.. =
Single page applicationdır.

Redux = state management.
state bir datanın son hali.

rest -> geriye kalan parametreler demek
function(id, ...products){
}

spread -> ayrıştırmak anlamında.
rest te array geçilmişse ayrıştırmak
icin kullanıyoruz.
let points = [1, 2 ,3, 4 ,5]
console.log(...points)

destructuring ->
let populations = [1000, 2000, 3000]
let [small, medium, large] = populations
console.log(small)
console.log(medium)
console.log(large)
*/
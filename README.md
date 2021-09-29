<h3 align=center>Loan Application System</h3>
<p align="center">Loan application system is a Restfull application which written using with Spring Boot Framework.</p>

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li><a href="#roadmap">Business Rules</a></li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

## About The Project  
It receives loan applications and returns the credit result to the customer according to the relevant criteria.

### Built With
- Spring Boot  
- Spring Data Jpa  
- Spring Cloud  
- Feign Client  
- Eureka Server   
- Postgre Sql  
- Mongo DB  
- Mapstruct  
- Lombok  
- Swagger
- Docker

## Business Rules
- CRUD operations for Customer/Loan.
- If the credit score is below 500 points, the loan application of the customer is rejected. (Credit result: REJECTION)
- If the credit score is between 500 points and 1000 points and the monthly income is below 5000 TL, the loan application of the customer is approved. A limit of 10,000 TL is assigned to the customer. (Credit result: APPROVAL)
- If the credit score is between 500 points and 1000 points and the monthly income is above 5000 TL, the loan application of the customer is approved. A limit of 20,000 TL is assigned to the customer. (Credit result: APPROVAL)
- If the credit score is 1000 points or more the loan application of the customer is approved. A limit equal to the monthly income x credit limit multiplier is assigned to the customer. (Credit result: APPROVAL)
- The credit limit multiplier is 4 by default.
- A completed loan application is only queried with an identification number.

<!-- GETTING STARTED -->
## Getting Started
There are 3 modules in the project. Discover service, credit score service and loan application service. Run these services by following the steps below.   

### Prerequisites
You should have Maven and JDK 1.8 to build the project.  
[Maven](https://maven.apache.org/download.cgi)  
[JDK 1.8](https://www.oracle.com/java/technologies/downloads/#java8)

### Installation

Step 1. Clone the repo to your local
   ```sh
   https://github.com/113-GittiGidiyor-Java-Spring-Bootcamp/gittigidiyor-graduation-project-betul-sahin.git
   ```
Step 2. Run the discovery service
   ```sh
   cd discovery-service
   mvn clean install spring-boot:run
   ```
Step 3. Run the credit score service
   ```sh
   cd credit-score-service
   docker-compose -f docker-compose.yml up -d
   mvn clean install spring-boot:run
   ```
Step 4. Run the loan application service
   ```sh
   cd loan-application-service
   mvn clean install spring-boot:run
   ```

<!-- USAGE EXAMPLES -->
## Usage

Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources.

_For more examples, please refer to the [Documentation](https://example.com)_


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.  

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Contact
[Betül Şahin](https://www.linkedin.com/in/betulsahin/)

## License
Distributed under the MIT License. See [LICENSE](https://github.com/113-GittiGidiyor-Java-Spring-Bootcamp/gittigidiyor-graduation-project-betul-sahin/blob/main/LICENSE) for more information.  


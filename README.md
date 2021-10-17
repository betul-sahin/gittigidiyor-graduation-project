# Loan Application System :receipt:
Loan application system is a Restfull application which written using with Spring Boot. It receives loan applications and returns the credit result to the customer according to the relevant criteria.

## Tech stack and whys :hammer_and_wrench:
- [Spring Boot](https://spring.io/projects/spring-boot)  
- [Spring Data Jpa](https://spring.io/projects/spring-data-jpa)  
- [Spring Cloud](https://spring.io/projects/spring-cloud)  
- [Feign Client](https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-feign.html)  
- [Eureka Server](https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-eureka-server.html)   
- [Postgre Sql](https://www.postgresql.org/)  
- [Mongo DB](https://www.mongodb.com/)  
- [Mapstruct](https://mapstruct.org/)  - Good performance than other java mapping framework
- [Lombok](https://projectlombok.org/)  - Less boilerplate code
- [Swagger](https://swagger.io/) - Easy implementation and widespread use
- [Docker](https://www.docker.com/)    
- [AWS Elastic Beanstalk & RDS](https://aws.amazon.com/tr/)   

## Business Rules :pushpin:
- CRUD operations for Customer/Loan.
- If the credit score is below 500 points, the loan application of the customer is rejected. (Credit result: REJECTION)
- If the credit score is between 500 points and 1000 points and the monthly income is below 5000 TL, the loan application of the customer is approved. A limit of 10,000 TL is assigned to the customer. (Credit result: APPROVAL)
- If the credit score is between 500 points and 1000 points and the monthly income is above 5000 TL, the loan application of the customer is approved. A limit of 20,000 TL is assigned to the customer. (Credit result: APPROVAL)
- If the credit score is 1000 points or more the loan application of the customer is approved. A limit equal to the monthly income x credit limit multiplier is assigned to the customer. (Credit result: APPROVAL)
- The credit limit multiplier is 4 by default.
- A completed loan application is only queried with an identification number.

<!-- GETTING STARTED -->
## Getting Started 💥
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
   docker-compose -f docker-compose.yml up -d
   ```
Step 3. Run the credit score service
   ```sh
   cd credit-score-service
   docker-compose -f docker-compose.yml up -d
   ```
Step 4. Run the loan application service
   ```sh
   cd loan-application-service
   docker-compose -f docker-compose.yml up -d
   ```

<!-- USAGE EXAMPLES -->
## Usage :desktop_computer:

- Discorvery service hosted on `http://localhost:9090`  
- Credit score service hosted on `http://localhost:8082`  
- Mongo DB which using by credit score service hosted on `http://localhost:8081`  
- Loan application service hosted on `http://localhost:8080`  

### Swagger UI
Endpoints : `http://localhost:8080/swagger-ui.html`

## Contributing 👏
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.  

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License 📝
Distributed under the MIT License. See [LICENSE](https://github.com/113-GittiGidiyor-Java-Spring-Bootcamp/gittigidiyor-graduation-project-betul-sahin/blob/main/LICENSE) for more information.  

## Contact 📫 
[Betül Şahin](https://www.linkedin.com/in/betulsahin/)

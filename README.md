# Loan Application System
Loan application system is a Restfull application which written using with Spring Boot Framework. It receives loan applications and returns the credit result to the customer according to the relevant criteria.

## Business Rules
- CRUD operations for Customer/Loan.
- If the credit score is below 500 points, the loan application of the customer is rejected. (Credit result: REJECTION)
- If the credit score is between 500 points and 1000 points and the monthly income is below 5000 TL, the loan application of the customer is approved. A limit of 10,000 TL is assigned to the customer. (Credit result: APPROVAL)
- If the credit score is between 500 points and 1000 points and the monthly income is above 5000 TL, the loan application of the customer is approved. A limit of 20,000 TL is assigned to the customer. (Credit result: APPROVAL)
- If the credit score is 1000 points or more the loan application of the customer is approved. A limit equal to the monthly income x credit limit multiplier is assigned to the customer. (Credit result: APPROVAL)
- The credit limit multiplier is 4 by default.
- A completed loan application is only queried with an identification number.

## Tech Stack
Spring Boot  
Spring Data Jpa  
Spring Cloud  
Feign Client  
Eureka Server   
Postgre Sql  
Mongo DB  
Mapstruct  
Lombok  
Swagger

## Run the Application
There are 3 modules in the project. Discover service, credit score service and loan application service.

1. Pull the project to your local from the command line.
   `git pull https://github.com/113-GittiGidiyor-Java-Spring-Bootcamp/gittigidiyor-graduation-project-betul-sahin.git`   
2. Navigate to "discovery-service" directory.
   `cd discovery-service`
3. And run the discovery-service application with below command.
   `mvn clean install spring-boot:run`
4. After then navigate to "credit-score-service" directory.
   `cd credit-score-service`
5. And run the mongo db containers with below command.
   `docker-compose -f docker-compose.yml up -d`
6. And run the credit-score-service application with below command.
   `mvn clean install spring-boot:run`
7. After then navigate to "loan-application-service" directory.
   `cd loan-application-service`
8. And run the loan-application-service application with below command.
   `mvn clean install spring-boot:run`

## License
Distributed under the MIT License. See [LICENSE](https://github.com/113-GittiGidiyor-Java-Spring-Bootcamp/gittigidiyor-graduation-project-betul-sahin/blob/main/LICENSE) for more information.  


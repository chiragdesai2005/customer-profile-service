# Springboot REST API service to expose customer profile REST APIs

- Prerequisites softwares
    - JDK 1.8 or higher
    - Apache maven 3.0 or higher

- How to build the application
   open terminal/command window and run 'mvn clen install' command from home directory the application 

- Swagger API docs. URL
    - http://localhost:8080/customer-profile/services/swagger-ui.html
    - SwaggerDefinition - http://localhost:8080/customer-profile/services/v2/api-docs
    
- Order to call API
    - call /v1/token
    - use this token in header like below
        
        Authorization         Bearer <token>            
    
- GET, POST, PUT APIs are implemented with h2 in memory database    
    
- Security 
    - basic Jwt is implemented (/v1/token) - post API to get Jwt
    - Preference is to use third party API gateway like Apigee Edge or APACHE WAF or NgInx or TAM etc..
        - Can manage security at following levels
            1) Quota per user, time
            2) Spike arrest 
            3) OAUTH2 
            4) Spring security
            
- Assumptions
    - H2 DB must be replaced with Mongo DB/No SQL DB
    - DELETE or similar operations can be implemented like GET/POST
    - Sample test cases are implemented to demo how to set up and start testing
    - Ideally testing db is diff. than dev. database
    - Error messages must be loaded from .yaml file using Java annotations. marked as TODO
    
    
                 
    
        



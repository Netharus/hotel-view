# hotel-view

## Overview

hotel-view is a hotel property management backend application. It is designed to manage hotel entities, amenities, addresses, contacts, arrival times, and related statistics through a RESTful API.

## Technologies Used

- **Java**
- **Spring Boot** (with Spring Data JPA, REST controllers, and service layers)
- **Lombok** (for reducing boilerplate code)
- **Jakarta Validation**
- **JUnit** (for unit testing)
- **OpenAPI/Swagger** (API documentation)
- **MapStruct** (for type-safe and performant DTO/entity mapping)
- **Liquibase** (for version-controlled database migrations)
- **Maven** (build tool, inferred from project structure)

## Project Structure

- `src/main/java/com/netharus/hotelview/`: Main source code
  - `controller/`: REST controllers (e.g., `HotelRestController`, `HistogramRestController`)
  - `service/` and `service/impl/`: Service interfaces and implementations
  - `repository/`: Spring Data JPA repositories
  - `domain/`: Entity classes
  - `dto/`: Data Transfer Objects for requests and responses
- `src/test/java/com/netharus/hotelview/util/UnitTestUtils.java`: Utilities for unit testing

## Key Features

- Manage hotels, amenities, addresses, contacts, and arrival times
- Grouping and statistics endpoints (e.g., group hotels by brand or amenities)
- Type-safe DTO and entity mapping with MapStruct for clean and maintainable code
- Database migrations and schema versioning using Liquibase
- Data validation and business logic via service layer
- Extensible repository and service patterns

## Getting Started

To build and run the project:

1. **Clone the repository**
   ```bash
   git clone https://github.com/Netharus/hotel-view.git
   cd hotel-view
   ```
2. **Build the project**  
   (Depending on your build tool: Maven or Gradle)
   ```bash
   mvn clean install
   ```
3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the API documentation**  
   OpenAPI/Swagger docs will typically be available at:  
   [http://localhost:8092/swagger-ui.html](http://localhost:8092/swagger-ui/index.html#/)

## Testing

Run unit tests using your build tool:
```bash
mvn test
```

# Topkart Lightning Deals API

Topkart Lightning Deals API is a RESTful API designed to handle lightning deals on the Topkart e-commerce website. It provides functionality for both administrators and customers to manage and interact with lightning deals.

## Features

- **Admin Actions:**
  - Create and update lightning deals
  - Approve orders

- **Customer Actions:**
  - Access available unexpired deals
  - Place orders
  - Check the status of their order

## Technologies Used

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Spring Scheduler
- Maven

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 11 or higher
- Maven

### Installation

1. Clone the repository: `git clone https://github.com/your-username/topkart-lightning-deals-api.git`
2. Navigate to the project directory: `cd topkart-lightning-deals-api`

### Configuration

1. Open `application.properties` file in the `src/main/resources` directory.
2. Update the database configuration properties according to your setup.

### Build and Run

1. Build the project using Maven: `mvn clean package`
2. Run the application: `java -jar target/topkart-lightning-deals-api.jar`

The API will be accessible at `http://localhost:8080`.

## API Endpoints

### Admin Actions

- `POST /admin/lightning-deals` - Create a new lightning deal
- `PUT /admin/lightning-deals/{dealId}` - Update an existing lightning deal
- `POST /admin/orders/{orderId}/approve` - Approve an order

### Customer Actions

- `GET /customer/lightning-deals` - Get available unexpired lightning deals
- `POST /customer/orders` - Place an order for a lightning deal
- `GET /customer/orders/{orderId}` - Get the status of an order

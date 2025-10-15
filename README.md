# RewardsProgram
# Retail Rewards API

# Project Overview
A RESTful API to calculate customer reward points based on transaction history.

#Tech Stack
- Java 8
- Spring Boot
- H2 Database
- JPA
- Postman
- JUnit & Mockito

# Reward Logic
- 2 points for every dollar spent over $100
- 1 point for every dollar spent between $50 and $100

# API Endpoint(Postman)
# Post
http://localhost:2318/api/rewards/customer

==>Response<== :
json
//VALID REQUEST
{
"customerId": 101,
"startDate": "2025-07-01",
"endDate": "2025-09-30"
}

{
"customerId": 201,
"startDate": "2025-08-01",
"endDate": "2025-08-31"
}

{
"customerId": 301,
"startDate": "2025-08-01",
"endDate": "2025-08-31"
}

//INVALID REQUEST

{
"customerId": 401,
"startDate": "2025-10-01",
"endDate": "2025-09-01"
}

{
"customerId": null,
"startDate": null,
"endDate": null
}

]

}

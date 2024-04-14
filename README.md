### Backend Service Documentation for Commercial Proposals Creation

## User Management

### Endpoints

- **GET /api/users/{userId}/mycompanies**
    - Retrieve the list of companies associated with a user by their ID.
    - **Parameters:**
        - `userId`: Long (Path)
    - **Response:**
        - Success: `200 OK` with the list of companies
        - Error: `400 Bad Request` with an error message

- **PUT /api/users/moderate/{reviewId}**
    - Moderate a review.
    - **Parameters:**
        - `reviewId`: Long (Path)
        - `userId`: Long (Query)
    - **Response:**
        - Success: `200 OK` with a moderation message
        - Error: `400 Bad Request` with an error message

## Company Management

### Endpoints

- **URL:** `/new`
- **Method:** `POST`
- **Parameters:**
    - `Request Body`: CompanyEntity (JSON)
    - `Query`: userId (Long)
- **Description:** Create a new company.
- **Response:**
    - Success: `200 OK` - Returns details of the created company.
    - Error: `400 Bad Request` - Returns an error message.

- **URL:** `/all`
- **Method:** `GET`
- **Description:** Get details of all companies.
- **Response:**
    - Success: `200 OK` - Returns a list of CompanyDTO objects.
    - Error: `400 Bad Request` - Returns an error message.

- **URL:** `/{companyId}/users`
- **Method:** `GET`
- **Parameters:**
    - `Path Variable`: companyId (Long)
- **Description:** Get users associated with a specific company.
- **Response:**
    - Success: `200 OK` - Returns a set of UserEntity objects.
    - Error: `400 Bad Request` - Returns an error message.

- **URL:** `/`
- **Method:** `PUT`
- **Parameters:**
    - `Request Body`: CompanyEntity (JSON)
    - `Query`: companyId (Long)
- **Description:** Update details of an existing company.
- **Response:**
    - Success: `200 OK` - Returns updated company details.
    - Error: `400 Bad Request` - Returns an error message.

- **URL:** `/{companyId}/{userId}`
- **Method:** `DELETE`
- **Parameters:**
    - `Path Variables`: companyId (Long), userId (Long)
- **Description:** Delete a company.
- **Response:**
    - Success: `200 OK` - Returns a success message upon deletion.
    - Error: `400 Bad Request` - Returns an error message.

- **URL:** `/add/{userId}`
- **Method:** `PUT`
- **Parameters:**
    - `Path Variable`: userId (Long)
    - `Query`: companyId (Long)
- **Description:** Add a user to a company.
- **Response:**
    - Success: `200 OK` - Returns updated company details.
    - Error: `400 Bad Request` - Returns an error message.

- **URL:** `/`
- **Method:** `GET`
- **Parameters:**
    - `Query`: companyId (Long)
- **Description:** Get details of a specific company.
- **Response:**
    - Success: `200 OK` - Returns company details.
    - Error: `400 Bad Request` - Returns an error message.

# Review Management

### Endpoints

- **POST /api/reviews/new/{companyId}**
    - Create a new review for a company.
    - Request Body: ReviewDTO
    - Parameters:
        - companyId: Long (Path)
        - userId: Long (Query)
    - **Response:**
        - Success: `200 OK` with the created review
        - Error: `400 Bad Request` with an error message

- **GET /api/reviews/all**
    - Get all reviews.
    - **Response:**
        - Success: `200 OK` with the list of reviews
        - Error: `400 Bad Request` with an error message

- **DELETE /api/reviews/{reviewId}/{companyId}**
    - Delete a review by its ID for a specific company.
    - Parameters:
        - reviewId: Long (Path)
        - companyId: Long (Path)
    - **Response:**
        - Success: `200 OK` with a success message
        - Error: `400 Bad Request` with an error message

- **GET /api/reviews/{reviewId}**
    - Get a review by its ID.
    - Parameters:
        - reviewId: Long (Path)
    - **Response:**
        - Success: `200 OK` with the review
        - Error: `400 Bad Request` with an error message

# Category Management

### Endpoints

- **POST /api/categories/new**
    - Create a new category.
    - Request Body: CategoryEntity
    - **Response:**
        - Success: `200 OK` with the created category
        - Error: `400 Bad Request` with an error message

- **GET /api/categories/all**
    - Get all categories.
    - **Response:**
        - Success: `200 OK` with the list of categories
        - Error: `400 Bad Request` with an error message

- **GET /api/categories/{categoryId}**
    - Get a category by its ID.
    - Parameters:
        - categoryId: Long (Path)
    - **Response:**
        - Success: `200 OK` with the category
        - Error: `400 Bad Request` with an error message

- **DELETE /api/categories/{categoryId}**
    - Delete a category by its ID.
    - Parameters:
        - categoryId: Long (Path)
    - **Response:**
        - Success: `200 OK` with a success message
        - Error: `400 Bad Request` with an error message

# Subcategory Management

### Endpoints

- **POST /api/subcategories/new**
    - Create a new subcategory.
    - Request Body: SubcategoryEntity
    - **Response:**
        - Success: `200 OK` with the created subcategory
        - Error: `400 Bad Request` with an error message

- **GET /api/subcategories/all**
    - Get all subcategories.
    - **Response:**
        - Success: `200 OK` with the list of subcategories
        - Error: `400 Bad Request` with an error message

- **GET /api/subcategories/{subcategoryId}**
    - Get a subcategory by its ID.
    - Parameters:
        - subcategoryId: Long (Path)
    - **Response:**
        - Success: `200 OK` with the subcategory
        - Error: `400 Bad Request` with an error message

- **DELETE /api/subcategories/{subcategoryId}**
    - Delete a subcategory by its ID.
    - Parameters:
        - subcategoryId: Long (Path)
    - **Response:**
        - Success: `200 OK` with a success message
        - Error: `400 Bad Request` with an error message

CREATE TABLE room (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    number INT NOT NULL,
    capacity INT NOT NULL,
    price_per_night DOUBLE NOT NULL,
    hotel_status VARCHAR(255) NOT NULL,
    hotel_type VARCHAR(255) NOT NULL
);

CREATE TABLE users (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    user_role VARCHAR(255) NOT NULL
);

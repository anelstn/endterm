CREATE TABLE country (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(20) NOT NULL,
    FOREIGN KEY country_id REFERENCES country(id)
)

CREATE TABLE country (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    FOREIGN KEY category_id REFERENCES category(id)
)

CREATE TABLE item (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    country_id BIGINT,
    FOREIGN KEY item_id REFERENCES item(id)
)

CREATE TABLE item_category (
    id BIGSERIAl PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (item_id, category_id),
    FOREIGN KEY item_id REFERENCES item(id),
    FOREIGN KEY category_id REFERENCES category(id)
)


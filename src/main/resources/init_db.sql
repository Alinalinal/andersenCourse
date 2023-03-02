CREATE TABLE Product
(
    id              int AUTO_INCREMENT PRIMARY KEY,
    name            varchar(45)   NOT NULL,
    is_food         varchar(8)    NOT NULL,
    currency_code   varchar(3)    NOT NULL,
    purchase_price  decimal(7, 2) NOT NULL,
    selling_price   decimal(7, 2) NOT NULL,
    produced_on     date,
    shelf_life_days int           NOT NULL
);

CREATE TABLE User
(
    id            int AUTO_INCREMENT PRIMARY KEY,
    name          varchar(30)        NOT NULL,
    date_of_birth date               NOT NULL,
    email         varchar(45) UNIQUE NOT NULL
);

CREATE TABLE `Order`
(
    id            int AUTO_INCREMENT PRIMARY KEY,
    user_id       int  NOT NULL,
    date_of_order date NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User (id) ON DELETE CASCADE
);

CREATE TABLE Order_Product
(
    order_id   int,
    product_id int,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES `Order` (id),
    FOREIGN KEY (product_id) REFERENCES Product (id)
);

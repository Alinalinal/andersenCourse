CREATE TABLE IF NOT EXISTS Product (
    id int AUTO_INCREMENT PRIMARY KEY,
    name varchar(45) NOT NULL,
    is_food varchar(8) NOT NULL,
    currency_code varchar(3) NOT NULL,
    purchase_price decimal(7, 2) NOT NULL,
    selling_price decimal(7, 2) NOT NULL,
    produced_on date,
    shelf_life_days int NOT NULL
);

CREATE TABLE IF NOT EXISTS User (
    id int AUTO_INCREMENT PRIMARY KEY,
    name varchar(30) NOT NULL,
    date_of_birth date NOT NULL,
    email varchar(45) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS Orders (
    id int AUTO_INCREMENT PRIMARY KEY,
    user_id int NOT NULL,
    is_confirmed boolean DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Order_Product (
    order_id int NOT NULL,
    product_id int NOT NULL,
    PRIMARY KEY (order_id, product_id),
    CONSTRAINT `fk_order_product_order` FOREIGN KEY (order_id) REFERENCES Orders(id),
    CONSTRAINT `fk_order_product_product` FOREIGN KEY (product_id) REFERENCES Product(id)
);

CREATE TABLE IF NOT EXISTS Information (
    id int NOT NULL AUTO_INCREMENT,
    created_at date,
    is_processed boolean,
    order_id int UNIQUE,
    PRIMARY KEY (id),
    FOREIGN KEY (order_id) REFERENCES Orders(id)
);

CREATE TRIGGER confirm_order_trigger
    AFTER UPDATE ON Orders
    FOR EACH ROW
BEGIN
    IF NEW.is_confirmed = 1 THEN
        INSERT INTO Information (created_at, is_processed, order_id)
        VALUES (NOW(), FALSE, NEW.id);
END IF;
END;

CREATE PROCEDURE getUsersOrderHistory(IN userId int)
BEGIN
    SELECT i.id, i.created_at, i.is_processed, i.total_sum, i.order_id FROM Information i JOIN Orders o
        ON i.order_id = o.id WHERE o.user_id = userId;
END;

CREATE SCHEMA dm;

DROP TABLE IF EXISTS dm.`users`;
CREATE TABLE dm.`users`
(
    `user_id`    INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(30) NOT NULL,
    `last_name`  VARCHAR(30) NOT NULL,
    `phone_no`   INT         NOT NULL UNIQUE,
    `email`      VARCHAR(60),
    `created_at` TIMESTAMP DEFAULT (now())
);

DROP TABLE IF EXISTS dm.`order_items`;
CREATE TABLE dm.`order_items`
(
    `order_id`   INT,
    `product_id` INT,
    `quantity`   INT DEFAULT 1,
    `price`      FLOAT
);

DROP TABLE IF EXISTS dm.`orders`;
CREATE TABLE dm.`orders`
(
    `order_id`   INT PRIMARY KEY AUTO_INCREMENT,
    `user_id`    INT UNIQUE NOT NULL,
    `status`     VARCHAR(10),
    `created_at` TIMESTAMP DEFAULT (now()) COMMENT 'When order created'
);

DROP TABLE IF EXISTS dm.`product_category`;
CREATE TABLE dm.`product_category`
(
    `category_id`          INT PRIMARY KEY AUTO_INCREMENT,
    `category_description` VARCHAR(255) UNIQUE
);

DROP TABLE IF EXISTS dm.`products`;
CREATE TABLE dm.`products`
(
    `product_id`  INT PRIMARY KEY AUTO_INCREMENT,
    `name`        VARCHAR(100),
    `brand_id`    INT,
    `price`       INT,
    `quantity`    INT,
    `status`      VARCHAR(10),
    `category_id` INT,
    `created_at`  TIMESTAMP DEFAULT (now())
);

DROP TABLE IF EXISTS dm.`brands`;
CREATE TABLE dm.`brands`
(
    `brand_id`   INT PRIMARY KEY AUTO_INCREMENT,
    `brand_name` VARCHAR(255) UNIQUE
);

DROP TABLE IF EXISTS dm.`suppliers`;
CREATE TABLE dm.`suppliers`
(
    `supplier_id`    INT PRIMARY KEY AUTO_INCREMENT,
    `supplier_name`  VARCHAR(255) NOT NULL,
    `supplier_email` VARCHAR(60),
    `supplier_phone` INT NOT NULL,
    `created at`     TIMESTAMP DEFAULT (now())
);

DROP TABLE IF EXISTS dm.`product_supplier`;
CREATE TABLE dm.`product_supplier`
(
    `product_delivery_id` INT PRIMARY KEY AUTO_INCREMENT,
    `product_id`          INT,
    `supplier_id`         INT,
    `delivery_date`       DATETIME,
    `delivery_qty`        INT,
    `landing_price`       FLOAT,
    `created_at`          TIMESTAMP DEFAULT (now())
);

ALTER TABLE dm.`order_items` ADD FOREIGN KEY (`order_id`) REFERENCES dm.`orders` (`order_id`);

ALTER TABLE dm.`order_items` ADD FOREIGN KEY (`product_id`) REFERENCES dm.`products` (`product_id`);

ALTER TABLE dm.`product_supplier` ADD FOREIGN KEY (`product_id`) REFERENCES dm.`products` (`product_id`);

ALTER TABLE dm.`product_supplier` ADD FOREIGN KEY (`supplier_id`) REFERENCES dm.`suppliers` (`supplier_id`);

ALTER TABLE dm.`orders` ADD FOREIGN KEY (`user_id`) REFERENCES dm.`users` (`user_id`);

ALTER TABLE dm.`products` ADD FOREIGN KEY (`category_id`) REFERENCES dm.`product_category` (`category_id`);

ALTER TABLE dm.`products` ADD FOREIGN KEY (`brand_id`) REFERENCES dm.`brands` (`brand_id`);

CREATE INDEX `product_status` ON dm.`products` (`status`);

CREATE UNIQUE INDEX `products_index_1` ON dm.`products` (`product_id`);

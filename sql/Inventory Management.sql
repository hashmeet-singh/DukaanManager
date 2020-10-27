CREATE SCHEMA dm;
USE dm;
CREATE TABLE `users` (
  `user_id` INT PRIMARY KEY AUTO_INCREMENT,
  `first_name` VARCHAR(30) NOT NULL,
  `last_name` VARCHAR(30) NOT NULL,
  `phone_no` INT NOT NULL UNIQUE ,
  `email` VARCHAR(60),
  `created_at` TIMESTAMP DEFAULT (now())
);

CREATE TABLE `order_items` (
  `order_id` INT,
  `product_id` INT,
  `quantity` INT DEFAULT 1,
  `price` FLOAT
);

CREATE TABLE `orders` (
  `order_id` INT PRIMARY KEY AUTO_INCREMENT,
  `user_id` INT UNIQUE NOT NULL,
  `status` VARCHAR(10),
  `created_at` TIMESTAMP DEFAULT (now()) COMMENT 'When order created'
);

CREATE TABLE `product_category` (
  `category_id` INT PRIMARY KEY AUTO_INCREMENT,
  `category_description` VARCHAR(255)
);

CREATE TABLE `products` (
  `product_id` INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(100),
  `brand_id` INT,
  `price` INT,
  `quantity` INT,
  `status` VARCHAR(10),
  `category_id` INT,
  `created_at` TIMESTAMP DEFAULT (now())
);

CREATE TABLE `brands` (
  `brand_id` INT PRIMARY KEY AUTO_INCREMENT,
  `brand_name` VARCHAR(255)
);

CREATE TABLE `suppliers` (
  `supplier_id` INT AUTO_INCREMENT,
  `supplier_name` VARCHAR(255) NOT NULL,
  `supplier_email` VARCHAR(60) ,
  `supplier_phone` INT NOT NULL,
  `created at` TIMESTAMP DEFAULT (now()),
  PRIMARY KEY (`supplier_id`)
);

CREATE TABLE `product_supplier` (
  `product_delivery_id` INT PRIMARY KEY AUTO_INCREMENT,
  `product_id` INT,
  `supplier_id` INT,
  `delivery_date` DATETIME,
  `delivery_qty` INT,
  `landing_price` FLOAT,
  `created_at` TIMESTAMP DEFAULT (now())
);

ALTER TABLE `order_items` ADD FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`);

ALTER TABLE `order_items` ADD FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`);

ALTER TABLE `product_supplier` ADD FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`);

ALTER TABLE `product_supplier` ADD FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`supplier_id`);

ALTER TABLE `orders` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `products` ADD FOREIGN KEY (`category_id`) REFERENCES `product_category` (`category_id`);

ALTER TABLE `products` ADD FOREIGN KEY (`brand_id`) REFERENCES `brands` (`brand_id`);

CREATE INDEX `product_status` ON `products` (`status`);

CREATE UNIQUE INDEX `products_index_1` ON `products` (`product_id`);

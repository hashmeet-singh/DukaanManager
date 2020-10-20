CREATE SCHEMA dm;
USE dm;
CREATE TABLE `users` (
  `user_id` int PRIMARY KEY AUTO_INCREMENT,
  `first_name` varchar(30) not null,
  `last_name` varchar(30) not null,
  `phone_no` int not null,
  `email` varchar(60),
  `created_at` timestamp DEFAULT (now())
);

CREATE TABLE `order_items` (
  `order_id` int,
  `product_id` int,
  `quantity` int DEFAULT 1,
  `price` float
);

CREATE TABLE `orders` (
  `order_id` int PRIMARY KEY AUTO_INCREMENT,
  `user_id` int UNIQUE NOT NULL,
  `status` varchar(10),
  `created_at` timestamp DEFAULT (now()) COMMENT 'When order created'
);

CREATE TABLE `product_category` (
  `category_id` int PRIMARY KEY AUTO_INCREMENT,
  `category_description` varchar(255)
);

CREATE TABLE `products` (
  `product_id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(100),
  `brand_id` int,
  `price` int,
  `quantity` int,
  `status` varchar(10),
  `category_id` int,
  `created_at` timestamp DEFAULT (now())
);

CREATE TABLE `brands` (
  `brand_id` INT PRIMARY KEY AUTO_INCREMENT,
  `brand_name` varchar(255)
);

CREATE TABLE `suppliers` (
  `supplier_id` int AUTO_INCREMENT,
  `supplier_name` varchar(255) not null,
  `supplier_email` varchar(60) ,
  `supplier_phone` int not null,
  `created at` timestamp DEFAULT (now()),
  PRIMARY KEY (`supplier_id`)
);

CREATE TABLE `product_supplier` (
  `product_delivery_id` int PRIMARY KEY AUTO_INCREMENT,
  `product_id` int,
  `supplier_id` int,
  `delivery_date` datetime,
  `delivery_qty` int,
  `landing_price` float,
  `created_at` timestamp DEFAULT (now())
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

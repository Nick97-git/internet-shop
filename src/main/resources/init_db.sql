CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8;

CREATE TABLE `internet_shop`.`products`
(
    `product_id` BIGINT         NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(255)   NOT NULL,
    `price`      DECIMAL(11, 2) NOT NULL,
    PRIMARY KEY (`product_id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
);

CREATE TABLE `internet_shop`.`users`
(
    `user_id`  BIGINT       NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(256) NOT NULL,
    `login`    VARCHAR(256) NOT NULL,
    `password` VARCHAR(256) NOT NULL,
    `salt` VARBINARY(500) NOT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE
);

CREATE TABLE `internet_shop`.`orders`
(
    `order_id` BIGINT NOT NULL AUTO_INCREMENT,
    `user_id`  BIGINT NOT NULL,
    PRIMARY KEY (`order_id`),
    INDEX `orders_users_fk_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `orders_users_fk`
        FOREIGN KEY (`user_id`)
            REFERENCES `internet_shop`.`users` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

CREATE TABLE `internet_shop`.`orders_products`
(
    `order_id`   BIGINT NOT NULL,
    `product_id` BIGINT NOT NULL,
    INDEX `orders_id_fk_idx` (`order_id` ASC) VISIBLE,
    INDEX `products_id_fk_idx` (`product_id` ASC) VISIBLE,
    CONSTRAINT `orders_products_orders_fk`
        FOREIGN KEY (`order_id`)
            REFERENCES `internet_shop`.`orders` (`order_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `orders_products_products_fk`
        FOREIGN KEY (`product_id`)
            REFERENCES `internet_shop`.`products` (`product_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

CREATE TABLE `internet_shop`.`roles`
(
    `role_id`   BIGINT       NOT NULL AUTO_INCREMENT,
    `role_name` VARCHAR(256) NOT NULL,
    PRIMARY KEY (`role_id`),
    UNIQUE INDEX `role_name_UNIQUE` (`role_name` ASC) VISIBLE
);

insert into roles(role_name)
values ('ADMIN');
insert into roles(role_name)
values ('USER');

CREATE TABLE `internet_shop`.`users_roles`
(
    `user_id` BIGINT NOT NULL,
    `role_id` BIGINT NOT NULL,
    INDEX `users_roles_users_fk_idx` (`user_id` ASC) VISIBLE,
    INDEX `users_roles_roles_fk_idx` (`role_id` ASC) VISIBLE,
    CONSTRAINT `users_roles_users_fk`
        FOREIGN KEY (`user_id`)
            REFERENCES `internet_shop`.`users` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `users_roles_roles_fk`
        FOREIGN KEY (`role_id`)
            REFERENCES `internet_shop`.`roles` (`role_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

CREATE TABLE `internet_shop`.`shopping_carts`
(
    `cart_id` BIGINT NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    PRIMARY KEY (`cart_id`),
    INDEX `carts_users_fk_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `carts_users_fk`
        FOREIGN KEY (`user_id`)
            REFERENCES `internet_shop`.`users` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

CREATE TABLE `internet_shop`.`shopping_carts_products`
(
    `cart_id`    BIGINT NOT NULL,
    `product_id` BIGINT NOT NULL,
    INDEX `carts_products_carts_fk_idx` (`cart_id` ASC) VISIBLE,
    INDEX `carts_products_products_fk_idx` (`product_id` ASC) VISIBLE,
    CONSTRAINT `carts_products_carts_fk`
        FOREIGN KEY (`cart_id`)
            REFERENCES `internet_shop`.`shopping_carts` (`cart_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `carts_products_products_fk`
        FOREIGN KEY (`product_id`)
            REFERENCES `internet_shop`.`products` (`product_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

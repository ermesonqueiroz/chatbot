CREATE TABLE IF NOT EXISTS `messages` (
    `id` VARCHAR(36) NOT NULL,
    `content` VARCHAR(255) NOT NULL,
    `role` ENUM('user', 'model') DEFAULT 'model',
    `customer_id` VARCHAR(36),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`)
);
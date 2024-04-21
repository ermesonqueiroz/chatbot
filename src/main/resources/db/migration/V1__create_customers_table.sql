CREATE TABLE IF NOT EXISTS `customers` (
    `id` VARCHAR(36) NOT NULL,
    `phone_number` VARCHAR(15) UNIQUE NOT NULL,
    `name` VARCHAR(255),
    PRIMARY KEY (`id`)
);

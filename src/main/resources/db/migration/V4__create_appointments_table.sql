CREATE TABLE IF NOT EXISTS `appointments`
(
    `id`          VARCHAR(36) NOT NULL,
    `confirmed`   BOOLEAN   DEFAULT FALSE,
    `customer_id` VARCHAR(36) NOT NULL,
    `service_id`  VARCHAR(36),
    `created_at`  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at`  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
    FOREIGN KEY (`service_id`) REFERENCES `services` (`id`)
);
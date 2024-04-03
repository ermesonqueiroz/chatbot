<?php

namespace App\DTOs;

class CustomerDTO {
    public function __construct(
        public readonly string $name,
        public readonly string $phoneNumber
    ) {}
}

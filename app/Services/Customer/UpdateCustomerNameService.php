<?php

namespace App\Services\Customer;

use App\Models\Customer;

class UpdateCustomerNameService
{
    public function __construct(private readonly Customer $customer) {}

    public function run(string $name): void
    {
        $this->customer->update(compact('name'));
    }
}

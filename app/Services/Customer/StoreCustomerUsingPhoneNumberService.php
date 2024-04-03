<?php

namespace App\Services\Customer;

use App\Models\Customer;

class StoreCustomerUsingPhoneNumberService
{
    public function __construct(private readonly Customer $customer) {}

    public function run(string $phone_number): Customer
    {
        return $this->customer->create(compact('phone_number'));
    }
}

<?php

namespace App\Services\Customer;

use App\Models\Customer;

class GetCustomerByPhoneNumber
{
    public function __construct(private readonly Customer $customer) {}

    public function run(string $phoneNumber):?Customer
    {
        return $this->customer->where('phone_number', $phoneNumber)->first();
    }
}

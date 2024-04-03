<?php

namespace App\Actions;

use App\Models\Customer;

abstract class Action
{
    abstract static function create(Customer $customer): Action;
    abstract public function run(string $customerName): void;
}

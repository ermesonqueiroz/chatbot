<?php

namespace App\Helpers;

use App\Actions\Action;
use App\Models\Customer;
use ReflectionClass;

class LoadAction
{
    public static function run(string $className, Customer $customer): Action
    {
        $reflectionClass = new ReflectionClass("App\\Actions\\$className");
        return $reflectionClass->newInstanceWithoutConstructor()->create($customer);
    }
}

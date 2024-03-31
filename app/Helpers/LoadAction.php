<?php

namespace App\Helpers;

use App\Actions\Action;
use ReflectionClass;

class LoadAction
{
    public static function run(string $className): Action
    {
        $reflectionClass = new ReflectionClass("App\\Actions\\$className");
        return $reflectionClass->newInstanceWithoutConstructor()->create();
    }
}

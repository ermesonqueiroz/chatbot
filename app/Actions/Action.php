<?php

namespace App\Actions;

abstract class Action
{
    abstract static function create(): Action;
    abstract public function run(string $data): void;
}

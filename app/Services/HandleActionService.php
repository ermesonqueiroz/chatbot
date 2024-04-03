<?php

namespace App\Services;

use App\Helpers\LoadAction;
use App\Models\Customer;

class HandleActionService
{
    public function run(string $actionName, Customer $customer, string $data): void
    {
        $actionName = collect(explode('_', $actionName))
            ->map(fn (string $item) => ucfirst($item))
            ->push('Action')
            ->join('');

        $action = LoadAction::run($actionName, $customer);
        $action->run($data);
    }
}

<?php

namespace App\Services;

use App\Helpers\LoadAction;

class HandleActionService
{
    public function run(string $actionName, string $data): void
    {
        $actionName = collect(explode('_', $actionName))
            ->map(fn (string $item) => ucfirst($item))
            ->push('Action')
            ->join('');

        $action = LoadAction::run($actionName);
        $action->run($data);
    }
}

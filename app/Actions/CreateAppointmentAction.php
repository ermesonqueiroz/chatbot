<?php

namespace App\Actions;

use App\Services\CreateAppointmentService;

class CreateAppointmentAction extends Action
{
    public function __construct(
        private readonly CreateAppointmentService $createAppointmentService
    ) {}

    public function run(string $data): void
    {
        $this->createAppointmentService->run();
    }

    static function create(): Action
    {
        return new CreateAppointmentAction(
            new CreateAppointmentService()
        );
    }
}

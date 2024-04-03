<?php

namespace App\Actions;

use App\Services\CreateAppointmentService;
use Carbon\Carbon;

class CreateAppointmentAction extends Action
{
    public function __construct(
        private readonly CreateAppointmentService $createAppointmentService
    ) {}

    public function run(string $customerName): void
    {
        $start = Carbon::create($customerName);
        $end = Carbon::create($customerName)->addHour(1);

        $this->createAppointmentService->run(
            'Agendamento de alguma cliente teste',
            $start,
            $end
        );
    }

    static function create(): Action
    {
        return new CreateAppointmentAction(
            new CreateAppointmentService()
        );
    }
}

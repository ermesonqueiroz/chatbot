<?php

namespace App\Actions;

use App\Services\CreateAppointmentService;
use Illuminate\Support\Carbon;

class CreateAppointmentAction extends Action
{
    public function __construct(
        private readonly CreateAppointmentService $createAppointmentService
    ) {}

    public function run(string $data): void
    {
        $start = Carbon::create($data);
        $this->createAppointmentService->run(
            'Agendamento de alguma cliente teste',
            $start,
            $start->addHour(1)
        );
    }

    static function create(): Action
    {
        return new CreateAppointmentAction(
            new CreateAppointmentService()
        );
    }
}

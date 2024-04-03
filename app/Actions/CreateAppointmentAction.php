<?php

namespace App\Actions;

use App\Models\Customer;
use App\Services\CreateAppointmentService;
use Carbon\Carbon;

class CreateAppointmentAction extends Action
{
    public function __construct(
        private readonly CreateAppointmentService $createAppointmentService,
        private readonly Customer $customer
    ) {}

    public function run(string $customerName): void
    {
        $start = Carbon::create($customerName);
        $end = Carbon::create($customerName)->addHour(1);

        $this->createAppointmentService->run(
            "Agendamento do cliente {$this->customer->name}",
            $start,
            $end
        );
    }

    static function create(Customer $customer): Action
    {
        return new CreateAppointmentAction(
            new CreateAppointmentService(),
            $customer
        );
    }
}

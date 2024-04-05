<?php

namespace App\Services\Appointment;

use App\Models\Appointment;
use App\Models\Service;

class AttachServiceToAppointmentService
{
    public function __construct(private readonly Appointment $appointment) {}

    public function run(Service $service): void
    {
        $this->appointment->service()->associate($service);
    }
}

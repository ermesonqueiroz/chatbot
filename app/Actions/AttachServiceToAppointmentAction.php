<?php

namespace App\Actions;

use App\Models\Customer;
use App\Models\Service;
use App\Services\Appointment\AttachServiceToAppointmentService;
use App\Services\Service\GetServiceByIdService;

class AttachServiceToAppointmentAction extends Action
{
    public function __construct(
        private readonly AttachServiceToAppointmentService $attachServiceToAppointmentService,
        private readonly GetServiceByIdService $getServiceByIdService
    ) {}

    public function run(string $data): void
    {
        $service = $this->getServiceByIdService->run($data);
        $this->attachServiceToAppointmentService->run($service);
    }

    static function create(Customer $customer): Action
    {
        return new AttachServiceToAppointmentAction(
            new AttachServiceToAppointmentService($customer->appointments()->get()->last()),
            new GetServiceByIdService(new Service())
        );
    }
}

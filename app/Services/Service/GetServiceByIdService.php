<?php

namespace App\Services\Service;

use App\Models\Service;

class GetServiceByIdService
{
    public function __construct(private readonly Service $service) {}

    public function run(string $id): ?Service
    {
        return $this->service->where('id', $id)->first();
    }
}

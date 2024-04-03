<?php

namespace App\Actions;

use App\Models\Customer;
use App\Services\Customer\UpdateCustomerNameService;

class UpdateCustomerNameAction extends Action
{
    public function __construct(
        private readonly UpdateCustomerNameService $updateCustomerNameService
    ) {}

    public function run(string $customerName): void
    {
        $this->updateCustomerNameService->run($customerName);
    }

    static function create(Customer $customer): Action
    {
        return new UpdateCustomerNameAction(
            new UpdateCustomerNameService($customer)
        );
    }
}

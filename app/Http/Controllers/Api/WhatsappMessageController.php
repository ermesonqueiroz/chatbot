<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Http\Requests\ReceiveWhatsappMessageRequest;
use App\Services\CreateMessageService;
use App\Services\Customer\GetCustomerByPhoneNumber;
use App\Services\Customer\StoreCustomerUsingPhoneNumberService;
use App\Services\HandleWhatsappMessageService;

class WhatsappMessageController extends Controller
{
    public function receive(
        ReceiveWhatsappMessageRequest        $receiveWhatsappMessageRequest,
        CreateMessageService                 $createMessageService,
        HandleWhatsappMessageService         $handleMessageService,
        GetCustomerByPhoneNumber             $getCustomerByPhoneNumber,
        StoreCustomerUsingPhoneNumberService $storeCustomerUsingPhoneNumberService
    ): void
    {
        $data = $receiveWhatsappMessageRequest->validated();
        $createMessageService->run($data['Body']);

        $phoneNumber = str_replace('whatsapp:', '', $data['From']);
        $customer = $getCustomerByPhoneNumber->run($phoneNumber);

        if (!$customer) {
            $customer = $storeCustomerUsingPhoneNumberService->run($phoneNumber);
        }

        $handleMessageService->run($customer);
    }
}

<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Http\Requests\ReceiveWhatsappMessageRequest;
use App\Services\CreateMessageService;
use App\Services\HandleWhatsappMessage;

class WhatsappMessageController extends Controller
{
    public function receive(
        ReceiveWhatsappMessageRequest $receiveWhatsappMessageRequest,
        CreateMessageService $createMessageService,
        HandleWhatsappMessage $handleMessageService
    ): void
    {
        $data = $receiveWhatsappMessageRequest->validated();
        $createMessageService->run($data['Body']);

        $handleMessageService->run();
    }
}

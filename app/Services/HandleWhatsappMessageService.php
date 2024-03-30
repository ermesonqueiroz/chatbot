<?php

namespace App\Services;

use App\Helpers\MountMessagesHistory;
use App\Models\Message;

class HandleWhatsappMessageService
{
    public function __construct(
        private readonly Message $message,
        private readonly SendPromptToAIService $sendPromptToAIService,
        private readonly AddModelResponseInMessage $addModelResponseInMessage,
        private readonly SendWhatsappMessageService $sendWhatsappMessageService,
        private readonly RecognizeActionService $recognizeActionService,
        private readonly HandleActionService $handleActionService,
    ) {}

    public function run(): void
    {
        $chatHistory = MountMessagesHistory::run($this->message->get()->toArray());

        $lastMessage = $this->message->get()->last();
        $response = $this->sendPromptToAIService->run($chatHistory);

        [$action, $data] = explode(',', $this->recognizeActionService->run($chatHistory));
        $this->handleActionService->run($action, $data);

        $this->addModelResponseInMessage->run($lastMessage, $response);
        $this->sendWhatsappMessageService->run($response);
    }
}

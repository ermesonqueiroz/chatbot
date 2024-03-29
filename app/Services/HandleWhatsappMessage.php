<?php

namespace App\Services;

use App\Data\PromptContent;
use App\Helpers\Prompt\CreateBasePrompt;
use App\Models\Message;

class HandleWhatsappMessage
{
    public function __construct(
        private readonly Message $message,
        private readonly SendPromptToAIService $sendPromptToAIService,
        private readonly AddModelResponseInMessage $addModelResponseInMessage,
        private readonly SendWhatsappMessageService $sendWhatsappMessageService
    ) {}

    public function run(): void
    {
        $chatHistory = CreateBasePrompt::create();

        foreach ($this->message->all() as $message) {
            $chatHistory[] = PromptContent::create('user', $message->body);
            if ($message?->model_response) $chatHistory[] = PromptContent::create('model', $message->model_response);
        }

        $lastMessage = $this->message->get()->last();
        $response = $this->sendPromptToAIService->run($chatHistory);
        $this->addModelResponseInMessage->run($lastMessage, $response);
        $this->sendWhatsappMessageService->run($response);
    }
}

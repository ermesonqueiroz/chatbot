<?php

namespace App\Services;

use App\Data\PromptContent;
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
        $chatHistory = MountMessagesHistory::run($this->message->all());

        logger(json_encode(array_slice($chatHistory, 1)));

        $lastMessage = $this->message->get()->last();
        $response = $this->sendPromptToAIService->run($chatHistory);
        $this->addModelResponseInMessage->run($lastMessage, $response);

        [$action, $data] = $this->recognizeActionService->run([
            ...$chatHistory,
            PromptContent::create('model', $response)
        ]);

        if ($action != 'no_action') $this->handleActionService->run($action, $data);
        $this->sendWhatsappMessageService->run($response);
    }
}

<?php

namespace App\Helpers;

use App\Data\PromptContent;

class MountMessagesHistory
{
    public static function run(array $messages): array
    {
        $chatHistory = CreateBasePrompt::create();;

        foreach ($messages as $message) {
            $chatHistory[] = PromptContent::create('user', $message->body);
            if ($message?->model_response) $chatHistory[] = PromptContent::create('model', $message->model_response);
        }

        return $chatHistory;
    }
}

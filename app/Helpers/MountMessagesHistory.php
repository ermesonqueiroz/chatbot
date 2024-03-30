<?php

namespace App\Helpers;

use App\Data\PromptContent;
use Illuminate\Support\Collection;

class MountMessagesHistory
{
    public static function run(Collection $messages): array
    {
        $chatHistory = collect(CreateBasePrompt::create());

        $messages->each(function ($message) use ($chatHistory) {
            $chatHistory->push(PromptContent::create('user', $message->body));
            if ($message?->model_response) $chatHistory->push(PromptContent::create('model', $message->model_response));
        });

        return $chatHistory->toArray();
    }
}

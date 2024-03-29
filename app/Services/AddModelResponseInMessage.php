<?php

namespace App\Services;

use App\Models\Message;

class AddModelResponseInMessage
{
    public function run(Message $message, string $model_response): void
    {
        $message->update(compact('model_response'));
    }
}

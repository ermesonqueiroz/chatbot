<?php

namespace App\Services;

use App\Models\Message;

class CreateMessageService
{
    public function __construct(private readonly Message $message) {}

    public function run(string $body): Message
    {
        return $this->message->create(compact('body'));
    }
}

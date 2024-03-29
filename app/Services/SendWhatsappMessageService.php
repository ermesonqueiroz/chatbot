<?php

namespace App\Services;

use Twilio\Rest\Client;
use Twilio\Rest\Api\V2010\Account\MessageInstance;

class SendWhatsappMessageService
{
    public function run(string $message): MessageInstance
    {
        $client = new Client(
            env('TWILIO_ACCOUNT_SID'),
            env('TWILIO_AUTH_TOKEN')
        );

        return $client->messages->create('whatsapp:+558581633529', [
            'from' => 'whatsapp:+14155238886',
            'body' => $message
        ]);
    }
}

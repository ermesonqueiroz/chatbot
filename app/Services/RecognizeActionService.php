<?php

namespace App\Services;

use App\Data\PromptContent;

class RecognizeActionService
{
    public function __construct(
        private readonly SendPromptToAIService $sendPromptToAIService
    ) {}

    public function run(array $chatHistory): array
    {
        $prompt = <<< EOD
        Preciso saber qual ação tomar agora, então identifique quão ação eu devo tomar à partir da última mensagem do cliente.

        Quando o cliente pedir para marcar um horário aguarde o momento em que ele passa todos os dados necessários para o agendamento e retorne uma mensagem no seguinte formato:  create_appointment, {{data_do_agendamento}}>
        Caso seja apenas uma mensagem pedindo uma informação ou algo que não gere um efeito colateral no back-end retorne uma mensagem no seguinte formato: no_action, null
        EOD;


        $response = $this->sendPromptToAIService->run([
           ...$chatHistory,
           PromptContent::create('user', $prompt)
        ]);

        logger($response);

        return explode(',', $response);
    }
}

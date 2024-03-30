<?php

namespace App\Services;

use App\Data\PromptContent;

class RecognizeActionService
{
    public function __construct(
        private readonly SendPromptToAIService $sendPromptToAIService
    ) {}

    public function run(array $chatHistory): string
    {
        $prompt = <<< EOD
        Preciso saber qual ação tomar agora, então identifique quão ação eu devo tomar à partir da última mensagem do cliente.

        Ações:

        Cliente informou o horário que precisa fazer um agendamento, caso seja verdade retorne uma mensagem no seguinte formato:  create_appointment, <data_do_agendamento>
        Caso nenhuma das ações anteriores sejam verdadeiras retorne uma mensagem no seguinte formato: no_action
        EOD;


        return $this->sendPromptToAIService->run([
           ...$chatHistory,
           PromptContent::create('user', $prompt)
        ]);
    }
}

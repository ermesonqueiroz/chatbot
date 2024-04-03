<?php

namespace App\Services;

use App\Data\PromptContent;

class RecognizeActionService
{
    public function __construct(private readonly SendPromptToAIService $sendPromptToAIService) {}

    public function run(array $chatHistory): array
    {
        $today = today();
        $prompt = <<< EOD
        Preciso saber qual ação tomar agora, então identifique quão ação eu devo tomar à partir da última mensagem do cliente.

        Caso o cliente peça para marcar um horário, mas ainda não informou seu nome, solicite o nome do cliente e quando ele informar seu nome retorne uma mensagem no seguinte formato: update_customer_name,<nome_do_cliente>

        Quando o cliente pedir para marcar um horário e informar o dia e a hora do agendamento retorne uma mensagem no seguinte formato: create_appointment,<data_do_agendamento>.
        Caso seja apenas uma mensagem pedindo uma informação ou algo que não gere um efeito colateral no back-end retorne uma mensagem no seguinte formato: no_action,null

        A <data_do_agendamento> deve seguir o formato ISO 8601 (leve em consideração a data de hoje sendo {$today->toIso8601String()})
        EOD;


        $response = $this->sendPromptToAIService->run([
           ...$chatHistory,
           PromptContent::create('user', $prompt)
        ]);

        return explode(',', $response);
    }
}

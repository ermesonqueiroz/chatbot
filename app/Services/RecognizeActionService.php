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
        $today = today();
        $prompt = <<< EOD
        Preciso saber qual ação tomar agora, então identifique quão ação eu devo tomar à partir da última mensagem do cliente.

        Quando o cliente pedir para marcar um horário e informar o dia e a hora do agendamento retorne uma mensagem no seguinte formato: create_appointment,<data_do_agendamento>.
        Caso seja apenas uma mensagem pedindo uma informação ou algo que não gere um efeito colateral no back-end retorne uma mensagem no seguinte formato: no_action,null

        A <data_do_agendamento> deve seguir o seguinte formato <DIA/MES/ANO> <HORA> (leve em consideração a data de hoje sendo $today->localeDayOfWeek - {$today->toDateTimeLocalString()})
        EOD;


        $response = $this->sendPromptToAIService->run([
           ...$chatHistory,
           PromptContent::create('user', $prompt)
        ]);

        logger($response);

        return explode(',', $response);
    }
}

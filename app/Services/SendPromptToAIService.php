<?php

namespace App\Services;

use Illuminate\Support\Facades\Http;

class SendPromptToAIService
{
    public function run(array $contents): string
    {
        $url = 'https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=' . env('GEMINI_API_KEY');
        $response = Http::post($url, [
            'contents' => $contents,
            'generationConfig' => [
                'temperature' => 0.9,
                'topK' => 1,
                'topP' => 1,
                'maxOutputTokens' => 2048,
                'stopSequences' => []
            ],
            'safetySettings' => [
                [
                    'category' => 'HARM_CATEGORY_HARASSMENT',
                    'threshold' => 'BLOCK_MEDIUM_AND_ABOVE'
                ],
                [
                    'category' => 'HARM_CATEGORY_HATE_SPEECH',
                    'threshold' => 'BLOCK_MEDIUM_AND_ABOVE'
                ],
                [
                    'category' => 'HARM_CATEGORY_SEXUALLY_EXPLICIT',
                    'threshold' => 'BLOCK_MEDIUM_AND_ABOVE'
                ],
                [
                    'category' => 'HARM_CATEGORY_DANGEROUS_CONTENT',
                    'threshold' => 'BLOCK_MEDIUM_AND_ABOVE'
                ]
            ]
        ]);

        logger($response->json());
        return $response->json('candidates')[0]['content']['parts'][0]['text'];
    }
}

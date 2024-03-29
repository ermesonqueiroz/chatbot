<?php

namespace App\Data;

class PromptContent
{
    public static function create(string $role, string $text): array
    {
        return [
            'role' => $role,
            'parts' => [['text' => $text]]
        ];
    }
}

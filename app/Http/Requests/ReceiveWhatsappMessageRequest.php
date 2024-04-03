<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class ReceiveWhatsappMessageRequest extends FormRequest
{
    public function rules(): array
    {
        return [
            'Body' => ['required', 'string'],
            'From' => ['required', 'string'],
        ];
    }
}

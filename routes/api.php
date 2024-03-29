<?php

use App\Http\Controllers\Api\WhatsappMessageController;
use App\Services\SendWhatsappMessageService;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

Route::prefix('message')->group(function () {
    Route::post('receive', [WhatsappMessageController::class, 'receive']);
});

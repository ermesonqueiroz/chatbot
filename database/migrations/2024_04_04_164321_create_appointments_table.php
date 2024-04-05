<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    public function up(): void
    {
        Schema::create('appointments', function (Blueprint $table) {
            $table->id();

            $table->dateTime('schedule');
            $table->boolean('confirmed');
            $table->boolean('canceled');

            $table->foreignId('service_id')->nullable()->constrained();
            $table->foreignId('customer_id')->constrained();

            $table->softDeletes();
            $table->timestamps();
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('appointments');
    }
};

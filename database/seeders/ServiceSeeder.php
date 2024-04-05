<?php

namespace Database\Seeders;

use App\Models\Service;
use Illuminate\Database\Seeder;

class ServiceSeeder extends Seeder
{
    public function run(): void
    {
        Service::create([
            'name' => 'Design com henna',
            'price' => 3000, // R$ 30
            'duration' => 3600, // 1 hour
        ]);

        Service::create([
            'name' => 'Design personalizado',
            'price' => 3000, // R$ 30
            'duration' => 3600, // 1 hour
        ]);

        Service::create([
            'name' => 'Micropigmentação',
            'price' => 30000, // R$ 30
            'duration' => 3600, // 1 hour
        ]);
    }
}

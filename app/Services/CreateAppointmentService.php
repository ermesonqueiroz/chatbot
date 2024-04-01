<?php

namespace App\Services;

use Google\Client;
use Google\Service\Calendar;
use Google\Service\Calendar\Event;
use Illuminate\Support\Carbon;

class CreateAppointmentService
{
    private readonly Calendar $calendarService;

    public function __construct()
    {
        $client = new Client();
        $client->setAuthConfig(__DIR__ . '/../../google-calendar-key.json');
        $client->setRedirectUri('http://18.224.69.123');
        $client->addScope(Calendar::CALENDAR);

        $this->calendarService = new Calendar($client);
    }

    public function run(string $summary, Carbon $start, Carbon $end): void
    {
        $data = new Event([
            'summary' => $summary,
            'start' => [
                'dateTime' => $start->toIso8601String(),
                'timeZone' => 'America/Fortaleza',
            ],
            'end' => [
                'dateTime' => $end->toIso8601String(),
                'timeZone' => 'America/Fortaleza',
            ],
        ]);

        $this->calendarService->events->insert('ermeson.sampaio.queiroz@gmail.com', $data);
    }
}

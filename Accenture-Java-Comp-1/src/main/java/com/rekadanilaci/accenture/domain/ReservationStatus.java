package com.rekadanilaci.accenture.domain;

/**
 * When Reservation is created, it gets the ENROLLED status automatically.
 * (When the request for creating a reservation is invalid, no Reservation
 * will be created.) It means, the Reservation will be added the to the
 * appropriate DailyList, selected based on the requested entry day.
 *
 * When an employee enters the office, the Status of the appropriate
 * Reservation becomes ENTERED_OFFICE showing that this Reservation is
 * currently used. It means, one place in the office is blocked.
 *
 * When an employee leaves the office, the status of the appropriate
 * Reservation becomes LEFT_OFFICE. It means, one place in the office is
 * unblocked. Leaving the office is a prerequisite for new Reservation
 * for the same actual day.
 */

public enum ReservationStatus {
    ENROLLED, ENTERED_OFFICE, LEFT_OFFICE
}

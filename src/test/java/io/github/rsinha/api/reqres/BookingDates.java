package io.github.rsinha.api.reqres;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookingDates {
    private String checkin;
    private String checkout;
}

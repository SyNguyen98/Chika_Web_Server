package com.chika.server.payload.responses.house;

import com.chika.server.models.histories.DeviceHistory;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Data
@NoArgsConstructor
public class HistoryResponse {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime( FormatStyle.SHORT )
                                                .withLocale( Locale.UK )
                                                .withZone( ZoneId.systemDefault() );

    private Boolean state;
    private String time;

    public HistoryResponse(DeviceHistory deviceHistory) {
        this.state = deviceHistory.getState();
        this.time = formatter.format(deviceHistory.getTime());
    }
}

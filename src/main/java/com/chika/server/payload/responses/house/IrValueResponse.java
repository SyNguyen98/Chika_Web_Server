package com.chika.server.payload.responses.house;

import com.chika.server.models.house.IrValue;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class IrValueResponse {

    private String id;
    private String device;
    private String function;
    private String protocol;
    private Integer nbit;
    private String value;
    private List<Integer> state;
    private List<Integer> rawData;

    public IrValueResponse(IrValue irValue) {
        this.id = irValue.getId();
        this.device = irValue.getDevice();
        this.function = irValue.getFunction();
        this.protocol = irValue.getProtocol();
        this.nbit = irValue.getNbit();
        this.value = irValue.getValue();
        this.state = parseStringToArray(irValue.getState());
        this.rawData = parseStringToArray(irValue.getRawData());
    }

    private List<Integer> parseStringToArray(String string) {
        String[] strings = string.substring(1, string.length() - 1).split(",");
        return Arrays.stream(strings).map(Integer::parseInt).collect(Collectors.toList());
    }
}

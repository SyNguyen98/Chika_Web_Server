package com.chika.server.payload.responses.house;

import com.chika.server.models.house.IrData;
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
    private String protocol;
    private Integer size;
    private List<Object> dataList;

    public IrValueResponse(IrValue irValue) {
        this.id = irValue.getId();
        this.device = irValue.getDevice();
        this.protocol = irValue.getProtocol();
        this.size = irValue.getSize();
        if (this.device.equals("TV")) {
            this.dataList = irValue.getIrData().stream().map(RawDataResponse::new).collect(Collectors.toList());
        } else {
            this.dataList = irValue.getIrData().stream().map(BinaryDataResponse::new).collect(Collectors.toList());
        }
    }

    static class RawDataResponse {
        private final String function;
        private final List<Integer> rawData;

        public RawDataResponse(IrData irData) {
            this.function = irData.getFunction();
            this.rawData = parseStringToArray(irData.getData());
        }

        private List<Integer> parseStringToArray(String string) {
            String[] strings = string.substring(1, string.length() - 1).split(",");
            return Arrays.stream(strings).map(Integer::parseInt).collect(Collectors.toList());
        }

        public String getFunction() {
            return function;
        }

        public List<Integer> getRawData() {
            return rawData;
        }
    }

    static class BinaryDataResponse {
        private final String function;
        private final String binaryData;

        public BinaryDataResponse(IrData irData) {
            this.function = irData.getFunction();
            this.binaryData = irData.getData();
        }

        public String getFunction() {
            return function;
        }

        public String getBinaryData() {
            return binaryData;
        }
    }
}

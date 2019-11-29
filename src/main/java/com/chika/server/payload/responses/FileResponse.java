package com.chika.server.payload.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileResponse {

    private String fileName;

    private String fileUri;

    private String fileType;

    private String label;
}
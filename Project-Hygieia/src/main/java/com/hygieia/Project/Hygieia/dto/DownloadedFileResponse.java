package com.hygieia.Project.Hygieia.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class DownloadedFileResponse implements Serializable {

   @Serial
   private static final long serialVersionUID = 1L;

   private String fileName;
   private String contentType;
   private byte[] data;
}

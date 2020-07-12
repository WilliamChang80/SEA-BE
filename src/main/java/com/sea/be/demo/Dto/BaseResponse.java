package com.sea.be.demo.Dto;

import com.sea.be.demo.Enum.HttpResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class BaseResponse {
    private int code;
    private String message;
    private Object data;
}

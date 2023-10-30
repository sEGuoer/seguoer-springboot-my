package com.seguoer.method;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "统一返回结果")
public class R {
    @Schema(description = "状态码")
    private Integer code;

    @Schema(description = "状态消息")
    private String msg;

    @Schema(description = "具体数据")
    private Object data;
    public static R ok(Object data){
        return new R(HttpStatus.OK.value(),HttpStatus.OK.getReasonPhrase(),data);
    }
    public static R ok(Integer state, Object data){
        return new R(state,HttpStatus.OK.getReasonPhrase(),data);
    }
    public static R ok(){
        return new R(HttpStatus.OK.value(),HttpStatus.OK.getReasonPhrase(),"ok");
    }
}

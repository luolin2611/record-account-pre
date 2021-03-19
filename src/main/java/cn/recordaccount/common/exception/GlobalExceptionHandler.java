package cn.recordaccount.common.exception;

import cn.recordaccount.common.constant.ResStatusEnum;
import cn.recordaccount.common.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * 全局异常处理类
 * @author rollin
 * @date 2021-03-01 17:51:32
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class )
    public Response defaultErrorHandler(HttpServletRequest request, Exception e) {
        HashMap<String,Object> map = new HashMap();
        map.put("url", request.getRequestURL());
        map.put("localizedMessage", e.getLocalizedMessage());
        map.put("exception", e.getMessage());
        log.error("请求异常!", e);
        return Response.error("服务器异常", map);
    }

    /**
     * 参数效验异常处理器
     *
     * @param e 参数验证异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response parameterExceptionHandler(MethodArgumentNotValidException e) {
        log.error("", e);
        // 获取异常信息
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                return Response.error(ResStatusEnum.INCORRECT_SUBMISSION_INFORMATION.getCode(), fieldError.getDefaultMessage());
            }
        }
        return Response.error(ResStatusEnum.INCORRECT_SUBMISSION_INFORMATION);
    }
}

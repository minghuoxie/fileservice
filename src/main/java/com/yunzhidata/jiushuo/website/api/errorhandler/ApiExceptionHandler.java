package com.yunzhidata.jiushuo.website.api.errorhandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ApiExceptionHandler {
    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //System.out.println("----------------initBinder");
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        //System.out.println("----------------addAttributes");
        model.addAttribute("author", "Magical Sam");
    }


    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ApiResponseEntity errorHandler(Exception ex) {
      //  System.out.println("----------------errorHandler"+ex.getMessage());
//        Map map = new HashMap();
//        map.put("code", 100);
//        map.put("msg", ex.getMessage());
        ApiResponseEntity errorBody=new ApiResponseEntity(500,false,"请求失败", ex.getMessage());
        return errorBody;
    }
}

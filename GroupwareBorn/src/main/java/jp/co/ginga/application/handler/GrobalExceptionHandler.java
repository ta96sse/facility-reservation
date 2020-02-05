package jp.co.ginga.application.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Configuration
@Component
@ControllerAdvice
public class GrobalExceptionHandler implements HandlerExceptionResolver {
	private static final Logger logger = LoggerFactory.getLogger(GrobalExceptionHandler.class);

	@ExceptionHandler
	@ResponseStatus()
	public ModelAndView notSupported(Exception ex) {
		String name = ex.getClass().getSimpleName();
		String detail = ex.getMessage();
		ex.printStackTrace();
		System.out.println("★★★"+detail);

		ModelAndView Mav = new ModelAndView();
		Mav.addObject("message", "【" + name + "】");
		Mav.addObject("detail", "例外 : " + detail + "");
		Mav.setViewName("error/error");
		return Mav;
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		String name = ex.getClass().getSimpleName();
		String detail = ex.getMessage();
		System.out.println("★★"+detail);

		ModelAndView Mav = new ModelAndView();
		Mav.addObject("message", "【" + name + "】");
		Mav.addObject("detail", "例外 : " + detail + "");
		Mav.setViewName("error/error");
		return Mav;
	}

}

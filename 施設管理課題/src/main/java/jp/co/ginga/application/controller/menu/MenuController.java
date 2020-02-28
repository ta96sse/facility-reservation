package jp.co.ginga.application.controller.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author yoshi
 *
 */
@Controller
public class MenuController {


	/**
	 *
	 * @return
	 */
	@RequestMapping(path = "/menu", method = RequestMethod.GET)
	public String createMenuFormGet() {
		System.out.println("createMenuForm");
		return "menu/menu-index";
	}

}

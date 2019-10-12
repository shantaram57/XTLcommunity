package life.majiang.community;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/*
* Contorller注解意义在于，把当前类作为路由API的承载着
* */
@Controller
public class IndexController {
	@GetMapping("/")
	public String index() {
		return "index";
	}
}

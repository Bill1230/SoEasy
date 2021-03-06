package com.soeasy.controller.nutritionistController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.soeasy.model.CartItem;
import com.soeasy.model.CustomerBean;
import com.soeasy.model.NutritionistBean;
import com.soeasy.model.NutritionistCategoryBean;
import com.soeasy.model.PostBean;
import com.soeasy.model.ProductBean;
import com.soeasy.service.mallService.ProductService;
import com.soeasy.service.nutritionistService.NutritionistCategoryService;
import com.soeasy.service.nutritionistService.NutritionistService;
import com.soeasy.validator.nutritionistValidator.NutritionistValidator;

@Controller
@RequestMapping("/nutritionistController")
@SessionAttributes("customerSignInSuccess")
public class NutritionistIdController {

	@Autowired
	NutritionistService nutritionistService;
	
	@Autowired
	ProductService productService; 

	@Autowired
	NutritionistCategoryService nutritionistCategoryService;
	
	@Autowired
	ServletContext context;
	
	// ?????????????????????
	@GetMapping("/getAllNutritionists")
	public String DisplayNutritionist(Model model) {
		model.addAttribute("nutritionists", nutritionistService.findAllByNutritionistId());
		return "/nutritionist/getAllNutritionists";
	}

	// ??????????????????
	@GetMapping(value = "/nutritionist/{nutritionistId}")
	public String getOneNutritionistById(@PathVariable("nutritionistId") Integer nutritionistId, Model model) {
		NutritionistBean nutritionistBean = nutritionistService.findByNutritionistId(nutritionistId);
		Random r = new Random();  //????????????
		
		List<ProductBean> chickenList=productService.findNutritionistByRelatedCategory(2); //??????????????????2??????
		List<ProductBean> beefList=productService.findNutritionistByRelatedCategory(3);    //??????????????????3??????
		chickenList.addAll(beefList);                                          //??????????????? 
		ProductBean productBean1= chickenList.get(r.nextInt(chickenList.size())); //???????????????????????????
		
		List<ProductBean> porkList=productService.findNutritionistByRelatedCategory(4);
		List<ProductBean> FishList=productService.findNutritionistByRelatedCategory(5);
		porkList.addAll(FishList);
		ProductBean productBean2=porkList.get(r.nextInt(porkList.size()));
		
		List<ProductBean> fruitList=productService.findNutritionistByRelatedCategory(1);
		ProductBean productBean3 = fruitList.get(r.nextInt(fruitList.size()));
		
		List<ProductBean> drinklist=productService.findNutritionistByRelatedCategory(7);
		ProductBean productBean4 = drinklist.get(r.nextInt(drinklist.size()));
		 
		model.addAttribute("nutritionistBean", nutritionistBean);
		model.addAttribute("productBean1", productBean1);
		model.addAttribute("productBean2", productBean2);
		model.addAttribute("productBean3", productBean3);
		model.addAttribute("productBean4", productBean4);
		return "/nutritionist/nutritionistIndex";
	}

	// ????????????????????????????????????????????????????????????
	@GetMapping(value = "/addNutritionist")
	public String emptyNutritionist(Model model) {
		NutritionistBean nutritionistBean = new NutritionistBean();
		// ??????????????????
		nutritionistBean.setNutritionistName("???????????????");
		nutritionistBean.setNutritionistGender("M");
		nutritionistBean.setNutritionistDegree("????????????????????????????????????");
		nutritionistBean.setNutritionistEmail("fatcat05@gmail.com");
		model.addAttribute("nutritionistBean", nutritionistBean);

		return "/nutritionist/addNutritionists";
	}

	// ???????????????
	@PostMapping(value = "/addNutritionist")
	public String addNutritionist(@ModelAttribute("nutritionistBean") NutritionistBean nutritionistBean,
			BindingResult result, Model model, HttpServletRequest request) {

		// ??????????????????????????????????????????
		NutritionistValidator validator = new NutritionistValidator();
		validator.validate(nutritionistBean, result);
		if (result.hasErrors()) {
			return "/nutritionist/addNutritionists";
		}

		// ??????addnutritionist.jsp????????????????????????
		MultipartFile nutritionistImg = nutritionistBean.getNutritionistMultiImg();

		// ??????Blob??????????????? Hibernate ???????????????
		if (nutritionistImg != null && !nutritionistImg.isEmpty()) {
			try {
				byte[] b = nutritionistImg.getBytes();
				Blob blob = new SerialBlob(b);
				nutritionistBean.setNutritionistImage(blob);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("????????????????????????: " + e.getMessage());
			}
		}

		// ?????????????????????
		long miliseconds = System.currentTimeMillis();
		Date Date = new Date(miliseconds);
		nutritionistBean.setNutritionistDate(Date);
		
		NutritionistCategoryBean nutritionistCategoryBean = nutritionistCategoryService.
				getNutritionistCategory(nutritionistBean.getNutritionistCategoryBean().getNutritionistCategoryId());
		nutritionistBean.setNutritionistCategoryBean(nutritionistCategoryBean);	
		nutritionistBean.setNutritionistCategory(nutritionistCategoryBean.getNutritionistCategoryName());
		
		try {
			nutritionistService.addNutritionist(nutritionistBean);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			return "nutritionist/addNutritionists";

		}

		return "redirect:/nutritionistController/getAllNutritionists";
	}
	
	// ???????????????????????????
	@RequestMapping(value = "/getImage/{nutritionistId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImage(HttpServletRequest resp,@PathVariable Integer nutritionistId ){
		String filePath="/image/NoImage.jpg";
		byte[] media = null;
		String filename = "";
		int len = 0;
		NutritionistBean nutritionistBean = nutritionistService.findByNutritionistId(nutritionistId);
		if(nutritionistBean !=null) {
			Blob blob=nutritionistBean.getNutritionistImage();
			if(blob!=null) {
				try {
					len=(int)blob.length();
					media = blob.getBytes(1, len);
				}catch(SQLException e) {
					throw new RuntimeException("StudentController???getPicture()??????SQLException: " + e.getMessage());
				}
			}else {
				media = toByteArray(filePath);
				filename = filePath;
			}
		}else {
			media = toByteArray(filePath);
			filename = filePath;
		}
		ResponseEntity<byte[]> re= new ResponseEntity<>(media, HttpStatus.OK);
		
		return re;
	}
	
	// ??????toByteArray
	private byte[] toByteArray(String filePath) {
		byte[] b = null;
		String realPath = context.getRealPath(filePath);
		try {
			File file = new File(realPath);
			long size = file.length();
			b = new byte[(int) size];
			InputStream fis = context.getResourceAsStream(filePath);
			fis.read(b);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b;
	}

	// ?????????????????????
	@ModelAttribute
	public void commonData(Model model) {
		Map<String, String> genderMap = new HashMap<>();
		genderMap.put("M", "??????");
		genderMap.put("F", "??????");
		model.addAttribute("genderMap", genderMap);

		List<NutritionistCategoryBean> nutritionistCategoryList = nutritionistCategoryService
				.getAllNutritionistCategorys();
		model.addAttribute("nutritionistCategoryList", nutritionistCategoryList);
	}
	
	// ?????????????????????(??????)
	@GetMapping(value="/getAllNutritionist.json",produces = { "application/json; charset=UTF-8" })
	public ResponseEntity<Map<String, Object>> getPageNutritionist(
			@RequestParam(value = "pageNo", required = false, defaultValue = "1")Integer pageNo,
			@RequestParam(value = "totalPage", required = false)Integer totalPage){
		
		if (pageNo == null) {
			pageNo = 1; // ??????????pageNo=??????
		}
		
		if (totalPage == null) {
			totalPage = nutritionistService.getTotalPages();
		}
		
		Map<String, Object> genderMap=new HashMap<>();
		List<NutritionistBean> listTarget= nutritionistService.getAllPageNutritionist(pageNo);

		genderMap.put("currPage", String.valueOf(pageNo));
		genderMap.put("totalPage", totalPage);
		genderMap.put("nutritionistPage", nutritionistService.getAllPageNutritionist(pageNo));
		
		ResponseEntity<Map<String, Object>> re =new ResponseEntity<>(genderMap, HttpStatus.OK);
		System.out.println("re="+re);
		return re;
	}
	
	
//	===========================???????????????????????????==========================================	
	@GetMapping("/buy/{productId}")
	public String buy(@PathVariable("productId") Integer productId, 
			HttpSession session,Model model,HttpServletRequest request) {

		// ????????????
		CustomerBean customerBean = (CustomerBean) model.getAttribute("customerSignInSuccess");
		if (customerBean == null) {
			return "redirect:/customerController/customerSignIn";
		}
		
		
	
		//  ????????????????????????
		if (session.getAttribute("cart")==null) {
			List<CartItem> cart = new ArrayList<CartItem>();
			cart.add(new CartItem(productService.findProductById(productId),1));
			session.setAttribute("cart", cart);
			}else {	
				List<CartItem>cart=(List<CartItem>)session.getAttribute("cart");
				int index = exists(productId,cart);
				if(index==-1) {
					//????????????????????????+1
					cart.add(new CartItem(productService.findProductById(productId),1));
				}else {
					int newQuantity=cart.get(index).getCartQuantity()+1;
					cart.get(index).setCartQuantity(newQuantity);
					
				}
		}
		String referer = request.getHeader("Referer"); 
		return "redirect:"+ referer;
		
	}
//	===========================(End)???????????????????????????==========================================	
	private int exists(Integer productId,List<CartItem>cart) {
		for(int i=0;i<cart.size();i++) {
			if(cart.get(i).getProduct().getProductId()==productId) {
				return i ;
			}
		}
		return -1;
	}
	
}

package com.soeasy.controller.customerController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.soeasy.model.CustomerBean;
import com.soeasy.model.CustomerHealthBean;
import com.soeasy.model.FavoriteBean;
import com.soeasy.model.PostBean;
import com.soeasy.model.ProductBean;
import com.soeasy.model.SportMapBean;
import com.soeasy.model.member.CustomerSignInBean;
import com.soeasy.service.customerService.CustomerService;
import com.soeasy.service.favoriteService.FavoriteService;
import com.soeasy.service.mallService.ProductService;
import com.soeasy.service.postService.PostService;
import com.soeasy.service.sportmapService.SportMapService;
import com.soeasy.util.GlobalService;
import com.soeasy.validator.customerValidator.CustomerBeanValidator;

@Controller
@RequestMapping("/customerController")
@SessionAttributes("customerSignInSuccess")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	ServletContext servletContext;
		
	@Autowired
	FavoriteService favoriteService;
	
	@Autowired
	SportMapService sportMapService;
	
	@Autowired
	PostService postService;
	
	@Autowired
	ProductService productService;

	//????????????--????????????_????????????--??????
	@GetMapping("/addCustomer")
	public String addCustomerSendForm(Model model) {
		CustomerBean customerBean = new CustomerBean();
		CustomerSignInBean customerSignInBean = new CustomerSignInBean();
		String signMode = "sign_up";
		
		customerBean.setCustomerName("?????????");
		customerBean.setCustomerEmail("fangTC2021@gmail.com");
		customerBean.setCustomerPassword("ab1234");
		customerBean.setCustomerCheckPassword("ab1234");
		
		customerSignInBean.setCustomerSignInEmail("fangTC2021@gmail.com");
		customerSignInBean.setCustomerSignInPassword("ab1234");
		
		model.addAttribute("customerBean", customerBean);
		model.addAttribute("customerSignInBean", customerSignInBean);
		model.addAttribute("signMode", signMode);
		return "customer/customerSignInUp";
	}
	
	//????????????--??????????????????
	@PostMapping("/addCustomer")
	public String addCustomerProcess(
				@ModelAttribute("customerBean")
				CustomerBean customerBean,
				BindingResult result, Model model,
				HttpServletRequest request
			) {
		//??????????????????????????????????????????
		CustomerBeanValidator validator = new CustomerBeanValidator();
		validator.validate(customerBean, result);
		if (result.hasErrors()) {
			//??????????????????
			model = resetSendForm(model);
			return "customer/customerSignInUp";
		}
		
		//???????????????
		//????????????????????????
		customerBean.setCustomerHealthBean(new CustomerHealthBean());
		//?????????????????????
		//customerBean.setShoppingcartBean(new ShoppingcartBean());
		//???????????????0
		customerBean.setCustomerScore(0);
		//????????????:??????
		customerBean.setCustomerStatus(GlobalService.CUSTOMER_STATUS_NORMAL);
		//??????????????????
		Timestamp registerTime = new Timestamp(System.currentTimeMillis());
		customerBean.setCustomerRegisterTime(registerTime);
		
		// ?????? customerEmail????????????
		if (customerService.emailExists(customerBean.getCustomerEmail())) {
			//??????????????????
			model = resetSendForm(model);
			result.rejectValue("customerEmail", "", "?????????????????????????????????");
			return "customer/customerSignInUp";
		}
				
		try {
			customerService.addCustomer(customerBean);
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ", ex.getMessage()=" + e.getMessage());
			result.rejectValue("customerName", "", "????????????????????????????????????..." + e.getMessage());
			return "customer/customerSignInUp";
		}
		
		return "redirect:/";
	}
	
	public Model resetSendForm(Model model) {
		//?????????????????????????????????
		CustomerSignInBean customerSignInBean = new CustomerSignInBean();
		//???????????????????????????
		String signMode = "sign_up";
		model.addAttribute("customerSignInBean", customerSignInBean);
		model.addAttribute("signMode", signMode);
		
		return model;
	}
	
	//????????????
	@GetMapping("/customerPage")
	public String customerPage(Model model) {
		CustomerBean customerSignInSuccess = (CustomerBean)model.getAttribute("customerSignInSuccess");
		CustomerBean originCustomer = customerService.findByCustomerId(customerSignInSuccess.getCustomerId());
		//------------------------------------------------------------------------
		//??????map?????????ItemId
		List<Integer> mapItemIds = new ArrayList<>();
		for (FavoriteBean favoriteBean : originCustomer.getFavoriteBeans()) {
			if(favoriteBean.getFavoriteCategory().equals("sportMap")) {
				mapItemIds.add(favoriteBean.getFavoriteItemId());
			}
		}
		//???mapItemIds??????sportMap
		List<SportMapBean> sportMaps = sportMapService.findAllById(mapItemIds);
		//------------------------------------------------------------------------
		//??????post?????????ItemId
		List<Integer> postItemIds = new ArrayList<>();
		for(FavoriteBean favoriteBean : originCustomer.getFavoriteBeans()) {
			if(favoriteBean.getFavoriteCategory().equals("post")) {
				postItemIds.add(favoriteBean.getFavoriteItemId());
			}
		}
		//???mapItemIds??????sportMap
		List<PostBean> posts = postService.findAllById(postItemIds);
		//------------------------------------------------------------------------
		//??????product?????????ItemId
		List<Integer> productItemIds = new ArrayList<>();
		for(FavoriteBean favoriteBean : originCustomer.getFavoriteBeans()) {
			if(favoriteBean.getFavoriteCategory().equals("productFavorite")) {
				productItemIds.add(favoriteBean.getFavoriteItemId());
			}
		}
		//???mapItemIds??????sportMap
		List<ProductBean> products = productService.findAllById(productItemIds);
		
		model.addAttribute("sportMaps", sportMaps);
		model.addAttribute("posts", posts);
		model.addAttribute("products", products);
		return "/customer/customerPage";
	}
	
	//????????????????????????
	@PostMapping(value = "/updateCustomerInfo", produces = { "application/json; charset=UTF-8" })
	
	public @ResponseBody Map<String, String> updateCustomerInfo(@RequestBody CustomerBean customerBean, Model model){
		//????????????ID???????????????????????????
		CustomerBean originalBean = customerService.findByCustomerId(customerBean.getCustomerId());
		
		//??????????????????
		Map<String, String> updateMessage = new HashMap<String, String>();
		
		if(customerBean.getCustomerName() == null) {
			updateMessage.put("updateMessage", "??????????????????");
		}
		
		
		//???????????????????????????????????????
		originalBean.setCustomerName(customerBean.getCustomerName());
		originalBean.setCustomerNickname(customerBean.getCustomerNickname());
		originalBean.setCustomerPhone(customerBean.getCustomerPhone());
		originalBean.setCustomerBirthDay(customerBean.getCustomerBirthDay());
		
		//save????????????
		customerService.updateCustomer(originalBean);
		
		//????????????????????????session
		model.addAttribute("customerSignInSuccess", originalBean);
		
		//??????????????????
		updateMessage.put("updateMessage", "????????????");
		
		return updateMessage;
	}
	
		//????????????????????????
		@PostMapping(value = "/updateCustomerHealthInfo", produces = { "application/json; charset=UTF-8" })
		
		public @ResponseBody Map<String, String> updateCustomerHealthInfo(@RequestBody CustomerBean customerBean, Model model){
			//????????????ID???????????????????????????
			CustomerBean originalCustomer = customerService.findByCustomerId(customerBean.getCustomerId());
			CustomerHealthBean updateCustomerHealthBean = customerBean.getCustomerHealthBean();
			//??????????????????
			Map<String, String> updateHealthMessage = new HashMap<String, String>();
			
			
			
			//???????????????????????????????????????
			originalCustomer.setCustomerHealthBean(updateCustomerHealthBean);
			
//			System.out.println(updateCustomerHealthBean.getCustomerGender());
//			System.out.println(updateCustomerHealthBean.getCustomerDiet());
//			System.out.println(updateCustomerHealthBean.getCustomerExerciseHabits());
			//save????????????
			customerService.updateCustomer(originalCustomer);
			
			//????????????????????????session
			model.addAttribute("customerSignInSuccess", originalCustomer);
			
			//??????????????????
			updateHealthMessage.put("updateHealthMessage", "????????????");
			
			return updateHealthMessage;
		}
		
		//??????????????????
		@PostMapping("/uploadCustomerImg")
		public String uploadCustomerImg(@RequestParam("customerImgUpload")MultipartFile customerMultiImg, Model model) {
			
			CustomerBean customerSignInSuccess = (CustomerBean)model.getAttribute("customerSignInSuccess");
			CustomerBean originalCustomer = customerService.findByCustomerId(customerSignInSuccess.getCustomerId());
			
			//????????????MultipartFile --> Blob
			if(customerMultiImg != null && !customerMultiImg.isEmpty()) {
				try {
					byte[] bImg = customerMultiImg.getBytes();
					Blob blob = new SerialBlob(bImg);
					originalCustomer.setCustomerImg(blob);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			//save????????????
			customerService.updateCustomer(originalCustomer);
			
			return "/customer/customerPage";
		}
		
		//?????????????????????????????????
		@GetMapping("/getCustomerImg")
		public ResponseEntity<byte[]> getCustomerImg(Model model) {
			//???session???????????????ID
			Integer customerId = ((CustomerBean)model.getAttribute("customerSignInSuccess")).getCustomerId();
			CustomerBean originalCustomer = customerService.findByCustomerId(customerId);
			
			Blob customerImg = originalCustomer.getCustomerImg();
			
			InputStream is = null;
			String fileName = null;
			byte[] media = null;
			ResponseEntity<byte[]> responseEntity = null;
			
			try {
				if (customerImg != null) {
					is = customerImg.getBinaryStream();
				}
				// ??????????????????????????????????????????????????????(/images/salad.png)	
				if(is == null) {
					fileName = "salad.png";
					is = servletContext.getResourceAsStream(
							"/images/" + fileName);
				}
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				// ???InputStream???????????????????????????OutputStream??????
				int len = 0;
				byte[] bytes = new byte[8192];
				
				while ((len = is.read(bytes)) != -1) {
					baos.write(bytes, 0, len);
				}
				
				media = baos.toByteArray();
				responseEntity = new ResponseEntity<>(media, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				try {
					if (is != null) is.close();
				} catch(IOException e) {
					;
				}
			}
			return responseEntity;
		}
		
		//???ID??????????????????
		@GetMapping("/getCustomerImgById/{customerId}")
		public ResponseEntity<byte[]> getCustomerImgById(@PathVariable("customerId")Integer customerId){
			CustomerBean originalCustomer = customerService.findByCustomerId(customerId);
			
			Blob customerImg = originalCustomer.getCustomerImg();
			
			InputStream is = null;
			String fileName = null;
			byte[] media = null;
			ResponseEntity<byte[]> responseEntity = null;
			
			try {
				if (customerImg != null) {
					is = customerImg.getBinaryStream();
				}
				// ??????????????????????????????????????????????????????(/images/salad.png)	
				if(is == null) {
					fileName = "salad.png";
					is = servletContext.getResourceAsStream(
							"/images/" + fileName);
				}
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				// ???InputStream???????????????????????????OutputStream??????
				int len = 0;
				byte[] bytes = new byte[8192];
				
				while ((len = is.read(bytes)) != -1) {
					baos.write(bytes, 0, len);
				}
				
				media = baos.toByteArray();
				responseEntity = new ResponseEntity<>(media, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				try {
					if (is != null) is.close();
				} catch(IOException e) {
					;
				}
			}
			return responseEntity;
		}
		
		//??????????????????
		@GetMapping("/customerForgotPassword")
		public String customerForgotPassword() {
			return "/customer/customerPasswordReset";
		}
		
		
}

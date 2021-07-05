package com.soeasy.controller.adminController.adminManage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
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
import org.springframework.web.multipart.MultipartFile;

import com.soeasy.model.ProductBean;
import com.soeasy.model.ProductCategoryBean;
import com.soeasy.model.SportCategoryBean;
import com.soeasy.service.mallService.ProductCategoryService;
import com.soeasy.service.mallService.ProductService;
import com.soeasy.validator.mallValidator.ProductBeanValidator;


@Controller
@RequestMapping("/admin/adminManage")
public class AdminProduct {
			
		@Autowired
		private ProductService productService;
		
		@Autowired
		ServletContext context;
		
		@Autowired
		private ProductCategoryService productCategoryService;

		
		
		//頁面跳轉
				@RequestMapping("/adminProduct")
				public String viewPage(Model model){
					String keyword = null;
					return ListByPage(model,1,"productName","asc",keyword);
				}


			//showProduct
			@GetMapping("/adminProduct/{pageNo}")
			public String ListByPage(Model model,
			        @PathVariable("pageNo") int currentPage,
			        @Param("sortField")String sortField,
			        @Param("sortDir")String sortDir,
			        @Param("keyword")String keyword)
					{
					Page<ProductBean> page = productService.findAllByPage(currentPage,sortField,sortDir,keyword);
					    
					    System.out.println("印出來all products");
					    //分頁參數
					    model.addAttribute("currentPage", currentPage);
					    model.addAttribute("totalPages", page.getTotalPages());
					    model.addAttribute("totalItems", page.getTotalElements());
					   //羅列產品
					    List<ProductBean> list = page.getContent();
					    model.addAttribute("product", list);
					    //用各類別進去排序&
					    model.addAttribute("sortField", sortField);
					    model.addAttribute("sortDir", sortDir);
					    model.addAttribute("keyword",keyword);
					    
					    String reverseSortDir =sortDir.equals("asc") ? "desc":"asc";
					    model.addAttribute("reverSortDir", reverseSortDir);
					     
					    return "admin/adminMall/adminProduct";
					}
				

			
			//使用ID查詢單一產品
			@GetMapping("/adminProduct/search/{productId}")
			 public String getOne(@PathVariable Integer productId,Model model) {
			      ProductBean product = productService.findProductById(productId);
		      		model.addAttribute("product", product);
			        	System.out.println("印出來單一產品了");
			            return "admin/adminMall/adminOneProduct";
			       
			    }
		

			//新增產品 使用Post請求
			//跳轉add提交頁  於新增時，送出空白的表單讓使用者輸入資料

			@GetMapping("/add")
			public String add(Model model) {
				ProductBean product = new ProductBean();
				product.setProductName("麥當勞歡樂全家餐");
				product.setProductDescription("快樂肥宅餐");
				product.setCategory(5);
				product.setProductAmount(100);
				product.setProductCalories(500);
				product.setProductPrice(210);
				product.setProductCost(300);
				product.setProductDate(java.sql.Date.valueOf("2021-07-20"));
				model.addAttribute("product", product);

			return "admin/adminMall/adminAddProduct";
		}
		
//		==============================
//			=========================

			// 當使用者填妥資料按下Submit按鈕後，本方法接收將瀏覽器送來的會員資料，新進行資料的檢查，
			// 若資料有誤，轉向寫入錯誤訊息的網頁，若資料無誤，則呼叫Service元件寫入資料庫
			// BindingResult 參數必須與@ModelAttribute修飾的參數連續編寫，中間不能夾其他參數
			@PostMapping("/add")
				public String addProduct(
						@ModelAttribute("product") /* @Valid */ ProductBean product, 
						BindingResult result, Model model,HttpServletRequest request) {
				
					
				ProductBeanValidator validator = new ProductBeanValidator();
				// 呼叫Validate進行資料檢查
				validator.validate(product, result);
				if (result.hasErrors()) {			
					return "mall/addProduct";
				}
					// 取得addProduct.jsp所送來的圖片資訊
				MultipartFile picture = product.getProductMultiImg();
			
				// 建立Blob物件，交由 Hibernate 寫入資料庫
				if (picture != null && !picture.isEmpty()) {
					try {
						byte[] b = picture.getBytes();
						Blob blob = new SerialBlob(b);
						product.setProductImg(blob);
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
					}
				}
					// 自動帶入創建時間
					long miliseconds = System.currentTimeMillis();
					Date Date = new Date(miliseconds);
					product.setProductDate(Date);
					try {
						productService.save(product);
					} catch (org.hibernate.exception.ConstraintViolationException e) {
						return "admin/adminMall/adminProduct";
				}

					
				// 跳轉至查詢所有頁面
					return "redirect:/admin/adminManage/adminProduct";
				}
			
			
			
			// 讀圖轉成位元組陣列
				@RequestMapping(value = "/getImage/{productId}", method = RequestMethod.GET)
				public ResponseEntity<byte[]> getImage(HttpServletRequest resp,@PathVariable Integer productId ){
					String filePath="/image/NoImage.jpg";
					byte[] media = null;
					String filename = "";
					int len = 0;
					ProductBean product = productService.findProductById(productId);
					if(product !=null) {
						Blob blob=product.getProductImg();
						if(blob!=null) {
							try {
								len=(int)blob.length();
								media = blob.getBytes(1, len);
							}catch(SQLException e) {
								throw new RuntimeException("StudentController的getPicture()發生SQLException: " + e.getMessage());
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
				
				// 方法toByteArray
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
			
			
			
//			=========================================================
			
				// 刪除一筆紀錄
				//送不出DELETE 先用POST
					@GetMapping("/adminDelete/{productId}")
					public String delete(@PathVariable("productId") Integer productId) {
				        productService.deleteById(productId);
			        	System.out.println("刪除products");
						return "redirect:/admin/adminManage/adminProduct"; 
					}
					
				
//			=========================================================
//			==================== 修改 =================================
			
					// 修改時，送回含有會員資料的表單，讓使用者進行修改
					// 由這個方法送回修改記錄的表單...
						@GetMapping("/adminUpdate/{productId}")
						// 取得原始的PostBean物件
						public String showDataForm(@PathVariable("productId") Integer productId, Model model) {
							ProductBean product = productService.findProductById(productId);
				      		model.addAttribute("product", product);
							return "admin/adminMall/adminUpdateProduct";
				
						}
						
						
						@PostMapping("/adminUpdate/{productId}")
						// BindingResult 參數必須與@ModelAttribute修飾的參數連續編寫，中間不能夾其他參數
						public String modify(
								@ModelAttribute("product") ProductBean product, 
								BindingResult result, 
								Model model,
								@PathVariable Integer productId, 
								HttpServletRequest request) {
							

							// 自動帶入創建時間
							long miliseconds = System.currentTimeMillis();
							Date Date = new Date(miliseconds);
							product.setProductDate(Date);
							
							
							
							// 取得addProduct.jsp所送來的圖片資訊
							MultipartFile picture = product.getProductMultiImg();
							// 建立Blob物件，交由 Hibernate 寫入資料庫
							if (picture != null && !picture.isEmpty()) {
								try {
									byte[] b = picture.getBytes();
									Blob blob = new SerialBlob(b);
									product.setProductImg(blob);
								} catch (Exception e) {
									e.printStackTrace();
									throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
								}
							}
								try {
									productService.save(product);
								} catch (org.hibernate.exception.ConstraintViolationException e) {
									return "mall/addProduct";
							}

						
							
							productService.save(product);
							return "redirect:/admin/adminManage/adminProduct";
						}
						
					
					
//			=========================================================

	
		

		

	}

		
		


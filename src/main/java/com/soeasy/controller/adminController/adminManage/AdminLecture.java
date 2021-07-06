package com.soeasy.controller.adminController.adminManage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.soeasy.model.LectureBean;
import com.soeasy.model.LectureCategoryBean;
import com.soeasy.service.lectureService.LectureCategoryService;
import com.soeasy.service.lectureService.LectureService;
import com.soeasy.validator.lectureValidator.LectureBeanValidator;

@Controller
@RequestMapping("/admin/adminManage")
public class AdminLecture {

	@Autowired
	LectureService lectureService;

	@Autowired
	LectureCategoryService lectureCategoryService;

	@Autowired
	ServletContext context;

	// 後台講座首頁
	@GetMapping("/adminLecture")
	public String adminLectureIndex(Model model) {
		model.addAttribute("lecture", lectureService.getAllByLectureId());
		return "/admin/adminLecture/adminLecture";
	}

	// 新增講座，先送一個空白表單，並給予初值
		@GetMapping("/adminLecture/addLecture")
		public String showEmptyForm(Model model) {
			LectureBean lectureBean = new LectureBean();
			// 預設表單資料
			lectureBean.setLectureTitle("12週居家徒手健身計畫");
			lectureBean.setLectureCategory("運動");
			lectureBean.setLectureContent(
					"這是一門針對全身肌肉訓練的線上課程。它提供給日常忙碌、沒有時間或不想上健身房，想要在家自我訓練的人。你可以依照自己的時間，隨時開始這項計畫。我所設計的動作不需要任何健身設備，全程徒手就可以完成。\r\n"
							+ "這門課程將透過12週的自主訓練，幫助你強化肌力、改善體能，並增強自我對肌肉的控制力，讓你減少腰痠背痛，進一步擁有緊實的曲線！<br>七年資深健身教練。深感健身對一般大眾有無窮的潛在效益，經營個人部落格「健美女大生」宣導正確觀念。靠著從小由媽媽淬煉出一手流利的文筆，碩士班訓練出的資訊統整與思辨能力，加上推廣健身的滿腔熱情，把艱澀的健身知識化為親切易讀、不失科學專業的文章，讓讀者深深受益。著有：《健身從深蹲開始》。");
//				lectureBean.setLectureStatus("1");
			lectureBean.setLectureMultiImg(null);

			model.addAttribute("lectureBean", lectureBean);

			return "/admin/adminLecture/adminAddLecture";
		}
		
		// 新增講座
		@PostMapping(value = "/adminLecture/addLecture")
		public String addLecture(@ModelAttribute("lectureBean") LectureBean lectureBean, BindingResult result, Model model,
				HttpServletRequest request) {

			// 檢測不正當欄位並回傳提示訊息
			LectureBeanValidator validator = new LectureBeanValidator();
			validator.validate(lectureBean, result);
			if (result.hasErrors()) {
				return "/admin/adminLecture/adminAddLecture";
			}

			// 取得adminLectureUpdate.jsp所送來的圖片資訊
			MultipartFile lectureImg = lectureBean.getLectureMultiImg();

			// 建立Blob物件，交由 Hibernate 寫入資料庫
			if (lectureImg != null && !lectureImg.isEmpty()) {
				try {
					byte[] b = lectureImg.getBytes();
					Blob blob = new SerialBlob(b);
					lectureBean.setLectureImg(blob);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
				}
			}
			
			lectureService.addLecture(lectureBean);

//			// 判斷講座時間
//			long miliseconds = System.currentTimeMillis();
//			Date Date = new Date(miliseconds);
//			lectureBean.setLectureDate(Date);
	//
//			try {
//				lectureService.addLecture(lectureBean);
//			} catch (org.hibernate.exception.ConstraintViolationException e) {
//				return "/admin/adminLecture/adminAddLecture";
	//
//			}

			return "redirect:/admin/adminManage/adminLecture";
		}
	
	// 讀圖轉成位元組陣列
	@RequestMapping(value = "/getImage/{lectureId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImage(HttpServletRequest resp, @PathVariable Integer lectureId) {
		String filePath = "/image/NoImage.jpg";
		byte[] media = null;
		String filename = "";
		int len = 0;
		LectureBean lectureBean = lectureService.getOneByLectureId(lectureId);
		if (lectureBean != null) {
			Blob blob = lectureBean.getLectureImg();
			if (blob != null) {
				try {
					len = (int) blob.length();
					media = blob.getBytes(1, len);
				} catch (SQLException e) {
					throw new RuntimeException("StudentController的getPicture()發生SQLException: " + e.getMessage());
				}
			} else {
				media = toByteArray(filePath);
				filename = filePath;
			}
		} else {
			media = toByteArray(filePath);
			filename = filePath;
		}
		ResponseEntity<byte[]> re = new ResponseEntity<>(media, HttpStatus.OK);

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

	// 產生講座類型radio選單
	@ModelAttribute
	public void commonData(Model model) {
		Map<String, String> lectureCategory = new HashMap<>();
		lectureCategory.put("Eat", "飲食");
		lectureCategory.put("Exercise", "運動");
		model.addAttribute("lectureCategory", lectureCategory);

		List<LectureCategoryBean> lectureCategoryList = lectureCategoryService.getAllLectureCategories();
		model.addAttribute("lectureCategoryList", lectureCategoryList);
	}

//		// 產生講座radio選單
//		@ModelAttribute
//		public void commonData(Model model) {
//			Map<String, String> lectureStatus = new HashMap<>();
//			lectureStatus.put("Ongoing", "進行中");
//			lectureStatus.put("Upcoming", "即將舉辦");
//			lectureStatus.put("Archived", "精采回顧");
//			model.addAttribute("lectureStatus", lectureStatus);
//			
//			List<LectureCategoryBean> lectureStatus = lectureCategoryService
//					.getAllLectureCategories();
//			model.addAttribute("lectureCategoryList", lectureCategoryList);
//		}

	// 刪除單筆講座
	@PostMapping("/adminLecture/deleteLecture/{lectureId}")
	public String deleteLecture(@PathVariable("lectureId") Integer lectureId) {
		lectureService.deleteLecture(lectureId);
		return "redirect:/admin/adminManage/adminLecture";
	}

	// 查詢修改單筆講座表單
	@GetMapping(value = "/adminLecture/updateLecture/{lectureId}")
	public String getOneByLectureId(@PathVariable("lectureId") Integer lectureId, Model model) {
		LectureBean lectureBean = lectureService.getOneByLectureId(lectureId);
		model.addAttribute("lectureBean", lectureBean);

		return "/admin/adminLecture/adminUpdateLecture"; // 帶出修改資料

	}

	// 查詢修改單筆講座表單
	@PostMapping("/adminLecture/updateLecture/{lectureId}")
	public String emptyLecture(@ModelAttribute("lectureBean") LectureBean lectureBean, BindingResult result,
			Model model, @PathVariable("lectureId") Integer lectureId, HttpServletRequest request) {
		LectureBeanValidator validator = new LectureBeanValidator();
		validator.validate(lectureBean, result);
		if (result.hasErrors()) {
			return "/admin/adminLecture/adminUpdateLecture";
		}

		// 取得addnutritionist.jsp所送來的圖片資訊
		MultipartFile lectureImg = lectureBean.getLectureMultiImg();

		// 建立Blob物件，交由 Hibernate 寫入資料庫
		if (lectureImg != null && !lectureImg.isEmpty()) {
			try {
				byte[] b = lectureImg.getBytes();
				Blob blob = new SerialBlob(b);
				lectureBean.setLectureImg(blob);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}
		}

//		// 講座時間
//		long miliseconds = System.currentTimeMillis();
//		Date Date = new Date(miliseconds);
//		lectureBean.setLectureDate(Date);
//
//		try {
//			lectureService.updateLecture(lectureBean);
//		} catch (org.hibernate.exception.ConstraintViolationException e) {
//			return "/admin/adminLecture/adminUpdateLecture";
//		}
		
		return "redirect:/admin/adminManage/adminLecture";
	}

}

package com.howard.rentalorder.controller;

import com.howard.rentalmytrack.service.RentalMyTrackService;
import com.howard.rentalmytrack.vo.RentalMyTrackVo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;


@WebServlet("/rentalorder/RentalOrderController")
public class RentalOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");


        // 來自新增頁面(addTrack.jsp)，新增追蹤品的請求
        if ("insert".equals(action)) {

            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

            // 檢查租借品編號
//			Integer No = null;
//			try {
//				rOrdNo = Integer.valueOf(req.getParameter("rOrdNo").trim());
//			} catch (NumberFormatException e) {
////				if (String.valueOf(rNo).trim() == "") {
////					errorMsgs.put("rNo", "租借品編號不能空白");
////				}
//				errorMsgs.put("rOrdNo", "租借品編號請填數字");
//			}
            // 檢查會員編號
            Integer memNo = null;
            try {
                memNo = Integer.valueOf(req.getParameter("memNo").trim());
            } catch (NumberFormatException e) {
                errorMsgs.put("memNo", "會員編號請填數字");
            } catch (NullPointerException nullPointerException) {
                errorMsgs.put("memNo", "會員編號不能空白");
            }

            // 檢查訂購人姓名
            String nameReg = "^[\\u4e00-\\u9fa5a-zA-Z]$";
            String rByrName = req.getParameter("rByrName");
            if (rByrName == null || rByrName.trim().isEmpty()) {
                errorMsgs.put("rByrName", "訂購人姓名 : 請勿空白!");
            } else if (!rByrName.equals(nameReg)) {
                errorMsgs.put("rByrName", "訂購人姓名 : 只能是中文或英文!");
            }

            // 檢查訂購人手機號碼
            String phoneReg = "^[0][9][0-9]{8}$";
            String rByrPhone = req.getParameter("rByrPhone");
            if (rByrPhone == null || rByrPhone.trim().isEmpty()) {
                errorMsgs.put("rByrPhone", "訂購人手機號碼 : 請勿空白!");
            } else if (!rByrPhone.equals(phoneReg)) {
                errorMsgs.put("rByrPhone", "訂購人手機號碼 : 請輸入09開頭的10位數字!");
            }

            // 檢查訂購人 Email
            String emailReg = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            String rByrEmail = req.getParameter("rByrEmail");
            if (rByrEmail == null || rByrEmail.trim().isEmpty()) {
                errorMsgs.put("rRcvName", "Email : 請勿空白!");
            } else if (!rByrPhone.equals(emailReg)) {
                errorMsgs.put("rByrEmail", "Email : 格式不正確喔");
            }

            // 檢查收件人姓名 (直接拿 訂購人姓名 那邊的正則表達式來用)
            String rRcvName = req.getParameter("rRcvName");
            if (rRcvName == null || rRcvName.trim().isEmpty()) {
                errorMsgs.put("rRcvName", "收件人姓名 : 請勿空白!");
            } else if (!rRcvName.equals(nameReg)) {
                errorMsgs.put("rRcvName", "收件人姓名 : 只能是中文或英文!");
            }

            // 檢查收件人手機號碼 (直接拿 訂購人手機號碼 那邊的正則表達式來用)
            String rRcvPhone = req.getParameter("rRcvPhone");
            if (rByrPhone == null || rByrPhone.trim().isEmpty()) {
                errorMsgs.put("rByrPhone", "收件人手機號碼 : 請勿空白!");
            } else if (!rByrPhone.equals(phoneReg)) {
                errorMsgs.put("rByrPhone", "收件人手機號碼 : 請輸入09開頭的10位數字!");
            }

            // 檢查取貨方式
            Byte rTakeMethod = null;
            try {
                rTakeMethod = Byte.valueOf(req.getParameter("rTakeMethod"));
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }

            // 檢查宅配住址
            String addrReg = "^[\\u4e00-\\u9fa5a0-9]{10,100}$";
            String rAddr = req.getParameter("rAddr");
            if (rAddr == null || rAddr.trim().isEmpty()) {
                errorMsgs.put("rAddr", "宅配地址 : 請勿空白!");
            } else if (!rAddr.equals(addrReg)) {
                errorMsgs.put("rAddr", "宅配地址 : 格式不正確喔");
            }

            // 檢查付款方式
            Byte rPayMethod = null;
            try {
                rPayMethod = Byte.valueOf(req.getParameter("rPayMethod"));
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }


//            expRentalDate = new Date(System.currentTimeMillis());

//			RentalMyTrackVo rentalMyTrackVO = new RentalMyTrackVo();
//
//			rentalMyTrackVO.setrNo(rNo == null? null : Integer.valueOf(rNo));
//			rentalMyTrackVO.setmemNo(memNo == null? null : Integer.valueOf(memNo));
//			rentalMyTrackVO.setexpRentalDate(expRentalDate);
//
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("trackVO", rentalMyTrackVO); // 含有輸入格式錯誤的trackVO物件,也存入req
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("addTrack.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//
//			/***************************2.開始新增資料***************************************/
//			RentalMyTrackService tSvc = new RentalMyTrackService();
//			rentalMyTrackVO = tSvc.addTrack(Integer.valueOf(rNo), Integer.valueOf(memNo),
//					expRentalDate);
//
//			/***************************3.新增完成,準備轉交(Send the Success view)***********/
//			String url = "/rentalmytrack/listAllTrack.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//			successView.forward(req, res);
//
		} // 新增結束
//
//		// 來自所有追蹤品頁面(listAllTrack.jsp)、刪除追蹤品頁面(deleteTrack.jsp)，刪除單筆的請求
//        if ("delete".equals(action)) {
//
//            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//            req.setAttribute("errorMsgs", errorMsgs);
//
////            Integer rNo = Integer.valueOf(req.getParameter("rNo"));
////            Integer memNo = Integer.valueOf(req.getParameter("memNo"));
//
//			/***********************1.接收請求參數 - 輸入格式的錯誤處理************************/
//
//			Integer rNo = null;
//			try {
//				rNo = Integer.valueOf(req.getParameter("rNo").trim());
//			} catch (NumberFormatException e) {
//				errorMsgs.put("rNo", "租借品編號請填數字");
//			}
//			// 檢查會員編號
//			Integer memNo = null;
//			try {
//				memNo = Integer.valueOf(req.getParameter("memNo").trim());
//			} catch (NumberFormatException e) {
//				errorMsgs.put("memNo", "會員編號請填數字");
//			} catch (NullPointerException nullPointerException) {
//				errorMsgs.put("memNo", "會員編號不能空白");
//			}
//
//			if (!errorMsgs.isEmpty()) {
////				req.setAttribute("trackVO", trackVO); // 含有輸入格式錯誤的trackVO物件,也存入req
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/rentalmytrack/deleteTrack.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//
//
//            /***************************2.開始刪除資料***************************************/
//            RentalMyTrackService rentalMyTrackService = new RentalMyTrackService();
//            rentalMyTrackService.deleteTrack(rNo, memNo);
//
//            /***************************3.刪除完成,準備轉交(Send the Success view)***********/
//            String url = "/rentalmytrack/listAllTrack.jsp";
//            RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功，轉交回送出刪除的來源網站
//            successView.forward(req, res);
//
//        } // 刪除結束
//
//
//        // 來自修改頁面(update_emp_input.jsp)，修改追蹤品資料的請求
//		if ("update".equals(action)) {
//
//			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//			String rNoReg = "^([1-9]|[1-3][0-9]|40)$";
//
//			// 檢查租借品編號
//			Integer rNo = null;
//			try {
//				rNo = Integer.valueOf(req.getParameter("rNo").trim());
//			} catch (NumberFormatException e) {
//				errorMsgs.put("rNo", "租借品編號請填數字");
//			} catch (NullPointerException nullPointerException) {
//				errorMsgs.put("rNo", "租借品編號不能空白");
//			}
//			// 檢查會員編號
//			Integer memNo = null;
//			try {
//				memNo = Integer.valueOf(req.getParameter("memNo").trim());
//			} catch (NumberFormatException e) {
//				errorMsgs.put("memNo", "會員編號請填數字");
//			} catch (NullPointerException nullPointerException) {
//				errorMsgs.put("memNo", "會員編號不能空白");
//			}
//			// 檢查期望租借日期
//			Date expRentalDate = null;
//			try {
//				expRentalDate = Date.valueOf(req.getParameter("expRentalDate"));
//			} catch (IllegalArgumentException e) {
//				expRentalDate = new Date(System.currentTimeMillis());
//				errorMsgs.put("expRentalDate", "請輸入日期!");
//			}
//
//			RentalMyTrackVo rentalMyTrackVO = new RentalMyTrackVo();
//
//			rentalMyTrackVO.setrNo(rNo == null ? null : Integer.valueOf(rNo));
//			rentalMyTrackVO.setmemNo(memNo == null ? null : Integer.valueOf(memNo));
//			rentalMyTrackVO.setexpRentalDate(expRentalDate);
//
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("trackVO", rentalMyTrackVO); // 含有輸入格式錯誤的trackVO物件,也存入req
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/rentalmytrack/update_mytrack_input.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//
//			/***************************2.開始修改資料*****************************************/
//			RentalMyTrackService tSvc = new RentalMyTrackService();
//			rentalMyTrackVO = tSvc.updateTrack(Integer.valueOf(rNo), Integer.valueOf(memNo),
//					expRentalDate);
//
//			/***************************3.修改完成,準備轉交(Send the Success view)*************/
//			req.setAttribute("trackVO", rentalMyTrackVO); // 資料庫update成功後,正確的的empVO物件,存入req
//			String url = "listOneTrack.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//			successView.forward(req, res);
//
//		} // 修改結束
//
//		// 來自所有追蹤租借品頁面(listAllNO.jsp)，修改追蹤品資料的請求
//        if ("getOne_For_Update".equals(action)) { //
//
//            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//            req.setAttribute("errorMsgs", errorMsgs);
//
//            /***************************1.接收請求參數****************************************/
//            Integer rNo = Integer.valueOf(req.getParameter("rNo"));
//            Integer memNo = Integer.valueOf(req.getParameter("memNo"));
//
//            /***************************2.開始查詢資料****************************************/
//            RentalMyTrackService rentalMyTrackService = new RentalMyTrackService();
//            RentalMyTrackVo rentalMyTrackVO = rentalMyTrackService.getOneTrack(rNo, memNo);
//
//            /***************************3.查詢完成,準備轉交(Send the Success view)************/
//
//            String url = "update_mytrack_input.jsp";
//        	req.setAttribute("trackVO", rentalMyTrackVO);
//            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_member_input.jsp
//            successView.forward(req, res);
//        }
//
//
//		// 來自首頁(select_page.jsp)，查詢單筆的請求
//		if ("getOne_For_Display".equals(action)) {
//
//			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/***************************1.接收請求參數、輸入格式的錯誤處理**********************/
//
//			// 檢查租借品編號
//			Integer rNo = null;
//			try {
//				rNo = Integer.valueOf(req.getParameter("rNo").trim());
//			} catch (NumberFormatException e) {
//				errorMsgs.put("rNo", "租借品編號請填數字");
//			} catch (NullPointerException nullPointerException) {
//				errorMsgs.put("rNo", "租借品編號不能空白");
//			}
//			// 檢查會員編號
//			Integer memNo = null;
//			try {
//				memNo = Integer.valueOf(req.getParameter("memNo").trim());
//			} catch (NumberFormatException e) {
//				errorMsgs.put("memNo", "會員編號請填數字");
//			} catch (NullPointerException nullPointerException) {
//				errorMsgs.put("memNo", "會員編號不能空白");
//			}
//
//
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("select_page.jsp");
//				failureView.forward(req, res);
//				return;//程式中斷
//			}
//
//			/***************************2.開始查詢資料*****************************************/
//			RentalMyTrackService tSvc = new RentalMyTrackService();
//			RentalMyTrackVo rentalMyTrackVO = tSvc.getOneTrack(rNo, memNo);
//			if (rentalMyTrackVO == null) {
//				errorMsgs.put("noData", "查無資料");
//			}
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("select_page.jsp");
//				failureView.forward(req, res);
//				return;//程式中斷
//			}
//
//			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//			req.setAttribute("trackVO", rentalMyTrackVO); // 資料庫取出的empVO物件,存入req
//			String url = "listOneTrack.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//			successView.forward(req, res);
//
//		} // 查詢單筆結束

    } // doPost 結束

}
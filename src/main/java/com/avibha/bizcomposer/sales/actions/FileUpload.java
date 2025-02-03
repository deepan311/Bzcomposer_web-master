/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.sales.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.avibha.bizcomposer.sales.dao.ItemInfo;
import com.avibha.bizcomposer.sales.forms.ItemForm;
import com.avibha.common.log.Loger;

public class FileUpload extends Action {
	/**
	 * This is the main action called from the Struts framework.
	 * 
	 * @param mapping
	 *            The ActionMapping used to select this instance.
	 * @param form
	 *            The optional ActionForm bean for this request.
	 * @param request
	 *            The HTTP Request we are processing.
	 * @param response
	 *            The HTTP Response we are processing.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String forward = "false";
		String fileSep = System.getProperty("file.separator");
		ItemForm itemFrm = (ItemForm) form;
		FileOutputStream fo = null;
		try {
			// FileUpload fup = new FileUpload();
			FormFile f = itemFrm.getPhotoName();

			Loger.log("value of f: " + f);
			String contentType = "";
			String filename = "";
			if (f != null) {
				contentType = f.getContentType();
				Loger.log(contentType);
				filename = f.getFileName();
			}

			Loger.log("file name: " + filename);

			StringTokenizer st = new StringTokenizer(contentType, "/");
			if (st.hasMoreTokens()) {
				String val = st.nextToken("/");
				if ("image".equals(val) == true) {
					forward = "true";
					Loger.log(servlet.getServletConfig().getServletContext()
							.getRealPath("/"));
					if (filename.length() > 0) {

						String s = this.servlet.getServletContext()
								.getRealPath("uploadedImages")
								+ fileSep + filename;
						Loger.log("file name path: " + s);
						byte[] contentArray = f.getFileData();

						// store file onto the server

						ItemInfo item = new ItemInfo();
						if (item.updatePicName(filename))
							;
						if (contentArray != null || contentArray.length > 0) {
							File tosave = new File(s);
							fo = new FileOutputStream(tosave);
							fo.write(contentArray);
						}
					}

				}
			}

		} catch (IOException ee) {
			Loger.log(2, "error in execute() in PhotoAction class" + ee);
		} catch (Exception eee) {
			Loger.log(2, "error in execute() in PhotoAction class" + eee);
		} finally {
			try {
				if (fo != null)
					fo.close();
			} catch (Exception eeee) {
				Loger.log(2, "File Not Stored Properly in PhotoAction class"
						+ eeee);
			}
		}
		String msg = "Please upload image!!!";
		if (forward.equals("true")) {
			msg = "Image uploaded successfully";
		}

		request.setAttribute("msg", msg);
		Loger.log(msg);

		return mapping.findForward("success");
	}

}

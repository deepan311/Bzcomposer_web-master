package com.avibha.bizcomposer.sales.dao;
import java.io.InputStream;

import org.apache.struts.upload.FormFile;

public class FrmFile implements FormFile{
	private String file;
	public FrmFile(){
		
	}
	public void destroy() {
	
	}
	public String getContentType() {
		String content="";
		
		return content;
	}
	public byte[] getFileData(){
		byte[] b =new byte[100];
		return b;
	}
	public int	getFileSize(){
		int size=0;
		return size;
	}
	public InputStream getInputStream(){
		return null;
	}
	public void setFileName(String file){
		this.file=file;
	}
	public void setFileSize(int fsize){
		
	}
	public String getFileName(){
		return this.file;
	}
	public void setContentType(String fname){
		
	}
}

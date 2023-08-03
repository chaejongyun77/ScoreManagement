package Student.dto;

import java.util.Date;

public class HwLogDto {
	private String studentId, studentName; // 학생ID, 학생이름
	private String fileName;	// 파일명
	private Date uploadDate;	// 파일제출날짜
	
	//Constructor
	public HwLogDto() {
	
	}

	public HwLogDto(String studentId, String studentName, String fileName, Date uploadDate) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.fileName = fileName;
		this.uploadDate = uploadDate;
	}

	//Getter, Setter
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	//toString()
	@Override
	public String toString() {
		return "studentId=" + studentId + ", studentName=" + studentName + ", fileName=" + fileName
				+ ", uploadDate=" + uploadDate;
	}
}

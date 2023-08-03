package Student.dto;

public class ScoreDto {
	private String studentId, studentName, studentClass; // 학생Id, 학생이름, 학생반
	private int midTerm, hw, finals, attend, totalScore, rank; // 중간고사점수, 과제점수, 기말점수, 출석점수, 총점, 등수
	
	//Constructor
	public ScoreDto() {
		
	}

	public ScoreDto(String studentId, String studentName, String studentClass, int midTerm, int hw, int finals,
			int attend, int totalScore, int rank) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentClass = studentClass;
		this.midTerm = midTerm;
		this.hw = hw;
		this.finals = finals;
		this.attend = attend;
		this.totalScore = totalScore;
		this.rank = rank;
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

	public String getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}

	public int getMidTerm() {
		return midTerm;
	}

	public void setMidTerm(int midTerm) {
		this.midTerm = midTerm;
	}

	public int getHw() {
		return hw;
	}

	public void setHw(int hw) {
		this.hw = hw;
	}

	public int getFinals() {
		return finals;
	}

	public void setFinals(int finals) {
		this.finals = finals;
	}

	public int getAttend() {
		return attend;
	}

	public void setAttend(int attend) {
		this.attend = attend;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	//toString()
	@Override
	public String toString() {
		return "studentId=" + studentId + ", studentName=" + studentName + ", studentClass=" + studentClass
				+ ", midTerm=" + midTerm + ", hw=" + hw + ", finals=" + finals + ", attend=" + attend + ", totalScore="
				+ totalScore + ", rank=" + rank;
	}
}

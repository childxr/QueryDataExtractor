package pojo;

public class Task {
	private String titleLine = "NONE";
	private String outputFile = "NONE";
	private String query = "";
	

	public String getOutputFile() {
		return outputFile;
	}
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getTitleLine() {
		return titleLine;
	}
	public void setTitleLine(String titleLine) {
		this.titleLine = titleLine;
	}
}

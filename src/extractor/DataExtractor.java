package extractor;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class DataExtractor {
	public static void loadData(ResultSet rs, String titleLine, String fileout) {
		try {
			if (rs != null) {
				File output = new File(fileout);
				BufferedWriter wt = new BufferedWriter(new FileWriter(output));
				wt.write(titleLine+"\n");
				ResultSetMetaData rsmd = rs.getMetaData();
				while(rs.next()) {
					for (int i = 1; i <= rsmd.getColumnCount(); i++) {
						if (i < rsmd.getColumnCount()) wt.write(rs.getString(i)+",");
						else {
							String str = rs.getString(i);
							if (str == null || str.equals("null")) str = "";
							wt.write(str+"\n");
						}
					}
				}
				wt.close();
			}
			else {
				System.out.println("==>The result set is null!!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

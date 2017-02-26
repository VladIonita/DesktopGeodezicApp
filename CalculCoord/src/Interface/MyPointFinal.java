package Interface;





//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Scanner;
//
//import javafx.collections.ObservableList;

public class MyPointFinal {
	private String x ;
	private String y ;
	private String z ;
	private String id;
	public MyPointFinal() {
	}
	public MyPointFinal(String id,String x, String y, String z) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public MyPointFinal(String x, String y, String z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
//	public static void ListPointTable(ObservableList<MyPoint> listPoints) {
//		File file = new File(null);
//			Scanner read;
//			try {
//				read = new Scanner(file);
//				while (read.hasNextLine()) {
//					MyPoint point = new MyPoint(read.nextInt(), read.nextDouble(), read.nextDouble(), read.nextDouble(),
//							read.nextInt());
//					listPoints.add(point);
//				}
//				read.close();
//			} catch (FileNotFoundException e) {
//				System.out.println("File was not found.");
//			}
//	}
	
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getZ() {
		return z;
	}
	public void setZ(String z) {
		this.z = z;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id =  id;
		
	}
	@Override
	public String toString() {
		return "MyPointFinal [id=" + id + ", x=" + x + ", y=" + y + ", z=" + z + "]";
	}
	
}

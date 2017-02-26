package Interface;

import java.util.LinkedList;
import java.util.List;

//import javafx.collections.ObservableList;

public class Masuratori {
	
	public static void main(String[] args) {
	}
	
	private String from;
	private String to;
	private String type;
	private String distance;
	private String sigmaDX;
	private String sigmaDY;
	private String sigmaDZ;
	public Masuratori() {
	}
	public Masuratori(String from, String to, String type, String distance, String sigmaDX, String sigmaDY,
			String sigmaDZ) {
		this.from = from;
		this.to = to;
		this.type = type;
		this.distance = distance;
		this.sigmaDX = sigmaDX;
		this.sigmaDY = sigmaDY;
		this.sigmaDZ = sigmaDZ;
	}
	
//	public static void ListMasTable(ObservableList<Masuratori> listCoord) {
//		Scanner readMas;
//		try {
//			readMas = new Scanner(new File("GNSS1.txt"));
//			while (readMas.hasNext()) {
//				Masuratori mas = new Masuratori(readMas.next(), readMas.next(), readMas.next(), readMas.nextDouble(),
//						readMas.next(), readMas.next(), readMas.next());
//				listCoord.add(mas);
//			}
//			readMas.close();
//		} catch (FileNotFoundException e) {
//			System.out.println("File was not found.");
//		}
//	}
//	public static void doImportMasuratori(String tipe, List<Masuratori> listMas) {
//
//		Scanner readMas;
//		try {
//			readMas = new Scanner(new File("GNSS1.txt"));
//			while (readMas.hasNextLine()) {
//				Masuratori mas = new Masuratori(readMas.next(), readMas.next(), readMas.next(), readMas.next(),
//						readMas.next(), readMas.next(), readMas.next());
//				listMas.add(mas);
//			}
//			readMas.close();
//		} catch (FileNotFoundException e) {
//			System.out.println("File was not found.");
//		}
//	}
	public static void doCalcule(Integer num) {
		List<Masuratori> listMas = new LinkedList<Masuratori>();

		System.out.println(listMas.get(num));

	}


	public String getFrom() {
		return from;
	}
	public String getTo() {
		return to;
	}
	public String getType() {
		return type;
	}
	public String getDistance() {
		return distance;
	}
	public String getSigmaDX() {
		return sigmaDX;
	}
	public String getSigmaDY() {
		return sigmaDY;
	}
	public String getSigmaDZ() {
		return sigmaDZ;
	}
	
	
	public void setFrom(String from) {
		this.from = from;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public void setSigmaDX(String sigmaDX) {
		this.sigmaDX = sigmaDX;
	}
	public void setSigmaDY(String sigmaDY) {
		this.sigmaDY = sigmaDY;
	}
	public void setSigmaDZ(String sigmaDZ) {
		this.sigmaDZ = sigmaDZ;
	}
	@Override
	public String toString() {
		return "Masuratori [from=" + from + ", to=" + to + ", type=" + type + ", distance=" + distance + ", sigmaDX="
				+ sigmaDX + ", sigmaDY=" + sigmaDY + ", sigmaDZ=" + sigmaDZ + "]";
	}
	
	
}

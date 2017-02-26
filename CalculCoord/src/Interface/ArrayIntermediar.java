package Interface;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import Jama.Matrix;
//import javafx.stage.Stage;

public class ArrayIntermediar {
	double[][] arrQll;
	double[][] arrA;
	double[][] arrL;
	double[][] xf;
	List<Double> xArr;

	// formarea matricei Qll
	public void makingQll(int cr, ArrayList<Double> l) {
		arrQll = new double[cr][cr];
		int i = 0;
		int j = 0;
		int k = 0;
		int n = 0;
		int x = 1;
		for (i = 0; i < cr; i++) {
			for (j = 0; j < cr; j++) {
				if ((i == (3 * x)) && (j == (3 * x))) {
					n = n + 3;
					x++;
					if ((n <= i) && (i < n + 3)) {
						if ((n <= j) && (j < n + 3)) {
							arrQll[i][j] = l.get(k);
							k++;
						}
					}
				} else {
					if ((n <= i) && (i < n + 3)) {
						if ((n <= j) && (j < n + 3)) {
							arrQll[i][j] = l.get(k);
							k++;
						}
					}
				}
			}
		}
		// printQll(arrQll);

	}

	public static void printQll(double[][] arrQll) {
		System.out.println(Arrays.deepToString(arrQll));
	}

	// formarea matricei A
	public void makingA(int cr, int cr2, ArrayList<String> l, ArrayList<String> b) {
		int m = 1;
		arrA = new double[cr][cr2];
		for (int i = 0; i < cr; i++) {
			for (int j = 0; j < cr2; j++) {
				if (l.get(j).equals(b.get(m - 1))) {
					if (((j + 1) % 3) == ((i + 1) % 3)) {
						arrA[i][j] = -1.0;
						if (j == (cr2 - 1)) {
							m = m + 2;
						}
					} else {
						arrA[i][j] = 0.0;

						if (j == (cr2 - 1)) {
							m = m + 2;
						}
					}
				} else if (l.get(j).equals(b.get(m))) {
					if (((j + 1) % 3) == ((i + 1) % 3)) {
						arrA[i][j] = 1.0;
						if (j == (cr2 - 1)) {
							m = m + 2;
						}
					} else {
						arrA[i][j] = 0.0;
						if (j == (cr2 - 1)) {
							m = m + 2;
						}
					}
				} else {
					arrA[i][j] = 0.0;
					if (j == (cr2 - 1)) {
						m = m + 2;
					}
				}
			}
		}
		// printA(arrA);
	}

	public static void printA(double[][] arrA) {
		System.out.println(Arrays.deepToString(arrA));
	}

	// formarea matricei L
	public void makingL(int cr, ArrayList<String> l1, ArrayList<Double> l2, ArrayList<String> l3, ArrayList<Double> l4,
			ArrayList<Double> l5) {
		int k = 0;
		int m = 0;
		double a = 0.0;
		double b = 0.0;
		double c = 0.0;
		int j = 1;
		arrL = new double[cr][j];
		for (int i = 0; i < cr; i++) {
			for (j = 0; j < 1; j++) {
				if ((i + 1) % 3 == 1) {
					for (k = 0; k < l1.size(); k++) {
						if (l1.get(k).equalsIgnoreCase(l3.get(m))) {
							a = l2.get(k);
						} else if (l1.get(k).equalsIgnoreCase(l3.get(m + 1))) {
							b = l2.get(k);
						}
						c = Double.parseDouble(l3.get(m + 2));
						if (k == l1.size() - 1) {
							m = m + 3;
						}
					}
				} else if ((i + 1) % 3 == 2) {
					for (k = 0; k < l1.size(); k++) {
						if (l1.get(k).equalsIgnoreCase(l3.get(m))) {
							a = l4.get(k);
						} else if (l1.get(k).equalsIgnoreCase(l3.get(m + 1))) {
							b = l4.get(k);
						}
						c = Double.parseDouble(l3.get(m + 2));
						if (k == l1.size() - 1) {
							m = m + 3;
						}
					}
				} else if ((i + 1) % 3 == 0) {
					for (k = 0; k < l1.size(); k++) {
						if (l1.get(k).equalsIgnoreCase(l3.get(m))) {
							a = l5.get(k);
						} else if (l1.get(k).equalsIgnoreCase(l3.get(m + 1))) {
							b = l5.get(k);
						}
						c = Double.parseDouble(l3.get(m + 2));
						if (k == l1.size() - 1) {
							m = m + 3;
						}
					}
				}
				arrL[i][j] = b - a - c;
			}
		}
		// printL(arrL);
	}

	public static void printL(double[][] arrL) {
		System.out.println(Arrays.deepToString(arrL));
	}

	double[] columnVector;
	int s;
	int p;

	public void executionCompensation() {
		Matrix A = new Matrix(arrA);
		Matrix l = new Matrix(arrL);
		Matrix Q = new Matrix(arrQll);
		int sig0 = 1;
		Matrix Qinv = Q.inverse();
		Matrix P = Qinv.times(sig0 * sig0);
		Matrix At = A.transpose();
		Matrix N = At.times(P.times(A));
		Matrix Qxx = N.inverse();
		Matrix x = (Qxx.times(-1).times(At.times(P.times(l))));
		double c[][] = x.getArray();
		s = x.getColumnDimension();
		p = x.getRowDimension();

		columnVector = new double[c.length];
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c[i].length; j++) {
				columnVector[i] = c[i][j];
			}
		}
//		Matrix v = A.times(x).plus(l);
	}
//	ControllerForFinalPoint cf = new ControllerForFinalPoint();
	ArrayList<MyPointFinal> pff = new ArrayList<>();
	
	
	public void makingFinal(ArrayList<String> l1, ArrayList<Double> l2, ArrayList<Double> l4, ArrayList<Double> l5,
			ArrayList<Double> l6) {
		int k = 0;
		int m = 0;
		int n = 0;
		double x = 0;
		double y = 0;
		double z = 0;

		for (int l = 0; l < l1.size();) {
			if (l6.get(l) == 1) {
				l++;
			} else {
				for (int i = 0; i < columnVector.length; i++) {
					if ((i + 1) % 3 == 1) {
						x = l2.get(k) + columnVector[i];
						k++;
					} else if ((i + 1) % 3 == 2) {
						y = l4.get(m) + columnVector[i];
						m++;
					} else if ((i + 1) % 3 == 0) {
						z = l5.get(n) + columnVector[i];
						n++;
					}
					if ((i + 1) % 3 == 0) {
						MyPointFinal p = new MyPointFinal(l1.get(l), String.valueOf(x), String.valueOf(y), String.valueOf(z));
						pff.add(p);
//						cf.data.add(p);
//						pff.add(l1.get(l));
//						pff.add( String.valueOf(x));
//						pff.add(String.valueOf(y));
//						pff.add(String.valueOf(z));
						l++;
					}
				}
			}
		}
		afis();
	}
	public void afis() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
			for(MyPointFinal f : pff) {
					  writer.write(String.valueOf(f.getId()));
					  writer.write("  ");
					  writer.write(String.valueOf(f.getX()));
					  writer.write("  ");
					  writer.write(String.valueOf(f.getY()));
					  writer.write("  ");
					  writer.write(String.valueOf(f.getZ()));
					  writer.newLine();
					  
				System.out.println(f);
			}
			  System.out.println("Fisier creat");
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
		}


		

	}
//	public void startview2() {
//		for (int i = 0; i < pff.size(); i=i+4) {
//		}
//		Stage secStage = new Stage();
//		finalPointsShow fps = new finalPointsShow();
//		try {
//			fps.start(secStage);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}

package Interface;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class Controller implements Initializable {

	FileChooser fileChooser = new FileChooser();
	Stage primaryStage = new Stage();
	private ObservableList<MyPoint> listPoints;
	private ObservableList<Masuratori> listCoord;
	ToggleGroup tg = new ToggleGroup();

	public void exitApplication(ActionEvent exitApplication) {
		Platform.exit();
	}

	// actiuni menu project
	public void newFile(ActionEvent newFile) {

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Project files (*.psv)", "*.psv");
		fileChooser.getExtensionFilters().add(extFilter);
		// Show save file dialog
		fileChooser.showSaveDialog(primaryStage);
	}

	private static void configureFileChooser(final FileChooser fileChooser) {
		fileChooser.setTitle("Open Resource File");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"));
	}

	public void openFile(ActionEvent openFile) {

	}

	public void saveFile(ActionEvent saveFile) {
		fileChooser.setTitle("Save Resource File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
		fileChooser.showOpenDialog(primaryStage);

	}

	public void importFileMasuratori(ActionEvent importFileMasuratori) {
		configureFileChooser(fileChooser);
		File file = fileChooser.showOpenDialog(primaryStage);
		if (file != null) {
			Scanner readMas;
			try {
				readMas = new Scanner(file);
				while (readMas.hasNext()) {
					Masuratori mas = new Masuratori(readMas.next(), readMas.next(), readMas.next(), readMas.next(),
							readMas.next(), readMas.next(), readMas.next());
					listCoord.add(mas);
				}
				readMas.close();
			} catch (FileNotFoundException e) {
				System.out.println("File was not found.");
			}
		}
	}

	public void importFileCoordonate(ActionEvent importFileCoordonate) {
		configureFileChooser(fileChooser);
		File file = fileChooser.showOpenDialog(primaryStage);
		if (file != null) {
			Scanner read;
			try {
				read = new Scanner(file);
				while (read.hasNext()) {
					MyPoint point = new MyPoint(read.next(), read.next(), read.next(), read.next(), read.next());
					listPoints.add(point);
				}
				read.close();
			} catch (FileNotFoundException e) {
				System.out.println("File was not found.");
			}
		}
	}

	public void exportFile(ActionEvent exportFile) {

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Export files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);
		fileChooser.showSaveDialog(primaryStage);
	}

	// for delete points button
	@FXML
	public void deleteButtonClicked() {
		ObservableList<MyPoint> pointSelected, allPoints;
		allPoints = tableCoord.getItems();
		pointSelected = tableCoord.getSelectionModel().getSelectedItems();
		pointSelected.forEach(allPoints::remove);
	}

	// for add points file button
	@FXML
	private void addButton(ActionEvent event) {
		listPoints.add(new MyPoint(idField.getText(), xField.getText(), yField.getText(), zField.getText(),
				obsField.getText()));
		idField.clear();
		xField.clear();
		yField.clear();
		zField.clear();
		obsField.clear();
	}

	@FXML
	private TextField idField;
	@FXML
	private TextField xField;
	@FXML
	private TextField yField;
	@FXML
	private TextField zField;
	@FXML
	private TextField obsField;

	// clear all points button
	@FXML
	public void clearAllButtonClicked() {
		ObservableList<MyPoint> allPoints;
		allPoints = tableCoord.getItems();
		allPoints.removeAll(allPoints);
	}

	// for delete mesurements button
	@FXML
	public void deleteMesurementsButtonClicked() {
		ObservableList<Masuratori> masSelected, allMas;
		allMas = tableMas.getItems();
		masSelected = tableMas.getSelectionModel().getSelectedItems();
		masSelected.forEach(allMas::remove);
	}

	// for add mas file button
	@FXML
	private void addMasButton(ActionEvent event) {
		listCoord.add(new Masuratori(fromField.getText(), toField.getText(), typeField.getText(),
				distanceField.getText(), sigmaDXField.getText(), sigmaDYField.getText(), sigmaDZField.getText()));
		fromField.clear();
		toField.clear();
		typeField.clear();
		distanceField.clear();
		sigmaDXField.clear();
		sigmaDYField.clear();
		sigmaDZField.clear();
	}

	@FXML
	private TextField fromField;
	@FXML
	private TextField toField;
	@FXML
	private TextField typeField;
	@FXML
	private TextField distanceField;
	@FXML
	private TextField sigmaDXField;
	@FXML
	private TextField sigmaDYField;
	@FXML
	private TextField sigmaDZField;

	// clear all mesurements button
	@FXML
	public void clearAllMesButtonClicked() {
		ObservableList<Masuratori> allMas;
		allMas = tableMas.getItems();
		allMas.removeAll(allMas);
	}

	// elipsoid chose
	@FXML
	private RadioMenuItem grs80;
	@FXML
	private RadioMenuItem wgs84;
	@FXML
	private RadioMenuItem krasovsky;

	@FXML
	public void elipsoidChose() {
		double a, f = 1, e;
		if (grs80.isSelected()) {
			a = 6378137;
			f = 1 / 298.257222101;
			System.out.println("whe use grs80");
		} else if (wgs84.isSelected()) {
			a = 6378137;
			f = 1 / 298.257223563;
			System.out.println("whe use wgs84");
		} else if (krasovsky.isSelected()) {
			a = 6378245;
			f = 1 / 298.3;
			System.out.println("whe use krasovsky");
		}

		e = Math.sqrt((2 * f) - Math.pow(f, 2));

	}

	// Pt tabelul de coord
	@FXML
	private TableView<MyPoint> tableCoord;
	@FXML
	private TableColumn<MyPoint, String> Id;
	@FXML
	private TableColumn<MyPoint, String> X;
	@FXML
	private TableColumn<MyPoint, String> Y;
	@FXML
	private TableColumn<MyPoint, String> Z;
	@FXML
	private TableColumn<MyPoint, String> obs;

	// Pt tabelul de masuratori
	@FXML
	private TableView<Masuratori> tableMas;
	@FXML
	private TableColumn<Masuratori, String> from;
	@FXML
	private TableColumn<Masuratori, String> to;
	@FXML
	private TableColumn<Masuratori, String> type;
	@FXML
	private TableColumn<Masuratori, String> distance;
	@FXML
	private TableColumn<Masuratori, String> sigmaDX;
	@FXML
	private TableColumn<Masuratori, String> sigmaDY;
	@FXML
	private TableColumn<Masuratori, String> sigmaDZ;
	int count;
	int count2;
	String str = "1";
	ArrayList<Double> listQ = new ArrayList<>();
	ArrayList<String> listA1 = new ArrayList<>();
	ArrayList<String> listA2 = new ArrayList<>();
	ArrayList<String> listL1 = new ArrayList<>();
	ArrayList<Double> listL2 = new ArrayList<>();
	ArrayList<String> listL3 = new ArrayList<>();
	ArrayList<Double> listL4 = new ArrayList<>();
	ArrayList<Double> listL5 = new ArrayList<>();
	ArrayList<Double> listL6 = new ArrayList<>();



	@FXML
	private void showData() {
		for (Masuratori m : tableMas.getItems()) {
			count++;
			listQ.add(Double.parseDouble(m.getSigmaDX()));
			listQ.add(Double.parseDouble(m.getSigmaDY()));
			listQ.add(Double.parseDouble(m.getSigmaDZ()));
		}
		for (MyPoint p : tableCoord.getItems()) {
			if(p.getObs().equals("0")) {
				listA1.add(p.getId());
				listA1.add(p.getId());
				listA1.add(p.getId());
				count2++;
			}

		}
		for (Masuratori m : tableMas.getItems()) {
			listA2.add(m.getFrom());
			listA2.add(m.getTo());
		}
		for (MyPoint p : tableCoord.getItems()) {
			listL1.add(p.getId());
		}
		for (MyPoint p : tableCoord.getItems()) {
			listL2.add(Double.parseDouble(p.getX()));
		}
		for (MyPoint p : tableCoord.getItems()) {
			listL4.add(Double.parseDouble(p.getY()));
		}
		for (MyPoint p : tableCoord.getItems()) {
			listL5.add(Double.parseDouble(p.getZ()));
		}
		for (MyPoint p : tableCoord.getItems()) {
			listL6.add(Double.parseDouble(p.getObs()));
		}

		for (Masuratori m : tableMas.getItems()) {
			listL3.add(m.getFrom());
			listL3.add(m.getTo());
			listL3.add(m.getDistance());
		}
		pornire();

	}

	private void pornire() {
		ArrayIntermediar ar = new ArrayIntermediar();
		ar.makingQll(count, listQ);
		ar.makingA(count, listA1.size(), listA1, listA2);
		ar.makingL(count, listL1, listL2, listL3, listL4, listL5);
		ar.executionCompensation();
		ar.makingFinal(listL1, listL2, listL4, listL5,listL6);
//		ar.startview2();
		System.out.println("sa terminat pornirea");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		tableCoord.setEditable(true);
		tableMas.setEditable(true);

//		Callback<TableColumn, TableCell> cellFactory = p -> new EditingCell();

		Id.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MyPoint, String>>() {
			@Override
			public void handle(CellEditEvent<MyPoint, String> arg0) {
				((MyPoint) arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()))
						.setId(arg0.getNewValue());
			}
		});
		X.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MyPoint, String>>() {
			@Override
			public void handle(CellEditEvent<MyPoint, String> arg0) {
				((MyPoint) arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()))
						.setX(arg0.getNewValue());
			}
		});
		Y.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MyPoint, String>>() {
			@Override
			public void handle(CellEditEvent<MyPoint, String> arg0) {
				((MyPoint) arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()))
						.setY(arg0.getNewValue());
			}
		});
		Z.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MyPoint, String>>() {
			@Override
			public void handle(CellEditEvent<MyPoint, String> arg0) {
				((MyPoint) arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()))
						.setZ(arg0.getNewValue());
			}
		});
		obs.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MyPoint, String>>() {
			@Override
			public void handle(CellEditEvent<MyPoint, String> arg0) {
				((MyPoint) arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()))
						.setObs(arg0.getNewValue());
			}
		});

		grs80.setToggleGroup(tg);
		wgs84.setToggleGroup(tg);
		krasovsky.setToggleGroup(tg);

		listPoints = FXCollections.observableArrayList();
		Id.setCellValueFactory(new PropertyValueFactory<MyPoint, String>("Id"));
		Id.setCellFactory(TextFieldTableCell.forTableColumn());
		X.setCellValueFactory(new PropertyValueFactory<MyPoint, String>("X"));
		X.setCellFactory(TextFieldTableCell.forTableColumn());
		Y.setCellValueFactory(new PropertyValueFactory<MyPoint, String>("Y"));
		Y.setCellFactory(TextFieldTableCell.forTableColumn());
		Z.setCellValueFactory(new PropertyValueFactory<MyPoint, String>("Z"));
		Z.setCellFactory(TextFieldTableCell.forTableColumn());
		obs.setCellValueFactory(new PropertyValueFactory<MyPoint, String>("obs"));
		obs.setCellFactory(TextFieldTableCell.forTableColumn());
		tableCoord.setItems(listPoints);

		// MyPoint.ListPointTable(listPoints);

		listCoord = FXCollections.observableArrayList();
		from.setCellValueFactory(new PropertyValueFactory<Masuratori, String>("from"));
		from.setCellFactory(TextFieldTableCell.forTableColumn());
		to.setCellValueFactory(new PropertyValueFactory<Masuratori, String>("to"));
		to.setCellFactory(TextFieldTableCell.forTableColumn());
		type.setCellValueFactory(new PropertyValueFactory<Masuratori, String>("type"));
		type.setCellFactory(TextFieldTableCell.forTableColumn());
		distance.setCellValueFactory(new PropertyValueFactory<Masuratori, String>("distance"));
		distance.setCellFactory(TextFieldTableCell.forTableColumn());
		sigmaDX.setCellValueFactory(new PropertyValueFactory<Masuratori, String>("sigmaDX"));
		sigmaDX.setCellFactory(TextFieldTableCell.forTableColumn());
		sigmaDY.setCellValueFactory(new PropertyValueFactory<Masuratori, String>("sigmaDY"));
		sigmaDY.setCellFactory(TextFieldTableCell.forTableColumn());
		sigmaDZ.setCellValueFactory(new PropertyValueFactory<Masuratori, String>("sigmaDZ"));
		sigmaDZ.setCellFactory(TextFieldTableCell.forTableColumn());
		tableMas.setItems(listCoord);

		// edit table masurement cell
		from.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Masuratori, String>>() {
			@Override
			public void handle(CellEditEvent<Masuratori, String> arg0) {
				((Masuratori) arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()))
						.setFrom(arg0.getNewValue());
			}
		});
		to.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Masuratori, String>>() {
			@Override
			public void handle(CellEditEvent<Masuratori, String> arg0) {
				((Masuratori) arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()))
						.setTo(arg0.getNewValue());
			}
		});
		type.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Masuratori, String>>() {
			@Override
			public void handle(CellEditEvent<Masuratori, String> arg0) {
				((Masuratori) arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()))
						.setType(arg0.getNewValue());
			}
		});
		distance.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Masuratori, String>>() {
			@Override
			public void handle(CellEditEvent<Masuratori, String> arg0) {
				((Masuratori) arg0.getTableView().getItems().get(arg0.getTablePosition().getRow()))
						.setDistance(arg0.getNewValue());
			}
		});

		// Masuratori.ListMasTable(listCoord);

		from.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getFrom()));
		to.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTo()));
		type.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getType()));
		distance.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDistance()));
		sigmaDX.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getSigmaDX()));
		sigmaDY.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getSigmaDY()));
		sigmaDZ.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getSigmaDZ()));

	}

}


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;
import javafx.scene.chart.NumberAxis;

public class RMSDdynamic extends Application {
	public static void main(String [] args) {
		launch(args);
	}

	@Override public void start(Stage stage) throws IOException {
		stage.setTitle("PLOT");
		final NumberAxis yAxis = new NumberAxis(0.0, 1.75, 0.25);
        final CategoryAxis xAxis = new CategoryAxis();
        
        xAxis.setLabel("Frames");       
        yAxis.setLabel("RMSD (Å)");
        final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);
                
        lineChart.setTitle("RMSD of Atomic Positions");
                                
        XYChart.Series series = new XYChart.Series();
        series.setName("Dynamic in water");
		int basis = 26;
		String framesSourceName = "/Users/thiagolopes/Desktop/traject_withou_solute.xyz";
		FindMol xyzData = new FindMol(framesSourceName, basis);
		ArrayList<Integer> xyzStartLines = xyzData.takeFrameLines();
		ArrayList<Integer> framesToTake = new ArrayList<Integer>();
		framesToTake.add(1);
		for (int i = 1000; i < 47850; i=i+1000) {
			framesToTake.add(i);
		}
		ArrayList<Molecule> frames = new Takeframes(
				basis, framesSourceName, xyzStartLines
		).returnFrames(framesToTake);
		System.out.println(frames);
		ArrayList<RmsdCalc> rsmdDynamic = new ArrayList<RmsdCalc>();
		ArrayList<Double> rmsdVAlor = new ArrayList<Double>();
		for (int i = 1; i < framesToTake.size(); i++) {
			int i_1 = i-1;
			int i_2 = i;
			RmsdCalc rmsd= new RmsdCalc(frames.get(i_1), frames.get(i_2));
			rsmdDynamic.add(rmsd);
			rmsdVAlor.add(rmsd.returnRMSDmol());
			series.getData().add(new XYChart.Data(String.format("%d", framesToTake.get(i_2)) , rmsd.returnRMSDmol()));
		}
		
        Scene scene  = new Scene(lineChart,800,600);
        scene.getStylesheets().add("chart.css");
        lineChart.getData().add(series);
        stage.setScene(scene);
        stage.show();
        saveAsPng(lineChart);
        
    }
	
	public void saveAsPng(LineChart<String,Number> lineChart) {
		SnapshotParameters parameters = new SnapshotParameters();
		parameters.setTransform(Transform.scale(4, 4));
		parameters.setFill(Color.TRANSPARENT);
	    WritableImage image = lineChart.snapshot(parameters, null);

	    // TODO: probably use a file chooser here
	    File file = new File("chart.png");

	    try {
	        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
	    } catch (IOException e) {
	        // TODO: handle exception here
	    }
	}

	
}
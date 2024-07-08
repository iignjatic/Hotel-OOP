package manage;

import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;



import javax.swing.JDialog;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.style.markers.SeriesMarkers;


public class AdministratorManager {

	// CRUD za sve entitete(zaposlene, goste, rezervacije, sobe, cjenovnik)
	// dodaje nove zaposlene(sobarice i recepcioneri)
	// uvid u podatke o svim zaposlenim i o prihodima/ rashodima
	// definise cjenovnik za svaki tip sobe, dodatnu uslugu
	// i datum pocetka i kraja vazenja

	public void report1(int waitlist, int accepted, int denied, int canceled) {
		 PieChart chart = new PieChartBuilder()
		            .width(800)
		            .height(600)
		            .title("Status rezervacija u poslednjih 30 dana")
		            .theme(ChartTheme.Matlab)
		            .build();

		        
		        chart.getStyler().setLegendVisible(false);
		      
		        chart.getStyler().setPlotContentSize(.7);
		        chart.getStyler().setStartAngleInDegrees(90);

		        // dodavanje podataka
		        chart.addSeries("NA CEKANJU", waitlist);
		        chart.addSeries("POTVRDJENA", accepted);
		        chart.addSeries("ODBIJENA", denied);
		        chart.addSeries("OTKAZANA", canceled);

		       
	
			    XChartPanel<PieChart> chartPanel = new XChartPanel<>(chart);
			    JDialog dialog = new JDialog();
			    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			    dialog.add(chartPanel);
			    dialog.pack();
			    dialog.setVisible(true);
			

	}
	
	public void report2(HashMap<String, Integer> result) {
		 PieChart chart = new PieChartBuilder()
		            .width(800)
		            .height(600)
		            .title("Sobarice i ociscene sobe u poslednjih 30 dana")
		            .theme(ChartTheme.Matlab)
		            .build();

		        
		        chart.getStyler().setLegendVisible(false);
		      
		        chart.getStyler().setPlotContentSize(.7);
		        chart.getStyler().setStartAngleInDegrees(90);

		        // dodavanje podataka
		        
		        for(String maid: result.keySet()) {
		        	chart.addSeries(maid, result.get(maid));
		        
		        }

		       
	
			    XChartPanel<PieChart> chartPanel = new XChartPanel<>(chart);
			    JDialog dialog = new JDialog();
			    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			    dialog.add(chartPanel);
			    dialog.pack();
			    dialog.setVisible(true);
			

	}
	
	public List<Date> makeMonths() {
		List<Date> months = new ArrayList<>();
		DateFormat sdf = new SimpleDateFormat("MM.yyyy");
	    Date date = null;
	    LocalDate thisYear = LocalDate.now().plusMonths(1);
	    for (int i = 0; i < 12; i++) {
	    	String trenutniMesec = thisYear.toString().split("-")[1];
	    	String godina = thisYear.toString().split("-")[0];
	      try {
	    	  date = sdf.parse(trenutniMesec + "."+godina);
	      } catch (ParseException e) {
	      }
	      months.add(date);
	      thisYear = thisYear.minusMonths(1);
	    }
		return months;
	}
	
	public void report3(HashMap<String, List<Double>> result) {
	    XYChart chart = new XYChartBuilder().width(800).height(600).title("Iskucani Graf").xAxisTitle("Mjeseci").yAxisTitle("Vrijednosti").build();
	
	
	    
	    List<Date> months = makeMonths();
	    for (String type : result.keySet()) {
	    	List<Double> yData = result.get(type);
	    	List<Double> finalResults = new ArrayList<>();
	    	for(Date d: months) {
	    		@SuppressWarnings("deprecation")
				int month = d.getMonth();
	    		finalResults.add(yData.get(month));
	    	}
	    	
	            XYSeries series = chart.addSeries(type, months, finalResults);
	            series.setMarker(SeriesMarkers.CIRCLE);
	        
	    }
	    
	    
	
	  
	    XChartPanel<XYChart> chartPanel = new XChartPanel<>(chart);
	    JDialog dialog = new JDialog();
	    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    dialog.add(chartPanel);
	    dialog.pack();
	    dialog.setVisible(true);
	}
	}
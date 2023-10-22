package application;



import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;
public class SampleController {
	
	
	 @FXML
	    private ChoiceBox<String> firstChoiceBox;
	 @FXML
	    private TextField textFieldOne;
	 @FXML
	    private ChoiceBox<String> secondChoiceBox;
	 @FXML
	    private Text equality;
	 @FXML
	    private Text firstPart;
	 @FXML
	    private Text typjednostki;
	 @FXML
	    private Text rownasie;
	 @FXML
	    private Text typjednostki2;
	
	 private String[] opcje = {"stopnie Celcjusza","stopnie Fahrenheita","stopnie Kelwina","stopnie Renkine'a"};
	 private String[] wartosci = {"℃", "℉", "K", "°R"};
	   public void initialize() {
		   equality.setVisible(false);
		   firstPart.setVisible(false);
		   typjednostki.setVisible(false);
		   rownasie.setVisible(false);
		   typjednostki2.setVisible(false);
		   
		   firstChoiceBox.getItems().addAll(opcje);
		   secondChoiceBox.getItems().addAll(opcje);
		   
		   firstChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> convertAndDisplay());
	        secondChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> convertAndDisplay());
	        
	        textFieldOne.setTextFormatter(new TextFormatter<Double>(new StringConverter<Double>() {
	            @Override
	            public String toString(Double object) {
	                if (object == null) {
	                    return "";
	                }
	                return object.toString();
	            }

	            @Override
	            public Double fromString(String string) {
	                try {
	                    // sparubuje parsowac watosc inputa na double
	                    return Double.parseDouble(string);
	                } catch (NumberFormatException e) {
	                    // jeśli sa tam litery odzuc
	                    return null;
	                }
	            }
	        }));

	        //dodaj listenera  by sprawdzic czy sa jakies nie numerowe wartosci
	        textFieldOne.textProperty().addListener((observable, oldValue, newValue) -> {
	            if (newValue != null && !newValue.matches("\\d*\\.?\\d*")) {
	                // jesli input ma jakos nie umeryczna wartosc cofa do poprzedniej wartosci
	                textFieldOne.setText(oldValue);
	            }
	        });
	        
	        firstChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateVisibility());
	        secondChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateVisibility());
	        textFieldOne.textProperty().addListener((observable, oldValue, newValue) -> updateVisibility());
	        
	        
	   }
	   
	   private void updateVisibility() {
		    String selectedValue1 = firstChoiceBox.getValue();
		    String selectedValue2 = secondChoiceBox.getValue();
		    String inputValue = textFieldOne.getText();

		    if (selectedValue1 != null && selectedValue2 != null && !inputValue.isEmpty()) {
		        // Show Text elements when both ChoiceBoxes and textField have values
		        equality.setVisible(true);
		        firstPart.setVisible(true);
		        typjednostki.setVisible(true);
		        rownasie.setVisible(true);
		        typjednostki2.setVisible(true);
		    } else {
		        // Hide Text elements if any of the conditions is not met
		        equality.setVisible(false);
		        firstPart.setVisible(false);
		        typjednostki.setVisible(false);
		        rownasie.setVisible(false);
		        typjednostki2.setVisible(false);
		    }
		}
	   private void convertAndDisplay() {
	        String originalUnit = firstChoiceBox.getValue();
	        String newUnit = secondChoiceBox.getValue();

	        try {
	            double originalValue = Double.parseDouble(textFieldOne.getText());

	            // dokonuje zamiane jednostek
	            double convertedValue = convertTemperature(originalValue, originalUnit, newUnit);

	            // aktualizuj elementy tekstu
	            firstPart.setText(String.valueOf(originalValue));
	            typjednostki.setText(wartosci[firstChoiceBox.getItems().indexOf(originalUnit)]);
	            typjednostki2.setText(wartosci[secondChoiceBox.getItems().indexOf(newUnit)]);
	            rownasie.setText(String.valueOf(convertedValue));
	        } catch (NumberFormatException e) {
	            // w razie nieprawidlowego inputu
	            firstPart.setText("");
	            typjednostki.setText("");
	            typjednostki2.setText("");
	            rownasie.setText("Invalid input");
	        }
	    }

	    private double convertTemperature(double value, String fromUnit, String toUnit) {
	       
	     
	        if ("stopnie Celcjusza".equals(fromUnit) && "stopnie Fahrenheita".equals(toUnit)) {
	            return (value * 9 / 5) + 32;
	        } else if ("stopnie Fahrenheita".equals(fromUnit) && "stopnie Celcjusza".equals(toUnit)) {
	            return (value - 32) * 5 / 9;
	        } else if ("stopnie Celcjusza".equals(fromUnit) && "stopnie Kelwina".equals(toUnit)) {
	          
	            return value + 273.15;
	        } else if ("stopnie Kelwina".equals(fromUnit) && "stopnie Celcjusza".equals(toUnit)) {
	           
	            return value - 273.15;
	        }else if ("stopnie Celcjusza".equals(fromUnit) && "stopnie Renkine'a".equals(toUnit)) {
	        
	            return value * 1.8 + 491.67;
	        }else if ("stopnie Renkine'a".equals(fromUnit) && "stopnie Celcjusza".equals(toUnit)) {
	           
	            return (value - 491.67) / 1.8;
	        }else if ("stopnie Fahrenheita".equals(fromUnit) && "stopnie Kelwina".equals(toUnit)) {
	          
	            return (value  - 32.0) * 5.0 / 9.0 + 273.15;
	        }else if ("stopnie Kelwina".equals(fromUnit) && "stopnie Fahrenheita".equals(toUnit)) {
	        
	            return (value  - 273.15) * 9.0 / 5.0 + 32.0;
	        }else if ("stopnie Fahrenheita".equals(fromUnit) && "stopnie Renkine'a".equals(toUnit)) {
	         
	            return value + 459.67;
	        }else if ("stopnie Renkine'a".equals(fromUnit) && "stopnie Fahrenheita".equals(toUnit)) {
	          
	            return value - 459.67;
	        }else if ("stopnie Kelwina".equals(fromUnit) && "stopnie Renkine'a".equals(toUnit)) {
	        
	            return  value * 1.8;
	        }else if ("stopnie Renkine'a".equals(fromUnit) && "stopnie Kelwina".equals(toUnit)) {
	           
	            return value / 1.8;
	        }else {
	        	return value;
	        }
	    }
	   
	   
	   
}

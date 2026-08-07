package c213.dosaoopproject.commonClass.util;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class ValidationUtil {

    public static boolean isEmpty(String textField) {
        return textField == null || textField.trim().isEmpty();
    }

    public static boolean isComboUnselected(ComboBox<?> comboBox) {
        return comboBox == null || comboBox.getValue() == null;
    }

    public static boolean isDateUnselected(DatePicker datePicker) {
        return datePicker == null || datePicker.getValue() == null;
    }

    public static boolean isNumeric(String str) {
        if (str == null || str.trim().isEmpty()) return false;
        try {
            Double.parseDouble(str.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isFutureDate(LocalDate date) {
        return date != null && date.isAfter(LocalDate.now());
    }
}
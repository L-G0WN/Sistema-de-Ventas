package com.monagas.view.sales.components;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CustomJTextField extends JTextField {

    private final int MAX_LENGTH = 50;
    private final int MAX_LENGTH_CEDULA = 8;
    private final int MAX_LENGTH_RIF = 10;
    private final int MAX_LENGTH_CODE = 4;
    private final int MAX_LENGTH_PHONE = 7;
    private final int MAX_LENGTH_EMAIL = 255;
    private final int MAX_LENGTH_ADDRESS = 255;
    private final int MAX_LENGTH_DESCRIPTION = 255;
    private final int MAX_LENGTH_MONEY = 99999999;
    private final int MAX_LENGTH_AMOUNT = 10;

    private static final Map<String, String> TYPE_MESSAGES = new HashMap<>();

    static {
        TYPE_MESSAGES.put("Name", "Nombre");
        TYPE_MESSAGES.put("Firstname", "Nombre");
        TYPE_MESSAGES.put("Lastname", "Apellido");
        TYPE_MESSAGES.put("Cedula", "Cedula");
        TYPE_MESSAGES.put("Code", "Codigo");
        TYPE_MESSAGES.put("Rif", "RIF");
        TYPE_MESSAGES.put("Phone", "Número de teléfono");
        TYPE_MESSAGES.put("Email", "Correo Electrónico (Opcional)");
        TYPE_MESSAGES.put("Address", "Dirección principal (Opcional)");
        TYPE_MESSAGES.put("Description", "Descripción del Producto");
        TYPE_MESSAGES.put("Price", "P.C.");
        TYPE_MESSAGES.put("Purchase", "P.V.");
        TYPE_MESSAGES.put("Dolar", "Precio del Dólar");
        TYPE_MESSAGES.put("Amount", "Stock/Cantidad");
        TYPE_MESSAGES.put("Username", "Usuario (Opcional)");
        TYPE_MESSAGES.put("Username2", "Usuario");
        TYPE_MESSAGES.put("Answer", "Respuesta (Opcional)");
        TYPE_MESSAGES.put("Answer2", "Respuesta");
        TYPE_MESSAGES.put("Commerce", "Ingresa el nombre del comercio");
    }

    public CustomJTextField(String type) {
        if (TYPE_MESSAGES.containsKey(type)) {
            setValues(type, TYPE_MESSAGES.get(type));
        }

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if (getName() != null) {
                    if (getName().equals("Username") || getName().equals("Username2") || getName().equals("Commerce")) {
                        if (getText().length() >= MAX_LENGTH) {
                            e.consume();
                        }
                    }

                    Set<String> listNames = new HashSet<>(Arrays.asList("Firstname", "Lastname"));

                    if (listNames.contains(getName())) {
                        if (!Character.isLetter(c) && c != ' ' || getText().length() >= MAX_LENGTH) {
                            e.consume();
                        }
                    }

                    if (getName().equals("Name")) {
                        if (getText().length() >= MAX_LENGTH) {
                            e.consume();
                        }
                    }

                    if (getName().equals("Cedula")) {
                        if (!Character.isDigit(c) || getText().length() >= MAX_LENGTH_CEDULA) {
                            e.consume();
                        }
                    }

                    if (getName().equals("Code")) {
                        if (!Character.isDigit(c) || getText().length() >= MAX_LENGTH_CODE) {
                            e.consume();
                        }
                    }

                    if (getName().equals("Rif")) {
                        if (!Character.isDigit(c) || getText().length() >= MAX_LENGTH_RIF) {
                            e.consume();
                        }
                    }

                    if (getName().equals("Phone")) {
                        if (!Character.isDigit(c) || getText().length() >= MAX_LENGTH_PHONE) {
                            e.consume();
                        }
                    }

                    if (getName().equals("Email")) {
                        JTextField textField = (JTextField) e.getSource();
                        String currentText = textField.getText();

                        if (e.isControlDown() || e.isAltDown() || e.isMetaDown() || e.isShiftDown()) {
                            return;
                        }

                        if (e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE) {
                            if (!currentText.isEmpty()) {
                                currentText = currentText.substring(0, (currentText.length() - 1));
                                textField.setText(currentText);
                            }
                            e.consume();
                            return;
                        }

                        if (c == ' ' || currentText.length() >= MAX_LENGTH_EMAIL) {
                            e.consume();
                        }
                    }

                    if (getName().equals("Address") || getName().equals("Answer") || getName().equals("Answer2")) {
                        if (getText().length() >= MAX_LENGTH_ADDRESS) {
                            e.consume();
                        }
                    }

                    if (getName().equals("Description")) {
                        if (getText().length() >= MAX_LENGTH_DESCRIPTION) {
                            e.consume();
                        }
                    }

                    if (getName().equals("Price") || getName().equals("Purchase") || getName().equals("Dolar")) {
                        if (!Character.isDigit(c) && c != '.' || getText().length() >= MAX_LENGTH_MONEY) {
                            e.consume();
                        }

                        if (getText().indexOf(".") != getText().lastIndexOf(".")) {
                            e.consume();
                        }

                        if (getText().contains(".")) {
                            String[] parts = getText().split("\\.");
                            if (parts.length > 1 && parts[1].length() > 1) {
                                e.consume();
                            }
                        }
                    }

                    if (getName().equals("Amount")) {
                        if (!Character.isDigit(c) || getText().length() >= MAX_LENGTH_AMOUNT) {
                            e.consume();
                        }
                    }
                }
            }
        });
    }

    private void setValues(String type, String message) {
        setName(type);
        putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, message);
    }

    public CustomJTextField(JTable table) {
        propertyField(table);
    }

    private void propertyField(JTable table) {
        putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Buscador...");
        putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new ImageIcon(getClass().getResource("/images/LogoSearch.png")));
        putClientProperty(FlatClientProperties.STYLE, ""
                + "showClearButton:true;");

        putClientProperty("JTextField.clearCallback",
                (Runnable) () -> {
                    setText("");
                    keyEvent(table);
                });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                keyEvent(table);
            }
        });
    }

    private void keyEvent(JTable table) {
        TableRowSorter<TableModel> SorterFilter = new TableRowSorter<>(table.getModel());

        int[] columns = new int[]{2, 3};

        if (table.getName() != null) {
            if (table.getName().equals("Products2")) {
                columns = new int[]{0, 1};
            }
            
            if (table.getName().equals("Controls")) {
                columns = new int[]{1, 2};
            }
        }

        SorterFilter.setRowFilter(RowFilter.regexFilter("(?iu)" + getText(), columns));
        table.setRowSorter(SorterFilter);
    }
}

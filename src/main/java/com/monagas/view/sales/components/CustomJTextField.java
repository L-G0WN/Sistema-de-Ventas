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
    private final int MAX_LENGTH_PHONE = 7;
    private final int MAX_LENGTH_ADDRESS = 255;

    private static final Map<String, String> TYPE_MESSAGES = new HashMap<>();

    static {
        TYPE_MESSAGES.put("Firstname", "Nombre");
        TYPE_MESSAGES.put("Lastname", "Apellido");
        TYPE_MESSAGES.put("Cedula", "Cedula");
        TYPE_MESSAGES.put("Phone", "Número de teléfono");
        TYPE_MESSAGES.put("Address", "Dirección principal");
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
                    if (getName().equals("Username")) {
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

                    if (getName().equals("Cedula")) {
                        if (!Character.isDigit(c) || getText().length() >= MAX_LENGTH_CEDULA) {
                            e.consume();
                        }
                    }

                    if (getName().equals("Phone")) {
                        if (!Character.isDigit(c) || getText().length() >= MAX_LENGTH_PHONE) {
                            e.consume();
                        }
                    }

                    if (getName().equals("Address")) {
                        if (getText().length() >= MAX_LENGTH_ADDRESS) {
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

        int[] columns = null;

        if (getName() != null && getName().equals("Clients")) {
            columns = new int[]{1, 2, 3};
        }

        if (table.isEditing()) {
            table.getCellEditor().stopCellEditing();
        }

        SorterFilter.setRowFilter(RowFilter.regexFilter("(?iu)" + getText(), columns));
        table.setRowSorter(SorterFilter);
    }
}

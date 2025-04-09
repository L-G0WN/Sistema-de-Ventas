package com.monagas.controllers.login;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.monagas.entities.Address;
import com.monagas.entities.DetailPerson;
import com.monagas.entities.Person;
import com.monagas.entities.login.SecurityQuestion;
import com.monagas.entities.login.User;
import com.monagas.services.login.UserService;
import com.monagas.view.login.Login;
import com.monagas.view.login.components.CredentialManager;
import com.monagas.view.login.forms.FormRecovery;
import com.monagas.view.login.forms.FormVerify;
import com.monagas.view.login.forms.button.ButtonCancel;
import com.monagas.view.sales.Sales;
import java.awt.Frame;
import java.util.List;
import java.util.Optional;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class UserController {

    private final UserService userService = new UserService();

    public void login(Frame parent, JTextField txtUsername, JPasswordField txtPassword, JCheckBox cbRememberMe, JButton btnLogin, JButton btnForget) {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        if (!username.isEmpty() && !password.isEmpty()) {
            try {
                userService.login(username, password);

                txtUsername.setEditable(false);
                txtPassword.setEditable(false);
                cbRememberMe.setEnabled(false);
                btnLogin.setEnabled(false);
                btnForget.setEnabled(false);

                if (cbRememberMe.isSelected()) {
                    CredentialManager.saveCredentials(username);
                } else {
                    CredentialManager.clearCredentials();
                }

                Timer timer = new Timer(1500, ex -> {
                    parent.dispose();
                    new Sales().setVisible(true);
                });

                timer.setRepeats(false);
                timer.start();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        parent,
                        ex.getMessage(),
                        "Sistema de Ventas - Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(
                    parent,
                    "Por favor, complete todos los campos vacíos.",
                    "Sistema de Ventas - Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void isFirstime(Frame parent,
            JDialog dialog,
            Long id,
            JPasswordField txtPassword,
            JPasswordField txtConfirmPassword,
            int passwordStrength,
            JComboBox cbQuestions1, JPasswordField txtAnswer1,
            JComboBox cbQuestions2, JPasswordField txtAnswer2,
            JComboBox cbQuestions3, JPasswordField txtAnswer3) {
        String password = new String(txtPassword.getPassword());
        String confirmPassword = new String(txtConfirmPassword.getPassword());
        String question1 = cbQuestions1.getSelectedItem().toString();
        String answer1 = new String(txtAnswer1.getPassword());
        String question2 = cbQuestions2.getSelectedItem().toString();
        String answer2 = new String(txtAnswer2.getPassword());
        String question3 = cbQuestions3.getSelectedItem().toString();
        String answer3 = new String(txtAnswer3.getPassword());

        if (!password.isEmpty() && !confirmPassword.isEmpty()
                && !answer1.isEmpty() && !answer2.isEmpty() && !answer3.isEmpty()) {
            if (password.equals(confirmPassword)) {
                if ((passwordStrength == 2) || (passwordStrength == 3)) {
                    User user = userService.findUserById(id);

                    SecurityQuestion sq = user.getSecurityQuestions();
                    sq.setQuestion1(question1);
                    sq.setAnswer1(answer1);
                    sq.setQuestion2(question2);
                    sq.setAnswer2(answer2);
                    sq.setQuestion3(question3);
                    sq.setAnswer3(answer3);

                    user.setPassword(password);
                    user.setFirstime(false);
                    user.setSecurityQuestions(sq);

                    try {
                        userService.edit(user, null, null, null, sq);
                        JOptionPane.showMessageDialog(
                                parent,
                                "Se han realizado los cambios correctamente.",
                                "Sistema de Ventas - Información",
                                JOptionPane.INFORMATION_MESSAGE);
                        dialog.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(
                                parent,
                                ex.getMessage(),
                                "Sistema de Ventas - Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            parent,
                            "La contraseña es muy debil...",
                            "Sistema de Ventas - Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(
                        parent,
                        "Las contraseñas no coinciden. Por favor revise y vuelve a intentarlo.",
                        "Sistema de Ventas - Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(
                    parent,
                    "Por favor, complete todos los campos vacíos.",
                    "Sistema de Ventas - Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void createUser(Frame parent, JTable table, JTextField txtFirstname, JTextField txtLastname, JTextField txtUsername, JPasswordField txtPassword, JComboBox cbStatus) {
        String firstname = txtFirstname.getText().toUpperCase();
        String lastname = txtLastname.getText().toUpperCase();
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());
        boolean status = cbStatus.getSelectedIndex() == 0;

        Address address = new Address();
        DetailPerson detailPerson = new DetailPerson();
        Person person = new Person();
        SecurityQuestion sq = new SecurityQuestion();
        User user = new User();

        if (!firstname.isEmpty()
                && !lastname.isEmpty()
                && !username.isEmpty()) {
            address.setState(null);
            address.setCity(null);
            address.setTown(null);
            address.setParish(null);
            address.setAddressDetails(null);

            detailPerson.setPhone(null);

            person.setFirstname(firstname);
            person.setLastname(lastname);
            person.setAddress(address);
            person.setDetailPerson(detailPerson);

            sq.setQuestion1(null);
            sq.setAnswer1(null);
            sq.setQuestion2(null);
            sq.setAnswer2(null);
            sq.setQuestion3(null);
            sq.setAnswer3(null);

            user.setUsername(username);
            user.setPassword(password);
            user.setAccountType(0);
            user.setEnabled(status);
            user.setFirstime(true);
            user.setPerson(person);
            user.setSecurityQuestions(sq);

            try {
                userService.create(user, address, detailPerson, person, sq);
                loadUsers(table);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        parent,
                        ex.getMessage(),
                        "Sistema de Ventas - Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(
                    parent,
                    "Por favor, complete todos los campos requeridos para registrar al usuario.",
                    "Sistema de Ventas - Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void editUser(Frame parent, JTable table, Long id, JTextField txtFirstname, JTextField txtLastname, JTextField txtUsername, JPasswordField txtPassword, JComboBox cbStatus) {
        String firstname = txtFirstname.getText().toUpperCase();
        String lastname = txtLastname.getText().toUpperCase();
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());
        boolean status = cbStatus.getSelectedIndex() == 0;

        User user = userService.findUserById(id);
        Person person = user.getPerson();

        if (!firstname.isEmpty()
                && !lastname.isEmpty()
                && !username.isEmpty()
                && !password.isEmpty()) {
            person.setFirstname(firstname);
            person.setLastname(lastname);
            user.setUsername(username);
            user.setPassword(password);
            user.setEnabled(status);
            user.setFirstime(true);

            try {
                userService.edit(user, null, null, person, null);
                loadUsers(table);

                JOptionPane.showMessageDialog(
                        parent,
                        "Se han realizado los cambios correctamente.",
                        "Sistema de Ventas - Información",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        parent,
                        ex.getMessage(),
                        "Sistema de Ventas - Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(
                    parent,
                    "Por favor, complete los campos requeridos para actualizar la información.",
                    "Sistema de Ventas - Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void editAccount(Frame parent,
            User currentUser,
            JTextField txtFirstname,
            JTextField txtLastname,
            JTextField txtUsername,
            JPasswordField txtPassword,
            JComboBox cbQuestions1, JPasswordField txtAnswer1,
            JComboBox cbQuestions2, JPasswordField txtAnswer2,
            JComboBox cbQuestions3, JPasswordField txtAnswer3,
            JComboBox cbCode, JTextField txtPhone,
            JTextField txtState, JTextField txtCity, JTextField txtTown, JTextField txtParish, JTextField txtDetails,
            JMenu mAccount) {
        String firstname = txtFirstname.getText().toUpperCase();
        String lastname = txtLastname.getText().toUpperCase();
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());
        String question1 = cbQuestions1.getSelectedItem().toString();
        String answer1 = new String(txtAnswer1.getPassword());
        String question2 = cbQuestions2.getSelectedItem().toString();
        String answer2 = new String(txtAnswer2.getPassword());
        String question3 = cbQuestions3.getSelectedItem().toString();
        String answer3 = new String(txtAnswer3.getPassword());
        String code = cbCode.getSelectedItem().toString();
        String phone = txtPhone.getText();
        String state = txtState.getText().toUpperCase();
        String city = txtCity.getText().toUpperCase();
        String town = txtTown.getText().toUpperCase();
        String parish = txtParish.getText().toUpperCase();
        String details = txtDetails.getText().toUpperCase();

        User user = currentUser;
        SecurityQuestion sq = currentUser.getSecurityQuestions();
        DetailPerson detailPerson = currentUser.getPerson().getDetailPerson();
        Address address = currentUser.getPerson().getAddress();
        Person person = currentUser.getPerson();

        if (!firstname.isEmpty() && !lastname.isEmpty()) {
            try {
                if (userService.verifyPassword(currentUser.getUsername(), password)) {
                    person.setFirstname(firstname);
                    person.setLastname(lastname);

                    if (currentUser.getUsername().equals("Ventas") && !username.isEmpty() && !username.equals(currentUser.getUsername())) {
                        throw new Exception("El usuario \"Ventas\" no se puede cambiar.");
                    }

                    if (!username.isEmpty()) {
                        user.setUsername(username);
                    }

                    if (!phone.isEmpty()) {
                        detailPerson.setPhone(code + "-" + phone);
                    }

                    user.setFirstime(false);
                    user.setPassword(password);
                    address.setState(state);
                    address.setCity(city);
                    address.setTown(town);
                    address.setParish(parish);
                    address.setAddressDetails(details);

                    if (!answer1.isEmpty()) {
                        sq.setQuestion1(question1);
                        sq.setAnswer1(answer1);
                    }

                    if (!answer2.isEmpty()) {
                        sq.setQuestion2(question2);
                        sq.setAnswer2(answer2);
                    }

                    if (!answer3.isEmpty()) {
                        sq.setQuestion3(question3);
                        sq.setAnswer3(answer3);
                    }

                    userService.edit(user, address, detailPerson, person, sq);
                    txtUsername.setText("");
                    txtPassword.setText("");
                    cbQuestions1.setSelectedIndex(0);
                    txtAnswer1.setText("");
                    cbQuestions2.setSelectedIndex(0);
                    txtAnswer2.setText("");
                    cbQuestions3.setSelectedIndex(0);
                    txtAnswer3.setText("");

                    mAccount.setText((user.getAccountType() == 1 ? " Administrador: " : "Empleado: ") + user.getPerson().getFirstname() + " " + user.getPerson().getLastname());

                    JOptionPane.showMessageDialog(
                            parent,
                            "Se han realizado los cambios correctamente.",
                            "Sistema de Ventas - Información",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        parent,
                        ex.getMessage(),
                        "Sistema de Ventas - Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(
                    parent,
                    "Por favor, complete los campos requeridos para actualizar la información.",
                    "Sistema de Ventas - Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void deleteUser(Frame parent, JTable table, Long id) {
        try {
            boolean isDeleted = userService.destroy(id);

            if (isDeleted) {
                loadUsers(table);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    parent,
                    ex.getMessage(),
                    "Sistema de Ventas - Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public User loadUserById(Long id,
            JTextField txtFirstname, JTextField txtLastname,
            JTextField txtUsername, JPasswordField txtPassword,
            JComboBox cbStatus, JTextField txtPhone,
            JTextField txtState, JTextField txtCity, JTextField txtTown, JTextField txtParish, JTextField txtDetails) {
        User user = userService.findUserById(id);

        txtFirstname.setText(user.getPerson().getFirstname());
        txtLastname.setText(user.getPerson().getLastname());
        txtUsername.setText(user.getUsername());
        txtPassword.setText(user.getPassword());
        txtPhone.setText((user.getPerson().getDetailPerson().getPhone() != null) ? user.getPerson().getDetailPerson().getPhone() : "");
        txtState.setText((user.getPerson().getAddress().getState() != null) ? user.getPerson().getAddress().getState() : "");
        txtCity.setText((user.getPerson().getAddress().getCity() != null) ? user.getPerson().getAddress().getCity() : "");
        txtTown.setText((user.getPerson().getAddress().getTown() != null) ? user.getPerson().getAddress().getTown() : "");
        txtParish.setText((user.getPerson().getAddress().getParish() != null) ? user.getPerson().getAddress().getParish() : "");
        txtDetails.setText((user.getPerson().getAddress().getAddressDetails() != null) ? user.getPerson().getAddress().getAddressDetails() : "");
        cbStatus.setSelectedItem(user.isEnabled() ? "Activado" : "Desactivado");
        return user;
    }

    public List<User> loadUsers(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        List<User> users = userService.findUsersEntities();

        for (User user : users) {
            if (user.getAccountType() == 0) {
                model.addRow(new Object[]{
                    "U" + user.getUserId(),
                    user.getUsername(),
                    user.getPerson().getFirstname() + " " + user.getPerson().getLastname(),
                    (user.isEnabled() ? "Activado" : "Desactivado")
                });
            }
        }

        return users;
    }

    public List<User> loadUsers(JComboBox<String> combo) {
        List<User> users = userService.findUsersEntities();

        for (User user : users) {
            if (user.getAccountType() == 0) {
                combo.addItem(user.getUserId() + " - " + user.getPerson().getFirstname() + " " + user.getPerson().getLastname());
            }
        }

        return users;
    }

    public void findUserByUsername(Login parent, JTextField txtUserContinue, JButton btnContinue, JButton Cancel) {
        String username = txtUserContinue.getText();

        if (!username.isEmpty()) {
            if (Optional.ofNullable(userService.findUserByUsername(username)).isPresent()) {
                txtUserContinue.setEditable(false);
                btnContinue.setEnabled(false);
                Cancel.setEnabled(false);

                JOptionPane.showMessageDialog(
                        parent,
                        "Se ha encontrado un usuario. Por favor, revise su información\ny responda a la siguiente pregunta secreta.",
                        "Sistema de Ventas - Información",
                        JOptionPane.INFORMATION_MESSAGE);

                Timer timer = new Timer(1500, ex -> {
                    FlatAnimatedLafChange.showSnapshot();
                    parent.switchPanel(new FormVerify(parent, username), 650);
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                });

                timer.setRepeats(false);
                timer.start();
            } else {
                JOptionPane.showMessageDialog(
                        parent,
                        "Usuario no encontrado. Por favor, revise y vuelve a intentarlo.",
                        "Sistema de Ventas - Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(
                    parent,
                    "Por favor, complete el campo vacio para continuar.",
                    "Sistema de Ventas - Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void verify(Login parent, String username,
            JComboBox cbQuestions1, JPasswordField txtAnswer1,
            JComboBox cbQuestions2, JPasswordField txtAnswer2,
            JComboBox cbQuestions3, JPasswordField txtAnswer3,
            JButton btnVerify, JButton Cancel) {
        String question1 = cbQuestions1.getSelectedItem().toString();
        String answer1 = new String(txtAnswer1.getPassword());
        String question2 = cbQuestions2.getSelectedItem().toString();
        String answer2 = new String(txtAnswer2.getPassword());
        String question3 = cbQuestions3.getSelectedItem().toString();
        String answer3 = new String(txtAnswer3.getPassword());

        if (!answer1.isEmpty() && !answer2.isEmpty() && !answer3.isEmpty()) {
            try {
                userService.verify(username, question1, answer1, question2, answer2, question3, answer3);

                txtAnswer1.setEditable(false);
                cbQuestions1.setEnabled(false);
                txtAnswer2.setEditable(false);
                cbQuestions2.setEnabled(false);
                txtAnswer3.setEditable(false);
                cbQuestions3.setEnabled(false);
                btnVerify.setEnabled(false);
                Cancel.setEnabled(false);

                JOptionPane.showMessageDialog(
                        parent,
                        "Enhorabuena! Los datos que has introducido son correctos.",
                        "Sistema de Ventas - Información",
                        JOptionPane.INFORMATION_MESSAGE);

                Timer timer = new Timer(1500, ex -> {
                    FlatAnimatedLafChange.showSnapshot();
                    parent.switchPanel(new FormRecovery(parent, username), 0);
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                });

                timer.setRepeats(false);
                timer.start();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        parent,
                        ex.getMessage(),
                        "Sistema de Ventas - Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(
                    parent,
                    "Por favor, complete el campo vacio para continuar.",
                    "Sistema de Ventas - Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void changePassword(Login parent, String username, JPasswordField txtPassword, JPasswordField txtConfirmPassword, int passwordStrength, JButton btnChangePassword, JButton Cancel) {
        String password = new String(txtPassword.getPassword());
        String confirmPassword = new String(txtConfirmPassword.getPassword());

        if (!password.isEmpty() && !confirmPassword.isEmpty()) {
            if (password.equals(confirmPassword)) {
                if ((passwordStrength == 2) || (passwordStrength == 3)) {
                    try {
                        userService.changePassword(username, password);

                        txtPassword.setEditable(false);
                        txtConfirmPassword.setEditable(false);
                        btnChangePassword.setEnabled(false);
                        Cancel.setEnabled(false);

                        JOptionPane.showMessageDialog(
                                parent,
                                "La contraseña se ha cambiado exitosamente.",
                                "Sistema de Ventas - Información",
                                JOptionPane.INFORMATION_MESSAGE);

                        Timer timer = new Timer(1500, ex -> {
                            ButtonCancel back = new ButtonCancel(parent);
                            back.ActionPerfomedCancel();
                        });

                        timer.setRepeats(false);
                        timer.start();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(
                                parent,
                                ex.getMessage(),
                                "Sistema de Ventas - Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            parent,
                            "La contraseña es muy debil...",
                            "Sistema de Ventas - Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(
                        parent,
                        "Las contraseñas no coinciden. Por favor revise y vuelve a intentarlo.",
                        "Sistema de Ventas - Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(
                    parent,
                    "Por favor, complete todos los campos vacíos.",
                    "Sistema de Ventas - Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}

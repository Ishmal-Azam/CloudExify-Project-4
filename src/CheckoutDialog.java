import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CheckoutDialog extends JDialog {

    private final Customer customer;
    private final ShoppingCart cart;
    private final ECommerceApp app;

    private JTextField nameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField addressField;
    private JTextField cityField;
    private JTextField postalField;

    private JComboBox<String> paymentBox;

    // =====================================================
    // COLORS
    // =====================================================

    private static final Color BG =
            new Color(18, 19, 27);

    private static final Color CARD =
            new Color(27, 28, 39);

    private static final Color FIELD =
            new Color(38, 39, 52);

    private static final Color PURPLE =
            new Color(124, 92, 255);

    private static final Color PURPLE_LIGHT =
            new Color(165, 140, 255);

    private static final Color WHITE =
            Color.WHITE;

    private static final Color MUTED =
            new Color(155, 157, 175);

    private static final Color BORDER =
            new Color(55, 56, 70);


    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public CheckoutDialog(
            ECommerceApp parent,
            Customer customer,
            ShoppingCart cart) {

        super(parent, "Checkout", true);

        this.app = parent;
        this.customer = customer;
        this.cart = cart;

        setSize(650, 700);
        setLocationRelativeTo(parent);
        setResizable(false);

        buildUI();
    }


    // =====================================================
    // BUILD UI
    // =====================================================

    private void buildUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(BG);

        mainPanel.setBorder(
                new EmptyBorder(
                        25, 30, 25, 30));


        // =================================================
        // HEADER
        // =================================================

        JPanel header =
                new JPanel();

        header.setLayout(
                new BoxLayout(
                        header,
                        BoxLayout.Y_AXIS));

        header.setBackground(BG);

        JLabel title =
                new JLabel("CHECKOUT");

        title.setForeground(WHITE);

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        26));

        JLabel subtitle =
                new JLabel(
                        "Enter your delivery details");

        subtitle.setForeground(MUTED);

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13));

        header.add(title);

        header.add(
                Box.createVerticalStrut(5));

        header.add(subtitle);

        mainPanel.add(
                header,
                BorderLayout.NORTH);


        // =================================================
        // FORM
        // =================================================

        JPanel form =
                new JPanel();

        form.setLayout(
                new BoxLayout(
                        form,
                        BoxLayout.Y_AXIS));

        form.setBackground(CARD);

        form.setBorder(
                new EmptyBorder(
                        20, 20, 20, 20));


        // DELIVERY DETAILS

        form.add(
                createSectionLabel(
                        "DELIVERY DETAILS"));

        form.add(
                Box.createVerticalStrut(12));


        // FULL NAME

        form.add(
                createLabel("Full Name"));

        nameField =
                createTextField(
                        "Enter your full name");

        form.add(nameField);

        form.add(
                Box.createVerticalStrut(12));


        // PHONE

        form.add(
                createLabel("Phone Number"));

        phoneField =
                createTextField(
                        "03XX-XXXXXXX");

        form.add(phoneField);

        form.add(
                Box.createVerticalStrut(12));


        // EMAIL

        form.add(
                createLabel("Email"));

        emailField =
                createTextField(
                        "example@email.com");

        form.add(emailField);

        form.add(
                Box.createVerticalStrut(12));


        // ADDRESS

        form.add(
                createLabel(
                        "Delivery Address"));

        addressField =
                createTextField(
                        "House / Street / Area");

        form.add(addressField);

        form.add(
                Box.createVerticalStrut(12));


        // CITY + POSTAL CODE

        JPanel locationPanel =
                new JPanel(
                        new GridLayout(
                                1, 2, 12, 0));

        locationPanel.setBackground(CARD);


        JPanel cityPanel =
                new JPanel();

        cityPanel.setLayout(
                new BoxLayout(
                        cityPanel,
                        BoxLayout.Y_AXIS));

        cityPanel.setBackground(CARD);

        cityPanel.add(
                createLabel("City"));

        cityField =
                createTextField("City");

        cityPanel.add(cityField);


        JPanel postalPanel =
                new JPanel();

        postalPanel.setLayout(
                new BoxLayout(
                        postalPanel,
                        BoxLayout.Y_AXIS));

        postalPanel.setBackground(CARD);

        postalPanel.add(
                createLabel("Postal Code"));

        postalField =
                createTextField(
                        "Postal Code");

        postalPanel.add(postalField);


        locationPanel.add(cityPanel);
        locationPanel.add(postalPanel);

        form.add(locationPanel);

        form.add(
                Box.createVerticalStrut(20));


        // =================================================
        // PAYMENT
        // =================================================

        form.add(
                createSectionLabel(
                        "PAYMENT METHOD"));

        form.add(
                Box.createVerticalStrut(10));


        paymentBox =
                new JComboBox<>(
                        new String[]{
                                "Cash on Delivery",
                                "Card",
                                "Online Payment"
                        });


        paymentBox.setBackground(FIELD);

        paymentBox.setForeground(WHITE);

        paymentBox.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13));

        paymentBox.setPreferredSize(
                new Dimension(0, 42));

        paymentBox.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42));

        form.add(paymentBox);


        mainPanel.add(
                form,
                BorderLayout.CENTER);


        // =================================================
        // BOTTOM BUTTONS
        // =================================================

        JPanel bottom =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                0));

        bottom.setBackground(BG);


        JButton cancelButton =
                new JButton("CANCEL");

        styleSecondaryButton(
                cancelButton);

        cancelButton.addActionListener(
                e -> dispose());


        JButton placeOrderButton =
                new JButton(
                        "PLACE ORDER");

        stylePrimaryButton(
                placeOrderButton);

        placeOrderButton.addActionListener(
                e -> placeOrder());


        bottom.add(cancelButton);
        bottom.add(placeOrderButton);


        mainPanel.add(
                bottom,
                BorderLayout.SOUTH);


        setContentPane(mainPanel);


        // =================================================
        // CUSTOMER INFORMATION
        // =================================================

        if (customer != null) {

            nameField.setText(
                    customer.getName());

            emailField.setText(
                    customer.getEmail());

            phoneField.setText(
                    customer.getPhone());
        }
    }


    // =====================================================
    // LABEL
    // =====================================================

    private JLabel createLabel(
            String text) {

        JLabel label =
                new JLabel(text);

        label.setForeground(MUTED);

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11));

        label.setBorder(
                new EmptyBorder(
                        0, 0, 5, 0));

        return label;
    }


    // =====================================================
    // SECTION LABEL
    // =====================================================

    private JLabel createSectionLabel(
            String text) {

        JLabel label =
                new JLabel(text);

        label.setForeground(
                PURPLE_LIGHT);

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11));

        return label;
    }


    // =====================================================
    // TEXT FIELD
    // =====================================================

    private JTextField createTextField(
            String placeholder) {

        JTextField field =
                new JTextField();

        field.setText(placeholder);

        field.setForeground(MUTED);

        field.setBackground(FIELD);

        field.setCaretColor(WHITE);

        field.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13));

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER),
                        new EmptyBorder(
                                8, 12, 8, 12)));

        field.setPreferredSize(
                new Dimension(0, 42));

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42));


        field.addFocusListener(
                new java.awt.event.FocusAdapter() {

                    @Override
                    public void focusGained(
                            java.awt.event.FocusEvent e) {

                        if (field.getText()
                                .equals(placeholder)) {

                            field.setText("");

                            field.setForeground(
                                    WHITE);
                        }
                    }


                    @Override
                    public void focusLost(
                            java.awt.event.FocusEvent e) {

                        if (field.getText()
                                .trim()
                                .isEmpty()) {

                            field.setText(
                                    placeholder);

                            field.setForeground(
                                    MUTED);
                        }
                    }
                });


        return field;
    }


    // =====================================================
    // PRIMARY BUTTON
    // =====================================================

    private void stylePrimaryButton(
            JButton button) {

        button.setBackground(PURPLE);

        button.setForeground(WHITE);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setOpaque(true);

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        11, 22, 11, 22));

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12));

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR));


        button.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            java.awt.event.MouseEvent e) {

                        button.setBackground(
                                PURPLE_LIGHT);
                    }


                    @Override
                    public void mouseExited(
                            java.awt.event.MouseEvent e) {

                        button.setBackground(
                                PURPLE);
                    }
                });
    }


    // =====================================================
    // SECONDARY BUTTON
    // =====================================================

    private void styleSecondaryButton(
            JButton button) {

        button.setBackground(CARD);

        button.setForeground(MUTED);

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createLineBorder(
                        BORDER));

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12));

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR));
    }


    // =====================================================
    // PLACE ORDER
    // =====================================================

    private void placeOrder() {

        String name =
                nameField.getText().trim();

        String phone =
                phoneField.getText().trim();

        String email =
                emailField.getText().trim();

        String address =
                addressField.getText().trim();

        String city =
                cityField.getText().trim();

        String postal =
                postalField.getText().trim();

        String payment =
                (String) paymentBox
                        .getSelectedItem();


        // =================================================
        // VALIDATION
        // =================================================

        if (name.isEmpty()
                || phone.isEmpty()
                || email.isEmpty()
                || address.isEmpty()
                || city.isEmpty()
                || postal.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all delivery details.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }


        // =================================================
        // PLACEHOLDER CHECK
        // =================================================

        if (name.equals(
                    "Enter your full name")
                || phone.equals(
                    "03XX-XXXXXXX")
                || email.equals(
                    "example@email.com")
                || address.equals(
                    "House / Street / Area")
                || city.equals("City")
                || postal.equals(
                    "Postal Code")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter your actual delivery details.",
                    "Invalid Information",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }


        // =================================================
        // SHOW CUSTOM CONFIRMATION
        // =================================================

        boolean confirmed =
                showOrderConfirmation(
                        address,
                        city,
                        payment);


        if (!confirmed) {
            return;
        }


        // =================================================
        // CREATE ORDER
        // =================================================

        String orderId =
                "ORD"
                + System.currentTimeMillis();

        Order order =
                new Order(
                        orderId,
                        customer,
                        cart);


        // =================================================
        // SAVE ORDER
        // =================================================

        app.addOrder(order);


        // =================================================
        // CLEAR CART
        // =================================================

        cart.clear();


        // =================================================
        // SHOW SUCCESS
        // =================================================

        showOrderSuccess(
                orderId,
                address,
                city);


        // Close checkout
        dispose();
    }


    // =====================================================
    // CONFIRM ORDER DIALOG
    // =====================================================

    private boolean showOrderConfirmation(
            String address,
            String city,
            String payment) {

        final boolean[] confirmed =
                {false};


        JDialog dialog =
                new JDialog(
                        this,
                        "Confirm Order",
                        true);

        dialog.setSize(
                500,
                430);

        dialog.setLocationRelativeTo(
                this);

        dialog.setResizable(false);


        JPanel main =
                new JPanel(
                        new BorderLayout());

        main.setBackground(BG);

        main.setBorder(
                new EmptyBorder(
                        25, 30, 25, 30));


        // =================================================
        // HEADER
        // =================================================

        JPanel header =
                new JPanel();

        header.setLayout(
                new BoxLayout(
                        header,
                        BoxLayout.Y_AXIS));

        header.setBackground(BG);


        JLabel title =
                new JLabel(
                        "CONFIRM ORDER");

        title.setForeground(WHITE);

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        24));


        JLabel subtitle =
                new JLabel(
                        "Please review your order details");

        subtitle.setForeground(MUTED);

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12));


        header.add(title);

        header.add(
                Box.createVerticalStrut(6));

        header.add(subtitle);


        main.add(
                header,
                BorderLayout.NORTH);


        // =================================================
        // DETAILS CARD
        // =================================================

        JPanel details =
                new JPanel();

        details.setLayout(
                new BoxLayout(
                        details,
                        BoxLayout.Y_AXIS));

        details.setBackground(CARD);

        details.setBorder(
                new EmptyBorder(
                        18, 20, 18, 20));


        JLabel deliveryTitle =
                new JLabel(
                        "DELIVERY ADDRESS");

        deliveryTitle.setForeground(
                PURPLE_LIGHT);

        deliveryTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11));


        details.add(deliveryTitle);

        details.add(
                Box.createVerticalStrut(8));


        JLabel addressLabel =
                new JLabel(
                        "<html>"
                        + address
                        + "<br>"
                        + city
                        + "</html>");

        addressLabel.setForeground(WHITE);

        addressLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13));


        details.add(addressLabel);

        details.add(
                Box.createVerticalStrut(18));


        JLabel paymentTitle =
                new JLabel(
                        "PAYMENT METHOD");

        paymentTitle.setForeground(
                PURPLE_LIGHT);

        paymentTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11));


        details.add(paymentTitle);

        details.add(
                Box.createVerticalStrut(8));


        JLabel paymentLabel =
                new JLabel(payment);

        paymentLabel.setForeground(WHITE);

        paymentLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13));


        details.add(paymentLabel);


        main.add(
                details,
                BorderLayout.CENTER);


        // =================================================
        // BUTTONS
        // =================================================

        JPanel buttons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                0));

        buttons.setBackground(BG);


        JButton cancel =
                new JButton("CANCEL");

        styleSecondaryButton(cancel);

        cancel.addActionListener(
                e -> dialog.dispose());


        JButton confirm =
                new JButton(
                        "CONFIRM ORDER");

        stylePrimaryButton(confirm);

        confirm.addActionListener(
                e -> {

                    confirmed[0] = true;

                    dialog.dispose();
                });


        buttons.add(cancel);
        buttons.add(confirm);


        main.add(
                buttons,
                BorderLayout.SOUTH);


        dialog.setContentPane(main);

        dialog.setVisible(true);


        return confirmed[0];
    }


    // =====================================================
    // SUCCESS DIALOG
    // =====================================================

    private void showOrderSuccess(
            String orderId,
            String address,
            String city) {


        JDialog dialog =
                new JDialog(
                        this,
                        "Order Confirmed",
                        true);

        dialog.setSize(
                500,
                400);

        dialog.setLocationRelativeTo(
                this);

        dialog.setResizable(false);


        JPanel main =
                new JPanel(
                        new BorderLayout());

        main.setBackground(BG);

        main.setBorder(
                new EmptyBorder(
                        25, 30, 25, 30));


        // =================================================
        // CHECK ICON
        // =================================================

        JLabel icon =
                new JLabel("✓");

        icon.setHorizontalAlignment(
                SwingConstants.CENTER);

        icon.setForeground(WHITE);

        icon.setBackground(PURPLE);

        icon.setOpaque(true);

        icon.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        28));

        icon.setPreferredSize(
                new Dimension(
                        60,
                        60));


        JPanel iconPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER));

        iconPanel.setBackground(BG);

        iconPanel.add(icon);


        main.add(
                iconPanel,
                BorderLayout.NORTH);


        // =================================================
        // CENTER
        // =================================================

        JPanel center =
                new JPanel();

        center.setLayout(
                new BoxLayout(
                        center,
                        BoxLayout.Y_AXIS));

        center.setBackground(BG);


        JLabel title =
                new JLabel(
                        "ORDER CONFIRMED!");

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        title.setForeground(WHITE);

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        22));


        JLabel message =
                new JLabel(
                        "Your order has been placed successfully.");

        message.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        message.setForeground(MUTED);

        message.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12));


        center.add(title);

        center.add(
                Box.createVerticalStrut(7));

        center.add(message);

        center.add(
                Box.createVerticalStrut(18));


        // =================================================
        // ORDER ID CARD
        // =================================================

        JPanel orderCard =
                new JPanel(
                        new GridLayout(2, 1));

        orderCard.setBackground(CARD);

        orderCard.setBorder(
                new EmptyBorder(
                        10, 18, 10, 18));


        JLabel orderText =
                new JLabel(
                        "ORDER ID");

        orderText.setForeground(
                PURPLE_LIGHT);

        orderText.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10));


        JLabel orderNumber =
                new JLabel(orderId);

        orderNumber.setForeground(WHITE);

        orderNumber.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14));


        orderCard.add(orderText);
        orderCard.add(orderNumber);


        center.add(orderCard);

        center.add(
                Box.createVerticalStrut(14));


        JLabel delivery =
                new JLabel(
                        "Delivery: "
                        + address
                        + ", "
                        + city);

        delivery.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        delivery.setForeground(MUTED);

        delivery.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11));


        center.add(delivery);


        main.add(
                center,
                BorderLayout.CENTER);


        // =================================================
        // DONE BUTTON
        // =================================================

        JPanel bottom =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER));

        bottom.setBackground(BG);


        JButton done =
                new JButton("DONE");

        stylePrimaryButton(done);

        done.addActionListener(
                e -> dialog.dispose());


        bottom.add(done);


        main.add(
                bottom,
                BorderLayout.SOUTH);


        dialog.setContentPane(main);

        dialog.setVisible(true);
    }
}
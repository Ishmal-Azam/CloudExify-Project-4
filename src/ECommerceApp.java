import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class ECommerceApp extends JFrame {

    // =========================================================
    // COLORS
    // =========================================================

    private static final Color BG = new Color(12, 13, 20);
    private static final Color SIDEBAR = new Color(17, 18, 28);
    private static final Color CARD = new Color(24, 25, 37);
    private static final Color CARD_HOVER = new Color(31, 32, 48);
    private static final Color INPUT = new Color(29, 30, 43);

    private static final Color WHITE = new Color(245, 245, 250);
    private static final Color MUTED = new Color(155, 157, 175);

    private static final Color PURPLE = new Color(132, 91, 255);
    private static final Color PURPLE_LIGHT = new Color(158, 125, 255);

    private static final Color GREEN = new Color(73, 205, 140);
    private static final Color RED = new Color(235, 92, 105);
    private static final Color GOLD = new Color(245, 190, 70);

    // =========================================================
    // DATA
    // =========================================================

    private StoreService storeService;
    private ShoppingCart cart;
    private Customer customer;
    private String currentCategory = "All";
    private JButton selectedMenuButton;

    // =========================================================
    // MAIN UI
    // =========================================================

    private JPanel contentPanel;
    private CardLayout cardLayout;

    private JPanel productsGrid;

    private JTextField searchField;

    private JLabel cartBadge;
    private JLabel homeProductCount;
    private JLabel homeCartCount;
    private JLabel homeOrderCount;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ECommerceApp() {

        storeService = new StoreService();
        cart = new ShoppingCart();

        customer = new Customer(
                "CUS001",
                "Guest Customer",
                "guest@example.com",
                "03000000000",
                "Pakistan"
        );

        setupFrame();
        buildInterface();
        showHome();
    }
    public void addOrder(Order order) {
    storeService.addOrder(order);
}
    // =========================================================
    // FRAME
    // =========================================================

    private void setupFrame() {

        setTitle("ECOMMERCE | ONLINE SHOPPING APP");

        setSize(1400, 800);

        setMinimumSize(
                new Dimension(1100, 700));

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(BG);

        setLayout(new BorderLayout());
    }

    // =========================================================
    // MAIN INTERFACE
    // =========================================================

    private void buildInterface() {

    setLayout(new BorderLayout());

    JPanel sidebar = createSidebar();

    add(sidebar, BorderLayout.WEST);

    JPanel mainPanel = new JPanel(new BorderLayout());
    mainPanel.setBackground(BG);

    mainPanel.add(
            createTopBar(),
            BorderLayout.NORTH
    );

    cardLayout = new CardLayout();

    contentPanel = new JPanel(cardLayout);
    contentPanel.setBackground(BG);

    contentPanel.add(
            createHomePage(),
            "HOME"
    );

    contentPanel.add(
            createShopPage(),
            "SHOP"
    );

    contentPanel.add(
            createCartPage(),
            "CART"
    );

    contentPanel.add(
            createOrdersPage(),
            "ORDERS"
    );

    mainPanel.add(
            contentPanel,
            BorderLayout.CENTER
    );

    add(
            mainPanel,
            BorderLayout.CENTER
    );

    cardLayout.show(
            contentPanel,
            "HOME"
    );
}

    // =========================================================
    // SIDEBAR
    // =========================================================

    private JPanel createSidebar() {

        JPanel sidebar =
                new JPanel();

        sidebar.setPreferredSize(
                new Dimension(250, 0));

        sidebar.setBackground(SIDEBAR);

        sidebar.setLayout(
                new BoxLayout(
                        sidebar,
                        BoxLayout.Y_AXIS));

        sidebar.setBorder(
                new EmptyBorder(
                30, 18, 20, 18));

        // LOGO

        JPanel logoPanel =
                new JPanel();

        logoPanel.setOpaque(false);

        logoPanel.setLayout(
                new FlowLayout(
                        FlowLayout.LEFT,
                        5,
                        0));

        JLabel nova =
                new JLabel("NOVA");

        nova.setForeground(WHITE);

        nova.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        27));

        JLabel dot =
                new JLabel(".");

        dot.setForeground(PURPLE);

        dot.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        30));

        logoPanel.add(nova);
        logoPanel.add(dot);

        sidebar.add(logoPanel);

        JLabel subtitle =
                new JLabel("ONLINE SHOPPING APP");

        subtitle.setForeground(MUTED);

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10));

        subtitle.setBorder(
                new EmptyBorder(
                        2, 5, 30, 0));

        sidebar.add(subtitle);

        // MENU LABEL

        JLabel menuLabel =
                new JLabel("EXPLORE");

        menuLabel.setForeground(
                new Color(105, 107, 125));

        menuLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11));

        menuLabel.setBorder(
                new EmptyBorder(
                        0, 5, 10, 0));

        sidebar.add(menuLabel);

        // BUTTONS

        JButton home =
                createMenuButton(
                        "⌂",
                        "Home");

        home.addActionListener(
                e -> showPage("HOME"));

        JButton shop =
                createMenuButton(
                        "◇",
                        "Shop");

        shop.addActionListener(
        e -> {

            currentCategory = "All";

            showPage("SHOP");

            refreshProducts();

        });

        JButton electronics =
                createMenuButton(
                        "◈",
                        "Electronics");

        electronics.addActionListener(
                e -> {
                    currentCategory =
                            "Electronics";

                    showPage("SHOP");
                    refreshProducts();
                });

        JButton books =
                createMenuButton(
                        "▱",
                        "Books");

        books.addActionListener(
                e -> {
                    currentCategory = "Book";

                    showPage("SHOP");
                    refreshProducts();
                });

        JButton clothing =
                createMenuButton(
                        "◇",
                        "Clothing");

        clothing.addActionListener(
                e -> {
                    currentCategory =
                            "Clothing";

                    showPage("SHOP");
                    refreshProducts();
                });

        sidebar.add(home);
        sidebar.add(Box.createVerticalStrut(5));
        sidebar.add(shop);
        sidebar.add(Box.createVerticalStrut(5));
        sidebar.add(electronics);
        sidebar.add(Box.createVerticalStrut(5));
        sidebar.add(books);
        sidebar.add(Box.createVerticalStrut(5));
        sidebar.add(clothing);

        sidebar.add(
                Box.createVerticalGlue());

        // LOWER MENU

        JLabel accountLabel =
                new JLabel("ACCOUNT");

        accountLabel.setForeground(
                new Color(105, 107, 125));

        accountLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11));

        accountLabel.setBorder(
                new EmptyBorder(
                        0, 5, 10, 0));

        sidebar.add(accountLabel);

        JButton cartButton =
                createMenuButton(
                        "🛒",
                        "Shopping Cart");

        cartButton.addActionListener(
                e -> showPage("CART"));

        JButton ordersButton =
                createMenuButton(
                        "▣",
                        "My Orders");

        ordersButton.addActionListener(
                e -> showPage("ORDERS"));

        sidebar.add(cartButton);
        sidebar.add(Box.createVerticalStrut(5));
        sidebar.add(ordersButton);

        sidebar.add(
                Box.createVerticalStrut(20));

        JPanel profile =
                createProfilePanel();

        sidebar.add(profile);

        return sidebar;
    }

    private JButton createMenuButton(
            String icon,
            String text) {

        JButton button =
                new JButton();

        button.setLayout(
                new BorderLayout());

        button.setPreferredSize(
                new Dimension(
                        195,
                        45));

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45));

        button.setBackground(
                SIDEBAR);

        button.setForeground(MUTED);

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        5, 10, 5, 10));

        JLabel iconLabel =
                new JLabel(icon);

        iconLabel.setPreferredSize(
                new Dimension(35, 30));

        iconLabel.setForeground(
                PURPLE_LIGHT);

        iconLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18));

        JLabel textLabel =
                new JLabel(text);

        textLabel.setForeground(MUTED);

        textLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13));

        button.add(
                iconLabel,
                BorderLayout.WEST);

        button.add(
                textLabel,
                BorderLayout.CENTER);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR));

        return button;
    }

    private JPanel createProfilePanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout());

        panel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        65));

        panel.setBackground(CARD);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        45, 46, 60)),
                        new EmptyBorder(
                                8, 10, 8, 10)));

        JLabel avatar =
                new JLabel("G");

        avatar.setHorizontalAlignment(
                SwingConstants.CENTER);

        avatar.setPreferredSize(
                new Dimension(38, 38));

        avatar.setOpaque(true);

        avatar.setBackground(PURPLE);

        avatar.setForeground(WHITE);

        avatar.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        16));

        JLabel name =
                new JLabel(
                        "<html><b>Guest Customer</b>"
                        + "<br><font color='#9B9DAF'>Member</font>"
                        + "</html>");

        name.setForeground(WHITE);

        panel.add(
                avatar,
                BorderLayout.WEST);

        panel.add(
                name,
                BorderLayout.CENTER);

        return panel;
    }

    // =========================================================
    // TOP BAR
    // =========================================================

    private JPanel createTopBar() {

        JPanel top =
                new JPanel(
                        new BorderLayout());

        top.setPreferredSize(
                new Dimension(
                        0,
                        80));

        top.setBackground(BG);

        top.setBorder(
                new EmptyBorder(
                        18, 25, 10, 25));

        JLabel pageTitle =
                new JLabel(
                        "Good evening, Guest 👋");

        pageTitle.setForeground(WHITE);

        pageTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        19));

        top.add(
                pageTitle,
                BorderLayout.WEST);

        JPanel right =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                0));

        right.setOpaque(false);

        searchField =
                new JTextField();

        searchField.setPreferredSize(
                new Dimension(
                        270,
                        40));

        searchField.setBackground(INPUT);

        searchField.setForeground(WHITE);

        searchField.setCaretColor(WHITE);

        searchField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        45, 46, 62)),
                        new EmptyBorder(
                                5, 14, 5, 14)));

        searchField.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13));

        searchField.setToolTipText(
                "Search products");

        searchField.addActionListener(
                e -> searchProducts());

        JButton search =
                createSmallButton(
                        "Search");

        search.addActionListener(
                e -> searchProducts());
        
        searchField.getDocument()
        .addDocumentListener(
                new javax.swing.event.DocumentListener() {

                    public void insertUpdate(
                            javax.swing.event.DocumentEvent e) {

                        refreshProducts();
                    }

                    public void removeUpdate(
                            javax.swing.event.DocumentEvent e) {

                        refreshProducts();
                    }

                    public void changedUpdate(
                            javax.swing.event.DocumentEvent e) {

                        refreshProducts();
                    }
                });

        JButton cart =
                createSmallButton(
                        "🛒");

        cartBadge =
                new JLabel("0");

        cartBadge.setForeground(WHITE);

        cartBadge.setBackground(RED);

        cartBadge.setOpaque(true);

        cartBadge.setHorizontalAlignment(
                SwingConstants.CENTER);

        cartBadge.setPreferredSize(
                new Dimension(
                        22,
                        22));

        cartBadge.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11));

        JPanel cartPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                -8,
                                0));

        cartPanel.setOpaque(false);

        cartPanel.add(cart);
        cartPanel.add(cartBadge);

        cart.addActionListener(
                e -> showPage("CART"));

        right.add(searchField);
        right.add(search);
        right.add(cartPanel);

        top.add(
                right,
                BorderLayout.EAST);

        return top;
    }

    private JButton createSmallButton(
            String text) {

        JButton button =
                new JButton(text);

        button.setBackground(INPUT);

        button.setForeground(WHITE);

        button.setFocusPainted(false);

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12));

        button.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        48, 49, 65)),
                        new EmptyBorder(
                                8, 13, 8, 13)));

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR));

        return button;
    }

    // =========================================================
    // HOME PAGE
    // =========================================================

    private JPanel createHomePage() {

        JPanel page =
                new JPanel(
                        new BorderLayout(
                                20,
                                20));

        page.setBackground(BG);

        page.setBorder(
                new EmptyBorder(
                        5, 25, 25, 25));

        JPanel hero =
                createHero();

        page.add(
                hero,
                BorderLayout.NORTH);

        JPanel lower =
                new JPanel(
                        new BorderLayout(
                                15,
                                15));

        lower.setOpaque(false);

        JPanel stats =
                createStats();

        lower.add(
                stats,
                BorderLayout.NORTH);

        JPanel section =
                new JPanel(
                        new BorderLayout());

        section.setOpaque(false);

        JLabel title =
                new JLabel(
                        "Trending right now");

        title.setForeground(WHITE);

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        21));

        JButton browse =
                createTextButton(
                        "View all →");

        browse.addActionListener(
                e -> showPage("SHOP"));

        section.add(
                title,
                BorderLayout.WEST);

        section.add(
                browse,
                BorderLayout.EAST);

        lower.add(
                section,
                BorderLayout.CENTER);

        page.add(
                lower,
                BorderLayout.CENTER);

        return page;
    }

    private JPanel createHero() {

        JPanel hero =
                new JPanel(
                        new BorderLayout());

        hero.setPreferredSize(
                new Dimension(
                        0,
                        245));

        hero.setBackground(
                new Color(
                        25, 23, 45));

        hero.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        67, 55, 110)),
                        new EmptyBorder(
                                30, 35, 30, 35)));

        JPanel left =
                new JPanel();

        left.setOpaque(false);

        left.setLayout(
                new BoxLayout(
                        left,
                        BoxLayout.Y_AXIS));

        JLabel small =
                new JLabel(
                        "✦ CURATED FOR YOU");

        small.setForeground(
                PURPLE_LIGHT);

        small.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11));

        JLabel heading =
                new JLabel(
                        "<html>Find something<br>"
                        + "worth keeping.</html>");

        heading.setForeground(WHITE);

        heading.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        32));

        heading.setBorder(
                new EmptyBorder(
                        10, 0, 5, 0));

        JLabel description =
                new JLabel(
                        "Explore our collection of products "
                        + "picked for modern living.");

        description.setForeground(MUTED);

        description.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13));

       JButton explore =
        createPrimaryButton(
                "Explore Collection  →");

        explore.setAlignmentX(
                LEFT_ALIGNMENT);

        explore.addActionListener(
                e -> {

            currentCategory = "All";

            showPage("SHOP");

            refreshProducts();
        });

        left.add(small);
        left.add(heading);
        left.add(description);

        left.add(
                Box.createVerticalStrut(18));

        left.add(explore);

        hero.add(
                left,
                BorderLayout.WEST);

        // RIGHT DECORATION

        JPanel visual =
                new JPanel(
                        new GridBagLayout());

        visual.setOpaque(false);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        8, 8, 8, 8);

        JLabel circle =
                new JLabel("N");

        circle.setHorizontalAlignment(
                SwingConstants.CENTER);

        circle.setPreferredSize(
                new Dimension(
                        125,
                        125));

        circle.setOpaque(true);

        circle.setBackground(
                PURPLE);

        circle.setForeground(WHITE);

        circle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        55));

        visual.add(circle, gbc);

        hero.add(
                visual,
                BorderLayout.EAST);

        return hero;
    }

    // =========================================================
    // STATS
    // =========================================================

    private JPanel createStats() {

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                12,
                                0));

        panel.setOpaque(false);

        homeProductCount =
                new JLabel(
                        String.valueOf(
                                storeService
                                        .getProducts()
                                        .size()));

        homeCartCount =
                new JLabel(
                        String.valueOf(
                                cart.getTotalItems()));

        homeOrderCount =
                new JLabel(
                        String.valueOf(
                                storeService
                                        .getOrders()
                                        .size()));

        panel.add(
                createStatCard(
                        "Available Products",
                        homeProductCount,
                        "◇"));

        panel.add(
                createStatCard(
                        "Items In Cart",
                        homeCartCount,
                        "🛒"));

        panel.add(
                createStatCard(
                        "Your Orders",
                        homeOrderCount,
                        "▣"));

        return panel;
    }

    private JPanel createStatCard(
            String title,
            JLabel value,
            String icon) {

        JPanel panel =
                new JPanel(
                        new BorderLayout());

        panel.setBackground(CARD);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        43, 44, 58)),
                        new EmptyBorder(
                                13, 16, 13, 16)));

        JLabel iconLabel =
                new JLabel(icon);

        iconLabel.setForeground(
                PURPLE_LIGHT);

        iconLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        20));

        value.setForeground(WHITE);

        value.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        22));

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setForeground(MUTED);

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11));

        JPanel text =
                new JPanel();

        text.setOpaque(false);

        text.setLayout(
                new BoxLayout(
                        text,
                        BoxLayout.Y_AXIS));

        text.add(value);
        text.add(titleLabel);

        panel.add(
                iconLabel,
                BorderLayout.WEST);

        panel.add(
                text,
                BorderLayout.CENTER);

        return panel;
    }

    // =========================================================
    // SHOP PAGE
    // =========================================================

    private JPanel createShopPage() {

        JPanel page =
                new JPanel(
                        new BorderLayout(
                                15,
                                15));

        page.setBackground(BG);

        page.setBorder(
                new EmptyBorder(
                        5, 25, 25, 25));

        JPanel header =
                new JPanel(
                        new BorderLayout());

        header.setOpaque(false);

        JLabel title =
                new JLabel(
                        "Discover Products");

        title.setForeground(WHITE);

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        25));

        JLabel subtitle =
                new JLabel(
                        "Everything you need, "
                        + "all in one place.");

        subtitle.setForeground(MUTED);

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12));

        JPanel titleBox =
                new JPanel();

        titleBox.setOpaque(false);

        titleBox.setLayout(
                new BoxLayout(
                        titleBox,
                        BoxLayout.Y_AXIS));

        titleBox.add(title);
        titleBox.add(
                Box.createVerticalStrut(4));
        titleBox.add(subtitle);

        header.add(
                titleBox,
                BorderLayout.WEST);

        page.add(
                header,
                BorderLayout.NORTH);

        JPanel center =
                new JPanel(
                        new BorderLayout(
                                10,
                                15));

        center.setOpaque(false);

        JPanel categories =
                createCategoryBar();

        center.add(
                categories,
                BorderLayout.NORTH);

        productsGrid =
                new JPanel();

        productsGrid.setOpaque(false);

        productsGrid.setLayout(
                new GridLayout(
                        0,
                        3,
                        15,
                        15));

        JScrollPane scroll =
                new JScrollPane(
                        productsGrid);

        scroll.setBorder(null);

        scroll.getViewport()
                .setBackground(BG);

        scroll.setBackground(BG);

        center.add(
                scroll,
                BorderLayout.CENTER);

        page.add(
                center,
                BorderLayout.CENTER);

        return page;
    }

    private JPanel createCategoryBar() {

    JPanel bar =
            new JPanel(
                    new FlowLayout(
                            FlowLayout.LEFT,
                            8,
                            0));

    bar.setOpaque(false);

    String[] categories = {
        "All",
        "Electronics",
        "Book",
        "Clothing"
    };

    for (String category : categories) {

        JButton button =
                createCategoryButton(
                        category);

        button.addActionListener(
                e -> {

                    // Change selected category
                    currentCategory =
                            category;

                    // Reset all category buttons
                    for (java.awt.Component component :
                            bar.getComponents()) {

                        if (component instanceof JButton) {

                            component.setBackground(
                                    INPUT);
                        }
                    }

                    // Highlight selected button
                    button.setBackground(
                            PURPLE);

                    // Refresh products
                    refreshProducts();
                });

        bar.add(button);
    }

    return bar;
}

    private JButton createMenuButton1(String icon,String text) {

    JButton button =
            new JButton();

    button.setLayout(
            new BorderLayout());

    button.setPreferredSize(
            new Dimension(
                    195,
                    45));

    button.setMaximumSize(
            new Dimension(
                    Integer.MAX_VALUE,
                    45));

    button.setBackground(
            SIDEBAR);

    button.setForeground(
            MUTED);

    button.setFocusPainted(false);

    button.setBorder(
            BorderFactory.createEmptyBorder(
                    5, 10, 5, 10));

    JLabel iconLabel =
            new JLabel(icon);

    iconLabel.setPreferredSize(
            new Dimension(35, 30));

    iconLabel.setForeground(
            PURPLE_LIGHT);

    iconLabel.setFont(
            new Font(
                    "SansSerif",
                    Font.BOLD,
                    18));

    JLabel textLabel =
            new JLabel(text);

    textLabel.setForeground(MUTED);

    textLabel.setFont(
            new Font(
                    "SansSerif",
                    Font.BOLD,
                    13));

    button.add(
            iconLabel,
            BorderLayout.WEST);

    button.add(
            textLabel,
            BorderLayout.CENTER);

    button.setCursor(
            new Cursor(
                    Cursor.HAND_CURSOR));


    // SELECTED BUTTON EFFECT

    button.addActionListener(
            e -> {

                if (selectedMenuButton != null) {

                    selectedMenuButton.setBackground(
                            SIDEBAR);
                }

                button.setBackground(
                        new Color(45, 48, 75));

                selectedMenuButton = button;
            });


    return button;
}

   private JButton createCategoryButton(
        String text) {

    JButton button =
            new JButton(
                    text.equals("Book")
                            ? "Books"
                            : text);

    button.setFocusPainted(false);

    button.setForeground(WHITE);

    button.setBackground(
            text.equals(currentCategory)
                    ? PURPLE
                    : INPUT);

    button.setFont(
            new Font(
                    "SansSerif",
                    Font.BOLD,
                    12));

    button.setBorder(
            BorderFactory.createEmptyBorder(
                    9, 18, 9, 18));

    button.setCursor(
            new Cursor(
                    Cursor.HAND_CURSOR));

    return button;
}

    // =========================================================
    // PRODUCT CARDS
    // =========================================================

    private JPanel createProductCard(
            Product product) {

        JPanel card =
                new JPanel(
                        new BorderLayout(
                                10,
                                10));

        card.setBackground(CARD);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        45, 46, 61)),
                        new EmptyBorder(
                                15, 15, 15, 15)));

        // PRODUCT VISUAL

        JPanel visual =
                new JPanel(
                        new GridBagLayout());

        visual.setPreferredSize(
                new Dimension(
                        0,
                        125));

        visual.setBackground(
                getProductColor(
                        product));

        JLabel type =
                new JLabel(
                        product.getProductType()
                                .toUpperCase());

        type.setForeground(WHITE);

        type.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11));

        visual.add(type);

        card.add(
                visual,
                BorderLayout.NORTH);

        // DETAILS

        JPanel details =
                new JPanel();

        details.setOpaque(false);

        details.setLayout(
                new BoxLayout(
                        details,
                        BoxLayout.Y_AXIS));

        JLabel name =
                new JLabel(
                        product.getName());

        name.setForeground(WHITE);

        name.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15));

        JLabel id =
                new JLabel(
                        product.getProductID());

        id.setForeground(
                new Color(
                        105, 107, 125));

        id.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        10));

        JLabel rating =
                new JLabel(
                        "★ "
                        + product.getRating()
                        + "   •   "
                        + product.getStock()
                        + " in stock");

        rating.setForeground(
                GOLD);

        rating.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11));

        JLabel price =
                new JLabel(
                        "Rs. "
                        + String.format(
                                "%.0f",
                                product.getPrice()));

        price.setForeground(WHITE);

        price.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        20));

        details.add(name);
        details.add(
                Box.createVerticalStrut(3));
        details.add(id);
        details.add(
                Box.createVerticalStrut(7));
        details.add(rating);
        details.add(
                Box.createVerticalStrut(7));
        details.add(price);

        card.add(
                details,
                BorderLayout.CENTER);

        // BOTTOM BUTTON

        JPanel bottom =
                new JPanel(
                        new BorderLayout());

        bottom.setOpaque(false);

        JButton add =
                createPrimaryButton(
                        product.isAvailable()
                                ? "Add to Cart"
                                : "Out of Stock");

        add.setEnabled(
                product.isAvailable());

        add.addActionListener(
                e -> addProduct(product));

        bottom.add(
                add,
                BorderLayout.CENTER);

        card.add(
                bottom,
                BorderLayout.SOUTH);

        card.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR));

        return card;
    }

    private Color getProductColor(
            Product product) {

        if (product instanceof Electronics) {
            return new Color(
                    40, 34, 72);
        }

        if (product instanceof Book) {
            return new Color(
                    35, 55, 58);
        }

        if (product instanceof Clothing) {
            return new Color(
                    60, 38, 48);
        }

        return new Color(
                40, 40, 55);
    }

    // =========================================================
    // REFRESH PRODUCTS
    // =========================================================

   private void refreshProducts() {

    if (productsGrid == null) {
        return;
    }

    productsGrid.removeAll();

    // IMPORTANT:
    // Always restore the normal product grid layout
    productsGrid.setLayout(
            new GridLayout(
                    0,
                    3,
                    15,
                    15));

    List<Product> products =
            getFilteredProducts();

    if (products.isEmpty()) {

        JLabel empty =
                new JLabel(
                        "No products found.",
                        SwingConstants.CENTER);

        empty.setForeground(MUTED);

        empty.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        16));

        productsGrid.setLayout(
                new BorderLayout());

        productsGrid.add(
                empty,
                BorderLayout.CENTER);

    } else {

        // IMPORTANT:
        // Restore GridLayout when products exist
        productsGrid.setLayout(
                new GridLayout(
                        0,
                        3,
                        15,
                        15));

        for (Product product :
                products) {

            productsGrid.add(
                    createProductCard(
                            product));
        }
    }

    productsGrid.revalidate();
    productsGrid.repaint();
}

    private List<Product> getFilteredProducts() {

        List<Product> result =
                new ArrayList<>();

        String search =
                searchField == null
                        ? ""
                        : searchField
                                .getText()
                                .trim()
                                .toLowerCase();

        for (Product product :
                storeService.getProducts()) {

            boolean categoryMatch =
                    currentCategory.equals("All")
                    ||
                    product.getProductType()
                            .equalsIgnoreCase(
                                    currentCategory);

            boolean searchMatch =
                    search.isEmpty()
                    ||
                    product.getName()
                            .toLowerCase()
                            .contains(search)
                    ||
                    product.getProductID()
                            .toLowerCase()
                            .contains(search)
                    ||
                    product.getProductType()
                            .toLowerCase()
                            .contains(search);

            if (categoryMatch
                    && searchMatch) {

                result.add(product);
            }
        }

        return result;
    }

    private void searchProducts() {

    showPage("SHOP");

    refreshProducts();
}

    // =========================================================
    // CART PAGE
    // =========================================================

    private JPanel createCartPage() {

        JPanel page =
                new JPanel(
                        new BorderLayout(
                                20,
                                20));

        page.setBackground(BG);

        page.setBorder(
                new EmptyBorder(
                        5, 25, 25, 25));

        JLabel title =
                new JLabel(
                        "Your Shopping Cart");

        title.setForeground(WHITE);

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        25));

        page.add(
                title,
                BorderLayout.NORTH);

        JPanel list =
                new JPanel();

        list.setBackground(BG);

        list.setLayout(
                new BoxLayout(
                        list,
                        BoxLayout.Y_AXIS));

        if (cart.isEmpty()) {

            JPanel empty =
                    new JPanel(
                            new GridBagLayout());

            empty.setBackground(CARD);

            JLabel text =
                    new JLabel(
                            "<html><center>"
                            + "<font size='5'>Your cart is empty</font>"
                            + "<br><br>"
                            + "Discover something amazing "
                            + "and add it here."
                            + "</center></html>");

            text.setForeground(MUTED);

            empty.add(text);

            list.add(empty);

        } else {

            for (CartItem item :
                    cart.getItems()) {

                list.add(
                        createCartItem(
                                item));

                list.add(
                        Box.createVerticalStrut(
                                10));
            }
        }

        JScrollPane scroll =
                new JScrollPane(list);

        scroll.setBorder(null);

        scroll.getViewport()
                .setBackground(BG);

        page.add(
                scroll,
                BorderLayout.CENTER);

        JPanel summary =
                createCartSummary();

        page.add(
                summary,
                BorderLayout.EAST);

        return page;
    }

    private JPanel createCartItem(
            CartItem item) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                15,
                                10));

        panel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        105));

        panel.setBackground(CARD);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        45, 46, 60)),
                        new EmptyBorder(
                                15, 15, 15, 15)));

        JPanel icon =
                new JPanel(
                        new GridBagLayout());

        icon.setPreferredSize(
                new Dimension(
                        75,
                        70));

        icon.setBackground(
                getProductColor(
                        item.getProduct()));

        JLabel type =
                new JLabel(
                        item.getProduct()
                                .getProductType()
                                .substring(
                                        0,
                                        1));

        type.setForeground(WHITE);

        type.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        28));

        icon.add(type);

        panel.add(
                icon,
                BorderLayout.WEST);

        JPanel info =
                new JPanel();

        info.setOpaque(false);

        info.setLayout(
                new BoxLayout(
                        info,
                        BoxLayout.Y_AXIS));

        JLabel name =
                new JLabel(
                        item.getProduct()
                                .getName());

        name.setForeground(WHITE);

        name.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15));

        JLabel quantity =
                new JLabel(
                        "Quantity: "
                        + item.getQuantity());

        quantity.setForeground(MUTED);

        quantity.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12));

        JLabel subtotal =
                new JLabel(
                        "Rs. "
                        + String.format(
                                "%.0f",
                                item.getSubtotal()));

        subtotal.setForeground(
                PURPLE_LIGHT);

        subtotal.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        16));

        info.add(name);
        info.add(
                Box.createVerticalStrut(6));
        info.add(quantity);
        info.add(
                Box.createVerticalStrut(6));
        info.add(subtotal);

        panel.add(
                info,
                BorderLayout.CENTER);

        JButton remove =
                createSmallButton(
                        "Remove");

        remove.addActionListener(
                e -> {

                    cart.removeProduct(
                            item.getProduct()
                                    .getProductID());

                    updateCartBadge();

                    showPage("CART");
                });

        panel.add(
                remove,
                BorderLayout.EAST);

        return panel;
    }

    private JPanel createCartSummary() {

        JPanel panel =
                new JPanel();

        panel.setPreferredSize(
                new Dimension(
                        280,
                        0));

        panel.setBackground(CARD);

        panel.setBorder(
                new EmptyBorder(
                        20,
                        20,
                        20,
                        20));

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS));

        JLabel title =
                new JLabel(
                        "Order Summary");

        title.setForeground(WHITE);

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        19));

        JLabel items =
                new JLabel(
                        "Items: "
                        + cart.getTotalItems());

        items.setForeground(MUTED);

        items.setBorder(
                new EmptyBorder(
                        20, 0, 5, 0));

        JLabel total =
                new JLabel(
                        "Rs. "
                        + String.format(
                                "%.0f",
                                cart.getTotal()));

        total.setForeground(WHITE);

        total.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        27));

        JButton checkout =
                createPrimaryButton(
                        "Proceed to Checkout");

        checkout.setAlignmentX(
                LEFT_ALIGNMENT);

        checkout.addActionListener(
        e -> {

            CheckoutDialog dialog =
                    new CheckoutDialog(
                            this,
                            customer,
                            cart
                    );

            dialog.setVisible(true);
        }
);

        JButton clear =
                createSmallButton(
                        "Clear Cart");

        clear.setAlignmentX(
                LEFT_ALIGNMENT);

        clear.addActionListener(
                e -> {

                    cart.clear();

                    updateCartBadge();

                    showPage("CART");
                });

        panel.add(title);
        panel.add(items);
        panel.add(
                Box.createVerticalStrut(5));
        panel.add(
                new JLabel(
                        "Total"));

        JLabel totalCaption =
                new JLabel(
                        "Final amount");

        totalCaption.setForeground(MUTED);

        panel.add(totalCaption);
        panel.add(total);

        panel.add(
                Box.createVerticalStrut(25));

        panel.add(checkout);

        panel.add(
                Box.createVerticalStrut(10));

        panel.add(clear);

        return panel;
    }

    // =========================================================
    // ORDERS
    // =========================================================

    private JPanel createOrdersPage() {

        JPanel page =
                new JPanel(
                        new BorderLayout(
                                15,
                                15));

        page.setBackground(BG);

        page.setBorder(
                new EmptyBorder(
                        5, 25, 25, 25));

        JLabel title =
                new JLabel(
                        "Your Orders");

        title.setForeground(WHITE);

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        25));

        page.add(
                title,
                BorderLayout.NORTH);

        JPanel list =
                new JPanel();

        list.setBackground(BG);

        list.setLayout(
                new BoxLayout(
                        list,
                        BoxLayout.Y_AXIS));

        if (storeService
                .getOrders()
                .isEmpty()) {

            JPanel empty =
                    new JPanel(
                            new GridBagLayout());

            empty.setPreferredSize(
                    new Dimension(
                            0,
                            200));

            empty.setBackground(CARD);

            JLabel text =
                    new JLabel(
                            "No orders yet.");

            text.setForeground(MUTED);

            text.setFont(
                    new Font(
                            "SansSerif",
                            Font.BOLD,
                            17));

            empty.add(text);

            list.add(empty);

        } else {

            for (Order order :
                    storeService.getOrders()) {

                list.add(
                        createOrderCard(
                                order));

                list.add(
                        Box.createVerticalStrut(
                                10));
            }
        }

        JScrollPane scroll =
                new JScrollPane(list);

        scroll.setBorder(null);

        scroll.getViewport()
                .setBackground(BG);

        page.add(
                scroll,
                BorderLayout.CENTER);

        return page;
    }

    private JPanel createOrderCard(
            Order order) {

        JPanel panel =
                new JPanel(
                        new BorderLayout());

        panel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        115));

        panel.setBackground(CARD);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        45, 46, 60)),
                        new EmptyBorder(
                                18, 20, 18, 20)));

        JLabel id =
                new JLabel(
                        "ORDER "
                        + order.getOrderID());

        id.setForeground(PURPLE_LIGHT);

        id.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12));

        JLabel amount =
                new JLabel(
                        "Rs. "
                        + String.format(
                                "%.0f",
                                order.getTotalAmount()));

        amount.setForeground(WHITE);

        amount.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        22));

        JLabel status =
                new JLabel(
                        "● "
                        + order.getStatus());

        status.setForeground(GREEN);

        status.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12));

        JPanel left =
                new JPanel();

        left.setOpaque(false);

        left.setLayout(
                new BoxLayout(
                        left,
                        BoxLayout.Y_AXIS));

        left.add(id);

        left.add(
                Box.createVerticalStrut(
                        10));

        left.add(
                new JLabel(
                        order.getItems()
                                .size()
                                + " product(s)"));

        JLabel products =
                (JLabel) left.getComponent(
                        left.getComponentCount() - 1);

        products.setForeground(MUTED);

        panel.add(
                left,
                BorderLayout.WEST);

        panel.add(
                amount,
                BorderLayout.CENTER);

        panel.add(
                status,
                BorderLayout.EAST);

        return panel;
    }

    // =========================================================
    // ADD PRODUCT
    // =========================================================

    private void addProduct(Product product) {

    final JDialog dialog =
            new JDialog(
                    this,
                    "Add to Cart",
                    true);

    dialog.setSize(430, 330);
    dialog.setLocationRelativeTo(this);
    dialog.setResizable(false);


    JPanel main =
            new JPanel(
                    new BorderLayout());

    main.setBackground(BG);

    main.setBorder(
            new EmptyBorder(
                    25, 25, 25, 25));


    // ================= HEADER =================

    JPanel header =
            new JPanel();

    header.setLayout(
            new BoxLayout(
                    header,
                    BoxLayout.Y_AXIS));

    header.setBackground(BG);


    JLabel title =
            new JLabel(
                    "ADD TO CART");

    title.setForeground(WHITE);

    title.setFont(
            new Font(
                    "SansSerif",
                    Font.BOLD,
                    22));


    JLabel productName =
            new JLabel(
                    product.getName());

    productName.setForeground(
            PURPLE_LIGHT);

    productName.setFont(
            new Font(
                    "SansSerif",
                    Font.BOLD,
                    14));


    header.add(title);

    header.add(
            Box.createVerticalStrut(5));

    header.add(productName);


    main.add(
            header,
            BorderLayout.NORTH);


    // ================= CENTER =================

    JPanel center =
            new JPanel();

    center.setLayout(
            new BoxLayout(
                    center,
                    BoxLayout.Y_AXIS));

    center.setBackground(CARD);

    center.setBorder(
            new EmptyBorder(
                    20, 20, 20, 20));


    JLabel quantityLabel =
            new JLabel(
                    "How many would you like?");

    quantityLabel.setForeground(WHITE);

    quantityLabel.setFont(
            new Font(
                    "SansSerif",
                    Font.BOLD,
                    13));


    center.add(quantityLabel);

    center.add(
            Box.createVerticalStrut(12));


    JTextField quantityField =
            new JTextField("1");

    quantityField.setHorizontalAlignment(
            SwingConstants.CENTER);

    quantityField.setForeground(WHITE);

    quantityField.setBackground(
            new Color(38, 39, 52));

    quantityField.setCaretColor(WHITE);

    quantityField.setFont(
            new Font(
                    "SansSerif",
                    Font.BOLD,
                    18));

    quantityField.setBorder(
        BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(55, 56, 70)),
                new EmptyBorder(
                        8, 10, 8, 10)));

    quantityField.setMaximumSize(
            new Dimension(
                    Integer.MAX_VALUE,
                    45));


    center.add(quantityField);


    main.add(
            center,
            BorderLayout.CENTER);


    // ================= BUTTONS =================

    JPanel buttons =
            new JPanel(
                    new FlowLayout(
                            FlowLayout.RIGHT,
                            10,
                            0));

    buttons.setBackground(BG);


    JButton cancel =
            new JButton(
                    "CANCEL");

    cancel.setBackground(CARD);
cancel.setForeground(MUTED);
cancel.setFocusPainted(false);
cancel.setBorder(
        BorderFactory.createLineBorder(
                new Color(55, 56, 70)));
cancel.setFont(
        new Font("SansSerif", Font.BOLD, 12));
cancel.setCursor(
        new Cursor(Cursor.HAND_CURSOR));

    cancel.addActionListener(
            e -> dialog.dispose());


    JButton confirm =
            new JButton(
                    "ADD TO CART");

    confirm.setBackground(PURPLE);
confirm.setForeground(WHITE);
confirm.setFocusPainted(false);
confirm.setBorderPainted(false);
confirm.setFont(
        new Font("SansSerif", Font.BOLD, 12));
confirm.setCursor(
        new Cursor(Cursor.HAND_CURSOR));

    confirm.addActionListener(
            e -> {

                try {

                    int quantity =
                            Integer.parseInt(
                                    quantityField
                                            .getText()
                                            .trim());


                    if (quantity <= 0) {

                        JOptionPane.showMessageDialog(
                                dialog,
                                "Please enter a quantity greater than zero.",
                                "Invalid Quantity",
                                JOptionPane.WARNING_MESSAGE);

                        return;
                    }


                    // ================= ADD PRODUCT =================

                    cart.addProduct(
                            product,
                            quantity);


                    updateCartBadge();


                    // ================= SUCCESS =================

                    showAddedToCartDialog(
        dialog,
        product,
        quantity
);

dialog.dispose();


                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                            dialog,

                            "Please enter a valid number.",

                            "Invalid Quantity",

                            JOptionPane.WARNING_MESSAGE);


                } catch (OutOfStockException ex) {

                    JOptionPane.showMessageDialog(
                            dialog,

                            ex.getMessage(),

                            "Out of Stock",

                            JOptionPane.WARNING_MESSAGE);
                }
            });


    buttons.add(cancel);
    buttons.add(confirm);


    main.add(
            buttons,
            BorderLayout.SOUTH);


    dialog.setContentPane(main);

    dialog.setVisible(true);
}
 private void showAddedToCartDialog(
        JDialog parent,
        Product product,
        int quantity) {

    JDialog successDialog =
            new JDialog(
                    parent,
                    "Added to Cart",
                    true);

    successDialog.setSize(
            430,
            300);

    successDialog.setLocationRelativeTo(
            parent);

    successDialog.setResizable(false);


    JPanel main =
            new JPanel(
                    new BorderLayout());

    main.setBackground(BG);

    main.setBorder(
            new EmptyBorder(
                    25, 25, 25, 25));


    // ================= ICON =================

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
                    26));

    icon.setPreferredSize(
            new Dimension(
                    55,
                    55));


    JPanel iconPanel =
            new JPanel(
                    new FlowLayout(
                            FlowLayout.CENTER));

    iconPanel.setBackground(BG);

    iconPanel.add(icon);


    main.add(
            iconPanel,
            BorderLayout.NORTH);


    // ================= MESSAGE =================

    JPanel center =
            new JPanel();

    center.setLayout(
            new BoxLayout(
                    center,
                    BoxLayout.Y_AXIS));

    center.setBackground(BG);


    JLabel title =
            new JLabel(
                    "Added to Cart!");

    title.setAlignmentX(
        0.5f);

    title.setForeground(WHITE);

    title.setFont(
            new Font(
                    "SansSerif",
                    Font.BOLD,
                    21));


    JLabel productLabel =
            new JLabel(
                    product.getName()
                    + " × "
                    + quantity);

    productLabel.setAlignmentX(
            0.5f);

    productLabel.setForeground(
            PURPLE_LIGHT);

    productLabel.setFont(
            new Font(
                    "SansSerif",
                    Font.BOLD,
                    13));


    JLabel message =
            new JLabel(
                    "The item has been added successfully.");

    message.setAlignmentX(
            0.5f);

    message.setForeground(MUTED);

    message.setFont(
            new Font(
                    "SansSerif",
                    Font.PLAIN,
                    11));


    center.add(
            title);

    center.add(
            Box.createVerticalStrut(7));

    center.add(
            productLabel);

    center.add(
            Box.createVerticalStrut(5));

    center.add(
            message);


    main.add(
            center,
            BorderLayout.CENTER);


    // ================= BUTTON =================

    JPanel bottom =
            new JPanel(
                    new FlowLayout(
                            FlowLayout.CENTER));

    bottom.setBackground(BG);


    JButton done =
            new JButton("DONE");

    done.setBackground(PURPLE);

    done.setForeground(WHITE);

    done.setFocusPainted(false);

    done.setBorderPainted(false);

    done.setFont(
            new Font(
                    "SansSerif",
                    Font.BOLD,
                    12));

    done.setCursor(
            new Cursor(
                    Cursor.HAND_CURSOR));


    done.addActionListener(
            e -> successDialog.dispose());


    bottom.add(done);


    main.add(
            bottom,
            BorderLayout.SOUTH);


    successDialog.setContentPane(main);

    successDialog.setVisible(true);
}
    // =========================================================
    // CHECKOUT
    // =========================================================

    private void checkout() {

        if (cart.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Your cart is empty.",
                    "Empty Cart",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String[] methods = {
                "Cash on Delivery",
                "Card",
                "Online Payment"
        };

        String method =
                (String) JOptionPane.showInputDialog(
                        this,
                        "Choose your payment method:",
                        "Checkout",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        methods,
                        methods[0]);

        if (method == null) {
            return;
        }

        Order order =
                new Order(
                        "ORD"
                        + (storeService
                                .getOrders()
                                .size() + 1),
                        customer,
                        cart);

        Payment payment =
                new Payment(
                        "PAY"
                        + (storeService
                                .getOrders()
                                .size() + 1),
                        order.getTotalAmount(),
                        method);

        try {

            payment.processPayment();

            for (CartItem item :
                    order.getItems()) {

                storeService.purchase(
                        item.getProduct(),
                        item.getQuantity());
            }

            order.setStatus(
                    "Confirmed");

            storeService.addOrder(order);

            Receipt receipt =
                    new Receipt(order);

            cart.clear();

            updateCartBadge();

            refreshProducts();

            updateHomeStats();

            JOptionPane.showMessageDialog(
                    this,
                    receipt.generateReceipt(),
                    "Order Confirmed!",
                    JOptionPane.INFORMATION_MESSAGE);

            showPage("ORDERS");

        } catch (InvalidPaymentException | OutOfStockException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage());

        }
    }

    // =========================================================
    // UI UPDATES
    // =========================================================

    private void updateCartBadge() {

        cartBadge.setText(
                String.valueOf(
                        cart.getTotalItems()));

        updateHomeStats();
    }

    private void updateHomeStats() {

        if (homeProductCount != null) {

            homeProductCount.setText(
                    String.valueOf(
                            storeService
                                    .getProducts()
                                    .size()));
        }

        if (homeCartCount != null) {

            homeCartCount.setText(
                    String.valueOf(
                            cart.getTotalItems()));
        }

        if (homeOrderCount != null) {

            homeOrderCount.setText(
                    String.valueOf(
                            storeService
                                    .getOrders()
                                    .size()));
        }
    }

    // =========================================================
    // NAVIGATION
    // =========================================================

    private void showPage(
            String page) {

        if (page.equals("SHOP")) {
            refreshProducts();
        }

        if (page.equals("CART")) {

            contentPanel.remove(2);

            contentPanel.add(
                    createCartPage(),
                    "CART",
                    2);
        }

        if (page.equals("ORDERS")) {

            contentPanel.remove(3);

            contentPanel.add(
                    createOrdersPage(),
                    "ORDERS",
                    3);
        }

        contentPanel.revalidate();
        contentPanel.repaint();

        cardLayout.show(
                contentPanel,
                page);
    }

    private void showHome() {

        updateHomeStats();

        cardLayout.show(
                contentPanel,
                "HOME");
    }

    // =========================================================
    // BUTTON STYLES
    // =========================================================

    private JButton createPrimaryButton(
            String text) {

        JButton button =
                new JButton(text);

        button.setBackground(PURPLE);

        button.setForeground(WHITE);

        button.setFocusPainted(false);

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12));

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 18, 10, 18));

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR));

        return button;
    }

    private JButton createTextButton(
            String text) {

        JButton button =
                new JButton(text);

        button.setBackground(BG);

        button.setForeground(
                PURPLE_LIGHT);

        button.setFocusPainted(false);

        button.setBorder(null);

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12));

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR));

        return button;
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(() -> {

            ECommerceApp app =
                    new ECommerceApp();

            app.setVisible(true);
        });
    }
}
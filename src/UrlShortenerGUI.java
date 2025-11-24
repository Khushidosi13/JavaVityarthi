import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UrlShortenerGUI extends JFrame {
    private final UrlShortener shortener;
    private final JTextField longUrlField;
    private final JTextField shortUrlField;
    private final JTextArea outputArea;

    public UrlShortenerGUI() {
        shortener = new UrlShortener();

        setTitle("🔗 Simple URL Shortener");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // ====== Top Panel ======
        JPanel topPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        topPanel.setBorder(BorderFactory.createTitledBorder("Enter URLs"));

        JLabel longLabel = new JLabel("Long URL:");
        JLabel shortLabel = new JLabel("Short URL:");

        longUrlField = new JTextField();
        shortUrlField = new JTextField();

        topPanel.add(longLabel);
        topPanel.add(longUrlField);
        topPanel.add(shortLabel);
        topPanel.add(shortUrlField);

        // ====== Button Panel ======
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton shortenBtn = new JButton("Shorten");
        JButton expandBtn = new JButton("Expand");
        JButton clearBtn = new JButton("Clear");

        shortenBtn.setBackground(new Color(0, 150, 0));
        shortenBtn.setForeground(Color.WHITE);
        expandBtn.setBackground(new Color(0, 102, 204));
        expandBtn.setForeground(Color.WHITE);
        clearBtn.setBackground(new Color(204, 0, 0));
        clearBtn.setForeground(Color.WHITE);

        buttonPanel.add(shortenBtn);
        buttonPanel.add(expandBtn);
        buttonPanel.add(clearBtn);

        // ====== Output Area ======
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        outputArea.setBorder(BorderFactory.createTitledBorder("Output"));

        // ====== Add Components ======
        add(topPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        // ====== Button Actions ======
        shortenBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String longUrl = longUrlField.getText().trim();
                if (longUrl.isEmpty()) {
                    outputArea.setText("⚠️ Please enter a long URL.");
                    return;
                }
                String shortUrl = shortener.shortenUrl(longUrl);
                shortUrlField.setText(shortUrl);
                outputArea.setText("✅ Shortened Successfully!\n\n" +
                        "Long URL : " + longUrl + "\n" +
                        "Short URL: " + shortUrl);
            }
        });

        expandBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String shortUrl = shortUrlField.getText().trim();
                if (shortUrl.isEmpty()) {
                    outputArea.setText("⚠️ Please enter a short URL.");
                    return;
                }
                String longUrl = shortener.expandUrl(shortUrl);
                longUrlField.setText(longUrl);
                outputArea.setText("🔍 Expanded Result:\n\n" +
                        "Short URL: " + shortUrl + "\n" +
                        "Long URL : " + longUrl);
            }
        });

        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                longUrlField.setText("");
                shortUrlField.setText("");
                outputArea.setText("");
            }
        });
    }
}

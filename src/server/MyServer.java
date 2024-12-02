package server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;

import Services.ITinhToan;

public class MyServer {
    private JFrame frame;
    private static JTextArea textArea;
    private JButton startButton, closeButton;
    private Registry registry;

    /**
     * @wbp.parser.entryPoint
     */
    public MyServer() {
        // Tạo cửa sổ GUI
        frame = new JFrame("Server RMI");

        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        // Tạo nút bấm để khởi động server
        startButton = new JButton("Khởi động Server");
        startButton.addActionListener(e -> startServer());

        // Tạo nút bấm để đóng server
        closeButton = new JButton("Tắt Server");
        closeButton.setEnabled(false); // Chỉ bật khi server đang chạy
        closeButton.addActionListener(e -> closeServer());

        // Tạo khu vực hiển thị thông tin client
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Panel chứa các nút bấm
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(startButton);
        buttonPanel.add(closeButton);

        // Thêm các thành phần vào frame
        frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void logMessage(String message) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        textArea.append("[" + timestamp + "] " + message + "\n");
    }

    private void startServer() {
        try {
            // Object `tt` này dùng để tt với DB MySQL
            TinhToan tt = new TinhToan();
            ITinhToan atmInterface = tt;
            registry = LocateRegistry.createRegistry(1099);
            registry.bind("TinhToan", atmInterface);

            logMessage("Server đã khởi động thành công!\nĐang chờ Client yêu cầu...");
            startButton.setEnabled(false); // Vô hiệu hóa nút Start
            closeButton.setEnabled(true); // Kích hoạt nút Close
        } catch (Exception ex) {
            logMessage("Lỗi khi khởi động server: " + ex.getMessage());
        }
    }

    private void closeServer() {
        try {
            if (registry != null) {
                registry.unbind("TinhToan"); // Hủy đăng ký đối tượng
                UnicastRemoteObject.unexportObject(registry, true); // Dừng registry
                logMessage("Server đã được tắt!");
            }
            startButton.setEnabled(true); // Kích hoạt lại nút Start
            closeButton.setEnabled(false); // Vô hiệu hóa nút Close
        } catch (Exception ex) {
            logMessage("Lỗi khi tắt server: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MyServer::new);
    }
}

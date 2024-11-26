package server;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Services.ClientCallback;

import Services.ITinhToan;

public class MyServer{
    private JFrame frame;
    private static JTextArea textArea;
    private JButton startButton;
    
    
    
    public MyServer() {
        // Tạo cửa sổ GUI
        frame = new JFrame("Server RMI");
        
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        // Tạo nút bấm để khởi động server
        startButton = new JButton("Khởi động Server");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startServer();
            }
        });
        
        // Tạo khu vực hiển thị thông tin client
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        // Thêm các thành phần vào frame
        frame.add(startButton, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        
        frame.setVisible(true);
    }
    
    public static void logMessage(String message) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        textArea.append("[" + timestamp + "] " + message + "\n");
    }
    
    
    
    private void startServer() {
        try {
        	//object tt này dùng để tt với db mysql
            TinhToan tt = new TinhToan();
            ITinhToan atmInterface = tt; 
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("TinhToan", atmInterface);
            textArea.append("Server đã khởi động thành công!\nĐang chờ Client yêu cầu...\n");
            
            // Giả sử bạn có phương thức trong `TinhToan` để lắng nghe yêu cầu từ client
            // Cập nhật giao diện khi có yêu cầu từ client bằng cách thêm dòng vào `textArea`

        } catch (Exception ex) {
            textArea.append("Lỗi khi khởi động server: " + ex + "\n");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MyServer());
    }

}

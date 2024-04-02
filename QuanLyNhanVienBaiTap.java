/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package baitapjava;

import java.util.ArrayList;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class QuanLyNhanVienBaiTap extends javax.swing.JFrame {

    ArrayList<QuanLyNhanVien> ArrayNV = new ArrayList<>();
    DefaultTableModel model = new DefaultTableModel();

    String url = "jdbc:sqlserver://localhost:1433; "
            + "databaseName=QuanLyNhanVien;" //Cấu hình theo tên database muốn kết nối
            + "user=sa;"
            + "password=123456;" //Cấu hình theo mật khẩu tài khoản sa
            + "encrypt=true;trustServerCertificate=true";

    /**
     * Creates new form QuanLyNhanVienBaiTap
     */
    public QuanLyNhanVienBaiTap() {
        initComponents();
        setLocationRelativeTo(null);
        model = (DefaultTableModel) tbNhanVien.getModel();
        init();
    }

    public void init() {
        try {
            Connection connect = DriverManager.getConnection(url);
            System.out.println("Ok init");
            String sql = "select * from NhanVien";
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ArrayNV.removeAll(ArrayNV);
            while (rs.next()) {
                QuanLyNhanVien qLNV = new QuanLyNhanVien();

                int i = 1;

                qLNV.setMaNV(rs.getString(i++));
                qLNV.setHotTen(rs.getString(i++));
                qLNV.setGioiTinh(rs.getBoolean(i++));
                qLNV.setDiaChi(rs.getString(i++));
                ArrayNV.add(qLNV);
            }
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi !!!");
        }
        filltable();
    }

    public void filltable() {

        model.setRowCount(0);
        for (QuanLyNhanVien qlnv : ArrayNV) {
            Object[] row = new Object[]{qlnv.getMaNV(), qlnv.getHotTen(), qlnv.isGioiTinh() ?1 :0, qlnv.getDiaChi()};
            model.addRow(row);
        }
        tbNhanVien.setModel(model);

    }

    public void showDetail() {
        int index = tbNhanVien.getSelectedRow();
        QuanLyNhanVien qlnv = ArrayNV.get(index);

        txtMaNV.setText(qlnv.getMaNV());
        txtHoTen.setText(qlnv.getHotTen());
        txtDiaChi.setText(qlnv.getDiaChi());

//        if (qlnv.isGioiTinh()) {
//            rdoNam.setSelected(true);
//        } else {
//            rdoNu.setSelected(true);
//        }
        Object GioiTinh = model.getValueAt(index, 2);
        if (GioiTinh != null) {
            boolean gt = (GioiTinh instanceof Boolean) ? (boolean) GioiTinh : (Integer.parseInt(GioiTinh.toString()) == 1);
            if (gt) {
                rdoNam.setSelected(true);
            } else {
            rdoNu.setSelected(true);
            }
        }
    }

    public boolean CheckSave() {
        if (txtMaNV.getText().isEmpty() || txtHoTen.getText().isEmpty() || txtDiaChi.getText().isEmpty() || buttonGroup1.getSelection() == null) {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống !!!");
        }

        try {
            Connection connect = DriverManager.getConnection(url);
            System.out.println("Ok insert");
            String sqlInsert = "insert into NhanVien values (?, ?, ?, ?)";
            PreparedStatement ps = connect.prepareStatement(sqlInsert);

            int i = 1;
            ps.setString(i++, txtMaNV.getText());
            ps.setString(i++, txtHoTen.getText());
            ps.setInt(i++, rdoNam.isSelected() ? 1 : 0);
            ps.setString(i++, txtDiaChi.getText());

            ps.execute();
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi !!!" + e);
        }

        init();
        return true;
    }

    public boolean checkUpdate() {
        if (txtMaNV.getText().isEmpty() || txtHoTen.getText().isEmpty() || txtDiaChi.getText().isEmpty() || buttonGroup1.getSelection() == null) {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống !!!");
        }
        try {
            Connection connect = DriverManager.getConnection(url);
            System.out.println("OkUpdate");
            String sqlUpdate = "update NhanVien set HoTen = ?, GioiTinh = ?, DiaChi = ? where MaNV = ?";
            PreparedStatement ps = connect.prepareStatement(sqlUpdate);

            int i = 1;

            ps.setString(i++, txtHoTen.getText());
            ps.setInt(i++, rdoNam.isSelected() ? 1 : 0);
            ps.setString(i++, txtDiaChi.getText());
            ps.setString(i++, txtMaNV.getText());

            ps.executeUpdate();
            init();
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi !!!" + e);
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbNhanVien = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Quản Lý Nhân Viên");

        jLabel2.setText("Mã NV:");

        jLabel3.setText("Họ tên:");

        jLabel4.setText("Giới tính:");

        jLabel5.setText("Địa chỉ:");

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        tbNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã NV", "Họ tên", "Giới tính", "Địa chỉ"
            }
        ));
        tbNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbNhanVien);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(43, 43, 43)
                                .addComponent(txtMaNV))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(43, 43, 43)
                                .addComponent(txtHoTen))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(42, 42, 42)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 7, Short.MAX_VALUE))
                                    .addComponent(txtDiaChi)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSave, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnReset, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(73, 73, 73))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(279, 279, 279))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)
                        .addGap(18, 18, 18)
                        .addComponent(btnReset)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(222, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        CheckSave();

    }//GEN-LAST:event_btnSaveActionPerformed

    private void tbNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNhanVienMouseClicked
        // TODO add your handling code here:
        showDetail();
    }//GEN-LAST:event_tbNhanVienMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        checkUpdate();

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        try {
            Connection connect = DriverManager.getConnection(url);
            System.out.println("Ok delete");
            String sqlDelete = "delete from NhanVien where MaNV = ?";
            PreparedStatement ps = connect.prepareStatement(sqlDelete);

            ps.setString(1, txtMaNV.getText());

            ps.execute();
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi !!!" + e);
        }
        init();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        txtMaNV.setText(null);
        txtHoTen.setText(null);
        txtDiaChi.setText(null);
        buttonGroup1.clearSelection();
    }//GEN-LAST:event_btnResetActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVienBaiTap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVienBaiTap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVienBaiTap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVienBaiTap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyNhanVienBaiTap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tbNhanVien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaNV;
    // End of variables declaration//GEN-END:variables
}

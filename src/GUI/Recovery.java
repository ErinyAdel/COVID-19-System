package GUI;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Recovery extends javax.swing.JFrame
{    
    public int count_recovery = 0;
    public int count_valid_recovery = 0;
        
    public Recovery() {
        initComponents();
        setTitle("Recovery");
        ImageIcon img = new ImageIcon("E:\\Faculty\\Summer 2020\\Applications\\Desktop App\\COVID-19\\src\\Images\\health_icon2.png");
        setIconImage(img.getImage());
        getContentPane().setBackground(Color.BLACK);
        setLabelsUnvisible();
    }

    public void num_of_valid_blood_donors()
    {
        String firstPCR, secondPCR;
        int diffDays = 0;
        try 
        {
            String driverClass = "com.mysql.jdbc.Driver";
            String URL = "jdbc:mysql://localhost:3306/covid19_form";
            String username = "root";
            String password = "";

            Class.forName(driverClass);
            Connection conn = (Connection) DriverManager.getConnection(URL,username,password);
            Statement stmt = conn.createStatement();
            String sql     = "SELECT `DateOfFirstPCR`, `DateOfSecondPCR` "
                           + "FROM `persons` WHERE `IsRecovered` = 'Yes' AND `IsDonated` IS NULL;"; 
            ResultSet res = stmt.executeQuery(sql);
            
            while (res.next()) 
            {
                firstPCR = res.getString("DateOfFirstPCR"); secondPCR = res.getString("DateOfSecondPCR");

                String format = "yyyy-MM-dd";
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);  
                LocalDateTime now = LocalDateTime.now();  
                String date2 = dtf.format(now);
                String date1 = null;
                if(secondPCR.equals("") || secondPCR == null) {
                    date1 = firstPCR;
                }
                else {
                    date1 = secondPCR;
                }
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                Date dateObj1 = sdf.parse(date1);
                Date dateObj2 = sdf.parse(date2);
                long diff = dateObj2.getTime() - dateObj1.getTime();
                diffDays = (int) (diff / (24 * 60 * 60 * 1000));
                if(diffDays >= 14) {
                    count_valid_recovery++;
                }
            }
            stmt.close();    
            numOfRecoveryLabel.setText(String.valueOf(count_recovery));
            numOfRecoveryLabel1.setText(String.valueOf(count_valid_recovery));
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void num_of_rec_people()
    {
        try {
            String driverClass = "com.mysql.jdbc.Driver";
            String URL = "jdbc:mysql://localhost:3306/covid19_form";
            String username = "root";
            String password = "";

            Class.forName(driverClass);
            Connection conn = (Connection) DriverManager.getConnection(URL,username,password);

            Statement stmt = conn.createStatement();
            String sql = "SELECT COUNT(1) FROM `persons` WHERE `IsRecovered` = 'Yes' OR `IsRecovered` = 'yes';";
            ResultSet res  = stmt.executeQuery(sql);

            while (res.next()) {
                count_recovery = res.getInt("count(1)");
            }
            stmt.close();
            numOfRecoveryLabel.setText(String.valueOf(count_recovery));
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"MYSQL connector not found!"+ex,"Error",JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(null).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Wrong Query!"+ex,"Error",JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(null).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void setLabelsUnvisible() {
        jLabel1.setVisible(false); jLabel2.setVisible(false);
        numOfRecoveryLabel.setVisible(false); numOfRecoveryLabel1.setVisible(false);
    }
    
    public void setLabelsVisible() {
        jLabel1.setVisible(true); jLabel2.setVisible(true);
        numOfRecoveryLabel.setVisible(true); numOfRecoveryLabel1.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        numOfRecoveryLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        back_btn = new javax.swing.JLabel();
        numOfRecoveryLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        arrow_btn = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(330, 100));

        numOfRecoveryLabel.setBackground(new java.awt.Color(0, 0, 0));
        numOfRecoveryLabel.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        numOfRecoveryLabel.setForeground(new java.awt.Color(255, 255, 255));
        numOfRecoveryLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Untitled-1.png"))); // NOI18N
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        back_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/back_button2.png"))); // NOI18N
        back_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                back_btnMouseClicked(evt);
            }
        });

        numOfRecoveryLabel1.setBackground(new java.awt.Color(0, 0, 0));
        numOfRecoveryLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        numOfRecoveryLabel1.setForeground(new java.awt.Color(255, 255, 255));
        numOfRecoveryLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Untitled-12-Recovered.png"))); // NOI18N
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        arrow_btn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        arrow_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/right-arrow.png"))); // NOI18N
        arrow_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                arrow_btnMouseClicked(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ministry of Health and Population, Egypt");

        jLabel6.setBackground(new java.awt.Color(204, 204, 204));
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Managment protocolfor COVID-19");

        jLabel7.setBackground(new java.awt.Color(204, 204, 204));
        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("patients");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(324, 324, 324)
                .addComponent(arrow_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(210, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(jLabel6)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel7)
                        .addGap(110, 110, 110)))
                .addGap(108, 108, 108)
                .addComponent(back_btn)
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(numOfRecoveryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(numOfRecoveryLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(69, 69, 69))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(arrow_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numOfRecoveryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numOfRecoveryLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(back_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void back_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back_btnMouseClicked
        Home home = new Home();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_back_btnMouseClicked

    private void arrow_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_arrow_btnMouseClicked
        setLabelsVisible();
        arrow_btn.setVisible(false);
        num_of_rec_people();
        num_of_valid_blood_donors();
    }//GEN-LAST:event_arrow_btnMouseClicked

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Recovery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Recovery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Recovery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Recovery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Recovery().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel arrow_btn;
    private javax.swing.JLabel back_btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel numOfRecoveryLabel;
    private javax.swing.JLabel numOfRecoveryLabel1;
    // End of variables declaration//GEN-END:variables
}

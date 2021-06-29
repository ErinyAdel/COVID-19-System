package GUI;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class ListAvaialbeRecoveryingPeople extends javax.swing.JFrame
{
    private String selectedEmail = null, selectedFirstName = null;
    private String content = null;
    
    public ListAvaialbeRecoveryingPeople() 
    {
        initComponents();
        ImageIcon img = new ImageIcon("E:\\Faculty\\Summer 2020\\Applications\\Desktop App\\COVID-19\\src\\Images\\lists_icon.png");
        setIconImage(img.getImage());
        num_of_valid_blood_donors();
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedFirstName = jTable1.getValueAt(jTable1.getSelectedRow(),0).toString();
                selectedEmail     = jTable1.getValueAt(jTable1.getSelectedRow(),4).toString();
                
                Email.to_textField1.setText(selectedEmail);
                content = "Hello ' "+selectedFirstName+" ' ,\n"
                           + "\n"
                           + "How are you? Hope you are in best health.\n"
                           + "\n"
                           + "We need you to donate to use the plasma for saving \n"
                           + "another people.\n"
                           + "\n"
                           + "If you ready to do it, \n"
                           + "• conatct with us in this link \n"
                           + "\"http://localhost/Small%20Form%20(Link%20in%20\n"
                           + "  COVID-19%20Email)/index.php\" ,\n"
                           + "• Fill the form in it to know your health state then\n" 
                           + "  print the form and go to hospital in your time\n"
                           + "  (if you need to change the date ,\n"
                           + "  please contact with us in +20-1201414212).";
                Email.text_TexrField.setText(content);
            }
        });
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
            Statement stmt = (Statement) conn.createStatement();            
            String sql     = "SELECT `FirstName`, `LastName`, `Email`, `PhoneNumber`, `age`, `gender`, `TimesOfPregnant`, `DateOfFirstPCR`, `DateOfSecondPCR` "
                           + "FROM `persons` WHERE `IsRecovered` = 'Yes' AND `IsDonated` IS NULL;"; 
            ResultSet res = stmt.executeQuery(sql);
            
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            Object[] rowData = new Object[7];
            
            while (res.next()) 
            {
                firstPCR = res.getString("DateOfFirstPCR"); 
                secondPCR = res.getString("DateOfSecondPCR");
                
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
                if(diffDays >= 14) 
                {
                    rowData[0] = res.getString("FirstName");
                    rowData[1] = res.getString("LastName");
                    rowData[2] = res.getInt("Age");
                    rowData[3] = res.getString("Gender");
                    rowData[4] = res.getString("Email");
                    rowData[5] = res.getString("PhoneNumber");
                    rowData[6] = res.getInt("TimesOfPregnant");

                    model.addRow(rowData);
                }
            }
            stmt.close();  
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(330, 275));
        setPreferredSize(new java.awt.Dimension(750, 400));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FirstName", "LastName", "Age", "Gender", "Email", "Phone Number", "Time of pregnant"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ListAvaialbeRecoveryingPeople.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListAvaialbeRecoveryingPeople.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListAvaialbeRecoveryingPeople.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListAvaialbeRecoveryingPeople.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListAvaialbeRecoveryingPeople().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

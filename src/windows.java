
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class windows extends javax.swing.JFrame {

    private static final int TIME_VISIBLE = 1000;

    public windows() {
        initComponents();
        getconnection();
        show_products_In_Jtable();

    }
    String ImgPath = null;
    int pos = 0;
    static Connection con;
    String url = "jdbc:mysql://localhost:3306/products_db2";
    String user = "root";
    String password = "123456";

    public Connection getconnection() {

        try {

            con = DriverManager.getConnection(url, user, password);
            lblcheck.setText("DATABASE is Connected");
            checkbtnImage2();

            return con;
        } catch (SQLException e) {
            checkbtnImage();
            lblcheck.setText("DATABASE is not Connected");
            checkbtnImage();
            return null;

        }

    }

    public boolean chechInput() {
        if (txt_Name.getText() == null
                || txt_Price.getText() == null
                || txt_addDate == null) {
            return false;
        } else {
            try {
                Float.parseFloat(txt_Price.getText());
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    // resize Image
    public ImageIcon ResizeImage(String imgPath, byte[] pic) {
        ImageIcon myImage = null;
        if (imgPath != null) {
            myImage = new ImageIcon(imgPath);

        } else {
            myImage = new ImageIcon(pic);
        }
        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(lbl_Image.getWidth(), lbl_Image.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        return image;
    }

    public ArrayList<Product> getProduct() {
        ArrayList<Product> productList = new ArrayList<>();
        con = getconnection();
        String query = "SELECT * FROM products_db2.products";
        Statement st;
        ResultSet rs;
        try {

            st = con.createStatement();
            rs = st.executeQuery(query);
            Product product;

            while (rs.next()) {
                product = new Product(rs.getInt("id"), rs.getString("name"), Float.parseFloat(rs.getString("price")), rs.getString("add_date"), rs.getBytes("image"));
                productList.add(product);
            }

        } catch (SQLException e) {
        }
        return productList;
    }

    public void show_products_In_Jtable() {
        ArrayList<Product> list = getProduct();
        DefaultTableModel model = (DefaultTableModel) table_show.getModel();
        // clear Jtable Content
        model.setRowCount(0);
        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getPrice();
            row[3] = list.get(i).getAddDate();
            model.addRow(row);

        }

    }

    public void checkbtnImage() {

        try {
            Image img = ImageIO.read(getClass().getResource("Image/cloud-off.png"));
            ImageIcon icon = new ImageIcon(img);
            lblcheck.setIcon(icon);
        } catch (IOException e) {

        }
    }

    public void checkbtnImage2() {
        try {
            Image img = ImageIO.read(getClass().getResource("Image/connected.png"));
            ImageIcon icon = new ImageIcon(img);
            lblcheck.setIcon(icon);
        } catch (IOException e) {
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_chooser_Image = new javax.swing.JButton();
        lbl_Image = new javax.swing.JLabel();
        txt_Price = new javax.swing.JTextField();
        txt_Name = new javax.swing.JTextField();
        txt_ID = new javax.swing.JTextField();
        btn_Insert = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btn_First = new javax.swing.JButton();
        btn_Next = new javax.swing.JButton();
        btn_Previous = new javax.swing.JButton();
        btn_Last = new javax.swing.JButton();
        txt_addDate = new com.toedter.calendar.JDateChooser();
        lblcheck = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_show = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 51, 51));
        jPanel1.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Asset Id");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Asset name");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Price");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Date");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Image");

        btn_chooser_Image.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_chooser_Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/image.png"))); // NOI18N
        btn_chooser_Image.setText("Choose Image");
        btn_chooser_Image.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chooser_ImageActionPerformed(evt);
            }
        });

        lbl_Image.setBackground(new java.awt.Color(204, 255, 255));
        lbl_Image.setOpaque(true);

        btn_Insert.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Insert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/add.png"))); // NOI18N
        btn_Insert.setText("Insert");
        btn_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_InsertActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/refresh.png"))); // NOI18N
        jButton4.setText("Update");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/delete.png"))); // NOI18N
        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btn_First.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_First.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/First.png"))); // NOI18N
        btn_First.setText("First");
        btn_First.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_FirstActionPerformed(evt);
            }
        });

        btn_Next.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Nexts.png"))); // NOI18N
        btn_Next.setText("Next");
        btn_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NextActionPerformed(evt);
            }
        });

        btn_Previous.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Previous.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/prevous.png"))); // NOI18N
        btn_Previous.setText("Previous");
        btn_Previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PreviousActionPerformed(evt);
            }
        });

        btn_Last.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Last.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Last.png"))); // NOI18N
        btn_Last.setText("Last");
        btn_Last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LastActionPerformed(evt);
            }
        });

        lblcheck.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblcheck.setForeground(new java.awt.Color(51, 51, 255));
        lblcheck.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        table_show.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NAME", "PRICE", "ADD_DATE"
            }
        ));
        table_show.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_showMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_show);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_Insert)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(11, 11, 11)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbl_Image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_Price)
                                        .addComponent(txt_Name)
                                        .addComponent(btn_chooser_Image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_addDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_First)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Next)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Previous)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Last))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblcheck, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 138, 138))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_addDate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(jLabel5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_chooser_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblcheck, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Insert)
                    .addComponent(jButton4)
                    .addComponent(jButton2)
                    .addComponent(btn_First)
                    .addComponent(btn_Next)
                    .addComponent(btn_Previous)
                    .addComponent(btn_Last))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_chooser_ImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chooser_ImageActionPerformed
        JFileChooser fileChooser = new JFileChooser(new File("C:\\Users\\Babak\\Documents\\NetBeansProjects\\StorPlay\\src\\Image"));

        String[] EXTENSION = new String[]{"jpeg", "jpg", "png"};
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", EXTENSION);

        fileChooser.addChoosableFileFilter(filter);

        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            lbl_Image.setIcon(ResizeImage(path, null));
            ImgPath = path;
        } else {
            System.out.println("No File Selected");

        }
    }//GEN-LAST:event_btn_chooser_ImageActionPerformed

    private void btn_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_InsertActionPerformed

        if (chechInput() && ImgPath != null) {
            try {
                getconnection();
                String sql = "insert into products" + "(name,price,add_date,image)" + "values(?,?,?,?)";

                //sql = String.format(sql, txtID.getText(), txtname.getText(), txtFamily.getText());
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, txt_Name.getText());
                ps.setString(2, txt_Price.getText());
                SimpleDateFormat dateformat = new SimpleDateFormat("MM dd,yyyy");
                String addDate = dateformat.format(txt_addDate.getDate());
                ps.setString(3, addDate);
                InputStream img = new FileInputStream(new File(ImgPath));
                ps.setBlob(4, img);
                ps.executeUpdate();
                show_products_In_Jtable();
                JOptionPane pane = new JOptionPane("insert successfully", JOptionPane.INFORMATION_MESSAGE);
                JDialog dialog = pane.createDialog(null, "Title");
                dialog.setModal(false);
                dialog.setVisible(true);
                new Timer(TIME_VISIBLE, (ActionEvent e1) -> {
                    dialog.setVisible(false);
                }).start();

                //JOptionPane.showMessageDialog(this, "insert successfully ");
            } catch (HeadlessException | FileNotFoundException | SQLException ex) {

                JOptionPane.showMessageDialog(this, ex.getMessage());

            }

        } else {
            JOptionPane.showMessageDialog(null, "One or More field are empty");
        }

    }//GEN-LAST:event_btn_InsertActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (chechInput() && txt_ID.getText() != null) {
            String UpdateQuery = null;
            PreparedStatement ps = null;
            con = getconnection();

            //Update without Image
            if (ImgPath == null) {
                UpdateQuery = "Update products SET name= ?,price= ?" + ",add_date= ? WHERE id=?";
                try {
                    ps = con.prepareStatement(UpdateQuery);
                    ps.setString(1, txt_Name.getText());
                    ps.setString(2, txt_Price.getText());
                    SimpleDateFormat dateformat = new SimpleDateFormat("MM dd,yyyy");
                    String addDate = dateformat.format(txt_addDate.getDate());
                    ps.setString(3, addDate);
                    ps.setInt(4, Integer.parseInt(txt_ID.getText()));
                    ps.executeUpdate();
                    show_products_In_Jtable();

                    // timer for JOptionPane
                    JOptionPane pane = new JOptionPane("جدول با موفقیت آپدیت شد", JOptionPane.INFORMATION_MESSAGE);
                    JDialog dialog = pane.createDialog(null, "Title");
                    dialog.setModal(false);
                    dialog.setVisible(true);

                    new Timer(TIME_VISIBLE, (ActionEvent e1) -> {
                        dialog.setVisible(false);
                    }).start();

                    // JOptionPane.showMessageDialog(this, "The table was successfully updated.");
                } catch (NumberFormatException | SQLException e) {
                    e.getMessage();
                }

            } //update with Image
            else {
                try {
                    InputStream img = new FileInputStream(new File(ImgPath));
                    UpdateQuery = "Update products SET name= ?,price= ?" + ",image=?,add_date= ? WHERE id=?";
                    ps = con.prepareStatement(UpdateQuery);
                    ps.setString(1, txt_Name.getText());
                    ps.setString(2, txt_Price.getText());
                    SimpleDateFormat dateformat = new SimpleDateFormat("MM dd,yyyy");
                    String addDate = dateformat.format(txt_addDate.getDate());
                    ps.setBlob(3, img);
                    ps.setString(4, addDate);

                    ps.setInt(5, Integer.parseInt(txt_ID.getText()));
                    ps.executeUpdate();
                    show_products_In_Jtable();

                    // timer for JOptionPane
                    JOptionPane pane = new JOptionPane("جدول با موفقیت آپدیت شد", JOptionPane.INFORMATION_MESSAGE);
                    JDialog dialog = pane.createDialog(null, "Title");
                    dialog.setModal(false);
                    dialog.setVisible(true);

                    new Timer(TIME_VISIBLE, (ActionEvent e1) -> {
                        dialog.setVisible(false);
                    }).start();

                } catch (FileNotFoundException | NumberFormatException | SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "One or More Fields Are Empty Or Wrong");
        }

    }//GEN-LAST:event_jButton4ActionPerformed
    public void ShowItem(int index) {
        txt_ID.setText(Integer.toString(getProduct().get(index).getId()));
        txt_Name.setText(getProduct().get(index).getName());
        txt_Price.setText(Float.toString(getProduct().get(index).getPrice()));

        try {
            Date newDate = new SimpleDateFormat("MM dd,yyyy").parse((String) getProduct().get(index).getAddDate());
            txt_addDate.setDate(newDate);
        } catch (ParseException ex) {
            Logger.getLogger(windows.class.getName()).log(Level.SEVERE, null, ex);
        }
        lbl_Image.setIcon(ResizeImage(null, getProduct().get(index).getPicture()));
    }
    private void table_showMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_showMouseClicked
        int index = table_show.getSelectedRow();
        ShowItem(index);

    }//GEN-LAST:event_table_showMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            String UpdateQuery = null;
            PreparedStatement ps = null;
            con = getconnection();
            if (table_show.isCellSelected(table_show.getSelectedRow(), table_show.getSelectedColumn())) {
                UpdateQuery = "delete from products_db2.products" + " where id=?";
                ps = con.prepareStatement(UpdateQuery);
                ps.setString(1, Integer.toString((int) table_show.getValueAt(table_show.getSelectedRow(), 0)));
                ps.execute();
                show_products_In_Jtable();
                JOptionPane pane = new JOptionPane("ردیف مورد نظر حذف گردید", JOptionPane.INFORMATION_MESSAGE);
                JDialog dialog = pane.createDialog(null, "Title");
                dialog.setModal(false);
                dialog.setVisible(true);
                new Timer(TIME_VISIBLE, (ActionEvent e1) -> {
                    dialog.setVisible(false);
                }).start();
                //JOptionPane.showMessageDialog(null, "ردیف مورد نظر حذف گردید");
            } else {
                JOptionPane.showMessageDialog(null, "please select row", null, JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException | SQLException e) {
            System.out.println(e.getMessage());
        }
        updateTxT();
    }//GEN-LAST:event_jButton2ActionPerformed
    public void updateTxT() {
        txt_ID.setText(null);
        txt_Name.setText(null);
        txt_Price.setText(null);
        txt_addDate.setDate(null);
        lbl_Image.setIcon(null);
    }
    private void btn_FirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_FirstActionPerformed
        pos = 0;
        ShowItem(pos);
    }//GEN-LAST:event_btn_FirstActionPerformed

    private void btn_LastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LastActionPerformed
        pos = getProduct().size() - 1;
        ShowItem(pos);
    }//GEN-LAST:event_btn_LastActionPerformed

    private void btn_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NextActionPerformed
        pos++;
        if(pos>=getProduct().size())
        {
           pos=getProduct().size()-1;
        }
        ShowItem(pos);
    }//GEN-LAST:event_btn_NextActionPerformed

    private void btn_PreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PreviousActionPerformed
       pos--;
       if(pos<0)
       {
           pos=0;
       }
        ShowItem(pos);
    }//GEN-LAST:event_btn_PreviousActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(windows.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(windows.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(windows.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(windows.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new windows().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btn_First;
    javax.swing.JButton btn_Insert;
    javax.swing.JButton btn_Last;
    javax.swing.JButton btn_Next;
    javax.swing.JButton btn_Previous;
    javax.swing.JButton btn_chooser_Image;
    javax.swing.JButton jButton2;
    javax.swing.JButton jButton4;
    javax.swing.JLabel jLabel1;
    javax.swing.JLabel jLabel2;
    javax.swing.JLabel jLabel3;
    javax.swing.JLabel jLabel4;
    javax.swing.JLabel jLabel5;
    javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    javax.swing.JLabel lbl_Image;
    javax.swing.JLabel lblcheck;
    javax.swing.JTable table_show;
    javax.swing.JTextField txt_ID;
    javax.swing.JTextField txt_Name;
    javax.swing.JTextField txt_Price;
    com.toedter.calendar.JDateChooser txt_addDate;
    // End of variables declaration//GEN-END:variables
}

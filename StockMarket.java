import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;



public class StockMarket extends JFrame implements ActionListener {
    JLabel ProductId,ProductName,Category,Quantity,Price,SupplierName;
    JTextField txtProductId,txtProductName,txtCategory,txtQuantity,txtPrice,txtSupplierName;
    JButton Add,Search,Update,Delete,Clear,btnExit;
    JTable table;
    DefaultTableModel tableModel;
    StockMarket(){
        setTitle("STOCK MARKET MANAGEMENT SYSTEM");
        getContentPane().setBackground(new Color(245,245,245));

        //Label......

        ProductId=new JLabel("Product ID:");
        ProductId.setBounds(50,50,100,30);
        ProductId.setFont(new Font("ARIAL",Font.BOLD,12));
        add(ProductId);

        ProductName=new JLabel("Product Name:");
        ProductName.setBounds(50,100,100,30);
        ProductName.setFont(new Font("ARIAL",Font.BOLD,12));
        add(ProductName);

        Category=new JLabel("Category:");
        Category.setBounds(50,150,100,30);
        Category.setFont(new Font("ARIAL",Font.BOLD,12));
        add(Category);

        Quantity=new JLabel("Quantity:");
        Quantity.setBounds(50,200,100,30);
        Quantity.setFont(new Font("ARIAL",Font.BOLD,12));
        add(Quantity);

        Price=new JLabel("Price:");
        Price.setBounds(50,250,100,30);
        Price.setFont(new Font("ARIAL",Font.BOLD,12));
        add(Price);

        SupplierName=new JLabel("Supplier Name:");
        SupplierName.setBounds(50,300,100,30);
        SupplierName.setFont(new Font("ARIAL",Font.BOLD,12));
        add(SupplierName);

        //textfield.....

        txtProductId=new JTextField();
        txtProductId.setBounds(200,50,150,30);
        txtProductId.setBackground(Color.WHITE);
        add(txtProductId);

        txtProductName=new JTextField();
        txtProductName.setBounds(200,100,150,30);
         txtProductName.setBackground(Color.white);
        add(txtProductName);


        txtCategory=new JTextField();
        txtCategory.setBounds(200,150,150,30);
         txtCategory.setBackground(Color.WHITE);
        add(txtCategory);

        txtQuantity=new JTextField();
        txtQuantity.setBounds(200,200,150,30);
         txtQuantity.setBackground(Color.WHITE);
        add(txtQuantity);

        txtPrice=new JTextField();
        txtPrice.setBounds(200,250,150,30);
         txtPrice.setBackground(Color.WHITE);
        add(txtPrice);

        txtSupplierName=new JTextField();
        txtSupplierName.setBounds(200,300,150,30);
         txtSupplierName.setBackground(Color.WHITE);
        add(txtSupplierName);

        //table...
        tableModel=new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Product ID","Product Name","Category","Quantity","Price","Supplier Name"});        
        table=new JTable(tableModel);
        table.getTableHeader().setBackground(new Color(173,216,230));
        table.setFont(new Font("Seogoe UI",Font.PLAIN,12));
        JScrollPane scrollPane=new JScrollPane(table);
        scrollPane.setBounds(50,350,500,200);
        add(scrollPane);



        //button....

        Add=new JButton("Add");
        Add.setBounds(400,50,100,30);
        Add.setBackground(Color.CYAN);
        Add.setFont(new Font("Arial",Font.BOLD,12));
        add(Add);
        Add.addActionListener(this);

        Search =new JButton("Search");
        Search.setBounds(400,100,100,30);
        Search.setBackground(Color.CYAN);
        Search.setFont(new Font("Arial",Font.BOLD,12));
        add(Search);
        Search.addActionListener(this);

        Update=new JButton("Update");
        Update.setBounds(400,150,100,30);
        Update.setBackground(Color.CYAN);
        Update.setFont(new Font("Arial",Font.BOLD,12));
        add(Update);

        Update.addActionListener(this);
        
        Delete=new JButton("Delete");
        Delete.setBounds(400,200,100,30);
        Delete.setBackground(Color.CYAN);
     Delete.setFont(new Font("Arial",Font.BOLD,12));
        add(Delete);
        Delete.addActionListener(this);

         Clear=new JButton("Clear");
         Clear.setBounds(400,250,100,30);
         Clear.setBackground(Color.CYAN);
        Clear.setFont(new Font("Arial",Font.BOLD,12));
         add(Clear);
            Clear.addActionListener(this);

        btnExit=new JButton("Exit");
        btnExit.setBounds(400,300,100,30);
        btnExit.setBackground(Color.CYAN);
        btnExit.setFont(new Font("Arial",Font.BOLD,12));
        add(btnExit);
        btnExit.addActionListener(this);
        loadTable();

        

         setLayout(null);
        setVisible(true);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100,100,600,600);
    }
    public void actionPerformed(ActionEvent ae){

        if(ae.getSource()==Add){
            addProduct();
        }
        else if(ae.getSource()==Search){
            searchProduct();
        }
        else if(ae.getSource()==Update){
            updateProduct();
        }
        else if(ae.getSource()==Delete){
            deleteProduct();
        }
        else if(ae.getSource()==Clear){
            clear();
        }
        else if(ae.getSource()==btnExit){
            System.exit(0);
        }
        
    }
    private void addProduct(){
        try{
            int Id=Integer.parseInt(txtProductId.getText());
            String Name=txtProductName.getText();
            String category=txtCategory.getText();
            int quantity=Integer.parseInt(txtQuantity.getText());
            String price=txtPrice.getText();
            String supplier=txtSupplierName.getText();

            if(Name.isEmpty()||category.isEmpty()||price.isEmpty()||supplier.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please fill all fields","Error",JOptionPane.ERROR_MESSAGE);
                return;

            }
            Connection con=DBConnection.getConnection();
            String query="INSERT INTO products  VALUES(?,?,?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1, Id);
            ps.setString(2, Name);
            ps.setString(3, category);
            ps.setInt(4, quantity);
            ps.setString(5, price);
            ps.setString(6, supplier);
            int x=ps.executeUpdate();
            
            if(x>0){
                JOptionPane.showMessageDialog(this, "Product added successfully");
                loadTable();
                clear();
                
            
            }else{
                JOptionPane.showMessageDialog(this, "Failed to add product","Error",JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e){
            e.printStackTrace();
            
            
        
    }
}
    private void searchProduct(){
        try{
            int Id=Integer.parseInt(txtProductId.getText());
            Connection con=DBConnection.getConnection();
            String query="SELECT * FROM products WHERE product_id=?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1, Id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                txtProductName.setText(rs.getString("product_name"));
                txtCategory.setText(rs.getString("category"));
                txtQuantity.setText(String.valueOf(rs.getInt("quantity")));
                txtPrice.setText(rs.getString("price"));
                txtSupplierName.setText(rs.getString("supplier_name"));
            }else{
                JOptionPane.showMessageDialog(this, "Product not found","Error",JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        
    }
    private void updateProduct(){
        try{
            int id=Integer.parseInt(txtProductId.getText());
            String name=txtProductName.getText();
            String category=txtCategory.getText();
            int quantity=Integer.parseInt(txtQuantity.getText());
            String price=txtPrice.getText();
      
            String supplier=txtSupplierName.getText();
            Connection con=DBConnection.getConnection();
            String query="UPDATE products SET product_name=?,category=?,quantity=?,price=?,supplier_name=? WHERE product_id=?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, category);
            ps.setInt(3, quantity);
            ps.setString(4, price);
            ps.setString(5, supplier);
            ps.setInt(6, id);
            int n=ps.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(this, "Product updated successfully");
                loadTable();
                clear();
            }
            else{
                JOptionPane.showMessageDialog(this, "Product not found","Error",JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    
    private void deleteProduct(){
        try{

            int id=Integer.parseInt(txtProductId.getText());
            Connection con=DBConnection.getConnection();
            String query="DELETE FROM products WHERE product_id=?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1, id);
            int n=ps.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(this, "Product deleted successfully");
                clear();
                loadTable();
            }
            else{
                JOptionPane.showMessageDialog(this, "Product not found","Error",JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e){
            e.printStackTrace();
        
    }
}

    private void clear(){
        txtProductId.setText("");
        txtProductName.setText("");
        txtCategory.setText("");
        txtQuantity.setText("");
        txtPrice.setText("");
        txtSupplierName.setText("");
    }
    private void loadTable(){
        try{
            tableModel.setRowCount(0);
            Connection con=DBConnection.getConnection();
              String query="SELECT * FROM products";
            Statement st=con.createStatement();
           
            ResultSet rs=st.executeQuery(query);

            while(rs.next()){
                Object[] row={rs.getInt("product_id"),rs.getString("product_name"),rs.getString("category"),rs.getInt("quantity"),rs.getString("price"),rs.getString("supplier_name")};
                tableModel.addRow(row);
            }
        }catch(Exception e){
            e.printStackTrace();




        }
    }
    

    public static void main(String[] args) {
        new StockMarket();
    }
    
}
    
    
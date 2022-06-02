package Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class GUI extends JFrame {

    private JButton insert_button, update_button, delete_button, view_button;
    private JPanel main_panel, insert_panel, update_panel, delete_panel, view_panel;
    private JLabel title;
    Menu menu = new Menu();
    
    // ------------------------------------ menu ------------------------------------ //
    public GUI() {
        initFrame();

        title = new JLabel("Database PT Pudding", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        main_panel = initPanel(500, 500, true, 5, 1, 50, 25);
        insert_panel = insertPanel(500, 500, false, 7, 1, 50, 25);
        update_panel = updatePanel(500, 500, false, 7, 1, 50, 25);
        delete_panel = deletePanel(500, 500, false, 3, 1, 50, 25);
        view_panel = viewPanel(500, 500, false, 3, 1, 50, 25);

        insert_button = initButton("Insert Menu", 40, 50, false, true, false, false, false);
        update_button = initButton("Update Menu", 40, 50, false, false, true, false, false);
        delete_button = initButton("Delete Menu", 40, 50, false, false, false, true, false);
        view_button = initButton("View Menu", 40, 50, false, false, false, false, true);

        main_panel.add(title);
        main_panel.add(insert_button);
        main_panel.add(update_button);
        main_panel.add(delete_button);
        main_panel.add(view_button);

        this.add(main_panel);
        this.add(insert_panel);
        this.add(insert_panel);
        this.add(update_panel);
        this.add(delete_panel);
        this.add(view_panel);
        
//        SwingUtilities.updateComponentTreeUI(this);
        this.invalidate();
        this.validate();
        this.repaint();
    }

    // ------------------------------------ init ------------------------------------ //
    private void initFrame() {
        this.setVisible(true);
        this.setTitle("Database PT Pudding");
        this.setSize(525, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private JPanel initPanel(Integer width, Integer height, Boolean visible, Integer rows, Integer cols, Integer hgap, Integer vgap) {
        JPanel temp_panel = new JPanel();
        temp_panel.setLayout(new GridLayout(rows, cols, hgap, vgap));
        temp_panel.setSize(width, height);
        temp_panel.setVisible(visible);
        return temp_panel;
    }

    private JButton initButton(String text, Integer width, Integer height, Boolean main, Boolean insert, Boolean update, Boolean delete, Boolean view) {
        JButton temp_button = new JButton(text);
        temp_button.setPreferredSize(new Dimension(width, height));
        temp_button.setMaximumSize(new Dimension(width, height));
        temp_button.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    main_panel.setVisible(main);
                    insert_panel.setVisible(insert);
                    update_panel.setVisible(update);
                    delete_panel.setVisible(delete);
                    view_panel.setVisible(view);
//                    SwingUtilities.updateComponentTreeUI(this);
                    invalidate();
                    validate();
                    repaint();
                }
            }
        );
        return temp_button;
    }

    private JPanel inputPanel(String text, Integer width, Integer height, Boolean visible, Integer rows, Integer cols, Integer hgap, Integer vgap, JTextField temp_input) {
        JPanel temp_panel = initPanel(525, 50, true, 1, 2, 0, 0);
        JLabel temp_label = new JLabel(text);
        temp_panel.add(temp_label);
        temp_panel.add(temp_input);
        return temp_panel;
    }

    // ------------------------------------ insert ------------------------------------ //
    private JPanel insertPanel(Integer width, Integer height, Boolean visible, Integer rows, Integer cols, Integer hgap, Integer vgap) {
        JLabel title = new JLabel("Insert Menu", SwingConstants.CENTER);
        title.setBounds(100, 50, 200, 50);
        title.setFont(new Font("Sans-Serif", Font.BOLD, 20));

        JTextField kode_field = new JTextField(1);
        JTextField nama_field = new JTextField(1);
        JTextField harga_field = new JTextField(1);
        JTextField stok_field = new JTextField(1);

	    JPanel kode_panel = inputPanel("Kode Menu", 525, 50, true, 1, 2, 0, 0, kode_field);
	    JPanel nama_panel = inputPanel("Nama Menu", 525, 50, true, 1, 2, 0, 0, nama_field);
	    JPanel harga_panel = inputPanel("Harga Menu", 525, 50, true, 1, 2, 0, 0, harga_field);
	    JPanel stok_panel = inputPanel("Stok Menu", 525, 50, true, 1, 2, 0, 0, stok_field);

        JButton submit_button = initButton("Submit", 40, 50, false, true, false, false, false);
        submit_button.addActionListener(
        	new ActionListener() {
        		public void actionPerformed(ActionEvent ae) {
        			String kode = kode_field.getText();
        			String nama = nama_field.getText();
        			Integer harga = Integer.parseInt(harga_field.getText());
        			Integer stok = Integer.parseInt(stok_field.getText());
        			
        			Integer option = JOptionPane.showConfirmDialog(null, "Are You Sure Want To Insert " + nama + "?", "Insert", JOptionPane.YES_NO_OPTION);
        			if(option == 0 && !kode.isEmpty()) {
        				String query = "'" +kode + "', '" + nama + "', '" + harga + "', '" + stok + "'";
            			menu.insertQuery(query);
        			}
        		}
        	}
        );
        
        JButton back_button = initButton("Back", 40, 50, true, false, false, false, false);

        JPanel temp_panel = initPanel(width, height, visible, rows, cols, hgap, vgap);
        temp_panel.add(title);
        temp_panel.add(kode_panel);
        temp_panel.add(nama_panel);
        temp_panel.add(harga_panel);
        temp_panel.add(stok_panel);
        temp_panel.add(submit_button);
        temp_panel.add(back_button);
        
//        this.remove(insert_panel);
//        SwingUtilities.updateComponentTreeUI(this);
        this.invalidate();
        this.validate();
        this.repaint();
        return temp_panel;
    }

    // ------------------------------------ update ------------------------------------ //
    private JPanel updatePanel(Integer width, Integer height, Boolean visible, Integer rows, Integer cols, Integer hgap, Integer vgap) {
    	JLabel title = new JLabel("Update Menu", SwingConstants.CENTER);
        title.setBounds(100, 50, 200, 50);
        title.setFont(new Font("Sans-Serif", Font.BOLD, 20));

        JTextField kode_field = new JTextField(1);
        JTextField nama_field = new JTextField(1);
        JTextField harga_field = new JTextField(1);
        JTextField stok_field = new JTextField(1);

	    JPanel kode_panel = inputPanel("Kode Menu", 525, 50, true, 1, 2, 0, 0, kode_field);
	    JPanel nama_panel = inputPanel("Nama Menu", 525, 50, true, 1, 2, 0, 0, nama_field);
	    JPanel harga_panel = inputPanel("Harga Menu", 525, 50, true, 1, 2, 0, 0, harga_field);
	    JPanel stok_panel = inputPanel("Stok Menu", 525, 50, true, 1, 2, 0, 0, stok_field);

        JButton submit_button = initButton("Submit", 40, 50, false, true, false, false, false);
        submit_button.addActionListener(
        	new ActionListener() {
        		public void actionPerformed(ActionEvent ae) {
        			String kode = kode_field.getText();
        			String nama = nama_field.getText();
        			Integer harga = Integer.parseInt(harga_field.getText());
        			Integer stok = Integer.parseInt(stok_field.getText());
        			
        			Integer option = JOptionPane.showConfirmDialog(null, "Are You Sure Want To Update " + nama + "?", "Insert", JOptionPane.YES_NO_OPTION);
        			if(option == 0 && !kode.isEmpty()) {
            			menu.updateQuery(kode, nama, harga, stok);
        			}
        		}
        	}
        );
        
        JButton back_button = initButton("Back", 40, 50, true, false, false, false, false);

        JPanel temp_panel = initPanel(width, height, visible, rows, cols, hgap, vgap);
        temp_panel.add(title);
        temp_panel.add(kode_panel);
        temp_panel.add(nama_panel);
        temp_panel.add(harga_panel);
        temp_panel.add(stok_panel);
        temp_panel.add(submit_button);
        temp_panel.add(back_button);
        
//        this.remove(update_panel);
//        SwingUtilities.updateComponentTreeUI(this);
        this.invalidate();
        this.validate();
        this.repaint();
        return temp_panel;
    }

    // ------------------------------------ delete ------------------------------------ //
    private JPanel deletePanel(Integer width, Integer height, Boolean visible, Integer rows, Integer cols, Integer hgap, Integer vgap) {
        JLabel title = new JLabel("Delete Menu", SwingConstants.CENTER);
        title.setBounds(100, 50, 200, 50);
        title.setFont(new Font("Sans-Serif", Font.BOLD, 20));

        menu.rs = menu.getTabel();
        
        String[][] data = new String[1000000][4];
        Integer i = 0;
        
        try {
        	while(menu.rs.next()) {
                String kode = menu.rs.getString("kode");
                String nama = menu.rs.getString("nama");
                String harga = menu.rs.getString("harga");
                String stok = menu.rs.getString("stok");

                data[i][0] = kode;
                data[i][1] = nama;
                data[i][2] = harga;
                data[i][3] = stok;
                i++;
        	}
        }
        catch (SQLException e) {
        	e.printStackTrace();
        }

        String[] kolom = {"kode", "nama", "harga", "stok"};

        JTable table = new JTable(data, kolom) {
			private static final long serialVersionUID = 1L;
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        
        table.setFocusable(false);
        table.addMouseListener(
            new MouseAdapter() {
                public void mouseClicked(MouseEvent mouse) {
                    if (mouse.getClickCount() == 2) {
                        JTable selected = (JTable)mouse.getSource();
                        Integer row = selected.getSelectedRow();
                        if(row > 0) {
                        	Integer option = JOptionPane.showConfirmDialog(null, "Are You Sure Want To Delete " + data[row][0] + "?", "Delete", JOptionPane.YES_NO_OPTION);
	                        if(option == 0) {
	                        	menu.deleteRow(data[row][0]);
	                        	table.removeRowSelectionInterval(row, row);
	                        }
                        }
                    }
                }
            }
        );

        JScrollPane scrollpane = new JScrollPane(table);

        JButton back_button = initButton("Back", 40, 50, true, false, false, false, false);

        JPanel temp_panel = initPanel(width, height, visible, rows, cols, hgap, vgap);
        temp_panel.add(title);
        temp_panel.add(scrollpane);
        temp_panel.add(back_button);
        
//        this.remove(update_panel);
//        SwingUtilities.updateComponentTreeUI(this);
        this.invalidate();
        this.validate();
        this.repaint();
        return temp_panel;
    }
    
    // ------------------------------------ view ------------------------------------ //
    private JPanel viewPanel(Integer width, Integer height, Boolean visible, Integer rows, Integer cols, Integer hgap, Integer vgap) {
        JLabel title = new JLabel("View Menu", SwingConstants.CENTER);
        title.setBounds(100, 50, 200, 50);
        title.setFont(new Font("Sans-Serif", Font.BOLD, 20));

        menu.rs = menu.getTabel();
        
        String[][] data = new String[1000000][4];
        Integer i = 0;
        
        try {
        	while(menu.rs.next()) {
                String kode = menu.rs.getString("kode");
                String nama = menu.rs.getString("nama");
                String harga = menu.rs.getString("harga");
                String stok = menu.rs.getString("stok");

                data[i][0] = kode;
                data[i][1] = nama;
                data[i][2] = harga;
                data[i][3] = stok;
                i++;
        	}
        }
        catch (SQLException e) {
        	e.printStackTrace();
        }

        String[] kolom = {"kode", "nama", "harga", "stok"};

        JTable table = new JTable(data, kolom) {
			private static final long serialVersionUID = 1L;
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        
        table.setFocusable(false);
        JScrollPane scrollpane = new JScrollPane(table);

        JButton back_button = initButton("Back", 40, 50, true, false, false, false, false);

        JPanel temp_panel = initPanel(width, height, visible, rows, cols, hgap, vgap);
        temp_panel.add(title);
        temp_panel.add(scrollpane);
        temp_panel.add(back_button);

//        this.remove(view_panel);
//        SwingUtilities.updateComponentTreeUI(this);
        this.invalidate();
        this.validate();
        this.repaint();
        return temp_panel;
    }
}
    

    
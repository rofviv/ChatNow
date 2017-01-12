package view;

import controller.ConexionServidorController;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Mensaje;
import model.Usuario;

public class Principal extends javax.swing.JFrame {

    private Usuario user;
    private ConexionServidorController conexion;
    Date fechaActual;
    private DateFormat hora = new SimpleDateFormat("HH:mm");

    public Principal() {
        initComponents();
        txtEntradaTexto.requestFocus();
    }

    public void usuario(Usuario user, ConexionServidorController conexion) {
        this.user = user;
        this.conexion = conexion;
        conexion.iniciar();
        lblConectado.setText("<html><body>" + lblConectado.getText() + "<b>" + user.getNombre() + "</b>" + "</body></html>");
        lblDestinatario.setText("<html><body>" + lblDestinatario.getText() + "<b>" + "Todos" + "</b>" + "</body></html>");
    }

    private void enviarMensajeMasivo(int tipo, String mensaje) {
        fechaActual = new Date();
        Mensaje msj = new Mensaje(this.user, null, tipo, mensaje, hora.format(fechaActual));
        conexion.enviarMensaje(msj);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlConectados = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lsUsuarios = new javax.swing.JList<>();
        lblTitulo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnEnviar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        pnlVerMensajes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtVerMensajes = new javax.swing.JTextArea();
        pnlEntradaTexto = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtEntradaTexto = new javax.swing.JTextArea();
        lblCantLetras = new javax.swing.JLabel();
        lblDestinatario = new javax.swing.JLabel();
        lblConectado = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlConectados.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Conectados")));

        jScrollPane3.setViewportView(lsUsuarios);

        javax.swing.GroupLayout pnlConectadosLayout = new javax.swing.GroupLayout(pnlConectados);
        pnlConectados.setLayout(pnlConectadosLayout);
        pnlConectadosLayout.setHorizontalGroup(
            pnlConectadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConectadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlConectadosLayout.setVerticalGroup(
            pnlConectadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConectadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        lblTitulo.setText("ChatNow");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        pnlVerMensajes.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Chat")));

        txtVerMensajes.setEditable(false);
        txtVerMensajes.setColumns(20);
        txtVerMensajes.setLineWrap(true);
        txtVerMensajes.setRows(5);
        txtVerMensajes.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtVerMensajes);

        javax.swing.GroupLayout pnlVerMensajesLayout = new javax.swing.GroupLayout(pnlVerMensajes);
        pnlVerMensajes.setLayout(pnlVerMensajesLayout);
        pnlVerMensajesLayout.setHorizontalGroup(
            pnlVerMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVerMensajesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        pnlVerMensajesLayout.setVerticalGroup(
            pnlVerMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVerMensajesLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlEntradaTexto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Mensaje")));

        txtEntradaTexto.setColumns(20);
        txtEntradaTexto.setLineWrap(true);
        txtEntradaTexto.setRows(5);
        txtEntradaTexto.setTabSize(4);
        txtEntradaTexto.setWrapStyleWord(true);
        txtEntradaTexto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEntradaTextoKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEntradaTextoKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(txtEntradaTexto);

        lblCantLetras.setText("0/255");

        javax.swing.GroupLayout pnlEntradaTextoLayout = new javax.swing.GroupLayout(pnlEntradaTexto);
        pnlEntradaTexto.setLayout(pnlEntradaTextoLayout);
        pnlEntradaTextoLayout.setHorizontalGroup(
            pnlEntradaTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradaTextoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEntradaTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(pnlEntradaTextoLayout.createSequentialGroup()
                        .addComponent(lblCantLetras)
                        .addGap(0, 449, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlEntradaTextoLayout.setVerticalGroup(
            pnlEntradaTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradaTextoLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCantLetras)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblDestinatario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDestinatario.setText("Mensaje para ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlEntradaTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlVerMensajes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEnviar)))
                .addGap(14, 14, 14))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lblDestinatario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlVerMensajes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEntradaTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar)
                    .addComponent(btnEnviar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblConectado.setText("Conectado como ");

        jMenu1.setText("Menu");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlConectados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblConectado, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTitulo)
                        .addGap(275, 275, 275)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblConectado)
                    .addComponent(lblTitulo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlConectados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        enviarMensajeMasivo(3, txtEntradaTexto.getText());
        txtEntradaTexto.setText("");
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtEntradaTexto.setText("");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtEntradaTextoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntradaTextoKeyPressed
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                enviarMensajeMasivo(3, txtEntradaTexto.getText());
                txtEntradaTexto.setText("");
        }
    }//GEN-LAST:event_txtEntradaTextoKeyPressed

    private void txtEntradaTextoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntradaTextoKeyTyped

    }//GEN-LAST:event_txtEntradaTextoKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        enviarMensajeMasivo(0, "");
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblCantLetras;
    public static javax.swing.JLabel lblConectado;
    private javax.swing.JLabel lblDestinatario;
    private javax.swing.JLabel lblTitulo;
    public static javax.swing.JList<String> lsUsuarios;
    private javax.swing.JPanel pnlConectados;
    private javax.swing.JPanel pnlEntradaTexto;
    private javax.swing.JPanel pnlVerMensajes;
    public static javax.swing.JTextArea txtEntradaTexto;
    public static javax.swing.JTextArea txtVerMensajes;
    // End of variables declaration//GEN-END:variables
}
